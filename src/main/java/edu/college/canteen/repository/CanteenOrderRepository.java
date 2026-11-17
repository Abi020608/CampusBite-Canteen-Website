package edu.college.canteen.repository;
import edu.college.canteen.model.CanteenOrder; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.List;
public interface CanteenOrderRepository extends JpaRepository<CanteenOrder,Long>{
 long countByOrderTimeBetween(LocalDateTime s,LocalDateTime e);
 @Query("select coalesce(sum(o.totalAmount),0) from CanteenOrder o where o.orderTime between :s and :e")
 BigDecimal revenueBetween(@Param("s") LocalDateTime s,@Param("e") LocalDateTime e);
 List<CanteenOrder> findTop8ByOrderByOrderTimeDesc();
}