import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientManager {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();


    private final String DOCTOR_FILE = "doctors.txt";
    private final String PATIENT_FILE = "patients.txt";
    private final String APPOINTMENT_FILE = "appointments.txt";

    public PatientManager() {
        loadData();
    }

    public void addDoctor(String name, int age, String gender, String spec) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age provided for Doctor.");
        }
        Doctor doctor = new Doctor(name, age, gender, spec);
        doctors.add(doctor);
        saveToFile(DOCTOR_FILE, name + "," + age + "," + gender + "," + spec);
        System.out.println("Doctor registered and saved successfully.");
    }

    public void addPatient(String name, int age, String gender, String sickness) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age provided for Patient.");
        }
        Patient patient = new Patient(name, age, gender, sickness);
        patients.add(patient);
        saveToFile(PATIENT_FILE, name + "," + age + "," + gender + "," + sickness);
        System.out.println("Patient registered and saved successfully.");
    }

    public void scheduleAppointment(String date, int docIdx, int patIdx) throws PatientSystemException {
        if (docIdx < 0 || docIdx >= doctors.size() || patIdx < 0 || patIdx >= patients.size()) {
            throw new SchedulingException("Invalid Selection: Choose existing Doctor and Patient indices.");
        }

        Doctor doc = doctors.get(docIdx);
        Patient pat = patients.get(patIdx);
        Appointment appt = new Appointment(doc, pat, date);
        appointments.add(appt);


        saveToFile(APPOINTMENT_FILE, date + "," + doc.getName() + "," + pat.getName());
        System.out.println("Appointment scheduled and saved successfully!");
    }

    public void viewRecords() throws PatientSystemException {
        if (doctors.isEmpty() && patients.isEmpty()) {
            throw new PatientSystemException("Records Empty: No Doctors or Patients found.");
        }
        System.out.println("\n--- Registered Doctors ---");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.print("[" + i + "] ");
            doctors.get(i).performRole();
        }
        System.out.println("\n--- Registered Patients ---");
        for (int i = 0; i < patients.size(); i++) {
            System.out.print("[" + i + "] ");
            patients.get(i).performRole();
        }
    }

    public void viewAppointments() throws PatientSystemException {
        if (appointments.isEmpty()) {
            throw new SchedulingException("No appointments found in the system.");
        }
        System.out.println("\n--- All Appointments ---");
        for (Appointment a : appointments) {
            a.displayAppointmentDetails();
        }
    }



    private void saveToFile(String filename, String data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Critical Error: Could not save to " + filename);
        }
    }

    private void loadData() {
        loadDoctors();
        loadPatients();
    }

    private void loadDoctors() {
        File file = new File(DOCTOR_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    doctors.add(new Doctor(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading doctor data.");
        }
    }

    private void loadPatients() {
        File file = new File(PATIENT_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    patients.add(new Patient(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading patient data.");
        }
    }
}