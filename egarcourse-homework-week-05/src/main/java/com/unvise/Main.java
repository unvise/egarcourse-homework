package com.unvise;

import com.unvise.dao.BaseDao;
import com.unvise.dao.BaseDaoImpl;
import com.unvise.dao.PassengerDao;
import com.unvise.entity.FlightEntity;
import com.unvise.entity.PassengerEntity;

public class Main {
    public static void main(String[] args) {
        BaseDao<FlightEntity, Integer> baseDao = new BaseDaoImpl<>();
        baseDao.setClazz(FlightEntity.class);

        System.out.println(baseDao.findAll());
        System.out.println("---------------------------------------");

        PassengerDao<Integer> passengerDao = new PassengerDao<>();

        passengerDao.findPassengerByFirstNameAndLastName("%e%", "%o%").forEach(System.out::println);
        System.out.println("---------------------------------------");

        passengerDao.findPassengersWithTicketsMoreThan(1).forEach(System.out::println);
        System.out.println("---------------------------------------");

        passengerDao.findAll().forEach(System.out::println);
        System.out.println("---------------------------------------");

        PassengerEntity passenger = new PassengerEntity();
        passenger.setFirstName("Maxim");
        passenger.setLastName("Vinnikov");
        passenger.setPhone("+7 (952) 192-25-83");

        passengerDao.save(passenger);

        passengerDao.findPassengerByFirstNameAndLastName("Maxim", "Vinnikov").forEach(System.out::println);
        System.out.println("---------------------------------------");

        passengerDao.findAll().forEach(System.out::println);
        System.out.println("---------------------------------------");

        passengerDao.findById(2).ifPresentOrElse(value -> {
                    System.out.println("Deleting passenger with id 2");
                    passengerDao.deleteById(2);
                    System.out.println("Passenger with id 2 was been deleted");
                },
                () -> {
                    System.out.println("Can't find passenger with id 2");
                    System.out.println("---------------------------------------");
                }
        );
    }
}