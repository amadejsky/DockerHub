package org.amadejsky.parcelmanager;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ParcelManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParcelManagerApplication.class, args);
    }
    @Bean
    public MessageConverter messageConverter(){
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(mapper);
    }
//    @Bean
//    public MessageConverter massageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }


}
