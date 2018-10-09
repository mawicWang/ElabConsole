package com.duofuen.elab.web;

import com.duofuen.elab.domain.Banner;
import com.duofuen.elab.domain.Image;
import com.duofuen.elab.dto.BannerDto;
import com.duofuen.elab.service.BannerService;
import com.duofuen.elab.service.ImageService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class BannerController {

    private final BannerService bannerService;
    private final ImageService imageService;

    @Autowired
    public BannerController(BannerService bannerService, ImageService imageService) {
        this.bannerService = bannerService;
        this.imageService = imageService;
    }

    @RequestMapping("/listBanner")
    public String listCharacter(Model model) {
        Iterable<Banner> lBanner = bannerService.findAll();
        List<BannerDto> listBannerDto = new ArrayList<>();
        for (Banner b : lBanner) {
            BannerDto dto = new BannerDto(b);
            Image image = imageService.findById(b.getImage());
            dto.setImageUrl(image.getUrl());
            listBannerDto.add(dto);
        }
        model.addAttribute("listBannerDto", listBannerDto);
        return "listBanner";
    }

    @RequestMapping("/detailBanner")
    public String detailBanner(Integer id, Model model) {
        Banner banner = bannerService.findById(id);
        BannerDto bannerDto = new BannerDto(banner);
        model.addAttribute("banner", bannerDto);
        return "detailBanner";
    }


    @Transactional
    @PostMapping(path = "/saveBanner")
    @ResponseBody
    public String saveBanner(@ModelAttribute BannerDto banner) throws IOException {
        MultipartFile imageFile = banner.getImageFile();

        Integer imageId = banner.getImage();
        if (imageFile != null) {
            imageId = imageService.saveImageUrl(banner.getImageUrl()).getId();
//            imageId = imageService.saveFromFile(imageFile).getId();
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
