import model.Roles;
import service.RoleService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        UserService service = new UserService();
        System.out.println("Enter id to delete: ");
        int id = Integer.parseInt(scan.nextLine());
        boolean xoa = service.deleteUser(id);
        if (xoa) System.out.println("Successfully!");
        else System.out.println("Failed!");
    }
}
