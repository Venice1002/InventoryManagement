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

import cn.edu.zucc.inventorymanagement.control.SupplierManager;
import cn.edu.zucc.inventorymanagement.model.Supplier;
import cn.edu.zucc.inventorymanagement.util.BaseException;

public class FrmSupplier_Modify extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelSupplierName = new JLabel("供应商名称：");
	private JLabel labelSupplierAddress = new JLabel("供应商地址：");
	private JLabel labelSupplierPhone = new JLabel("联系电话：");

	private JTextField edtSupplierName = new JTextField(20);
	private JTextField edtSupplierAddress = new JTextField(20);
	private JTextField edtSupplierPhone = new JTextField(20);
	
	private Supplier originSupplier = null;

	public FrmSupplier_Modify(FrmSupplierManager frmSupplierManager, String s, boolean b, Supplier supplier)
	{
		super(frmSupplierManager, "新建供应商", b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		//传入原始供应商信息
		originSupplier = supplier;
		
		workPane.setLayout(null);
		labelSupplierName.setBounds(50, 43, 100, 14);
		workPane.add(labelSupplierName);

		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		labelSupplierAddress.setBounds(50, 83, 100, 14);
		workPane.add(labelSupplierAddress);
		
		labelSupplierPhone.setBounds(50, 123, 100, 14);
		workPane.add(labelSupplierPhone);
		
		edtSupplierName.setBounds(150, 40, 100, 20);
		edtSupplierName.setText(originSupplier.getSupplierName());
		workPane.add(edtSupplierName);
		
		edtSupplierAddress.setBounds(150, 80, 100, 20);
		edtSupplierAddress.setText(originSupplier.getSupplierAddress());
		workPane.add(edtSupplierAddress);
		
		edtSupplierPhone.setBounds(150, 120, 100, 20);
		edtSupplierPhone.setText(originSupplier.getSupplierPhone());
		workPane.add(edtSupplierPhone);
		
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
			Supplier supplier = new Supplier();
			supplier.setSupplierId(originSupplier.getSupplierId());
			supplier.setSupplierName(edtSupplierName.getText());
			supplier.setSupplierAddress(edtSupplierAddress.getText());
			supplier.setSupplierPhone(edtSupplierPhone.getText());
			SupplierManager hm = new SupplierManager();
			try
			{
				hm.modifySupplier(supplier);
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
