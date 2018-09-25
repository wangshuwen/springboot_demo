package com.zkxh.demo.service.gas.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.dao.rt_gas.RtGasInfoMapper;
import com.zkxh.demo.dto.gas.GasInfoAndStaffDto;
import com.zkxh.demo.model.rt_gas.RtGasInfoExample;
import com.zkxh.demo.service.gas.GasInfoService;
import com.zkxh.demo.vo.resp.GasSearchRespVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GasInfoServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/21 16:10
 * @Vserion v0.0.1
 */
@Service
public class GasInfoServiceImpl implements GasInfoService {

    @Resource
    private RtGasInfoMapper rtGasInfoMapper;

    @Override
    public String findGasInfoByStaffName(String staffName, Integer startPage, Integer pageSize) {

        PageHelper.startPage(startPage, pageSize);

        //TODO 添加 sql语句， resultMap
//        RtGasInfoExample rtGasInfoExample = new RtGasInfoExample();
        List<GasInfoAndStaffDto> list = rtGasInfoMapper.selectGasInfoByStaffName();
        //TODO 创建VO类
        PageInfo<GasInfoAndStaffDto> pageInfo = new PageInfo<>(list);

        return ResultUtil.jsonToStringSuccess(pageInfo);
    }
}
