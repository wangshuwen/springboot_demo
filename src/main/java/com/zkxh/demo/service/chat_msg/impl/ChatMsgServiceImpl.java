package com.zkxh.demo.service.chat_msg.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.dao.chat.ChatMsgMapper;
import com.zkxh.demo.dto.chat_msg.ChatMsgHistoryDto;
import com.zkxh.demo.service.chat_msg.ChatMsgService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName ChatMsgServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/24 13:02
 * @Vserion v0.0.1
 */
@Service
public class ChatMsgServiceImpl implements ChatMsgService {

    @Resource
    private ChatMsgMapper chatMsgMapper;

    @Override
    public PageInfo<ChatMsgHistoryDto> findMsgHistory(Integer userId, Integer staffId, Integer startPage, Integer pageSize) {

        PageHelper.startPage(startPage, pageSize);
        List<Map<String, Object>> map = chatMsgMapper.selectMsgByUserIdAndStaffId(userId, staffId);
        List<ChatMsgHistoryDto> list = Collections.synchronizedList(new ArrayList<>());
        ChatMsgHistoryDto chatMsgHistoryDto = null;
        for (Map<String, Object> item : map) {
            Integer msgId = (Integer) item.get("msg_id");
            String postMsg = (String) item.get("post_msg");
            String postIp = (String) item.get("post_ip");

            Date post_time = (Date) item.get("post_time");

            Integer post_user_id = (Integer) item.get("post_user_id");

            String station_ip = (String) item.get("station_ip");

            Integer terminal_id = (Integer) item.get("terminal_id");

            chatMsgHistoryDto = new ChatMsgHistoryDto();
            chatMsgHistoryDto.setMsg_id(msgId);
            chatMsgHistoryDto.setPost_msg(postMsg);
            chatMsgHistoryDto.setPostIp(postIp);
            chatMsgHistoryDto.setPostTime(post_time);
            chatMsgHistoryDto.setPostUserId(post_user_id);
            chatMsgHistoryDto.setStationIp(station_ip);
            chatMsgHistoryDto.setTerminalId(terminal_id);
            list.add(chatMsgHistoryDto);
        }

        PageInfo<ChatMsgHistoryDto> l = new PageInfo<>(list);
        return l;
    }
}
