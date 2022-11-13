package guru.springfamework.payload;

import guru.springfamework.domain.Category;
import lombok.*;

import java.util.List;

/**
 * Created by jt on 9/26/17.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListDTO {

    List<Category> categories;

}
