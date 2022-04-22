package com.techelevator.dao;

import com.techelevator.model.State;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;


public class JdbcStateDao implements StateDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcStateDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public State getStateInformation(String stateAbbreviation) {
        State state = null;
        String sql = "SELECT s.state_abbreviation, s.state_name, s.population, s.area, s.capital, c.city_name, s.sales_tax, s.state_nickname, "
                    + "s.census_region FROM state s JOIN city c ON s.capital = c.city_id WHERE s.state_abbreviation = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateAbbreviation);
        if (results.next()) {
            state = mapRowToState(results);
        }
        return state;
    }


    private State mapRowToState(SqlRowSet rowSet) {
        State state = new State();
        state.setStateAbbreviation(rowSet.getString("state_abbreviation"));
        state.setStateName(rowSet.getString("state_name"));
        state.setPopulation(rowSet.getLong("population"));
        state.setArea(rowSet.getLong("area"));
        state.setCapital_id(rowSet.getInt("capital"));
        state.setCapitalName(rowSet.getString("city_name"));
        state.setCensusRegion(rowSet.getString("census_region"));
        state.setStateNickName(rowSet.getString("state_nickname"));
        state.setStateSalesTax(rowSet.getDouble("sales_tax"));
        return state;
    }
}

