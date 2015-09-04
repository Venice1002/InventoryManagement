package cn.edu.zucc.inventorymanagement.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.inventorymanagement.control.HouseManager;
import cn.edu.zucc.inventorymanagement.control.StoreManager;
import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Store;
import cn.edu.zucc.inventorymanagement.util.BaseException;

public class FrmMain extends JFrame implements ActionListener
{
	private JMenuBar menubar = new JMenuBar();;
	private JMenu menu_system = new JMenu("系统管理");
	private JMenu menu_houseAndGoods = new JMenu("仓库/物料管理");
	private JMenu menu_supAndCus = new JMenu("供应商/客户管理");
	private JMenu menu_store = new JMenu("库存管理");
	private JMenu menu_list = new JMenu("清单管理");
	private JMenu menu_static = new JMenu("查询统计");
	private JMenu menu_more = new JMenu("更多");

	private JMenuItem menuItem_WorkerManage = new JMenuItem("用户管理");
	private JMenuItem menuItem_HouseManage = new JMenuItem("仓库管理");
	private JMenuItem menuItem_GoodsManage = new JMenuItem("物料管理");
	private JMenuItem menuItem_SupplierManage = new JMenuItem("供应商管理");
	private JMenuItem menuItem_CunstomManage = new JMenuItem("客户管理");
	private JMenuItem menuItem_StoreCheck = new JMenuItem("库存盘查");
	private JMenuItem menuItem_EnterManage = new JMenuItem("入库管理");
	private JMenuItem menuItem_ExitManage = new JMenuItem("出库管理");
	private JMenuItem menuItem_ExchangeManage = new JMenuItem("移库管理");
	private JMenuItem menuItem_ReturnManage = new JMenuItem("退库管理");
	private JMenuItem menuItem_DestoryManage = new JMenuItem("报废管理");

	private JMenuItem menuItem_modifyPwd = new JMenuItem("密码修改");

	private JMenuItem menuItem_monthStatic = new JMenuItem("本月统计");
	private JMenuItem menuItem_timeStatic = new JMenuItem("时间统计");

	private FrmLogin dlgLogin = null;
	private JPanel statusBar = new JPanel();

	private Object tblHouseTitle[] =
	{ "编号", "仓库名称", "库存总量", "库存总金额", "仓库状态", "更多" };
	private Object tblHouseData[][];
	DefaultTableModel tabHouseModel = new DefaultTableModel();
	private JTable dataHouse = new JTable(tabHouseModel);

	private Object tblStore[] =
	{ "编号", "仓库编号", "物料编号", "批次号", "存储数量", "入库单价", "单位" };
	private Object tblStoreData[][];
	DefaultTableModel tabStoreModel = new DefaultTableModel();
	private JTable dataTableStore = new JTable(tabStoreModel);

	List<House> houseList = null;
	List<Store> storeList = null;

	private void reloadHouseTable()
	{
		// 重新加载仓库信息
		houseList = (new HouseManager()).loadAllHouse();
		tblHouseData = new Object[houseList.size()][6];
		for (int i = 0; i < houseList.size(); i++)
		{
			tblHouseData[i][0] = houseList.get(i).getHouseId();
			tblHouseData[i][1] = houseList.get(i).getHouseName();
			tblHouseData[i][2] = houseList.get(i).getTotalAmount();
			tblHouseData[i][3] = houseList.get(i).getTotalPrice();
			tblHouseData[i][4] = houseList.get(i).getHouseState();
			tblHouseData[i][5] = "更多";
		}
		tabHouseModel.setDataVector(tblHouseData, tblHouseTitle);
		this.dataHouse.validate();
		this.dataHouse.repaint();
	}

