/**
 * $Id: TicketRedisTemplate.java 3706 2016-03-01 06:14:09Z huxiaowei $
 * Copyright(C) 2014-2020 easegame, All Rights Reserved.
 */
package org.jasig.cas.ticket.registry;

import org.jasig.cas.ticket.Ticket;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author <a href="mailto:cafebabe.hu@gmail.com">Xiaowei Hu</a>
 * @version 1.0 2016年03月01日 1:47 PM:00
 */
public class TicketRedisTemplate extends RedisTemplate<String, Ticket> {
    public TicketRedisTemplate() {
        RedisSerializer<String> string = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdk = new JdkSerializationRedisSerializer();
        setKeySerializer(string);
        setValueSerializer(jdk);
        setHashKeySerializer(string);
        setHashValueSerializer(jdk);
    }

    public TicketRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }
}
