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

import cn.edu.zucc.inventorymanagement.control.ReturnManager;
import cn.edu.zucc.inventorymanagement.model.Return;

public class FrmReturn_Check extends JDialog
{
	private Object tblReturnTitle[] =
	{ "�˿ⵥ���", "�ֿ���", "���ϱ��", "���κ�", "�˿ⵥ���", "�˿ⵥ��", "�˿�����", "�˿�ʱ��", "�˿���",
			"��ע" };
	private Object tblReturnData[][];
	DefaultTableModel tabReturnModel = new DefaultTableModel();
	private JTable dataReturn = new JTable(tabReturnModel);

	List<Return> reList = null;

	private void reloadReturnTable()
	{
		reList = (new ReturnManager()).loadAllReturn();

		tblReturnData = new Object[reList.size()][10];
		//�����˿��嵥
		for (int i = 0; i < reList.size(); i++)
		{
			tblReturnData[i][0] = reList.get(i).getReturnId();
			tblReturnData[i][1] = reList.get(i).getHouseId();
			tblReturnData[i][2] = reList.get(i).getGoodsId();
			tblReturnData[i][3] = reList.get(i).getBatchId();
			tblReturnData[i][4] = reList.get(i).getCustomerId();
			tblReturnData[i][5] = reList.get(i).getReturnPrice();
			tblReturnData[i][6] = reList.get(i).getReturnAmount();
			tblReturnData[i][7] = reList.get(i).getReturnTime();
			tblReturnData[i][8] = reList.get(i).getWorkerId();
			tblReturnData[i][9] = reList.get(i).getReturnNote();
		}

		tabReturnModel.setDataVector(tblReturnData, tblReturnTitle);
		this.dataReturn.validate();
		this.dataReturn.repaint();
	}

	public FrmReturn_Check(FrmReturnManager frmReturnManager, String s, boolean b)
	{
		super(frmReturnManager, s, b);

		// ��ȡ��������
		this.reloadReturnTable();
		this.getContentPane().add(new JScrollPane(this.dataReturn),
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
