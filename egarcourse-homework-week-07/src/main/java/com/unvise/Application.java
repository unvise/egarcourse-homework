package com.unvise;

import com.unvise.domain.entity.*;
import com.unvise.repository.customRepository.exchange_rate.CustomExchangeRateRepository;
import com.unvise.repository.customRepository.exchange_rate.CustomExchangeRateRepositoryImpl;
import com.unvise.repository.customRepository.person.CustomPersonRepository;
import com.unvise.repository.customRepository.person.CustomPersonRepositoryImpl;
import com.unvise.service.AccountService;
import com.unvise.service.ExchangeRateService;
import com.unvise.service.PersonService;
import com.unvise.service.TransactionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.unvise");

        // ------------------ Beans ------------------
        Person person1 = context.getBean(Person.class);
        person1.setName("Maxim Vinnikov");
        person1.setPhone("80-90-23");
        person1.setEmail("vinnikov.m.d@gmail.com");
        person1.setLogin("unvise");
        person1.setPassword("dsjfg2934zjhs@*#$%SDF82390j0q234j0");

        Person person2 = context.getBean(Person.class);
        person2.setName("Garais Eduard");
        person2.setPhone("92-10-17");
        person2.setEmail("garais.e.v@gmail.com");
        person2.setLogin("firehead");
        person2.setPassword("zkdfq,.xaj4i)@#$=Q)#4Qewq2-i34-je");

        Account account1 = context.getBean(Account.class);
        account1.setBalance(BigDecimal.valueOf(15000));
        account1.setCurrency(CurrencyType.RUB);
        account1.setPerson(person1);

        Account account2 = context.getBean(Account.class);
        account2.setBalance(BigDecimal.valueOf(0));
        account2.setCurrency(CurrencyType.KZT);
        account2.setPerson(person2);

        Transaction transaction = context.getBean(Transaction.class);
        transaction.setFromAccount(account1);
        transaction.setToAccount(account2);
        transaction.setAmount(BigDecimal.valueOf(15_000));

        ExchangeRate exchangeRate1 = context.getBean(ExchangeRate.class);
        exchangeRate1.setFromCurrency(CurrencyType.RUB);
        exchangeRate1.setToCurrency(CurrencyType.KZT);
        exchangeRate1.setRate(BigDecimal.valueOf(7.75));

        ExchangeRate exchangeRate2 = context.getBean(ExchangeRate.class);
        exchangeRate2.setFromCurrency(CurrencyType.KZT);
        exchangeRate2.setToCurrency(CurrencyType.RUB);
        exchangeRate2.setRate(BigDecimal.valueOf(0.13));


        // ------------------ Creating Services ------------------
        PersonService personService = context.getBean(PersonService.class);
        AccountService accountService = context.getBean(AccountService.class);
        TransactionService transactionService = context.getBean(TransactionService.class);
        ExchangeRateService exchangeRateService = context.getBean(ExchangeRateService.class);

        // ------------------ Working with Services ------------------
        System.out.println(personService.save(person1));
        System.out.println(personService.save(person2));
        System.out.println(personService.getAll());
        System.out.println("---------------------------------\n");

        System.out.println(accountService.save(account1));
        System.out.println(accountService.save(account2));
        System.out.println(accountService.getAll());
        System.out.println("---------------------------------\n");

        System.out.println(exchangeRateService.save(exchangeRate1));
        System.out.println(exchangeRateService.save(exchangeRate2));
        System.out.println(exchangeRateService.getAll());
        System.out.println("---------------------------------\n");

        System.out.println(transactionService.save(transaction));
        System.out.println(transactionService.getAll());
        System.out.println("---------------------------------\n");

        System.out.println(accountService.getAll());
        System.out.println("---------------------------------\n");

        // ------------------ Creating Custom Repositories ------------------
        CustomExchangeRateRepository customExchangeRateRepository =
                context.getBean(CustomExchangeRateRepositoryImpl.class);
        CustomPersonRepository customPersonRepository =
                context.getBean(CustomPersonRepositoryImpl.class);

        // ------------------ Working with Custom Repositories ------------------
        System.out.println(customExchangeRateRepository.findExchangeRateByRateBetween(BigDecimal.ZERO, BigDecimal.ONE));
        System.out.println("---------------------------------\n");

        System.out.println(customPersonRepository.findByName("Vin"));
        System.out.println(customPersonRepository.findByEmailAndPhoneOrderByNameAsc("", ""));
        System.out.println(customPersonRepository.findByEmailAndPhoneOrderByNameDesc("", ""));
        System.out.println("---------------------------------\n");

        context.close();
    }
}