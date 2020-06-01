package com.soldier.service.caseset.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soldier.dao.entity.TestCase;
import com.soldier.dao.entity.TestCaseExample;
import com.soldier.dao.mapper.TestCaseMapper;
import com.soldier.service.caseset.CaseService;
import com.soldier.service.common.CaseRes;
import com.soldier.service.common.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class CaseServiceImpl implements CaseService {
    @Autowired
    private TestCaseMapper testCaseMapper;

    @Override
    public CaseRes<TestCase> queryCase(Integer pageNum, Integer pageSize) {
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        //查询所有case信息
        CaseRes<TestCase> caseRes = new CaseRes<>();
        TestCaseExample caseExample = new TestCaseExample();
        caseExample.createCriteria().andIdIsNotNull();
        List<TestCase> caseList = testCaseMapper.selectByExampleWithBLOBs(caseExample);
        caseRes.setCaseList(caseList);
        PageInfo<TestCase> pageInfo = new PageInfo<>(caseList);
        PageBean<TestCase> pageBean = new PageBean<>(pageInfo);
        pageBean.setCurrent(pageNum);
        caseRes.setPagination(pageBean);
        return caseRes;
    }

    @Override
    public int insertCase() {
        TestCase testCase = new TestCase();
        testCase.setCaseName("数据采集系统");
        testCase.setCaseType("1");
        testCase.setCaseContent("test");
        int flag = testCaseMapper.insert(testCase);
        if(flag == 1){
            return  testCase.getId();
        }
        return -1;
    }

    @Override
    public int updateCase(int id) {
        TestCase testCase = new TestCase();
        testCase.setId(id);
        testCase.setCaseName("数据采集系统update");
        testCase.setCaseType("1");
        testCase.setCaseContent("testupdate");
        return testCaseMapper.updateByPrimaryKey(testCase);
    }

    @Override
    public int deleteCase(int id) {
        return testCaseMapper.deleteByPrimaryKey(id);
    }

}
