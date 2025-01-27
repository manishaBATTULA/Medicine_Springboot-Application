package com.example.user.Controller;

import com.example.user.Model.Order;
import com.example.user.Service.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private Order order;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        order = new Order(1L, 101L, 2, "NEW");
    }

    // ✅ Test Create Order
    @Test
    public void testCreateOrder() throws Exception {
        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"medicineId\":101, \"quantity\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.medicineId").value(101))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.status").value("NEW"));
    }

    // ✅ Test Get Order Details (Success via MockMvc)
    @Test
    public void testGetOrderDetails() throws Exception {
        Long orderId = 1L;
        when(orderService.getOrderDetails(orderId)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/api/orders/{id}", orderId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.medicineId").value(101))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.status").value("NEW"));
    }

    // ✅ Test Get Order Details (Success via Direct Call)
    @Test
    void testGetOrderDetails_Success() {
        Long orderId = 1L;
        when(orderService.getOrderDetails(orderId)).thenReturn(Optional.of(order));

        ResponseEntity<Order> response = orderController.getOrderDetails(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    // ✅ Test Get Order Details (Not Found)
    @Test
    void testGetOrderDetails_NotFound() {
        Long orderId = 2L;
        when(orderService.getOrderDetails(orderId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, 
            () -> orderController.getOrderDetails(orderId),
            "Order not found"
        );
    }

    // ✅ Test Cancel Order
    @Test
    public void testCancelOrder() throws Exception {
        Long orderId = 1L;
        doNothing().when(orderService).cancelOrder(orderId);

        mockMvc.perform(delete("/api/orders/{id}", orderId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Order cancelled successfully"));

        verify(orderService, times(1)).cancelOrder(orderId);
    }
}