	private void reloadStoreTabel()
	{
		int i = this.dataHouse.getSelectedRow();
		if (i < 0)
		{
			JOptionPane.showMessageDialog(null, "请选仓库", "提示",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int houseId = Integer.parseInt(this.tblHouseData[i][0].toString());

		storeList = (new StoreManager()).loadStoreByHouseId(houseId);

		tblStoreData = new Object[storeList.size()][7];
		//加载收入条目
		for (i = 0; i < storeList.size(); i++)
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

	public FrmMain()
	{

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("库存管理系统");
		dlgLogin = new FrmLogin(this, "登陆", true);
		dlgLogin.setVisible(true);
		// FrmMonthStatic dlg = new FrmMonthStatic(this, "本月统计", true);
		// dlg.setVisible(true);
		// 菜单
		this.menu_system.add(this.menuItem_WorkerManage);
		this.menuItem_WorkerManage.addActionListener(this);

		this.menu_houseAndGoods.add(this.menuItem_HouseManage);
		this.menuItem_HouseManage.addActionListener(this);
		this.menu_houseAndGoods.add(this.menuItem_GoodsManage);
		this.menuItem_GoodsManage.addActionListener(this);
		this.menu_supAndCus.add(this.menuItem_SupplierManage);
		this.menuItem_SupplierManage.addActionListener(this);
		this.menu_supAndCus.add(this.menuItem_CunstomManage);
		this.menuItem_CunstomManage.addActionListener(this);

		this.menu_store.add(this.menuItem_StoreCheck);
		this.menuItem_StoreCheck.addActionListener(this);

		this.menu_list.add(this.menuItem_EnterManage);
		this.menuItem_EnterManage.addActionListener(this);
		this.menu_list.add(this.menuItem_ExitManage);
		this.menuItem_ExitManage.addActionListener(this);
		this.menu_list.add(this.menuItem_ExchangeManage);
		this.menuItem_ExchangeManage.addActionListener(this);
		this.menu_list.add(this.menuItem_ReturnManage);
		this.menuItem_ReturnManage.addActionListener(this);
		this.menu_list.add(this.menuItem_DestoryManage);
		this.menuItem_DestoryManage.addActionListener(this);

		this.menu_static.add(this.menuItem_monthStatic);
		this.menuItem_monthStatic.addActionListener(this);
		this.menu_static.add(this.menuItem_timeStatic);
		this.menuItem_timeStatic.addActionListener(this);

		this.menu_more.add(this.menuItem_modifyPwd);
		this.menuItem_modifyPwd.addActionListener(this);

		menubar.add(menu_system);
		menubar.add(menu_houseAndGoods);
		menubar.add(menu_supAndCus);
		menubar.add(menu_store);
		menubar.add(menu_list);
		menubar.add(menu_static);
		menubar.add(menu_more);
		this.setJMenuBar(menubar);

		this.getContentPane().add(new JScrollPane(this.dataHouse),
				BorderLayout.WEST);
		this.dataHouse.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				int i = FrmMain.this.dataHouse.getSelectedRow();
				if (i < 0)
				{
					return;
				}
				 FrmMain.this.reloadStoreTabel();
			}

		});
		this.getContentPane().add(new JScrollPane(this.dataTableStore),
				BorderLayout.CENTER);

		this.reloadHouseTable();
		// 状态栏
		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("您好! "
				+ WorkerManager.currentWorker.getWorkerName());// 您好！+登陆用户名
		statusBar.add(label);
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == menuItem_HouseManage)
		{
			FrmHouseManager dlg = new FrmHouseManager(this, "仓库管理", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_GoodsManage)
		{
			FrmGoodsManager dlg = new FrmGoodsManager(this, "物料管理", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_SupplierManage)
		{
			FrmSupplierManager dlg = new FrmSupplierManager(this, "供应商管理", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_CunstomManage)
		{
			FrmCustomerManager dlg = new FrmCustomerManager(this, "客户管理", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_EnterManage)
		{
			FrmEnterManager dlg = new FrmEnterManager(this, "入库管理", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ExitManage)
		{
			FrmExitManager dlg = new FrmExitManager(this, "出库管理", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ReturnManage)
		{
			FrmReturnManager dlg = new FrmReturnManager(this, "退库管理", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_DestoryManage)
		{
			FrmDestoryManager dlg = new FrmDestoryManager(this, "报废管理", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ExchangeManage)
		{
			FrmExchangeManager dlg = new FrmExchangeManager(this, "移库管理", true);
			dlg.setVisible(true);
		}
	}
}
