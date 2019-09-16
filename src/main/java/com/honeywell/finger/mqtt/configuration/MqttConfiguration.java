package com.honeywell.finger.mqtt.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeywell.finger.mqtt.bean.MqttProcessor;
import com.honeywell.finger.util.MqttMessageConverter;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

/**
 * @author: xiaomingCao
 * @date: 2019/4/4
 */
@Configuration
@EnableConfigurationProperties({
  MqttProperties.class,
  MqttSubscribeProperties.class,
  MqttPublishProperties.class
})
public class MqttConfiguration {

  @Autowired
  private MqttProperties mqttProperties;

  @Autowired
  private MqttSubscribeProperties mqttSubscribeProperties;

  @Autowired
  private MqttPublishProperties mqttPublishProperties;

  @Autowired
  private ObjectMapper objectMapper;

  @Bean
  public DefaultPahoMessageConverter messageConverter() {
    MqttMessageConverter converter =
        new MqttMessageConverter(
            mqttPublishProperties.getQos(),
            mqttPublishProperties.isRetained(),
            mqttPublishProperties.getCharset());
    converter.setObjectMapper(objectMapper);
    converter.setPayloadAsBytes(mqttSubscribeProperties.isBinary());
    return converter;
  }

  @Bean
  public MqttPahoClientFactory mqttPahoClientFactory() {
    DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
    factory.setPersistence(null);
    MqttConnectOptions options = new MqttConnectOptions();
    options.setServerURIs(mqttProperties.getUrl());
    options.setUserName(mqttProperties.getUsername());
    options.setPassword(mqttProperties.getPassword().toCharArray());
    options.setCleanSession(mqttProperties.isCleanSession());
    options.setConnectionTimeout(mqttProperties.getConnectionTimeout());
    options.setKeepAliveInterval(mqttProperties.getKeepAliveInterval());
    options.setMaxInflight(3000);
    factory.setConnectionOptions(options);
    return factory;
  }

  @Bean
  @ServiceActivator(inputChannel = MqttProcessor.PUB)
  public MqttPahoMessageHandler mqttOutbound(
      @Autowired MqttPahoClientFactory mqttPahoClientFactory,
      @Autowired DefaultPahoMessageConverter messageConverter) {
    MqttPahoMessageHandler messageHandler =
        new MqttPahoMessageHandler(mqttPublishProperties.getClientId(), mqttPahoClientFactory);
    messageHandler.setAsync(mqttPublishProperties.isAsync());
    messageHandler.setDefaultTopic(mqttPublishProperties.getTopic());
    messageHandler.setConverter(messageConverter);
    messageHandler.setCompletionTimeout(mqttPublishProperties.getCompletionTimeout());
    return messageHandler;
  }

  @Bean
  public MessageProducer mqttInbound(
      @Autowired MqttPahoClientFactory mqttPahoClientFactory,
      @Autowired DefaultPahoMessageConverter messageConverter) {
    MqttPahoMessageDrivenChannelAdapter adapter =
        new MqttPahoMessageDrivenChannelAdapter(
            mqttSubscribeProperties.getClientId(),
            mqttPahoClientFactory,
            mqttSubscribeProperties.getTopics());
    adapter.setQos(mqttSubscribeProperties.getQos());
    adapter.setConverter(messageConverter);
    adapter.setOutputChannelName(MqttProcessor.SUB);
    return adapter;
  }
}
