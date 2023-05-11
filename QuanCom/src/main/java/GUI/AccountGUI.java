package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import model.Roles;
import model.Staff;
import model.User;
import service.RoleService;
import service.UserService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AccountGUI extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel accListPanel;
    private JTable UserTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane accScrollPane;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel searchPanel;
    private JLabel lblNewLabel;
    private JTextField idAccTxt;
    private JTextField nameAccTxt;
    private JTextField passTxt;
    private JTextField emailTxt;
    private JPanel UserInfoPanel;
    private JButton addAccBtn;
    private JButton clearInfoBtn;
    private JButton fixAccBtn;
    private JButton delAccBtn;
    public JComboBox<String> positioncbB = new JComboBox<>();
    private JLabel lblTmKim;
    private JComboBox<String> searchCbB;
    private JTextField searchTxt;
    private JLabel lblSpXp;
    private JComboBox<String> sortCbB;
    UserService userService = new UserService();
    RoleService roleService = new RoleService();
    List<User> userList = userService.getAllUsers();
    List<Roles> rolesList = roleService.getAllRoles();
    private JButton rmSearchBtn;
    private JButton searchButton;
    private JTextField idStaffTxt;
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
        accListPanel.setBackground(new Color(0, 0,0, 80));
        accListPanel.setBounds(0, 350, 1080, 320);

        contentField.add(accListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã tài khoản", "Tên đăng nhập","Mật khẩu", "Email", "Quyền", "Mã nhân viên"}, 0);
        UserTable = new MacOSStyleTable(detailTableModel);
        UserTable.setFont(new Font("Arial", Font.PLAIN, 14));
        UserTable.setDefaultRenderer(String.class, centerRenderer);
        UserTable.setRowHeight(30);
        for(int i = 0; i < 6; i++) {
            if(i == 1 || i == 3) {
                UserTable.getColumnModel().getColumn(i).setPreferredWidth(150);
                UserTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                //UserTable.getColumnModel().getColumn(i).setPreferredWidth(125);
                UserTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        ListSelectionModel listSelectionModel = UserTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = UserTable.getSelectedRow();
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
                    idStaffTxt.setText(detailTableModel.getValueAt(row, 5).toString());
                }
            }
        });

        accScrollPane = new CustomScrollPane(UserTable);
        accScrollPane.setBounds(5, 5, 1070, 310);
        accListPanel.add(accScrollPane);

        UserInfoPanel = new JPanel();
        UserInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        UserInfoPanel.setBounds(0, 50, 690, 300);
        contentField.add(UserInfoPanel);
        UserInfoPanel.setLayout(null);

        lblNewLabel = new JLabel("Thông tin tài khoản");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(274, 0, 150, 40);
        UserInfoPanel.add(lblNewLabel);

        JLabel idAccLabel = new JLabel("Mã TK");
        idAccLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idAccLabel.setBounds(79, 60, 70, 30);
        UserInfoPanel.add(idAccLabel);

        idAccTxt = new JTextField();
        idAccTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idAccTxt.setColumns(10);
        idAccTxt.setBounds(149, 60, 170, 30);
        idAccTxt.setEditable(false);
        UserInfoPanel.add(idAccTxt);

        nameAccTxt = new JTextField();
        nameAccTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameAccTxt.setColumns(10);
        nameAccTxt.setBounds(409, 60, 170, 30);
        UserInfoPanel.add(nameAccTxt);

        JLabel nameAccLabel = new JLabel("Tên TK");
        nameAccLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameAccLabel.setBounds(339, 60, 70, 30);
        UserInfoPanel.add(nameAccLabel);

        JLabel passLabel = new JLabel("Mật khẩu");
        passLabel.setFont(new Font("Arial", Font.BOLD, 13));
        passLabel.setBounds(339, 110, 70, 30);
        UserInfoPanel.add(passLabel);

        passTxt = new JTextField();
        passTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        passTxt.setColumns(10);
        passTxt.setBounds(409, 110, 170, 30);
        UserInfoPanel.add(passTxt);

        addAccBtn = new JButton("Thêm");
        addAccBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!idAccTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Không được chọn tài khoản đã có sẵn để thêm! Khi thêm id phải để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameAccTxt.getText().equals("") || passTxt.getText().equals("") || emailTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else if(!emailTxt.getText().matches("^\\w+[a-z0-9]*@gmail.com$")) {
                    JOptionPane.showMessageDialog(null, "Định dạng email sai (VD:username@gmail.com)!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int roleId = 0;
                    for (Roles roles : rolesList){
                        if (positioncbB.getSelectedItem().equals(roles.getName())){
                            roleId = roles.getId();
                            break;
                        }
                    }
                    userService.addUser(nameAccTxt.getText(), emailTxt.getText(), passTxt.getText(),roleId, Integer.parseInt(idStaffTxt.getText()));
                    showTableAcc();
                    JOptionPane.showMessageDialog(null, "Đã thêm tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addAccBtn.setBounds(160, 250, 90, 35);
        UserInfoPanel.add(addAccBtn);

        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserTable.clearSelection();
                idAccTxt.setText(null);
                nameAccTxt.setText(null);
                emailTxt.setText(null);
                passTxt.setText(null);
                showTableAcc();
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        clearInfoBtn.setBounds(424, 250, 90, 35);
        UserInfoPanel.add(clearInfoBtn);

        fixAccBtn = new JButton("Cập nhật");
        fixAccBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idAccTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 tài khoản và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameAccTxt.getText().equals("") || passTxt.getText().equals("") || emailTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else if(!emailTxt.getText().matches("^\\w+[a-z0-9]*@gmail.com$")) {
                    JOptionPane.showMessageDialog(null, "Định dạng email sai (VD:username@gmail.com)!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int modifyIdOfRole=0;
                    for (Roles role : rolesList){
                        if (positioncbB.getSelectedItem().equals(role.getName())){
                            modifyIdOfRole = role.getId();
                            break;
                        }
                    }
                    userService.modifyUser(Integer.parseInt(idAccTxt.getText()),nameAccTxt.getText(),emailTxt.getText(),passTxt.getText(),modifyIdOfRole, Integer.parseInt(idStaffTxt.getText()));
                    showTableAcc();
                    JOptionPane.showMessageDialog(null, "Đã sửa tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        fixAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        fixAccBtn.setBounds(248, 250, 90, 35);
        UserInfoPanel.add(fixAccBtn);

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
        delAccBtn.setBounds(335, 250, 90, 35);
        UserInfoPanel.add(delAccBtn);

        JLabel UserDateLabel = new JLabel("Email");
        UserDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        UserDateLabel.setBounds(79, 110, 70, 30);
        UserInfoPanel.add(UserDateLabel);

        //positioncbB
        for (Roles roles : rolesList){
            positioncbB.addItem(roles.getName());
        }
        positioncbB.setBounds(149, 161, 121, 30);
        UserInfoPanel.add(positioncbB);

        JLabel lblChcV = new JLabel("Quyền");
        lblChcV.setFont(new Font("Arial", Font.BOLD, 13));
        lblChcV.setBounds(79, 161, 70, 30);
        UserInfoPanel.add(lblChcV);

        emailTxt = new JTextField();
        emailTxt.setBounds(149, 110, 170, 30);
        UserInfoPanel.add(emailTxt);

        JLabel idStaffLb = new JLabel("Mã NV");
        idStaffLb.setFont(new Font("Arial", Font.BOLD, 13));
        idStaffLb.setBounds(339, 161, 70, 30);
        UserInfoPanel.add(idStaffLb);

        idStaffTxt = new JTextField();
        idStaffTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idStaffTxt.setColumns(10);
        idStaffTxt.setBounds(409, 161, 170, 30);
        UserInfoPanel.add(idStaffTxt);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBackground(new Color(0x007AFF));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel UserLabel = new JLabel("TÀI KHOẢN");
        UserLabel.setForeground(SystemColor.text);
        UserLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(UserLabel);
        UserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        UserLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        ///Tìm kiếm
        searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        searchPanel.setBounds(690, 50, 390, 300);
        contentField.add(searchPanel);
        searchPanel.setLayout(null);

        lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(136, 0, 120, 40);
        searchPanel.add(lblTmKim);

        searchCbB = new JComboBox<String>();
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã tài khoản", "Tên đăng nhập", "Quyền"}));
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setBounds(10, 64, 150, 40);
        searchPanel.add(searchCbB);

        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        searchTxt.setColumns(10);
        searchTxt.setBounds(189, 64, 191, 40);
        searchPanel.add(searchTxt);

        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(10, 134, 150, 40);
        searchPanel.add(lblSpXp);

        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Mã tài khoản giảm dần", "Tên đăng nhập", "Quyền"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(189, 134, 191, 40);
        searchPanel.add(sortCbB);

        rmSearchBtn = new JButton("Hủy");
        rmSearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTxt.setText("");
                showTableAcc();
                sortCbB.setSelectedIndex(0);
            }
        });
        rmSearchBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        rmSearchBtn.setBounds(189, 230, 100, 50);
        searchPanel.add(rmSearchBtn);

        searchButton = new JButton("OK");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSearchResult(searchTxt.getText(), searchCbB.getSelectedItem().toString().trim(), sortCbB.getSelectedItem().toString().trim());
            }
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(90, 230, 100, 50);
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
            String roleName = "";
            for (Roles roles : rolesList){
                if (user.getRoleId() == roles.getId()){
                    roleName = roles.getName();
                    break;
                }
            }
            detailTableModel.addRow(new Object[] {
                    user.getId(), user.getName(), user.getPassword(), user.getEmail(), roleName, user.getStaffId()
            });
        }
    }

    private void showSearchResult(String searchTxt, String optSearch, String optSort) {
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<User> searchResultList = userService.getAllSearchResult(searchTxt, optSearch, optSort);
        for(User user : searchResultList) {
            String roleName = "";
            for (Roles roles : rolesList){
                if (user.getRoleId() == roles.getId()){
                    roleName = roles.getName();
                    break;
                }
            }
            detailTableModel.addRow(new Object[] {
                    user.getId(), user.getName(), user.getPassword(), user.getEmail(), roleName, user.getStaffId()
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}