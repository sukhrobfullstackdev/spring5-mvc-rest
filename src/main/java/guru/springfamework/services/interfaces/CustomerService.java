package guru.springfamework.services.interfaces;

import guru.springfamework.payload.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);

    CustomerDTO updateCustomerPartial(Long id, CustomerDTO customerDTO);
}
