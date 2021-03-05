package ru.pronichev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.pronichev.model.Product;
import ru.pronichev.services.Action;
import ru.pronichev.services.Cart;
import ru.pronichev.services.ProductRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {
    private static final ApplicationContext CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);

    private static BufferedReader reader;
    private static ProductRepository repository;
    private static Cart cart;

    public static void main(String[] args) {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            showMenu();
            cart = CONTEXT.getBean(Cart.class);
            repository = CONTEXT.getBean(ProductRepository.class);

            Action action;
            do {
                action = initAction(reader.readLine().trim());
                processing(action);

            } while (action != Action.CLOSE);
        } catch (IOException e) {
            System.out.println("Main error");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void showMenu() {
        System.out.println("Use this actions for control your cart:");
        Arrays.stream(Action.values())
                .forEach(System.out::println);
    }

    private static Action initAction(String string) {
        if (string.isBlank()) {
            return Action.MENU;
        }
        for (Action action : Action.values()) {
            if (action.getName().equals(string)) {
                return action;
            }
        }
        return Action.MENU;
    }

    private static void processing(Action action) {
        switch (action) {
            case MENU:
                showMenu();
                break;
            case SHOW_ALL:
                showAll();
                break;
            case SHOW_CART:
                showCart();
                break;
            case ADD_TO_CART:
                toAdd();
                break;
            case DELETE_FROM_CART:
                toDelete();
                break;
            case CLOSE:
                toClose();
                break;
            default:
                System.out.println("Unknown action");
        }
    }

    private static void showAll() {
        if (repository.getAll().isEmpty()) {
            System.out.println("There is no products, all have been eaten");
        }
        repository.getAll()
                .forEach(System.out::println);
    }

    private static void showCart() {
        if (cart.getAll().isEmpty()) {
            System.out.println("Your cart is empty");
        }
        cart.getAll()
                .forEach(System.out::println);
    }

    private static void toAdd() {
        try {
            cart.putByID(giveIdFromConsole());
            System.out.println("great choice");
        } catch (IOException | ProductRepository.RepositoryException e) {
            System.out.println("It is no current id");
        }
    }

    private static void toDelete() {
        try {
            cart.remove(giveIdFromConsole());
            System.out.println("removed");
        } catch (IOException | Cart.CartException e) {
            System.out.println("You have no product like this in your cart");
        }
    }

    private static int giveIdFromConsole() throws IOException {
        System.out.println("Write product ID");
        return Integer.parseInt(reader.readLine().trim());
    }

    private static void toClose() {
        int sum = cart.getAll()
                .stream()
                .mapToInt(Product::getCost)
                .sum();
        System.out.println("Your bill is " + sum + " tugrics");
    }
}