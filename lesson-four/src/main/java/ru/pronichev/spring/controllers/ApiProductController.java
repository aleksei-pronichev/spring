package ru.pronichev.spring.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pronichev.spring.data.Product;
import ru.pronichev.spring.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ApiProductController {
    private static final String JSON = "json";
    private static final String XML = "xml";
    private static final String YAML = "yml";
    private static final String PROPERTIES = "prop";

    private final ProductService service;

    private final ObjectMapper jsonMapper;
    private final XmlMapper xmlMapper;
    private final YAMLMapper yamlMapper;
    private final JavaPropsMapper propsMapper;

    @Autowired
    public ApiProductController(ProductService service) {
        this.service = service;
        this.jsonMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();
        this.yamlMapper = new YAMLMapper();
        this.propsMapper = new JavaPropsMapper();
    }

    @GetMapping(value = "/{type}/{id}")
    public String getJson(
            @PathVariable(value = "id") int id,
            @PathVariable(value = "type") String type
    ) throws JsonProcessingException {
        Product product = service.getByID(id);
        switch (type) {
            case JSON:
                return jsonMapper.writeValueAsString(product);
            case XML:
                return xmlMapper.writeValueAsString(product);
            case YAML:
                return yamlMapper.writeValueAsString(product);
            case PROPERTIES:
                return propsMapper.writeValueAsString(product);
            default:
                throw new UnsupportedOperationException("Error format");
        }
    }
}