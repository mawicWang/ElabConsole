package com.duofuen.elab.web;

import com.duofuen.elab.domain.Category1;
import com.duofuen.elab.domain.Category2;
import com.duofuen.elab.domain.Image;
import com.duofuen.elab.dto.Category1Dto;
import com.duofuen.elab.service.CategoryService;
import com.duofuen.elab.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final ImageService imageService;

    @Autowired
    public CategoryController(CategoryService categoryService, ImageService imageService) {
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    @RequestMapping("/listCategory1")
    public String listCategory1(Model model) {
        Iterable<Category1>  iterCategory1 =  categoryService.findAll1();
        List<Category1Dto> listCategory1Dto = new ArrayList<>();

        for (Category1 c : iterCategory1) {
            Category1Dto dto = new Category1Dto(c);
            Image image1 = imageService.findById(c.getImage1());
            Image image2 = imageService.findById(c.getImage2());

            dto.setImageUrl1(image1.getUrl());
            dto.setImageUrl2(image2.getUrl());
            listCategory1Dto.add(dto);
        }

        model.addAttribute("listCategory1", listCategory1Dto);

        return "listCategory1";
    }

    @RequestMapping("/detailCategory1")
    public String detailCategory1(Integer id, Model model) {
        Category1 category1 = categoryService.findById1(id);
        model.addAttribute("category1", new Category1Dto(category1));
        return "detailCategory1";
    }

    @Transactional
    @PostMapping(path = "/saveCategory1")
    @ResponseBody
    public String saveCategory1(@ModelAttribute Category1Dto category1Dto) throws IOException {
        MultipartFile imageFile1 = category1Dto.getImageFile1();
        MultipartFile imageFile2 = category1Dto.getImageFile2();

        Integer imageId1 = category1Dto.getImage1();
        Integer imageId2 = category1Dto.getImage2();
        if (imageFile1 != null) {
//            imageId1 = imageService.saveFromFile(imageFile1).getId();
            imageId1 = imageService.saveImageUrl(category1Dto.getImageUrl1()).getId();
        }
        if (imageFile2 != null) {
//            imageId2 = imageService.saveFromFile(imageFile2).getId();
            imageId2 = imageService.saveImageUrl(category1Dto.getImageUrl2()).getId();

        }

        Category1 category1 = new Category1();
        category1.setId(category1Dto.getId());
        category1.setTextTitle(category1Dto.getTextTitle());
        category1.setTextCn(category1Dto.getTextCn());
        category1.setTextEn(category1Dto.getTextEn());
        category1.setImage1(imageId1);
        category1.setImage2(imageId2);
        category1.setComment(category1Dto.getComment());
        category1.setPriority(category1Dto.getPriority());

        categoryService.save1(category1);
        return "success";
    }

    @Transactional
    @RequestMapping("/deleteCategory1")
    @ResponseBody
    public String deleteCategory1(Integer id) {
        boolean deleted = categoryService.deleteById1(id);
        if (!deleted) {
            return "删除失败！";
        }
        return "删除成功！";
    }


    @RequestMapping("/listCategory2")
    public String listCategory2(@RequestParam Integer category1Id, Model model) {
        model.addAttribute("listCategory2", categoryService.findAll2ByParent(category1Id));
        model.addAttribute("category1", categoryService.findById1(category1Id));
        return "listCategory2";
    }

    @RequestMapping("/detailCategory2")
    public String detailCategory2(Integer id, Integer parent, Model model) {
        Category2 category2 = categoryService.findById2(id);
        if (category2.getParent() == null) {
            category2.setParent(parent);
        }
        model.addAttribute("category2", category2);
        return "detailCategory2";
    }

    @Transactional
    @PostMapping(path = "/saveCategory2")
    @ResponseBody
    public String saveCategory2(@RequestBody Category2 category2) {
        categoryService.save2(category2);
        return "success";
    }

    @Transactional
    @RequestMapping("/deleteCategory2")
    @ResponseBody
    public String deleteCategory2(Integer id) {
        boolean deleted = categoryService.deleteById2(id);
        if (!deleted) {
            return "删除失败！";
        }
        return "删除成功！";
    }
}
