import java.util.Scanner;

public class PatientManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager manager = new PatientManager();
        int option = -1;

        do {
            try {
                System.out.println("\n--- Patient Management System ---");
                System.out.println("1. Register Doctor");
                System.out.println("2. Register Patient");
                System.out.println("3. View Registered Persons");
                System.out.println("4. Schedule Appointment");
                System.out.println("5. View Appointments");
                System.out.println("0. Exit");
                System.out.print("\nPlease Select: ");

                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Doctor Name: ");
                        String dName = scanner.nextLine();
                        System.out.print("Age: ");
                        int dAge = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Gender: ");
                        String gender = scanner.nextLine();
                        System.out.print("Specialty: ");
                        String spec = scanner.nextLine();

                        manager.addDoctor(dName, dAge, gender, spec);
                        break;

                    case 2:
                        // New: Capturing a Unique ID for the Map structure
                        System.out.print("Assign Patient ID (Unique): ");
                        String pId = scanner.nextLine();
                        System.out.print("Patient Name: ");
                        String pName = scanner.nextLine();
                        System.out.print("Age: ");
                        int pAge = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Gender: ");
                        String pGender = scanner.nextLine();
                        System.out.print("Sickness: ");
                        String sickness = scanner.nextLine();

                        // Passing ID to manager for Map.put() operation
                        manager.addPatient(pId, pName, pAge, pGender, sickness);
                        break;

                    case 3:
                        manager.viewRecords();
                        break;

                    case 4:
                        // Now we identify WHICH doctor and WHICH patient to link
                        System.out.print("Enter Doctor Name to assign: ");
                        String docToAssign = scanner.nextLine();
                        System.out.print("Enter Patient ID to assign: ");
                        String patIdToAssign = scanner.nextLine();
                        System.out.print("Enter Date (e.g: 30/05/2025): ");
                        String date = scanner.nextLine();

                        manager.scheduleAppointment(docToAssign, patIdToAssign, date);
                        break;

                    case 5:
                        manager.viewAppointments();
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid Option.");
                }
            } catch (Exception e) {
                // Meaningful error messages for robustness
                System.out.println("ALERT: " + e.getMessage());
                if (e instanceof java.util.InputMismatchException) {
                    scanner.nextLine(); // Clear buffer to prevent infinite loops
                }
            }
        } while (option != 0);
        scanner.close();
    }
}