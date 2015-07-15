package com.good.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.good.commons.BusinessException;
import com.good.service.goodService;
import com.good.service.SaleRecordService;
 
import com.good.vo.SaleRecord;
import com.good.vo.good;
import com.good.vo.goodSaleRecord;
 

/**
 * ���۽���

 */
public class SalePanel extends CommonPanel {

	goodService goodService;
	//���ۼ�¼��
	Vector columns;
	//��Ʒ�����ۼ�¼��
	Vector goodSaleRecordColumns;
	//���ۼ�¼��ҵ��ӿ�
	SaleRecordService saleRecordService;
	//��Ʒ�Ľ��׼�¼�б�
	JTable goodSaleRecordTable;
	//��Ʒѡ���������
	JComboBox goodComboBox;
	//��Ʒ�����ۼ�¼����
	Vector<goodSaleRecord> goodSaleRecordDatas;
	//���ۼ�¼��id�ı���
	JTextField saleRecordId;
	//���ۼ�¼�ܼ�
	JTextField totalPrice;
	//��������
	JTextField recordDate;
	//����������
	JTextField amount;
	//��հ�ť
	JButton clearButton;
	//��Ʒ�ĵ���
	JLabel singlePrice;
	//������Ʒ������
	JTextField goodAmount;
	//��Ʒ��Ӧ�Ŀ��
	JLabel repertorySize;
	//�����Ʒ�İ�ť
	JButton addgoodButton;
	//ɾ����Ʒ�İ�ť
	JButton deletegoodButton;
	//�ɽ���ť
	JButton confirmButton;
	//��ѯ��ť
	JButton queyrButton;
	//��ѯ���������
	JTextField queryDate;
	//���ڸ�ʽ
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	//ʱ���ʽ
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	
	private void initColumns() {
		//��ʼ�����ۼ�¼�б����
		this.columns = new Vector();
		this.columns.add("id");
		this.columns.add("������Ʒ");
		this.columns.add("�ܼ�");
		this.columns.add("��������");
		this.columns.add("������");
		//��ʼ�����ۼ�¼����Ʒ�б����
		this.goodSaleRecordColumns = new Vector();
		this.goodSaleRecordColumns.add("id");
		this.goodSaleRecordColumns.add("��Ʒ��");
		this.goodSaleRecordColumns.add("����");
		this.goodSaleRecordColumns.add("����");
		this.goodSaleRecordColumns.add("goodId");
	}
	
