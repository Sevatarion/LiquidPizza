package com.pizzaCloud.pizzaCloud.objects;

import java.util.List;
import lombok.Data;
@Data
public class Pizza {
    private String name;
    private List<Ingredient> ingredients;
}
