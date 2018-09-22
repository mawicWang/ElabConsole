package com.duofuen.elab.rest;


import com.duofuen.elab.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

import static com.duofuen.elab.util.Const.Rest.ROOT_PATH;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = ROOT_PATH)
public class RestController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final BannerRepository bannerRepository;
    private final Category1Repository category1Repository;
    private final Category2Repository category2Repository;
    private final ImageRepository imageRepository;
    private final JobRepository jobRepository;

    public RestController(BannerRepository bannerRepository, Category1Repository category1Repository, Category2Repository category2Repository, ImageRepository imageRepository, JobRepository jobRepository) {
        this.bannerRepository = bannerRepository;
        this.category1Repository = category1Repository;
        this.category2Repository = category2Repository;
        this.imageRepository = imageRepository;
        this.jobRepository = jobRepository;
    }


    @GetMapping(value = "/listBanner")
    public BaseResponse listBanner() {
        LOGGER.info("==>restful method listBanner called");

        RbList<RbBanner> listRbBanner = new RbList<>();

        Iterable<Banner> listBanner = bannerRepository.findAllByOrderByPriorityDesc();
        for (Banner b : listBanner) {
            RbBanner rbb = new RbBanner();
            rbb.setName(b.getName());
            rbb.setUrl(b.getUrl());
            rbb.setComment(b.getComment());
            rbb.setUrl(buildImageUrl(b.getImage()));
            listRbBanner.getList().add(rbb);
        }

        return BaseResponse.success(listRbBanner);
    }

    @GetMapping(value = "/listCategory1")
    public BaseResponse listCategory1() {
        LOGGER.info("==>restful method listCategory1 called");

        RbList<RbCategory1> listRbCategory1 = new RbList<>();

        Iterable<Category1> listCategory1 = category1Repository.findAllByOrderByPriorityDesc();
        for (Category1 c : listCategory1) {
            RbCategory1 rbc = new RbCategory1();
            rbc.setId(c.getId());
            rbc.setTextTitle(c.getTextTitle());
            rbc.setTextCn(c.getTextCn());
            rbc.setTextEn(c.getTextEn());
            rbc.setImage1(buildImageUrl(c.getImage1()));
            rbc.setImage2(buildImageUrl(c.getImage2()));
            rbc.setComment(c.getComment());
            listRbCategory1.getList().add(rbc);
        }

        return BaseResponse.success(listRbCategory1);
    }


    @GetMapping(value = "/listCategory2")
    public BaseResponse listCategory2(Integer parent) {
        LOGGER.info("==>restful method listCategory2 called");

        RbCategory2 rbCategory2 = new RbCategory2();
        rbCategory2.setParent(parent);
        ArrayList<RbCategory2Detail> list = new ArrayList<>();
        rbCategory2.setList(list);

        Iterable<Category2> listCategory2 = category2Repository.findAllByParentOrderByPriorityDesc(parent);
        for (Category2 c : listCategory2) {
            RbCategory2Detail detail = new RbCategory2Detail();
            detail.setName(c.getName());
            detail.setLocation(c.getLocation());
            detail.setComment(c.getComment());
            detail.setUrl(c.getUrl());
            rbCategory2.getList().add(detail);
        }

        return BaseResponse.success(rbCategory2);
    }

    @GetMapping(value = "/listJob")
    public BaseResponse listJob(Integer parent) {
        LOGGER.info("==>restful method listJob called");

        RbList<RbJob> listRbJob = new RbList<>();

        Iterable<Job> listJob = jobRepository.findAllByOrderByPriorityDesc();
        for (Job j : listJob) {
            RbJob rbj = new RbJob();
            rbj.setPostion(j.getPostion());
            rbj.setNumber(j.getNumber());
            rbj.setCompany(j.getCompany());
            rbj.setLocaton(j.getLocaton());
            rbj.setUpdateTime(j.getUpdateTime().getTime() / 1000);
            rbj.setDescription(j.getDescription());
            listRbJob.getList().add(rbj);
        }

        return BaseResponse.success(listRbJob);
    }


    private String buildImageUrl(Integer imageId) {
        return "/img?id=" + imageId;
    }
}
