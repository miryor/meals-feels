package org.sampledsu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import org.sampledsu.common.models.Meal;
import org.sampledsu.common.dao.mongo.MealService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

/**
 * Our spring mvc controller for Meals
 * Expects a MealService to exist.
 * @author royrim
 *
 */
@Controller
public class MealController {
	private static Logger logger = LoggerFactory.getLogger( MealController.class );

	@Autowired
    private MealService mealService;
     
    @RequestMapping(value = "/meal", method = RequestMethod.GET)  
    public String getMealList(ModelMap model, Principal principal ) {  
        model.addAttribute("mealList", mealService.userMealList( principal.getName() ) );  
        return "meal_list";  
    }  
     
    @RequestMapping(value = "/meal/save", method = RequestMethod.POST)  
    public View createMeal(@ModelAttribute Meal meal, ModelMap model, Principal principal) {
        if(StringUtils.hasText(meal.getId())) {
        	if ( logger.isDebugEnabled() ) logger.debug( "has id already, doing update" );
            mealService.updateMeal(meal);
        } else {
        	if ( logger.isDebugEnabled() ) logger.debug( "no id inserting" );
        	//meal.setUser( principal.getName() );
            mealService.insertMeal(meal);
        }
        return new RedirectView("/meal");  
    }
         
    @RequestMapping(value = "/meal/delete", method = RequestMethod.GET)  
    public View deleteMeal(@ModelAttribute Meal meal, ModelMap model) {  
        mealService.deleteMeal(meal);  
        return new RedirectView("/meal");  
    }    
}
