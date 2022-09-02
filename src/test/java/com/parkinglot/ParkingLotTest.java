package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
  @Test
    void should_return_parking_ticket_when_park_given_parking_lot_and_cars(){
      //given
      ParkingLot parkingLot = new ParkingLot();
      Car car = new Car();

      //When
      ParkingTicket parkingTicket = parkingLot.park(car);

      //then
      assertNotNull(parkingTicket);
    }

  @Test
  void should_return_parked_car_when_fetch_given_parking_lot_and_ticket(){
    //given
    ParkingLot parkingLot = new ParkingLot();
    Car car = new Car();
    ParkingTicket ticket = parkingLot.park(car);

    //When

    Car fetchedCar = parkingLot.fetch(ticket);

    //then
    assertEquals(car, fetchedCar);
  }

  @Test
  void should_return_right_car_when_fetch_twice_given_parking_lot_with_two_parked_cars_and_two_ticket(){
    //given
    ParkingLot parkingLot = new ParkingLot();
    Car carA = new Car();
    Car carB = new Car();
    ParkingTicket ticketA = parkingLot.park(carA);
    ParkingTicket ticketB = parkingLot.park(carB);

    //When

    Car fetchedCarWithTicketA = parkingLot.fetch(ticketA);
    Car fetchedCarWithTicketB = parkingLot.fetch(ticketB);

    //then
    assertEquals(carA, fetchedCarWithTicketA);
    assertEquals(carB, fetchedCarWithTicketB);
  }

  @Test
  void should_return_nothing_when_fetch_given_parking_lot_and_wrong_parking_ticket(){
    //given
    ParkingLot parkingLot = new ParkingLot();
    Car parkedCar = new Car();
    parkingLot.park(parkedCar);
    ParkingTicket wrongTicket = new ParkingTicket();

    //When
    Car fetchedCar = parkingLot.fetch(wrongTicket);

    //then
   assertNull(fetchedCar);
  }

  @Test
  void should_return_nothing_when_fetch_given_parking_lot_used_ticket() {
    //given
    ParkingLot parkingLot = new ParkingLot();
    Car parkedCar = new Car();
    ParkingTicket ticket = parkingLot.park(parkedCar);
    parkingLot.fetch(ticket);


    //When
    Car fetchedCar = parkingLot.fetch(ticket);

    //then
    assertNull(fetchedCar);
    }

  @Test
  void should_throw() {
    //given
    ParkingLot parkingLot = new ParkingLot();
    Car parkedCar = new Car();
    ParkingTicket ticket = parkingLot.park(parkedCar);
    parkingLot.fetch(ticket);


    //When
    Car fetchedCar = parkingLot.fetch(ticket);

    //then
    assertNull(fetchedCar);
  }


  }





