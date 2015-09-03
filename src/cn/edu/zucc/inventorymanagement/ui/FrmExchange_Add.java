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

import cn.edu.zucc.inventorymanagement.control.ExchangeManager;
import cn.edu.zucc.inventorymanagement.control.ReturnManager;
import cn.edu.zucc.inventorymanagement.control.GoodsManager;
import cn.edu.zucc.inventorymanagement.control.HouseManager;
import cn.edu.zucc.inventorymanagement.control.StoreManager;
import cn.edu.zucc.inventorymanagement.control.CustomerManager;
import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.Exchange;
import cn.edu.zucc.inventorymanagement.model.Goods;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Store;
import cn.edu.zucc.inventorymanagement.model.Customer;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.DbException;

public class FrmExchange_Add extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelBatchId = new JLabel("���κţ�");
	private JLabel labelExchangeAmount = new JLabel("�ƿ�������");
	private JLabel labelExchangePrice = new JLabel("��浥�ۣ�");
	private JLabel labelExchangeTime = new JLabel("�ƿ�ʱ�䣺");
	private JLabel labelLastHouse = new JLabel("ԭʼ�ֿ⣺");
	private JLabel labelNextHouse = new JLabel("Ŀ��ֿ⣺");
	private JLabel labelGoods = new JLabel("���ϣ�");
	private JLabel labelUnit = new JLabel("��λ��");
	private JLabel labelExchangeNote = new JLabel("��ע��");
	private JLabel labelWorker = new JLabel("�����ˣ�");

	private JLabel labelNote = new JLabel("ʱ�䲻��Ĭ��Ϊ��ǰʱ��   ��ʽ��2015-01-01");
	private JTextField edtExchangeAmount = new JTextField(20);
	private JTextField edtExchangeTime = new JTextField(20);
	private JTextField edtBatchId = new JTextField(20);
	private JTextField edtExchangePrice = new JTextField(20);
	private JTextField edtUnit = new JTextField(20);
	private JTextField edtExchangeNote = new JTextField(20);
	private JTextField edtWorker = new JTextField(20);
	private JComboBox cmbLastHouse;
	private JComboBox cmbNextHouse;
	private JComboBox cmbGoods;
	private House lastHouse = null;
	private Goods goods = null;
	private List<House> nextHouseList = null;

	/**
	 * @wbp.parser.constructor
	 */
	public FrmExchange_Add(FrmExchangeManager frmExchangeManager, String s, boolean b,
			Store store)
	{
		super(frmExchangeManager, s, b);
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

		labelExchangeAmount.setBounds(30, 80, 70, 14);
		workPane.add(labelExchangeAmount);

		edtExchangeAmount.setBounds(112, 77, 120, 20);
		workPane.add(edtExchangeAmount);

		labelExchangeTime.setBounds(30, 118, 70, 14);
		workPane.add(labelExchangeTime);

		edtExchangeTime.setBounds(112, 112, 120, 20);
		workPane.add(edtExchangeTime);

		labelNote.setBounds(30, 143, 400, 14);
		workPane.add(labelNote);

		labelLastHouse.setBounds(30, 171, 55, 14);
		workPane.add(labelLastHouse);
		
		
		
		labelNextHouse.setBounds(170, 171, 55, 14);
		workPane.add(labelNextHouse);

		cmbLastHouse = new JComboBox();
		cmbLastHouse.setBounds(70, 168, 80, 20);
		cmbGoods = new JComboBox();
		cmbGoods.setBounds(350, 168, 80, 20);
		cmbNextHouse = new JComboBox();
		cmbNextHouse.setBounds(210, 168, 80, 20);
		workPane.add(cmbNextHouse);
		workPane.add(cmbLastHouse);
		labelGoods.setBounds(310, 171, 55, 14);
		workPane.add(labelGoods);
		workPane.add(cmbGoods);
		this.getContentPane().add(workPane, BorderLayout.CENTER);

		labelExchangePrice.setBounds(30, 49, 70, 14);
		workPane.add(labelExchangePrice);

		edtExchangePrice.setBounds(112, 46, 120, 20);
		edtExchangePrice.setText(String.valueOf(store.getStorePrice()));
		edtExchangePrice.setEnabled(false);
		workPane.add(edtExchangePrice);

		labelUnit.setBounds(277, 18, 70, 14);
		workPane.add(labelUnit);

		edtUnit.setBounds(341, 15, 120, 20);
		edtUnit.setText(store.getUnit());
		edtUnit.setEnabled(false);
		workPane.add(edtUnit);

		labelExchangeNote.setBounds(277, 49, 70, 14);
		workPane.add(labelExchangeNote);

		edtExchangeNote.setBounds(341, 46, 120, 20);
		workPane.add(edtExchangeNote);

		edtWorker.setBounds(341, 77, 120, 20);
		edtWorker.setText(WorkerManager.currentWorker.getWorkerName());
		workPane.add(edtWorker);

		labelWorker.setBounds(277, 80, 70, 14);
		workPane.add(labelWorker);
		this.setSize(500, 300);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.cmbLastHouse.addActionListener(this);
		this.cmbGoods.addActionListener(this);

		//��ʼ�������˵�
		try
		{
			lastHouse = (new HouseManager()).searchHouseByHouseId(store
					.getHouseId());
			cmbLastHouse.addItem(lastHouse.getHouseName());
			
			nextHouseList = (new HouseManager()).loadAllHouse();
			cmbNextHouse.addItem("");
			for (int i = 0; i < nextHouseList.size(); i++)
			{
				cmbNextHouse.addItem(nextHouseList.get(i).getHouseName());
			}

			goods = (new GoodsManager()).searchGoodsByGoodsId(store
					.getGoodsId());
			cmbGoods.addItem(goods.getGoodsName());
		}
		catch (BaseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Exchange exchange = new Exchange();
			Store lastStore = new Store();
			Store nextStore = new Store();
			House lastHouse = new House();
			House nextHouse = new House();
			Goods goods = new Goods();
			HouseManager hm = new HouseManager();
			GoodsManager gm = new GoodsManager();

			try
			{
				lastHouse = hm.searchHouseByHouseName(cmbLastHouse.getSelectedItem()
						.toString());
				nextHouse = hm.searchHouseByHouseName(cmbNextHouse.getSelectedItem()
						.toString());
				goods = gm.searchGoodsByGoodsName(cmbGoods.getSelectedItem()
						.toString());
			}
			catch (BaseException e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (edtExchangeTime.getText().equals(""))
				exchange.setExchangeTime(new java.sql.Timestamp(System
						.currentTimeMillis()));
			else
			{
				// ��ʽ������ʱ��
				String date = this.edtExchangeTime.getText().toString();
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
				exchange.setExchangeTime(new java.sql.Date(date1.getTime()));
			}

			exchange.setBatchId(Integer.valueOf(edtBatchId.getText()));
			exchange.setGoodsId(goods.getGoodsId());
			exchange.setLastHouseId(lastHouse.getHouseId());
			exchange.setNextHouseId(nextHouse.getHouseId());
			exchange.setExchangeAmount(Float.valueOf(edtExchangeAmount.getText()));
			exchange.setWorkerId(WorkerManager.currentWorker.getWorkerId());
			exchange.setExchangeNote(edtExchangeNote.getText());

			lastStore = (new StoreManager()).searchStore(exchange.getLastHouseId(),exchange.getBatchId(),exchange.getGoodsId());
			lastStore.setStoreAmount(lastStore.getStoreAmount() - exchange.getExchangeAmount());
			
			//����ƿ��Ŀ����û�м�¼ ������һ����¼ �� ����м�¼ ���������
			
			nextStore = (new StoreManager()).searchStore(exchange.getNextHouseId(),exchange.getBatchId(),exchange.getGoodsId());
			if(nextStore == null)
			{
				nextStore = new Store();
				System.out.println("create");
				nextStore.setHouseId(exchange.getNextHouseId());
				nextStore.setGoodsId(exchange.getGoodsId());
				nextStore.setBatchId(exchange.getBatchId());
				nextStore.setStoreAmount(exchange.getExchangeAmount());
				nextStore.setStorePrice(lastStore.getStorePrice());
				nextStore.setUnit(lastStore.getUnit());
				(new StoreManager()).createStore(nextStore);
			}
			else
			{
				System.out.println("modify");
				nextStore.setStoreAmount(nextStore.getStoreAmount() + exchange.getExchangeAmount());
				try
				{
					(new StoreManager()).modifyStore(nextStore);
				}
				catch (BaseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			(new ExchangeManager()).createExchange(exchange);
			try
			{
				(new StoreManager()).modifyStore(lastStore);
				
			}
			catch (BaseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "�����ɹ�", "�ɹ�",
					JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			return;
		}
	}
}
