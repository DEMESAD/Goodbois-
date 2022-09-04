package com.parkinglot;

import com.parkinglot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    //Story 5 Cases
    @Test
    public void should_return_parking_ticket_when_park_given_two_parking_lost_and_car_with_smart_parking_boy(){
        //Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        multipleParkingLot.add(new ParkingLot());
        multipleParkingLot.add(new ParkingLot());

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(multipleParkingLot);

        Car car = new Car();

        //When
        ParkingTicket ticket = smartParkingBoy.park(car);

        //Then
        assertNotNull(ticket);
    }
    @Test
    public void should_return_parking_lot_with_more_space_when_park_given_two_parking_lots_and_car_with_smart_parking_boy(){
//        Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        ParkingLot lessEmptySlotParkingLot = new ParkingLot(5);
        ParkingLot moreEmptySlotParkingLot = new ParkingLot();
        multipleParkingLot.add(lessEmptySlotParkingLot);

        multipleParkingLot.add(moreEmptySlotParkingLot);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(multipleParkingLot);
        Car car = new Car();

        //When
        ParkingTicket ticket = smartParkingBoy.park(car);

        //Then
        assertEquals(moreEmptySlotParkingLot, smartParkingBoy.getTicket(ticket));
    }
    @Test
    public void should_return_right_cars_with_each_ticket_when_fetch_given_two_parking_lots_both_have_car_and_ticket_with_smart_parking_boy(){
        //Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        multipleParkingLot.add(new ParkingLot(1));
        multipleParkingLot.add(new ParkingLot(1));

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(multipleParkingLot);
        Car carA = new Car();
        Car carB = new Car();

        ParkingTicket ticketA = smartParkingBoy.park(carA);
        ParkingTicket ticketB = smartParkingBoy.park(carB);

        //When
        Car fetchedCarA = smartParkingBoy.fetch(ticketA);
        Car fetchedCarB = smartParkingBoy.fetch(ticketB);

        //Then
        assertEquals(carA, fetchedCarA);
        assertEquals(carB, fetchedCarB);
    }
    @Test
    public void should_return_nothing_with_error_message_when_fetch_given_two_parking_lots_and_wrong_ticket_with_smart_parking_boy(){
        //Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        multipleParkingLot.add(new ParkingLot());
        multipleParkingLot.add(new ParkingLot());

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(multipleParkingLot);
        Car car = new Car();
        smartParkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //When & then
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(wrongTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    public void should_return_nothing_with_error_message_when_fetch_given_two_parking_lots_and_used_ticket_with_smart_parking_boy(){
        //Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        multipleParkingLot.add(new ParkingLot());
        multipleParkingLot.add(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(multipleParkingLot);

        Car car = new Car();

        ParkingTicket usedTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(usedTicket);

        //When & then
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(usedTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_nothing_with_message_when_park_given_with_two_parking_lots_is_full_with_smart_parking_boy(){
        //Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        multipleParkingLot.add(new ParkingLot(0));
        multipleParkingLot.add(new ParkingLot(0));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(multipleParkingLot);

        Car car = new Car();

        //When & then
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> smartParkingBoy.park(car));
        assertEquals("No available position.", exception.getMessage());
    }
}