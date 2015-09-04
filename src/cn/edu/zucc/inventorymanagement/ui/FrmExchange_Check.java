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

import cn.edu.zucc.inventorymanagement.control.ExchangeManager;
import cn.edu.zucc.inventorymanagement.model.Exchange;

public class FrmExchange_Check extends JDialog
{
	private Object tblExchangeTitle[] =
	{ "移库单编号", "原仓库编号", "现仓库编号", "物料编号", "批次号", "移库数量", "移库时间", "移库人",
			"备注" };
	private Object tblExchangeData[][];
	List<Exchange> exchangeList = null;
	DefaultTableModel tabExchangeModel = new DefaultTableModel();
	private JTable dataExchange = new JTable(tabExchangeModel);

	private void reloadExchangeTable()
	{
		// 重新加载移库单信息
		exchangeList = (new ExchangeManager()).loadAllExchange();
		tblExchangeData = new Object[exchangeList.size()][10];
		for (int i = 0; i < exchangeList.size(); i++)
		{
			tblExchangeData[i][0] = exchangeList.get(i).getExchangeId();
			tblExchangeData[i][1] = exchangeList.get(i).getLastHouseId();
			tblExchangeData[i][2] = exchangeList.get(i).getNextHouseId();
			tblExchangeData[i][3] = exchangeList.get(i).getGoodsId();
			tblExchangeData[i][4] = exchangeList.get(i).getBatchId();
			tblExchangeData[i][5] = exchangeList.get(i).getExchangeAmount();
			tblExchangeData[i][6] = exchangeList.get(i).getExchangeTime();
			tblExchangeData[i][7] = exchangeList.get(i).getWorkerId();
			tblExchangeData[i][8] = exchangeList.get(i).getExchangeNote();
		}
		tabExchangeModel.setDataVector(tblExchangeData, tblExchangeTitle);
		this.dataExchange.validate();
		this.dataExchange.repaint();
	}

	public FrmExchange_Check(FrmExchangeManager frmExchangeManager, String s, boolean b)
	{
		super(frmExchangeManager, s, b);

		// 提取现有数据
		this.reloadExchangeTable();
		this.getContentPane().add(new JScrollPane(this.dataExchange),
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
