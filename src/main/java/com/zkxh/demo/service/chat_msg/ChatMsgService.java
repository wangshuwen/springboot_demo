package com.zkxh.demo.service.chat_msg;

import com.github.pagehelper.PageInfo;
import com.zkxh.demo.dto.chat_msg.ChatMsgHistoryDto;

/**
 * @ClassName ChatMsgService
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/24 13:02
 * @Vserion v0.0.1
 */

public interface ChatMsgService {
    PageInfo<ChatMsgHistoryDto> findMsgHistory(Integer userId, Integer staffId, Integer startPage, Integer pageSize);
}
