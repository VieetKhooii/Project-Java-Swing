import GUI.AccountAndRoleGUI;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1080,700));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AccountAndRoleGUI accountAndRoleGUI = new AccountAndRoleGUI();
        frame.add(accountAndRoleGUI);

//        StaffsGUI staffGUI = new StaffsGUI();
//        frame.add(staffGUI);
    }
}
