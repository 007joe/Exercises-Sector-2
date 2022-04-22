package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.exception.AuctionNotFoundException;
import com.techelevator.auctions.model.Auction;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/auctions")
@PreAuthorize("isAuthenticated()")
public class AuctionController {

    private AuctionDao dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDao();
    }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(defaultValue = "") String title_like, @RequestParam(defaultValue = "0") double currentBid_lte) {

        if (!title_like.equals("")) {
            return dao.searchByTitle(title_like);
        }
        if (currentBid_lte > 0) {
            return dao.searchByPrice(currentBid_lte);
        }

        return dao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) throws AuctionNotFoundException {
        return dao.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public Auction create(@Valid @RequestBody Auction auction) {
        return dao.create(auction);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public Auction update(@Valid @RequestBody Auction auction, @PathVariable int id) throws AuctionNotFoundException {
        return dao.update(auction, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) throws AuctionNotFoundException {
        dao.delete(id);
    }

    @RequestMapping(path = "/whoami")
    public String whoAmI(Principal principal) {
      return principal.getName();
    }

}
