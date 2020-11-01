package io.phayao.demo.customer;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Flux<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public Mono<Customer> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Mono<Customer> createNewCustomer(Customer customerBody) {
        return customerRepository.save(customerBody);
    }

    public Mono<Customer> editCustomerById(Long customerId, Customer customerBody) {
        return customerRepository.findById(customerId)
                .flatMap(customer -> {
                    customerBody.setId(customer.getId());
                    return customerRepository.save(customerBody);
                });
    }

    public Mono<Void> removeCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .flatMap(customerRepository::delete);
    }

}
