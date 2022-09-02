package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Car car;

    private Map<ParkingTicket, Car> parkedPosition;
    public ParkingLot(){
        this.parkedPosition = new HashMap<>();
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        this.parkedPosition.put(ticket, car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
    return this.parkedPosition.get(ticket);
    }


}
