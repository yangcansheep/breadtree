package com.rokid.soldier.dao.mapper;

import com.rokid.soldier.dao.entity.TaskHistory;
import com.rokid.soldier.dao.entity.TaskHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskHistoryMapper {
    long countByExample(TaskHistoryExample example);

    int deleteByExample(TaskHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskHistory record);

    int insertSelective(TaskHistory record);

    List<TaskHistory> selectByExample(TaskHistoryExample example);

    TaskHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskHistory record, @Param("example") TaskHistoryExample example);

    int updateByExample(@Param("record") TaskHistory record, @Param("example") TaskHistoryExample example);

    int updateByPrimaryKeySelective(TaskHistory record);

    int updateByPrimaryKey(TaskHistory record);
}