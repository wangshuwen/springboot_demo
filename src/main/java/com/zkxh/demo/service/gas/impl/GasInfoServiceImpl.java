package com.zkxh.demo.service.gas.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.common.util.convert.DateConvert;
import com.zkxh.demo.dao.rt_gas.RtGasInfoMapper;
import com.zkxh.demo.dto.gas.GasInfoAndStaffDto;
import com.zkxh.demo.model.rt_gas.RtGasInfoExample;
import com.zkxh.demo.service.gas.GasInfoService;
import com.zkxh.demo.service.staff.StaffService;
import com.zkxh.demo.vo.resp.GasSearchRespVO;
import com.zkxh.demo.vo.resp.GasWSRespVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

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

    @Resource
    private StaffService staffService;

    @Override
    public Page findGasInfoByStaffName(String staffName, Integer startPage, Integer pageSize) {

        Page page = PageHelper.startPage(startPage, pageSize);

        List<GasWSRespVO> lists = rtGasInfoMapper.selectGasInfoByStaffName(staffName);
        return page;
    }

    @Override
    public GasWSRespVO findGasInfoByStaffIdAndTerminalId(Integer terminalId) throws ParseException {
        Map<String, Object> map = rtGasInfoMapper.selectGasInfoByTerminalLastTime(terminalId);

//
//        rt_gas_info_id, co, co_unit, ch4, ch4_unit, o2, o2_unit, co2, co2_unit, temperature,
//                temperature_unit, humidity, humidity_unit, field_3, field_3_unit, create_time, terminal_id,
//                station_id, terminal_ip, station_ip, terminal_real_time, info_type, sequence_id,
//                position_id
        GasWSRespVO gasWSRespVO = new GasWSRespVO();
        gasWSRespVO.setRt((Date) map.get("terminal_real_time"));
        gasWSRespVO.setCo((double) map.get("co"));
        gasWSRespVO.setCo_type((Integer) map.get("co_unit"));
        gasWSRespVO.setCo2((double) map.get("co2"));
        gasWSRespVO.setCo2_type((Integer) map.get("co2_unit"));
        gasWSRespVO.setCh4((double) map.get("ch4"));
        gasWSRespVO.setCh4_type((Integer) map.get("ch4_unit"));
        gasWSRespVO.setO2((double) map.get("o2"));
        gasWSRespVO.setO2_type((Integer) map.get("o2_unit"));
        gasWSRespVO.setHumidity_type((Integer) map.get("humidity_unit"));
        gasWSRespVO.setHumidity((double) map.get("humidity"));
        gasWSRespVO.setTemperature((double) map.get("temperature"));
        gasWSRespVO.setTemperature_type((Integer) map.get("temperature_unit"));
        return gasWSRespVO;
    }


    /**
     * @param [number] 需要获取的气体数量
     * @return java.util.List<com.zkxh.demo.vo.resp.GasWSRespVO>
     * @description 获取最近上传气体信息数量 以时间倒序
     * @date 17:35 2018/10/26
     * @auther lifeng
     **/
    public List<GasWSRespVO> findGasInfoLastTenRecords(int number) {
        List<Map<String, Object>> maps = rtGasInfoMapper.selectGasInfoLastTenRecords(number);
        List<GasWSRespVO> list = Collections.synchronizedList(new ArrayList<>());
        GasWSRespVO gasWSRespVO = null;
        for (Map<String, Object> item : maps) {
            gasWSRespVO = new GasWSRespVO();
            gasWSRespVO.setRt((Date) item.get("terminal_real_time"));
            gasWSRespVO.setCo((double) item.get("co"));
            gasWSRespVO.setCo_type((Integer) item.get("co_unit"));
            gasWSRespVO.setCo2((double) item.get("co2"));
            gasWSRespVO.setCo2_type((Integer) item.get("co2_unit"));
            gasWSRespVO.setCh4((double) item.get("ch4"));
            gasWSRespVO.setCh4_type((Integer) item.get("ch4_unit"));
            gasWSRespVO.setO2((double) item.get("o2"));
            gasWSRespVO.setO2_type((Integer) item.get("o2_unit"));
            gasWSRespVO.setHumidity_type((Integer) item.get("humidity_unit"));
            gasWSRespVO.setHumidity((double) item.get("humidity"));
            gasWSRespVO.setTemperature((double) item.get("temperature"));
            gasWSRespVO.setTemperature_type((Integer) item.get("temperature_unit"));
            Integer terminalId = (Integer) item.get("terminalId");
            Map<String, Object> result = staffService.findStaffIdByTerminalId(terminalId);
//            staff.staff_id, staff_number, staff_name, staff_sex, staff_birthday, staff_id_card, staff_wedlock,
//                    staff_email, staff_address, staff_phone, staff_job_id, staff_native_place, staff_type_id,
//                    is_person, staff.create_time, group_id

            gasWSRespVO.setStaffName((String) item.get("staff_name"));
            gasWSRespVO.setSequenceId((Integer) item.get("sequence_id"));
            gasWSRespVO.setCreateTime((Date) item.get("create_time"));
//            gasWSRespVO.setStaffNumber((String) result.get("staff_number"));
            list.add(gasWSRespVO);
        }
        return list;
    }
}
