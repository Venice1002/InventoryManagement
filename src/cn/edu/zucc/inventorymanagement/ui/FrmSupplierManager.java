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

import cn.edu.zucc.inventorymanagement.control.SupplierManager;
import cn.edu.zucc.inventorymanagement.model.Supplier;

public class FrmSupplierManager extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("�½���Ӧ��");
	private Button btnModify = new Button("�޸Ĺ�Ӧ��");
	private Button btnDelete = new Button("ɾ����Ӧ��");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Object tblSupplierTitle[] =
	{ "���", "��Ӧ������", "��Ӧ�̵�ַ", "��ϵ�绰" };
	private Object tblSupplierData[][];
	List<Supplier> supplierList = null;
	DefaultTableModel tabSupplierModel = new DefaultTableModel();
	private JTable dataSupplier = new JTable(tabSupplierModel);

	private void reloadTable()
	{
		// ���¼��ع�Ӧ����Ϣ
		supplierList = (new SupplierManager()).loadAllSupplier();
		tblSupplierData = new Object[supplierList.size()][4];
		for (int i = 0; i < supplierList.size(); i++)
		{
			tblSupplierData[i][0] = supplierList.get(i).getSupplierId();
			tblSupplierData[i][1] = supplierList.get(i).getSupplierName();
			tblSupplierData[i][2] = supplierList.get(i).getSupplierAddress();
			tblSupplierData[i][3] = supplierList.get(i).getSupplierPhone();
		}
		tabSupplierModel.setDataVector(tblSupplierData, tblSupplierTitle);
		this.dataSupplier.validate();
		this.dataSupplier.repaint();
	}

	public FrmSupplierManager(Frame f, String s, boolean b)
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
		this.getContentPane().add(new JScrollPane(this.dataSupplier),
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
			FrmSupplier_Add dlg = new FrmSupplier_Add(this, "�½���Ӧ��", true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnModify)
		{
			int i = this.dataSupplier.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ��Ӧ��", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Supplier supplier = this.supplierList.get(i);
			FrmSupplier_Modify dlg = new FrmSupplier_Modify(this, "�޸Ĺ�Ӧ��",
					true, supplier);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnDelete)
		{
			int i = this.dataSupplier.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ��Ӧ��", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Supplier supplier = this.supplierList.get(i);
			if (JOptionPane.showConfirmDialog(this,
					"ȷ��ɾ��" + supplier.getSupplierName() + "��", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				(new SupplierManager())
						.deleteSupplier(supplier.getSupplierId());
				this.reloadTable();
			}
		}
		else if (e.getSource() == this.btnSearch)
		{
			this.reloadTable();
		}

	}
}
