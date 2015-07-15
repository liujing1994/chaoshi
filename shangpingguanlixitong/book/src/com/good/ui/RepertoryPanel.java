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
 * 库存界面

 */
public class RepertoryPanel extends CommonPanel {

	private InRecordService inRecordService;
	//入库记录列表的列集合
	Vector columns;
	//商品的入库记录的列集合
	Vector goodInColumns;
	//商品的入库列表JTable
	JTable goodInTable;
	//商品的入库记录列表数据
	Vector<goodInRecord> goodInRecords;
	
	goodService goodService;
	//选择商品的下拉框
	JComboBox goodComboBox;
	JTextField amount;
	JTextField inDate;
	//保存具体某条入库记录的隐藏域
	JTextField inRecordId;
	//清空按钮
	JButton clearButton;
	//添加商品的按钮
	JButton addgoodButton;
	//删除商品的按钮
	JButton deletegoodButton;
	//向商品的入库列表添加商品时的数量输入框
	JTextField goodAmount;
	//商品原有的数量
	JLabel repertorySize;
	//时间格式
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	JButton inButton;
	
	private void initColumns() {
		this.columns = new Vector();
		this.columns.add("id");
		this.columns.add("入库商品");
		this.columns.add("入库日期");
		this.columns.add("入库数量");
		this.goodInColumns = new Vector();
		this.goodInColumns.add("id");
		this.goodInColumns.add("商品名");
		this.goodInColumns.add("单价");
		this.goodInColumns.add("数量");
		this.goodInColumns.add("goodId");
	}
	
