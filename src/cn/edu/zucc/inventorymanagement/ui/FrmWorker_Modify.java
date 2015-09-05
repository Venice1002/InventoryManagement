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

import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.Worker;
import cn.edu.zucc.inventorymanagement.util.BaseException;

public class FrmWorker_Modify extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelWorkerName = new JLabel("姓名：");
	private JLabel labelWorkerDepartment = new JLabel("工作部门：");
	private JLabel labelWorkerPhone = new JLabel("联系电话：");
	private JLabel labelWorkerPemission = new JLabel("操作权限：");

	private JTextField edtWorkerName = new JTextField(20);
	private JTextField edtWorkerDepartment = new JTextField(20);
	private JTextField edtWorkerPhone = new JTextField(20);
	private JTextField edtWorkerPemission = new JTextField(20);
	
	private Worker originWorker = null;

	public FrmWorker_Modify(FrmWorkerManager frmWorkerManager, String s, boolean b, Worker worker)
	{
		super(frmWorkerManager, "修改工作人员信息", b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		//传入原始工作人员信息
		originWorker = worker;
		
		workPane.setLayout(null);
		labelWorkerName.setBounds(50, 25, 100, 14);
		workPane.add(labelWorkerName);

		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		labelWorkerDepartment.setBounds(50, 65, 100, 14);
		workPane.add(labelWorkerDepartment);
		
		labelWorkerPhone.setBounds(50, 105, 100, 14);
		workPane.add(labelWorkerPhone);
		
		labelWorkerPemission.setBounds(50, 145, 100, 14);
		workPane.add(labelWorkerPemission);
		
		edtWorkerName.setBounds(150, 22, 100, 20);
		edtWorkerName.setText(originWorker.getWorkerName());
		workPane.add(edtWorkerName);
		
		edtWorkerDepartment.setBounds(150, 62, 100, 20);
		edtWorkerDepartment.setText(originWorker.getWorkerDepartment());
		workPane.add(edtWorkerDepartment);
		
		edtWorkerPhone.setBounds(150, 102, 100, 20);
		edtWorkerPhone.setText(originWorker.getWorkerPhone());
		workPane.add(edtWorkerPhone);
		
		edtWorkerPemission.setBounds(150, 142, 100, 20);
		edtWorkerPemission.setText(String.valueOf(originWorker.getWorkerPermission()));
		workPane.add(edtWorkerPemission);
		
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
			Worker worker = new Worker();
			worker.setWorkerId(originWorker.getWorkerId());
			worker.setWorkerName(edtWorkerName.getText());
			worker.setWorkerDepartment(edtWorkerDepartment.getText());
			worker.setWorkerPhone(edtWorkerPhone.getText());
			worker.setWorkerPermission(Integer.valueOf(edtWorkerPemission.getText()));
			WorkerManager wm = new WorkerManager();
			try
			{
				wm.modifyWorker(worker);
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
