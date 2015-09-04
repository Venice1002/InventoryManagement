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

import cn.edu.zucc.inventorymanagement.control.DestoryManager;
import cn.edu.zucc.inventorymanagement.model.Destory;

public class FrmDestory_Check extends JDialog
{
	private Object tblDestoryTitle[] =
	{ "报废单编号", "仓库编号", "物料编号", "批次号", "报废时间", "报废数量", "报废人",  "备注" };
	private Object tblDestoryData[][];
	List<Destory> destoryList = null;
	DefaultTableModel tabDestoryModel = new DefaultTableModel();
	private JTable dataDestory = new JTable(tabDestoryModel);

	private void reloadTable()
	{
		// 重新加载入库单信息
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

	public FrmDestory_Check(FrmDestoryManager frmDestoryManager, String s, boolean b)
	{
		super(frmDestoryManager, s, b);

		// 提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataDestory),
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
