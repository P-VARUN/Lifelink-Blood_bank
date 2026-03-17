package com.lifelink;

import java.time.LocalDate;

public class Donor {
    private String donorId;
    private String name;
    private int age;
    private String gender;
    private String bloodGroup;
    private double weightKg;
    private String phoneNumber;
    private String city;
    private LocalDate lastDonationDate;
    private boolean isAvailable;

    Donor(String donorId, String name, int age, String gender, String bloodGroup, double weightKg, String phoneNumber, String city, LocalDate lastDonationDate, boolean isAvailable) {
        this.donorId = donorId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.weightKg = weightKg;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.lastDonationDate = lastDonationDate;
        this.isAvailable = isAvailable;
    }

    public String getDonorId() {
        return donorId;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public double getWeightKg() {
        return weightKg;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getCity() {
        return city;
    }
    public LocalDate getLastDonationDate() {
        return lastDonationDate;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        String lastDon = (lastDonationDate == null) ? "Never" : lastDonationDate.toString();
        return "Donor [" + donorId + "]\n" +
                "  Name       : " + name + "\n" +
                "  Age        : " + age + "\n" +
                "  Gender     : " + gender + "\n" +
                "  Blood group: " + bloodGroup + "\n" +
                "  Weight     : " + weightKg + " kg\n" +
                "  City       : " + city + "\n" +
                "  Phone      : " + phoneNumber + "\n" +
                "  Available  : " + (isAvailable ? "Yes" : "No") + "\n" +
                "  Last donation: " + lastDon;
    }
}