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

import cn.edu.zucc.inventorymanagement.control.ExitManager;
import cn.edu.zucc.inventorymanagement.model.Exit;

public class FrmExit_Check extends JDialog
{
	private Object tblExitTitle[] =
	{ "出库单编号", "仓库编号", "物料编号", "批次号", "出库单编号", "出库单价", "出库数量", "出库时间", "出库人",
			"备注" };
	private Object tblExitData[][];
	DefaultTableModel tabExitModel = new DefaultTableModel();
	private JTable dataExit = new JTable(tabExitModel);

	List<Exit> exitList = null;

	private void reloadTable()
	{
		exitList = (new ExitManager()).loadAllExit();

		tblExitData = new Object[exitList.size()][10];
		//加载库存清单
		for (int i = 0; i < exitList.size(); i++)
		{
			tblExitData[i][0] = exitList.get(i).getExitId();
			tblExitData[i][1] = exitList.get(i).getHouseId();
			tblExitData[i][2] = exitList.get(i).getGoodsId();
			tblExitData[i][3] = exitList.get(i).getBatchId();
			tblExitData[i][4] = exitList.get(i).getCustomerId();
			tblExitData[i][5] = exitList.get(i).getExitPrice();
			tblExitData[i][6] = exitList.get(i).getExitAmount();
			tblExitData[i][7] = exitList.get(i).getExitTime();
			tblExitData[i][8] = exitList.get(i).getWorkerId();
			tblExitData[i][9] = exitList.get(i).getExitNote();
		}

		tabExitModel.setDataVector(tblExitData, tblExitTitle);
		this.dataExit.validate();
		this.dataExit.repaint();
	}

	public FrmExit_Check(FrmExitManager frmExitManager, String s, boolean b)
	{
		super(frmExitManager, s, b);

		// 提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataExit),
				BorderLayout.CENTER);

		// 屏幕居中显示
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
