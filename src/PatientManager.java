import java.util.*; // Import List, Set, Map and their implementations

public class PatientManager {

    private List<Appointment> appointments = new ArrayList<>();


    private Set<Doctor> doctorRegistry = new HashSet<>();


    private Map<String, Patient> patientRec = new HashMap<>();

    public void addDoctor(String name, int age, String gender, String spec) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age provided for Doctor.");
        }
        Doctor newDoc = new Doctor(name, age, gender, spec);


        if (doctorRegistry.add(newDoc)) {
            System.out.println("Doctor registered successfully.");
        } else {
            System.out.println("Alert: This doctor is already in the registry.");
        }
    }

    public void addPatient(String id, String name, int age, String gender, String sickness) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age provided for Patient.");
        }
        Patient newPat = new Patient(name, age, gender, sickness);


        patientRec.put(id, newPat);
        System.out.println("Patient " + name + " registered successfully with ID: " + id);
    }

    public void viewRecords() throws PatientSystemException {
        if (doctorRegistry.isEmpty() && patientRec.isEmpty()) {
            throw new PatientSystemException("Records Empty: No Doctors or Patients found.");
        }

        System.out.println("\n--- Registered Doctors ---");
        for (Doctor d : doctorRegistry) d.performRole();

        System.out.println("\n--- Registered Patients ---");
        for (Patient p : patientRec.values()) p.performRole();
    }

    public void scheduleAppointment(String doctorName, String patientId, String date) throws PatientSystemException {
        Patient pat = patientRec.get(patientId);

        Doctor doc = null;
        for (Doctor d : doctorRegistry) {
            if (d.getName().equalsIgnoreCase(doctorName)) {
                doc = d;
                break;
            }
        }
        if (doc == null || pat == null) {
            throw new SchedulingException("Error: Doctor or Patient not found in system.");
        }
        appointments.add(new Appointment(doc, pat, date));
        System.out.println("Appointment linked successfully!");
    }

    public void viewAppointments() throws PatientSystemException {
        if (appointments.isEmpty()) {
            throw new SchedulingException("No appointments found in the system.");
        }
        for (Appointment app : appointments) {
            app.displayAppointmentDetails();
        }
    }
}