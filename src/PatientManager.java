public class PatientManager {
    private Doctor activeDoctor;
    private Patient activePatient;
    private Appointment activeAppointment;

    public void addDoctor(String name, int age,String gender, String spec) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age provided for Doctor.");
        }
        this.activeDoctor = new Doctor(name, age, gender, spec);
        System.out.println("Doctor registered successfully.");
    }

    public void addPatient(String name, int age,String gender, String sickness) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age provided for Patient.");
        }
        this.activePatient = new Patient(name, age, gender, sickness);
        System.out.println("Patient registered successfully.");
    }


    public void viewRecords() throws PatientSystemException {
        if (activeDoctor == null && activePatient == null) {
            throw new PatientSystemException("Records Empty: No Doctors or Patients found.");
        }
        if (activeDoctor != null) activeDoctor.performRole();
        if (activePatient != null) activePatient.performRole();
    }

    public void scheduleAppointment(String date) throws PatientSystemException {
        if (activeDoctor == null || activePatient == null) {
            throw new SchedulingException("Incomplete Data: Register both a Doctor and Patient first.");
        }
        this.activeAppointment = new Appointment(activeDoctor, activePatient, date);
        System.out.println("Appointment linked successfully!");
    }

    public void viewAppointments() throws PatientSystemException {
        if (activeAppointment == null) {
            throw new SchedulingException("No appointments found in the system.");
        }
        activeAppointment.displayAppointmentDetails();
    }
}