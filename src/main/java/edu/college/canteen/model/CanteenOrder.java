package edu.college.canteen.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.*;
@Entity @Table(name="canteen_orders") public class CanteenOrder {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="customer_name",nullable=false,length=100) private String customerName;
 @Column(name="order_time",nullable=false) private LocalDateTime orderTime;
 @Column(name="total_amount",nullable=false,precision=10,scale=2) private BigDecimal totalAmount;
 @Column(nullable=false,length=20) private String status="PLACED";
 @OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true) private List<OrderLine> items=new ArrayList<>();
 public CanteenOrder(){} public CanteenOrder(String n,LocalDateTime t,BigDecimal a){customerName=n;orderTime=t;totalAmount=a;}
 public void addItem(OrderLine l){items.add(l);l.setOrder(this);}
 public Long getId(){return id;} public String getCustomerName(){return customerName;} public LocalDateTime getOrderTime(){return orderTime;}
 public BigDecimal getTotalAmount(){return totalAmount;} public String getStatus(){return status;} public List<OrderLine> getItems(){return items;}
}