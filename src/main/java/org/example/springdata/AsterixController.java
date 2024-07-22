package org.example.springdata;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/asterix")
@RequiredArgsConstructor
public class AsterixController {

    private final CharacterService characterService;



    @GetMapping("/characters")
    public List<Character> getAllProducts() {
      return characterService.getAllCharacters();
    }

    @GetMapping("/characters/{id}")
    public Character getCharacterById(@PathVariable String id) {
        return  characterService.getCharacterById(id);
    }

    @PostMapping("/characters")
    public Character addCharacter(@RequestBody CharacterDTO character) {
        return characterService.addCharacter(character);
    }

    @DeleteMapping("/characters/{id}")
    public void deleteCharacter(@PathVariable String id) {
        characterService.deleteCharacter(id);
    }

    @PutMapping("/characters/{id}")
    public Character updateCharacter(@PathVariable String id, @RequestBody CharacterDTO character) {
        return characterService.updateCharacter(id, character);
    }



}
