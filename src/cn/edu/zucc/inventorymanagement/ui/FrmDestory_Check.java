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

import cn.edu.zucc.inventorymanagement.control.DestoryManager;
import cn.edu.zucc.inventorymanagement.control.EnterManager;
import cn.edu.zucc.inventorymanagement.model.Destory;

public class FrmDestory_Check extends JDialog implements ActionListener
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
	
	private Object tblDestoryTitle[] =
	{ "���ϵ����", "�ֿ���", "���ϱ��", "���κ�", "����ʱ��", "��������", "���ϲ�����",  "��ע" };
	private Object tblDestoryData[][];
	List<Destory> destoryList = null;
	DefaultTableModel tabDestoryModel = new DefaultTableModel();
	private JTable dataDestory = new JTable(tabDestoryModel);

	private void reloadDestoryTable()
	{
		// ���¼��ر��ϵ���Ϣ
		destoryList = (new DestoryManager()).loadAllDestory();
		tblDestoryData = new Object[destoryList.size()][8];
		for (int i = 0; i < destoryList.size(); i++)
		{
			tblDestoryData[i][0] = destoryList.get(i).getDestoryId();
			tblDestoryData[i][1] = destoryList.get(i).getHouseId();
			tblDestoryData[i][2] = destoryList.get(i).getGoodsId();
			tblDestoryData[i][3] = destoryList.get(i).getBatchId();
			tblDestoryData[i][4] = destoryList.get(i).getDestoryTime();
			tblDestoryData[i][5] = destoryList.get(i).getDestoryAmount();
			tblDestoryData[i][6] = destoryList.get(i).getWorkerId();
			tblDestoryData[i][7] = destoryList.get(i).getDestoryNote();
		}
		tabDestoryModel.setDataVector(tblDestoryData, tblDestoryTitle);
		this.dataDestory.validate();
		this.dataDestory.repaint();
	}
	
	private void searchDestoryTable(String lastTime, String nextTime, int listId, int houseId)
	{
		// ���¼����ƿⵥ��Ϣ
		destoryList = (new DestoryManager()).searchDestory(lastTime, nextTime, listId, houseId);
		tblDestoryData = new Object[destoryList.size()][8];
		for (int i = 0; i < destoryList.size(); i++)
		{
			tblDestoryData[i][0] = destoryList.get(i).getDestoryId();
			tblDestoryData[i][1] = destoryList.get(i).getHouseId();
			tblDestoryData[i][2] = destoryList.get(i).getGoodsId();
			tblDestoryData[i][3] = destoryList.get(i).getBatchId();
			tblDestoryData[i][4] = destoryList.get(i).getDestoryTime();
			tblDestoryData[i][5] = destoryList.get(i).getDestoryAmount();
			tblDestoryData[i][6] = destoryList.get(i).getWorkerId();
			tblDestoryData[i][7] = destoryList.get(i).getDestoryNote();
		}
		tabDestoryModel.setDataVector(tblDestoryData, tblDestoryTitle);
		this.dataDestory.validate();
		this.dataDestory.repaint();
	}

	public FrmDestory_Check(FrmDestoryManager frmDestoryManager, String s, boolean b)
	{
		super(frmDestoryManager, s, b);
		
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
		this.reloadDestoryTable();
		this.getContentPane().add(new JScrollPane(this.dataDestory),
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
			searchDestoryTable(edtLastTime.getText(), edtNextTime.getText(), listId, houseId);
		}
	}

}
