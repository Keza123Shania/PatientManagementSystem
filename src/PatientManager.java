import java.util.*;

public class PatientManager {
    // I chose a List for appointments because I want to keep them in the order they were booked.
    // Coz, in a real clinic, a patient might visit multiple times for the same thing, so allowing duplicates is a must.
    // This represents a "One-to-Many" relationship: One system/doctor managing many visits.
    private List<Appointment> appointments = new ArrayList<>();

    // I'm using a Set for the doctor registry because I don't want the same doctor to be added twice.
    // It's a "Unique Relationship"—every doctor in this list should be a distinct professional.
    private Set<Doctor> doctorRegistry = new HashSet<>();

    // I went with a Map for patients because searching through a long list by hand is slow.
    // By using a "Key-Value" relationship (linking a unique ID to a Patient object),
    // I can find anyone's medical file instantly without looping.
    private Map<String, Patient> patientDatabase = new HashMap<>();

    public void addDoctor(String name, int age, String gender, String spec) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Wait, the age doesn't seem right.");
        }
        Doctor newDoc = new Doctor(name, age, gender, spec);

        // The Set's add() method handles the uniqueness check for me automatically.
        if (doctorRegistry.add(newDoc)) {
            System.out.println("Doctor " + name + " is now in the registry.");
        } else {
            System.out.println("Looks like this doctor is already registered!");
        }
    }

    public void addPatient(String id, String name, int age, String gender, String sickness) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age input is invalid.");
        }
        Patient newPat = new Patient(name, age, gender, sickness);

        // I'm using .put() to store the patient using their ID as the unique key.
        patientDatabase.put(id, newPat);
        System.out.println("Patient " + name + " saved under ID: " + id);
    }

    public void viewRecords() throws PatientSystemException {
        // Just checking if we actually have data to show.
        if (doctorRegistry.isEmpty() && patientDatabase.isEmpty()) {
            throw new PatientSystemException("Our database is currently empty.");
        }

        System.out.println("\n--- Registered Doctors ---");
        for (Doctor d : doctorRegistry) d.performRole();

        System.out.println("\n--- Registered Patients ---");
        for (Patient p : patientDatabase.values()) p.performRole();
    }

    public void scheduleAppointment(String doctorName, String patientId, String date) throws PatientSystemException {
        // Here I'm using the Map to quickly grab the patient by their ID.
        Patient pat = patientDatabase.get(patientId);

        // I have to find the right doctor in the Set before I can link them to the appointment.
        Doctor doc = null;
        for (Doctor d : doctorRegistry) {
            if (d.getName().equalsIgnoreCase(doctorName)) {
                doc = d;
                break;
            }
        }

        if (doc == null || pat == null) {
            throw new SchedulingException("We couldn't find that doctor or patient in our records.");
        }

        // Creating the link and adding it to our dynamic list.
        appointments.add(new Appointment(doc, pat, date));
        System.out.println("All set! The appointment is booked.");
    }

    public void viewAppointments() throws PatientSystemException {
        if (appointments.isEmpty()) {
            throw new SchedulingException("There are no appointments scheduled yet.");
        }
        for (Appointment app : appointments) {
            app.displayAppointmentDetails();
        }
    }
}