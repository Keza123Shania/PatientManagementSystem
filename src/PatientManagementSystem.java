import java.util.Scanner;

public class PatientManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager manager = new PatientManager();
        int option = -1;

        do {
            try {
                System.out.println("\n--- Patient Management Console ---");
                System.out.println("1. Register Doctor");
                System.out.println("2. Register Patient");
                System.out.println("3. View Records");
                System.out.println("4. Book Appointment");
                System.out.println("5. View All Bookings");
                System.out.println("0. Exit");
                System.out.print("\nWhat would you like to do? ");

                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Enter Name: "); String dName = scanner.nextLine();
                        System.out.print("Age: "); int dAge = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Gender: "); String gender = scanner.nextLine();
                        System.out.print("Specialty: "); String spec = scanner.nextLine();

                        manager.addDoctor(dName, dAge, gender, spec);
                        break;

                    case 2:
                        // I added a Patient ID prompt here so we can have a unique key for the Map.
                        System.out.print("Enter a unique Patient ID: "); String pId = scanner.nextLine();
                        System.out.print("Name: "); String pName = scanner.nextLine();
                        System.out.print("Age: "); int pAge = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Gender: "); String pGender = scanner.nextLine();
                        System.out.print("Sickness: "); String sickness = scanner.nextLine();

                        manager.addPatient(pId, pName, pAge, pGender, sickness);
                        break;

                    case 3:
                        manager.viewRecords();
                        break;

                    case 4:
                        // This case shows how we link different objects together using IDs.
                        System.out.print("Which doctor is attending? "); String docToAssign = scanner.nextLine();
                        System.out.print("What is the patient's ID? "); String patIdToAssign = scanner.nextLine();
                        System.out.print("Enter Date (DD/MM/YYYY): "); String date = scanner.nextLine();

                        manager.scheduleAppointment(docToAssign, patIdToAssign, date);
                        break;

                    case 5:
                        manager.viewAppointments();
                        break;

                    case 0:
                        System.out.println("Shutting down the system...");
                        break;

                    default:
                        System.out.println("That's not a valid option, try again.");
                }
            } catch (Exception e) {
                // This keeps the program from crashing if someone types a letter instead of a number.
                System.out.println("Oops! Something went wrong: " + e.getMessage());
                if (e instanceof java.util.InputMismatchException) {
                    scanner.nextLine(); // Clear the wrong input
                }
            }
        } while (option != 0);

        scanner.close();
    }
}