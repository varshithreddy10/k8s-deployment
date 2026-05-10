package com.ecom.cartapi.controller;

import com.ecom.cartapi.dto.CartDto;
import com.ecom.cartapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController
{
    @Autowired
    private CartService cartservice;

    @GetMapping("/check/cartapi")
    public ResponseEntity<String> checkapi()
    {
        String message = "cartapi";
        return new ResponseEntity<>(message , HttpStatus.CREATED);
    }

    @PostMapping("/carts/products/{productId}/quantity/{quantity}/customer/{customerId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long productId , @PathVariable Integer quantity , @PathVariable Long customerId)
    {
        CartDto cartdto = cartservice.addProductToCartServ(productId , quantity , customerId);
        return new ResponseEntity<>(cartdto , HttpStatus.CREATED);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<CartDto>> getAllCarts()
    {
        List<CartDto> allcartdto = cartservice.getAllCartsServ();
        return new ResponseEntity<>(allcartdto , HttpStatus.CREATED);
    }

    @GetMapping("/get/cart/{customerId}")
    public  ResponseEntity<CartDto> getCustomerCart(@PathVariable Long customerId)
    {
        CartDto cartdto = cartservice.getCustomerCartServ(customerId);
        return new ResponseEntity<>(cartdto , HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<String> deleteCustomerCart(@PathVariable Long cartId)
    {
        String message = cartservice.deleteCustomerCartserv(cartId);
        return new ResponseEntity<>(message , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartId}/{productId}")
    public ResponseEntity<CartDto> deleteProductfronCart(@PathVariable Long cartId , @PathVariable Long productId)
    {
        CartDto cartdto= cartservice.deleteProductFromCart(cartId , productId);
        return new ResponseEntity<>(cartdto , HttpStatus.OK);
    }


}
