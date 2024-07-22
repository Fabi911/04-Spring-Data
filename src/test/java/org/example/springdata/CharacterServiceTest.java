package org.example.springdata;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CharacterServiceTest {

    CharactersRepo charactersRepo=mock(CharactersRepo.class);
    IdService idService=mock(IdService.class);
    CharacterService characterService=new CharacterService(charactersRepo,idService);

    @Test
    void getAllCharacters_EmptyList() {
        List<Character> characters=characterService.getAllCharacters();

        List<Character> expected= new ArrayList<>();
        assertEquals(expected,characters);
    }

    @Test
    void getAllCharacters() {
        //GIVEN
        Character character=new Character("123", "Asterix", 35, "warrior");
        List<Character> characters=List.of(character);
        when(charactersRepo.findAll()).thenReturn(characters);
        //WHEN
        List<Character> expected=characterService.getAllCharacters();
        //THEN
        List<Character> actual=new ArrayList<>();
        actual.add(new Character("123", "Asterix", 35, "warrior"));

        verify(charactersRepo).findAll();
        assertEquals(expected,actual);
    }

    @Test
    void addCharacter() {
    }

    @Test
    public void testGetCharacterById_Success() {
        //GIVEN
        Character character=new Character("123", "Test Character", 35, "warrior");
        when(charactersRepo.findById("123")).thenReturn(Optional.of(character));
        //WHEN
        Character result = characterService.getCharacterById("123");
        //THEN
        assertEquals("123", result.id());
        assertEquals("Test Character", result.name());
        verify(charactersRepo).findById("123");
    }

    @Test
    public void testGetCharacterById_NotFound() {
        //GIVEN
        when(charactersRepo.findById("123")).thenReturn(Optional.empty());
        //WHEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            characterService.getCharacterById("123");
        });
        //THEN
        assertEquals("Character not found", exception.getMessage());
        verify(charactersRepo).findById("123");
    }

    @Test
    void testDeleteCharacter() {
        // Arrange
        String characterId = "123";

        // Act
        characterService.deleteCharacter(characterId);

        // Assert
        verify(charactersRepo).deleteById(characterId);
    }

    @Test
    public void testUpdateCharacter() {
        //GIVEN
        CharacterDTO characterDTO= new CharacterDTO("Updated Character", 35, "Updated Profession");
        Character updatedCharacter=new Character("123", "Asterix", 35, "warrior");
        doNothing().when(charactersRepo).deleteById("123");
        when(charactersRepo.save(any(Character.class))).thenReturn(updatedCharacter);

        //WHEN
        Character result = characterService.updateCharacter("123", characterDTO);

        //THEN
        assertNotNull(result);
        assertEquals("123", result.id());
        assertEquals("Updated Character", result.name());
        assertEquals(35, result.age());
        assertEquals("Updated Profession", result.profession());
        verify(charactersRepo).deleteById("123");
        verify(charactersRepo).save(any(Character.class));
    }

    @Test
    public void testAddCharacterWithGenerateId() {
        //GIVEN
        CharacterDTO characterDTO= new CharacterDTO("New Character", 35, "New Profession");
        String generatedId= "12345";
        Character character=new Character(generatedId, "New Character", 35, "New Profession");

        when(idService.generateId()).thenReturn(generatedId);
        when(charactersRepo.save(any(Character.class))).thenReturn(character);
        //WHEN
        Character result = characterService.addCharacter(characterDTO);
        //THEN
        assertEquals(generatedId, result.id());
        assertEquals("New Character", result.name());
        assertEquals(35, result.age());
        assertEquals("New Profession", result.profession());

        verify(idService).generateId();
        verify(charactersRepo).save(any(Character.class));
    }
}