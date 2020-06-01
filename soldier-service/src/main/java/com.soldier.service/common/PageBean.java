package com.soldier.service.common;

import com.github.pagehelper.PageInfo;

public class PageBean<T> {

    private Long total;
    private Integer current;
    private Integer pageSize;

    public PageBean(PageInfo<T> pageInfo){
        this.pageSize = pageInfo.getPageSize();
        this.current = pageInfo.getPageNum();
        this.total = pageInfo.getTotal();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
