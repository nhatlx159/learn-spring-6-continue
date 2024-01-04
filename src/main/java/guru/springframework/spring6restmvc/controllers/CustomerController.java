package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomer(){
        return customerService.listCustomer();
    }

    @GetMapping("{customerId}")
    public Customer getCustomerById(@PathVariable("customerId")UUID customerId){
        return customerService.getCustomerId(customerId);
    }

    @PostMapping
    public ResponseEntity postCustomer(@RequestBody Customer customer){
        Customer savedNewCustomer = customerService.savedNewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/" + customer.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    @PutMapping("{customerId}")
    public ResponseEntity putCustomer(@PathVariable("customerId")UUID customerId, @RequestBody Customer customer){
        customerService.updateCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") UUID customerId){
        customerService.deleteCustomer(customerId);
    }
}
