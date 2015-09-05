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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.inventorymanagement.control.EnterManager;
import cn.edu.zucc.inventorymanagement.control.ExchangeManager;
import cn.edu.zucc.inventorymanagement.model.Enter;

public class FrmEnter_Check extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JTextField edtLastTime = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private JLabel labelTime = new JLabel("ʱ�䣺");
	private JLabel labelSymbol = new JLabel("��");
	private JTextField edtNextTime = new JTextField(10);
	private JLabel labelListId = new JLabel("���ݺţ�");
	private JTextField edtListId = new JTextField(10);
	private JLabel labelHouseId = new JLabel("�ֿ�ţ�");
	private JTextField edtHouseId = new JTextField(10);
	
	private Object tblEnterTitle[] =
	{ "��ⵥ���", "�ֿ���", "���ϱ��", "���κ�", "��ⵥ���", "��ⵥ��", "�������", "���ʱ��", "�����",
			"��ע" };
	private Object tblEnterData[][];
	List<Enter> enterList = null;
	DefaultTableModel tabEnterModel = new DefaultTableModel();
	private JTable dataEnter = new JTable(tabEnterModel);

	private void reloadEnterTable()
	{
		// ���¼�����ⵥ��Ϣ
		enterList = (new EnterManager()).loadAllEnter();
		tblEnterData = new Object[enterList.size()][10];
		for (int i = 0; i < enterList.size(); i++)
		{
			tblEnterData[i][0] = enterList.get(i).getEnterId();
			tblEnterData[i][1] = enterList.get(i).getHouseId();
			tblEnterData[i][2] = enterList.get(i).getGoodsId();
			tblEnterData[i][3] = enterList.get(i).getBatchId();
			tblEnterData[i][4] = enterList.get(i).getSupplierId();
			tblEnterData[i][5] = enterList.get(i).getEnterPrice();
			tblEnterData[i][6] = enterList.get(i).getEnterAmount();
			tblEnterData[i][7] = enterList.get(i).getEnterTime();
			tblEnterData[i][8] = enterList.get(i).getWorkerId();
			tblEnterData[i][9] = enterList.get(i).getEnterNote();
		}
		tabEnterModel.setDataVector(tblEnterData, tblEnterTitle);
		this.dataEnter.validate();
		this.dataEnter.repaint();
	}
	
	private void searchEnterTable(String lastTime, String nextTime, int listId, int houseId)
	{
		// ���¼����ƿⵥ��Ϣ
		enterList = (new EnterManager()).searchEnter(lastTime, nextTime, listId, houseId);
		tblEnterData = new Object[enterList.size()][10];
		for (int i = 0; i < enterList.size(); i++)
		{
			tblEnterData[i][0] = enterList.get(i).getEnterId();
			tblEnterData[i][1] = enterList.get(i).getHouseId();
			tblEnterData[i][2] = enterList.get(i).getGoodsId();
			tblEnterData[i][3] = enterList.get(i).getBatchId();
			tblEnterData[i][4] = enterList.get(i).getSupplierId();
			tblEnterData[i][5] = enterList.get(i).getEnterPrice();
			tblEnterData[i][6] = enterList.get(i).getEnterAmount();
			tblEnterData[i][7] = enterList.get(i).getEnterTime();
			tblEnterData[i][8] = enterList.get(i).getWorkerId();
			tblEnterData[i][9] = enterList.get(i).getEnterNote();
		}
		tabEnterModel.setDataVector(tblEnterData, tblEnterTitle);
		this.dataEnter.validate();
		this.dataEnter.repaint();
	}

	public FrmEnter_Check(FrmEnterManager frmEnterManager, String s, boolean b)
	{
		super(frmEnterManager, s, b);

		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));

		toolBar.add(labelTime);
		toolBar.add(edtLastTime);
		toolBar.add(labelSymbol);
		toolBar.add(edtNextTime);
		toolBar.add(labelListId);
		toolBar.add(edtListId);
		toolBar.add(labelHouseId);
		toolBar.add(edtHouseId);
		toolBar.add(btnSearch);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		// ��ȡ��������
		this.reloadEnterTable();
		this.getContentPane().add(new JScrollPane(this.dataEnter),
				BorderLayout.CENTER);

		// ��Ļ������ʾ
		this.setSize(880, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

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
		if(e.getSource() == btnSearch)
		{
			int listId = 0, houseId = 0;
			if(edtListId.getText().equals(""))
			{
				listId = 0;
			}
			else
			{
				listId = Integer.valueOf(edtListId.getText());
			}
			if(edtHouseId.getText().equals(""))
			{
				houseId = 0;
			}
			else
			{
				houseId = Integer.valueOf(edtHouseId.getText());
			}
			searchEnterTable(edtLastTime.getText(), edtNextTime.getText(), listId, houseId);
		}
	}

}
