package com.github.auggud.contactmanager;

import java.util.Scanner;

public class ContactUI {

    private final Scanner scanner;
    private final ContactManager contactManager;

    public ContactUI(Scanner scanner, ContactManager contactManager) {
        this.scanner = scanner;
        this.contactManager = contactManager;
    }

    public void Start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addContact();
                case 2 -> listContacts();
                case 3 -> updateContact();
                case 4 -> deleteContact();
                case 5 -> searchContacts();
                case 6 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }
    private void printMenu() {
        System.out.println("\n--- Contact manager Menu ---");
        System.out.println("1. Add Contact");
        System.out.println("2. List Contacts");
        System.out.println("3. Update Contact");
        System.out.println("4. Delete Contact");
        System.out.println("5. Search by name");
        System.out.println("6. Exit");
    }

    // used for id receiving from the user
    private int readInt(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }
    private void addContact() {
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Please enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Please enter your address: ");
        String address = scanner.nextLine();

        try {
            Contact contact = contactManager.addContact(name, email, phoneNumber, address);
            System.out.println("Added contact: " + contact.toString());
        } catch (InvalidContactException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void listContacts() {
        System.out.println("--- List of contacts ---");
        for(Contact contact : contactManager.getAllContacts()) {
            System.out.println(contact.toString());
        }
    }
    private void updateContact() {
        int id = readInt("Contact ID you'd like to update: ");
        Contact contact = contactManager.getContactById(id);

        if (contact == null) {
            System.out.println("Contact with ID: " + id + " does not exist");
        }

        System.out.println("Leave field empty to keep current value.");
        System.out.println("New name (" + contact.getName() + "): ");
        String newName = scanner.nextLine();
        if (newName.isBlank()) newName = contact.getName();

        System.out.println("New email (" + contact.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (newEmail.isBlank()) newEmail = contact.getEmail();

        System.out.println("New phone number (" + contact.getPhone() + "): ");
        String newPhone = scanner.nextLine();
        if (newPhone.isBlank()) newPhone = contact.getPhone();

        System.out.println("New address (" + contact.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (newAddress.isBlank()) newAddress = contact.getAddress();

        try {
            contactManager.updateContactById(id, newName, newEmail, newPhone, newAddress);
            System.out.println("Updated contact: " + contact);
        } catch (InvalidContactException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteContact() {
        int id = readInt("Contact ID you'd like to delete: ");

        if (contactManager.deleteContactById(id)) {
            System.out.println("Contact with ID: " + id + " has been deleted.");
        } else {
            System.out.println("Contact with ID: " + id + " does not exist");
        }
    }

    private void searchContacts() {
        System.out.println("Search name: ");
        String query = scanner.nextLine();

        for (Contact contact : contactManager.searchByName(query)) {
            System.out.println(contact.toString());
        }
    }
}
