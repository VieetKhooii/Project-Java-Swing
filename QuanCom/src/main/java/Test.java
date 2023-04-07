import model.Roles;
import service.RoleService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String userName = scan.nextLine();
        System.out.println("Enter password: ");
        String password = scan.nextLine();
        System.out.println("Enter full name: ");
        String fullName = scan.nextLine();
        System.out.println("Enter email: ");
        String email = scan.nextLine();
        System.out.println("Enter address: ");
        String address = scan.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scan.nextLine();
        System.out.println("Enter role ID: ");
        int id = Integer.parseInt(scan.nextLine());

        UserService userService = new UserService();
        boolean check = userService.modifyUser(3,userName, password, fullName, email, address, phone, id);
        if (check) System.out.println("Sc");
        else System.out.println("failed!");
    }
}
