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

import cn.edu.zucc.inventorymanagement.control.DestoryManager;
import cn.edu.zucc.inventorymanagement.control.ReturnManager;
import cn.edu.zucc.inventorymanagement.control.GoodsManager;
import cn.edu.zucc.inventorymanagement.control.HouseManager;
import cn.edu.zucc.inventorymanagement.control.StoreManager;
import cn.edu.zucc.inventorymanagement.control.CustomerManager;
import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.Destory;
import cn.edu.zucc.inventorymanagement.model.Goods;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Store;
import cn.edu.zucc.inventorymanagement.model.Customer;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.DbException;

public class FrmDestory_Add extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelBatchId = new JLabel("���κţ�");
	private JLabel labelDestoryAmount = new JLabel("����������");
	private JLabel labelDestoryPrice = new JLabel("��浥�ۣ�");
	private JLabel labelDestoryTime = new JLabel("����ʱ�䣺");
	private JLabel labelHouse = new JLabel("�ֿ⣺");
	private JLabel labelGoods = new JLabel("���ϣ�");
	private JLabel labelUnit = new JLabel("��λ��");
	private JLabel labelDestoryNote = new JLabel("��ע��");
	private JLabel labelWorker = new JLabel("�����ˣ�");

	private JLabel labelNote = new JLabel("ʱ�䲻��Ĭ��Ϊ��ǰʱ��   ��ʽ��2015-01-01");
	private JTextField edtDestoryAmount = new JTextField(20);
	private JTextField edtDestoryTime = new JTextField(20);
	private JTextField edtBatchId = new JTextField(20);
	private JTextField edtDestoryPrice = new JTextField(20);
	private JTextField edtUnit = new JTextField(20);
	private JTextField edtDestoryNote = new JTextField(20);
	private JTextField edtWorker = new JTextField(20);
	private JComboBox cmbHouse;
	private JComboBox cmbGoods;
	private House house = null;
	private Goods goods = null;
	private List<Customer> customerList = null;

	/**
	 * @wbp.parser.constructor
	 */
	public FrmDestory_Add(FrmDestoryManager frmDestoryManager, String s, boolean b,
			Store store)
	{
		super(frmDestoryManager, s, b);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);

		labelGoods.setBounds(165, 171, 55, 14);
		workPane.add(labelGoods);
		
		labelDestoryPrice.setBounds(30, 49, 70, 14);
		workPane.add(labelDestoryPrice);
		
		labelBatchId.setBounds(30, 18, 70, 14);
		workPane.add(labelBatchId);
		
		labelDestoryAmount.setBounds(30, 80, 70, 14);
		workPane.add(labelDestoryAmount);

		labelDestoryTime.setBounds(30, 118, 70, 14);
		workPane.add(labelDestoryTime);
		
		labelNote.setBounds(30, 143, 400, 14);
		workPane.add(labelNote);

		labelHouse.setBounds(30, 171, 55, 14);
		workPane.add(labelHouse);
		
		labelUnit.setBounds(277, 18, 70, 14);
		workPane.add(labelUnit);
		
		labelDestoryNote.setBounds(277, 49, 70, 14);
		workPane.add(labelDestoryNote);
		
		labelWorker.setBounds(277, 80, 70, 14);
		workPane.add(labelWorker);

		edtBatchId.setBounds(112, 15, 120, 20);
		edtBatchId.setText(String.valueOf(store.getBatchId()));
		edtBatchId.setEnabled(false);
		workPane.add(edtBatchId);

		edtDestoryAmount.setBounds(112, 77, 120, 20);
		workPane.add(edtDestoryAmount);

		edtDestoryTime.setBounds(112, 112, 120, 20);
		workPane.add(edtDestoryTime);

		edtDestoryPrice.setBounds(112, 46, 120, 20);
		edtDestoryPrice.setText(String.valueOf(store.getStorePrice()));
		edtDestoryPrice.setEnabled(false);
		workPane.add(edtDestoryPrice);

		edtUnit.setBounds(341, 15, 120, 20);
		edtUnit.setText(store.getUnit());
		edtUnit.setEnabled(false);
		workPane.add(edtUnit);

		edtDestoryNote.setBounds(341, 46, 120, 20);
		workPane.add(edtDestoryNote);

		edtWorker.setBounds(341, 77, 120, 20);
		edtWorker.setText(WorkerManager.currentWorker.getWorkerName());
		workPane.add(edtWorker);

		cmbHouse = new JComboBox();
		cmbHouse.setBounds(71, 168, 80, 20);
		workPane.add(cmbHouse);
		cmbGoods = new JComboBox();
		cmbGoods.setBounds(202, 168, 80, 20);
		workPane.add(cmbGoods);
		this.getContentPane().add(workPane, BorderLayout.CENTER);

		this.setSize(500, 300);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.cmbHouse.addActionListener(this);
		this.cmbGoods.addActionListener(this);

		//��ʼ�������˵�
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
			Destory destory = new Destory();
			Store store = new Store();
			House house = new House();
			Goods goods = new Goods();
			HouseManager hm = new HouseManager();
			GoodsManager gm = new GoodsManager();

			//��ȡ�����˵��е�ѡ�� 
			try
			{
				house = hm.searchHouseByHouseName(cmbHouse.getSelectedItem()
						.toString());
				goods = gm.searchGoodsByGoodsName(cmbGoods.getSelectedItem()
						.toString());
			}
			catch (BaseException e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			//�ж��Ƿ�����ʱ��
			//�������룬��ʹ��ϵͳʱ��
			if (edtDestoryTime.getText().equals(""))
				destory.setDestoryTime(new java.sql.Timestamp(System
						.currentTimeMillis()));
			else
			{
				// ��ʽ������ʱ��
				String date = this.edtDestoryTime.getText().toString();
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
				destory.setDestoryTime(new java.sql.Date(date1.getTime()));
			}

			destory.setBatchId(Integer.valueOf(edtBatchId.getText()));
			destory.setGoodsId(goods.getGoodsId());
			destory.setHouseId(house.getHouseId());
			destory.setDestoryAmount(Float.valueOf(edtDestoryAmount.getText()));
			destory.setWorkerId(WorkerManager.currentWorker.getWorkerId());
			destory.setDestoryNote(edtDestoryNote.getText());

			store = (new StoreManager()).searchStore(destory.getHouseId(),destory.getBatchId(),destory.getGoodsId());
			
			//�жϱ��������Ƿ�С�ڵ��ڿ������
			if(destory.getDestoryAmount() > store.getStoreAmount())
			{
				JOptionPane.showMessageDialog(null, "�����������ܴ��ڿ������", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			store.setStoreAmount(store.getStoreAmount() - destory.getDestoryAmount());

			(new DestoryManager()).createDestory(destory);
			try
			{
				(new StoreManager()).modifyStore(store);
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
