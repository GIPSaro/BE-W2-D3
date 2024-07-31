import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<Product> warehouse = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {

        initializeWarehouse();
        createCustomers();
        placeOrders();


        System.out.println("--------------------------------- PRODUCTS ----------------------------------");
        warehouse.forEach(System.out::println);
        System.out.println("--------------------------------- CUSTOMERS ----------------------------------");
        customers.forEach(System.out::println);
        System.out.println("--------------------------------- ORDERS ----------------------------------");
        orders.forEach(System.out::println);
        System.out.println("---------------------------------- ES 1 ------------------------------------");
        getBooks().forEach(System.out::println);
        System.out.println("---------------------------------- ES 2 ------------------------------------");
        getBabyOrders().forEach(System.out::println);
        System.out.println("---------------------------------- ES 3 ------------------------------------");
        getBoysProductsWithDiscount().forEach(System.out::println);
        System.out.println("---------------------------------- ES 4 ------------------------------------");
        getTier2Products().forEach(System.out::println);
    }

    // ESERCIZIO 1
    public static List<Product> getBooks() {
        return warehouse.stream().filter(product -> product.getCategory().equals("Books") && product.getPrice() > 100).toList();

    }

    //ESERCIZIO 2
    public static List<Order> getBabyOrders() {

        return orders.stream().filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("Baby"))).toList();
    }

    //ESERCIZIO 3
    public static List<Product> getBoysProductsWithDiscount() {
        return warehouse.stream().filter(p -> p.getCategory().equals("Boys")).map(product -> {
            product.setPrice(product.getPrice() * 0.90);
            return product;
        }).toList();
    }

    // ESERCIZIO 4
    public static List<Product> getTier2Products() {
        List<Order> filteredByTierAndDates = orders.stream()
                .filter(order -> order.getCustomer().getTier() == 2
                        && order.getOrderDate().isBefore(LocalDate.parse("2024-08-09"))
                        && order.getOrderDate().isAfter(LocalDate.parse("2024-07-31")))
                .toList();

        List<Product> products = new ArrayList<>();

        for (Order order : filteredByTierAndDates) {
            products.addAll(order.getProducts());
        }
        return products;
    }


    public static void initializeWarehouse() {
        Product iPhone = new Product("IPhone", "Smartphones", 2000.0);
        Product korBook = new Product("Il Signore degli anelli", "Books", 102);
        Product fightClubBook = new Product("Fight Club", "Books", 18);
        Product beatYouBook = new Product("Beautiful You", "Books", 15);
        Product diapers = new Product("Pampers", "Baby", 3);
        Product toyCar = new Product("Car", "Boys", 15);
        Product toyPlane = new Product("Plane", "Boys", 25);
        Product lego = new Product("Lego Star Wars", "Boys", 500);

        warehouse.addAll(Arrays.asList(iPhone, korBook, fightClubBook, beatYouBook, diapers, toyCar, toyPlane, lego));
    }

    public static void createCustomers() {
        Customer edward = new Customer("Edward Norton", 1);
        Customer penny = new Customer("Penny Harrigan", 2);
        Customer frodo = new Customer("Frodo Baggins", 3);
        Customer steve = new Customer("Steve Jobs", 2);

        customers.add(edward);
        customers.add(penny);
        customers.add(frodo);
        customers.add(steve);
    }

    public static void placeOrders() {
        Order edwardOrder = new Order(customers.get(0), LocalDate.of(2024, 8, 1));
        Order pennyOrder = new Order(customers.get(1), LocalDate.of(2024, 8, 2));
        Order frodoOrder = new Order(customers.get(2), LocalDate.of(2024, 8, 3));
        Order steveOrder = new Order(customers.get(3), LocalDate.of(2024, 8, 4));
        Order frodoOrder2 = new Order(customers.get(2), LocalDate.of(2024, 8, 5));

        Product iPhone = warehouse.get(0);
        Product korBook = warehouse.get(1);
        Product fightClubBook = warehouse.get(2);
        Product beatYouBook = warehouse.get(3);
        Product diaper = warehouse.get(4);

        edwardOrder.addProduct(iPhone);
        edwardOrder.addProduct(korBook);
        edwardOrder.addProduct(diaper);

        pennyOrder.addProduct(fightClubBook);
        pennyOrder.addProduct(beatYouBook);
        pennyOrder.addProduct(iPhone);

        frodoOrder.addProduct(korBook);
        frodoOrder.addProduct(diaper);

        steveOrder.addProduct(diaper);

        frodoOrder2.addProduct(iPhone);

        orders.add(edwardOrder);
        orders.add(pennyOrder);
        orders.add(frodoOrder);
        orders.add(steveOrder);
        orders.add(frodoOrder2);

    }
}

