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

import cn.edu.zucc.inventorymanagement.control.EnterManager;
import cn.edu.zucc.inventorymanagement.control.HouseManager;
import cn.edu.zucc.inventorymanagement.control.StoreManager;
import cn.edu.zucc.inventorymanagement.model.Enter;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Store;
import cn.edu.zucc.inventorymanagement.util.BaseException;

public class FrmEnterManager extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("���");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Button btnCheck = new Button("�̲�");
	private Object tblHouseTitle[] =
	{ "���", "�ֿ�����", "�������", "����ܽ��", "�ֿ�״̬", "����" };
	private Object tblHouseData[][];
	List<House> houseList = null;
	DefaultTableModel tabHouseModel = new DefaultTableModel();
	private JTable dataHouse = new JTable(tabHouseModel);

	private void reloadHouseTable()
	{
		// ���¼��زֿ���Ϣ
		houseList = (new HouseManager()).loadAllHouse();
		tblHouseData = new Object[houseList.size()][10];
		for (int i = 0; i < houseList.size(); i++)
		{
			tblHouseData[i][0] = houseList.get(i).getHouseId();
			tblHouseData[i][1] = houseList.get(i).getHouseName();
			tblHouseData[i][2] = houseList.get(i).getTotalAmount();
			tblHouseData[i][3] = houseList.get(i).getTotalPrice();
			tblHouseData[i][4] = houseList.get(i).getHouseState();
			tblHouseData[i][5] = "����";
		}
		tabHouseModel.setDataVector(tblHouseData, tblHouseTitle);
		this.dataHouse.validate();
		this.dataHouse.repaint();
	}

	public FrmEnterManager(Frame f, String s, boolean b)
	{
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		toolBar.add(btnCheck);

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadHouseTable();
		this.getContentPane().add(new JScrollPane(this.dataHouse),
				BorderLayout.CENTER);

		// ��Ļ������ʾ
		this.setSize(880, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.btnCheck.addActionListener(this);
		
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
		if (e.getSource() == this.btnAdd)
		{
			int i = this.dataHouse.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ��ֿ�", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			int houseId = Integer.parseInt(this.tblHouseData[i][0].toString());
			House house = null;
			try
			{
				house = (new HouseManager()).searchHouseByHouseId(houseId);
			}
			catch (BaseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			FrmEnter_Add dlg = new FrmEnter_Add(this, "�½���ⵥ", true, house);
			dlg.setVisible(true);
			this.reloadHouseTable();
		}
		else if (e.getSource() == this.btnSearch)
		{
			this.reloadHouseTable();
		}
		else if (e.getSource() == btnCheck)
		{
			FrmEnter_Check dlg = new FrmEnter_Check(this, "��ⵥ�̲�", true);
			dlg.setVisible(true);
		}
	}
}
