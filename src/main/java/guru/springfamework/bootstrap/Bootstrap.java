package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args)  {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Fruits"));
        categories.add(new Category("Dried"));
        categories.add(new Category("Fresh"));
        categories.add(new Category("Exotic"));
        categories.add(new Category("Nuts"));
        categoryRepository.saveAll(categories);
    }
}
