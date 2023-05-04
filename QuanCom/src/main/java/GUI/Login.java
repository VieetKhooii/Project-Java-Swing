package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.*;
import model.User;
import org.jdesktop.swingx.prompt.PromptSupport;
import service.UserService;

public class Login extends JFrame implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Font userFont = new Font("Arial", Font.PLAIN, 18);
    private JLabel logo;
    private JPanel loginField;
    private JPanel inside_wrap;
    private JLabel userIcon;
    private JTextField user;
    private JLabel passIcon;
    private JPasswordField pass;
    private JLabel messageLb;
    private JButton loginBtn;
    private JPanel windowBtn;
    private JPanel title;
    private UserService userService = new UserService();
    public static User userStatic = new User();
    List<User> userList = userService.getAllUsers();
    public Login() {
        init();
    }
    public void init() {
        this.setTitle("Login");
        getContentPane().setLayout(null);
        this.setResizable(false);
        //this.setUndecorated(true);
        //Logo
        logo = new JLabel();
        logo.setForeground(Color.PINK);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setFont(new Font("Ravie", Font.PLAIN, 26));
        logo.setText("QUÁN CƠM JAVA");
        logo.setBounds(50, 23, 300, 100);
        // Wrap login
        loginField = new JPanel();
        loginField.setLayout(null);
        loginField.setBounds(0, 20, 400, 700);
        loginField.setBackground(Color.white);

        // Be wraped login
        inside_wrap = new JPanel();
        inside_wrap.setLayout(null);
        inside_wrap.setBounds(50, 140, 300, 400);
        inside_wrap.setBackground(Color.WHITE);
        // Login label
        JLabel loginLabel = new JLabel();
        loginLabel.setText("Đăng nhập");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setBounds(100, 25, 150, 25);
        //Icon user
        userIcon = new JLabel();
        userIcon.setIcon(new ImageIcon("ImagesIcon/user2.png"));
        userIcon.setBounds(0, 80, 40, 40);
        // user textfield
        user = new RoundedJTextField(20);
        user.setBorder(null);
        user.setBounds(50, 80, 250, 39);
        PromptSupport.setPrompt("Tên đăng nhập", user);
        user.setFont(userFont);
        user.setBackground(Color.white);
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setBounds(50, 120, 250, 1);
        // Icon password
        passIcon = new JLabel();
        passIcon.setIcon(new ImageIcon("ImagesIcon/padlock.png"));
        passIcon.setBounds(0, 140, 40, 40);
        // password
        JPanel passPanel = new JPanel(new BorderLayout());
        passPanel.setBackground(Color.white);
        passPanel.setBounds(50, 140, 250, 39);
        pass = new JPasswordField(20);
        PromptSupport.setPrompt("Mật khẩu", pass);
        pass.setFont(userFont);
        pass.setBackground(Color.white);
        pass.setBorder(null);
        char defa = pass.getEchoChar();
        passPanel.add(pass, BorderLayout.CENTER);
        JToggleButton showHideButton = new JToggleButton(new ImageIcon("ImagesIcon/viewpass_2.png"), false); // Thay đổi đường dẫn đến tập tin hình ảnh
        showHideButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    pass.setEchoChar((char) 0);
                    showHideButton.setIcon(new ImageIcon("ImagesIcon/hidepass_2.png"));
                } else {
                    pass.setEchoChar(defa);
                    showHideButton.setIcon(new ImageIcon("ImagesIcon/viewpass_2.png"));
                    showHideButton.setBackground(Color.white);
                }
            }
        });
        showHideButton.setBackground(Color.white);
        showHideButton.setBorder(null);
        showHideButton.setContentAreaFilled(false);
        showHideButton.setFocusPainted(false);
        passPanel.add(showHideButton, BorderLayout.EAST);
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setBounds(50, 180, 250, 1);
        // thông báo
        messageLb = new JLabel("");
        messageLb.setFont(new Font("Arial", Font.PLAIN, 13));
        messageLb.setForeground(Color.red);
        messageLb.setBounds(0, 200, 200, 20);
        messageLb.setBackground(null);
        messageLb.setFocusable(false);
        // Login button
        loginBtn = new JButton("GO!");
        loginBtn.setFont(userFont);
        loginBtn.setBounds(90, 270, 120, 50);
        loginBtn.setBackground(new Color(255, 204, 153));
        loginBtn.setBorder(null);
        loginBtn.setFocusPainted(false);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                boolean check = userService.login(user.getText(), new String(pass.getPassword()));
                for (User user1 : userList){
                    if (user.getText().equals(user1.getName()) && new String (pass.getPassword()).equals(user1.getPassword())){
                        userStatic.setRoleId(user1.getRoleId());
                        break;
                    }
                }
                if (check) {         
                    GiaoDien a = new GiaoDien();
                    a.setVisible(true);
                    dispose();
                }
                else messageLb.setText("*Sai tên đăng nhập hoặc mật khẩu");
            }
        });
        loginBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginBtn.setBackground(new Color(255, 153, 51));
            }
            public void mouseExited(MouseEvent e) {
                loginBtn.setBackground(new Color(255, 204, 153));
            }
        });
        //
        inside_wrap.add(loginLabel);
        inside_wrap.add(user);
        inside_wrap.add(passPanel);
        inside_wrap.add(messageLb);
        inside_wrap.add(loginBtn);
        inside_wrap.add(userIcon);
        inside_wrap.add(passIcon);
        inside_wrap.add(separator1);
        inside_wrap.add(separator2);
        // End be wraped login
        loginField.add(inside_wrap);
        loginField.add(logo);
        //End Wraped login

        // Tao thanh tieu de
        this.setUndecorated(true);
        windowBtn = new JPanel();
        windowBtn.setLayout(new BorderLayout());
        windowBtn.setBounds(0, 0, 400, 32);
        this.setUndecorated(true);
        title = new TitlePanel(this);
        windowBtn.add(title, BorderLayout.NORTH);
        //End thanh tieu de

        getContentPane().add(windowBtn);
        getContentPane().add(loginField);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setSize(400, 632);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    public static void main(String[] args) {
        new Login();
    }

}
class RoundedJTextField extends JTextField {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RoundedJTextField(int columns) {
        super(columns);
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 14, 14);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
    }

    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 14, 14);
        return shape.contains(x, y);
    }
}