	public RepertoryPanel(InRecordService inRecordService, goodService goodService) {
		this.inRecordService = inRecordService;
		this.goodService = goodService;
		initColumns();
		setViewDatas();
		//设置列表
		DefaultTableModel model = new DefaultTableModel(getDatas(), this.columns);
		JTable table = new CommonJTable(model);
		setJTable(table);
		
		JScrollPane upPane = new JScrollPane(table);
		upPane.setPreferredSize(new Dimension(1000, 350));
		

		//设置添加, 修改的界面
		JPanel downPane = new JPanel();
		downPane.setLayout(new BoxLayout(downPane, BoxLayout.Y_AXIS));
		
	
		/*******************************************************/
		//
		Box downBox1 = new Box(BoxLayout.X_AXIS);
		//保存入库记录的隐藏域
		this.inRecordId = new JTextField();
		downBox1.add(this.inRecordId);
		inRecordId.setVisible(false);
				
		downBox1.add(new JLabel("入库日期："));
		this.inDate = new JTextField(10);
		this.inDate.setEditable(false);
		setInDate();
		downBox1.add(this.inDate);
		downBox1.add(new JLabel("      "));
		
		downBox1.add(new JLabel("总数量："));
		this.amount = new JTextField(10);
		this.amount.setEditable(false);
		downBox1.add(this.amount);
		downBox1.add(new JLabel("      "));

		/*******************************************************/
		//商品列表
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
		
		downBox3.add(new JLabel("商品："));
		downBox3.add(Box.createHorizontalStrut(20));
		this.goodComboBox = new JComboBox();
		buildgoodsComboBox();
		downBox3.add(goodComboBox);
		
		downBox3.add(Box.createHorizontalStrut(50));
		
		downBox3.add(new JLabel("数量："));
		downBox3.add(Box.createHorizontalStrut(20));
		this.goodAmount = new JTextField(10);
		downBox3.add(this.goodAmount);
		downBox3.add(Box.createHorizontalStrut(50));
		
		downBox3.add(new JLabel("现有："));
		downBox3.add(Box.createHorizontalStrut(20));
		this.repertorySize = new JLabel();
		downBox3.add(this.repertorySize);
		downBox3.add(Box.createHorizontalStrut(50));
		
		this.addgoodButton = new JButton("添加");
		downBox3.add(this.addgoodButton);

		downBox3.add(Box.createHorizontalStrut(30));
		
		this.deletegoodButton = new JButton("删除");
		downBox3.add(this.deletegoodButton);
		
		/*******************************************************/
		Box downBox4 = new Box(BoxLayout.X_AXIS);
		
		this.inButton = new JButton("入库");
		downBox4.add(this.inButton);
		
		downBox4.add(Box.createHorizontalStrut(130));
		
		this.clearButton = new JButton("清空");
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
		
		/*******************查询******************/
		JPanel queryPanel = new JPanel();
		Box queryBox = new Box(BoxLayout.X_AXIS);
		queryBox.add(new JLabel("日期："));
		queryBox.add(Box.createHorizontalStrut(30));
		queryBox.add(new JTextField(20));
		queryBox.add(Box.createHorizontalStrut(30));
		queryBox.add(new JButton("查询"));
		queryPanel.add(queryBox);
		this.add(queryPanel);
		
		//列表为添加界面
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upPane, downPane);
		split.setDividerSize(5);
		this.add(split);
		//初始化监听器
		initListeners();
	}
	
	//初始化监听器
	private void initListeners() {
		//表格选择监听器
		getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				//当选择行时鼠标释放时才执行
				if (!event.getValueIsAdjusting()) {
					//如果没有选中任何一行, 则返回
					if (getJTable().getSelectedRowCount() != 1) return;
					view();
				}
			}
		});
		//商品选择下拉监听器
		this.goodComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				changegood();
			}
		});
		//设置显示商品的已有数量
		changegood();
		//清空按钮
		this.clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		//向列表添加一条商品的入库记录的按钮
		this.addgoodButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				appendgood();
			}
		});
		//删除商品的交易记录按钮
		this.deletegoodButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				removegood();
			}
		});
		//入库按钮
		this.inButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				in();
			}
		});
	}
	
	//入库的方法
	private void in() {
		if (!this.inRecordId.getText().equals("")) {
			showWarn("请清空再进行操作"); 
			return;
		}
		//如果没有成交任何商品, 返回
		if (this.goodInRecords.size() == 0) {
			showWarn("没有任何商品入库");
			return;
		}
		InRecord r = new InRecord();
		r.setRECORD_DATE(this.inDate.getText());
		r.setgoodInRecords(this.goodInRecords);
		inRecordService.save(r);
		//重新读取数据
		setViewDatas();
		//清空界面
		clear();
	}
	
	//从列表中移除一条商品的入库记录
	private void removegood() {
		if (!this.inRecordId.getText().equals("")) {
			showWarn("请清空再进行操作"); 
			return;
		}
		if (this.goodInTable.getSelectedRow() == -1) {
			showWarn("请选择需要删除的行");
			return;
		}
		//在集合中删除对应的索引的数据
		this.goodInRecords.remove(this.goodInTable.getSelectedRow());
		//刷新列表
		refreshgoodInRecordTableData();
		//重新计算总数量
		countAmount();
	}
	
	//向商品的入库记录列表中添加一本商品
	private void appendgood() {
		if (!this.inRecordId.getText().equals("")) {
			showWarn("请清空再进行操作"); 
			return;
		}
		if (this.goodAmount.getText().equals("")) {
			showWarn("请输入商品的数量"); 
			return;
		}
		//获得选中的对象
		good good = (good)goodComboBox.getSelectedItem();
		String amount = this.goodAmount.getText();
		appendOrUpdate(good, amount);
		refreshgoodInRecordTableData();
		//计算总数
		countAmount();
	}
	
	//当商品选择下拉框发生改变时, 执行该方法
	private void changegood() {
		//获得选择的good对象
		good good = (good)goodComboBox.getSelectedItem();
		if (good == null) return;
		this.repertorySize.setText(good.getREPERTORY_SIZE());
	}
	
	//计算一次入库商品的总数
	private void countAmount() {
		int amount = 0;
		for (goodInRecord r : this.goodInRecords) {
			amount += Integer.valueOf(r.getIN_SUM());
		}
		this.amount.setText(String.valueOf(amount));
	}
	
	//添加或者修改商品交易记录中的对象
	private void appendOrUpdate(good good, String amount) {
		goodInRecord r = getgoodInRecordFromView(good);
		//如果为空, 则为新添加的商品, 非空, 则该商品已经在列表中
		if (r == null) {
			//创建goodSaleRecord对象并添加到数据集合中
			goodInRecord record = new goodInRecord();
			record.setgood(good);
			record.setIN_SUM(amount);
			this.goodInRecords.add(record);
		} else {
			int newAmount = Integer.valueOf(amount) + Integer.valueOf(r.getIN_SUM());
			r.setIN_SUM(String.valueOf(newAmount));
		}
	}
	
	//获取在列表中是否已经存在相同的商品
	private goodInRecord getgoodInRecordFromView(good good) {
		for (goodInRecord r : this.goodInRecords) {
			if (r.getgood().getID().equals(good.getID())) {
				return r;
			}
		}
		return null;
	}
	
	//刷新表单方法
	public void clear() {
		//刷新主列表
		refreshTable();
		this.inRecordId.setText("");
		this.amount.setText("");
		this.inDate.setText("");
		//清空商品的入库记录的数据
		this.goodInRecords.removeAll(this.goodInRecords);
		refreshgoodInRecordTableData();
		//刷新下拉
		this.goodComboBox.removeAllItems();
		buildgoodsComboBox();
		setInDate();
	}
	
	//设置商品的入库记录的列表
	private void setgoodInRecordFace() {
		this.goodInTable.setRowHeight(30);
		//隐藏商品入库记录的id列
		this.goodInTable.getColumn("id").setMinWidth(-1);
		this.goodInTable.getColumn("id").setMaxWidth(-1);
		//隐藏商品入库记录对应的商品id列
		this.goodInTable.getColumn("goodId").setMinWidth(-1);
		this.goodInTable.getColumn("goodId").setMaxWidth(-1);
	}
	
	//查看一入入库记录
	private void view() {
		//获得入库记录的id
		String id = getSelectId(getJTable());
		//从数据库中查找
		InRecord r = inRecordService.get(id);
		//设置列表数据对应的集合
		this.goodInRecords = r.getgoodInRecords();
		//刷新商品的入库记录列表
		refreshgoodInRecordTableData();
		//设置当前查看记录的隐藏域id
		this.inRecordId.setText(r.getID());
		//设置界面总数量和入库日期
		this.amount.setText(String.valueOf(r.getAmount()));
		this.inDate.setText(r.getRECORD_DATE());
	}

	@Override
	public Vector<String> getColumns() {
		return this.columns;
	}


	@Override
	public void setTableFace() {
		getJTable().getColumn("入库商品").setMinWidth(350);
		getJTable().setRowHeight(30);
		getJTable().getColumn("id").setMinWidth(-1);
		getJTable().getColumn("id").setMaxWidth(-1);
	}

	//重新读取数据
	public void setViewDatas() {
		Vector<InRecord> records = (Vector<InRecord>)inRecordService.getAll(new Date());
		Vector<Vector> datas = changeDatas(records);
		setDatas(datas);
	}
	
	//将数据转换成主列表的数据格式
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
	
	//刷新商品入库记录的列表
	private void refreshgoodInRecordTableData() {
		Vector<Vector> view = changegoodInRecordDate(this.goodInRecords);
		DefaultTableModel tableModel = (DefaultTableModel)this.goodInTable.getModel();
		//将数据设入表格Model中
		tableModel.setDataVector(view, this.goodInColumns);
		//设置表格样式
		setgoodInRecordFace();
	}
	
	//将商品的入库记录转换成列表格式
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
	
	//创建界面中选择商品的下拉框
	private void buildgoodsComboBox() {
		Collection<good> goods = goodService.getAll();
		for (good good : goods) {
			this.goodComboBox.addItem(makegood(good));
		}
	}

	//创建good对象, 用于添加到下拉框中, 重写了equals和toString方法
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
	
	//设置界面显示的交易时间
	private void setInDate() {
		//设置成交日期为当前时间
		Date now = new Date();
		timeFormat.format(now);
		this.inDate.setText(timeFormat.format(now));
	}

}
