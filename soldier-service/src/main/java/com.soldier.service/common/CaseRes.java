package com.soldier.service.common;



import com.soldier.dao.entity.TestCase;

import java.util.List;

public class CaseRes<T> {
    private List<TestCase> list;
    private PageBean<T> pagination;

    public List<TestCase> getList() {
        return list;
    }

    public void setCaseList(List<TestCase> list) {
        this.list = list;
    }

    public PageBean<T> getPagination() {
        return pagination;
    }

    public void setPagination(PageBean<T> pagination) {
        this.pagination = pagination;
    }
}
