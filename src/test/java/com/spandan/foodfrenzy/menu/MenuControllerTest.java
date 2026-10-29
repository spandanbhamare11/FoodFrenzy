package com.spandan.foodfrenzy.menu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest {

    @Mock
    private MenuItemRepository repository;

    @InjectMocks
    private MenuController controller;

    @Test
    void allReturnsMenuItems() {
        MenuItem item = new MenuItem();
        item.setId(1L);
        item.setName("Paneer Wrap");

        when(repository.findAll()).thenReturn(List.of(item));

        List<MenuItem> result = controller.all();

        assertEquals(1, result.size());
        assertEquals("Paneer Wrap", result.get(0).getName());
        verify(repository).findAll();
    }

    @Test
    void oneReturnsMenuItem() {
        MenuItem item = new MenuItem();
        item.setId(1L);
        item.setName("Paneer Wrap");

        when(repository.findById(1L)).thenReturn(Optional.of(item));

        MenuItem result = controller.one(1L);

        assertEquals("Paneer Wrap", result.getName());
    }

    @Test
    void oneThrowsWhenItemDoesNotExist() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> controller.one(999L));
    }

    @Test
    void createSavesMenuItem() {
        MenuItem item = new MenuItem();
        item.setId(99L);
        item.setName("Paneer Wrap");
        item.setCategory("Main Course");
        item.setPrice(new BigDecimal("180.00"));

        when(repository.save(any(MenuItem.class))).thenAnswer(invocation -> {
            MenuItem saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        MenuItem result = controller.create(item);

        assertEquals(1L, result.getId());
        assertEquals("Paneer Wrap", result.getName());
        verify(repository).save(item);
    }
}