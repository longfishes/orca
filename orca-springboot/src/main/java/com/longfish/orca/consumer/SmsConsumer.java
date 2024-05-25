package com.longfish.orca.consumer;

import com.alibaba.fastjson.JSON;
import com.longfish.orca.pojo.dto.SmsDTO;
import com.longfish.orca.util.CodeUtil;
import com.longfish.orca.util.PhoneUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.longfish.orca.constant.RabbitMQConstant.PHONE_QUEUE;

@Slf4j
@Component
@RabbitListener(queues = PHONE_QUEUE)
public class SmsConsumer {

    @Autowired
    private PhoneUtil phoneUtil;

    @Autowired
    private CodeUtil codeUtil;

    @RabbitHandler
    public void process(byte[] data) {
        SmsDTO smsDTO = JSON.parseObject(new String(data), SmsDTO.class);
        log.info("开始发送手机验证码TO：{}", smsDTO.getPhone());
        phoneUtil.sendMessage(smsDTO);
        codeUtil.insert(smsDTO.getPhone(), String.valueOf(smsDTO.getCode()));
    }

}
