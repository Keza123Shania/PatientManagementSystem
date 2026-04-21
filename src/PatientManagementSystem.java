import java.util.Scanner;

public class PatientManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager manager = new PatientManager();
        int option = -1;

        do {
            try {
                System.out.println("\n--- Patient Management System ---");
                System.out.println("1. Register Doctor\n2. Register Patient\n3. View Registered Persons");
                System.out.println("4. Schedule Appointment\n5. View Appointments\n0. Exit");
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
                        System.out.print("Patient Name: ");
                        String pName = scanner.nextLine();
                        System.out.print("Age: ");
                        int pAge = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Gender: ");
                        String pGender = scanner.nextLine();
                        System.out.print("Sickness: ");
                        String sickness = scanner.nextLine();
                        manager.addPatient(pName, pAge, pGender, sickness);
                        break;
                    case 3:
                        manager.viewRecords();
                        break;
                    case 4:
                        System.out.print("Enter Date (e.g: 30/05/2025): ");
                        manager.scheduleAppointment(scanner.nextLine());
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
                System.out.println("ALERT: " + e.getMessage());
                if (e instanceof java.util.InputMismatchException) scanner.nextLine();
            }
        } while (option != 0);
        scanner.close();
    }
}