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
import cn.edu.zucc.inventorymanagement.control.ReturnManager;
import cn.edu.zucc.inventorymanagement.model.Return;

public class FrmReturn_Check extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JTextField edtLastTime = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private JLabel labelTime = new JLabel("时间：");
	private JLabel labelSymbol = new JLabel("至");
	private JTextField edtNextTime = new JTextField(10);
	private JLabel labelListId = new JLabel("单据号：");
	private JTextField edtListId = new JTextField(10);
	private JLabel labelHouseId = new JLabel("仓库号：");
	private JTextField edtHouseId = new JTextField(10);

	private Object tblReturnTitle[] =
	{ "退库单编号", "仓库编号", "物料编号", "批次号", "退库单编号", "退库单价", "退库数量", "退库时间", "退库人",
			"备注" };
	private Object tblReturnData[][];
	DefaultTableModel tabReturnModel = new DefaultTableModel();
	private JTable dataReturn = new JTable(tabReturnModel);

	List<Return> reList = null;

	private void reloadReturnTable()
	{
		reList = (new ReturnManager()).loadAllReturn();

		tblReturnData = new Object[reList.size()][10];
		//加载退库清单
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

	private void searchReturnTable(String lastTime, String nextTime,
			int listId, int houseId)
	{
		// 重新加载移库单信息
		reList = (new ReturnManager()).searchReturn(lastTime, nextTime, listId,
				houseId);
		tblReturnData = new Object[reList.size()][10];
		//加载退库清单
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

	public FrmReturn_Check(FrmReturnManager frmReturnManager, String s,
			boolean b)
	{
		super(frmReturnManager, s, b);
		
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

		// 提取现有数据
		this.reloadReturnTable();
		this.getContentPane().add(new JScrollPane(this.dataReturn),
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
		if (e.getSource() == btnSearch)
		{
			int listId = 0, houseId = 0;
			if (edtListId.getText().equals(""))
			{
				listId = 0;
			}
			else
			{
				listId = Integer.valueOf(edtListId.getText());
			}
			if (edtHouseId.getText().equals(""))
			{
				houseId = 0;
			}
			else
			{
				houseId = Integer.valueOf(edtHouseId.getText());
			}
			searchReturnTable(edtLastTime.getText(), edtNextTime.getText(),
					listId, houseId);
		}
	}

}
