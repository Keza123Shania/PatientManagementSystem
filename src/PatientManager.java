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
        if (age < 0 || age > 120) throw new IllegalArgumentException("Invalid age.");

        Doctor doctor = new Doctor(name, age, gender, spec);
        doctors.add(doctor);


        String entry = String.format("%-20s | %-3d | %-10s | %-20s", name, age, gender, spec);
        String header = String.format("%-20s | %-3s | %-10s | %-20s\n%s",
                "NAME", "AGE", "GENDER", "SPECIALTY",
                "----------------------------------------------------------------------");

        saveToFileWithHeader(DOCTOR_FILE, header, entry);
        System.out.println("Doctor registered and logged in system records.");
    }

    public void addPatient(String name, int age, String gender, String sickness) {
        if (age < 0 || age > 120) throw new IllegalArgumentException("Invalid age.");

        Patient patient = new Patient(name, age, gender, sickness);
        patients.add(patient);

        String entry = String.format("%-20s | %-3d | %-10s | %-20s", name, age, gender, sickness);
        String header = String.format("%-20s | %-3s | %-10s | %-20s\n%s",
                "NAME", "AGE", "GENDER", "SICKNESS/REASON",
                "----------------------------------------------------------------------");

        saveToFileWithHeader(PATIENT_FILE, header, entry);
        System.out.println("Patient registered and logged in system records.");
    }

    public void scheduleAppointment(String date, int docIdx, int patIdx) throws PatientSystemException {

        int dIndex = docIdx - 1;
        int pIndex = patIdx - 1;

        if (dIndex < 0 || dIndex >= doctors.size() || pIndex < 0 || pIndex >= patients.size()) {
            throw new SchedulingException("Selection Out of Bounds: Please use the numbers shown in the records list.");
        }

        Doctor doc = doctors.get(dIndex);
        Patient pat = patients.get(pIndex);
        Appointment appt = new Appointment(doc, pat, date);
        appointments.add(appt);

        String entry = String.format("%-15s | Dr. %-15s | Patient: %-15s", date, doc.getName(), pat.getName());
        String header = String.format("%-15s | %-19s | %-15s\n%s",
                "DATE", "DOCTOR", "PATIENT",
                "----------------------------------------------------------------------");

        saveToFileWithHeader(APPOINTMENT_FILE, header, entry);
        System.out.println("Appointment successfully scheduled.");
    }

    public void viewRecords() throws PatientSystemException {
        if (doctors.isEmpty() && patients.isEmpty()) {
            throw new PatientSystemException("File Empty: No records found.");
        }

        System.out.println("\n--- MEDICAL STAFF DIRECTORY ---");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.print("[" + (i + 1) + "] ");
            doctors.get(i).performRole();
        }

        System.out.println("\n--- REGISTERED PATIENTS ---");
        for (int i = 0; i < patients.size(); i++) {
            System.out.print("[" + (i + 1) + "] ");
            patients.get(i).performRole();
        }
    }

    public void viewAppointments() throws PatientSystemException {
        if (appointments.isEmpty()) {
            throw new SchedulingException("No upcoming appointments scheduled.");
        }
        System.out.println("\n--- MASTER APPOINTMENT SCHEDULE ---");
        for (Appointment a : appointments) {
            a.displayAppointmentDetails();
        }
    }

    private void saveToFileWithHeader(String filename, String header, String data) {
        File file = new File(filename);
        boolean isNew = !file.exists() || file.length() == 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            if (isNew) {
                writer.write(header);
                writer.newLine();
            }
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("File Error: " + e.getMessage());
        }
    }

    private void loadData() {
        loadSimple(DOCTOR_FILE, "doctor");
        loadSimple(PATIENT_FILE, "patient");
    }

    private void loadSimple(String filename, String type) {
        File file = new File(filename);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (lineCount <= 2) continue;

                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    String gender = parts[2].trim();
                    String info = parts[3].trim();

                    if (type.equals("doctor")) doctors.add(new Doctor(name, age, gender, info));
                    else patients.add(new Patient(name, age, gender, info));
                }
            }
        } catch (Exception ignored) {}
    }
}