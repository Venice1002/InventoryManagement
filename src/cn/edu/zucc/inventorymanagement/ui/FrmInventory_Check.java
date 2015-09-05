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

import cn.edu.zucc.inventorymanagement.control.InventoryManager;
import cn.edu.zucc.inventorymanagement.control.ExchangeManager;
import cn.edu.zucc.inventorymanagement.model.Inventory;

public class FrmInventory_Check extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JTextField edtLastTime = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private JLabel labelTime = new JLabel("时间：");
	private JLabel labelSymbol = new JLabel("至");
	private JTextField edtNextTime = new JTextField(10);
	
	private Object tblInventoryTitle[] =
	{ "仓库编号", "物料编号", "入库数量", "出库数量", "移入数量", "移出数量", "退库数量", "报废数量", "存储数量",
			"平均成本" };
	private Object tblInventoryData[][];
	List<Inventory> inventoryList = null;
	DefaultTableModel tabInventoryModel = new DefaultTableModel();
	private JTable dataInventory = new JTable(tabInventoryModel);

	private void reloadInventoryTable()
	{
		// 重新加载入库单信息
		inventoryList = (new InventoryManager()).loadAllInventory();
		tblInventoryData = new Object[inventoryList.size()][10];
		for (int i = 0; i < inventoryList.size(); i++)
		{
			tblInventoryData[i][0] = inventoryList.get(i).getHouseId();
			tblInventoryData[i][1] = inventoryList.get(i).getGoodsId();
			tblInventoryData[i][2] = inventoryList.get(i).getEnterAmount();
			tblInventoryData[i][3] = inventoryList.get(i).getExitAmount();
			tblInventoryData[i][4] = inventoryList.get(i).getExchangeInAmount();
			tblInventoryData[i][5] = inventoryList.get(i).getExchangeOutAmount();
			tblInventoryData[i][6] = inventoryList.get(i).getReturnAmount();
			tblInventoryData[i][7] = inventoryList.get(i).getDestoryAmount();
			tblInventoryData[i][8] = inventoryList.get(i).getStoreAmount();
			tblInventoryData[i][9] = inventoryList.get(i).getAveragePrice();
		}
		tabInventoryModel.setDataVector(tblInventoryData, tblInventoryTitle);
		this.dataInventory.validate();
		this.dataInventory.repaint();
	}
	
	private void searchInventoryTable(String lastTime, String nextTime)
	{
		// 重新加载移库单信息
		InventoryManager im = new InventoryManager();
		im.clearInventory();
		im.createAllInventory(lastTime, nextTime);
		inventoryList = (new InventoryManager()).loadAllInventory();
		tblInventoryData = new Object[inventoryList.size()][10];
		for (int i = 0; i < inventoryList.size(); i++)
		{
			tblInventoryData[i][0] = inventoryList.get(i).getHouseId();
			tblInventoryData[i][1] = inventoryList.get(i).getGoodsId();
			tblInventoryData[i][2] = inventoryList.get(i).getEnterAmount();
			tblInventoryData[i][3] = inventoryList.get(i).getExitAmount();
			tblInventoryData[i][4] = inventoryList.get(i).getExchangeInAmount();
			tblInventoryData[i][5] = inventoryList.get(i).getExchangeOutAmount();
			tblInventoryData[i][6] = inventoryList.get(i).getReturnAmount();
			tblInventoryData[i][7] = inventoryList.get(i).getDestoryAmount();
			tblInventoryData[i][8] = inventoryList.get(i).getStoreAmount();
			tblInventoryData[i][9] = inventoryList.get(i).getAveragePrice();
		}
		tabInventoryModel.setDataVector(tblInventoryData, tblInventoryTitle);
		this.dataInventory.validate();
		this.dataInventory.repaint();
	}

	public FrmInventory_Check(Frame f, String s, boolean b)
	{
		super(f, s, b);

		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));

		toolBar.add(labelTime);
		toolBar.add(edtLastTime);
		toolBar.add(labelSymbol);
		toolBar.add(edtNextTime);
		toolBar.add(btnSearch);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		// 提取现有数据
		this.reloadInventoryTable();
		this.getContentPane().add(new JScrollPane(this.dataInventory),
				BorderLayout.CENTER);

		// 屏幕居中显示
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
			searchInventoryTable(edtLastTime.getText(), edtNextTime.getText());
		}
	}

}
