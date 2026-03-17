# LifeLink - Emergency Blood Donor System

A Java console-based application to find compatible blood donors quickly during emergencies.

---

## ✨ Features

- Register new blood donors with proper validation
- Age (18-65), Weight (≥45kg) and donation gap checks
- Smart blood group compatibility logic
- Find all compatible donors for a patient
- **Emergency Match** - Shows only currently available donors
- City-wise filtering support
- Pre-loaded with sample donors

---


## 📁 Project Structure
```
Lifelink-Blood_bank/
├── .gitignore
├── README.md
└── src/
    └── com/
        └── lifelink/
            ├── Donor.java
            ├── MatchEngine.java
            └── LifeLinkApp.java
```

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/P-VARUN/Lifelink-Blood_bank.git
   ```

2. Open the project in **IntelliJ IDEA**.

3. Run the `LifeLinkApp.java` file (Right-click → Run 'LifeLinkApp.main()').

4. Use the menu options to explore the system.

---

## 🩸 Blood Group Compatibility

The system follows real medical blood transfusion rules:
- O- can donate to everyone
- AB+ can receive from everyone
- Proper handling of Rh factor (+ and -)

---

## 🛠 Technologies Used

- Java (JDK 8 or above)
- Object-Oriented Programming
- `java.time.LocalDate` for date handling

---

## 🔮 Future Enhancements

- Graphical User Interface (Swing/JavaFX)
- Persistent data storage (File/JSON/Database)
- Search and filter options
- Admin dashboard

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

**Made with ❤️ by Varun**
