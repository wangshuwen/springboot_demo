package com.zkxh.demo.controller.chat_msg;

import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.dto.chat_msg.ChatMsgHistoryDto;
import com.zkxh.demo.service.chat_msg.ChatMsgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ChatMsgController
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/24 11:30
 * @Vserion v0.0.1
 */
@RestController
public class ChatMsgController {

    @Resource
    private ChatMsgService chatMsgService;

    /**
     * @param
     * @return
     * @description 获取历史信息
     * @date 11:03 2018/10/24
     * @auther lifeng
     **/
    @GetMapping("call/chatMsgHistory")
    @ApiOperation(value = "获取聊天分页数据", notes = "根据系统用户ID，员工ID查询历史聊天记录")
    public String getHistoryChatMsg(Integer userId, Integer staffId,
                                    @RequestParam(name = "startPage", defaultValue = "1", required = false) Integer startPage,
                                    @RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        PageInfo<ChatMsgHistoryDto> pageInfo = chatMsgService.findMsgHistory(userId, staffId, startPage, pageSize);
        return ResultUtil.jsonToStringSuccess(pageInfo);
    }
}
