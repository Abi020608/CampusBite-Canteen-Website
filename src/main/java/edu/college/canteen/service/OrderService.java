package edu.college.canteen.service;
import edu.college.canteen.dto.OrderRequest; import edu.college.canteen.model.*; import edu.college.canteen.repository.*;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal; import java.time.LocalDateTime;
@Service public class OrderService{
 private final MenuItemRepository menus; private final CanteenOrderRepository orders;
 public OrderService(MenuItemRepository m,CanteenOrderRepository o){menus=m;orders=o;}
 @Transactional public CanteenOrder place(OrderRequest req){
  CanteenOrder order=new CanteenOrder(req.customerName().trim(),LocalDateTime.now(),BigDecimal.ZERO); BigDecimal total=BigDecimal.ZERO;
  for(OrderRequest.Item r:req.items()){
   MenuItem m=menus.findById(r.menuItemId()).orElseThrow(()->new IllegalArgumentException("Menu item not found"));
   if(!m.isAvailable())throw new IllegalArgumentException(m.getName()+" is unavailable");
   if(m.getStockQty()<r.quantity())throw new IllegalArgumentException("Insufficient stock for "+m.getName());
   BigDecimal line=m.getPrice().multiply(BigDecimal.valueOf(r.quantity())); total=total.add(line); m.setStockQty(m.getStockQty()-r.quantity());
   order.addItem(new OrderLine(m,r.quantity(),m.getPrice(),line));
  }
  CanteenOrder saved=new CanteenOrder(req.customerName().trim(),LocalDateTime.now(),total);
  for(OrderLine l:order.getItems()) saved.addItem(new OrderLine(l.getMenuItem(),l.getQuantity(),l.getUnitPrice(),l.getLineTotal()));
  return orders.save(saved);
 }
}
