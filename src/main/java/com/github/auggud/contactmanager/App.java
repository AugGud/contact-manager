package com.github.auggud.contactmanager;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactManager cm = new ContactManager();

        ContactUI contactUI = new ContactUI(sc, cm);

        contactUI.Start();
    }
}
