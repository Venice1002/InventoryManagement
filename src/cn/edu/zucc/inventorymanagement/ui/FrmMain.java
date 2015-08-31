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
import cn.edu.zucc.inventorymanagement.control.WorkerManager;
import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.util.BaseException;

public class FrmMain extends JFrame implements ActionListener
{
	private JMenuBar menubar = new JMenuBar();;
	private JMenu menu_system = new JMenu("ϵͳ����");
	private JMenu menu_houseAndGoods = new JMenu("�ֿ�/���Ϲ���");
	private JMenu menu_supAndCus = new JMenu("��Ӧ��/�ͻ�����");
	private JMenu menu_store = new JMenu("������");
	private JMenu menu_list = new JMenu("�嵥����");
	private JMenu menu_static = new JMenu("��ѯͳ��");
	private JMenu menu_more = new JMenu("����");

	private JMenuItem menuItem_WorkerManage = new JMenuItem("�û�����");
	private JMenuItem menuItem_HouseManage = new JMenuItem("�ֿ����");
	private JMenuItem menuItem_GoodsManage = new JMenuItem("���Ϲ���");
	private JMenuItem menuItem_SupplierManage = new JMenuItem("��Ӧ�̹���");
	private JMenuItem menuItem_CunstomManage = new JMenuItem("�ͻ�����");
	// private JMenuItem menuItem_ModifyGoods = new JMenuItem("�޸�����");
	// private JMenuItem menuItem_DeleteGoods = new JMenuItem("ɾ������");
	private JMenuItem menuItem_StoreCheck = new JMenuItem("����̲�");
	private JMenuItem menuItem_EnterList = new JMenuItem("����嵥");
	private JMenuItem menuItem_ExitList = new JMenuItem("�����嵥");
	private JMenuItem menuItem_ExchangeList = new JMenuItem("�ƿ��嵥");
	private JMenuItem menuItem_ReturnList = new JMenuItem("�˿��嵥");
	private JMenuItem menuItem_DestoryList = new JMenuItem("�����嵥");

	private JMenuItem menuItem_modifyPwd = new JMenuItem("�����޸�");

	private JMenuItem menuItem_monthStatic = new JMenuItem("����ͳ��");
	private JMenuItem menuItem_timeStatic = new JMenuItem("ʱ��ͳ��");

	private FrmLogin dlgLogin = null;
	private JPanel statusBar = new JPanel();

	private Object tblHouseTitle[] =
	{ "���", "�ֿ�����", "�������", "����ܽ��", "�ֿ�״̬", "����" };
	private Object tblHouseData[][];
	DefaultTableModel tabHouseModel = new DefaultTableModel();
	private JTable dataHouse = new JTable(tabHouseModel);

	private Object tblItemTitle[] =
	{ "���", "ժҪ", "���", "ʱ��", "���", "��������" };
	private Object tblItemData[][];
	DefaultTableModel tabItemModel = new DefaultTableModel();
	private JTable dataTableItem = new JTable(tabItemModel);

	List<House> houseList = null;

	// List<IncomeItem> incomeItem = null;
	// List<PaymentItem> paymentItem = null;

	private void reloadHouseTable()
	{
		// ���¼��زֿ���Ϣ
		houseList = (new HouseManager()).loadAllHouse();
		tblHouseData = new Object[houseList.size()][6];
		for (int i = 0; i < houseList.size(); i++)
		{
			tblHouseData[i][0] = houseList.get(i).getHouseId();
			tblHouseData[i][1] = houseList.get(i).getHouseName();
			tblHouseData[i][2] = houseList.get(i).getTotalAmount();
			tblHouseData[i][3] = houseList.get(i).getTotalPrice();
			tblHouseData[i][4] = houseList.get(i).getHouseState();
			tblHouseData[i][5] = "����";
		}
		tabHouseModel.setDataVector(tblHouseData, tblHouseTitle);
		this.dataHouse.validate();
		this.dataHouse.repaint();
	}

