package cn.edu.zucc.inventorymanagement.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.inventorymanagement.control.CustomerManager;
import cn.edu.zucc.inventorymanagement.model.Customer;
import cn.edu.zucc.inventorymanagement.util.BaseException;

public class FrmCustomer_Add extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelCustomerName = new JLabel("客户名称：");
	private JLabel labelCustomerAddress = new JLabel("客户地址：");
	private JLabel labelCustomerPhone = new JLabel("联系电话：");

	private JTextField edtCustomerName = new JTextField(20);
	private JTextField edtCustomerAddress = new JTextField(20);
	private JTextField edtCustomerPhone = new JTextField(20);

	public FrmCustomer_Add(FrmCustomerManager frmCustomerManager, String s, boolean b)
	{
		super(frmCustomerManager, "新建客户", b);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		workPane.setLayout(null);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		labelCustomerName.setBounds(50, 43, 100, 14);
		workPane.add(labelCustomerName);
		
		labelCustomerAddress.setBounds(50, 83, 100, 14);
		workPane.add(labelCustomerAddress);
		
		labelCustomerPhone.setBounds(50, 123, 100, 14);
		workPane.add(labelCustomerPhone);
		
		edtCustomerName.setBounds(150, 40, 100, 20);
		workPane.add(edtCustomerName);
		
		edtCustomerAddress.setBounds(150, 80, 100, 20);
		workPane.add(edtCustomerAddress);
		
		edtCustomerPhone.setBounds(150, 120, 100, 20);
		workPane.add(edtCustomerPhone);
		
		this.setSize(360, 250);
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
			Customer customer = new Customer();
			customer.setCustomerName(edtCustomerName.getText());
			customer.setCustomerAddress(edtCustomerAddress.getText());
			customer.setCustomerPhone(edtCustomerPhone.getText());
			CustomerManager hm = new CustomerManager();
			try
			{
				hm.createCustomer(customer);
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
