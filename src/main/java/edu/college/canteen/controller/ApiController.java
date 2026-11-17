package edu.college.canteen.controller;
import edu.college.canteen.dto.OrderRequest; import edu.college.canteen.model.*; import edu.college.canteen.repository.*; import edu.college.canteen.service.OrderService;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import java.math.*; import java.time.*; import java.util.*;
@RestController @RequestMapping("/api") public class ApiController{
 private final MenuItemRepository menus; private final CanteenOrderRepository orders; private final OrderLineRepository lines; private final OrderService service;
 public ApiController(MenuItemRepository m,CanteenOrderRepository o,OrderLineRepository l,OrderService s){menus=m;orders=o;lines=l;service=s;}
 @GetMapping("/menu") public List<MenuItem> menu(){return menus.findAll().stream().filter(MenuItem::isAvailable).toList();}
 @PostMapping("/orders") public ResponseEntity<?> place(@Valid @RequestBody OrderRequest r){
  try{CanteenOrder o=service.place(r);return ResponseEntity.status(201).body(Map.of("message","Order placed successfully","orderId",o.getId(),"customerName",o.getCustomerName(),"totalAmount",o.getTotalAmount()));}
  catch(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));}}
 @GetMapping("/orders") public List<Map<String,Object>> recent(){return orders.findTop8ByOrderByOrderTimeDesc().stream().map(o->{Map<String,Object> m=new LinkedHashMap<>();m.put("id",o.getId());m.put("customerName",o.getCustomerName());m.put("orderTime",o.getOrderTime());m.put("totalAmount",o.getTotalAmount());m.put("status",o.getStatus());return m;}).toList();}
 @GetMapping("/dashboard") public Map<String,Object> dashboard(){LocalDate d=LocalDate.now();LocalDateTime s=d.atStartOfDay(),e=d.plusDays(1).atStartOfDay().minusNanos(1);Map<String,Object> m=new LinkedHashMap<>();m.put("todayOrders",orders.countByOrderTimeBetween(s,e));m.put("todayRevenue",orders.revenueBetween(s,e));m.put("menuCount",menus.count());m.put("lowStockCount",menus.findAll().stream().filter(x->x.getStockQty()<=5).count());m.put("bestSellers",lines.bestSellingItems());return m;}
 @PostMapping("/menu") public ResponseEntity<?> add(@RequestBody Map<String,String> b){try{return ResponseEntity.status(201).body(menus.save(new MenuItem(b.get("name"),b.get("category"),new BigDecimal(b.get("price")),Integer.parseInt(b.get("stockQty")))));}catch(Exception e){return ResponseEntity.badRequest().body(Map.of("message","Enter valid menu details"));}}
 @PutMapping("/menu/{id}/stock") public ResponseEntity<?> stock(@PathVariable Long id,@RequestBody Map<String,Integer> b){return menus.findById(id).map(x->{int q=b.getOrDefault("stockQty",-1);if(q<0)return ResponseEntity.badRequest().body(Map.of("message","Stock cannot be negative"));x.setStockQty(q);return ResponseEntity.ok(menus.save(x));}).orElse(ResponseEntity.notFound().build());}
}