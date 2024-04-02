package com.dk.restaurantListing.controller;

import com.dk.restaurantListing.dto.RestaurantDto;
import com.dk.restaurantListing.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {
    @InjectMocks
    RestaurantController restaurantController;
    @Mock
    RestaurantService service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAllRestaurant(){
        List<RestaurantDto> mockRestaurants= Arrays.asList(
              new RestaurantDto(1,"Restaurant 1","Address 1","city1","Desc 1")
       , new RestaurantDto(2,"Restaurant 2","Address 2","city2","Desc 2")  );

        when(service.findAllRestaurant()).thenReturn(mockRestaurants);
        //call the controller method
        ResponseEntity<List<RestaurantDto>> response=restaurantController.fetchAllRestaurant();


        //verfiy response

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockRestaurants,response.getBody());

        //verify service method was called
        verify(service,times(1)).findAllRestaurant();
    }

    @Test
    public void testSaveRestaurant(){
        RestaurantDto mockRestaurants= new RestaurantDto(1,"Restaurant 1","Address 1","city1","Desc 1");

        when(service.addRestaurant(mockRestaurants)).thenReturn(mockRestaurants);
        //call the controller method
        ResponseEntity<RestaurantDto> response=restaurantController.saveRestaurant(mockRestaurants);


        //verfiy response

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(mockRestaurants,response.getBody());

        //verify service method was called
        verify(service,times(1)).addRestaurant(mockRestaurants);
    }
    @Test
    public void testFetchRestaurantById(){
        Integer mockRestaurantId = 1;
        RestaurantDto mockRestaurants= new RestaurantDto(1,"Restaurant 1","Address 1","city1","Desc 1");

        when(service.findById(mockRestaurantId)).thenReturn( new ResponseEntity<RestaurantDto>(mockRestaurants,HttpStatus.OK) );
        //call the controller method
        ResponseEntity<RestaurantDto> response=restaurantController.saveRestaurant(mockRestaurantId);


        //verfiy response

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockRestaurants,response.getBody());

        //verify service method was called
        verify(service,times(1)).findById(mockRestaurantId);
    }
}
