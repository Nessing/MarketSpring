package ru.nessing.orders.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.nessing.core.interfaces.ITokenService;
import ru.nessing.core.models.UserInfo;
import ru.nessing.orders.services.CartService;
import ru.nessing.orders.services.OrderService;
import ru.nessing.routing.dtos.OrderDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final CartService cartService;

    private final ITokenService tokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(@RequestParam UUID cartUuid, @RequestParam String address) {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OrderDto orderDto = orderService.createFromUserCart(userInfo.getUserId(), cartUuid, address);
        cartService.clearCart(cartUuid);
        return orderDto;
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.findAllOrdersByUserId(userInfo.getUserId());
    }
}
