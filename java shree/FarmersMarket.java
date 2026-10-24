import java.util.Scanner;

public class FarmersMarket {

    static class Product {
        String name;
        double price;
        int stock;

        Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    static class Customer {
        String name;
        double walletBalance;

        Customer(String name, double walletBalance) {
            this.name = name;
            this.walletBalance = walletBalance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = {
            new Product("Tomato", 20.0, 50),
            new Product("Carrot", 15.0, 40),
            new Product("Potato", 12.0, 60),
            new Product("Onion", 30.0, 35),
            new Product("Beans", 40.0, 25)
        };

        Customer customer = new Customer("Customer", 1000.0);

        System.out.println("==================================");
        System.out.println("      FARMERS' MARKET SYSTEM");
        System.out.println("==================================");

        System.out.print("Enter customer name: ");
        customer.name = sc.nextLine();

        boolean continueShopping = true;
        double total = 0.0;

        while (continueShopping) {
            System.out.println("\n---------- PRODUCT LIST ----------");
            System.out.printf("%-5s %-12s %-12s %-10s%n",
                    "No.", "Product", "Price/kg", "Stock");

            for (int i = 0; i < products.length; i++) {
                System.out.printf("%-5d %-12s Rs.%-9.2f %-10d%n",
                        i + 1,
                        products[i].name,
                        products[i].price,
                        products[i].stock);
            }

            System.out.print("\nEnter product number (1-5): ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            if (choice < 1 || choice > products.length) {
                System.out.println("Invalid product number.");
                continue;
            }

            Product selected = products[choice - 1];

            System.out.print("Enter quantity in kg: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid quantity.");
                sc.next();
                continue;
            }

            int quantity = sc.nextInt();

            if (quantity <= 0) {
                System.out.println("Quantity must be greater than zero.");
                continue;
            }

            if (quantity > selected.stock) {
                System.out.println("Sorry, insufficient stock.");
                System.out.println("Available stock: "
                        + selected.stock + " kg");
                continue;
            }

            double amount = selected.price * quantity;

            System.out.println("\n---------- ORDER DETAILS ----------");
            System.out.println("Customer: " + customer.name);
            System.out.println("Product: " + selected.name);
            System.out.println("Quantity: " + quantity + " kg");
            System.out.printf("Price per kg: Rs.%.2f%n",
                    selected.price);
            System.out.printf("Order amount: Rs.%.2f%n", amount);

            System.out.print("Add this item to cart? (yes/no): ");
            String answer = sc.next();

            if (answer.equalsIgnoreCase("yes")) {
                total += amount;
                selected.stock -= quantity;
                System.out.println("Item added to cart successfully!");
            } else {
                System.out.println("Item not added.");
            }

            System.out.print("Continue shopping? (yes/no): ");
            String more = sc.next();

            if (!more.equalsIgnoreCase("yes")) {
                continueShopping = false;
            }
        }

        System.out.println("\n==================================");
        System.out.println("           FINAL BILL");
        System.out.println("==================================");
        System.out.println("Customer Name: " + customer.name);
        System.out.printf("Total Order Amount: Rs.%.2f%n", total);
        System.out.printf("Wallet Balance: Rs.%.2f%n",
                customer.walletBalance);

        if (total == 0.0) {
            System.out.println("No items ordered.");
        } else if (customer.walletBalance < total) {
            System.out.println("Insufficient wallet balance.");
            System.out.println("Payment unsuccessful.");
            System.out.println("Please add money to your wallet.");
        } else {
            System.out.print("Proceed to payment? (yes/no): ");
            String payment = sc.next();

            if (payment.equalsIgnoreCase("yes")) {
                customer.walletBalance -= total;

                System.out.println("\nPayment Successful!");
                System.out.println("Order Confirmed!");
                System.out.println(
                        "Thank you for supporting local farmers!");
            } else {
                System.out.println("Payment cancelled.");
                System.out.println("Order not confirmed.");
            }
        }

        System.out.println("\n==================================");
        System.out.println("           FINAL STATUS");
        System.out.println("==================================");
        System.out.printf("Remaining Wallet Balance: Rs.%.2f%n",
                customer.walletBalance);

        System.out.println("\nThank you for visiting Farmers' Market!");

        sc.close();
    }
}