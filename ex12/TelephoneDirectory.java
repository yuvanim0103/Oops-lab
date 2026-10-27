import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Telephone Directory using Map
 * Uses a TreeMap<String, String> (Name -> Phone Number) so that
 * contacts are always kept in alphabetical order of name.
 */
public class TelephoneDirectory {

    // Map: key = contact name, value = phone number
    private final Map<String, String> directory = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    // Add a new contact
    public void addContact(String name, String phone) {
        if (directory.containsKey(name)) {
            System.out.println("Contact '" + name + "' already exists. Use Update instead.");
        } else if (!isValidPhone(phone)) {
            System.out.println("Invalid phone number. Enter 10 digits.");
        } else {
            directory.put(name, phone);
            System.out.println("Contact added successfully.");
        }
    }

    // Search a contact by exact name
    public void searchContact(String name) {
        String phone = directory.get(name);
        if (phone != null) {
            System.out.println("Found -> " + name + " : " + phone);
        } else {
            System.out.println("Contact '" + name + "' not found.");
        }
    }

    // Search contacts whose name starts with a given prefix
    public void searchByPrefix(String prefix) {
        boolean found = false;
        for (Map.Entry<String, String> e : directory.entrySet()) {
            if (e.getKey().toLowerCase().startsWith(prefix.toLowerCase())) {
                System.out.printf("%-15s %s%n", e.getKey(), e.getValue());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts start with '" + prefix + "'.");
        }
    }

    // Update an existing contact's number
    public void updateContact(String name, String newPhone) {
        if (!directory.containsKey(name)) {
            System.out.println("Contact '" + name + "' not found.");
        } else if (!isValidPhone(newPhone)) {
            System.out.println("Invalid phone number. Enter 10 digits.");
        } else {
            directory.put(name, newPhone);
            System.out.println("Contact updated successfully.");
        }
    }

    // Delete a contact
    public void deleteContact(String name) {
        if (directory.remove(name) != null) {
            System.out.println("Contact '" + name + "' deleted.");
        } else {
            System.out.println("Contact '" + name + "' not found.");
        }
    }

    // Display all contacts in alphabetical order
    public void displayAll() {
        if (directory.isEmpty()) {
            System.out.println("Directory is empty.");
            return;
        }
        System.out.println("-----------------------------------");
        System.out.printf("%-4s %-15s %s%n", "No.", "Name", "Phone");
        System.out.println("-----------------------------------");
        int i = 1;
        for (Map.Entry<String, String> e : directory.entrySet()) {
            System.out.printf("%-4d %-15s %s%n", i++, e.getKey(), e.getValue());
        }
        System.out.println("-----------------------------------");
        System.out.println("Total contacts: " + directory.size());
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    public static void main(String[] args) {
        TelephoneDirectory td = new TelephoneDirectory();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== TELEPHONE DIRECTORY ======");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Search by Starting Letters");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Display All Contacts");
