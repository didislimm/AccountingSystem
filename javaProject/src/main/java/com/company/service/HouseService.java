package com.company.service;

import com.company.domain.House;
import com.company.repository.HouseRepository;

//TODO дом сервис сохранить дом
public class HouseService {
    public void safe(House house){

    }
    public boolean isNumberFree(Integer numberOfHouse){
        return HouseRepository.getHouseByNumber(numberOfHouse).isEmpty();
    }
}
