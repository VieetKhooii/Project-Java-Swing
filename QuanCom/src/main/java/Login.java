import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    public Login(){
        JLabel lb = new JLabel("Login!");

        lb.setBounds(20, 50, 450, 30);

        lb.setForeground(Color.red);

        lb.setFont(new Font("Serif", Font.BOLD, 20));

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 500);

        setLayout(null);

        add(lb);

        // Input

        JLabel lb1,lb2;
        JTextField tf1, tf2;

        lb1 = new JLabel("U_Name:");

        lb1.setBounds(50, 105, 100, 20);

        tf1 = new JTextField(50);

        tf1.setBounds(160, 105, 100, 20);

        lb2 = new JLabel("U_Pass:");

        lb2.setBounds(50, 130, 100, 20);

        tf2 = new JTextField(100);

        tf2.setBounds(160, 130, 200, 20);

        //Set TextField Editable False

        tf1.setEditable(true);

        tf2.setEditable(true);

        // add

        add(lb1);

        add(tf1);

        add(lb2);

        add(tf2);

        Button submit = new Button("Login");
        submit.setBounds(150,150,50,50);
        add(submit);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = tf1.getText();
                String password = tf2.getText();
                UserService userService = new UserService();
                boolean check = userService.login(email,password);
                if (check){
                    System.out.println("Correct");
                }
                else System.out.println("Failed!");
            }
        });
}

    public static void main(String[] args) {
        Login login = new Login();
    }
}
