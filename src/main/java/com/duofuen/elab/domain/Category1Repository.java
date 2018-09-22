package com.duofuen.elab.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface Category1Repository extends PagingAndSortingRepository<Category1, Integer> {

    Iterable<Category1> findAllByOrderByPriorityDesc();

}
