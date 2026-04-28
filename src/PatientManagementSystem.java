import java.util.Scanner;

public class PatientManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager manager = new PatientManager();
        int option = -1;

        System.out.println(">>> System Startup Successful.");


        do {
            try {
                System.out.println("\n==========================================");
                System.out.println("    PATIENT MANAGEMENT SYSTEM      ");
                System.out.println("==========================================");
                System.out.println("1. Register New Doctor");
                System.out.println("2. Register New Patient");
                System.out.println("3. View All Registered Records");
                System.out.println("4. Schedule Clinical Appointment");
                System.out.println("5. View Appointment Schedule");
                System.out.println("0. Secure Exit");
                System.out.print("\nEnter Selection: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Error: Please enter a valid number.");
                    scanner.next();
                    continue;
                }

                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("\n-- Registering New Doctor --");
                        System.out.print("Full Name: ");
                        String dName = scanner.nextLine();
                        System.out.print("Age: ");
                        int dAge = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Gender: ");
                        String dGen = scanner.nextLine();
                        System.out.print("Medical Specialty: ");
                        String spec = scanner.nextLine();
                        manager.addDoctor(dName, dAge, dGen, spec);
                        break;
                    case 2:
                        System.out.println("\n-- Registering New Patient --");
                        System.out.print("Full Name: ");
                        String pName = scanner.nextLine();
                        System.out.print("Age: ");
                        int pAge = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Gender: ");
                        String pGen = scanner.nextLine();
                        System.out.print("Primary Sickness/Reason: ");
                        String sickness = scanner.nextLine();
                        manager.addPatient(pName, pAge, pGen, sickness);
                        break;
                    case 3:
                        manager.viewRecords();
                        break;
                    case 4:
                        manager.viewRecords();
                        System.out.println("\n-- Scheduling Appointment --");
                        System.out.print("Enter Target Date (DD/MM/YYYY): ");
                        String date = scanner.nextLine();
                        System.out.print("Select Doctor ID (as shown above): ");
                        int dIdx = scanner.nextInt();
                        System.out.print("Select Patient ID (as shown above): ");
                        int pIdx = scanner.nextInt();
                        scanner.nextLine();
                        manager.scheduleAppointment(date, dIdx, pIdx);
                        break;
                    case 5:
                        manager.viewAppointments();
                        break;
                    case 0:
                        System.out.println("Finalizing background tasks...closed safely.");
                        break;
                    default:
                        System.out.println("Unknown command. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("SYSTEM ALERT: " + e.getMessage());
                if (scanner.hasNextLine()) scanner.nextLine();
            }
        } while (option != 0);
        scanner.close();
    }
}