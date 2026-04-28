# Patient Management System 

A simplified Java-based patient management system focusing on data persistence and oop principles. 

---

## File I/O & Persistence

- **Automatic Storage:** Saves Doctors, Patients, and Appointments into `.txt` files automatically upon registration.
- **Append Mode:** Implements `FileWriter(filename, true)` to add new entries without overwriting existing data.
- **Data Retrieval:** Uses `BufferedReader` to reload saved records into memory when the application starts.
- **Modern I/O:** Applies *try-with-resources* to ensure files are closed automatically and prevent memory leaks.

---

## OOP Principles Applied

- **Abstraction:** Uses an abstract class `Person` as a blueprint for all individuals in the system.
- **Inheritance:** `Doctor` and `Patient` extend the `Person` class to reuse common attributes (Name, Age, Gender).
- **Encapsulation:** Protects sensitive data using private fields with controlled access via getters and setters.
- **Polymorphism:** Overrides the `performRole()` method in subclasses to display role-specific behavior dynamically.

---

## Exception Handling

- **Custom Exceptions:** Includes `PatientSystemException` and `SchedulingException` for domain-specific error handling.
- **Data Validation:** Throws `IllegalArgumentException` for invalid inputs (e.g., negative ages or invalid selections).
- **Error Alerts:** Catches all `Exception` types and displays user-friendly **"SYSTEM ALERT"** messages instead of crashing.

