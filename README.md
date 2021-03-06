# cas-server-integration-redis
使用[Maven WAR overlay project](https://github.com/Jasig/cas-overlay-template)构建cas server

# Usage
添加POM依赖
```xml
<dependency>
  <groupId>org.jasig.cas</groupId>
  <artifactId>cas-server-integration-redis</artifactId>
  <version>${cas.version}</version>
</dependency>
```

将ticketRegistry.xml中的
```xml
<bean id="ticketRegistry" class="org.jasig.cas.ticket.registry.DefaultTicketRegistry"/>
```

改为
```xml
<bean id="ticketRegistry" class="org.jasig.cas.ticket.registry.RedisTicketRegistry">
    <constructor-arg index="0" ref="redisTemplate" />

    <!-- TGT timeout in seconds -->
    <constructor-arg index="1" value="1800" />

    <!-- ST timeout in seconds -->
    <constructor-arg index="2" value="300" />
</bean>

<bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig"
      p:maxTotal="${redis.pool.maxTotal}"
      p:maxIdle="${redis.pool.maxIdle}"
      p:maxWaitMillis="${redis.pool.maxWaitMillis}"
      p:testOnBorrow="${redis.pool.testOnBorrow}"/>

<bean id="jedisConnFactory"
      class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory"
      p:hostName="${redis.hostName}"
      p:port="${redis.port}"
      p:poolConfig-ref="jedisPoolConfig"/>

<bean id="redisTemplate" class="org.jasig.cas.ticket.registry.TicketRedisTemplate"
      p:connectionFactory-ref="jedisConnFactory"/>
```