package com.duan.library.Controller;

import javax.annotation.Resource;

import com.duan.library.service.impl.RecordServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 白日
 * @create 2023/12/24 14:21
 * @description
 */
@Controller
@RequestMapping("/record")
public class RecordController {
    @Resource
    private RecordServiceImpl recordService;

}
