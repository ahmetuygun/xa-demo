package com.krakozhia.visa.configuration;

import com.atomikos.jms.AtomikosConnectionFactoryBean;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.apache.activemq.artemis.jms.client.ActiveMQXAConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JTAConfiguration {

    @Bean
    public JmsTemplate jmsTemplate() throws Throwable {
        return new JmsTemplate(connectionFactory());
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public ConnectionFactory connectionFactory() throws JMSException {
        ActiveMQXAConnectionFactory activeMQXAConnectionFactory = new
                ActiveMQXAConnectionFactory();
        activeMQXAConnectionFactory.setBrokerURL("tcp://localhost:61616");
        AtomikosConnectionFactoryBean atomikosConnectionFactoryBean = new AtomikosConnectionFactoryBean();
        atomikosConnectionFactoryBean.setUniqueResourceName("xamq");
        atomikosConnectionFactoryBean.setLocalTransactionMode(false);
        atomikosConnectionFactoryBean.setXaConnectionFactory(activeMQXAConnectionFactory);
        return atomikosConnectionFactoryBean;
    }


}
