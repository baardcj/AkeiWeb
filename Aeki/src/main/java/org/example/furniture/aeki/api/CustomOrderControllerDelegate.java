package org.example.furniture.aeki.api;

import lombok.AllArgsConstructor;
import org.example.furniture.aeki.api.exceptions.CustomerOrderBedRequestException;
import org.example.furniture.aeki.api.exceptions.CustomerOrderNotFoundException;
import org.example.furniture.aeki.data.entities.Customer;
import org.example.furniture.aeki.data.entities.CustomerOrder;
import org.example.furniture.aeki.data.entities.OrderItem;
import org.example.furniture.aeki.data.repositories.CustomerRepository;
import org.example.furniture.aeki.data.repositories.FoodRepository;
import org.example.furniture.aeki.data.repositories.FurnitureRepository;
import org.example.furniture.aeki.data.repositories.OrderItemRepository;
import org.example.furniture.aeki.model.OrderForm;
import org.example.furniture.aeki.model.OrderItemProduct;
import org.example.furniture.aeki.services.DiscountService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomOrderControllerDelegate {

    private final CustomerRepository customerRepository;
    private final FurnitureRepository furnitureRepository;
    private final FoodRepository foodRepository;
    private final DiscountService discountService;
    private final OrderItemRepository orderItemRepository;

    public CustomerOrder createOrder(Optional<Long> customerId, Optional<Boolean> member) {
        return CustomerOrder.builder().customer(getCustomer(customerId, member)).build();
    }

    private Customer getCustomer(Optional<Long> customerId, Optional<Boolean> member) {
        if (customerId.isPresent()) {
            Customer customer = customerRepository.findById(customerId.get()).
                    orElseThrow(() -> new CustomerOrderNotFoundException(customerId.get()));
            member.ifPresent(customer::setMember);
            return customer;
        }
        return customerRepository.saveAndFlush(Customer.builder().build());
    }

    public OrderItem createOrderItem(OrderForm orderForm, Customer customer) {
        if (!orderForm.isWellFormed()) {
            throw new CustomerOrderBedRequestException("order item is not well formed");
        }
        OrderItemProduct orderItemProduct = findOrderItemProduct(orderForm);
        OrderItem orderItem = buildOrderItem(orderItemProduct);
        orderItem.setDiscount(discountService.calculateDiscount(customer, orderItemProduct.getDiscountable()));
        return orderItem;
    }

    private OrderItemProduct findOrderItemProduct(OrderForm orderform) {
        if (orderform.isProductOrder()) {
            return furnitureRepository.findByItemNumber(orderform.getItemNumber()).
                orElseThrow(() -> new CustomerOrderBedRequestException("unknown item"));
        } else {
            return foodRepository.findByTypeAndFlavour(orderform.getType(), orderform.getFoodFlavour()).
                orElseThrow(() -> new CustomerOrderBedRequestException("unknown item"));
        }
    }
    private OrderItem buildOrderItem(OrderItemProduct orderItemProduct) {

        if (!orderItemProduct.canBeOrdered()) {
            throw new CustomerOrderBedRequestException("item is not available");
        }
        orderItemProduct.markAsOrdered();
        OrderItem orderItem = OrderItem.builder().build();
        orderItem.setOrderItemProduct(orderItemProduct);
        return orderItem;
    }

    public void saverOrderItem(OrderItem orderItem, CustomerOrder customerOrder) {
        orderItem.setCustomerOrder(customerOrder);
        customerOrder.addOrderItem(orderItem);
        orderItemRepository.save(orderItem);
    }
}
