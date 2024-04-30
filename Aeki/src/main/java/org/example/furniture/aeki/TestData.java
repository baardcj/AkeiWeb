package org.example.furniture.aeki;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.furniture.aeki.data.entities.*;
import org.example.furniture.aeki.data.entities.keys.CustomerDiscountId;
import org.example.furniture.aeki.data.repositories.*;
import org.example.furniture.aeki.model.enums.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TestData {

    private final CustomerDiscountRepository customerDiscountRepository;
    private final CustomerRepository customerRepository;
    private final DiscountDetailsRepository discountDetailsRepository;
    private final DiscountRepository discountRepository;
    private final FoodRepository foodRepository;
    private final FurnitureRepository productRepository;

    @Transactional
    public void init() {

        customerRepository.save(Customer.builder()
                .member(false)
                .build());

        productRepository.save(Furniture.builder()
                .type(FurnitureType.TEXTILE)
                .status(FurnitureStatus.NOT_SOLD)
                .itemNumber(1000)
                .description("Merino carpet")
                .price(2350)
                .colour("Blue")
                .build());

        foodRepository.save(Food.builder()
                .type(FoodType.HOT_DOG)
                .flavour(FoodFlavour.CHILI_FLAVORED)
                .price(10)
                .description("Tasty chili hot dog")
                .stock(50)
                .build());


        productRepository.save(Furniture.builder()
                .type(FurnitureType.FURNITURE)
                .status(FurnitureStatus.NOT_SOLD)
                .itemNumber(2000)
                .description("Big wardrobe")
                .price(10000)
                .weight(130)
                .build());

        productRepository.save(Furniture.builder()
                .type(FurnitureType.TEXTILE)
                .status(FurnitureStatus.NOT_SOLD)
                .itemNumber(1001)
                .description("Rug")
                .price(500)
                .colour("Green")
                .build());

        foodRepository.save(Food.builder()
                .type(FoodType.HOT_DOG_VEGETARIAN)
                .flavour(FoodFlavour.NATURAL)
                .price(10)
                .description("Tasty vegetarian hot dog")
                .stock(50)
                .build());


        DiscountDetails discountDetails1 = DiscountDetails.builder()
                .id(1L)
                .foodType(FoodType.HOT_DOG)
                .discount(5)
                .build();

        DiscountDetails discountDetails2 = DiscountDetails.builder()
                .id(2L)
                .furnitureType(FurnitureType.FURNITURE)
                .discount(200)
                .build();

        Discount discount1 = Discount.builder()
                .id(1L)
                .type(DiscountType.FURNITURE_AND_HOT_DOG)
                .build();

        discount1.addDiscountDetails(discountDetails1);
        discount1.addDiscountDetails(discountDetails2);

        Customer customer2 = Customer.builder().id(2L).member(true).build();

        customerRepository.save(customer2);
        discountDetailsRepository.save(discountDetails1);
        discountDetailsRepository.save(discountDetails2);
        discountRepository.save(discount1);


        customerDiscountRepository.save(CustomerDiscount.builder()
                        .customer(customer2)
                        .discount(discount1).build());


    }
}
