package com.example.praca_inz_api.service;

import com.example.praca_inz_api.dao.AllergiesDao;
import com.example.praca_inz_api.dao.FoodDao;
import com.example.praca_inz_api.dao.UserDao;
import com.example.praca_inz_api.dto.FoodDTO;
import com.example.praca_inz_api.model.Allergies;
import com.example.praca_inz_api.model.Food;
import com.example.praca_inz_api.model.User;
import com.example.praca_inz_api.repository.FoodRepo;
import com.example.praca_inz_api.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService implements FoodRepo {
    @Autowired
    private FoodDao foodDao;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AllergiesDao allergiesDao;




    @Override
    public Collection<Food> getAllType(String type) {
        return foodDao.findAll().stream()
                .filter(food -> food.getType().equals(type))
                .collect(Collectors.toList());
    }

 

    @Override
    public Food getFoodById(String foodId) {
        return foodDao.findById(foodId)
                .orElseThrow(() -> new IllegalStateException(
                        "Food with " + foodId + " does not exists."));
    }





    @Override
    public Food createFood(FoodDTO foodDTO) {
            Food food = new Food();
//            Food food1 = foodDao.findByFoodName(foodDTO.getFoodName());
            List<Food> myList = userRepo.getMyFoodList(foodDTO.getType(), foodDTO.getUsername());
            food.setFoodName(foodDTO.getFoodName());
            food.setComposition(foodDTO.getComposition());
            food.setType(foodDTO.getType());
            food.setFavourite(foodDTO.getFavourite());
            boolean anyMatch = myList.stream().anyMatch(item -> item.getFoodName().equals(food.getFoodName()));
            if (anyMatch){
                return null;
            }
            return foodDao.save(food);
    }


    @Override
    public Food addFoodToUser(FoodDTO foodDTO) {
        Food food = createFood(foodDTO);
        if(food ==null){
            return null;
        }
        userRepo.addFoodToList(food,foodDTO.getUsername());
        return food;
    }



    @Override
    public void deleteFoodById(String foodId, String username) {
        Food food = foodDao.findBy_id(foodId);
        User user = userRepo.getUserByUsername(username);
        user = userRepo.deleteFoodFromUser(user, food);
        userDao.save(user);
        Allergies allergies = allergiesDao.findByAllergenId(foodId);
        if (allergies != null) {
            user = userRepo.deleteAllergiesFromUser(user, allergies);
            userDao.save(user);
        }
        foodDao.delete(food);
        allergiesDao.deleteByAllergenId(foodId);

    }


}