package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(VendorRepository vendorRepository, CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(new Vendor("First Vendor"));
        vendors.add(new Vendor("Second Vendor"));
        vendorRepository.saveAll(vendors);
    }

    private void loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Sukhrobbek", "Odilov"));
        customers.add(new Customer("Iymona", "Odilova"));
        customers.add(new Customer("Shirin", "Odilova"));
        customers.add(new Customer("Shoira", "Odilova"));
        customers.add(new Customer("Shukhrat", "Odilov"));
        customerRepository.saveAll(customers);
    }

    private void loadCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Fruits"));
        categories.add(new Category("Dried"));
        categories.add(new Category("Fresh"));
        categories.add(new Category("Exotic"));
        categories.add(new Category("Nuts"));
        categoryRepository.saveAll(categories);
    }
}
