package com.unvise;

import com.unvise.dao.PassengerDao;
import com.unvise.dao.PassengerDaoImpl;
import com.unvise.entity.PassengerEntity;

public class Main {

    public static void main(String[] args) {
        PassengerDao passengerDao = PassengerDaoImpl.getInstance();
        passengerDao.findAll().forEach(System.out::println);
        System.out.println("---------------------------------------");

        System.out.println(passengerDao.findById(3));
        System.out.println("---------------------------------------");

        PassengerEntity passenger = new PassengerEntity();
        passenger.setFirstName("Maxim");
        passenger.setLastName("Vinnikov");
        passenger.setPhone("+7 (952) 192-24-96");
        passengerDao.save(passenger);
        passengerDao.findAll().forEach(System.out::println);
        System.out.println("---------------------------------------");

        passenger.setId(2);
        passengerDao.update(passenger);
        passengerDao.findAll().forEach(System.out::println);
        System.out.println("---------------------------------------");

        passengerDao.delete(passenger);
        passengerDao.findAll().forEach(System.out::println);
        System.out.println("---------------------------------");
    }

}
