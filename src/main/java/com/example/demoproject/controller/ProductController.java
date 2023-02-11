package com.example.demoproject.controller;

import com.example.demoproject.domain.Product;
import com.example.demoproject.domain.enums.ProductStatus;
import com.example.demoproject.model.ProductForm;
import com.example.demoproject.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewProductsPage(Model model) {
        model.addAttribute("product_list", productService.findAll());
        return "products";
    }

    @GetMapping("/product/add/")
    public String viewProductsAddPageGet(Model model) {
        model.addAttribute("statuses", ProductStatus.values());
        model.addAttribute("is_edit", false);
        model.addAttribute("form", new ProductForm());
        return "product_update";
    }

    @PostMapping(path = "/product/add/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String viewProductsAddPagePost(@Valid @ModelAttribute("form") ProductForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ProductStatus.values());
            model.addAttribute("is_edit", false);
            model.addAttribute("form", form);
            return "product_update";
        }
        productService.add(form);
        return "redirect:/";
    }

    @GetMapping("/product/update/{productId}/")
    public String viewProductsUpdatePage(@PathVariable("productId") Integer productId, Model model) {
        Optional<Product> productOpt = productService.findById(productId);
        if(productOpt.isEmpty()) {
            return "redirect:/";
        }
        Product product = productOpt.get();

        ProductForm form = new ProductForm();
        form.setId(productId);
        form.setStatus(product.getStatus());
        form.setName(product.getName());

        model.addAttribute("statuses", ProductStatus.values());
        model.addAttribute("is_edit", true);
        model.addAttribute("form", form);
        return "product_update";
    }

    @PostMapping(path = "/product/update/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String viewProductsUpdatePagePost(@Valid @ModelAttribute("form") ProductForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ProductStatus.values());
            model.addAttribute("is_edit", true);
            model.addAttribute("form", form);
            return "product_update";
        }
        productService.update(form);
        return "redirect:/";
    }

}
