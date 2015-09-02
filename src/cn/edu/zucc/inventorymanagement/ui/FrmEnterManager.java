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

import cn.edu.zucc.inventorymanagement.control.EnterManager;
import cn.edu.zucc.inventorymanagement.model.Enter;

public class FrmEnterManager extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("入库");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private Object tblEnterTitle[] =
	{ "入库单编号", "仓库编号", "物料编号", "批次号", "入库单编号", "入库单价", "入库数量", "入库时间", "入库人",
			"备注" };
	private Object tblEnterData[][];
	List<Enter> enterList = null;
	DefaultTableModel tabEnterModel = new DefaultTableModel();
	private JTable dataEnter = new JTable(tabEnterModel);

	private void reloadTable()
	{
		// 重新加载入库单信息
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

	public FrmEnterManager(Frame f, String s, boolean b)
	{
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// 提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataEnter),
				BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(880, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
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
		if (e.getSource() == this.btnAdd)
		{
			FrmEnter_Add dlg = new FrmEnter_Add(this, "新建入库单", true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnSearch)
		{
			this.reloadTable();
		}

	}
}
