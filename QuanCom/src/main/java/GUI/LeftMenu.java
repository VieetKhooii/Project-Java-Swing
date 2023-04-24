package GUI;

import model.Functions;
import model.User;
import service.RoleFuncService;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class LeftMenu extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //private static final long serialVersionUID = 1L;
    private RoleFuncService roleFuncService= new RoleFuncService();
    User user = Login.userStatic;
    public List<Functions> funcNameList = roleFuncService.funcOfRole(user.getRoleId());
    public JButton[] funcBtn = new JButton[funcNameList.size()];
    int x = 0, y = 245;
    int w = 200, h = 65;
    private JLabel userLb;
    private JPanel nut;
    static JButton dangXuat;
    public LeftMenu() {

        init();
    }
    private void init() {
        this.setPreferredSize(new Dimension(200, 700));
        this.setLayout(null);
        //Logo
        userLb = new JLabel();
        userLb.setIcon(new ImageIcon("ImagesIcon/anh-meo-cute.jpg"));
        userLb.setBounds(0, 0, 200, 200);

        nut = new JPanel(new GridLayout(1,2));
        nut.setBounds(0, 200, 200, 45);
        JButton home = new JButton("Trang chủ");
        home.setSize(100,45);
        dangXuat = new JButton("Đăng xuất");
        dangXuat.setSize(100,45);

        nut.add(home);
        nut.add(dangXuat);
        this.add(nut);
        this.add(userLb);
        /////////////////////
        for(int i = 0; i < funcNameList.size(); i++) {
            funcBtn[i] = new JButton(funcNameList.get(i).getName());
            funcBtn[i].setBounds(x, y, w, h);
            funcBtn[i].setOpaque(true);
            funcBtn[i].setFocusPainted(false);
            funcBtn[i].setBorder(null);
            funcBtn[i].setFont(new Font("Arial", Font.BOLD, 15));
            funcBtn[i].addActionListener(this);
            this.add(funcBtn[i]);
            y = y + h;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String a = "func";
        String b;
        for(int i = 0; i < funcBtn.length; i++) {
            funcBtn[i].setEnabled(true);
        }
        for(int i = 0; i < funcBtn.length; i++) {
            if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals(funcNameList.get(i).getName())) {
                b = a + String.valueOf(i + 1);
                GiaoDien.cardLayout.show(GiaoDien.switchPanel, b);
                funcBtn[i].setEnabled(false);
            }
        }
    }
}
