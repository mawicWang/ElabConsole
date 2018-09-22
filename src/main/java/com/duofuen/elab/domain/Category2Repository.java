package com.duofuen.elab.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface Category2Repository extends PagingAndSortingRepository<Category2, Integer> {

    Iterable<Category2> findAllByParentOrderByPriorityDesc(Integer parent);
}
