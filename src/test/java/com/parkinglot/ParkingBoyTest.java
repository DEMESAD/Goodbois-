package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
        parkingBoy = new ParkingBoy(parkingLot);
    }
    //AC1
    @Test
    void should_return_parking_ticket_when_parkingBoy_park_given_a_parking_lot_and_a_car() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        // then
        assertNotNull(parkingTicket);
    }
    //AC2
    @Test
    void should_return_parked_car_when_parkingBoy_fetch_given_a_parking_lot_with_a_parked_car_and_a_parking_ticket() {
        // given
        ParkingTicket parkingTicket = parkingBoy.park(new Car());

        // when
        Car car = parkingBoy.fetch(parkingTicket);

        // then
        assertNotNull(car);
    }
    //AC3
    @Test
    void should_return_right_car_with_each_ticket_when_parkingBoy_fetch_twice_given_a_parking_lot_with_two_parked_car_and_two_parking_ticket() {
        // given
        Car carA = new Car();
        Car carB = new Car();
        ParkingTicket parkingTicketA = parkingBoy.park(carA);
        ParkingTicket parkingTicketB = parkingBoy.park(carB);

        // when
        Car firstFetchedCar = parkingBoy.fetch(parkingTicketA);
        Car secondFetchedCar = parkingBoy.fetch(parkingTicketB);

        // then
        assertEquals(carA, firstFetchedCar);
        assertEquals(carB, secondFetchedCar);
    }
    //AC4
    @Test
    void should_throw_exception_with_error_message_when_parkingBoy_fetch_given_a_parking_lot_and_an_unrecognized_parking_ticket() {
        // given
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        // when & then
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedParkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    //AC5
    @Test
    public void should_throw_exception_with_error_message_when_parkingBoy_fetch_given_a_parking_lot_and_a_used_parking_ticket() {
        // given
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        parkingBoy.fetch(parkingTicket);

        // when & then
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    //AC6
    @Test
    public void should_throw_exception_with_error_message_when_parkingBoy_park_given_a_parking_lot_with_no_available_position_and_a_car() {
        // given
        ParkingLot parkingLotFull = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotFull);
        parkingBoy.park(new Car());
        Car car = new Car();

        // when & then
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car));
        assertEquals("No available position.", exception.getMessage());
    }

    //Story 4:
    @Test
    public void should_return_parking_ticket_when_park_given_two_available_parking_lot_with_parking_boy_and_car(){
        //Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        multipleParkingLot.add(new ParkingLot());
        multipleParkingLot.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(multipleParkingLot);
        Car car = new Car();
        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);
    }
    @Test
    public void should_return_parking_ticket_when_park_given_one_full_parking_lot_and_one_available_parking_lot_with_parking_boy_and_car(){
        //Given
        List<ParkingLot> multipleParkingLots = new ArrayList<>();
        multipleParkingLots.add(new ParkingLot(0));
        multipleParkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(multipleParkingLots);
        Car car = new Car();
        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);
    }
    @Test
    public void should_return_right_car_with_each_ticket_when_fetch_given_two_parking_lots_both_have_parked_cars_and_two_tickets_with_parking_boy(){
        //Given
        List<ParkingLot> multipleParkingLots = new ArrayList<>();
        multipleParkingLots.add(new ParkingLot(1));
        multipleParkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(multipleParkingLots);
        Car carA = new Car();
        Car carB = new Car();
        ParkingTicket ticketA = parkingBoy.park(carA);
        ParkingTicket ticketB = parkingBoy.park(carB);
        //When
        Car fetchedCarA = parkingBoy.fetch(ticketA);
        Car fetchedCarB = parkingBoy.fetch(ticketB);
        //Then
        assertEquals(carA, fetchedCarA);
        assertEquals(carB, fetchedCarB);
    }
    @Test
    public void should_return_nothing__with_error_message_when_fetch_car_given_parking_lot_and_wrong_parking_ticket_with_parking_boy(){
        //Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        multipleParkingLot.add(new ParkingLot(1));
        multipleParkingLot.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(multipleParkingLot);
        Car carA = new Car();
        Car carB = new Car();
        parkingBoy.park(carA);
        parkingBoy.park(carB);
        ParkingTicket wrongTicket = new ParkingTicket();
        //When
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> parkingBoy.fetch(wrongTicket));
        //Then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    public void should_return_nothing_with_error_message_when_fetch_car_given_parking_lot_used_parking_ticket_with_parking_boy(){
        //Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        multipleParkingLot.add(new ParkingLot());
        multipleParkingLot.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(multipleParkingLot);
        Car car = new Car();
        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        //When
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> parkingBoy.fetch(ticket));
        //Then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    public void should_return_nothing_with_message_when_park_given_all_parking_lot_is_full_with_parking_boy(){
        //Given
        List<ParkingLot> multipleParkingLot = new ArrayList<>();
        multipleParkingLot.add(new ParkingLot(0));
        multipleParkingLot.add(new ParkingLot(0));

        ParkingBoy parkingBoy = new ParkingBoy(multipleParkingLot);

        Car car = new Car();
        //When
        Exception exception = assertThrows(NoAvailablePositionException.class,
                () -> parkingBoy.park(car));

        //Then
        assertEquals("No available position.", exception.getMessage());
    }

}