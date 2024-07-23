package org.example.springdata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CharacterIntegrationTest {

    @Autowired
     MockMvc mockMvc;

    @Autowired
     CharactersRepo charactersRepo;

    @Test
    @DirtiesContext
    void getAllProducts() throws Exception {
        mockMvc.perform(get("/asterix/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                  """));

    }

    @Test
    @DirtiesContext
    void postCharacter() throws Exception {
        mockMvc.perform(post("/asterix/characters")
                .contentType("application/json")
                .content("""
                        {
                            "name": "Asterix",
                            "age": 32,
                            "profession": "Warrior"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "name": "Asterix",
                            "age": 32,
                            "profession": "Warrior"
                        }
                        """))
                .andExpect(jsonPath("$.id").exists());
    }
    @Test
    @DirtiesContext
    void deleteCharacter() throws Exception {
        mockMvc.perform(delete("/asterix/characters/1"))
                .andExpect(status().isOk());
    }
    @Test
    @DirtiesContext
    void updateCharacter() throws Exception {
        mockMvc.perform(put("/asterix/characters/1")
                .contentType("application/json")
                .content("""
                        {
                            "name": "Asterix",
                            "age": 32,
                            "profession": "Warrior"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "name": "Asterix",
                            "age": 32,
                            "profession": "Warrior"
                        }
                        """));
    }
}