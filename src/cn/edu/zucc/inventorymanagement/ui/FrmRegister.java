package cn.edu.zucc.inventorymanagement.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.Worker;
import cn.edu.zucc.inventorymanagement.util.BaseException;

public class FrmRegister extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");

	private JLabel labelWorker = new JLabel("用户名 ：");
	private JLabel labelPwd = new JLabel("密码  ：");
	private JLabel labelPwd2 = new JLabel("确认密码：");
	private JLabel labelDepartment = new JLabel("部门 ：");
	private JLabel labelPhone = new JLabel("联系电话 ：");
	private JTextField edtWorkerId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JTextField textDepartment = new JTextField(20);
	private JTextField textPhone = new JTextField(20);

	public FrmRegister(Dialog f, String s, boolean b)
	{
		super(f, "注册", b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelWorker.setBounds(21, 8, 70, 14);
		workPane.add(labelWorker);
		edtWorkerId.setBounds(110, 5, 166, 20);
		workPane.add(edtWorkerId);
		labelPwd.setBounds(21, 39, 70, 14);
		workPane.add(labelPwd);
		edtPwd.setBounds(110, 36, 166, 20);
		workPane.add(edtPwd);
		labelPwd2.setBounds(21, 70, 70, 14);
		workPane.add(labelPwd2);
		edtPwd2.setBounds(110, 67, 166, 20);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		labelDepartment.setBounds(21, 101, 70, 14);
		workPane.add(labelDepartment);
		
		textDepartment.setBounds(110, 98, 166, 20);
		workPane.add(textDepartment);
		textDepartment.setColumns(10);
		
		labelPhone.setBounds(21, 134, 70, 14);
		workPane.add(labelPhone);
		
		textPhone.setBounds(110, 131, 166, 20);
		workPane.add(textPhone);
		textPhone.setColumns(10);
		this.setSize(320, 230);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCancel)
			this.setVisible(false);
		else if (e.getSource() == this.btnOk)
		{
			// 补充注册相关代码
			String name = edtWorkerId.getText();
			String password = String.valueOf(edtPwd.getPassword());
			String phone =textPhone.getText();
			String department = textDepartment.getText();
			if (password.equals(String.valueOf(edtPwd2.getPassword())) != true)
			{
				JOptionPane.showMessageDialog(null, "两次输入密码不一致", "错误",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Worker worker = new Worker();
			worker.setWorkerName(name);
			//默认权限2为普通
			worker.setWorkerPermission(2);
			worker.setWorkerPwd(password);
			worker.setWorkerDepartment(department);
			worker.setWorkerPhone(phone);
			WorkerManager wm = new WorkerManager();
			wm.createWorker(worker);
			JOptionPane.showMessageDialog(null, "注册成功", "成功",
					JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}

	}
}
