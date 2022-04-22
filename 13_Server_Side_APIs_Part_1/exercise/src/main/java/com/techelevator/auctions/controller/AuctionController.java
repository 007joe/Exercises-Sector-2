package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")     // localhost:8080/auctions
public class AuctionController {

    private AuctionDao dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDao();
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Auction> listAuctions(@RequestParam(defaultValue = "") String title_like, @RequestParam(defaultValue = "0") double currentBid_lte) {
        if(currentBid_lte > 0 && !title_like.isEmpty()) {
            return dao.searchByTitleAndPrice(title_like, currentBid_lte);
        } else if(!title_like.isEmpty()) {
            return dao.searchByTitle(title_like);
        } else if (currentBid_lte > 0) {
            return dao.searchByPrice(currentBid_lte);
        }
        return dao.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Auction createAuction(@RequestBody Auction auction) {
        return dao.create(auction);
    }

    @RequestMapping(path  = "/{id}", method = RequestMethod.GET)
    public Auction getAuction(@PathVariable int id) {

        return dao.get(id);
    }





}
