package com.techelevator.dao;

import com.techelevator.model.State;

import java.util.List;

public interface StateDao {

    State getStateInformation(String stateAbbreviation);

}
