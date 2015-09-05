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

import cn.edu.zucc.inventorymanagement.control.ExitManager;
import cn.edu.zucc.inventorymanagement.control.ReturnManager;
import cn.edu.zucc.inventorymanagement.control.StoreManager;
import cn.edu.zucc.inventorymanagement.model.Exit;
import cn.edu.zucc.inventorymanagement.model.Return;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Store;

public class FrmReturnManager extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("退库");
	private Button btnCheck = new Button("盘查");

	private Object tblExitTitle[] =
		{ "出库单编号", "仓库编号", "物料编号", "批次号", "出库单编号", "出库单价", "出库数量", "出库时间", "出库人",
				"备注" };
		private Object tblExitData[][];
		DefaultTableModel tabExitModel = new DefaultTableModel();
		private JTable dataExit = new JTable(tabExitModel);

	List<Exit> exitList = null;

	private void reloadExitTabel()
	{
		exitList = (new ExitManager()).loadAllExit();

		tblExitData = new Object[exitList.size()][10];
		//加载出库清单
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

	public FrmReturnManager(Frame f, String s, boolean b)
	{
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnCheck);

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// 提取现有数据
		this.reloadExitTabel();
		this.getContentPane().add(new JScrollPane(this.dataExit),
				BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(880, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnCheck.addActionListener(this);
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
			int i = this.dataExit.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "请选出库记录", "提示",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			int exitId = Integer.parseInt(this.tblExitData[i][0]
					.toString());
			Exit exit = (new ExitManager()).searchExit(exitId);
			FrmReturn_Add dlg = new FrmReturn_Add(this, "退库", true, exit);
			dlg.setVisible(true);
			this.reloadExitTabel();
		}
		else if (e.getSource() == this.btnCheck)
		{
			FrmReturn_Check dlg = new FrmReturn_Check(this, "退库单盘查", true);
			dlg.setVisible(true);
		}

	}
}
