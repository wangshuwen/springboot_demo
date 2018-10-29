package com.zkxh.demo.service.staff.job.impl;

import com.zkxh.demo.dao.staff.StaffJobMapper;
import com.zkxh.demo.model.staff.StaffJob;
import com.zkxh.demo.service.staff.job.StaffJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName StaffJobServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/12 15:04
 * @Vserion v0.0.1
 */

@Service
public class StaffJobServiceImpl implements StaffJobService {


    @Resource
    private StaffJobMapper staffJobMapper;

    @Override
    public List<StaffJob> getAllJobs() {
        return staffJobMapper.selectAllJobs();
    }
}
