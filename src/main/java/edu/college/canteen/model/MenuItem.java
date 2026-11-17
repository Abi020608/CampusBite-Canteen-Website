package edu.college.canteen.model;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="menu_items") public class MenuItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String name;
 @Column(nullable=false,length=60) private String category;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal price;
 @Column(name="stock_qty",nullable=false) private Integer stockQty;
 @Column(nullable=false) private boolean available=true;
 public MenuItem(){} public MenuItem(String n,String c,BigDecimal p,Integer s){name=n;category=c;price=p;stockQty=s;}
 public Long getId(){return id;} public String getName(){return name;} public String getCategory(){return category;}
 public BigDecimal getPrice(){return price;} public Integer getStockQty(){return stockQty;} public boolean isAvailable(){return available;}
 public void setStockQty(Integer s){stockQty=s;} public void setAvailable(boolean a){available=a;}
}