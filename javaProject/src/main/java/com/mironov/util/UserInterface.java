package com.mironov.util;

import com.mironov.model.Floor;
import com.mironov.model.House;
import com.mironov.repository.HouseRepository;
import com.mironov.repository.impl.HouseRepositoryImpl;
import com.mironov.services.HouseService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class UserInterface {
    public static final String OPERATIONS_MENU = """
            Input value 0 to 6
            Choose 1 if you want create new house
            Choose 2 if you want to know information about an existing house
            Choose 3 if you want remove house
            Choose 4 if you want compare 2 houses
            Choose 5 if you want to know values of free flat in house
            Choose 6 if you want to check status of flat
            Choose 0 if you want exit
            """;
    public ArrayList<Integer> squareOfFLat = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);
    public HouseRepository houseRepository =HouseRepositoryImpl.getInstance();
    public HouseService houseService =HouseService.getInstance(houseRepository) ;//houseRepository

    public int getUserInput() {
        int userInput;
        do {
            System.out.println("Please enter positive Integer value");
            while (!scanner.hasNextInt()) {
                System.out.println("It's not Integer");
                scanner.next();
            }
            userInput = scanner.nextInt();
        } while (userInput < 0);
        return userInput;
    }

    private void showOperationsMenu() {
        System.out.println(OPERATIONS_MENU);
    }

    private String printHouse(House house) {
        StringBuilder floorsToString = new StringBuilder();
        for (Floor floor : house.getFloors()) {
            floorsToString.append(floor.toString());
        }
        return "House " + house.getNumberOfHouse() + '\n' +
                "Total Area: " + houseService.getTotalAreaOfHouse(house.getNumberOfHouse()) + '\n' +
                "Total value of Lodgers: " + houseService.getTotalLodgersOfHouse(house.getNumberOfHouse()) + '\n' +
                " floors:" + System.lineSeparator() + floorsToString;
    }

    public void showMenu() {

        int userChoice;
        do {
            showOperationsMenu();
            System.out.println("Choose number of operation");
            userChoice = getUserInput();
            switch (userChoice) {
                case 1 -> {
                    int numberOfHouse;
                    boolean isNumberFree;
                    do {
                        System.out.println("Input number of house:" + houseService.outputAllNumberOfHouses()
                                + ",choose 0 if you want return to menu");
                        numberOfHouse = getUserInput();
                        isNumberFree = houseService.isNumberFree(numberOfHouse);
                        if (numberOfHouse == 0) {
                            break;
                        }
                        if (!isNumberFree) {
                            System.out.println("Number is busy");
                        }
                    } while (!isNumberFree);
                    System.out.println("Input value of Floors in house,choose 0 if you want return to menu");
                    int valueOfFloorsInHouse = getUserInput();
                    if (numberOfHouse == 0) {
                        break;
                    }
                    System.out.println("Input value of flats in floor,choose 0 if you want return to menu");
                    int valueOfFlatsInFloor = getUserInput();
                    if (valueOfFlatsInFloor == 0) {
                        break;
                    }
                    for (int i = 0; i < valueOfFlatsInFloor; i++) {
                        System.out.println("Input square of " + (i + 1) + " flat");
                        this.squareOfFLat.add(getUserInput());
                    }
                    HouseGenerator houseGenerator = HouseGenerator.getInstance(valueOfFloorsInHouse);
                    House house = houseGenerator.createRandomHouse(squareOfFLat);
                    house.setNumberOfHouse(numberOfHouse);
                    houseService.save(house);
                }
                case 2 -> {
                    if (houseService.isRepositoryNull()) {
                        System.out.println("You not create no one houses ");
                        break;
                    }
                    System.out.println("Input number of house:" + houseService.outputAllNumberOfHouses()
                            + ",choose 0 if you want return to menu");
                    int numberOfHouse = getUserInput();
                    if (numberOfHouse == 0) {
                        break;
                    }
                    while (houseService.isNumberFree(numberOfHouse)) {
                        System.out.println("There is no house with this number.Please try again");
                        numberOfHouse = getUserInput();
                    }
                    System.out.println("""
                            If you want to know information about this house press 1
                            If you want to know information about flat from this house press 2
                            choose 0 if you want return to menu""");
                    int userOperation;
                    do {
                        userOperation = getUserInput();
                        if (userOperation == 0) {
                            break;
                        }
                        switch (userOperation) {
                            case 1 -> {
                                System.out.println(printHouse(houseService.findHouseByNumber(numberOfHouse)));
                            }
                            case 2 -> {
                                System.out.println("Input number of flat at 1" + " to " +
                                        houseService.getValueOfFlatsInHouse(numberOfHouse));
                                int numberOfFlat = getUserInput();
                                while (!houseService.isFlatExisting(numberOfHouse, numberOfFlat)) {
                                    System.out.println("You input number non-existent flat");
                                    numberOfFlat = getUserInput();
                                }
                                System.out.println(houseService.getFlatByNumber(numberOfFlat, numberOfHouse));

                            }
                            default -> {
                                System.out.println("You input incorrect value");
                            }
                        }
                    } while (userOperation != 1 && userOperation != 2);

                }
                case 3 -> {
                    if (houseService.isRepositoryNull()) {
                        System.out.println("You not create no one houses ");
                        break;
                    }
                    System.out.println("Input number of house" + houseService.outputAllNumberOfHouses()
                            + ",choose 0 if you want return to menu");
                    int numberOfHouse = getUserInput();
                    if (numberOfHouse == 0) {
                        break;
                    }
                    while (houseService.isNumberFree(numberOfHouse)) {
                        System.out.println("There is no house with this number.");
                        numberOfHouse = getUserInput();
                    }
                    houseRepository.removeByKey(numberOfHouse);
                    System.out.println("House with number " + numberOfHouse + " will be removed");
                }
                case 4 -> {
                    if (houseService.isRepositoryHaveTwoHouses()) {
                        System.out.println("You not create two houses ");
                        break;
                    }
                    int userInput = -1;
                    do {
                        System.out.println("""
                                If you want to compare houses press 1
                                If you want to compare flats  press 2
                                choose 0 if you want return to menu""");
                        userInput = getUserInput();
                        if (userInput == 0) {
                            break;
                        }
                        switch (userInput) {
                            case 1 -> {
                                System.out.println("Input number of first house" + houseService.outputAllNumberOfHouses()
                                        + ",choose 0 if you want return to menu");
                                int numberOfFirstHouse = getUserInput();
                                if (numberOfFirstHouse == 0) {
                                    break;
                                }
                                while (houseService.isNumberFree(numberOfFirstHouse)) {
                                    System.out.println("There is no house with this number.Please try again");
                                    numberOfFirstHouse = getUserInput();
                                }
                                System.out.println("Input number of second house" + houseService.outputAllNumberOfHouses());
                                int numberOfSecondHouse = getUserInput();
                                while (houseService.isNumberFree(numberOfSecondHouse) ||
                                        numberOfFirstHouse == numberOfSecondHouse) {
                                    System.out.println("There is no house with this number.Or ypu input similar " +
                                            "numbers of houses.Please try again");
                                    numberOfSecondHouse = getUserInput();
                                }
                                System.out.println(houseService.compareTwoHouses(numberOfFirstHouse, numberOfSecondHouse));
                            }
                            case 2 -> {
                                System.out.println("Input number of first house" + houseService.outputAllNumberOfHouses()
                                        + ",choose 0 if you want return to menu");
                                int numberOfFirstHouse = getUserInput();
                                if (numberOfFirstHouse == 0) {
                                    break;
                                }
                                while (houseService.isNumberFree(numberOfFirstHouse)) {
                                    System.out.println("There is no house with this number.Please try again");
                                    numberOfFirstHouse = getUserInput();
                                }
                                System.out.println(" Input number of first flat by " + numberOfFirstHouse + " house" +
                                        "(1-" + houseService.getValueOfFlatsInHouse(numberOfFirstHouse) + ")");
                                int numberOfFirstFlat = getUserInput();
                                while (houseService.isFlatNonExisting(numberOfFirstHouse, numberOfFirstFlat)) {
                                    System.out.println("There is no flat with this number.Please try again");
                                    numberOfFirstFlat = getUserInput();
                                }

                                System.out.println("Input number of second house" + houseService.outputAllNumberOfHouses());
                                int numberOfSecondHouse = getUserInput();
                                while (houseService.isNumberFree(numberOfSecondHouse)) {
                                    System.out.println("There is no house with this number.Please try again");
                                    numberOfSecondHouse = getUserInput();
                                }

                                System.out.println(" Input number of first flat by " + numberOfSecondHouse + " house" +
                                        "(1-" + houseService.getValueOfFlatsInHouse(numberOfSecondHouse) + ")");
                                int numberOfSecondFlat = getUserInput();
                                while (houseService.isFlatNonExisting(numberOfSecondHouse, numberOfSecondFlat)
                                        || (numberOfFirstFlat == numberOfSecondFlat && numberOfFirstHouse==numberOfSecondHouse)) {
                                    System.out.println("There is no flat with this number or you input similar numbers " +
                                            "of flats\n" +
                                            ".Please try again");
                                    numberOfSecondFlat = getUserInput();
                                }

                                System.out.println(houseService.compareTwoFlats(numberOfFirstFlat, numberOfFirstHouse,
                                        numberOfSecondFlat, numberOfSecondHouse));

                            }
                            default -> System.out.println("You Input Incorrect value,please try again ");

                        }
                    } while (userInput != 1 && userInput != 2);

                }
                case 5 ->{
                    int numberOfHouse;
                    boolean isNumberFree;
                    do {
                        System.out.println("Input number of house:" + houseService.outputAllNumberOfHouses()
                                + ",choose 0 if you want return to menu");
                        numberOfHouse = getUserInput();
                        isNumberFree = houseService.isNumberFree(numberOfHouse);
                        if (numberOfHouse == 0) {
                            break;
                        }
                        if (isNumberFree) {
                            System.out.println("House is not exist");
                        }
                    } while (isNumberFree);
                    int valueOfFreeFlats=houseRepository.getValueOfFreeFlats(numberOfHouse);
                    System.out.println("Value of Free Flats: "+valueOfFreeFlats);
                }
                case 6->{
                    System.out.println("Input number of  house" + houseService.outputAllNumberOfHouses()
                            + ",choose 0 if you want return to menu");
                    int numberOfHouse = getUserInput();
                    if (numberOfHouse == 0) {
                        break;
                    }
                    while (houseService.isNumberFree(numberOfHouse)) {
                        System.out.println("There is no house with this number.Please try again");
                        numberOfHouse = getUserInput();
                    }
                    System.out.println(" Input number of  flat by " + numberOfHouse + " house" +
                            "(1-" + houseService.getValueOfFlatsInHouse(numberOfHouse) + ")");
                    int numberOfFirstFlat = getUserInput();
                    while (houseService.isFlatNonExisting(numberOfHouse, numberOfFirstFlat)) {
                        System.out.println("There is no flat with this number.Please try again");
                        numberOfFirstFlat = getUserInput();
                    }
                    if (houseRepository.isFlatInNormal(numberOfFirstFlat,numberOfHouse)){
                        System.out.println("This flat in normal");
                    }
                    else{
                        System.out.println("This flat does not have enough living space ");
                    }
                }
                case 0 -> {
                    System.out.println("Thank you for using CreatorHouse v2.2");

                }
                default -> {
                    System.out.println("You input incorrect value ...\n");
                }
            }
        } while (userChoice != 0);
    }
}
