package com.spandan.foodfrenzy.orders;
import com.spandan.foodfrenzy.menu.MenuItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name="customer_orders")
public class CustomerOrder {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotNull @ManyToOne(optional=false) @JoinColumn(name="menu_item_id") private MenuItem menuItem;
 @Min(1) private int quantity;
 private BigDecimal totalPrice;
 private String customerName;
 private LocalDateTime createdAt;
 @PrePersist void beforeInsert(){createdAt=LocalDateTime.now();}
 public CustomerOrder(){}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public MenuItem getMenuItem(){return menuItem;} public void setMenuItem(MenuItem v){menuItem=v;}
 public int getQuantity(){return quantity;} public void setQuantity(int v){quantity=v;}
 public BigDecimal getTotalPrice(){return totalPrice;} public void setTotalPrice(BigDecimal v){totalPrice=v;}
 public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
