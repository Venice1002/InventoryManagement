package cn.edu.zucc.inventorymanagement.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.inventorymanagement.control.WorkerManager;

public class FrmLogin extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	// 设置默认按钮要使用JButton
	// private Button btnLogin = new Button("登陆");
	private JButton btnLogin = new JButton("登陆");
	private Button btnCancel = new Button("退出");
	private Button btnRegister = new Button("注册");

	private JLabel labelWorker = new JLabel("用户：");
	private JLabel labelPwd = new JLabel("密码：");
	private JTextField edtWorkerId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);

	public FrmLogin(Frame f, String s, boolean b)
	{
		super(f, s, b);
		// 将登陆按钮设置为默认按钮 可以使用回车键触发
		this.getRootPane().setDefaultButton(btnLogin);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelWorker);
		workPane.add(edtWorkerId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 140);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.btnRegister.addActionListener(this);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnLogin)
		{
			// 验证输入的用户名、密码是否正确
			try
			{
				if ((new WorkerManager()).verifyWorker(edtWorkerId.getText(),
						String.valueOf(edtPwd.getPassword())) == false)
				{
					JOptionPane.showMessageDialog(null, "用户名或密码错误", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					WorkerManager.currentWorker = (new WorkerManager())
							.searchWorker(edtWorkerId.getText());
					
					//判断用户操作权限 如果是1 则已被冻结
					if(WorkerManager.currentWorker.getWorkerPermission() == 1)
					{
						JOptionPane.showMessageDialog(null, "该账户已被冻结", "错误",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					this.setVisible(false);
				}
			}
			catch (HeadlessException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else if (e.getSource() == this.btnCancel)
		{
			System.exit(0);
		}
		else if (e.getSource() == this.btnRegister)
		{
			FrmRegister dlg = new FrmRegister(this, "注册", true);
			dlg.setVisible(true);
		}
	}

}