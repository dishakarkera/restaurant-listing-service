package com.dk.restaurantListing.mapper;

import com.dk.restaurantListing.dto.RestaurantDto;
import com.dk.restaurantListing.entity.Restaurant;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantMapper INSTANCE= Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDTOToRestaurant(RestaurantDto restaurantDto);
   // RestaurantDto mapRestaurantToRestaurantDTO(Restaurant restaurant);


    RestaurantDto mapRestaurantToRestaurantDTO(Restaurant restaurant);
}
