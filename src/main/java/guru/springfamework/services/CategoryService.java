package guru.springfamework.services;


import guru.springfamework.domain.Category;

import java.util.List;

/**
 * Created by jt on 9/26/17.
 */
public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryByName(String name);
}
