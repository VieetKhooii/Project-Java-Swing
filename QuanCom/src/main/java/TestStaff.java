import bus.RoleFuncRepo;
import model.*;
import service.OrderService;
import service.StaffService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

public class TestStaff {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StaffService staffService = new StaffService();
        showStaff(staffService.getAllStaff());
    }

    public static void showStaff(List<Staff> list){
        for (Staff staff : list){
            System.out.println("Id: "+staff.getId()+" - Date: "+staff.getBirthDate());
        }
    }
}
