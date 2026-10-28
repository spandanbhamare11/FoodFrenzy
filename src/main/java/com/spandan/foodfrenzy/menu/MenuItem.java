package com.spandan.foodfrenzy.menu;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
@Entity @Table(name="menu_items")
public class MenuItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String name;
 @NotBlank private String category;
 @NotNull @DecimalMin(value="0.0",inclusive=false) private BigDecimal price;
 private boolean available=true;
 public MenuItem(){}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getName(){return name;} public void setName(String v){name=v;}
 public String getCategory(){return category;} public void setCategory(String v){category=v;}
 public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;}
 public boolean isAvailable(){return available;} public void setAvailable(boolean v){available=v;}
}
