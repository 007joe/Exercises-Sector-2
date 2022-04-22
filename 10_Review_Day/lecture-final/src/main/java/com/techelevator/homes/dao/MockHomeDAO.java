package com.techelevator.homes.dao;

import com.techelevator.homes.exception.HomeNotFoundException;
import com.techelevator.homes.model.Home;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MockHomeDAO implements HomeDAO{

    private JdbcTemplate jdbcTemplate;

    public MockHomeDAO(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public List<Home> retrieveHomesForSale() {
        List<Home> homes = new ArrayList<>();

        Home home = new Home();
        home.setMlsNumber("1233");
        homes.add(home);
        return homes;
    }

    @Override
    public Home addHome(Home home) {
        return null;
    }

    @Override
    public Home retrieveHomeByMLSId(String mlsId) throws HomeNotFoundException {
        return null;
    }

    @Override
    public void deleteHome(String mlsId) throws HomeNotFoundException {

    }
}
