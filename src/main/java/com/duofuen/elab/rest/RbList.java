package com.duofuen.elab.rest;

import java.util.ArrayList;
import java.util.List;

public class RbList<T> {

    private List<T> list;

    public RbList() {
        this.list = new ArrayList<T>();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
