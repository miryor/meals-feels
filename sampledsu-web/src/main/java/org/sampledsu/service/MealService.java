package org.sampledsu.service;

import java.util.List;
import java.util.UUID;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import org.sampledsu.common.models.Meal;

/**
 * initial attempt at MealService, ignore for now
 * @author royrim
 *
 */
@Repository
public class MealService {
    
   @Autowired
   private MongoTemplate mongoTemplate;
    
   public static final String COLLECTION_NAME = "meal";
    
   public void addMeal(Meal meal) {
       if (!mongoTemplate.collectionExists(Meal.class)) {
           mongoTemplate.createCollection(Meal.class);
       }       
       meal.setId(UUID.randomUUID().toString());
       mongoTemplate.insert(meal, COLLECTION_NAME);
   }
    
   public List<Meal> listMeal() {
       return mongoTemplate.findAll(Meal.class, COLLECTION_NAME);
   }
    
   public void deleteMeal(Meal meal) {
       mongoTemplate.remove(meal, COLLECTION_NAME);
   }
    
   public void updateMeal(Meal meal) {
       mongoTemplate.save(meal, COLLECTION_NAME);      
   }

}
