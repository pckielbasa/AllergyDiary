package com.example.praca_inz_api.dao;

import com.example.praca_inz_api.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealDao extends MongoRepository<Meal,String> {
}
