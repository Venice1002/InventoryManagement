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

import cn.edu.zucc.inventorymanagement.control.EnterManager;
import cn.edu.zucc.inventorymanagement.control.GoodsManager;
import cn.edu.zucc.inventorymanagement.control.HouseManager;
import cn.edu.zucc.inventorymanagement.control.StoreManager;
import cn.edu.zucc.inventorymanagement.control.SupplierManager;
import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.Enter;
import cn.edu.zucc.inventorymanagement.model.Goods;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Store;
import cn.edu.zucc.inventorymanagement.model.Supplier;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.DbException;

public class FrmEnter_Add extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelBatchId = new JLabel("批次号：");
	private JLabel labelEnterAmount = new JLabel("入库数量：");
	private JLabel labelEnterPrice = new JLabel("入库单价：");
	private JLabel labelEnterTime = new JLabel("入库时间：");
	private JLabel labelHouse = new JLabel("仓库：");
	private JLabel labelGoods = new JLabel("物料：");
	private JLabel labelUnit = new JLabel("单位：");
	private JLabel labelEnterNote = new JLabel("备注：");
	private JLabel labelWorker = new JLabel("出库人：");
	private JLabel labelSupplier = new JLabel("供应商：");

	private JLabel labelNote = new JLabel("时间不填默认为当前时刻   格式如2015-01-01");
	private JTextField edtEnterAmount = new JTextField(20);
	private JTextField edtEnterTime = new JTextField(20);
	private JTextField edtBatchId = new JTextField(20);
	private JTextField edtEnterPrice = new JTextField(20);
	private JTextField edtUnit = new JTextField(20);
	private JTextField edtEnterNote = new JTextField(20);
	private JTextField edtWorker = new JTextField(20);
	private JComboBox cmbHouse;
	private JComboBox cmbGoods;
	private JComboBox cmbSupplier;
	private House house = null;
	private List<Goods> goodsList = null;
	private List<Supplier> supplierList = null;

	/**
	 * @wbp.parser.constructor
	 */
	public FrmEnter_Add(FrmEnterManager frmEnterManager, String s, boolean b,
			House house)
	{
		super(frmEnterManager, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		
		labelBatchId.setBounds(30, 18, 70, 14);
		workPane.add(labelBatchId);
		
		edtBatchId.setBounds(112, 15, 120, 20);
		workPane.add(edtBatchId);
		
		labelEnterAmount.setBounds(30, 80, 70, 14);
		workPane.add(labelEnterAmount);
		
		edtEnterAmount.setBounds(112, 77, 120, 20);
		workPane.add(edtEnterAmount);
		
		labelEnterTime.setBounds(30, 118, 70, 14);
		workPane.add(labelEnterTime);
		
		edtEnterTime.setBounds(112, 112, 120, 20);
		workPane.add(edtEnterTime);
		
		labelNote.setBounds(30, 143, 400, 14);
		workPane.add(labelNote);
		
		labelHouse.setBounds(30, 171, 55, 14);
		workPane.add(labelHouse);

		cmbHouse = new JComboBox();
		cmbHouse.setBounds(71, 168, 80, 20);
		workPane.add(cmbHouse);
		cmbGoods = new JComboBox();
		cmbGoods.setBounds(202, 168, 80, 20);
		workPane.add(cmbGoods);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		labelGoods.setBounds(165, 171, 55, 14);
		workPane.add(labelGoods);
		
		labelEnterPrice.setBounds(30, 49, 70, 14);
		workPane.add(labelEnterPrice);

		edtEnterPrice.setBounds(112, 46, 120, 20);
		workPane.add(edtEnterPrice);

		labelUnit.setBounds(277, 18, 70, 14);
		workPane.add(labelUnit);

		edtUnit.setBounds(341, 15, 120, 20);
		workPane.add(edtUnit);

		labelEnterNote.setBounds(277, 49, 70, 14);
		workPane.add(labelEnterNote);

		edtEnterNote.setBounds(341, 46, 120, 20);
		workPane.add(edtEnterNote);
		edtWorker.setBounds(341, 77, 120, 20);
		edtWorker.setText(WorkerManager.currentWorker.getWorkerName());
		workPane.add(edtWorker);

		labelWorker.setBounds(277, 80, 70, 14);
		workPane.add(labelWorker);

		labelSupplier.setBounds(292, 171, 55, 14);
		workPane.add(labelSupplier);

		cmbSupplier = new JComboBox();
		cmbSupplier.setBounds(341, 168, 80, 20);
		workPane.add(cmbSupplier);
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
		this.cmbSupplier.addActionListener(this);

		//初始化下拉菜单
		try
		{
			this.house = (new HouseManager()).searchHouseByHouseId(house
					.getHouseId());
			cmbHouse.addItem(house.getHouseName());
		}
		catch (BaseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cmbGoods.removeAllItems();
		goodsList = (new GoodsManager()).loadAllGoods();
		cmbGoods.addItem("");
		for (int i = 0; i < goodsList.size(); i++)
		{
			cmbGoods.addItem(goodsList.get(i).getGoodsName());
		}

		cmbSupplier.removeAllItems();
		supplierList = (new SupplierManager()).loadAllSupplier();
		cmbSupplier.addItem("");
		for (int i = 0; i < supplierList.size(); i++)
		{
			cmbSupplier.addItem(supplierList.get(i).getSupplierName());
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
			Enter enter = new Enter();
			Store store = new Store();
			House house = new House();
			Goods goods = new Goods();
			Supplier supplier = new Supplier();
			HouseManager hm = new HouseManager();
			GoodsManager gm = new GoodsManager();
			SupplierManager sm = new SupplierManager();

			try
			{
				house = hm.searchHouseByHouseName(cmbHouse.getSelectedItem()
						.toString());
				goods = gm.searchGoodsByGoodsName(cmbGoods.getSelectedItem()
						.toString());
				supplier = sm.searchSupplierBySupplierName(cmbSupplier
						.getSelectedItem().toString());
			}
			catch (BaseException e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (edtEnterTime.getText().equals(""))
				enter.setEnterTime(new java.sql.Timestamp(System
						.currentTimeMillis()));
			else
			{
				// 格式化输入时间
				String date = this.edtEnterTime.getText().toString();
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
				enter.setEnterTime(new java.sql.Date(date1.getTime()));
			}

			enter.setBatchId(Integer.valueOf(edtBatchId.getText()));
			enter.setGoodsId(goods.getGoodsId());
			enter.setHouseId(house.getHouseId());
			enter.setSupplierId(supplier.getSupplierId());
			enter.setEnterAmount(Float.valueOf(edtEnterAmount.getText()));
			enter.setEnterPrice(Float.valueOf(edtEnterPrice.getText()));
			enter.setWorkerId(WorkerManager.currentWorker.getWorkerId());
			enter.setEnterNote(edtEnterNote.getText());

			store.setHouseId(house.getHouseId());
			store.setGoodsId(goods.getGoodsId());
			store.setBatchId(enter.getBatchId());
			store.setStoreAmount(enter.getEnterAmount());
			store.setStorePrice(enter.getEnterPrice());
			store.setUnit(goods.getUnit());

			(new EnterManager()).createEnter(enter);
			(new StoreManager()).createStore(store);
			JOptionPane.showMessageDialog(null, "创建成功", "成功",
					JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			return;
		}
	}
}
