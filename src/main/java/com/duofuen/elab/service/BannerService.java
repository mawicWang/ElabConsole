package com.duofuen.elab.service;

import com.duofuen.elab.domain.Banner;
import com.duofuen.elab.domain.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BannerService {

    private final BannerRepository bannerRepository;

    @Autowired
    public BannerService(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }


    public Iterable<Banner> findAll() {
        return bannerRepository.findAll();
    }

    public Banner findById(Integer id) {
        if (id != null) {
            Optional<Banner> banner = bannerRepository.findById(id);
            if (banner.isPresent()) {
                return banner.get();
            }
        }

        return new Banner();
    }

    public void save(Banner banner) {
        bannerRepository.save(banner);
    }

    public boolean deleteById(Integer id) {
        try {
            bannerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
