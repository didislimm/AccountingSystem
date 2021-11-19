package com.company.services;

import com.company.model.Flat;
import com.company.model.Floor;
import com.company.model.House;
import com.company.repository.HouseRepository;

public class HouseService {
    public void safe(House house) {
        HouseRepository.addHouse(house);
    }

    public boolean isNumberFree(Integer numberOfHouse) {
        return HouseRepository.getHouseByNumber(numberOfHouse).isEmpty();
    }

    public boolean isFlatExisting(Integer numberOfHouse, int numberOfFlat) {
        HouseService houseService = new HouseService();
        return (numberOfFlat > 0 && numberOfFlat <= houseService.getValueOfFlats(numberOfHouse));
    }

    public boolean isFlatNonExisting(Integer numberOfHouse, int numberOfFlat) {
        HouseService houseService = new HouseService();
        return !(numberOfFlat > 0 && numberOfFlat <= houseService.getValueOfFlats(numberOfHouse));
    }

    public Flat getFlatByNumber(int numberOfFlat, int numberOfHouse) {
        Flat resultFlat = null;
        int numberOfFloor = getFloorOfFlat(numberOfFlat, numberOfHouse);
        House house = HouseRepository.getHouseByNumber(numberOfHouse).get();
        Floor floor = house.getFloors().get(numberOfFloor - 1);
        for (Flat flat : floor.getFlats()) {
            if (flat.getNumberOfFlat() == numberOfFlat) {
                resultFlat = flat;
            }
        }
        return resultFlat;
    }

    public String compareTwoFlats(int numberOfFirstFlat, int numberOfFirstHouse, int numberOfSecondFlat, int numberOfSecondHouse) {
        Flat firstFlat = getFlatByNumber(numberOfFirstFlat, numberOfFirstHouse);
        Flat secondFlat = getFlatByNumber(numberOfSecondFlat, numberOfSecondHouse);
        String resultText = """
                Max square:
                """;
        if (firstFlat.getSquareOfFlat() > secondFlat.getSquareOfFlat()) {
            resultText += firstFlat.getSquareOfFlat() + " In flat with number " + numberOfFirstFlat + "\n";
        } else {
            resultText += secondFlat.getSquareOfFlat() + " In flat with number " + numberOfSecondFlat + "\n";
        }
        resultText += "Max value of Lodgers:";
        if (firstFlat.getNumberOfLodger() > secondFlat.getNumberOfLodger()) {
            resultText += firstFlat.getNumberOfLodger() + " In flat with number " + numberOfFirstFlat + "\n";
        } else {
            resultText += secondFlat.getNumberOfLodger() + " In flat with number " + numberOfSecondFlat + "\n";
        }
        resultText += "Max value of Rooms:";
        if (firstFlat.getNumberOfRoom() > secondFlat.getNumberOfRoom()) {
            resultText += firstFlat.getNumberOfRoom() + " In flat with number " + numberOfFirstFlat;
        } else {
            resultText += secondFlat.getNumberOfRoom() + " In flat with number " + numberOfSecondFlat;
        }
        return resultText;
    }

    public int getNumberOfFloors(int numberOfHouse) {
        House house = HouseRepository.getHouseByNumber(numberOfHouse).get();
        return house.getFloors().size();
    }

    public int getFloorOfFlat(int numberOfFlat, int numberOfHouse) {
        int numberOfFlatInFlor = getValueOfFlatInFloor(numberOfHouse);
        House house = HouseRepository.getHouseByNumber(numberOfHouse).get();
        int numberOfFloors = getNumberOfFloors(numberOfHouse);
        return (((numberOfFlat - 1) % (numberOfFloors * numberOfFlatInFlor) / numberOfFlatInFlor) + 1);
    }

    public int getValueOfFlats(int numberOfHouse) {
        House house = HouseRepository.getHouseByNumber(numberOfHouse).get();
        int valueOfFlats = 0;
        FloorService floorService = new FloorService();
        for (Floor floor : house.getFloors()) {
            valueOfFlats += floorService.countingOfFlats(floor);
        }
        return valueOfFlats;
    }

    public int getValueOfFlatInFloor(int numberOfHouse) {
        House house = HouseRepository.getHouseByNumber(numberOfHouse).get();
        return house.getFloors().get(0).getNumberOfFlatsInFlor();
    }

    public double getTotalAreaOfHouse(int numberOfHouse) {
        House house = HouseRepository.getHouseByNumber(numberOfHouse).get();
        FloorService floorService = new FloorService();
        double totalArea = 0;
        for (Floor floor : house.getFloors()) {
            totalArea += floorService.countingOfSquare(floor);
        }
        return totalArea;
    }

    public int getTotalLodgersOfHouse(int numberOfHouse) {
        House house = HouseRepository.getHouseByNumber(numberOfHouse).get();
        FloorService floorService = new FloorService();
        int totalLodgers = 0;
        for (Floor floor : house.getFloors()) {
            totalLodgers += floorService.countingOfLodger(floor);
        }
        return totalLodgers;
    }

    public String compareTwoHouses(int numberOfFirstHouse, int numberOfSecondHouse) {
        String resultText = """
                Max square:
                """;
        if (getTotalAreaOfHouse(numberOfFirstHouse) > getTotalAreaOfHouse(numberOfSecondHouse)) {
            resultText += getTotalAreaOfHouse(numberOfFirstHouse) + " In house with number " + numberOfFirstHouse + "\n";
        } else {
            resultText += getTotalAreaOfHouse(numberOfSecondHouse) + " In house with number " + numberOfSecondHouse + "\n";
        }
        resultText += "Max value of Lodgers:";
        if (getTotalLodgersOfHouse(numberOfFirstHouse) > getTotalLodgersOfHouse(numberOfSecondHouse)) {
            resultText += getTotalLodgersOfHouse(numberOfFirstHouse) + " In house with number " + numberOfFirstHouse;
        } else {
            resultText += getTotalLodgersOfHouse(numberOfSecondHouse) + " In house with number " + numberOfSecondHouse;
        }
        return resultText;
    }
}
