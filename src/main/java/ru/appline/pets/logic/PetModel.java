package ru.appline.pets.logic;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class PetModel implements Serializable {

    private final Map<Integer, Pet> model = new HashMap<>();

    public void add(Pet pet) {
        if (model.isEmpty())
            model.put(1, pet);
        else
            model.put(Collections.max(model.keySet()) + 1, pet);
    }

    public Pet getFromList(int id) {
        return model.get(id);
    }

}
