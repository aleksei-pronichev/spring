package ru.pronichev.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pronichev.data.Product;
import ru.pronichev.eceptions.ResourceNotFoundException;
import ru.pronichev.services.ProductService;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("products", productService.findAll(page - 1, 2));
        return "products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteOneProductById(@RequestParam(name = "id") Long id) {
        productService.deleteById(id);
        return "ok";
    }

}
