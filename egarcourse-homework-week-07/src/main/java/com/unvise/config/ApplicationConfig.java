package com.unvise.config;

import com.unvise.domain.entity.Account;
import com.unvise.domain.entity.ExchangeRate;
import com.unvise.domain.entity.Person;
import com.unvise.domain.entity.Transaction;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.unvise")
public class ApplicationConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Person person() {
        return new Person();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Account account() {
        return new Account();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Transaction transaction() {
        return new Transaction();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ExchangeRate exchangeRate() {
        return new ExchangeRate();
    }

}
