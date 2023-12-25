package com.duan.library.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.library.domain.Record;
import com.duan.library.domain.User;
import com.duan.library.entity.PageResult;
import com.duan.library.service.RecordService;
import com.duan.library.mapper.RecordMapper;
import org.springframework.stereotype.Service;

/**
* @author soga
* @description 针对表【record】的数据库操作Service实现
* @createDate 2023-12-24 21:25:30
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

    @Override
    public PageResult<Record> searchRecords(Record record, User user, Integer pageNum, Integer pageSize) {

        if(!"ADMIN".equals(user.getRole())){
            record.setBorrower(user.getName());
        }
        Page<Record> page = lambdaQuery()
                .like(record.getBorrower() != null, Record::getBorrower, record.getBorrower())
                .like(record.getBookname() != null, Record::getBookname, record.getBookname())
                .page(new Page<>(pageNum, pageSize));
        return new PageResult<>(page.getTotal(), page.getRecords());
    }
}




