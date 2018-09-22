package com.duofuen.elab.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BannerRepository extends PagingAndSortingRepository<Banner, Integer> {

    Iterable<Banner> findAllByOrderByPriorityDesc();
}
