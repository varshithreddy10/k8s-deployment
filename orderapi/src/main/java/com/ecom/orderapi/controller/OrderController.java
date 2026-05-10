package com.ecom.orderapi.controller;

import com.ecom.orderapi.dto.*;
import com.ecom.orderapi.repository.OrderRepository;
import com.ecom.orderapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController
{
    @Autowired
    private OrderService orderservice;

    @Autowired
    private OrderRepository orderrepo;

    @GetMapping("/check/orderapi")
    public ResponseEntity<String> checkapi()
    {
        String message = "orderapi";
        return new ResponseEntity<>(message , HttpStatus.CREATED);
    }

    @GetMapping("/getall/orders")
    public  ResponseEntity<List<OrderResponseDto>> getAllOrders()
    {
        List<OrderResponseDto> allorderdtos = orderservice.getAllOrdersServ();
        return new ResponseEntity<>(allorderdtos , HttpStatus.OK);
    }

    @GetMapping("/getall/orders/{customerId}")
    public  ResponseEntity<List<OrderResponseDto>> getAllOrdersofCustomer(@PathVariable Long customerId)
    {
        List<OrderResponseDto> allorderdtos = orderservice.getAllOrdersOfCustomerServ(customerId);
        return new ResponseEntity<>(allorderdtos, HttpStatus.OK);
    }

    @GetMapping("/get/order/byorderid/{orderId}")
    public  ResponseEntity<OrderResponseDto> getOrderByOrderId(@PathVariable Long orderId)
    {
        OrderResponseDto orderdto = orderservice.getOrderByOrderIdServ(orderId);
        return new ResponseEntity<>(orderdto , HttpStatus.OK);
    }

    @PutMapping("/modify/order/{orderId}")
    public  ResponseEntity<OrderDto> modifyOrder(@PathVariable Long orderId,@RequestBody OrderDto orderdto)
    {
        OrderDto modifiedorderdto = orderservice.modifyOrderServ(orderdto);

        return new ResponseEntity<>(modifiedorderdto ,HttpStatus.OK);
    }

    @DeleteMapping("/cancel/order/byorderid/{orderId}")
    public  ResponseEntity<PurchaseOrderResponseDto> cancelOrder(@PathVariable Long orderId)
    {
        System.out.println("entered the cancel controller for orderId = "+orderId);
        PurchaseOrderResponseDto purchaseresponse = orderservice.cancelOrderServ(orderId);
        return new ResponseEntity<>(purchaseresponse , HttpStatus.ACCEPTED);
    }

    @PostMapping("/placeorder")
    public ResponseEntity<PurchaseOrderResponseDto> placeOrder(@RequestBody PlaceOrderDto placeorderdto)
    {
        PurchaseOrderResponseDto purchaseresponse = orderservice.placeOrderServ(placeorderdto);
       return new ResponseEntity<>(purchaseresponse , HttpStatus.CREATED);
        //return new ResponseEntity<>(new PurchaseOrderResponseDto() , HttpStatus.CREATED);
    }


}
