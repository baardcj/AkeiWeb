package org.example.furniture.aeki.services;

import lombok.RequiredArgsConstructor;
import org.example.furniture.aeki.data.entities.Customer;
import org.example.furniture.aeki.data.entities.CustomerDiscountMap;
import org.example.furniture.aeki.data.entities.Discount;
import org.example.furniture.aeki.data.repositories.CustomerDiscountMapRepository;
import org.example.furniture.aeki.data.repositories.DiscountRepository;
import org.example.furniture.aeki.model.Discountable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class DiscountService {

    private final CustomerDiscountMapRepository customerDiscountMapRepository;
    private Map<Long, Discount> discountsMap = new HashMap<>(5);

    public int calculateDiscount(Customer customer, Discountable discountable) {
        return customer.isMember() ?
                findCustomerDiscounts(customer.getId()).stream()
                    .map(discount -> discount.getDiscount(discountable))
                    .reduce(0, Integer::sum)
                : 0;
    }

    public List<Discount> findCustomerDiscounts(Long customerId) {
        return customerDiscountMapRepository.findByCustomerId(customerId).stream().map(CustomerDiscountMap::getDiscount).toList();
    }
}
