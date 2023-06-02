package de.neuefische.burgerbestellsystem.service;

import de.neuefische.burgerbestellsystem.exception.MenuNotFoundException;
import de.neuefische.burgerbestellsystem.model.Menu;
import de.neuefische.burgerbestellsystem.repository.MenuRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuServiceTest {

    private final MenuRepository menuRepository = mock(MenuRepository.class);
    private final MenuService menuService = new MenuService(menuRepository);

    @Test
    void getAllMenus_whenMenuListIsEmpty_thenExpectEmptyList() {
        //GIVEN
        List<Menu> expected = List.of();
        //WHEN
        when(menuRepository.getAllMenus()).thenReturn(List.of());
        List<Menu> actual = menuService.getAllMenus();
        //THEN
        assertEquals(expected, actual);
        verify(menuRepository).getAllMenus();
    }

    @Test
    void getAllMenus_whenMenuListIsNotEmpty_thenExpectListOfMenus() {
        //GIVEN
        List<Menu> expected = List.of(
                new Menu("1", "Burger", 4.99, "Burger", "Fries", "Coke"),
                new Menu("2", "Cheeseburger", 5.99, "Cheeseburger", "Fries", "Coke"),
                new Menu("3", "Veggie Burger", 4.99, "Veggie Burger", "Fries", "Coke")
        );
        //WHEN
        when(menuRepository.getAllMenus()).thenReturn(expected);
        List<Menu> actual = menuService.getAllMenus();
        //THEN
        assertEquals(expected, actual);
        verify(menuRepository).getAllMenus();
    }

    @Test
    void getMenuById_whenMenuWithGivenIdExist_thenExpectMenu() {
        //GIVEN
        Menu expected = new Menu("1", "Burger", 4.99, "Burger", "Fries", "Coke");
        //WHEN
        when(menuRepository.getMenuById("1")).thenReturn(expected);
        Menu actual = menuService.getMenuById("1");
        //THEN
        assertEquals(expected, actual);
        verify(menuRepository).getMenuById("1");
    }

    @Test
    void addMenu_whenCalled_thenSaveAndReturnMenu() {
        //GIVEN
        Menu expected = new Menu("1", "Burger", 4.99, "Burger", "Fries", "Coke");
        //WHEN
        when(menuRepository.addMenu(expected)).thenReturn(expected);
        Menu actual = menuService.addMenu(expected);
        //THEN
        assertEquals(expected, actual);
        verify(menuRepository).addMenu(expected);
    }

    @Test
    void updateMenu_whenMenuWithGivenIdExist_thenUpdateAndReturnMenu() {
        //GIVEN
        String id = "1";
        Menu existingMenu = new Menu("1", "Burger", 4.99, "Burger", "Fries", "Coke");
        Menu updatedMenu = new Menu("1", "Hamburger", 5.99, "BBQ Burger", "Fries", "Coke");
        //WHEN
        when(menuRepository.getMenuById(id)).thenReturn(existingMenu);
        when(menuRepository.updateMenu(id, updatedMenu)).thenReturn(updatedMenu);
        Menu actual = menuService.updateMenu("1", updatedMenu);
        //THEN
        assertEquals(updatedMenu, actual);
        verify(menuRepository).updateMenu("1", updatedMenu);
    }

    @Test
    void deleteMenu_whenMenuWithGivenIdExist_thenExpectNothing() {
        //GIVEN
        String id = "1";
        //WHEN
        menuService.deleteMenu(id);
        //THEN
        verify(menuRepository).deleteMenu(id);
    }
}