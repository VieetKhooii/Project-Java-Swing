package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.toedter.calendar.JDateChooser;
import model.Staff;
import service.StaffService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.List;

public class StaffsGUI extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel staffListPanel;
    private JTable staffTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane staffScrollPane;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel searchPanel;
    private JLabel staffPhoto;
    private JLabel lblNewLabel;
    private JTextField idStaffTxt;
    private JTextField nameStaffTxt;
    private JTextField addressStaffTxt;
    private JTextField phoneNumbTxt;
    private JTextField searchTxt;
    private JButton searchButton;
    private JPanel staffInfoPanel;
    private JButton addStaffBtn;
    private JButton fixStaffBtn;
    private JButton delStaffBtn;
    private JButton clearInfoBtn;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton maleRadioBtn;
    private JRadioButton femaleRadioBtn;
    private JDateChooser dateChooser = new JDateChooser();
    StaffService staffService = new StaffService();
    List<Staff> staffList = staffService.getAllStaff();
    private JComboBox<String> searchCbB;
	private JComboBox<String> sortCbB;
	Color defaultColor = new Color(0, 0, 0, 80);
	private JPanel btnPanel;
	String filePath;
	private JButton browsePhoto;
    /**
     * Create the panel.
     */
    public StaffsGUI() {
        init();
    }
    private void init() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1080, 700));

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        //End


        // Panel table
        contentField = new JPanel(null);

        this.add(contentField, BorderLayout.CENTER);

        //Panel table nhan vien
        staffListPanel = new JPanel(null);
        staffListPanel.setBackground(defaultColor);
        staffListPanel.setBounds(0, 380, 1080, 320);

        contentField.add(staffListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ"}, 0);
        staffTable = new MacOSStyleTable(detailTableModel);
        staffTable.setFont(new Font("Arial", Font.PLAIN, 14));
        staffTable.setDefaultRenderer(String.class, centerRenderer);
        staffTable.setRowHeight(30);
        for(int i = 0; i < 6; i++) {
            if(i == 1 || i == 5) {
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
                    idStaffTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    nameStaffTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    dateChooser.setDate((java.sql.Date) detailTableModel.getValueAt(row, 3));
                    phoneNumbTxt.setText(detailTableModel.getValueAt(row, 4).toString());
                    addressStaffTxt.setText(detailTableModel.getValueAt(row, 5).toString());
                    if (detailTableModel.getValueAt(row, 2).toString().equalsIgnoreCase(maleRadioBtn.getText())){
                        maleRadioBtn.setSelected(true);
                        femaleRadioBtn.setSelected(false);
                    }
                    else {
                        femaleRadioBtn.setSelected(true);
                        maleRadioBtn.setSelected(false);
                    }
                    for(Staff i : staffList) {
                    	if(i.getId() == Integer.parseInt(idStaffTxt.getText())) {
                    		staffPhoto.setIcon(new ImageIcon(i.getImage()));
                    		System.out.println(i.getImage());
                    		break;
                    	}                   	
                    }
                }
            }
        });

        staffScrollPane = new CustomScrollPane(staffTable);         
        staffScrollPane.setBounds(5, 5, 1070, 310);
        staffListPanel.add(staffScrollPane);

        staffInfoPanel = new JPanel();
        staffInfoPanel.setForeground(Color.YELLOW);
        //staffInfoPanel.setBackground(defaultColor);
        staffInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffInfoPanel.setBounds(0, 50, 765, 330);
        contentField.add(staffInfoPanel);
        staffInfoPanel.setLayout(null);

        staffPhoto = new JLabel("Ảnh");       
        staffPhoto.setBackground(new Color(255, 255, 255));
        staffPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        staffPhoto.setBounds(40, 50, 140, 140);
        staffPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffInfoPanel.add(staffPhoto);

        lblNewLabel = new JLabel("Thông tin nhân viên");
        lblNewLabel.setForeground(SystemColor.desktop);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(284, -1, 200, 40);
        staffInfoPanel.add(lblNewLabel);

        JLabel idsStaffLabel = new JLabel("Mã NV");
        idsStaffLabel.setForeground(SystemColor.desktop);
        idsStaffLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idsStaffLabel.setBounds(219, 50, 70, 30);
        staffInfoPanel.add(idsStaffLabel);

        idStaffTxt = new JTextField();
        idStaffTxt.setBackground(SystemColor.text);
        idStaffTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idStaffTxt.setColumns(10);
        idStaffTxt.setBounds(289, 50, 170, 30);
        idStaffTxt.setEditable(false);
        staffInfoPanel.add(idStaffTxt);

        nameStaffTxt = new JTextField();        
        nameStaffTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameStaffTxt.setColumns(10);
        nameStaffTxt.setBounds(549, 50, 170, 30);
        staffInfoPanel.add(nameStaffTxt);

        JLabel nameStaffLabel = new JLabel("Tên NV");
        nameStaffLabel.setForeground(SystemColor.desktop);
        nameStaffLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameStaffLabel.setBounds(479, 50, 70, 30);
        staffInfoPanel.add(nameStaffLabel);

        JLabel addressStaffLabel = new JLabel("Địa chỉ");
        addressStaffLabel.setForeground(SystemColor.desktop);
        addressStaffLabel.setFont(new Font("Arial", Font.BOLD, 13));
        addressStaffLabel.setBounds(479, 150, 70, 30);
        staffInfoPanel.add(addressStaffLabel);

        addressStaffTxt = new JTextField();
        addressStaffTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        addressStaffTxt.setColumns(10);
        addressStaffTxt.setBounds(549, 150, 170, 30);
        staffInfoPanel.add(addressStaffTxt);

        JLabel phoneNumbLabel = new JLabel("SĐT");
        phoneNumbLabel.setForeground(SystemColor.desktop);
        phoneNumbLabel.setFont(new Font("Arial", Font.BOLD, 13));
        phoneNumbLabel.setBounds(219, 150, 70, 30);
        staffInfoPanel.add(phoneNumbLabel);

        phoneNumbTxt = new JTextField();
        phoneNumbTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        phoneNumbTxt.setColumns(10);
        phoneNumbTxt.setBounds(289, 150, 170, 30);
        staffInfoPanel.add(phoneNumbTxt);
        
        btnPanel = new JPanel();
        btnPanel.setBounds(219, 284, 354, 35);
        staffInfoPanel.add(btnPanel);
        btnPanel.setLayout(null);

        browsePhoto = new JButton("Chọn");
        browsePhoto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fChooser = new JFileChooser();
				int response = fChooser.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					filePath = fChooser.getSelectedFile().getAbsolutePath();
					staffPhoto.setIcon(new ImageIcon(filePath));
				}
			}
        	
        });
        browsePhoto.setBounds(70, 200, 80, 30);
        staffInfoPanel.add(browsePhoto);
        
        addStaffBtn = new JButton("Thêm");
        addStaffBtn.setBounds(0, 0, 90, 35);
        btnPanel.add(addStaffBtn);
        addStaffBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        
        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.setBounds(264, 0, 90, 35);
        btnPanel.add(clearInfoBtn);
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffTable.clearSelection();
                idStaffTxt.setText(null);
                nameStaffTxt.setText(null);
                nameStaffTxt.setText(null);
                dateChooser.setDate(null);
                phoneNumbTxt.setText(null);
                addressStaffTxt.setText(null);
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        
        fixStaffBtn = new JButton("Cập nhật");
        fixStaffBtn.setBounds(88, 0, 90, 35);
        btnPanel.add(fixStaffBtn);
        fixStaffBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (idStaffTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 quyền và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            	else {
            		int decide = JOptionPane.showConfirmDialog(null, "Xác nhận thay đổi?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    //xoa o day
                    if(decide == 0) {
                        String gender="";
                        if (maleRadioBtn.isSelected()) {
                            gender = "Nam";
                        }
                        else {
                            gender = "Nữ";
                        }
                        java.sql.Date sqlDate = new Date(dateChooser.getDate().getTime());
                        staffService.modifyStaff(
                                Integer.parseInt(idStaffTxt.getText()),
                                nameStaffTxt.getText(),
                                addressStaffTxt.getText(),
                                phoneNumbTxt.getText(),
                                sqlDate,
                                gender, filePath
                        );
                        showTableStaff();
                        JOptionPane.showMessageDialog(null, "Thay đổi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
            	}              
            }
        });
        fixStaffBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        
        delStaffBtn = new JButton("Xóa");
        delStaffBtn.setBounds(175, 0, 90, 35);
        btnPanel.add(delStaffBtn);
        delStaffBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //xoa o day
                if (idStaffTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 nhân viên và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (decide == 0) {
                    	staffService.deleteStaff(Integer.parseInt(idStaffTxt.getText()));
                        clearInfoBtn.doClick();
                        showTableStaff();
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        delStaffBtn.setFont(new Font("Arial", Font.PLAIN, 13)); 
        //Function
        addStaffBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(nameStaffTxt.getText().equals("") || addressStaffTxt.getText().equals("") || phoneNumbTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else if (!phoneNumbTxt.getText().matches("0[123456789]{1}\\d{8}")) {
                	JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu từ 0 và đủ 10 số!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else if (!idStaffTxt.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Id nhân viên phải để trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String gender="";
                    if (maleRadioBtn.isSelected()) {
                        gender = "Nam";
                    }
                    else {
                        gender = "Nữ";
                    }
                    java.sql.Date sqlDate = new Date(dateChooser.getDate().getTime());
                    staffService.addStaff(chuanHoa(nameStaffTxt.getText()),
                    		chuanHoa(addressStaffTxt.getText()),
                            phoneNumbTxt.getText(),
                            sqlDate,
                            gender, filePath);
                    showTableStaff();
                    JOptionPane.showMessageDialog(null, "Đã thêm nhân viên!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        

        JLabel lblGiiTnh = new JLabel("Giới tính");
        lblGiiTnh.setForeground(SystemColor.desktop);
        lblGiiTnh.setFont(new Font("Arial", Font.BOLD, 13));
        lblGiiTnh.setBounds(219, 100, 70, 30);
        staffInfoPanel.add(lblGiiTnh);

        maleRadioBtn = new JRadioButton("Nam");
        buttonGroup.add(maleRadioBtn);
        maleRadioBtn.setBounds(289, 100, 60, 30);
        staffInfoPanel.add(maleRadioBtn);

        femaleRadioBtn = new JRadioButton("Nữ");  
        buttonGroup.add(femaleRadioBtn);
        femaleRadioBtn.setBounds(349, 100, 60, 30);
        staffInfoPanel.add(femaleRadioBtn);

        JLabel staffDateLabel = new JLabel("Ngày sinh");
        staffDateLabel.setForeground(SystemColor.desktop);
        staffDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        staffDateLabel.setBounds(479, 100, 70, 30);
        staffInfoPanel.add(staffDateLabel);

        dateChooser.setBounds(549, 100, 170, 30);
        staffInfoPanel.add(dateChooser);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBackground(new Color(0x007AFF));
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel staffLabel = new JLabel("NHÂN VIÊN");
        staffLabel.setForeground(Color.WHITE);
        staffLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(staffLabel);
        staffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staffLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        searchPanel = new JPanel();
        //searchPanel.setBackground(defaultColor);
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        searchPanel.setBounds(766, 50, 314, 330);
        contentField.add(searchPanel);
        searchPanel.setLayout(null);
        
        searchCbB = new JComboBox<String>();
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã nhân viên", "Tên nhân viên"}));
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setBounds(10, 64, 135, 40);
        searchPanel.add(searchCbB);
        
        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        searchTxt.setColumns(10);
        searchTxt.setBounds(155, 64, 149, 40);
        searchPanel.add(searchTxt);
        
        JLabel lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setForeground(SystemColor.desktop);
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(10, 134, 135, 40);
        searchPanel.add(lblSpXp);
        
        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Mã nhân viên giảm dần", "Tên nhân viên"}));
        sortCbB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Thực hiện hành động khi chọn một tùy chọn trong JComboBox
                
            }
        });        
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(155, 134, 149, 40);
        searchPanel.add(sortCbB);
        
        searchButton = new JButton("OK");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {  
        		showSearchResult(searchTxt.getText(), searchCbB.getSelectedItem().toString().trim(), sortCbB.getSelectedItem().toString().trim());
        	}
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(56, 269, 100, 50);
        searchPanel.add(searchButton);
        
        JLabel lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setForeground(SystemColor.desktop);
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(85, 0, 120, 40);
        searchPanel.add(lblTmKim);
        
        JButton rmSearchBtn = new JButton("Hủy");
        rmSearchBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchTxt.setText("");
        		showTableStaff();
        		sortCbB.setSelectedIndex(0);
        	}
        });
        rmSearchBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        rmSearchBtn.setBounds(155, 269, 100, 50);
        searchPanel.add(rmSearchBtn);
        //End
        showTableStaff();
        
    }
    
    private void showTableStaff(){
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        staffList = staffService.getAllStaff();
        for(Staff staff : staffList) {
            detailTableModel.addRow(new Object[] {
                    staff.getId(), staff.getName(), staff.getGender(), staff.getBirthDate(),
                    staff.getPhone(), staff.getAddress()
            });
        }
    }
    
    private void showSearchResult(String searchTxt, String optSearch, String optSort) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<Staff> searchResultList = staffService.getAllSearchResult(searchTxt, optSearch, optSort);
        for(Staff i : searchResultList) {
            detailTableModel.addRow(new Object[] {
                    i.getId(), i.getName(), i.getGender(), i.getBirthDate(),
                    i.getPhone(), i.getAddress()
            });
        }
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public String chuanHoa(String message) {
    	message = message.toLowerCase();
	    char[] charArray = message.toCharArray();
	    boolean foundSpace = true;
	    for(int i = 0; i < charArray.length; i++) {
	      if(Character.isLetter(charArray[i])) {   
	        if(foundSpace) {	          
	          charArray[i] = Character.toUpperCase(charArray[i]);
	          foundSpace = false;
	        }
	      }
	      else {
	        foundSpace = true;
	      }
	    }
	    message = String.valueOf(charArray);
	    return message;
    }
}