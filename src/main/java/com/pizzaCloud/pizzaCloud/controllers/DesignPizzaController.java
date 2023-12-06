package com.pizzaCloud.pizzaCloud.controllers;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.pizzaCloud.pizzaCloud.objects.Ingredient;
import com.pizzaCloud.pizzaCloud.objects.Ingredient.Type;
import com.pizzaCloud.pizzaCloud.objects.Pizza;
import com.pizzaCloud.pizzaCloud.objects.PizzaOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {


    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("SLIM", "Slim", Type.DOUGH),
                new Ingredient("FATT", "Fat", Type.DOUGH),
                new Ingredient("RABB", "Rabbit", Type.MEAT),
                new Ingredient("COWW", "Cow", Type.MEAT),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @PostMapping
    public String processTaco(Pizza pizza,
                              @ModelAttribute PizzaOrder pizzaOrder) {
        pizzaOrder.addPizza(pizza);
        log.info("Processing taco: {}", pizza);
        return "redirect:/orders/current";
    }

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
    }
    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }
    @GetMapping
    public String showDesignForm() {
        return "design";
    }
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
