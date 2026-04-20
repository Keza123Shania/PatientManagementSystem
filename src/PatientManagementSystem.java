public class PatientManagementSystem {
    public static void main(String[] args) {

        Doctor doctor = new Doctor("Dr. Shania", 50, "female","Pediatrics");
        Patient patient = new Patient("Myles", 5, "male","asthma");


        Appointment appointment = new Appointment(doctor, patient, "April 20, 2023");

        System.out.println("--- Patient Management System ---");

        doctor.performRole();
        patient.performRole();

        System.out.println("\n--- Schedule Check ---");
        appointment.displayAppointmentDetails();
    }
}
