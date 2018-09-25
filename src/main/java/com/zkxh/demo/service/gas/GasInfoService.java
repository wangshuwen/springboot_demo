package com.zkxh.demo.service.gas;

import com.zkxh.demo.vo.resp.GasSearchRespVO;

import java.util.List;

/**
 * @ClassName GasInfoService
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/21 16:10
 * @Vserion v0.0.1
 */

public interface GasInfoService {
    String findGasInfoByStaffName(String staffName, Integer startPage, Integer pageSize);
}
