package com.honeywell.finger.mqtt;

import com.honeywell.finger.mqtt.bean.MqttProcessor;
import com.honeywell.finger.mqtt.bean.MqttResponseMessage;
import com.honeywell.finger.mqtt.constant.CmdIdType;
import com.honeywell.finger.mqtt.constant.MessageTypes;
import com.honeywell.finger.mqtt.service.MqttMessageService;
import com.honeywell.finger.util.JsonUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import java.util.concurrent.ExecutorService;

/**
 * @author: xiaomingCao
 * @date: 2019/4/4
 */
@Log4j2
@Configuration
@IntegrationComponentScan
public class MqttMessageHandler {

    @Autowired
    private MqttMessageService mqttMessageService;

    @Autowired
    @Qualifier("fingerExecutors")
    private ExecutorService executor;


    @Bean
    @ServiceActivator(inputChannel = MqttProcessor.SUB)
    public MessageHandler handler() {
        return (Message<?> rx) -> {
            MqttResponseMessage message = (MqttResponseMessage) rx.getPayload();
            if (message.getType().equals(MessageTypes.CONTROL)) {
                switch (message.getCmdId()) {
                    case CmdIdType.ADD:
                        executor.execute(() -> handlerAdd(message));
                        break;
                    default:
                        log.warn("unsupported message: {}", JsonUtil.toJsonStr(message));
                        break;
                }

            }
        };
    }

        private void handlerAdd(MqttResponseMessage message){
        }
    }
