package com.duofuen.elab.domain;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, String> {

    Users findByAuthorities_Authority(String authority);
}