	public SalePanel(goodService goodService, SaleRecordService saleRecordService) {
		this.goodService = goodService;
		this.saleRecordService = saleRecordService;
		//�����б�����
		setViewDatas();
		//��ʼ����
		initColumns();
		//�����б�
		DefaultTableModel model = new DefaultTableModel(datas, columns);
		JTable table = new CommonJTable(model);
		setJTable(table);
		JScrollPane upPane = new JScrollPane(table);
		upPane.setPreferredSize(new Dimension(1000, 350));
		//�������, �޸ĵĽ���
		JPanel downPane = new JPanel();
		downPane.setLayout(new BoxLayout(downPane, BoxLayout.Y_AXIS));
		/*******************************************************/
		Box downBox1 = new Box(BoxLayout.X_AXIS);
		this.saleRecordId = new JTextField(10);
		downBox1.add(this.saleRecordId);
		this.saleRecordId.setVisible(false);
		//�б������box
		downBox1.add(new JLabel("�ܼۣ�"));
		this.totalPrice = new JTextField(10);
		this.totalPrice.setEditable(false);
		downBox1.add(this.totalPrice);
		downBox1.add(new JLabel("      "));
		downBox1.add(new JLabel("�������ڣ�"));
		this.recordDate = new JTextField(10);
		this.recordDate.setEditable(false);
		//���õ�ǰ����ʱ��
		setRecordDate();
		downBox1.add(this.recordDate);
		downBox1.add(new JLabel("      "));
		downBox1.add(new JLabel("��������"));
		this.amount = new JTextField(10);
		this.amount.setEditable(false);
		downBox1.add(this.amount);
		downBox1.add(new JLabel("      "));
		/*******************************************************/
		//��Ʒ�б�
		Box downBox2 = new Box(BoxLayout.X_AXIS);
		this.goodSaleRecordDatas = new Vector();
		DefaultTableModel goodModel = new DefaultTableModel(this.goodSaleRecordDatas, 
				this.goodSaleRecordColumns);
		this.goodSaleRecordTable = new CommonJTable(goodModel);
		//������Ʒ���׼�¼�б����ʽ
		setgoodSaleRecordTableFace();
		JScrollPane goodScrollPane = new JScrollPane(this.goodSaleRecordTable);
		goodScrollPane.setPreferredSize(new Dimension(1000, 120));
		downBox2.add(goodScrollPane);
		/*******************************************************/
		Box downBox3 = new Box(BoxLayout.X_AXIS);
		downBox3.add(Box.createHorizontalStrut(100));
		downBox3.add(new JLabel("��Ʒ��"));
		downBox3.add(Box.createHorizontalStrut(20));
		//������������Ʒ��������
		this.goodComboBox = new JComboBox();
		//Ϊ�������������
		buildgoodsComboBox();
		downBox3.add(this.goodComboBox);
		downBox3.add(Box.createHorizontalStrut(50));
		downBox3.add(new JLabel("������"));
		downBox3.add(Box.createHorizontalStrut(20));
		this.goodAmount = new JTextField(10);
		downBox3.add(this.goodAmount);
		downBox3.add(Box.createHorizontalStrut(50));
		downBox3.add(new JLabel("���ۣ�"));
		downBox3.add(Box.createHorizontalStrut(20));
		this.singlePrice = new JLabel();
		downBox3.add(this.singlePrice);
		downBox3.add(Box.createHorizontalStrut(100));
		downBox3.add(new JLabel("��棺"));
		downBox3.add(Box.createHorizontalStrut(20));
		this.repertorySize = new JLabel();
		downBox3.add(this.repertorySize);
		downBox3.add(Box.createHorizontalStrut(80));
		this.addgoodButton = new JButton("���");
		downBox3.add(this.addgoodButton);
		downBox3.add(Box.createHorizontalStrut(30));
		this.deletegoodButton = new JButton("ɾ��");
		downBox3.add(this.deletegoodButton);
		/*******************************************************/
		Box downBox4 = new Box(BoxLayout.X_AXIS);
		this.confirmButton = new JButton("�ɽ�");
		downBox4.add(this.confirmButton);
		downBox4.add(Box.createHorizontalStrut(120));
		clearButton = new JButton("���");
		downBox4.add(clearButton);
		/*******************************************************/
		downPane.add(getSplitBox());
		downPane.add(downBox1);
		downPane.add(getSplitBox());
		downPane.add(downBox2);
		downPane.add(getSplitBox());
		downPane.add(downBox3);
		downPane.add(getSplitBox());
		downPane.add(downBox4);
		/*******************��ѯ******************/
		JPanel queryPanel = new JPanel();
		Box queryBox = new Box(BoxLayout.X_AXIS);
		queryBox.add(new JLabel("���ڣ�"));
		queryBox.add(Box.createHorizontalStrut(30));
		this.queryDate = new JTextField(20);
		queryBox.add(this.queryDate);
		queryBox.add(Box.createHorizontalStrut(30));
		this.queyrButton = new JButton("��ѯ");
		queryBox.add(this.queyrButton);
		queryPanel.add(queryBox);
		this.add(queryPanel);
		//�б�Ϊ��ӽ���
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upPane, downPane);
		split.setDividerSize(5);
		this.add(split);
		//��ʼ��������
		initListeners();
	}
	
	//��ʼ��������
	private void initListeners() {
		//���ѡ�������
		getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				//��ѡ����ʱ����ͷ�ʱ��ִ��
				if (!event.getValueIsAdjusting()) {
					//���û��ѡ���κ�һ��, �򷵻�
					if (getJTable().getSelectedRowCount() != 1) return;
					view();
				}
			}
		});
		//��հ�ť������
		this.clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		//��Ʒѡ������������
		this.goodComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				changegood();
			}
		});
		//������ʾ��Ʒ�ĵ���
		changegood();
		//���б����һ����Ʒ�����ۼ�¼�İ�ť
		this.addgoodButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				appendgood();
			}
		});
		//ɾ����Ʒ�Ľ��׼�¼��ť
		this.deletegoodButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				removegood();
			}
		});
		//�ɽ���ť
		this.confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sale();
			}
		});
		//��ѯ
		this.queyrButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
	}
	
	private void query() {
		String date = this.queryDate.getText();
		Date d = null;
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			showWarn("������yyyy-MM-dd�ĸ�ʽ����");
			return;
		}
		//����ִ�в�ѯ
		Vector<SaleRecord> records = (Vector<SaleRecord>)saleRecordService.getAll(d);
		Vector<Vector> datas = changeDatas(records);
		setDatas(datas);
		//ˢ���б�
		refreshTable();
	}
	
	//�ɽ��ķ���
	private void sale() {
		if (!this.saleRecordId.getText().equals("")) {
			showWarn("������ٽ��в���"); 
			return;
		}
		//���û�гɽ��κ���Ʒ, ����
		if (this.goodSaleRecordDatas.size() == 0) {
			showWarn("û�г����κε���Ʒ, ���óɽ�");
			return;
		}
		SaleRecord r = new SaleRecord();
		r.setRECORD_DATE(this.recordDate.getText());
		r.setgoodSaleRecords(this.goodSaleRecordDatas);
		try {
			saleRecordService.saveRecord(r);
		} catch (BusinessException e) {//�˴����쳣��ҵ���쳣
			showWarn(e.getMessage());
			return;
		}
		//���¶�ȡ����
		setViewDatas();
		//��ս���
		clear();
	}
	
	//���б����һ����Ʒ�����ۼ�¼
	private void appendgood() {
		if (!this.saleRecordId.getText().equals("")) {
			showWarn("������ٽ��в���"); 
			return;
		}
		if (this.goodAmount.getText().equals("")) {
			showWarn("��������Ʒ������"); 
			return;
		}
		//���ѡ�еĶ���
		good good = (good)goodComboBox.getSelectedItem();
		String amount = this.goodAmount.getText();
		appendOrUpdate(good, amount);
		//ˢ���б�
		refreshgoodSaleRecordTableData();
		//�����ܼ�
		countTotalPrice();
		//����������
		setTotalAmount();
	}
	
	//��ӻ����޸���Ʒ���׼�¼�еĶ���
	private void appendOrUpdate(good good, String amount) {
		goodSaleRecord r = getgoodSaleRecordFromView(good);
		//���Ϊ��, ��Ϊ����ӵ���Ʒ, �ǿ�, �����Ʒ�Ѿ����б���
		if (r == null) {
			//����goodSaleRecord������ӵ����ݼ�����
			goodSaleRecord record = new goodSaleRecord();
			record.setgood(good);
			record.setTRADE_SUM(amount);
			this.goodSaleRecordDatas.add(record);
		} else {
			int newAmount = Integer.valueOf(amount) + Integer.valueOf(r.getTRADE_SUM());
			r.setTRADE_SUM(String.valueOf(newAmount));
		}
	}
	
	//��ȡ���б����Ƿ��Ѿ�������ͬ����Ʒ
	private goodSaleRecord getgoodSaleRecordFromView(good good) {
		for (goodSaleRecord r : this.goodSaleRecordDatas) {
			if (r.getgood().getID().equals(good.getID())) {
				return r;
			}
		}
		return null;
	}
	
	//����������
	private void setTotalAmount() {
		int amount = 0;
		for (goodSaleRecord r : this.goodSaleRecordDatas) {
			amount += Integer.valueOf(r.getTRADE_SUM());
		}
		this.amount.setText(String.valueOf(amount));
	}
	
	//�����ܼ�
	private void countTotalPrice() {
		double totalPrice = 0;
		for (goodSaleRecord r : this.goodSaleRecordDatas) {
			totalPrice += (Integer.valueOf(r.getTRADE_SUM()) * 
					Double.valueOf(r.getgood().getgood_PRICE()));
		}
		this.totalPrice.setText(String.valueOf(totalPrice));
	}
	
	//���б����Ƴ�һ����Ʒ�����ۼ�¼
	private void removegood() {
		if (!this.saleRecordId.getText().equals("")) {
			showWarn("������ٽ��в���"); 
			return;
		}
		if (goodSaleRecordTable.getSelectedRow() == -1) {
			showWarn("��ѡ����Ҫɾ������");
			return;
		}
		//�ڼ�����ɾ����Ӧ������������
		this.goodSaleRecordDatas.remove(goodSaleRecordTable.getSelectedRow());
		//ˢ���б�
		refreshgoodSaleRecordTableData();
		//���¼����ܼۺ�������
		setTotalAmount();
		countTotalPrice();
	}
	
	
	//����Ʒѡ�����������ı�ʱ, ִ�и÷���
	private void changegood() {
		//���ѡ���good����
		good good = (good)goodComboBox.getSelectedItem();
		if (good == null) return;
		//������ʾ����Ʒ�ļ۸�
		this.singlePrice.setText(good.getgood_PRICE());
		this.repertorySize.setText(good.getREPERTORY_SIZE());
	}
	
	//���
	public void clear() {
		//ˢ�����б�
		refreshTable();
		this.saleRecordId.setText("");
		this.totalPrice.setText("");
		//���ý���Ľ���ʱ��Ϊ��ǰʱ��
		setRecordDate();
		this.amount.setText("");
		this.goodSaleRecordDatas.removeAll(this.goodSaleRecordDatas);
		refreshgoodSaleRecordTableData();
		//ˢ������
		this.goodComboBox.removeAllItems();
		buildgoodsComboBox();
	}
	
	//������ͼ����
	public void setViewDatas() {
		Vector<SaleRecord> records = (Vector<SaleRecord>)saleRecordService.getAll(new Date());
		Vector<Vector> datas = changeDatas(records);
		setDatas(datas);
	}
	
	//������ת�������б�����ݸ�ʽ
	private Vector<Vector> changeDatas(Vector<SaleRecord> records) {
		Vector<Vector> view = new Vector<Vector>();
		for (SaleRecord record : records) {
			Vector v = new Vector();
			v.add(record.getID());
			v.add(record.getgoodNames());
			v.add(record.getTotalPrice());
			v.add(record.getRECORD_DATE());
			v.add(record.getAmount());
			view.add(v);
		}
		return view;
	}
	
	//����������ѡ����Ʒ��������
	private void buildgoodsComboBox() {
		Collection<good> goods = goodService.getAll();
		for (good good : goods) {
			this.goodComboBox.addItem(makegood(good));
		}
	}
	
	//����good����, ������ӵ���������, ��д��equals��toString����
	private good makegood(final good source) {
		good good = new good(){
			public boolean equals(Object obj) {
				if (obj instanceof good) {
					good b = (good)obj;
					if (getID().equals(b.getID())) return true; 
				}
				return false;
			}
			public String toString() {
				return getgood_NAME();
			}
		};
		good.setgood_NAME(source.getgood_NAME());
		good.setgood_PRICE(source.getgood_PRICE());
		good.setREPERTORY_SIZE(source.getREPERTORY_SIZE());
		good.setID(source.getID());
		return good;
	}
	
	//�鿴һ�����ۼ�¼
	private void view() {
		String saleRecordId = getSelectId(getJTable());
		//�õ���Ʒ�Ľ��׼�¼
		SaleRecord record = saleRecordService.get(saleRecordId);
		//���õ�ǰ��Ʒ��������
		this.goodSaleRecordDatas = record.getgoodSaleRecords();
		//ˢ����Ʒ�����б�
		refreshgoodSaleRecordTableData();
		this.saleRecordId.setText(record.getID());
		this.totalPrice.setText(String.valueOf(record.getTotalPrice()));
		this.recordDate.setText(record.getRECORD_DATE());
		this.amount.setText(String.valueOf(record.getAmount()));
	}
	
	//����Ʒ�����ۼ�¼ת�����б��ʽ
	private Vector<Vector> changegoodSaleRecordDate(Vector<goodSaleRecord> records) {
		Vector<Vector> view = new Vector<Vector>();
		for (goodSaleRecord r : records) {
			Vector v = new Vector();
			v.add(r.getID());
			v.add(r.getgood().getgood_NAME());
			v.add(r.getgood().getgood_PRICE());
			v.add(r.getTRADE_SUM());
			v.add(r.getgood().getID());
			view.add(v);
		}
		return view;
	}
	
	//ˢ����Ʒ���ۼ�¼���б�
	private void refreshgoodSaleRecordTableData() {
		Vector<Vector> view = changegoodSaleRecordDate(this.goodSaleRecordDatas);
		DefaultTableModel tableModel = (DefaultTableModel)this.goodSaleRecordTable.getModel();
		//������������Model��
		tableModel.setDataVector(view, this.goodSaleRecordColumns);
		//���ñ����ʽ
		setgoodSaleRecordTableFace();
	}
	
	//������Ʒ���ۼ�¼����ʽ
	private void setgoodSaleRecordTableFace() {
		this.goodSaleRecordTable.setRowHeight(30);
		//�������ۼ�¼id��
		this.goodSaleRecordTable.getColumn("id").setMinWidth(-1);
		this.goodSaleRecordTable.getColumn("id").setMaxWidth(-1);
		//���ض�Ӧ����Ʒid��
		this.goodSaleRecordTable.getColumn("goodId").setMinWidth(-1);
		this.goodSaleRecordTable.getColumn("goodId").setMaxWidth(-1);
	}
	
	@Override
	public Vector<String> getColumns() {
		return this.columns;
	}

	@Override
	public void setTableFace() {
		getJTable().getColumn("id").setMinWidth(-1);
		getJTable().getColumn("id").setMaxWidth(-1);
		getJTable().getColumn("������Ʒ").setMinWidth(350);
		getJTable().setRowHeight(30);
	}

	//���ý�����ʾ�Ľ���ʱ��
	private void setRecordDate() {
		//���óɽ�����Ϊ��ǰʱ��
		Date now = new Date();
		timeFormat.format(now);
		this.recordDate.setText(timeFormat.format(now));
	}
}
