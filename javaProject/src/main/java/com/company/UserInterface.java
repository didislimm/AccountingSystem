package com.company;

import com.company.domain.House;
import com.company.repository.HouseRepository;
import com.company.service.HouseService;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserInterface {
    public static final String OPERATIONS_MENU = """
            Input value 0 to 5
            Choose 1 if you want create new house
            Choose 2 if you want to know information about an existing house
            Choose 3 if you want remove house
            Choose 4 if you want compare 2 houses
            Choose 5 if you want compare 2 flat
            Choose 0 if you want exit
            """;

    public ArrayList<Integer> squareOfFLat = new ArrayList<Integer>();
    Scanner scanner = new Scanner(System.in);
    HouseService houseService = new HouseService();

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

    public void showMenu() {
        int userChoice;
        do {
            showOperationsMenu();
            System.out.println("Choose number of operation");
            userChoice = getUserInput();
            switch (userChoice) {
                case 1: {
                    int numberOfHouse;
                    boolean isNumberFree = false;
                    do {
                        System.out.println("Input number of house,choose 0 if you want return to menu");
                        numberOfHouse = getUserInput();
                        if (numberOfHouse == 0) {
                            break;
                        }
                        isNumberFree = houseService.isNumberFree(numberOfHouse);
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
                        System.out.println("Input square of " + (i + 1));
                        this.squareOfFLat.add(getUserInput());
                    }
                    HouseGenerator houseGenerator = new HouseGenerator(valueOfFlatsInFloor, valueOfFloorsInHouse);
                    House house = houseGenerator.createHouse(squareOfFLat);
                    house.setNumberOfHouse(numberOfHouse);
                    houseService.safe(house);
                    break;
                }
                case 2: {
                    if (HouseRepository.isRepositoryNull()) {
                        System.out.println("You not create no one houses ");
                        break;
                    }
                    System.out.println("Input number of house,choose 0 if you want return to menu");
                    int numberOfHouse = getUserInput();
                    if (numberOfHouse == 0) {
                        break;
                    }
                    while (houseService.isNumberFree(numberOfHouse)) {
                        System.out.println("There is no house with this number.");
                        numberOfHouse = getUserInput();
                    }
                    System.out.println("""
                            If you want to know information about this house press 1
                            If you want to know information about flat from this house press 2
                            choose 0 if you want return to menu""");
                    int userOperation = getUserInput();
                    if (numberOfHouse == 0) {
                        break;
                    }
                    switch (userOperation) {
                        case 1: {
                            System.out.println(HouseRepository.getHouseByNumber(numberOfHouse));
                        }
                        case 2: {
                            System.out.println("Input number of flat at 1"+" to "+HouseRepository
                                    .getHouseByNumber(numberOfHouse).get().getValueOfFLats());
                            int numberOfFlat=getUserInput();
                            while (!houseService.isFlatExisting(numberOfHouse,numberOfFlat)){
                                System.out.println("You input number non-existent flat");
                                numberOfFlat=getUserInput();
                            }
                          System.out.println(HouseRepository.getHouseByNumber(numberOfHouse).get().getFlat(numberOfFlat));
                        }
                    }

                    break;
                }
                case 3: {
                    if (HouseRepository.isRepositoryNull()) {
                        System.out.println("You not create no one houses ");
                        break;
                    }
                    System.out.println("Input number of house,choose 0 if you want return to menu");
                    int numberOfHouse = getUserInput();
                    if (numberOfHouse == 0) {
                        break;
                    }
                    while (houseService.isNumberFree(numberOfHouse)) {
                        System.out.println("There is no house with this number.");
                        numberOfHouse = getUserInput();
                    }
                    HouseRepository.removeHouse(numberOfHouse);
                    System.out.println("House with number" + numberOfHouse + "will be removed");
                    break;
                }
                case 4: {
                    if (HouseRepository.isRepositoryNull()) {
                        System.out.println("You not create no one houses ");
                        break;
                    }
                    System.out.println("Choose two number of houses which you want to compare");
                    int numberOfHouse1 = getUserInput();
                    if (numberOfHouse1 == 0) {
                        break;
                    }
                    while (houseService.isNumberFree(numberOfHouse1)) {
                        System.out.println("There is no house with this number.");
                        numberOfHouse1 = getUserInput();
                    }
                    int numberOfHouse2 = getUserInput();
                    while (houseService.isNumberFree(numberOfHouse2)) {
                        System.out.println("There is no house with this number.");
                        numberOfHouse2 = getUserInput();
                    }
                    System.out.println(HouseRepository.getHouseByNumber(numberOfHouse1));
                    System.out.println(HouseRepository.getHouseByNumber(numberOfHouse2));
                }
                case 5: {
                    if (HouseRepository.isRepositoryNull()) {
                        System.out.println("You not create no one houses ");
                        break;
                    }


                }
                case 0: {
                    System.out.println("Thank you for using CreatorHouse v1.3");
                    break;
                }
                default: {
                    System.out.println("You input incorrect value ...\n");
                }
            }
        } while (userChoice != 0);
    }
}
