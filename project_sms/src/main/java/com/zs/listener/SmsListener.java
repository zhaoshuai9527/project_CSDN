package com.zs.listener;

import com.aliyuncs.exceptions.ClientException;
import com.zs.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil ;
    @Value("${aliyun.sms.templateCode}")
    private String templateCode ;

    @Value("${aliyun.sms.signName}")
    private String signName  ;

    //发送短信
    //（先从MQ取，再发给用户）java 发送短信 ->MQ ->从MQ中取出要发的内容 ->发送给真实的手机
    @RabbitHandler
    public void sendSms(Map<String,String> map){
        //先从MQ取
        String phone = map.get("phone") ;
        String smscode = map.get("code") ;
        String smscodeJsonStr = "{\"code\":\""+smscode+"\"}" ;

        System.out.println("手机号：" +phone);
        System.out.println("验证码：" +smscode);
        //发送
        try {
            smsUtil.sendSms(phone, templateCode, signName, smscodeJsonStr);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
