package org.example.furniture.aeki.services;

import lombok.AllArgsConstructor;
import org.example.furniture.aeki.data.entities.Customer;
import org.example.furniture.aeki.data.entities.CustomerDiscount;
import org.example.furniture.aeki.data.entities.Discount;
import org.example.furniture.aeki.data.repositories.CustomerDiscountRepository;
import org.example.furniture.aeki.data.repositories.DiscountRepository;
import org.example.furniture.aeki.model.Discountable;
import org.example.furniture.aeki.model.OrderItemProduct;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@Component
public class DiscountService {

    private final CustomerDiscountRepository customerDiscountRepository;
    private final DiscountRepository discountRepository;
    private HashSet<Discount> discounts;


    public int calculateDiscount(Customer customer, Discountable discountable) {
        return customer.isMember() ?
                findCustomerDiscounts(customer.getId()).stream()
                    .map(a -> a.getDiscount(discountable))
                    .reduce(0, Integer::sum)
                : 0;
    }

    public List<Discount> findCustomerDiscounts(Long customerId) {
        return customerDiscountRepository.findByCustomerId(customerId).stream().map(CustomerDiscount::getDiscount).toList();
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.discounts = UpdateDiscountData();
    }

    @Scheduled(cron = "*/15 * * * *")
    public void UpdateDiscountTask() {
        System.out.println(" RUNNING TAsk ");
        this.discounts = UpdateDiscountData();
    }

    public HashSet<Discount> UpdateDiscountData() {
        List<Discount> discount = discountRepository.findAll();
        return discounts == null ? new HashSet<>() : new HashSet<>(discount);
    }
}
