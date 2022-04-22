package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDao;
import com.techelevator.reservations.dao.MemoryHotelDao;
import com.techelevator.reservations.dao.MemoryReservationDao;
import com.techelevator.reservations.dao.ReservationDao;
import com.techelevator.reservations.model.Hotel;
import com.techelevator.reservations.model.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {

    private HotelDao hotelDao;
    private ReservationDao reservationDao;

    public HotelController() {
        this.hotelDao = new MemoryHotelDao();
        this.reservationDao = new MemoryReservationDao(hotelDao);
    }

    /**
     * Return All Hotels
     *
     * @return a list of all hotels in the system
     *
     * Path: /hotels
     * HTTP: GET
     */
    @RequestMapping(path="/hotels", method = RequestMethod.GET)
    public List<Hotel> list() {
        return hotelDao.list();
    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     *
     * Path:  /hotels/{id} --> valid ids are (1-7)
     */
    @RequestMapping(path="/hotels/{id}", method = RequestMethod.GET)
    public Hotel get(@PathVariable int id) {
        return hotelDao.get(id);
    }

    /**
     * Returns all reservations in the system
     *
     * @return all reservations
     *
     * Path: /reservations
     */
    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<Reservation> listReservations() {
        return reservationDao.findAll();
    }


    /**
     * Get a reservation by its id
     *
     * @param id
     * @return a single reservation
     *
     * Path: /reservations/{id}
     */
    @RequestMapping(path = "/reservations/{id}", method = RequestMethod.GET)
    public Reservation getReservation(@PathVariable int id) {
        return reservationDao.get(id);
    }

    /**
     * List of reservations by hotel
     *
     * @param hotelID
     * @return all reservations for a given hotel
     *
     * Path: /hotels/{id}/reservations
     */
    @RequestMapping(path = "/hotels/{id}/reservations", method = RequestMethod.GET)
    public List<Reservation> listReservationsByHotel(@PathVariable("id") int hotelID) {
        return reservationDao.findByHotel(hotelID);
    }

    /**
     * Create a new reservation for a given hotel
     *
     * @param reservation
     *
     * Path: /reservations
     */
    @RequestMapping(path = "/reservations", method = RequestMethod.POST)
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationDao.create(reservation, reservation.getHotelID());
    }


    /**
     * /hotels/filter?state=oh&city=cleveland
     *
     * @param state the state to filter by
     * @param city  the city to filter by
     * @return a list of hotels that match the city & state
     *
     * Path: /hotels/filter?state={state}&city={city}
     */
    @RequestMapping(path = "/hotels/filter", method = RequestMethod.GET)
    public List<Hotel> filterByStateAndCity(@RequestParam String state, @RequestParam(required=false) String city) {

        List<Hotel> filteredHotels = new ArrayList<>();
        List<Hotel> hotels = list();

        // return hotels that match state
        for (Hotel hotel : hotels) {

            // if city was passed we don't care about the state filter
            if (city != null) {
                if (hotel.getAddress().getCity().toLowerCase().equals(city.toLowerCase())) {
                    filteredHotels.add(hotel);
                }
            } else {
                if (hotel.getAddress().getState().toLowerCase().equals(state.toLowerCase())) {
                    filteredHotels.add(hotel);
                }

            }
        }

        return filteredHotels;
    }

}


/*

SVC method: get reservation by id
PATH: /reservations/{id}
return: reservation info for given id using path variable

SVC method: list all reservations by hotel
PATH: /hotels/{id}/reservations
return: list of all reservations in the system by hotel


SVC method: add new reservation
PATH: /reservations
add a new reservation based on the request body

SVC Method: filter hotels
PATH: /hotels/filter?state={state}&city={city}
find hotels by state and city (optional)

 */