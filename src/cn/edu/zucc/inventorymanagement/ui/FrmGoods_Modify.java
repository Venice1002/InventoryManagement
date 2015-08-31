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

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPanel;

import cn.edu.zucc.inventorymanagement.control.GoodsManager;
import cn.edu.zucc.inventorymanagement.model.Goods;
import cn.edu.zucc.inventorymanagement.util.BaseException;

public class FrmGoods_Modify extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelGoodsName = new JLabel("物料名称：");
	private JLabel labelGoodsType = new JLabel("物料类别：");
	private JLabel labelProduceDate = new JLabel("生产日期：");
	private JLabel labelLimitedDate = new JLabel("截止日期：");
	private JLabel labelUnit = new JLabel("物料单位：");
	private JLabel labelGoodsVolume = new JLabel("物料体积：");
	private JLabel labelGoodsLevel = new JLabel("物料等级：");
	private JLabel labelGoodsNote = new JLabel("物料备注：");
	private JLabel labelManufacturer = new JLabel("生产商：");

	private JTextField edtGoodsName = new JTextField(20);
	private JTextField edtGoodsType = new JTextField(20);
	private JTextField edtProduceDate = new JTextField(20);
	private JTextField edtLimitedDate = new JTextField(20);
	private JTextField edtUnit = new JTextField(20);
	private JTextField edtGoodsVolume = new JTextField(20);
	private JTextField edtGoodsNote = new JTextField(20);
	private JTextField edtGoodsLevel = new JTextField(20);
	private JTextField edtManufacturer = new JTextField(20);

	private Goods originGoods = null;

	public FrmGoods_Modify(FrmGoodsManager frmGoodsManager, String s,
			boolean b, Goods goods)
	{
		super(frmGoodsManager, "修改物料", b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelGoodsName.setBounds(24, 40, 70, 14);
		workPane.add(labelGoodsName);
		this.getContentPane().add(workPane, BorderLayout.CENTER);

		// 传入原始物料信息
		originGoods = goods;

		labelGoodsType.setBounds(24, 70, 70, 14);
		workPane.add(labelGoodsType);

		labelProduceDate.setBounds(24, 100, 70, 14);
		workPane.add(labelProduceDate);

		labelLimitedDate.setBounds(24, 130, 70, 14);
		workPane.add(labelLimitedDate);

		labelUnit.setBounds(218, 40, 70, 14);
		workPane.add(labelUnit);

		labelGoodsVolume.setBounds(218, 70, 70, 14);
		workPane.add(labelGoodsVolume);

		labelGoodsNote.setBounds(218, 160, 70, 14);
		workPane.add(labelGoodsNote);
		
		labelGoodsLevel.setBounds(218, 100, 70, 14);
		workPane.add(labelGoodsLevel);
		
		labelManufacturer.setBounds(218, 130, 70, 14);
		workPane.add(labelManufacturer);

		edtGoodsName.setBounds(89, 37, 100, 20);
		edtGoodsName.setText(originGoods.getGoodsName());
		workPane.add(edtGoodsName);

		edtGoodsType.setBounds(89, 67, 100, 20);
		edtGoodsType.setText(originGoods.getGoodsType());
		workPane.add(edtGoodsType);

		edtProduceDate.setBounds(89, 97, 100, 20);
		edtProduceDate.setText(originGoods.getProduceDate().toString());
		workPane.add(edtProduceDate);

		edtLimitedDate.setBounds(89, 127, 100, 20);
		edtLimitedDate.setText(originGoods.getLimitedDate().toString());
		workPane.add(edtLimitedDate);

		edtUnit.setBounds(283, 37, 100, 20);
		edtUnit.setText(originGoods.getUnit());
		workPane.add(edtUnit);

		edtGoodsVolume.setBounds(283, 67, 100, 20);
		edtGoodsVolume.setText(String.valueOf(originGoods.getGoodsVolume()));
		workPane.add(edtGoodsVolume);

		edtGoodsNote.setBounds(283, 157, 100, 20);
		edtGoodsNote.setText(originGoods.getGoodsNote());
		workPane.add(edtGoodsNote);

		edtGoodsLevel.setBounds(283, 97, 100, 20);
		edtGoodsLevel.setText(String.valueOf(originGoods.getGoodsLevel()));
		workPane.add(edtGoodsLevel);
		
		edtManufacturer.setBounds(283, 127, 100, 20);
		edtManufacturer.setText(originGoods.getManufacturer());
		workPane.add(edtManufacturer);
		
		this.setSize(440, 300);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);

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
			Goods goods = new Goods();
			//原始id
			goods.setGoodsId(originGoods.getGoodsId());
			//其他修改后的属性 
			goods.setGoodsName(edtGoodsName.getText());
			goods.setGoodsType(edtGoodsType.getText());
			goods.setGoodsNote(edtGoodsNote.getText());
			goods.setUnit(edtUnit.getText());
			goods.setGoodsLevel(Integer.valueOf(edtGoodsLevel.getText()));
			goods.setGoodsVolume(edtGoodsVolume.getText());
			goods.setManufacturer(edtManufacturer.getText());

			// 格式化输入时间
			String date1 = this.edtProduceDate.getText().toString();
			String date2 = this.edtLimitedDate.getText().toString();
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date dateProduce = null;
			Date dateLimited = null;
			try
			{
				dateProduce = fmt.parse(date1);
				dateLimited = fmt.parse(date2);
			}
			catch (ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			goods.setProduceDate(new java.sql.Date(dateProduce.getTime()));
			goods.setLimitedDate(new java.sql.Date(dateLimited.getTime()));

			GoodsManager hm = new GoodsManager();
			try
			{
				hm.createGoods(goods);
			}
			catch (BaseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);

		}

	}
}
