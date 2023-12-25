package com.duan.library.mapper;

import com.duan.library.domain.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author soga
* @description 针对表【record】的数据库操作Mapper
* @createDate 2023-12-24 21:25:30
* @Entity com.duan.library.domain.Record
*/
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

}




