package org.example.springdata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
@RequestMapping("/asterix")
public class AsterixController {
    private final CharactersRepo CharactersRepo;
    public AsterixController(CharactersRepo CharactersRepo) {
        this.CharactersRepo = CharactersRepo;
    }


    @GetMapping("/characters")
    public List<Character> getAllProducts() {
        List<Character> allCharacters = CharactersRepo.findAll();
        return allCharacters;
    }

    @GetMapping("/characters/{id}")
    public Character getCharacterByName(@PathVariable String name) {
        Character character = CharactersRepo.findById(name).orElseThrow();
        return character;
    }
}
