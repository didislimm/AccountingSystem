package com.mironov.services;

import com.mironov.model.*;
import com.mironov.repository.HouseRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoException.class)
@RunWith(MockitoJUnitRunner.class)
class HouseServiceTest {

    static House house = new House();

    @InjectMocks
    HouseService houseService;

    @Mock
    static HouseRepository houseRepository= mock(HouseRepository.class);

    @BeforeAll
    static void setUp() {
        Floor floor1 = new Floor();
        for (int i = 0; i < 5; i++) {
            floor1.getFlats().add(Flat.FlatBuilder
                    .aFlat()
                    .withNumberOfFlat(3)
                    .withNumberOfLodger(4)
                    .withNumberOfRoom(3)
                    .withSquareOfFlat(55)
                    .build());
        }
        Floor floor2 = new Floor();
        for (int i = 0; i < 5; i++) {
            floor2.getFlats().add(Flat.FlatBuilder
                    .aFlat()
                    .withNumberOfFlat(2)
                    .withNumberOfLodger(4)
                    .withNumberOfRoom(4)
                    .withSquareOfFlat(65)
                    .build());
        }
        house.getFloors().add(floor1);
        house.getFloors().add(floor2);
        house.setNumberOfHouse(1);
    }
    @Test
    void safe() {
    }

    @Test
    void isNumberFreeTrueExitTest() {
        when(houseRepository.getByKey(anyInt())).thenReturn(Optional.of(house));
        boolean result=houseService.isNumberFree(1);
        assertFalse(result);
    }

    @Test
    void isNumberFreeFalseExitTest() { assertTrue(houseService.isNumberFree(2)); }
    @Test
    void isFlatExisting() {
        assertTrue(houseService.isFlatExisting(1, 10));
    }

    @Test
    void isFlatNonExisting() {
        assertTrue(houseService.isFlatNonExisting(1, 11));
    }

    @Test
    void findHouseByNumber() {
    }

    @Test
    void getFlatByNumber() {
    }

    @Test
    void getNumberOfFloors() {
    }

    @Test
    void getFloorOfFlat() {
    }

    @Test
    void getValueOfFlats() {
    }

    @Test
    void getValueOfFlatInFloor() {
    }

    @Test
    void getTotalAreaOfHouse() {
    }

    @Test
    void getTotalLodgersOfHouse() {
    }

    @Test
    void compareTwoHouses() {
    }

    @Test
    void compareTwoFlats() {
    }

    @Test
    void isRepositoryNull() {

    }

    @Test
    void isRepositoryHaveTwoHouses() {
    }

    @Test
    void outputAllNumberOfHouses() {
    }
}