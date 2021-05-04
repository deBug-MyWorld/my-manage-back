package com.guixin.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatVo {
    private int type; // 0表示发送，1表示接收,2表示离开，3表示群发
    private String sendUser;
    private String toUser;
    private String avatar;
    private String message;
}
