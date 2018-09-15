package com.duofuen.elab.web;

import com.duofuen.elab.domain.Banner;
import com.duofuen.elab.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@Controller
public class BannerController {

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @RequestMapping("/listBanner")
    public String listCharacter(Model model) {
        model.addAttribute("listBanner", bannerService.findAll());
        return "listBanner";
    }

    @RequestMapping("/detailBanner")
    public String detailBanner(Integer id, Model model) {
        model.addAttribute("banner", bannerService.findById(id));
        return "detailBanner";
    }


    @Transactional
    @RequestMapping("/saveBanner")
    @ResponseBody
    public String saveBanner(@RequestBody Banner banner) {
        bannerService.save(banner);
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
