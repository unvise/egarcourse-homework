package com.unvise;

import com.unvise.domain.manager.Manager;
import com.unvise.domain.manager.TransactionManager;
import com.unvise.domain.entity.Person;
import com.unvise.domain.entity.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Person person1 = context.getBean("person1", Person.class);

        Person person2 = context.getBean("person2", Person.class);

        Transaction transaction = context.getBean("person1-to-person2-transaction", Transaction.class);

        System.out.println(person1);
        System.out.println(person1.getAccount());
        System.out.println("---------------------");

        System.out.println(person2);
        System.out.println(person2.getAccount());
        System.out.println("---------------------");

        System.out.println(transaction);
        System.out.println("---------------------");

        Manager transactionManager = new TransactionManager(transaction);
        transactionManager.complete();
        System.out.println("Was transaction successful: " + transactionManager.wasSuccessful());
        System.out.println("---------------------");

        System.out.println(person1.getAccount());
        System.out.println(person2.getAccount());
        System.out.println("---------------------");

        context.close();
    }
}