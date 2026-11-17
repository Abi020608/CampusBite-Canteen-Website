package edu.college.canteen.repository;
import edu.college.canteen.model.OrderLine; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface OrderLineRepository extends JpaRepository<OrderLine,Long>{
 @Query("select m.name as itemName, sum(l.quantity) as units, sum(l.lineTotal) as revenue from OrderLine l join l.menuItem m group by m.id,m.name order by sum(l.quantity) desc")
 List<Map<String,Object>> bestSellingItems();
}