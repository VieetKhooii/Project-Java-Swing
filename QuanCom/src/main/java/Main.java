import model.User;
import repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main extends JFrame {
    JLabel lb, lb1, lb2, lb3, lb4;

    JTextField tf1, tf2, tf3, tf4;
    public Main(){
        // Set up window
        lb = new JLabel("Fetching Student Information From Database");

        lb.setBounds(20, 50, 450, 20);

        lb.setForeground(Color.red);

        lb.setFont(new Font("Serif", Font.BOLD, 20));

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 500);

        lb1 = new JLabel("U_Name:");

        lb1.setBounds(50, 105, 100, 20);

        tf1 = new JTextField(50);

        tf1.setBounds(160, 105, 100, 20);

        lb2 = new JLabel("U_Mail:");

        lb2.setBounds(50, 135, 100, 20);

        tf2 = new JTextField(100);

        tf2.setBounds(160, 135, 200, 20);

        lb3 = new JLabel("U_Pass:");

        lb3.setBounds(50, 165, 100, 20);

        tf3 = new JTextField(50);

        tf3.setBounds(160, 165, 100, 20);

        lb4 = new JLabel("U_Country:");

        lb4.setBounds(50, 200, 100, 20);

        tf4 = new JTextField(50);

        tf4.setBounds(160, 200, 100, 20);

        setLayout(null);



        //Add components to the JFrame

        add(lb);

        add(lb1);

        add(tf1);

        add(lb2);

        add(tf2);

        add(lb3);

        add(tf3);

        add(lb4);

        add(tf4);



        //Set TextField Editable False

        tf1.setEditable(false);

        tf2.setEditable(false);

        tf3.setEditable(false);

        tf4.setEditable(false);

        UserRepository repository = new UserRepository();
        List<User> list = repository.getAllUser();
        tf1.setText(list.get(0).getEmail());
        tf2.setText(list.get(0).getPassword());
    }

    public static void main(String[] args) {
        Main javaTest = new Main();
    }
}