package com.github.auggud.contactmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactManager {
    //store contacts (in a Map)
    private Map<Integer,Contact> contacts = new HashMap<>();
    private int nextId = 1;

    //add contact
    public Contact addContact(String name, String email, String phone, String address) {
        Contact contact = new Contact(nextId++, name, email, phone, address);
        contacts.put(contact.getId(), contact);
        return contact;
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
}
