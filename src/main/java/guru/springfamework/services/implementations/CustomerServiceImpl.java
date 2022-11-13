package guru.springfamework.services.implementations;

import guru.springfamework.domain.Customer;
import guru.springfamework.payload.CustomerDTO;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.services.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMER_BASE_URL = "/api/v1/customers/";
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(customer.getFirstName(), customer.getLastName(), CUSTOMER_BASE_URL + customer.getId()));
        }
        return customerDTOs;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return new CustomerDTO(customer.getFirstName(), customer.getLastName(), CUSTOMER_BASE_URL + customer.getId());
        } else {
            return new CustomerDTO();
        }
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(new Customer(customerDTO.getFirstName(), customerDTO.getLastName()));
        return new CustomerDTO(savedCustomer.getFirstName(), savedCustomer.getLastName(), CUSTOMER_BASE_URL + savedCustomer.getId());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            Customer savedCustomer = customerRepository.save(customer);
            return new CustomerDTO(savedCustomer.getFirstName(), savedCustomer.getLastName(), CUSTOMER_BASE_URL + savedCustomer.getId());
        }
        return new CustomerDTO();
    }

    @Override
    public CustomerDTO updateCustomerPartial(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if (customerDTO.getFirstName() != null) {
                customer.setFirstName(customerDTO.getFirstName());
            }
            if (customerDTO.getLastName() != null) {
                customer.setLastName(customerDTO.getLastName());
            }
            Customer savedCustomer = customerRepository.save(customer);
            return new CustomerDTO(savedCustomer.getFirstName(), savedCustomer.getFirstName(), CUSTOMER_BASE_URL + savedCustomer.getId());
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
