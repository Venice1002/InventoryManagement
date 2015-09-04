package cn.edu.zucc.inventorymanagement.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.inventorymanagement.control.EnterManager;
import cn.edu.zucc.inventorymanagement.model.Enter;

public class FrmEnter_Check extends JDialog
{
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

	public FrmEnter_Check(FrmEnterManager frmEnterManager, String s, boolean b)
	{
		super(frmEnterManager, s, b);

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

		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				// System.exit(0);
			}
		});
	}

}
