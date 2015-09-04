package cn.edu.zucc.inventorymanagement.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.inventorymanagement.control.ExitManager;
import cn.edu.zucc.inventorymanagement.control.ReturnManager;
import cn.edu.zucc.inventorymanagement.control.GoodsManager;
import cn.edu.zucc.inventorymanagement.control.HouseManager;
import cn.edu.zucc.inventorymanagement.control.StoreManager;
import cn.edu.zucc.inventorymanagement.control.CustomerManager;
import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.Exit;
import cn.edu.zucc.inventorymanagement.model.Goods;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Store;
import cn.edu.zucc.inventorymanagement.model.Customer;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.DbException;

public class FrmExit_Add extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelBatchId = new JLabel("批次号：");
	private JLabel labelExitAmount = new JLabel("出库数量：");
	private JLabel labelExitPrice = new JLabel("出库单价：");
	private JLabel labelExitTime = new JLabel("出库时间：");
	private JLabel labelHouse = new JLabel("仓库：");
	private JLabel labelGoods = new JLabel("物料：");
	private JLabel labelUnit = new JLabel("单位：");
	private JLabel labelExitNote = new JLabel("备注：");
	private JLabel labelWorker = new JLabel("出库人：");
	private JLabel labelCustomer = new JLabel("客户：");

	private JLabel labelNote = new JLabel("时间不填默认为当前时刻   格式如2015-01-01");
	private JTextField edtExitAmount = new JTextField(20);
	private JTextField edtExitTime = new JTextField(20);
	private JTextField edtBatchId = new JTextField(20);
	private JTextField edtExitPrice = new JTextField(20);
	private JTextField edtUnit = new JTextField(20);
	private JTextField edtExitNote = new JTextField(20);
	private JTextField edtWorker = new JTextField(20);
	private JComboBox cmbHouse;
	private JComboBox cmbGoods;
	private JComboBox cmbCustomer;
	private House house = null;
	private Goods goods = null;
	private List<Customer> customerList = null;

	/**
	 * @wbp.parser.constructor
	 */
	public FrmExit_Add(FrmExitManager frmExitManager, String s, boolean b,
			Store store)
	{
		super(frmExitManager, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);

		labelBatchId.setBounds(30, 18, 70, 14);
		workPane.add(labelBatchId);

		edtBatchId.setBounds(112, 15, 120, 20);
		edtBatchId.setText(String.valueOf(store.getBatchId()));
		edtBatchId.setEnabled(false);
		workPane.add(edtBatchId);

		labelExitAmount.setBounds(30, 80, 70, 14);
		workPane.add(labelExitAmount);

		edtExitAmount.setBounds(112, 77, 120, 20);
		workPane.add(edtExitAmount);

		labelExitTime.setBounds(30, 118, 70, 14);
		workPane.add(labelExitTime);

		edtExitTime.setBounds(112, 112, 120, 20);
		//		edtExitTime.setText(new java.sql.Timestamp(System
		//						.currentTimeMillis()).toString());
		workPane.add(edtExitTime);

		labelNote.setBounds(30, 143, 400, 14);
		workPane.add(labelNote);

		labelHouse.setBounds(30, 171, 55, 14);
		workPane.add(labelHouse);

		cmbHouse = new JComboBox();
		cmbHouse.setBounds(71, 168, 80, 20);
		cmbGoods = new JComboBox();
		cmbGoods.setBounds(202, 168, 80, 20);
		workPane.add(cmbHouse);
		labelGoods.setBounds(165, 171, 55, 14);
		workPane.add(labelGoods);
		workPane.add(cmbGoods);
		this.getContentPane().add(workPane, BorderLayout.CENTER);

		labelExitPrice.setBounds(30, 49, 70, 14);
		workPane.add(labelExitPrice);

		edtExitPrice.setBounds(112, 46, 120, 20);
		workPane.add(edtExitPrice);

		labelUnit.setBounds(277, 18, 70, 14);
		workPane.add(labelUnit);

		edtUnit.setBounds(341, 15, 120, 20);
		edtUnit.setText(store.getUnit());
		edtUnit.setEnabled(false);
		workPane.add(edtUnit);

		labelExitNote.setBounds(277, 49, 70, 14);
		workPane.add(labelExitNote);

		edtExitNote.setBounds(341, 46, 120, 20);
		workPane.add(edtExitNote);

		edtWorker.setBounds(341, 77, 120, 20);
		edtWorker.setText(WorkerManager.currentWorker.getWorkerName());
		workPane.add(edtWorker);

		labelWorker.setBounds(277, 80, 70, 14);
		workPane.add(labelWorker);

		labelCustomer.setBounds(292, 171, 55, 14);
		workPane.add(labelCustomer);

		cmbCustomer = new JComboBox();
		cmbCustomer.setBounds(341, 168, 80, 20);
		workPane.add(cmbCustomer);
		this.setSize(500, 300);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.cmbHouse.addActionListener(this);
		this.cmbGoods.addActionListener(this);
		this.cmbCustomer.addActionListener(this);

		//初始化下拉菜单
		try
		{
			house = (new HouseManager()).searchHouseByHouseId(store
					.getHouseId());
			cmbHouse.addItem(house.getHouseName());

			goods = (new GoodsManager()).searchGoodsByGoodsId(store
					.getGoodsId());
			cmbGoods.addItem(goods.getGoodsName());
		}
		catch (BaseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customerList = (new CustomerManager()).loadAllCustomer();
		cmbCustomer.addItem("");
		for (int i = 0; i < customerList.size(); i++)
		{
			cmbCustomer.addItem(customerList.get(i).getCustomerName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCancel)
		{
			this.setVisible(false);
			return;
		}
		else if (e.getSource() == this.btnOk)
		{
			Exit exit = new Exit();
			Store store = new Store();
			House house = new House();
			Goods goods = new Goods();
			Customer customer = new Customer();
			HouseManager hm = new HouseManager();
			GoodsManager gm = new GoodsManager();
			CustomerManager cm = new CustomerManager();

			try
			{
				house = hm.searchHouseByHouseName(cmbHouse.getSelectedItem()
						.toString());
				goods = gm.searchGoodsByGoodsName(cmbGoods.getSelectedItem()
						.toString());
				customer = cm.searchCustomerByCustomerName(cmbCustomer
						.getSelectedItem().toString());
			}
			catch (BaseException e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (edtExitTime.getText().equals(""))
				exit.setExitTime(new java.sql.Timestamp(System
						.currentTimeMillis()));
			else
			{
				// 格式化输入时间
				String date = this.edtExitTime.getText().toString();
				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = null;
				try
				{
					date1 = fmt.parse(date);
				}
				catch (ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				exit.setExitTime(new java.sql.Date(date1.getTime()));
			}

			exit.setBatchId(Integer.valueOf(edtBatchId.getText()));
			exit.setGoodsId(goods.getGoodsId());
			exit.setHouseId(house.getHouseId());
			exit.setCustomerId(customer.getCustomerId());
			exit.setExitAmount(Float.valueOf(edtExitAmount.getText()));
			exit.setExitPrice(Float.valueOf(edtExitPrice.getText()));
			exit.setWorkerId(WorkerManager.currentWorker.getWorkerId());
			exit.setExitNote(edtExitNote.getText());

			store = (new StoreManager()).searchStore(exit.getHouseId(),exit.getBatchId(),exit.getGoodsId());
			store.setStoreAmount(store.getStoreAmount() - exit.getExitAmount());

			//判断出库数量是否小于等于库存数量
			if(exit.getExitAmount() > store.getStoreAmount())
			{
				JOptionPane.showMessageDialog(null, "出库数量不能大于库存数量", "提示",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			(new ExitManager()).createExit(exit);
			try
			{
				(new StoreManager()).modifyStore(store);
			}
			catch (BaseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "创建成功", "成功",
					JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			return;
		}
	}
}
