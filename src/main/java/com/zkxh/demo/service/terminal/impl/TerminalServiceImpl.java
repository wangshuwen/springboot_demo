package com.zkxh.demo.service.terminal.impl;

import com.zkxh.demo.dao.terminal.StaffTerminalMapper;
import com.zkxh.demo.model.terminal.StaffTerminal;
import com.zkxh.demo.service.terminal.TerminalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TerminalServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/12 9:29
 * @Vserion v0.0.1
 */
@Service
public class TerminalServiceImpl implements TerminalService {
    //extends BaseService<StaffTerminalMapper, StaffTerminal, StaffTerminalExample>
    @Resource
    private StaffTerminalMapper staffTerminalMapper;

    @Override
    public List<StaffTerminal> getAllTerminal() {
        return staffTerminalMapper.selectAllTerminals();
    }

    @Override
    public List<StaffTerminal> getNotBinDingTerminals() {
        return staffTerminalMapper.getNotBinDingTerminals();
    }

    @Override
    public boolean unBind(Integer staffTerminalId) {
        StaffTerminal staffTerminal = new StaffTerminal();
        staffTerminal.setTerminalId(staffTerminalId);
        staffTerminal.setStaffId(null);
        int i = staffTerminalMapper.updateByPrimaryKey(staffTerminal);
        return i == 1 ? true : false;
    }

    @Override
    public boolean unBindAndBinding(Integer staffId, Integer staffTerminalId) {
        StaffTerminal staffTerminal = new StaffTerminal();
        staffTerminal.setTerminalId(staffTerminalId);
        staffTerminal.setStaffId(staffId);
        int i = staffTerminalMapper.updateByPrimaryKeySelective(staffTerminal);
        return i == 1 ? true : false;
    }

    @Override
    public int addTerminal(StaffTerminal staffTerminal) {
        return staffTerminalMapper.insertSelective(staffTerminal);
    }

    @Override
    public Integer findTerminalInfoByStaffId(Integer staffId) {
        return staffTerminalMapper.selectTerminalIdByStaffId(staffId);
    }
}
