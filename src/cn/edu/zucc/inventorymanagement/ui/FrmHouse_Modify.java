package cn.edu.zucc.inventorymanagement.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPanel;

import cn.edu.zucc.inventorymanagement.control.HouseManager;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.util.BaseException;

public class FrmHouse_Modify extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelHouseName = new JLabel("仓库名称：");
	private JLabel labelHouseAddress = new JLabel("仓库地址：");
	private JLabel labelLinkman = new JLabel("联系人：");
	private JLabel labelLinkPhone = new JLabel("联系电话：");
	private JLabel labelHouseType = new JLabel("仓库类型 ：");
	private JLabel labelHouseState = new JLabel("仓库状态：");
	private JLabel labelHouseLevel= new JLabel("仓库等级：");
	private JLabel labelHouseNote = new JLabel("仓库备注：");

	private JTextField edtHouseName = new JTextField(20);
	private JTextField edtHouseAddress = new JTextField(20);
	private JTextField edtLinkman = new JTextField(20);
	private JTextField edtLinkPhone = new JTextField(20);
	private JTextField edtHouseType = new JTextField(20);
	private JTextField edtHouseState = new JTextField(20);
	private JTextField edtHouseLevel = new JTextField(20);
	private JTextField edtHouseNote = new JTextField(20);
	
	//原始仓库信息
	private House originHouse = null;

	public FrmHouse_Modify(FrmHouseManager frmHouseManager, String s, boolean b, House house)
	{
		super(frmHouseManager, "修改仓库", b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelHouseName.setBounds(24, 40, 70, 14);
		workPane.add(labelHouseName);
		edtHouseName.setBounds(89, 37, 100, 20);
		edtHouseName.setText(house.getHouseName());
		workPane.add(edtHouseName);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		//传入原始仓库的信息
		originHouse = house;
		
		labelHouseAddress.setBounds(24, 70, 70, 14);
		workPane.add(labelHouseAddress);
		
		labelLinkman.setBounds(24, 100, 70, 14);
		workPane.add(labelLinkman);
		
		labelLinkPhone.setBounds(24, 130, 70, 14);
		workPane.add(labelLinkPhone);
		
		labelHouseType.setBounds(218, 100, 70, 14);
		workPane.add(labelHouseType);
		
		labelHouseState.setBounds(218, 40, 70, 14);
		workPane.add(labelHouseState);
		
		labelHouseLevel.setBounds(218, 70, 70, 14);
		workPane.add(labelHouseLevel);
		
		labelHouseNote.setBounds(218, 130, 70, 14);
		workPane.add(labelHouseNote);
		
		edtHouseAddress.setBounds(89, 67, 100, 20);
		edtHouseAddress.setText(house.getHouseAddress());
		workPane.add(edtHouseAddress);
		
		edtLinkman.setBounds(89, 97, 100, 20);
		edtLinkman.setText(house.getLinkman());
		workPane.add(edtLinkman);
		
		edtLinkPhone.setBounds(89, 127, 100, 20);
		edtLinkPhone.setText(String.valueOf(house.getLinkPhone()));
		workPane.add(edtLinkPhone);
		
		edtHouseType.setBounds(283, 97, 100, 20);
		edtHouseType.setText(house.getHouseType());
		workPane.add(edtHouseType);
		
		edtHouseState.setBounds(283, 37, 100, 20);
		edtHouseState.setText(house.getHouseState());
		workPane.add(edtHouseState);
		
		edtHouseLevel.setBounds(283, 67, 100, 20);
		edtHouseLevel.setText(String.valueOf(house.getHouseLevel()));
		workPane.add(edtHouseLevel);
		
		edtHouseNote.setBounds(283, 127, 100, 20);
		edtHouseNote.setText(house.getHouseNote());
		workPane.add(edtHouseNote);
		this.setSize(440, 250);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCancel)
		{
			this.setVisible(false);
			return;
		}
		else if (e.getSource() == this.btnOk)
		{
			House house = new House();
			//原有的三个属性
			house.setHouseId(originHouse.getHouseId());
			house.setTotalAmount(originHouse.getTotalAmount());
			house.setTotalPrice(originHouse.getTotalPrice());
			//可以修改的其他属性
			house.setHouseName(edtHouseName.getText());
			house.setHouseAddress(edtHouseAddress.getText());
			house.setLinkman(edtLinkman.getText());
			house.setLinkPhone(Integer.valueOf(edtLinkPhone.getText()));
			house.setHouseType(edtHouseType.getText());
			house.setHouseState(edtHouseState.getText());
			house.setHouseLevel(Integer.valueOf(edtHouseLevel.getText()));
			house.setHouseNote(edtHouseNote.getText());
			HouseManager hm = new HouseManager();
			try
			{
				hm.modifyHouse(house);
			}
			catch (BaseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);

		}

	}
}
