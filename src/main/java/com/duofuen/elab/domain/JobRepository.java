package com.duofuen.elab.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobRepository extends PagingAndSortingRepository<Job, Integer> {

    Iterable<Job> findAllByOrderByPriorityDesc();

}
