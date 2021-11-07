package com.company.service;

import com.company.domain.House;
import com.company.repository.HouseRepository;

public class HouseService {
    public void safe(House house){
        HouseRepository.addHouse(house);
    }
    public boolean isNumberFree(Integer numberOfHouse){
        return HouseRepository.getHouseByNumber(numberOfHouse).isEmpty();
    }
    public boolean isNumberBusy(Integer numberOfHouse){
        return HouseRepository.getHouseByNumber(numberOfHouse).isPresent();
    }

}
