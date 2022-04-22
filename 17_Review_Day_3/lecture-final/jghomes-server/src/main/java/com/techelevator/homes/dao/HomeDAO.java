package com.techelevator.homes.dao;

import com.techelevator.homes.model.Home;
import com.techelevator.homes.exception.HomeNotFoundException;

import java.util.List;

public interface HomeDAO {

    List<Home> retrieveHomesForSale();
    Home addHome(Home home);
    Home retrieveHomeByMLSId(String mlsId) throws HomeNotFoundException;
    void deleteHome(String mlsId) throws HomeNotFoundException;

}
