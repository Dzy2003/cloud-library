package com.duan.library.service;

import com.duan.library.domain.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.library.domain.User;
import com.duan.library.entity.PageResult;

/**
* @author soga
* @description 针对表【record】的数据库操作Service
* @createDate 2023-12-24 21:25:30
*/
public interface RecordService extends IService<Record> {

    PageResult<Record> searchRecords(Record record, User user, Integer pageNum, Integer pageSize);

}
