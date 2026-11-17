package edu.college.canteen.repository;
import edu.college.canteen.model.MenuItem; import org.springframework.data.jpa.repository.JpaRepository;
public interface MenuItemRepository extends JpaRepository<MenuItem,Long>{}