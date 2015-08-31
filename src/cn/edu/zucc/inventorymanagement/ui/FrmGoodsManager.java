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

import cn.edu.zucc.inventorymanagement.control.GoodsManager;
import cn.edu.zucc.inventorymanagement.model.Goods;

public class FrmGoodsManager extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("�½�����");
	private Button btnModify = new Button("�޸�����");
	private Button btnDelete = new Button("ɾ������");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Object tblGoodsTitle[] =
	{ "���", "��������", "�������", "������", "��������", "��ֹ����", "���ϵȼ�", "�������", "���ϵ�λ", "���ϱ�ע" };
	private Object tblGoodsData[][];
	List<Goods> goodsList = null;
	DefaultTableModel tabGoodsModel = new DefaultTableModel();
	private JTable dataGoods = new JTable(tabGoodsModel);

	private void reloadTable()
	{
		// ���¼���������Ϣ
		goodsList = (new GoodsManager()).loadAllGoods();
		tblGoodsData = new Object[goodsList.size()][10];
		for (int i = 0; i < goodsList.size(); i++)
		{
			tblGoodsData[i][0] = goodsList.get(i).getGoodsId();
			tblGoodsData[i][1] = goodsList.get(i).getGoodsName();
			tblGoodsData[i][2] = goodsList.get(i).getGoodsType();
			tblGoodsData[i][3] = goodsList.get(i).getManufacturer();
			tblGoodsData[i][4] = goodsList.get(i).getProduceDate();
			tblGoodsData[i][5] = goodsList.get(i).getLimitedDate();
			tblGoodsData[i][6] = goodsList.get(i).getGoodsLevel();
			tblGoodsData[i][7] = goodsList.get(i).getGoodsVolume();
			tblGoodsData[i][8] = goodsList.get(i).getUnit();
			tblGoodsData[i][9] = goodsList.get(i).getGoodsNote();
		}
		tabGoodsModel.setDataVector(tblGoodsData, tblGoodsTitle);
		this.dataGoods.validate();
		this.dataGoods.repaint();
	}

	public FrmGoodsManager(Frame f, String s, boolean b)
	{
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnDelete);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataGoods),
				BorderLayout.CENTER);

		// ��Ļ������ʾ
		this.setSize(880, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
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
			FrmGoods_Add dlg = new FrmGoods_Add(this, "�½�����", true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnModify)
		{
			int i = this.dataGoods.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ������", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Goods goods = this.goodsList.get(i);
			FrmGoods_Modify dlg = new FrmGoods_Modify(this, "�޸�����", true, goods);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnDelete)
		{
			int i = this.dataGoods.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ������", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Goods goods = this.goodsList.get(i);
			if (JOptionPane.showConfirmDialog(this,
					"ȷ��ɾ��" + goods.getGoodsName() + "��", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				(new GoodsManager()).deleteGoods(goods.getGoodsId());
				this.reloadTable();
			}
		}
		else if (e.getSource() == this.btnSearch)
		{
			this.reloadTable();
		}

	}
}
