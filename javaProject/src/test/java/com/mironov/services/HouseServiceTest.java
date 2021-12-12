package com.mironov.services;

import com.mironov.model.Flat;
import com.mironov.model.Floor;
import com.mironov.model.House;
import com.mironov.repository.HouseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class HouseServiceTest {

    static House house = new House();

    @InjectMocks
    HouseService houseService;

    @Mock
    HouseRepository houseRepository;

    @BeforeAll
    static void setUp() {
        Floor floor1 = new Floor();
        floor1.setNumberOfFloor(1);
        for (int i = 0; i < 5; i++) {
            floor1.getFlats().add(Flat.FlatBuilder
                    .aFlat()
                    .withNumberOfFlat(i + 1)
                    .withNumberOfLodger(4)
                    .withNumberOfRoom(3)
                    .withSquareOfFlat(55)
                    .build());
        }
        floor1.setNumberOfFlatsInFloor(5);
        Floor floor2 = new Floor();
        floor2.setNumberOfFloor(2);
        floor2.setNumberOfFlatsInFloor(5);
        for (int i = 0; i < 5; i++) {
            floor2.getFlats().add(Flat.FlatBuilder
                    .aFlat()
                    .withNumberOfFlat(6 + i)
                    .withNumberOfLodger(4)
                    .withNumberOfRoom(4)
                    .withSquareOfFlat(65)
                    .build());
        }
        house.getFloors().add(floor1);
        house.getFloors().add(floor2);
        house.setNumberOfHouse(1);
    }

    @BeforeEach
    void mock() {
        when(houseRepository.getByKey(anyInt())).thenReturn(Optional.of(house));
    }

    @Test
    void saveTest() {
        houseService.save(house);
    }

    @Test
    void isNumberFreeTrueExitTest() {
        assertFalse(houseService.isNumberFree(1));
    }

    @Test
    void isNumberFreeFalseExitTest() {
        when(houseRepository.getByKey(anyInt())).thenReturn(Optional.empty());
        assertTrue(houseService.isNumberFree(2));
    }

    @Test
    void isFlatExistingTest() {
        assertTrue(houseService.isFlatExisting(1, 10));
    }

    @Test
    void isFlatNonExistingTest() {
        assertTrue(houseService.isFlatNonExisting(1, 11));
    }

//    @Test
//    void findHouseByNumberTest() {
//
//    }

    @Test
    void getFlatByNumberTrueExitTest() {
        assertEquals(house.getFloors().get(1).getFlats().get(2),
                houseService.getFlatByNumber(8, 1));
    }

    @Test
    void getFlatByNumberFalseExitTest() {
        assertNull(houseService.getFlatByNumber(12, 1));
    }

    @Test
    void getNumberOfFloorsTest() {
        assertEquals(2, houseService.getNumberOfFloors(1));
    }

    @Test
    void getFloorOfFlatTrueExitTest() {
        assertEquals(1, houseService.getFloorOfFlat(5, 1));
    }

    @Test
    void getFloorOfFlatFalseExitTest() {
        assertNotEquals(2, houseService.getFloorOfFlat(5, 1));
    }

    @Test
    void getValueOfFlatsInHouseTest() {
        assertEquals(10, houseService.getValueOfFlatsInHouse(1));
    }

    @Test
    void getValueOfFlatInFloorTest() {
        assertEquals(5, houseService.getValueOfFlatInFloor(1));
    }

    @Test
    void getTotalAreaOfHouseTest() {
        assertEquals(600, houseService.getTotalAreaOfHouse(1));
    }

    @Test
    void getTotalLodgersOfHouseTest() {
        assertEquals(40, houseService.getTotalLodgersOfHouse(1));
    }
}