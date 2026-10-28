package com.spandan.foodfrenzy.orders;
import com.spandan.foodfrenzy.menu.MenuItem;
import com.spandan.foodfrenzy.menu.MenuItemRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
@RestController @RequestMapping("/api/orders")
public class OrderController {
 private final CustomerOrderRepository orders; private final MenuItemRepository menu;
 public OrderController(CustomerOrderRepository orders,MenuItemRepository menu){this.orders=orders;this.menu=menu;}
 @GetMapping public List<CustomerOrder> all(){return orders.findAll();}
 @GetMapping("/{id}") public CustomerOrder one(@PathVariable Long id){return orders.findById(id).orElseThrow(()->new NoSuchElementException("Order not found: "+id));}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public CustomerOrder create(@Valid @RequestBody CustomerOrder input){
   if(input.getMenuItem()==null||input.getMenuItem().getId()==null) throw new IllegalArgumentException("menuItem.id is required");
   MenuItem item=menu.findById(input.getMenuItem().getId()).orElseThrow(()->new NoSuchElementException("Menu item not found: "+input.getMenuItem().getId()));
   if(!item.isAvailable()) throw new IllegalArgumentException("Menu item is not available");
   if(input.getQuantity()<1) throw new IllegalArgumentException("Quantity must be at least 1");
   input.setId(null); input.setMenuItem(item); input.setTotalPrice(item.getPrice().multiply(java.math.BigDecimal.valueOf(input.getQuantity()))); return orders.save(input);
 }
}
