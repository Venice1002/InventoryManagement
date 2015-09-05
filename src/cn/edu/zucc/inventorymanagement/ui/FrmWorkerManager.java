package cn.edu.zucc.inventorymanagement.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.inventorymanagement.control.ReturnManager;
import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.Exit;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Supplier;
import cn.edu.zucc.inventorymanagement.model.Worker;

public class FrmWorkerManager extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Button btnModify = new Button("�޸�");

	private Object tblWorker[] =
	{ "���", "����", "��������", "��ϵ�绰", "����Ȩ��" };
	private Object tblWorkerData[][];
	DefaultTableModel tabWorkerModel = new DefaultTableModel();
	private JTable dataTableWorker = new JTable(tabWorkerModel);

	List<Worker> workerList = null;

	private void reloadWorkerTabel()
	{
		workerList = (new WorkerManager()).loadAllWorker();

		tblWorkerData = new Object[workerList.size()][5];
		//���ؿ���嵥
		for (int i = 0; i < workerList.size(); i++)
		{
			tblWorkerData[i][0] = workerList.get(i).getWorkerId();
			tblWorkerData[i][1] = workerList.get(i).getWorkerName();
			tblWorkerData[i][2] = workerList.get(i).getWorkerDepartment();
			tblWorkerData[i][3] = workerList.get(i).getWorkerPhone();
			tblWorkerData[i][4] = workerList.get(i).getWorkerPermission();
		}

		tabWorkerModel.setDataVector(tblWorkerData, tblWorker);
		this.dataTableWorker.validate();
		this.dataTableWorker.repaint();
	}

	public FrmWorkerManager(Frame f, String s, boolean b)
	{
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		toolBar.add(btnModify);

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadWorkerTabel();
		this.getContentPane().add(new JScrollPane(this.dataTableWorker),
				BorderLayout.CENTER);

		// ��Ļ������ʾ
		this.setSize(880, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnSearch.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				// System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnSearch)
		{
			this.reloadWorkerTabel();
		}
		else if (e.getSource() == this.btnModify)
		{
			int i = this.dataTableWorker.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ������Ա", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Worker worker = this.workerList.get(i);
			FrmWorker_Modify dlg = new FrmWorker_Modify(this, "�޸�", true, worker);
			dlg.setVisible(true);
			this.reloadWorkerTabel();
		}

	}
}
