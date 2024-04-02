package com.dk.restaurantListing.service;


import com.dk.restaurantListing.dto.RestaurantDto;
import com.dk.restaurantListing.entity.Restaurant;
import com.dk.restaurantListing.mapper.RestaurantMapper;
import com.dk.restaurantListing.repo.RestaurantRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepo repo;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindAllRestaurant() {
        List<Restaurant> mockRestaurants= Arrays.asList(
                new Restaurant(1,"Restaurant 1","Address 1","city1","Desc 1")
                , new Restaurant(2,"Restaurant 2","Address 2","city2","Desc 2")  );

        when(repo.findAll()).thenReturn(mockRestaurants);

        List<RestaurantDto> mockRestaurantDTOList=  restaurantService.findAllRestaurant();

        assertEquals(mockRestaurants.size(),mockRestaurantDTOList.size());
        for(int i=0;i<mockRestaurants.size();i++){
            RestaurantDto expectedDto= RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(mockRestaurants.get(i));
            assertEquals(expectedDto,mockRestaurantDTOList.get(i));
        }

        verify(repo,times(1)).findAll();
    }

    @Test
    public void testAddRestaurant() {

        RestaurantDto mockRestaurantDTO= new RestaurantDto(1,"Restaurant 1","Address 1","city1","Desc 1");
        Restaurant mockRestaurant= RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(mockRestaurantDTO);
        when(repo.save(mockRestaurant)).thenReturn(mockRestaurant);

        RestaurantDto expectedRestaurantDTO=  restaurantService.addRestaurant(mockRestaurantDTO);

        assertEquals(expectedRestaurantDTO,mockRestaurantDTO);

        verify(repo,times(1)).save(mockRestaurant);
    }

    @Test
    public void testFindById_ExistingId() {
        Integer restaurantId=1;


        Restaurant mockRestaurant= new Restaurant(1,"Restaurant 1","Address 1","city1","Desc 1");
        when(repo.findById(restaurantId)).thenReturn(Optional.of(mockRestaurant));

        ResponseEntity<RestaurantDto> expectedRestaurantDTO=  restaurantService.findById(restaurantId);

        assertEquals(expectedRestaurantDTO.getStatusCode(), HttpStatus.OK);
        assertEquals(expectedRestaurantDTO.getBody().getId(), restaurantId);

        verify(repo,times(1)).findById(restaurantId);
    }

    @Test
    public void testFindById_NonExistingId() {
        Integer restaurantId=1;


        when(repo.findById(restaurantId)).thenReturn(Optional.empty());

        ResponseEntity<RestaurantDto> expectedRestaurantDTO=  restaurantService.findById(restaurantId);

        assertEquals(expectedRestaurantDTO.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(expectedRestaurantDTO.getBody(), null);

        verify(repo,times(1)).findById(restaurantId);
    }
}
