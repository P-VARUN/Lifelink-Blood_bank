package com.lifelink;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MatchEngine {
    private List<Donor> donors = new ArrayList<>();

    public void addDonor(Donor donor) {
        if (donor == null) {
            throw new IllegalArgumentException("Donor cannot be null");
        }
        for (Donor existing : donors) {
            if (existing.getDonorId().equals(donor.getDonorId())) {
                throw new IllegalArgumentException("Donor ID " + donor.getDonorId() + " already exists.");
            }
        }
        if (donor.getAge() < 18 || donor.getAge() > 65) {
            throw new IllegalArgumentException("Age must be between 18 and 65");
        }
        if (donor.getWeightKg() < 45) {
            throw new IllegalArgumentException("Weight must be at least 45.kg ");
        }
        LocalDate today = LocalDate.now();
        LocalDate last = donor.getLastDonationDate();
        if (last != null) {
            long daysSinceLast = ChronoUnit.DAYS.between(last, today);
            int minDays = donor.getGender().equalsIgnoreCase("Female") ? 120 : 90;
            if (daysSinceLast < minDays) {
                throw new IllegalArgumentException("Minimum " + minDays + " days since last donation. Days since last: " + daysSinceLast);
            }
        }
        donors.add(donor);
    }

    public List<Donor> getDonors() {
        return new ArrayList<>(donors);
    }


    public List<Donor> findCompatibleDonors(String patientBloodGroup, String city) {
        List<Donor> matches = new ArrayList<>();

        String pb = patientBloodGroup.toUpperCase().trim();

        for (Donor donor : donors) {
            if (isBloodGroupCompatible(donor.getBloodGroup(), pb)) {
                if (!city.isEmpty() && !donor.getCity().equalsIgnoreCase(city)) {
                    continue;
                }
                matches.add(donor);
            }
        }
        return matches;
    }

    public List<Donor> findEmergencyDonors(String patientBloodGroup, String city) {
        List<Donor> matches = new ArrayList<>();

        String pb = patientBloodGroup.toUpperCase().trim();

        for (Donor donor : donors) {
            if (donor.isAvailable() && isBloodGroupCompatible(donor.getBloodGroup(), pb)) {
                if (!city.isEmpty() && !donor.getCity().equalsIgnoreCase(city)) {
                    continue;
                }
                matches.add(donor);
            }
        }
        return matches;
    }

    private boolean isBloodGroupCompatible(String donorBg, String patientBg) {
        donorBg = donorBg.toUpperCase().trim();
        patientBg = patientBg.toUpperCase().trim();

        List<String> validBloodGroups = List.of("O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-");

        if (!validBloodGroups.contains(patientBg)) {
            return false;
        }

        return switch (patientBg) {
            case "O+" -> donorBg.equals("O+") || donorBg.equals("O-");
            case "O-" -> donorBg.equals("O-");
            case "A+" -> donorBg.equals("A+") || donorBg.equals("A-") || donorBg.equals("O+") || donorBg.equals("O-");
            case "A-" -> donorBg.equals("A-") || donorBg.equals("O-");
            case "B+" -> donorBg.equals("B+") || donorBg.equals("B-") || donorBg.equals("O+") || donorBg.equals("O-");
            case "B-" -> donorBg.equals("B-") || donorBg.equals("O-");
            case "AB+" -> true;
            case "AB-" -> donorBg.endsWith("-");
            default -> false;
        };
    }
    public int getDonorCount() {
        return donors.size();
    }
}