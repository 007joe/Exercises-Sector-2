package com.techelevator.dao;

import com.techelevator.model.City;

import java.util.List;

public interface CityDao {

    City getCityById(long cityId);

    City getCityByNameAndState( String cityName, String stateAbbreviation );

    List<City> getCityListByStateAbbreviation(String stateAbbreviation);

    City createCity(City city);

    void updateCity(City city);

    void deleteCity(long cityId);
}
