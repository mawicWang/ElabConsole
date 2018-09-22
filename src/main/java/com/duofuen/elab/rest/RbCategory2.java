package com.duofuen.elab.rest;

import java.util.List;

public class RbCategory2 {

    private Integer parent;
    private List<RbCategory2Detail> list;

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public List<RbCategory2Detail> getList() {
        return list;
    }

    public void setList(List<RbCategory2Detail> list) {
        this.list = list;
    }
}
