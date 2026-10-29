package com.spandan.foodfrenzy.orders;

import com.spandan.foodfrenzy.menu.MenuItem;
import com.spandan.foodfrenzy.menu.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private CustomerOrderRepository orders;

    @Mock
    private MenuItemRepository menu;

    @InjectMocks
    private OrderController controller;

    @Test
    void allReturnsOrders() {
        CustomerOrder order = new CustomerOrder();
        order.setId(1L);

        when(orders.findAll()).thenReturn(List.of(order));

        List<CustomerOrder> result = controller.all();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void createCalculatesTotalPrice() {
        MenuItem item = new MenuItem();
        item.setId(1L);
        item.setName("Paneer Wrap");
        item.setPrice(new BigDecimal("180.00"));
        item.setAvailable(true);

        CustomerOrder input = new CustomerOrder();
        input.setCustomerName("Aarav Patil");
        input.setMenuItem(item);
        input.setQuantity(2);

        when(menu.findById(1L)).thenReturn(Optional.of(item));
        when(orders.save(any(CustomerOrder.class))).thenAnswer(invocation -> {
            CustomerOrder saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        CustomerOrder result = controller.create(input);

        assertEquals(1L, result.getId());
        assertEquals(new BigDecimal("360.00"), result.getTotalPrice());
        assertEquals(2, result.getQuantity());
        verify(orders).save(input);
    }

    @Test
    void createRejectsZeroQuantity() {
        MenuItem item = new MenuItem();
        item.setId(1L);
        item.setPrice(new BigDecimal("180.00"));
        item.setAvailable(true);

        CustomerOrder input = new CustomerOrder();
        input.setMenuItem(item);
        input.setQuantity(0);

        when(menu.findById(1L)).thenReturn(Optional.of(item));

        assertThrows(IllegalArgumentException.class,
                () -> controller.create(input));

        verify(orders, never()).save(any(CustomerOrder.class));
    }

    @Test
    void createRejectsUnavailableMenuItem() {
        MenuItem item = new MenuItem();
        item.setId(1L);
        item.setPrice(new BigDecimal("180.00"));
        item.setAvailable(false);

        CustomerOrder input = new CustomerOrder();
        input.setMenuItem(item);
        input.setQuantity(1);

        when(menu.findById(1L)).thenReturn(Optional.of(item));

        assertThrows(IllegalArgumentException.class,
                () -> controller.create(input));

        verify(orders, never()).save(any(CustomerOrder.class));
    }
}