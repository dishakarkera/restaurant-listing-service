package com.dk.restaurantListing.controller;

import com.dk.restaurantListing.dto.RestaurantDto;
import com.dk.restaurantListing.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService service;


    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDto>> fetchAllRestaurant(){
        List<RestaurantDto> list=service.findAllRestaurant();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDto> saveRestaurant(@RequestBody RestaurantDto restaurantDto){
        RestaurantDto restaurantAdded=service.addRestaurant(restaurantDto);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }
    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDto> saveRestaurant(@PathVariable Integer id){
        ResponseEntity<RestaurantDto> restaurantDets=service.findById(id);
        return restaurantDets;
    }

}
