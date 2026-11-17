package edu.college.canteen.model;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="order_lines") public class OrderLine {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id",nullable=false) private CanteenOrder order;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="menu_item_id",nullable=false) private MenuItem menuItem;
 @Column(nullable=false) private Integer quantity;
 @Column(name="unit_price",nullable=false,precision=10,scale=2) private BigDecimal unitPrice;
 @Column(name="line_total",nullable=false,precision=10,scale=2) private BigDecimal lineTotal;
 public OrderLine(){} public OrderLine(MenuItem m,Integer q,BigDecimal p,BigDecimal t){menuItem=m;quantity=q;unitPrice=p;lineTotal=t;}
 public void setOrder(CanteenOrder o){order=o;} public MenuItem getMenuItem(){return menuItem;} public Integer getQuantity(){return quantity;}
 public BigDecimal getUnitPrice(){return unitPrice;} public BigDecimal getLineTotal(){return lineTotal;}
}