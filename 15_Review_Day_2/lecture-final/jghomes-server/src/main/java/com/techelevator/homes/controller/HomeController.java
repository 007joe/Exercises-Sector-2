package com.techelevator.homes.controller;
import com.techelevator.homes.dao.HomeDAO;
import com.techelevator.homes.exception.HomeNotFoundException;
import com.techelevator.homes.model.Address;
import com.techelevator.homes.model.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private HomeDAO homeDAO;

//    public HomeController(HomeDAO homeDAO) {     //dependency injection using constructor - option 1
//        this.homeDAO = homeDAO;
//    }
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @RequestMapping(path="/hello", method = RequestMethod.GET)
    public String helloService() {
        return "Hello. I am alive!";
    }

    @RequestMapping(path = "/homes", method = RequestMethod.GET)
    public List<Home> retrieveAllHomes() {
        return homeDAO.retrieveHomesForSale();
    }

    @RequestMapping(path = "/homes/{mlsId}", method = RequestMethod.GET)
    public Home retrieveHomeByMLSID(@PathVariable String mlsId) throws HomeNotFoundException {
        return homeDAO.retrieveHomeByMLSId(mlsId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/homes", method = RequestMethod.POST)
    public Home addHome(@Valid @RequestBody Home home) {
        return homeDAO.addHome(home);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/homes/{mlsId}", method = RequestMethod.DELETE)
    public void deleteHome(@PathVariable String mlsId) throws HomeNotFoundException {
        homeDAO.deleteHome(mlsId);
    }

}