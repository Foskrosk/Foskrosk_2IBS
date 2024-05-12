package ru.appline.pets.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.appline.pets.logic.Pet;
import ru.appline.pets.logic.PetModel;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("pets")
public class PetsController {

    private final PetModel petModel;
    private boolean isFirst = true;

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getModel();
    }

    @PostMapping(value = "/createPet", consumes = "application/json")
    public String createPet(@RequestBody Pet pet) {
        petModel.add(pet);
        if(isFirst){
            isFirst= false;
            return "Поздравляем, вы создали своего первого питомца";
        }
        return "Поздравляем, вы создали своего питомца";
    }

    @PutMapping(value = "/editPet/{id}", consumes = "application/json")
    public String editPet(@PathVariable int id, @RequestBody Pet pet){

        if(petModel.getModel().containsKey(id)){
            petModel.getModel().put(id,pet);
            return "Питомец изменен";
        }
        return "Питомец не найден";
    }

    @DeleteMapping(value = "/deletePet/{id}")
    public String deletePet(@PathVariable int id){

        if(petModel.getModel().containsKey(id)){
            petModel.getModel().remove(id);
            return "Питомец удален";
        }
        return "Питомец не найден";
    }
}
