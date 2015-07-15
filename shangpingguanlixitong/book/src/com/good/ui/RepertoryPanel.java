package com.good.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.good.service.goodService;
import com.good.service.InRecordService;
import com.good.vo.good;
import com.good.vo.goodInRecord;
import com.good.vo.InRecord;

/**
 * ������

 */
public class RepertoryPanel extends CommonPanel {

	private InRecordService inRecordService;
	//����¼�б���м���
	Vector columns;
	//��Ʒ������¼���м���
	Vector goodInColumns;
	//��Ʒ������б�JTable
	JTable goodInTable;
	//��Ʒ������¼�б�����
	Vector<goodInRecord> goodInRecords;
	
	goodService goodService;
	//ѡ����Ʒ��������
	JComboBox goodComboBox;
	JTextField amount;
	JTextField inDate;
	//�������ĳ������¼��������
	JTextField inRecordId;
	//��հ�ť
	JButton clearButton;
	//�����Ʒ�İ�ť
	JButton addgoodButton;
	//ɾ����Ʒ�İ�ť
	JButton deletegoodButton;
	//����Ʒ������б������Ʒʱ�����������
	JTextField goodAmount;
	//��Ʒԭ�е�����
	JLabel repertorySize;
	//ʱ���ʽ
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	JButton inButton;
	
	private void initColumns() {
		this.columns = new Vector();
		this.columns.add("id");
		this.columns.add("�����Ʒ");
		this.columns.add("�������");
		this.columns.add("�������");
		this.goodInColumns = new Vector();
		this.goodInColumns.add("id");
		this.goodInColumns.add("��Ʒ��");
		this.goodInColumns.add("����");
		this.goodInColumns.add("����");
		this.goodInColumns.add("goodId");
	}
	
