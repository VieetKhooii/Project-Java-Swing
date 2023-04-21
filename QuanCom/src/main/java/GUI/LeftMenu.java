package GUI;

import model.Functions;
import model.User;
import service.RoleFuncService;

import javax.swing.*;

import java.awt.*;
import java.util.List;


public class LeftMenu extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //private static final long serialVersionUID = 1L;
    private RoleFuncService roleFuncService= new RoleFuncService();
    User user = Login.userStatic;
    public JButton[] funcBtn = new JButton[7];
    public List<Functions> funcNameList = roleFuncService.funcOfRole(user.getRoleId());
    int x = 0, y = 245;
    int w = 200, h = 65;
    private JLabel userLb;
    private JPanel nut;
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
        JButton dangNhap = new JButton("Trang chủ");
        dangNhap.setSize(100,45);
        JButton dangXuat = new JButton("Đăng xuất");
        dangNhap.setSize(100,45);

        nut.add(dangNhap);
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
//            funcBtn[i].addActionListener(this);
            this.add(funcBtn[i]);
            y = y + h;

        }
    }
    public void mouseEntered(){

//		for(int i = 0; i < funcBtn.length; i++){
//			funcBtn[i].setBackground(Color.orange);
//		}

    }

    public void mouseExited(){
        //for(int i = 0 ; i < funcBtn.length; i++){
        //funcBtn[i].setBackground(Color.DARK_GRAY);
        //}

    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // TODO Auto-generated method stub
//        for(int i = 0; i < funcBtn.length; i++) {
//            funcBtn[i].setEnabled(true);
//        }
//        for(int i = 0; i < funcBtn.length; i++) {
//            if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Đơn hàng")) {
//                GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func1");
//                funcBtn[i].setEnabled(false);
//            }
//            else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Nhập hàng")) {
//                GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func2");
//                funcBtn[i].setEnabled(false);
//            }
//            else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Nguyên liệu")) {
//                GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func4");
//                funcBtn[i].setEnabled(false);
//            }
//            else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Món ăn")) {
//                GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func3");
//                funcBtn[i].setEnabled(false);
//            }
//            else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Tài khoản")) {
//                GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func5");
//                funcBtn[i].setEnabled(false);
//            }
//            else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Nhân viên")) {
//                GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func6");
//                funcBtn[i].setEnabled(false);
//            }
//            else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Nhà cung cấp")) {
//                GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func7");
//                funcBtn[i].setEnabled(false);
//            }
//
//        }
//    }

    public static void main(String[] args) {
        LeftMenu leftMenu = new LeftMenu();
        JFrame a = new JFrame();
        a.setSize(200,700);
        a.setVisible(true);
        a.add(leftMenu);
    }





}
