package com.duofuen.elab.service;

import com.duofuen.elab.domain.Category1;
import com.duofuen.elab.domain.Category1Repository;
import com.duofuen.elab.domain.Category2;
import com.duofuen.elab.domain.Category2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final Category1Repository category1Repository;
    private final Category2Repository category2Repository;

    @Autowired
    public CategoryService(Category1Repository category1Repository, Category2Repository category2Repository) {
        this.category1Repository = category1Repository;
        this.category2Repository = category2Repository;
    }


    public Iterable<Category1> findAll1() {
        return category1Repository.findAll();
    }

    public Iterable<Category2> findAll2ByParent(Integer category1Id) {
        return category2Repository.findAllByParentOrderByPriorityDesc(category1Id);
    }

    public Category1 findById1(Integer id) {
        if (id != null) {
            Optional<Category1> category1 = category1Repository.findById(id);
            if (category1.isPresent()) {
                return category1.get();
            }
        }

        return new Category1();
    }

    public Category2 findById2(Integer id) {
        if (id != null) {
            Optional<Category2> category2 = category2Repository.findById(id);
            if (category2.isPresent()) {
                return category2.get();
            }
        }

        return new Category2();
    }

    public void save1(Category1 category1) {
        category1Repository.save(category1);
    }

    public void save2(Category2 category2) {
        category2Repository.save(category2);
    }

    public boolean deleteById1(Integer id) {
        try {
            category1Repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById2(Integer id) {
        try {
            category2Repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
