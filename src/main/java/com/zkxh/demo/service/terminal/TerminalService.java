package com.zkxh.demo.service.terminal;


import com.zkxh.demo.model.terminal.StaffTerminal;

import java.util.List;

/**
 * @ClassName TerminalService
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/12 9:29
 * @Vserion v0.0.1
 */

public interface TerminalService {
    List<StaffTerminal> getAllTerminal();

    List<StaffTerminal> getNotBinDingTerminals();

    boolean unBind(Integer staffTerminalId);

    boolean unBindAndBinding(Integer staffId, Integer staffTerminalId);

    int addTerminal(StaffTerminal staffTerminal);

    Integer findTerminalInfoByStaffId(Integer staffId);
}
