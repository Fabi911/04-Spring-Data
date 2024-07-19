package org.example.springdata;

import org.springframework.web.bind.annotation.*;


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
    public Character getCharacterById(@PathVariable String id) {
        Character character = CharactersRepo.findById(id).orElseThrow(() -> new RuntimeException("Character not found"));
        return character;
    }

    @PostMapping("/characters")
    public Character addCharacter(@RequestBody Character character) {
        CharactersRepo.save(character);
        return character;
    }

    @DeleteMapping("/characters/{id}")
    public void deleteCharacter(@PathVariable String id) {
        CharactersRepo.deleteById(id);
    }

    @PutMapping("/characters/{id}")
    public Character updateCharacter(@PathVariable String id, @RequestBody Character character) {
        CharactersRepo.deleteById(id);
        CharactersRepo.save(character);
        return character;
    }



}
