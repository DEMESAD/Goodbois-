package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> multipleParkingLot){
        super(multipleParkingLot);
    }

    public ParkingLot getMoreEmptySlot(){
        return this.getMultipleParkingLot()
                .stream()
                .max(Comparator.comparing(parkingLot -> parkingLot.getAvailableSlots()))
                .orElse(null);
    }

    public ParkingLot getTicket(ParkingTicket ticket){
        return this.getMultipleParkingLot()
                .stream()
                .filter(eachParkingLot -> !eachParkingLot.isUnrecognizedTicket(ticket))
                .findAny()
                .orElse(null);
    }

}
