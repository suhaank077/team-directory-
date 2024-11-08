import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    private List<Item> inventory = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main system = new Main();
        system.run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                addItem();
            } else if (choice == 2) {
                updateQuantity();
            } else if (choice == 3) {
                removeItem();
            } else if (choice == 4) {
                viewInventory();
            } else if (choice == 5) {
                viewLowStockItems();
            } else if (choice == 6) {
                System.out.println("Exiting program...");
                running = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nFRC Team Inventory Management System:");
        System.out.println("1. Add New Item");
        System.out.println("2. Update Item Quantity");
        System.out.println("3. Remove Item");
        System.out.println("4. View All Items");
        System.out.println("5. View Low-Stock Items");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter category (e.g., electrical, mechanical, tools): ");
        String category = scanner.nextLine();
        
        Item item = new Item(name, quantity, category);
        inventory.add(item);
        System.out.println("Item added successfully!");
    }

    private void updateQuantity() {
        System.out.print("Enter the name of the item to update: ");
        String name = scanner.nextLine();
        Item item = findItem(name);
        
        if (item != null) {
            System.out.print("Enter new quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            item.setQuantity(quantity);
            System.out.println("Quantity updated successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    private void removeItem() {
        System.out.print("Enter the name of the item to remove: ");
        String name = scanner.nextLine();
        Item item = findItem(name);
        
        if (item != null) {
            inventory.remove(item);
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    private void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            System.out.println("Current Inventory:");
            for (Item item : inventory) {
                System.out.println(item);
            }
        }
    }

    private void viewLowStockItems() {
        System.out.print("Enter low-stock threshold: ");
        int threshold = scanner.nextInt();
        scanner.nextLine(); 
        boolean foundLowStock = false;

        System.out.println("Low-Stock Items:");
        for (Item item : inventory) {
            if (item.getQuantity() < threshold) {
                System.out.println(item);
                foundLowStock = true;
            }
        }
        if (!foundLowStock) {
            System.out.println("No low-stock items found.");
        }
    }

    private Item findItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
