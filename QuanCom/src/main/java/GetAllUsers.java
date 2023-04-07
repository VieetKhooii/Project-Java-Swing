import model.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GetAllUsers extends JFrame {
    JLabel lb;
    public GetAllUsers(){
        // Set up window
        lb = new JLabel("Fetching Student Information From Database");

        lb.setBounds(20, 50, 450, 20);

        lb.setForeground(Color.red);

        lb.setFont(new Font("Serif", Font.BOLD, 20));

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 500);

        setLayout(null);



        //Add components to the JFrame

        add(lb);

        UserService userService = new UserService();
        List<User> list = userService.getAllUsers();
        for (int i=0; i<list.size(); i++){
            JLabel lb1,lb2;
            JTextField tf1, tf2;

            lb1 = new JLabel("U_Name:");

            lb1.setBounds(50, 105+i*55, 100, 20);

            tf1 = new JTextField(50);

            tf1.setBounds(160, 105+i*55, 100, 20);

            lb2 = new JLabel("U_Pass:");

            lb2.setBounds(50, 130+i*55, 100, 20);

            tf2 = new JTextField(100);

            tf2.setBounds(160, 130+i*55, 200, 20);

            //Set TextField Editable False

            tf1.setEditable(false);

            tf2.setEditable(false);

            // add

            add(lb1);

            add(tf1);

            add(lb2);

            add(tf2);

            // content
            tf1.setText(list.get(i).getEmail());
            tf2.setText(list.get(i).getPassword());
        }
    }

    public static void main(String[] args) {
        GetAllUsers javaTest = new GetAllUsers();
    }
}