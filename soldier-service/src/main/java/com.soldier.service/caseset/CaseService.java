package com.soldier.service.caseset;


import com.soldier.dao.entity.TestCase;
import com.soldier.service.common.CaseRes;

public interface CaseService {
    CaseRes<TestCase> queryCase(Integer pageNum, Integer pageSize);
    int insertCase();
    int updateCase(int id);
    int deleteCase(int id);
}
