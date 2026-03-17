package com.lifelink;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LifeLinkApp {

    private static MatchEngine engine = new MatchEngine();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("\n ⏻");
        System.out.println(" ◌ Loading app...");
        System.out.println(" □□□□□□□□□□□■100%");
        System.out.println("\n                ωᥱℓᥴ\uD801\uDC2Bmᥱ!");

        System.out.println("========================================");
        System.out.println("LIFELINK - Emergency Blood Donor System");
        System.out.println("========================================");

        addInitialDonors();

        while (true) {
            printMenu();

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> listAllDonors();
                case 2 -> addNewDonor();
                case 3 -> findCompatibleDonors();
                case 4 -> emergencyMatch();
                case 0 -> {
                    System.out.println("Thank you for using LifeLink. Stay safe!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        }
    }

    // ====================== INITIAL DONORS ======================
    private static void addInitialDonors() {
        Donor[] initialDonors = {
                new Donor("D001", "Rahul Sharma", 29, "Male", "O+",
                        78.5, "9876543210", "Kukatpally",
                        LocalDate.of(2025, 12, 10), true),

                new Donor("D002", "Priya Reddy", 24, "Female", "A-",
                        55.0, "9123456789", "Secunderabad",
                        null, true),

                new Donor("D003", "Sandeep Kumar", 35, "Male", "B+",
                        82.0, "9988776655", "Hyderabad",
                        LocalDate.of(2025, 2, 20), false),

                new Donor("D004", "Anjali Mehta", 42, "Female", "AB+",
                        62.5, "7894561230", "Gachibowli",
                        LocalDate.of(2025, 9, 5), true)
        };

        for (Donor donor : initialDonors) {
            try {
                engine.addDonor(donor);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠\uFE0FError Encountered! Trying again...");
            }
        }
    }

    // ====================== MENU METHODS ======================
    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. List All Donors");
        System.out.println("2. Add New Donor");
        System.out.println("3. Find Compatible Donors for Patient");
        System.out.println("4. Emergency Match (Available Only)");
        System.out.println("0. Exit");
    }

    private static void listAllDonors() {
        System.out.println("\n--- All Registered Donors ---");
        if (engine.getDonorCount() == 0) {
            System.out.println("No donors registered yet.");
            return;
        }

        for (Donor donor : engine.getDonors()) {
            System.out.println(donor);
            System.out.println("----------------------------------");
        }
        System.out.println("Total donors: " + engine.getDonorCount());
    }

    private static void addNewDonor() {
        System.out.println("\n--- Add New Donor ---");

        System.out.print("Donor ID (e.g. D005): ");
        String donorId = sc.nextLine().trim();

        System.out.print("Full Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Gender (Male/Female): ");
        String gender = sc.nextLine().trim();

        System.out.print("Blood Group (e.g. O+, A-, B+): ");
        String bloodGroup = sc.nextLine().trim();

        System.out.print("Weight (kg): ");
        double weightKg = sc.nextDouble();
        sc.nextLine();

        System.out.print("Phone Number: ");
        String phone = sc.nextLine().trim();

        System.out.print("City (e.g. Hyderabad, Secunderabad): ");
        String city = sc.nextLine().trim();

        System.out.print("Last Donation Date (yyyy-mm-dd) or press Enter if First Time: ");
        String dateStr = sc.nextLine().trim();
        LocalDate lastDonation = dateStr.isEmpty() ? null : LocalDate.parse(dateStr);

        System.out.print("Is Available Now? (true/false): ");
        boolean available = sc.nextBoolean();

        Donor newDonor = new Donor(donorId, name, age, gender, bloodGroup,
                weightKg, phone, city, lastDonation, available);

        try {
            engine.addDonor(newDonor);
            System.out.println("✅ Donor added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Failed to add donor: " + e.getMessage());
        }
    }

    private static void findCompatibleDonors() {
        System.out.println("\n--- Find Compatible Donors ---");
        System.out.print("Patient Blood Group (e.g. O+): ");
        String bloodGroup = sc.nextLine().trim();

        if (!isvalidBloodGroups(bloodGroup)) {
            System.out.println("Invalid Blood Group. " + bloodGroup);
            return;
        }

        System.out.print("City (or press Enter for any city): ");
        String city = sc.nextLine().trim();

        List<Donor> matches = engine.findCompatibleDonors(bloodGroup, city);
        if (matches.isEmpty()) {
            System.out.println("No compatible donors found.");
        }
        else {
            System.out.println("\nFound "+ matches.size() +" compatible donor(s):\n");
            for (Donor donor : matches) {
                System.out.println(donor);
                System.out.println("------------------------------");
            }
        }
    }

    private static void emergencyMatch() {
        System.out.println("\n--- Emergency Match (Available Donors Only) ---");
        System.out.print("Patient Blood Group (e.g. O+): ");
        String bloodGroup = sc.nextLine().trim();

        if (!isvalidBloodGroups(bloodGroup)) {
            System.out.println("Invalid Blood Group. " + bloodGroup);
            return;
        }

        System.out.print("City (or press Enter for any city): ");
        String city = sc.nextLine().trim();

        List <Donor> matches = engine.findEmergencyDonors(bloodGroup, city);

        if (matches.isEmpty()) {
            System.out.println("No emergency available donors found at this moment.");
        }
        else {
            System.out.println("\nEMERGENCY MATCH FOUND! "+ matches.size() +" donor(s) ready:\n");
            for (Donor donor : matches) {
                System.out.println(donor);
                System.out.println("------------------------------");
            }
        }
    }

    private static boolean isvalidBloodGroups(String bg) {
        if (bg == null) return false;
        String b = bg.toUpperCase().trim();
        return List.of("O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-").contains(b);
    }
}