package com.duofuen.elab.web;

import com.duofuen.elab.domain.Banner;
import com.duofuen.elab.domain.Image;
import com.duofuen.elab.domain.ImageRepository;
import com.duofuen.elab.dto.BannerDto;
import com.duofuen.elab.service.BannerService;
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

@Controller
public class BannerController {

    private final BannerService bannerService;
    private final ImageRepository imageRepository;

    @Autowired
    public BannerController(BannerService bannerService, ImageRepository imageRepository) {
        this.bannerService = bannerService;
        this.imageRepository = imageRepository;
    }

    @RequestMapping("/listBanner")
    public String listCharacter(Model model) {
        model.addAttribute("listBanner", bannerService.findAll());
        return "listBanner";
    }

    @RequestMapping("/detailBanner")
    public String detailBanner(Integer id, Model model) {
        Banner banner = bannerService.findById(id);
        BannerDto bannerDto = new BannerDto(banner);
        model.addAttribute("banner", bannerDto);
        model.addAttribute("imageFile", null);
        return "detailBanner";
    }


    @Transactional
    @PostMapping(path = "/saveBanner")
    @ResponseBody
    public String saveBanner(@ModelAttribute BannerDto banner) throws IOException {
        MultipartFile imageFile = banner.getImageFile();

        Integer imageId = banner.getImage();
        if (imageFile != null) {
            String fullName = imageFile.getOriginalFilename();
            int suffixIdx = fullName.lastIndexOf('.');
            String suffix = fullName.substring(suffixIdx + 1);

            Image image = new Image();
            image.setType(suffix);
            image.setContent(imageFile.getBytes());
            imageId = imageRepository.save(image).getId();
        }

        Banner b = new Banner();
        b.setId(banner.getId());
        b.setName(banner.getName());
        b.setComment(banner.getComment());
        b.setPriority(banner.getPriority());
        b.setUrl(banner.getUrl());
        b.setImage(imageId);

        bannerService.save(b);
        return "success";
    }

    @Transactional
    @RequestMapping("/deleteBanner")
    @ResponseBody
    public String deleteBanner(Integer id) {
        boolean deleted = bannerService.deleteById(id);
        if (!deleted) {
            return "删除失败！";
        }
        return "删除成功！";
    }

}
