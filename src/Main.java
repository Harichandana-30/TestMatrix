import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentService studentService =
                new StudentService();
        AdminService adminService =
                new AdminService();

        System.out.println("=================================");
        System.out.println("       Welcome to TestMatrix");
        System.out.println("=================================");

        System.out.println("1. Register Student");
        System.out.println("2. Login Student");
        System.out.println("3. Admin Login");
        System.out.print("Enter Choice: ");

        int choice = scanner.nextInt();

        switch (choice) {

    case 1:
        studentService.registerStudent();
        break;

    case 2:
        studentService.loginStudent();
        break;

    case 3:
        adminService.adminLogin();
        break;

    default:
        System.out.println("Invalid Choice");
}
    }
}