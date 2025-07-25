import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    private static ArrayList<String> myList = new ArrayList<>();

    // Scanner for input passed to SafeInput
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String menuChoice;
        boolean quit = false;

        do {
            displayListWithMenu();
            menuChoice = SafeInput.getRegExString(in,
                    "Enter your choice", "[AaDdIiPpQq]").toUpperCase();

            switch (menuChoice) {
                case "A":
                    addItem(); // Adds item to the end of the list
                    break;
                case "D":
                    deleteItem(); // Deletes an item by number
                    break;
                case "I":
                    insertItem(); // Insert item at index
                    break;
                case "P":
                    printList(); // Print the entire list
                    break;
                case "Q":
                    quit = quitProgram(); // Ask for quit confirmation
                    break;
            }
        } while (!quit);

        System.out.println("Thank you for using ListMaker.");
    }

    private static void displayListWithMenu() {
        System.out.println("\n--- Your Current List ---");

        if (myList.isEmpty()) {
            System.out.println("[Empty]");
        } else {
            for (int i = 0; i < myList.size(); i++) {
                System.out.printf("%3d: %s\n", i + 1, myList.get(i));
            }
        }

        // Show available commands
        System.out.println("\nAvailable Menu:");
        System.out.println("A – Add item");
        System.out.println("D – Delete item");
        System.out.println("I – Insert item");
        System.out.println("P – Print list");
        System.out.println("Q – Quit");
    }

    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item you want to add");
        myList.add(item);
        System.out.println("Success! The item '" + item + "' has been added to the end of your list.");
        System.out.println("Your list now contains " + myList.size() + " item(s).");
    }

    private static void deleteItem() {
        System.out.println("\n--- Deleting Item from List ---");
        if (myList.isEmpty()) {
            System.out.println("Cannot delete from an empty list. Please add some items first.");
            return;
        }

        printList();

        int itemNum = SafeInput.getRangedInt(in, "Enter the number of the item you want to delete", 1, myList.size());

        String removedItem = myList.remove(itemNum - 1);
        System.out.println("Success! The item '" + removedItem + "' has been removed from your list.");
        System.out.println("Your list now contains " + myList.size() + " item(s).");
    }

    private static void insertItem() {
        String item = SafeInput.getNonZeroLenString(in, "Please enter the item you want to insert");

        int index = SafeInput.getRangedInt(in,
                "Enter the position to insert the item", 1, myList.size() + 1);

        myList.add(index - 1, item);
        System.out.println("Success! The item '" + item + "' was inserted at position " + index + ".");

    }

    private static void printList() {
        System.out.println("\n--- Full List ---");
        if (myList.isEmpty()) {
            System.out.println("[Empty]");
        } else {
            for (int i = 0; i < myList.size(); i++) {
                System.out.printf("%3d: %s\n", i + 1, myList.get(i));
            }
        }
    }

    private static boolean quitProgram() {
        return SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
    }
}
