package ru.pronichev.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pronichev.mvc.data.Product;
import ru.pronichev.mvc.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("frontProducts", service.getAll());
        return "products_all";
    }

    @GetMapping("/add")
    public String getAllProducts() {
        return "products_add";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product product) {
        service.save(product);
        return "redirect:/products/all";
    }
}