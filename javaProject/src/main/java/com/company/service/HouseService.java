package com.company.service;

import com.company.domain.Floor;
import com.company.domain.House;
import com.company.repository.HouseRepository;
//TODO тест
public class HouseService {
    public void safe(House house) {
        HouseRepository.addHouse(house);
    }

    public boolean isNumberFree(Integer numberOfHouse) {
        return HouseRepository.getHouseByNumber(numberOfHouse).isEmpty();
    }

    public boolean isNumberBusy(Integer numberOfHouse) {
        return HouseRepository.getHouseByNumber(numberOfHouse).isPresent();
    }

    public int getValueOfFlat(House house) {
        int valueOfFLat = 0;
        for (Floor floor : house.getFloors()) {
            valueOfFLat += floor.countingOfFlats();
        }
        return valueOfFLat;
    }

    public boolean isFlatExisting(Integer numberOfHouse, int numberOfFlat) {
        return (numberOfFlat > 0 && numberOfFlat <= HouseRepository.getHouseByNumber(numberOfHouse).get().getValueOfFLats());
    }
}
