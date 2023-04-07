import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUser extends JFrame {
    public AddUser(){
        JLabel lb;
        JLabel[] label;

        JTextField[] textFields;

        lb = new JLabel("Sign up");
        lb.setBounds(20, 50, 450, 20);
        lb.setForeground(Color.red);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        label = new JLabel[7];
        label[0] = new JLabel("Full Name");
        label[0].setBounds(50,100,100,20);

        label[1] = new JLabel("Address");
        label[1].setBounds(50,100+50*1,100,20);

        label[2] = new JLabel("Phone number");
        label[2].setBounds(50,100+50*2,100,20);

        label[3] = new JLabel("User Name");
        label[3].setBounds(50,100+50*3,100,20);

        label[4] = new JLabel("Email");
        label[4].setBounds(50,100+50*4,100,20);

        label[5] = new JLabel("Password");
        label[5].setBounds(50,100+50*5,100,20);

        label[6] = new JLabel("Roles Id");
        label[6].setBounds(50,100+50*6,100,20);

        textFields = new JTextField[7];
        for (int i = 0; i < textFields.length; i++){
            textFields[i] = new JTextField();
            textFields[i].setBounds(150,100+i*50,200,30);
            add(textFields[i]);
            add(label[i]);
        }

        setLayout(null);
        add(lb);

        Button submit = new Button("Login");
        submit.setBounds(150,500,50,50);
        add(submit);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fullName = textFields[0].getText();
                String address = textFields[1].getText();
                String phoneNumber = textFields[2].getText();
                String userName = textFields[3].getText();
                String email = textFields[4].getText();
                String password = textFields[5].getText();
                int roleId = Integer.parseInt(textFields[6].getText());

                UserService userService = new UserService();
                boolean check = userService.addUser(userName,fullName,email,password,address,phoneNumber,roleId);
                if (check) System.out.println("Successfully!");
                else System.out.println("Failed!");
            }
        });
    }

    public static void main(String[] args) {
        AddUser addUser = new AddUser();
    }
}
