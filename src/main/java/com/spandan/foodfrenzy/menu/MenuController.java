package com.spandan.foodfrenzy.menu;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
@RestController @RequestMapping("/api/menu")
public class MenuController {
 private final MenuItemRepository repository;
 public MenuController(MenuItemRepository repository){this.repository=repository;}
 @GetMapping public List<MenuItem> all(){return repository.findAll();}
 @GetMapping("/{id}") public MenuItem one(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new NoSuchElementException("Menu item not found: "+id));}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public MenuItem create(@Valid @RequestBody MenuItem item){item.setId(null);return repository.save(item);}
 @PutMapping("/{id}") public MenuItem update(@PathVariable Long id,@Valid @RequestBody MenuItem input){MenuItem item=one(id);item.setName(input.getName());item.setCategory(input.getCategory());item.setPrice(input.getPrice());item.setAvailable(input.isAvailable());return repository.save(item);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){repository.delete(one(id));}
}
