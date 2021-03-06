package com.mironov.services;

import com.mironov.model.Flat;
import com.mironov.model.Floor;
import com.mironov.model.House;
import com.mironov.repository.FlatRepository;
import com.mironov.repository.HouseRepository;
import com.mironov.repository.impl.FlatRepositoryImpl;
import com.mironov.util.SingletonFactory;
import com.mironov.util.UserInterface;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class HouseService  {

    private final HouseRepository houseRepository;



    private static HouseService instance=null;

    public HouseService(final HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public static synchronized HouseService getInstance(final HouseRepository houseRepository){
        if (Objects.isNull(instance)){
            instance=new HouseService(houseRepository);
        }
        return instance;
    }

    public void save(House house)  {
        try {
            houseRepository.add(house);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isNumberFree(Integer numberOfHouse) {
        return !houseRepository.getAllKey().contains(numberOfHouse);
    }

    public boolean isFlatExisting(Integer numberOfHouse, int numberOfFlat) {
        return (numberOfFlat > 0 && numberOfFlat <= getValueOfFlatsInHouse(numberOfHouse));
    }

    public House findHouseByNumber(Integer numberOfHouse) {
        UserInterface userInterface = new UserInterface();
        while (isNumberFree(numberOfHouse)) {
            System.out.println("There is no house with this number.Please try again");
            numberOfHouse = userInterface.getUserInput();
        }
        //return houseRepository.getByKey(numberOfHouse).get();
        return  houseRepository.getByKey(numberOfHouse).get();
    }

    public boolean isFlatNonExisting(Integer numberOfHouse, int numberOfFlat) {
        return !(numberOfFlat > 0 && numberOfFlat <= getValueOfFlatsInHouse(numberOfHouse));
    }

    public Flat getFlatByNumber(int numberOfFlat, int numberOfHouse) {
        Flat resultFlat = null;
        int numberOfFloor = getFloorOfFlat(numberOfFlat, numberOfHouse);
        House house = findHouseByNumber(numberOfHouse);
        Floor floor = house.getFloors().get(numberOfFloor - 1);
        for (Flat flat : floor.getFlats()) {
            if (flat.getNumberOfFlat() == numberOfFlat) {
                resultFlat = flat;
            }
        }
        return resultFlat;
    }

    public int getNumberOfFloors(int numberOfHouse) {
        House house = findHouseByNumber(numberOfHouse);
        return house.getFloors().size();
    }

    public int getFloorOfFlat(int numberOfFlat, int numberOfHouse) {
        int numberOfFlatInFlor = getValueOfFlatInFloor(numberOfHouse);
        int numberOfFloors = getNumberOfFloors(numberOfHouse);
        return (((numberOfFlat - 1) % (numberOfFloors * numberOfFlatInFlor) / numberOfFlatInFlor) + 1);
    }

    public int getValueOfFlatsInHouse(int numberOfHouse) {
        House house = findHouseByNumber(numberOfHouse);
        int valueOfFlats = 0;
        FloorService floorService = SingletonFactory.getObject(FloorService.class);
        for (Floor floor : house.getFloors()) {
            valueOfFlats += floorService.getCountingOfFlatsInFloor(floor);
        }
        return valueOfFlats;
    }

    public int getValueOfFlatInFloor(int numberOfHouse) {
        House house = findHouseByNumber(numberOfHouse);
        return house.getFloors().get(0).getNumberOfFlats();
    }

    public double getTotalAreaOfHouse(int numberOfHouse) {
        House house = findHouseByNumber(numberOfHouse);
       FloorService floorService = SingletonFactory.getObject(FloorService.class);
        double totalArea = 0;
        for (Floor floor : house.getFloors()) {
            totalArea += floorService.getCountingOfSquare(floor);
        }
        return totalArea;
    }

    public int getTotalLodgersOfHouse(int numberOfHouse) {
        House house = findHouseByNumber(numberOfHouse);
        FloorService floorService = SingletonFactory.getObject(FloorService.class);
        int totalLodgers = 0;
        for (Floor floor : house.getFloors()) {
            totalLodgers += floorService.getCountingOfLodger(floor);
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


    public boolean isRepositoryNull() {
        return houseRepository.getAllKey().isEmpty();
    }

    public boolean isRepositoryHaveTwoHouses() {
        return houseRepository.getAllKey().size() < 2;
    }

    public String outputAllNumberOfHouses() {
        return houseRepository.getAllKey().toString();
    }
}
