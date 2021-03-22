package ru.pronichev;

import lombok.Data;
import ru.pronichev.data.Customer;


@Data
public class CustomerDTO {
    private Long id;
    private String name;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }
}