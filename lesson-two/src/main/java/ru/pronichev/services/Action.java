package ru.pronichev.services;

import lombok.Getter;

@Getter
public enum Action {
    SHOW_ALL("all", "show all products"),
    SHOW_CART("cart", "show products in cart"),
    ADD_TO_CART("add", "put product in cart"),
    DELETE_FROM_CART("delete", "remove product from cart"),
    MENU("menu", "show menu again"),
    CLOSE("exit", "close the program");

    private final String name;
    private final String description;

    Action(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}