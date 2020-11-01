package io.phayao.demo.customer;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Flux<Customer> getCustomers() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/{customerId}")
    public Mono<Customer> getCustomerById(@PathVariable Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    @PostMapping
    public Mono<Customer> postCustomer(@RequestBody Customer customerBody) {
        return customerService.createNewCustomer(customerBody);
    }

    @PutMapping("/{customerId}")
    public Mono<Customer> putCustomerById(@PathVariable Long customerId, @RequestBody Customer customerBody) {
        return customerService.editCustomerById(customerId, customerBody);
    }

    @DeleteMapping("/{customerId}")
    public Mono<Void> deleteCustomerById(@PathVariable Long customerId) {
        return customerService.removeCustomerById(customerId);
    }

}
