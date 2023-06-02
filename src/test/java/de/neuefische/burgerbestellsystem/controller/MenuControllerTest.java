package de.neuefische.burgerbestellsystem.controller;

import de.neuefische.burgerbestellsystem.model.Menu;
import de.neuefische.burgerbestellsystem.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MenuRepository menuRepository;

    @Test
    @DirtiesContext
    void getAllMenus_whenMenuListIsEmpty_thenExpectStatusOkAndReturnEmptyListAsJson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/menus"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void getAllMenus_whenMenuListIsNotEmpty_thenExpectStatusOkAndReturnMenuListAsJson() throws Exception {

        menuRepository.addMenu(new Menu("1", "Burger", 4.99, "Burger", "Fries", "Coke"));
        menuRepository.addMenu(new Menu("2", "Sandwich", 2.99, "Sandwich", "Salad", "Sprite"));
        menuRepository.addMenu(new Menu("3", "Spaghetti", 9.99, "Spaghetti", "none", "Water"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/menus"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    void getMenuById_whenMenuWithGivenIdExist_thenExpectStatusOkAndReturnMenuAsJson() throws Exception {
        menuRepository.addMenu(new Menu("1", "Burger", 4.99, "Burger", "Fries", "Coke"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/menus/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @DirtiesContext
    void addMenu_whenApiCalled_thenExpectStatusOkAndReturnSavedMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/menus")
                .contentType("application/json")
                .content("""
                        {
                            "id": "1",
                            "name": "Burger",
                            "price": 4.99,
                            "mainDish": "Burger",
                            "sideDish": "Fries",
                            "beverage": "Coke"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                            "id": "1",
                            "name": "Burger",
                            "price": 4.99,
                            "mainDish": "Burger",
                            "sideDish": "Fries",
                            "beverage": "Coke"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void updateMenu_whenMenuWithGivenIdExist_thenExpectStatusOkAndReturnUpdatedMenu() throws Exception {
        menuRepository.addMenu(new Menu("1", "Burger", 4.99, "Burger", "Fries", "Coke"));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/menus/{id}", "1")
                .contentType("application/json")
                .content("""
                        {
                            "id": "1",
                            "name": "Burger",
                            "price": 19.99,
                            "mainDish": "Burger",
                            "sideDish": "Fries",
                            "beverage": "Coke"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "1",
                            "name": "Burger",
                            "price": 19.99,
                            "mainDish": "Burger",
                            "sideDish": "Fries",
                            "beverage": "Coke"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void deleteMenu_whenMenuWithGivenIdExist_thenExpectStatusOk() throws Exception {
        menuRepository.addMenu(new Menu("1", "Burger", 4.99, "Burger", "Fries", "Coke"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/menus/{id}", "1"))
                .andExpect(status().isOk());
    }
}