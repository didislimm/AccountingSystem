package com.company;

import javax.crypto.AEADBadTagException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    ArrayList<House> houses=new ArrayList<House>();
    int [] squareOfFLat;
    Scanner scanner=new Scanner(System.in);

    public int userInput() {
        int userNumber;
        do {
           // System.out.println("Please enter positive Integer value");
            while (!scanner.hasNextInt()) {
                System.out.println("It's not Integer");
                scanner.next();
            }
            userNumber = scanner.nextInt();
        } while (userNumber <= 0);
        return userNumber;
    }

    private void inputMenu() {
        System.out.println("Input value 0 to 3" + System.lineSeparator()
                + "Choose 1 if you want create  new house" + System.lineSeparator()
                + "Choose 2 if you want to know information about an existing house" + System.lineSeparator()
                + "Choose 3 if you want remove house" + System.lineSeparator()
                + "Choose 4 if you want compare 2 houses" + System.lineSeparator()
                + "Choose 5 if you want compare 2 flat" + System.lineSeparator()
                + "Choose 0 if you want exit");
    }

    public void menu() {
            int userChoice;
            do {
                inputMenu();
                System.out.println("Choose number of operation");
                userChoice = userInput();
                switch (userChoice) {
                    case 0: {
                        System.out.println("Thank you for using CreatorHouse v1.0");
                    }
                    case 1: {
                        System.out.println("Input value of flats in floor");
                        int valueOfFlatsInFloor = userInput();
                        System.out.println("Input value of Floors in house");
                        int valueOfFloorsInHouse = userInput();
                        int temp=valueOfFlatsInFloor;
                        for (int i=0;i<valueOfFlatsInFloor;i++){
                            System.out.println("Input square of "+(i+1)+" flat");
                            squareOfFLat[i]=userInput();
                        }
                        HouseGenerator houseGenerator = new HouseGenerator(valueOfFlatsInFloor, valueOfFloorsInHouse);
                        House house = houseGenerator.randomHouseCreator();
                        houses.add(house);
                    }
                    case 2: {

                    }
                    default: {
                        System.out.println("You input incorrect value ...\n");
                    }
                }
            } while (userChoice != 0);
        }
    }
