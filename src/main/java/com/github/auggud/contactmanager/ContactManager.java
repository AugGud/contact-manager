package com.github.auggud.contactmanager;

import javax.naming.InvalidNameException;
import java.util.*;

public class ContactManager {
    //store contacts (in a Map)
    private Map<Integer,Contact> contacts = new HashMap<>();
    private int nextId = 1;

    //add contact
    public Contact addContact(String name, String email, String phone, String address) {
        if(!ContactValidator.isValidName(name)){
            throw new InvalidContactException("Name is invalid");
        }
        if(!ContactValidator.isValidEmail(email)){
            throw new InvalidContactException("Email is invalid");
        }
        if(!ContactValidator.isValidPhone(phone)){
            throw new InvalidContactException("Phone number is invalid");
        }
        if(emailAlreadyExists(email)){
            throw new InvalidContactException("Email already exists");
        }

        Contact contact = new Contact(nextId++, name, email, phone, address);
        contacts.put(contact.getId(), contact);
        return contact;
    }

    private boolean emailAlreadyExists(String email){
        for(Contact contact : contacts.values()){
            if(contact.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }

    //get contact by id
    public Contact getContactById(int id) {
        return contacts.get(id);
    }

    //delete contact
    public boolean deleteContactById(int id) {
        return contacts.remove(id) != null;
    }

    //update contact
    public boolean updateContactById(int id, String name, String email, String phone, String address) {
        Contact contact = contacts.get(id);
        if(contact == null) return false;

        contact.setName(name);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);

        return true;
    }

    // get all contacts
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }

    // search
    public List<Contact> searchByName(String query) {
        List<Contact> results = new ArrayList<>();

        for (Contact contact : contacts.values()) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(contact);
            }
        }
        return results;
    }

    // sort alphabetically
    public List<Contact> getContactsByName() {
        List<Contact> results = getAllContacts();
        results.sort(Comparator.comparing(Contact::getName));

        return results;
    }
}
