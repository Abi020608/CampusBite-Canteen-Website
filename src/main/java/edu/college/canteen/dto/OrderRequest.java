package edu.college.canteen.dto;
import jakarta.validation.constraints.*; import jakarta.validation.Valid; import java.util.List;
public record OrderRequest(@NotBlank @Size(max=100) String customerName,@NotEmpty List<@Valid Item> items){
 public record Item(@NotNull Long menuItemId,@NotNull @Min(1) @Max(50) Integer quantity){}
}