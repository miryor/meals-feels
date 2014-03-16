package org.sampledsu.common.dao.mongo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.sampledsu.common.models.*;
import org.sampledsu.common.dao.mongo.MealRepository;

/**
 * Our mvc Service for handling Meal related actions.
 * Expectation for a MealRepository to a exist.
 * @author royrim
 *
 */
@Service
public class MealService {

	@Autowired
	private MealRepository mealRepository;
	
	public void insertMeal( Meal meal ) {
		meal.setId(UUID.randomUUID().toString());
		mealRepository.save( meal );
	}
	
	public void deleteMeal( Meal meal ) {
		mealRepository.delete( meal );
	}
	
	public void updateMeal( Meal meal ) {
		mealRepository.save( meal );
	}
	
	public List<Meal> userMealList( String user ) {
		return mealRepository.findByUser( user );
	}
	
}
