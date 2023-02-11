package com.example.demoproject.controller;

import com.example.demoproject.domain.Order;
import com.example.demoproject.domain.enums.OrderStatus;
import com.example.demoproject.model.OrderForm;
import com.example.demoproject.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/")
    public String viewOrdersPage(Model model) {
        model.addAttribute("order_list", orderService.findAll());
        return "orders";
    }

    @GetMapping("/order/add/")
    public String viewOrdersAddPageGet(Model model) {
        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("is_edit", false);
        model.addAttribute("form", new OrderForm());
        return "order_update";
    }

    @PostMapping(path = "/order/add/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String viewOrdersAddPagePost(@Valid @ModelAttribute("form") OrderForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", OrderStatus.values());
            model.addAttribute("is_edit", false);
            model.addAttribute("form", form);
            return "order_update";
        }
        orderService.add(form);
        return "redirect:/order/";
    }

    @GetMapping("/order/update/{orderId}/")
    public String viewOrderUpdatePage(@PathVariable("orderId") Integer orderId, Model model) {
        Optional<Order> orderOpt = orderService.findById(orderId);
        if(orderOpt.isEmpty()) {
            return "redirect:/order/";
        }
        Order order = orderOpt.get();

        OrderForm form = new OrderForm();
        form.setId(orderId);
        form.setStatus(order.getStatus());
        form.setName(order.getName());

        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("is_edit", true);
        model.addAttribute("form", form);
        return "order_update";
    }

    @PostMapping(path = "/order/update/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String viewOrderUpdatePagePost(@Valid @ModelAttribute("form") OrderForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", OrderStatus.values());
            model.addAttribute("is_edit", true);
            model.addAttribute("form", form);
            return "order_update";
        }
        orderService.update(form);
        return "redirect:/";
    }

}
