package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;
    private List<ParkingLot> multipleParkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.multipleParkingLot = new ArrayList<>();
        
    }
    public ParkingBoy(List<ParkingLot> parkingLots){
        this.multipleParkingLot = parkingLots;
        this.checkAvailableParkingLot();
    }

    private void checkAvailableParkingLot() {
        this.parkingLot = this.multipleParkingLot
                .stream()
                .filter(eachParkingLot -> !eachParkingLot.ifFull())
                .findFirst()
                .orElse(null);
        if(this.parkingLot == null){
            this.parkingLot = this.multipleParkingLot.get(0);
        }
    }



    public ParkingTicket park(Car car) {
        if(!this.multipleParkingLot.isEmpty()){
            this.checkAvailableParkingLot();
        }
        return this.parkingLot.park(car);
    }

    public void correctParkingLot(ParkingTicket ticket){
        this.parkingLot = this.multipleParkingLot
                .stream()
                .filter(eachParkingLot -> !eachParkingLot.isUnrecognizedTicket(ticket))
                .findFirst()
                .orElse(null);
        if(this.parkingLot == null){
            this.parkingLot = this.multipleParkingLot.get(0);
        }
    }


    public Car fetch(ParkingTicket ticket) {
        if(!this.multipleParkingLot.isEmpty()){
            this.correctParkingLot(ticket);
        }
        return this.parkingLot.fetch(ticket);
    }


}