package org.example.furniture.aeki.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.furniture.aeki.data.entities.Customer;
import org.example.furniture.aeki.data.entities.CustomerOrder;
import org.example.furniture.aeki.data.repositories.*;
import org.example.furniture.aeki.model.OrderForm;
import org.example.furniture.aeki.services.DiscountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerOrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerOrderRepository customerOrderRepository;

    @MockBean
    private CustomOrderControllerDelegate delegate;

    @Test
    void testGetAllOrders() throws Exception {
        when(customerOrderRepository.findAll()).thenReturn(
                Arrays.asList(CustomerOrder.builder().build()));

        Arrays.asList(CustomerOrder.builder().build());
        mvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

        verify(customerOrderRepository, times(1)).findAll();
    }

    @Test
    void testPostPassenger() throws Exception {

        List<OrderForm> orderForm = Arrays.asList(OrderForm.builder().itemNumber(2000).build());

        when(delegate.createOrder(Optional.of(4L), Optional.of(true))).
                thenReturn(CustomerOrder.builder().id(1L).customer(
                        Customer.builder().id(4L).build()).sum(10000).build());

        mvc.perform(post("/orders/new")
                .content(new ObjectMapper().writeValueAsString(orderForm))
                .param("customer_id", "4")
                .param("member" , "true")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.customer", is(4)))
                .andExpect(jsonPath("$.sum", is(10000)))
                .andExpect(jsonPath("$.items", is(notNullValue())));
    }

}