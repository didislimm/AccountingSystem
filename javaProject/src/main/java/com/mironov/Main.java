package com.mironov;

import com.mironov.repository.HouseRepository;
import com.mironov.repository.impl.FlatRepositoryImpl;
import com.mironov.repository.impl.HouseRepositoryImpl;
import com.mironov.util.UserInterface;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.showMenu();
    }
}
