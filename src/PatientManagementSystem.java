import java.util.Scanner;

public class PatientManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager manager = new PatientManager();
        int option = -1;

        System.out.println("System initialized. Data loaded from local storage.");

        do {
            try {
                System.out.println("\n--- Patient Management System ---");
                System.out.println("1. Register Doctor\n2. Register Patient\n3. View Registered Persons");
                System.out.println("4. Schedule Appointment\n5. View Appointments\n0. Exit");
                System.out.print("\nPlease Select: ");

                if (!scanner.hasNextInt()) {
                    scanner.next();
                    continue;
                }

                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Doctor Name: ");
                        String dName = scanner.nextLine();
                        System.out.print("Age: ");
                        int dAge = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Gender: ");
                        String dGen = scanner.nextLine();
                        System.out.print("Specialty: ");
                        String spec = scanner.nextLine();
                        manager.addDoctor(dName, dAge, dGen, spec);
                        break;
                    case 2:
                        System.out.print("Patient Name: ");
                        String pName = scanner.nextLine();
                        System.out.print("Age: ");
                        int pAge = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Gender: ");
                        String pGen = scanner.nextLine();
                        System.out.print("Sickness: ");
                        String sickness = scanner.nextLine();
                        manager.addPatient(pName, pAge, pGen, sickness);
                        break;
                    case 3:
                        manager.viewRecords();
                        break;
                    case 4:
                        manager.viewRecords();
                        System.out.print("\nEnter Date (e.g: 30/05/2025): ");
                        String date = scanner.nextLine();
                        System.out.print("Enter Doctor Index: ");
                        int dIdx = scanner.nextInt();
                        System.out.print("Enter Patient Index: ");
                        int pIdx = scanner.nextInt();
                        scanner.nextLine();
                        manager.scheduleAppointment(date, dIdx, pIdx);
                        break;
                    case 5:
                        manager.viewAppointments();
                        break;
                    case 0:
                        System.out.println("Exiting and ensuring all data is safe.");
                        break;
                    default:
                        System.out.println("Invalid Option.");
                }
            } catch (Exception e) {
                System.out.println("ALERT: " + e.getMessage());

            }
        } while (option != 0);
        scanner.close();
    }
}