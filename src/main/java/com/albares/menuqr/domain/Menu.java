package com.albares.menuqr.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {

    private List<Dish> dishes = new ArrayList();

    public Menu() {
    }

    public void addDish(Dish disch) {
        this.dishes.add(disch);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    
}
