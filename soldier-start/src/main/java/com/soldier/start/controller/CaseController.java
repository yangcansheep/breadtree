package com.soldier.start.controller;


import com.alibaba.fastjson.JSON;
import com.soldier.dao.entity.TestCase;
import com.soldier.service.caseset.CaseService;
import com.soldier.service.common.CaseRes;
import com.soldier.start.model.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/soldier/case")
public class CaseController {
    @Resource
    private CaseService caseService;

    @RequestMapping(value = "/query")
    public JsonData queryCase(@RequestParam(name = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        CaseRes<TestCase> caseRes = caseService.queryCase(currentPage, pageSize);
        log.debug("查询数据成功:" + JSON.toJSONString(caseRes));
        return JsonData.ok(caseRes);
    }

}
