package com.example.coffee_menu_service.service;

import com.example.coffee_menu_service.model.Coffee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {

    private final List<Coffee> coffees = new ArrayList<>();
    private int nextId = 1;

    public CoffeeService() {
        coffees.add(new Coffee(nextId++, "Espresso", 45.0));
        coffees.add(new Coffee(nextId++, "Latte", 55.0));
    }

    public List<Coffee> getAll() {
        return coffees;
    }

    public Optional<Coffee> getById(int id) {
        return coffees.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public Coffee add(Coffee newCoffee) {
        newCoffee.setId(nextId++);
        coffees.add(newCoffee);
        return newCoffee;
    }

    public Optional<Coffee> update(int id, Coffee updated) {
        return getById(id).map(c -> {
            c.setName(updated.getName());
            c.setPrice(updated.getPrice());
            return c;
        });
    }

    public boolean delete(int id) {
        return coffees.removeIf(c -> c.getId() == id);
    }
}