package com.duofuen.elab.web;

import com.duofuen.elab.domain.Page;
import com.duofuen.elab.dto.PageDto;
import com.duofuen.elab.service.PageService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class PageController {

    private final PageService pageService;

    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }


    @RequestMapping("/listPage")
    public String listCharacter(Model model) {
        model.addAttribute("listPage", pageService.findAll());
        return "listPage";
    }

    @RequestMapping("/detailPage")
    public String detailPage(Integer id, Model model) {
        Page page = pageService.findById(id);
        model.addAttribute("page", new PageDto(page));
        return "detailPage";
    }

    @Transactional
    @PostMapping(path = "/savePage")
    @ResponseBody
    public String savePage(@ModelAttribute PageDto pageDto) {
        String retMsg = "修改成功";


        if (pageDto.getId() == null) {
            MultipartFile file = pageDto.getFile();
            InputStream fis = null;
            String md5 = null;
            try {
                fis = file.getInputStream();
                md5 = DigestUtils.md5Hex(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int uploadResult = pageService.uploadFile(file, md5);
            if (uploadResult == 1) {
                return "解压失败!";
            } else if (uploadResult == -1) {
                return "上传失败";
            }
            retMsg = "上传成功";
            pageDto.setUrl("/h5/" + md5 + "/home.html");
            pageDto.setFileName(file.getOriginalFilename());
        }

        Page page = new Page();
        page.setId(pageDto.getId());
        page.setComment(pageDto.getComment());
        page.setFileName(pageDto.getFileName());
        page.setUrl(pageDto.getUrl());
        pageService.save(page);

        return retMsg;
    }

    @Transactional
    @RequestMapping("/deletePage")
    @ResponseBody
    public String deletePage(Integer id) {
        Page page = pageService.findById(id);

        boolean deleted = pageService.deleteById(id);

        if (!deleted) {
            return "删除失败！";
        }
        String folder = page.getUrl().replace("/home.html", "");
        boolean deletedFile = pageService.deleteFile("/var/www" + folder);
        if (!deletedFile) {
            return "删除文件失败";
        }
        return "删除成功！";
    }
}