	public RepertoryPanel(InRecordService inRecordService, goodService goodService) {
		this.inRecordService = inRecordService;
		this.goodService = goodService;
		initColumns();
		setViewDatas();
		//�����б�
		DefaultTableModel model = new DefaultTableModel(getDatas(), this.columns);
		JTable table = new CommonJTable(model);
		setJTable(table);
		
		JScrollPane upPane = new JScrollPane(table);
		upPane.setPreferredSize(new Dimension(1000, 350));
		

		//�������, �޸ĵĽ���
		JPanel downPane = new JPanel();
		downPane.setLayout(new BoxLayout(downPane, BoxLayout.Y_AXIS));
		
	
		/*******************************************************/
		//
		Box downBox1 = new Box(BoxLayout.X_AXIS);
		//��������¼��������
		this.inRecordId = new JTextField();
		downBox1.add(this.inRecordId);
		inRecordId.setVisible(false);
				
		downBox1.add(new JLabel("������ڣ�"));
		this.inDate = new JTextField(10);
		this.inDate.setEditable(false);
		setInDate();
		downBox1.add(this.inDate);
		downBox1.add(new JLabel("      "));
		
		downBox1.add(new JLabel("��������"));
		this.amount = new JTextField(10);
		this.amount.setEditable(false);
		downBox1.add(this.amount);
		downBox1.add(new JLabel("      "));

		/*******************************************************/
		//��Ʒ�б�
		Box downBox2 = new Box(BoxLayout.X_AXIS);
		
		this.goodInRecords = new Vector<goodInRecord>();
		DefaultTableModel goodModel = new DefaultTableModel(this.goodInRecords, 
				this.goodInColumns);
		this.goodInTable = new CommonJTable(goodModel);
		setgoodInRecordFace();
		
		JScrollPane goodScrollPane = new JScrollPane(this.goodInTable);
		goodScrollPane.setPreferredSize(new Dimension(1000, 120));
		downBox2.add(goodScrollPane);

		/*******************************************************/
		Box downBox3 = new Box(BoxLayout.X_AXIS);
		downBox3.add(Box.createHorizontalStrut(300));
		
		downBox3.add(new JLabel("��Ʒ��"));
		downBox3.add(Box.createHorizontalStrut(20));
		this.goodComboBox = new JComboBox();
		buildgoodsComboBox();
		downBox3.add(goodComboBox);
		
		downBox3.add(Box.createHorizontalStrut(50));
		
		downBox3.add(new JLabel("������"));
		downBox3.add(Box.createHorizontalStrut(20));
		this.goodAmount = new JTextField(10);
		downBox3.add(this.goodAmount);
		downBox3.add(Box.createHorizontalStrut(50));
		
		downBox3.add(new JLabel("���У�"));
		downBox3.add(Box.createHorizontalStrut(20));
		this.repertorySize = new JLabel();
		downBox3.add(this.repertorySize);
		downBox3.add(Box.createHorizontalStrut(50));
		
		this.addgoodButton = new JButton("���");
		downBox3.add(this.addgoodButton);

		downBox3.add(Box.createHorizontalStrut(30));
		
		this.deletegoodButton = new JButton("ɾ��");
		downBox3.add(this.deletegoodButton);
		
		/*******************************************************/
		Box downBox4 = new Box(BoxLayout.X_AXIS);
		
		this.inButton = new JButton("���");
		downBox4.add(this.inButton);
		
		downBox4.add(Box.createHorizontalStrut(130));
		
		this.clearButton = new JButton("���");
		downBox4.add(this.clearButton);
		
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
		queryBox.add(new JTextField(20));
		queryBox.add(Box.createHorizontalStrut(30));
		queryBox.add(new JButton("��ѯ"));
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
		//��Ʒѡ������������
		this.goodComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				changegood();
			}
		});
		//������ʾ��Ʒ����������
		changegood();
		//��հ�ť
		this.clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		//���б����һ����Ʒ������¼�İ�ť
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
		//��ⰴť
		this.inButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				in();
			}
		});
	}
	
	//���ķ���
	private void in() {
		if (!this.inRecordId.getText().equals("")) {
			showWarn("������ٽ��в���"); 
			return;
		}
		//���û�гɽ��κ���Ʒ, ����
		if (this.goodInRecords.size() == 0) {
			showWarn("û���κ���Ʒ���");
			return;
		}
		InRecord r = new InRecord();
		r.setRECORD_DATE(this.inDate.getText());
		r.setgoodInRecords(this.goodInRecords);
		inRecordService.save(r);
		//���¶�ȡ����
		setViewDatas();
		//��ս���
		clear();
	}
	
	//���б����Ƴ�һ����Ʒ������¼
	private void removegood() {
		if (!this.inRecordId.getText().equals("")) {
			showWarn("������ٽ��в���"); 
			return;
		}
		if (this.goodInTable.getSelectedRow() == -1) {
			showWarn("��ѡ����Ҫɾ������");
			return;
		}
		//�ڼ�����ɾ����Ӧ������������
		this.goodInRecords.remove(this.goodInTable.getSelectedRow());
		//ˢ���б�
		refreshgoodInRecordTableData();
		//���¼���������
		countAmount();
	}
	
	//����Ʒ������¼�б������һ����Ʒ
	private void appendgood() {
		if (!this.inRecordId.getText().equals("")) {
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
		refreshgoodInRecordTableData();
		//��������
		countAmount();
	}
	
	//����Ʒѡ�����������ı�ʱ, ִ�и÷���
	private void changegood() {
		//���ѡ���good����
		good good = (good)goodComboBox.getSelectedItem();
		if (good == null) return;
		this.repertorySize.setText(good.getREPERTORY_SIZE());
	}
	
	//����һ�������Ʒ������
	private void countAmount() {
		int amount = 0;
		for (goodInRecord r : this.goodInRecords) {
			amount += Integer.valueOf(r.getIN_SUM());
		}
		this.amount.setText(String.valueOf(amount));
	}
	
	//��ӻ����޸���Ʒ���׼�¼�еĶ���
	private void appendOrUpdate(good good, String amount) {
		goodInRecord r = getgoodInRecordFromView(good);
		//���Ϊ��, ��Ϊ����ӵ���Ʒ, �ǿ�, �����Ʒ�Ѿ����б���
		if (r == null) {
			//����goodSaleRecord������ӵ����ݼ�����
			goodInRecord record = new goodInRecord();
			record.setgood(good);
			record.setIN_SUM(amount);
			this.goodInRecords.add(record);
		} else {
			int newAmount = Integer.valueOf(amount) + Integer.valueOf(r.getIN_SUM());
			r.setIN_SUM(String.valueOf(newAmount));
		}
	}
	
	//��ȡ���б����Ƿ��Ѿ�������ͬ����Ʒ
	private goodInRecord getgoodInRecordFromView(good good) {
		for (goodInRecord r : this.goodInRecords) {
			if (r.getgood().getID().equals(good.getID())) {
				return r;
			}
		}
		return null;
	}
	
	//ˢ�±�����
	public void clear() {
		//ˢ�����б�
		refreshTable();
		this.inRecordId.setText("");
		this.amount.setText("");
		this.inDate.setText("");
		//�����Ʒ������¼������
		this.goodInRecords.removeAll(this.goodInRecords);
		refreshgoodInRecordTableData();
		//ˢ������
		this.goodComboBox.removeAllItems();
		buildgoodsComboBox();
		setInDate();
	}
	
	//������Ʒ������¼���б�
	private void setgoodInRecordFace() {
		this.goodInTable.setRowHeight(30);
		//������Ʒ����¼��id��
		this.goodInTable.getColumn("id").setMinWidth(-1);
		this.goodInTable.getColumn("id").setMaxWidth(-1);
		//������Ʒ����¼��Ӧ����Ʒid��
		this.goodInTable.getColumn("goodId").setMinWidth(-1);
		this.goodInTable.getColumn("goodId").setMaxWidth(-1);
	}
	
	//�鿴һ������¼
	private void view() {
		//�������¼��id
		String id = getSelectId(getJTable());
		//�����ݿ��в���
		InRecord r = inRecordService.get(id);
		//�����б����ݶ�Ӧ�ļ���
		this.goodInRecords = r.getgoodInRecords();
		//ˢ����Ʒ������¼�б�
		refreshgoodInRecordTableData();
		//���õ�ǰ�鿴��¼��������id
		this.inRecordId.setText(r.getID());
		//���ý������������������
		this.amount.setText(String.valueOf(r.getAmount()));
		this.inDate.setText(r.getRECORD_DATE());
	}

	@Override
	public Vector<String> getColumns() {
		return this.columns;
	}


	@Override
	public void setTableFace() {
		getJTable().getColumn("�����Ʒ").setMinWidth(350);
		getJTable().setRowHeight(30);
		getJTable().getColumn("id").setMinWidth(-1);
		getJTable().getColumn("id").setMaxWidth(-1);
	}

	//���¶�ȡ����
	public void setViewDatas() {
		Vector<InRecord> records = (Vector<InRecord>)inRecordService.getAll(new Date());
		Vector<Vector> datas = changeDatas(records);
		setDatas(datas);
	}
	
	//������ת�������б�����ݸ�ʽ
	private Vector<Vector> changeDatas(Vector<InRecord> records) {
		Vector<Vector> view = new Vector<Vector>();
		for (InRecord r : records) {
			Vector v = new Vector();
			v.add(r.getID());
			v.add(r.getgoodNames());
			v.add(r.getRECORD_DATE());
			v.add(r.getAmount());
			view.add(v);
		}
		return view;
	}
	
	//ˢ����Ʒ����¼���б�
	private void refreshgoodInRecordTableData() {
		Vector<Vector> view = changegoodInRecordDate(this.goodInRecords);
		DefaultTableModel tableModel = (DefaultTableModel)this.goodInTable.getModel();
		//������������Model��
		tableModel.setDataVector(view, this.goodInColumns);
		//���ñ����ʽ
		setgoodInRecordFace();
	}
	
	//����Ʒ������¼ת�����б��ʽ
	private Vector<Vector> changegoodInRecordDate(Vector<goodInRecord> records) {
		Vector<Vector> view = new Vector<Vector>();
		for (goodInRecord r : records) {
			Vector v = new Vector();
			v.add(r.getID());
			v.add(r.getgood().getgood_NAME());
			v.add(r.getgood().getgood_PRICE());
			v.add(r.getIN_SUM());
			v.add(r.getgood().getID());
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
	
	//���ý�����ʾ�Ľ���ʱ��
	private void setInDate() {
		//���óɽ�����Ϊ��ǰʱ��
		Date now = new Date();
		timeFormat.format(now);
		this.inDate.setText(timeFormat.format(now));
	}

}
