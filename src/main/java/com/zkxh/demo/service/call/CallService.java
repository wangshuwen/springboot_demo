package com.zkxh.demo.service.call;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName CallService
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/11 9:03
 * @Vserion v0.0.1
 */

public interface CallService {
    boolean callStaffByStaffId(MultipartFile wavFile, Integer staffId, Integer userId);

    boolean pingTerminal(Integer staffId);
}
