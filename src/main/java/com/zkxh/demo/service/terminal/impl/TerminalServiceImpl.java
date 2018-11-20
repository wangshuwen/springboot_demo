package com.zkxh.demo.service.terminal.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
//        StaffTerminal staffTerminal = new StaffTerminal();
//        staffTerminal.setTerminalId(staffTerminalId);
//        staffTerminal.setStaffId(null);
//        int i = staffTerminalMapper.updateByPrimaryKey(staffTerminal);
        int i = staffTerminalMapper.updateTerminalUnBinding(staffTerminalId);
        return i == 1 ? true : false;
    }

    @Override
    public boolean binding(Integer staffId, Integer staffTerminalId) {
        StaffTerminal staffTerminal = new StaffTerminal();
        staffTerminal.setTerminalId(staffTerminalId);
        staffTerminal.setStaffId(staffId);
//        int i = staffTerminalMapper.updateByPrimaryKeySelective(staffTerminal);
        int i = staffTerminalMapper.updateTerminalBinding(staffId, staffTerminalId);
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

    @Override
    public Page findTerminalInfoByParams(Integer startPage, Integer pageSize, Integer terminalId) {
        StaffTerminal staffTerminal = new StaffTerminal();
        staffTerminal.setTerminalId(terminalId);
        Page page = PageHelper.startPage(startPage, pageSize);
        List<StaffTerminal> list = staffTerminalMapper.selectTerminalsByParams(staffTerminal);
        return page;
    }

    @Override
    public int deleteTerminalByTerminalId(Integer[] ids) {
        int i = 0;
        for (Integer item : ids) {
            if (staffTerminalMapper.deleteByTerminalId(item) == 1)
                i++;
        }
        return i;
    }

    @Override
    public int updateTerminalByTerminalId(StaffTerminal staffTerminal) {
        return staffTerminalMapper.updateByTerminalId(staffTerminal);
    }

    @Override
    public boolean checkTerminalExist(Integer terminalId) {
        return staffTerminalMapper.countTerminalNumByTerminalId(terminalId);
    }
}
