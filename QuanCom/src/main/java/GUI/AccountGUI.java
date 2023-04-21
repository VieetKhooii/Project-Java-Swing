package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.toedter.calendar.JDateChooser;
import model.Functions;
import model.Roles;
import model.User;
import service.RoleService;
import service.UserService;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AccountGUI extends JPanel implements MouseListener, ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel accListPanel;
    private JTable staffTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane accScrollPane;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel searchPanel;
    private JLabel lblNewLabel;
    private JTextField idAccTxt;
    private JTextField nameAccTxt;
    private JTextField passTxt;
    private JTextField emailTxt;
    private JTextField textField;
    private JButton searchButton;
    private JPanel staffInfoPanel;
    private JButton addAccBtn;
    private JButton clearInfoBtn;
    private JButton fixAccBtn;
    private JButton delAccBtn;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton blockedStatusRadioBtn;
    private JRadioButton noneStatusRadioBtn;
    private JComboBox<String> positioncbB = new JComboBox<>();
    UserService userService = new UserService();
    RoleService roleService = new RoleService();
    List<User> userList = userService.getAllUsers();
    List<Roles> rolesList = roleService.getAllRoles();
    /**
     * Create the panel.
     */
    public AccountGUI() {
        init();
    }
    private void init() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1080, 670));

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        //End


        // Panel table
        contentField = new JPanel(null);

        this.add(contentField, BorderLayout.CENTER);

        //Panel table nhan vien
        accListPanel = new JPanel(null);
        accListPanel.setBackground(new Color(30, 144, 255));
        accListPanel.setBounds(0, 380, 1080, 290);

        contentField.add(accListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã tài khoản", "Tên đăng nhập","Password", "Email", "Quyền"}, 0);
        staffTable = new JTable(detailTableModel);
        staffTable.setFont(new Font("Arial", Font.PLAIN, 14));
        staffTable.setDefaultRenderer(String.class, centerRenderer);
        staffTable.setRowHeight(30);
        for(int i = 0; i < 5; i++) {
            if(i == 1 || i == 4) {
                staffTable.getColumnModel().getColumn(i).setPreferredWidth(150);
                staffTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                //staffTable.getColumnModel().getColumn(i).setPreferredWidth(125);
                staffTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        ListSelectionModel listSelectionModel = staffTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = staffTable.getSelectedRow();
                if (row >= 0) {
                    idAccTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    nameAccTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    passTxt.setText(detailTableModel.getValueAt(row, 2).toString());
                    emailTxt.setText(detailTableModel.getValueAt(row, 3).toString());
                    for (int i=0; i<rolesList.size(); i++){
                        if (positioncbB.getItemAt(i).equalsIgnoreCase(detailTableModel.getValueAt(row, 4).toString())){
                            positioncbB.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });

        accScrollPane = new JScrollPane(staffTable);
        accScrollPane.setBounds(5, 5, 1070, 280);
        accListPanel.add(accScrollPane);

        staffInfoPanel = new JPanel();
        staffInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffInfoPanel.setBounds(0, 50, 800, 330);
        contentField.add(staffInfoPanel);
        staffInfoPanel.setLayout(null);

        lblNewLabel = new JLabel("Thông tin tài khoản");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(321, 0, 150, 40);
        staffInfoPanel.add(lblNewLabel);

        JLabel idAccLabel = new JLabel("Mã TK");
        idAccLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idAccLabel.setBounds(141, 90, 70, 30);
        staffInfoPanel.add(idAccLabel);

        idAccTxt = new JTextField();
        idAccTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idAccTxt.setColumns(10);
        idAccTxt.setBounds(211, 90, 170, 30);
        idAccTxt.setEditable(false);
        staffInfoPanel.add(idAccTxt);

        nameAccTxt = new JTextField();
        nameAccTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameAccTxt.setColumns(10);
        nameAccTxt.setBounds(471, 90, 170, 30);
        staffInfoPanel.add(nameAccTxt);

        JLabel nameAccLabel = new JLabel("Tên TK");
        nameAccLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameAccLabel.setBounds(401, 90, 70, 30);
        staffInfoPanel.add(nameAccLabel);

        JLabel passLabel = new JLabel("Mật khẩu");
        passLabel.setFont(new Font("Arial", Font.BOLD, 13));
        passLabel.setBounds(401, 140, 70, 30);
        staffInfoPanel.add(passLabel);

        passTxt = new JTextField();
        passTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        passTxt.setColumns(10);
        passTxt.setBounds(471, 140, 170, 30);
        staffInfoPanel.add(passTxt);

        addAccBtn = new JButton("Thêm");
        addAccBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!idAccTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Không được chọn tài khoản đã có sẵn để thêm! Khi thêm id phải để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameAccTxt.getText().equals("") || passTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int roleId = 0;
                    for (Roles roles : rolesList){
                        if (positioncbB.getSelectedItem().equals(roles.getName())){
                            roleId = roles.getId();
                            break;
                        }
                    }
                    userService.addUser(nameAccTxt.getText(), emailTxt.getText(), passTxt.getText(),roleId);
                    showTableAcc();
                    JOptionPane.showMessageDialog(null, "Đã thêm tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addAccBtn.setBounds(270, 280, 90, 35);
        staffInfoPanel.add(addAccBtn);

        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffTable.clearSelection();
                idAccTxt.setText(null);
                nameAccTxt.setText(null);
                emailTxt.setText(null);
                passTxt.setText(null);
                showTableAcc();
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        clearInfoBtn.setBounds(534, 280, 90, 35);
        staffInfoPanel.add(clearInfoBtn);

        fixAccBtn = new JButton("Cập nhật");
        fixAccBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idAccTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 tài khoản và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameAccTxt.getText().equals("") || passTxt.getText().equals("") || emailTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int modifyIdOfRole=0;
                        for (Roles role : rolesList){
                            if (positioncbB.getSelectedItem().equals(role.getName())){
                                modifyIdOfRole = role.getId();
                                break;
                            }
                        }
                    userService.modifyUser(Integer.parseInt(idAccTxt.getText()),nameAccTxt.getText(),emailTxt.getText(),passTxt.getText(),modifyIdOfRole);
                    showTableAcc();
                    JOptionPane.showMessageDialog(null, "Đã sửa quyền!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        fixAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        fixAccBtn.setBounds(358, 280, 90, 35);
        staffInfoPanel.add(fixAccBtn);

        delAccBtn = new JButton("Xóa");
        delAccBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idAccTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 tài khoản và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    //xoa o day
                    if(decide == 0) {
                        userService.deleteUser(Integer.parseInt(idAccTxt.getText()));
                        clearInfoBtn.doClick();
                        showTableAcc();
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        delAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delAccBtn.setBounds(445, 280, 90, 35);
        staffInfoPanel.add(delAccBtn);

        JLabel lblGiiTnh = new JLabel("Trạng thái");
        lblGiiTnh.setFont(new Font("Arial", Font.BOLD, 13));
        lblGiiTnh.setBounds(141, 140, 70, 30);
        staffInfoPanel.add(lblGiiTnh);

        blockedStatusRadioBtn = new JRadioButton("Khóa");
        buttonGroup.add(blockedStatusRadioBtn);
        blockedStatusRadioBtn.setBounds(211, 140, 60, 30);
        staffInfoPanel.add(blockedStatusRadioBtn);

        noneStatusRadioBtn = new JRadioButton("None");
        noneStatusRadioBtn.setSelected(true);
        buttonGroup.add(noneStatusRadioBtn);
        noneStatusRadioBtn.setBounds(271, 140, 60, 30);
        staffInfoPanel.add(noneStatusRadioBtn);

        JLabel staffDateLabel = new JLabel("Email");
        staffDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        staffDateLabel.setBounds(141, 190, 70, 30);
        staffInfoPanel.add(staffDateLabel);

        //positioncbB
        for (Roles roles : rolesList){
            positioncbB.addItem(roles.getName());
        }
        positioncbB.setBounds(471, 190, 90, 30);
        staffInfoPanel.add(positioncbB);

        JLabel lblChcV = new JLabel("Quyền");
        lblChcV.setFont(new Font("Arial", Font.BOLD, 13));
        lblChcV.setBounds(401, 190, 70, 30);
        staffInfoPanel.add(lblChcV);

        emailTxt = new JTextField();
        emailTxt.setBounds(211, 190, 170, 30);
        staffInfoPanel.add(emailTxt);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel staffLabel = new JLabel("TÀI KHOẢN");
        staffLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(staffLabel);
        staffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staffLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        searchPanel.setBounds(800, 50, 280, 330);
        contentField.add(searchPanel);
        searchPanel.setLayout(null);

        JLabel lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 15));
        lblTmKim.setBounds(80, 80, 120, 40);
        searchPanel.add(lblTmKim);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        textField.setColumns(10);
        textField.setBounds(55, 131, 170, 30);
        searchPanel.add(textField);

        searchButton = new JButton("OK");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(90, 180, 100, 30);
        searchPanel.add(searchButton);
        //End
        showTableAcc();


    }

    private void showTableAcc(){
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        userList = userService.getAllUsers();
        for(User user : userList) {
            System.out.println(user.getRoleId());
            String roleName = "";
            for (Roles roles : rolesList){
                if (user.getRoleId() == roles.getId()){
                    roleName = roles.getName();
                    break;
                }
            }
            detailTableModel.addRow(new Object[] {
                    user.getId(), user.getName(), user.getPassword(), user.getEmail(), roleName
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
