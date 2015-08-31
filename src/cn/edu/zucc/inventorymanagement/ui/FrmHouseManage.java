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

import cn.edu.zucc.inventorymanagement.control.HouseManager;
import cn.edu.zucc.inventorymanagement.model.House;

public class FrmHouseManage extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("�½��ֿ�");
	private Button btnModify = new Button("�޸Ĳֿ�");
	private Button btnDelete = new Button("ɾ���ֿ�");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Object tblHouseTitle[] =
	{ "���", "�ֿ�����", "�ֿ��ַ", "�ֿ����", "�������", "����ܽ��", "�ֿ�״̬", "�ֿ�ȼ�", "�ϴ��̲�����", "��ϵ��",
			"��ϵ�绰", "�ֿⱸע" };
	private Object tblHouseData[][];
	List<House> houseList = null;
	DefaultTableModel tabHouseModel = new DefaultTableModel();
	private JTable dataHouse = new JTable(tabHouseModel);

	private void reloadTable()
	{
		// ���¼��زֿ���Ϣ
		houseList = (new HouseManager()).loadAllHouse();
		tblHouseData = new Object[houseList.size()][12];
		for (int i = 0; i < houseList.size(); i++)
		{
			tblHouseData[i][0] = houseList.get(i).getHouseId();
			tblHouseData[i][1] = houseList.get(i).getHouseName();
			tblHouseData[i][2] = houseList.get(i).getHouseAddress();
			tblHouseData[i][3] = houseList.get(i).getHouseType();
			tblHouseData[i][4] = houseList.get(i).getTotalAmount();
			tblHouseData[i][5] = houseList.get(i).getTotalPrice();
			tblHouseData[i][6] = houseList.get(i).getHouseState();
			tblHouseData[i][7] = houseList.get(i).getHouseLevel();
			tblHouseData[i][8] = houseList.get(i).getLastCheckDate();
			tblHouseData[i][9] = houseList.get(i).getLinkman();
			tblHouseData[i][10] = houseList.get(i).getLinkPhone();
			tblHouseData[i][11] = houseList.get(i).getHouseNote();
		}
		tabHouseModel.setDataVector(tblHouseData, tblHouseTitle);
		this.dataHouse.validate();
		this.dataHouse.repaint();
	}

	public FrmHouseManage(Frame f, String s, boolean b)
	{
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnDelete);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadTable();
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
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnSearch.addActionListener(this);
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
			FrmHouse_Add dlg = new FrmHouse_Add(this, "�½��ֿ�", true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnModify)
		{
			int i = this.dataHouse.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ��ֿ�", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			House house = this.houseList.get(i);
			FrmHouse_Modify dlg = new FrmHouse_Modify(this, "�޸Ĳֿ�", true, house);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnDelete)
		{
			int i = this.dataHouse.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ��ֿ�", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			House house = this.houseList.get(i);
			if (JOptionPane.showConfirmDialog(this,
					"ȷ��ɾ��" + house.getHouseName() + "��", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				(new HouseManager()).deleteHouse(house.getHouseId());
				this.reloadTable();
			}
		}
		else if (e.getSource() == this.btnSearch)
		{
			this.reloadTable();
		}

	}
}
