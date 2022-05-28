package com.mironov.util;

import com.mironov.repository.HouseRepository;
//import com.mironov.repository.impl.FloorRepositoryImpl;
import com.mironov.repository.impl.HouseRepositoryImpl;
import com.mironov.services.FloorService;
import com.mironov.services.HouseService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public final class SingletonFactory {

    private static Map<Class, Supplier> classToObject = new HashMap<>() {{
        put(FloorService.class, SingletonFactory::getFloorService);
        put(HouseService.class,SingletonFactory::getHouseService);
        put(HouseRepository.class,SingletonFactory::getHouseRepository);
    }};

    public static <T> T getObject(final Class<T> clazz) {
        Supplier supplier = classToObject.get(clazz);
        if (Objects.isNull(supplier)){
            throw new RuntimeException("Class not found");
        }
        return (T)supplier.get();
    }

    private static FloorService getFloorService() {
        return FloorService.getInstance();
    }

    private static HouseService getHouseService(){return HouseService.getInstance(getHouseRepository());}

    private static HouseRepository getHouseRepository(){ return HouseRepositoryImpl.getInstance();}

//    private static HouseGenerator getHouseGenerator(){ return HouseGenerator.getInstance();}

}
