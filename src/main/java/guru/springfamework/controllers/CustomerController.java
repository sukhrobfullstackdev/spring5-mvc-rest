package guru.springfamework.controllers;

import guru.springfamework.payload.CustomerDTO;
import guru.springfamework.payload.CustomerListDTO;
import guru.springfamework.services.interfaces.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getCustomers() {
        return ResponseEntity.ok(new CustomerListDTO(customerService.getAllCustomers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.updateCustomer(id, customerDTO));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerPartial(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.updateCustomerPartial(id, customerDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
