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

import cn.edu.zucc.inventorymanagement.control.CustomerManager;
import cn.edu.zucc.inventorymanagement.model.Customer;

public class FrmCustomerManager extends JDialog implements ActionListener
{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("�½��ͻ�");
	private Button btnModify = new Button("�޸Ŀͻ�");
	private Button btnDelete = new Button("ɾ���ͻ�");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Object tblCustomerTitle[] =
	{ "���", "�ͻ�����", "�ͻ���ַ", "��ϵ�绰" };
	private Object tblCustomerData[][];
	List<Customer> customerList = null;
	DefaultTableModel tabCustomerModel = new DefaultTableModel();
	private JTable dataCustomer = new JTable(tabCustomerModel);

	private void reloadTable()
	{
		// ���¼��ؿͻ���Ϣ
		customerList = (new CustomerManager()).loadAllCustomer();
		tblCustomerData = new Object[customerList.size()][4];
		for (int i = 0; i < customerList.size(); i++)
		{
			tblCustomerData[i][0] = customerList.get(i).getCustomerId();
			tblCustomerData[i][1] = customerList.get(i).getCustomerName();
			tblCustomerData[i][2] = customerList.get(i).getCustomerAddress();
			tblCustomerData[i][3] = customerList.get(i).getCustomerPhone();
		}
		tabCustomerModel.setDataVector(tblCustomerData, tblCustomerTitle);
		this.dataCustomer.validate();
		this.dataCustomer.repaint();
	}

	public FrmCustomerManager(Frame f, String s, boolean b)
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
		this.getContentPane().add(new JScrollPane(this.dataCustomer),
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
			FrmCustomer_Add dlg = new FrmCustomer_Add(this, "�½��ͻ�", true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnModify)
		{
			int i = this.dataCustomer.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ��ͻ�", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Customer customer = this.customerList.get(i);
			FrmCustomer_Modify dlg = new FrmCustomer_Modify(this, "�޸Ŀͻ�",
					true, customer);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnDelete)
		{
			int i = this.dataCustomer.getSelectedRow();
			if (i < 0)
			{
				JOptionPane.showMessageDialog(null, "��ѡ��ͻ�", "��ʾ",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Customer customer = this.customerList.get(i);
			if (JOptionPane.showConfirmDialog(this,
					"ȷ��ɾ��" + customer.getCustomerName() + "��", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				(new CustomerManager())
						.deleteCustomer(customer.getCustomerId());
				this.reloadTable();
			}
		}
		else if (e.getSource() == this.btnSearch)
		{
			this.reloadTable();
		}

	}
}
