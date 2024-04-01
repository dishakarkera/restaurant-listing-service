package com.dk.restaurantListing.service;

import com.dk.restaurantListing.dto.RestaurantDto;
import com.dk.restaurantListing.entity.Restaurant;
import com.dk.restaurantListing.mapper.RestaurantMapper;
import com.dk.restaurantListing.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo repo;

    public List<RestaurantDto> findAllRestaurant() {
        List<Restaurant> restList=repo.findAll();

        List<RestaurantDto> restDtoList =  restList.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());;
        return restDtoList;
    }

    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
        Restaurant saveRestaurant = repo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDto));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(saveRestaurant);
    }

    public ResponseEntity<RestaurantDto> findById(Integer id) {
        Optional<Restaurant> byId = repo.findById(id);
        return byId.map(restaurant -> new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }
}
