package cn.edu.zucc.inventorymanagement.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.Worker;
import cn.edu.zucc.inventorymanagement.util.BusinessException;

public class FrmModifyPwd extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");

	private JLabel labelPwdOld = new JLabel("ԭ���룺");
	private JLabel labelPwd = new JLabel("�����룺");
	private JLabel labelPwd2 = new JLabel("�����룺");
	private JTextField edtPwdOld = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);

	public FrmModifyPwd(Frame f, String s, boolean b)
	{
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPwdOld);
		workPane.add(edtPwdOld);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 180);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCancel)
			this.setVisible(false);
		else if (e.getSource() == this.btnOk)
		{
			//result��¼�����ݿ�������ĶԱȽ��
			boolean result = false;
			WorkerManager wm = new WorkerManager();
			Worker worker = WorkerManager.currentWorker;
			try
			{
				result = wm.verifyWorker(
						WorkerManager.currentWorker.getWorkerName(),
						edtPwdOld.getText());
			}
			catch (SQLException e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (result == false)
			{
				JOptionPane.showMessageDialog(null, "ԭʼ�������", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if (String.valueOf(edtPwd.getPassword()).equals(
					String.valueOf(edtPwd2.getPassword())) == false)
			{
				JOptionPane.showMessageDialog(null, "�������벻һ��", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				worker.setWorkerPwd(String.valueOf(edtPwd.getPassword()));
				try
				{
					wm.modifyWorkerPassword(worker);
				}
				catch (BusinessException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "�����޸ĳɹ�", "�ɹ�",
						JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			}
		}

	}

}
