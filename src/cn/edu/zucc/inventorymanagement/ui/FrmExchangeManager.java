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

import cn.edu.zucc.inventorymanagement.control.ReturnManager;
import cn.edu.zucc.inventorymanagement.control.StoreManager;
import cn.edu.zucc.inventorymanagement.model.Exit;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Store;

public class FrmExchangeManager extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private Button btnExchange = new Button("移库");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private Button btnCheck = new Button("盘查");

	private Object tblStore[] =
	{ "编号", "仓库编号", "物料编号", "批次号", "存储数量", "入库单价", "单位" };
	private Object tblStoreData[][];
	DefaultTableModel tabStoreModel = new DefaultTableModel();
	private JTable dataTableStore = new JTable(tabStoreModel);

	List<Store> storeList = null;

	private void reloadStoreTabel()
	{
		storeList = (new StoreManager()).loadAllStore();

		tblStoreData = new Object[storeList.size()][7];
		//加载库存清单
		for (int i = 0; i < storeList.size(); i++)
		{
			tblStoreData[i][0] = storeList.get(i).getStoreId();
			tblStoreData[i][1] = storeList.get(i).getHouseId();
			tblStoreData[i][2] = storeList.get(i).getGoodsId();
			tblStoreData[i][3] = storeList.get(i).getBatchId();
			tblStoreData[i][4] = storeList.get(i).getStoreAmount();
			tblStoreData[i][5] = storeList.get(i).getStorePrice();
			tblStoreData[i][6] = storeList.get(i).getUnit();
		}

		tabStoreModel.setDataVector(tblStoreData, tblStore);
		this.dataTableStore.validate();
		this.dataTableStore.repaint();
	}

	public FrmExchangeManager(Frame f, String s, boolean b)
	{
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnExchange);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		toolBar.add(btnCheck);

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// 提取现有数据
		this.reloadStoreTabel();
		this.getContentPane().add(new JScrollPane(this.dataTableStore),
				BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(880, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnExchange.addActionListener(this);
		this.btnSearch.addActionListener(this);
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
		if (e.getSource() == this.btnExchange)
		{
			int i = this.dataTableStore.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "请选库存条目", "提示",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			int storeId = Integer.parseInt(this.tblStoreData[i][0]
					.toString());
			Store store = (new StoreManager()).searchStore(storeId);
			FrmExchange_Add dlg = new FrmExchange_Add(this, "移库", true, store);
			dlg.setVisible(true);
			this.reloadStoreTabel();
		}
		else if (e.getSource() == this.btnSearch)
		{
			this.reloadStoreTabel();
		}
		else if (e.getSource() == this.btnCheck)
		{
			FrmExchange_Check dlg = new FrmExchange_Check(this, "移库单盘查", true);
			dlg.setVisible(true);
		}

	}
}
