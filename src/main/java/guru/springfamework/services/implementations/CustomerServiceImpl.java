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
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(customer.getFirstName(), customer.getLastName(), "/api/v1/customers/" + customer.getId()));
        }
        return customerDTOs;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return new CustomerDTO(customer.getFirstName(), customer.getLastName(), "/api/v1/customers/" + customer.getId());
        } else {
            return new CustomerDTO();
        }
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(new Customer(customerDTO.getFirstName(), customerDTO.getLastName()));
        return new CustomerDTO(savedCustomer.getFirstName(),savedCustomer.getLastName(),"/api/v1/customers/" + savedCustomer.getId());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            Customer savedCustomer = customerRepository.save(customer);
            return new CustomerDTO(savedCustomer.getFirstName(),savedCustomer.getLastName(),"/api/v1/customers/" + savedCustomer.getId());
        }
        return new CustomerDTO();
    }
}
