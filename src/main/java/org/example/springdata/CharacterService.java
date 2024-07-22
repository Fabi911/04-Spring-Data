package org.example.springdata;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharactersRepo charactersRepo;
    private final IdService idService;


    public List<Character> getAllCharacters() {
        List<Character> allCharacters =  charactersRepo.findAll();
        return allCharacters;
    }

    public Character addCharacter(CharacterDTO character) {
        Character newCharacterToAdd = new Character(
                idService.generateId(),
                character.name(),
                character.age(),
                character.profession()
        );
        return charactersRepo.save(newCharacterToAdd);
    }

    public Character getCharacterById(String id) {
        Character character = charactersRepo.findById(id).orElseThrow(() -> new RuntimeException("Character not found"));
        return character;
    }

    public void deleteCharacter(String id) {
        charactersRepo.deleteById(id);
    }

    public Character updateCharacter( String id,  CharacterDTO character) {
        charactersRepo.deleteById(id);
        Character updatedCharacter = new Character(
                id,
                character.name(),
                character.age(),
                character.profession()
        );
        charactersRepo.save(updatedCharacter);
        return updatedCharacter;

    }
}