	/*
	 * private void reloadItemTabel() { int i =
	 * this.dataProjectType.getSelectedRow(); if (i < 0) {
	 * JOptionPane.showMessageDialog(null, "��ѡ��Ŀ", "��ʾ",
	 * JOptionPane.ERROR_MESSAGE); return; } int projectId =
	 * Integer.parseInt(this.tblProjectData[i][0].toString());
	 * 
	 * incomeItem = (new IncomeItemManager()).loadIncomeItem(projectId); int
	 * incomeNum = 0; paymentItem = (new
	 * PaymentItemManager()).loadPaymentItem(projectId); int paumentNum = 0;
	 * 
	 * tblItemData = new Object[incomeItem.size() + paymentItem.size()][6]; //
	 * ����������Ŀ for (incomeNum = 0; incomeNum < incomeItem.size(); incomeNum++) {
	 * tblItemData[incomeNum][0] = incomeItem.get(incomeNum).getIncomeId();
	 * tblItemData[incomeNum][1] = incomeItem.get(incomeNum) .getIncomeNote();
	 * tblItemData[incomeNum][2] = incomeItem.get(incomeNum) .getIncomeAmount();
	 * tblItemData[incomeNum][3] = incomeItem.get(incomeNum)
	 * .getIncomeCreateDate(); // ���Ҳ���ʾ�������� IncomeType result = new
	 * IncomeType(); IncomeTypeManager tm = new IncomeTypeManager(); result =
	 * tm.searchIncomeType(incomeItem.get(incomeNum) .getIncomeTypeId());
	 * tblItemData[incomeNum][4] = "����"; tblItemData[incomeNum][5] =
	 * result.getIncomeTypeName(); } // ����֧����Ŀ for (paumentNum = incomeNum;
	 * paumentNum < (paymentItem.size() + incomeNum); paumentNum++) {
	 * tblItemData[paumentNum][0] = paymentItem .get(paumentNum -
	 * incomeNum).getPaymentId(); tblItemData[paumentNum][1] = paymentItem
	 * .get(paumentNum - incomeNum).getPaymentNote(); tblItemData[paumentNum][2]
	 * = paymentItem .get(paumentNum - incomeNum).getPaymentAmount();
	 * tblItemData[paumentNum][3] = paymentItem .get(paumentNum -
	 * incomeNum).getPaymentCreateDate(); // ���Ҳ���ʾ�������� PaymentType result = new
	 * PaymentType(); PaymentTypeManager tm = new PaymentTypeManager(); result =
	 * tm.searchPaymentType(paymentItem.get( paumentNum -
	 * incomeNum).getPaymentTypeId()); tblItemData[paumentNum][4] = "֧��";
	 * tblItemData[paumentNum][5] = result.getPaymentTypeName(); }
	 * 
	 * tabItemModel.setDataVector(tblItemData, tblItemTitle);
	 * this.dataTableItem.validate(); this.dataTableItem.repaint(); }
	 */
	public FrmMain()
	{

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("������ϵͳ");
		dlgLogin = new FrmLogin(this, "��½", true);
		dlgLogin.setVisible(true);
		// FrmMonthStatic dlg = new FrmMonthStatic(this, "����ͳ��", true);
		// dlg.setVisible(true);
		// �˵�
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
		//
		// this.menu_goods.add(this.menuItem_AddGoods);
		// this.menuItem_AddGoods.addActionListener(this);
		// this.menu_goods.add(this.menuItem_ModifyGoods);
		// this.menuItem_ModifyGoods.addActionListener(this);
		// this.menu_goods.add(this.menuItem_DeleteGoods);
		// this.menuItem_DeleteGoods.addActionListener(this);

		this.menu_store.add(this.menuItem_StoreCheck);
		this.menuItem_StoreCheck.addActionListener(this);

		this.menu_list.add(this.menuItem_EnterList);
		this.menuItem_EnterList.addActionListener(this);
		this.menu_list.add(this.menuItem_ExitList);
		this.menuItem_ExitList.addActionListener(this);
		this.menu_list.add(this.menuItem_ExchangeList);
		this.menuItem_ExchangeList.addActionListener(this);
		this.menu_list.add(this.menuItem_ReturnList);
		this.menuItem_ReturnList.addActionListener(this);
		this.menu_list.add(this.menuItem_DestoryList);
		this.menuItem_DestoryList.addActionListener(this);

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
				int projectId = Integer
						.parseInt(FrmMain.this.tblHouseData[i][0].toString());
				// FrmMain.this.reloadItemTabel();
			}

		});
		this.getContentPane().add(new JScrollPane(this.dataTableItem),
				BorderLayout.CENTER);

		this.reloadHouseTable();
		// ״̬��
		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("����! "
				+ WorkerManager.currentWorker.getWorkerName());// ���ã�+��½�û���
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
			FrmHouseManager dlg = new FrmHouseManager(this, "�ֿ����", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_GoodsManage)
		{
			FrmGoodsManager dlg = new FrmGoodsManager(this, "���Ϲ���", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_SupplierManage)
		{
			FrmSupplierManager dlg = new FrmSupplierManager(this, "��Ӧ�̹���", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_CunstomManage)
		{
			FrmCustomerManager dlg = new FrmCustomerManager(this, "�ͻ�����", true);
			dlg.setVisible(true);
		}
	}
}
