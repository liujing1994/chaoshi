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
 * 销售界面

 */
public class SalePanel extends CommonPanel {

	goodService goodService;
	//销售记录列
	Vector columns;
	//商品的销售记录列
	Vector goodSaleRecordColumns;
	//销售记录的业务接口
	SaleRecordService saleRecordService;
	//商品的交易记录列表
	JTable goodSaleRecordTable;
	//商品选择的下拉框
	JComboBox goodComboBox;
	//商品的销售记录数据
	Vector<goodSaleRecord> goodSaleRecordDatas;
	//销售记录的id文本框
	JTextField saleRecordId;
	//销售记录总价
	JTextField totalPrice;
	//销售日期
	JTextField recordDate;
	//销售总数量
	JTextField amount;
	//清空按钮
	JButton clearButton;
	//商品的单价
	JLabel singlePrice;
	//购买商品的数量
	JTextField goodAmount;
	//商品对应的库存
	JLabel repertorySize;
	//添加商品的按钮
	JButton addgoodButton;
	//删除商品的按钮
	JButton deletegoodButton;
	//成交按钮
	JButton confirmButton;
	//查询按钮
	JButton queyrButton;
	//查询输入的日期
	JTextField queryDate;
	//日期格式
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	//时间格式
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	
	private void initColumns() {
		//初始化销售记录列表的列
		this.columns = new Vector();
		this.columns.add("id");
		this.columns.add("购买商品");
		this.columns.add("总价");
		this.columns.add("交易日期");
		this.columns.add("总数量");
		//初始化销售记录中商品列表的列
		this.goodSaleRecordColumns = new Vector();
		this.goodSaleRecordColumns.add("id");
		this.goodSaleRecordColumns.add("商品名");
		this.goodSaleRecordColumns.add("单价");
		this.goodSaleRecordColumns.add("数量");
		this.goodSaleRecordColumns.add("goodId");
	}
	
	public SalePanel(goodService goodService, SaleRecordService saleRecordService) {
		this.goodService = goodService;
		this.saleRecordService = saleRecordService;
		//设置列表数据
		setViewDatas();
		//初始化列
		initColumns();
		//设置列表
		DefaultTableModel model = new DefaultTableModel(datas, columns);
		JTable table = new CommonJTable(model);
		setJTable(table);
		JScrollPane upPane = new JScrollPane(table);
		upPane.setPreferredSize(new Dimension(1000, 350));
		//设置添加, 修改的界面
		JPanel downPane = new JPanel();
		downPane.setLayout(new BoxLayout(downPane, BoxLayout.Y_AXIS));
		/*******************************************************/
		Box downBox1 = new Box(BoxLayout.X_AXIS);
		this.saleRecordId = new JTextField(10);
		downBox1.add(this.saleRecordId);
		this.saleRecordId.setVisible(false);
		//列表下面的box
		downBox1.add(new JLabel("总价："));
		this.totalPrice = new JTextField(10);
		this.totalPrice.setEditable(false);
		downBox1.add(this.totalPrice);
		downBox1.add(new JLabel("      "));
		downBox1.add(new JLabel("交易日期："));
		this.recordDate = new JTextField(10);
		this.recordDate.setEditable(false);
		//设置当前交易时间
		setRecordDate();
		downBox1.add(this.recordDate);
		downBox1.add(new JLabel("      "));
		downBox1.add(new JLabel("总数量："));
		this.amount = new JTextField(10);
		this.amount.setEditable(false);
		downBox1.add(this.amount);
		downBox1.add(new JLabel("      "));
		/*******************************************************/
		//商品列表
		Box downBox2 = new Box(BoxLayout.X_AXIS);
		this.goodSaleRecordDatas = new Vector();
		DefaultTableModel goodModel = new DefaultTableModel(this.goodSaleRecordDatas, 
				this.goodSaleRecordColumns);
		this.goodSaleRecordTable = new CommonJTable(goodModel);
		//设置商品交易记录列表的样式
		setgoodSaleRecordTableFace();
		JScrollPane goodScrollPane = new JScrollPane(this.goodSaleRecordTable);
		goodScrollPane.setPreferredSize(new Dimension(1000, 120));
		downBox2.add(goodScrollPane);
		/*******************************************************/
		Box downBox3 = new Box(BoxLayout.X_AXIS);
		downBox3.add(Box.createHorizontalStrut(100));
		downBox3.add(new JLabel("商品："));
		downBox3.add(Box.createHorizontalStrut(20));
		//创建界面中商品的下拉框
		this.goodComboBox = new JComboBox();
		//为下拉框添加数据
		buildgoodsComboBox();
		downBox3.add(this.goodComboBox);
		downBox3.add(Box.createHorizontalStrut(50));
		downBox3.add(new JLabel("数量："));
		downBox3.add(Box.createHorizontalStrut(20));
		this.goodAmount = new JTextField(10);
		downBox3.add(this.goodAmount);
		downBox3.add(Box.createHorizontalStrut(50));
		downBox3.add(new JLabel("单价："));
		downBox3.add(Box.createHorizontalStrut(20));
		this.singlePrice = new JLabel();
		downBox3.add(this.singlePrice);
		downBox3.add(Box.createHorizontalStrut(100));
		downBox3.add(new JLabel("库存："));
		downBox3.add(Box.createHorizontalStrut(20));
		this.repertorySize = new JLabel();
		downBox3.add(this.repertorySize);
		downBox3.add(Box.createHorizontalStrut(80));
		this.addgoodButton = new JButton("添加");
		downBox3.add(this.addgoodButton);
		downBox3.add(Box.createHorizontalStrut(30));
		this.deletegoodButton = new JButton("删除");
		downBox3.add(this.deletegoodButton);
		/*******************************************************/
		Box downBox4 = new Box(BoxLayout.X_AXIS);
		this.confirmButton = new JButton("成交");
		downBox4.add(this.confirmButton);
		downBox4.add(Box.createHorizontalStrut(120));
		clearButton = new JButton("清空");
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
		/*******************查询******************/
		JPanel queryPanel = new JPanel();
		Box queryBox = new Box(BoxLayout.X_AXIS);
		queryBox.add(new JLabel("日期："));
		queryBox.add(Box.createHorizontalStrut(30));
		this.queryDate = new JTextField(20);
		queryBox.add(this.queryDate);
		queryBox.add(Box.createHorizontalStrut(30));
		this.queyrButton = new JButton("查询");
		queryBox.add(this.queyrButton);
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
		//清空按钮监听器
		this.clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		//商品选择下拉监听器
		this.goodComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				changegood();
			}
		});
		//设置显示商品的单价
		changegood();
		//向列表添加一条商品的销售记录的按钮
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
		//成交按钮
		this.confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sale();
			}
		});
		//查询
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
			showWarn("请输入yyyy-MM-dd的格式日期");
			return;
		}
		//重新执行查询
		Vector<SaleRecord> records = (Vector<SaleRecord>)saleRecordService.getAll(d);
		Vector<Vector> datas = changeDatas(records);
		setDatas(datas);
		//刷新列表
		refreshTable();
	}
	
	//成交的方法
	private void sale() {
		if (!this.saleRecordId.getText().equals("")) {
			showWarn("请清空再进行操作"); 
			return;
		}
		//如果没有成交任何商品, 返回
		if (this.goodSaleRecordDatas.size() == 0) {
			showWarn("没有出售任何的商品, 不得成交");
			return;
		}
		SaleRecord r = new SaleRecord();
		r.setRECORD_DATE(this.recordDate.getText());
		r.setgoodSaleRecords(this.goodSaleRecordDatas);
		try {
			saleRecordService.saveRecord(r);
		} catch (BusinessException e) {//此处的异常是业务异常
			showWarn(e.getMessage());
			return;
		}
		//重新读取数据
		setViewDatas();
		//清空界面
		clear();
	}
	
	//向列表添加一条商品的销售记录
	private void appendgood() {
		if (!this.saleRecordId.getText().equals("")) {
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
		//刷新列表
		refreshgoodSaleRecordTableData();
		//计算总价
		countTotalPrice();
		//计算总数量
		setTotalAmount();
	}
	
	//添加或者修改商品交易记录中的对象
	private void appendOrUpdate(good good, String amount) {
		goodSaleRecord r = getgoodSaleRecordFromView(good);
		//如果为空, 则为新添加的商品, 非空, 则该商品已经在列表中
		if (r == null) {
			//创建goodSaleRecord对象并添加到数据集合中
			goodSaleRecord record = new goodSaleRecord();
			record.setgood(good);
			record.setTRADE_SUM(amount);
			this.goodSaleRecordDatas.add(record);
		} else {
			int newAmount = Integer.valueOf(amount) + Integer.valueOf(r.getTRADE_SUM());
			r.setTRADE_SUM(String.valueOf(newAmount));
		}
	}
	
	//获取在列表中是否已经存在相同的商品
	private goodSaleRecord getgoodSaleRecordFromView(good good) {
		for (goodSaleRecord r : this.goodSaleRecordDatas) {
			if (r.getgood().getID().equals(good.getID())) {
				return r;
			}
		}
		return null;
	}
	
	//设置总数量
	private void setTotalAmount() {
		int amount = 0;
		for (goodSaleRecord r : this.goodSaleRecordDatas) {
			amount += Integer.valueOf(r.getTRADE_SUM());
		}
		this.amount.setText(String.valueOf(amount));
	}
	
	//计算总价
	private void countTotalPrice() {
		double totalPrice = 0;
		for (goodSaleRecord r : this.goodSaleRecordDatas) {
			totalPrice += (Integer.valueOf(r.getTRADE_SUM()) * 
					Double.valueOf(r.getgood().getgood_PRICE()));
		}
		this.totalPrice.setText(String.valueOf(totalPrice));
	}
	
	//从列表中移除一条商品的销售记录
	private void removegood() {
		if (!this.saleRecordId.getText().equals("")) {
			showWarn("请清空再进行操作"); 
			return;
		}
		if (goodSaleRecordTable.getSelectedRow() == -1) {
			showWarn("请选择需要删除的行");
			return;
		}
		//在集合中删除对应的索引的数据
		this.goodSaleRecordDatas.remove(goodSaleRecordTable.getSelectedRow());
		//刷新列表
		refreshgoodSaleRecordTableData();
		//重新计算总价和总数量
		setTotalAmount();
		countTotalPrice();
	}
	
	
	//当商品选择下拉框发生改变时, 执行该方法
	private void changegood() {
		//获得选择的good对象
		good good = (good)goodComboBox.getSelectedItem();
		if (good == null) return;
		//设置显示的商品的价格
		this.singlePrice.setText(good.getgood_PRICE());
		this.repertorySize.setText(good.getREPERTORY_SIZE());
	}
	
	//清空
	public void clear() {
		//刷新主列表
		refreshTable();
		this.saleRecordId.setText("");
		this.totalPrice.setText("");
		//设置界面的交易时间为当前时间
		setRecordDate();
		this.amount.setText("");
		this.goodSaleRecordDatas.removeAll(this.goodSaleRecordDatas);
		refreshgoodSaleRecordTableData();
		//刷新下拉
		this.goodComboBox.removeAllItems();
		buildgoodsComboBox();
	}
	
	//设置视图数据
	public void setViewDatas() {
		Vector<SaleRecord> records = (Vector<SaleRecord>)saleRecordService.getAll(new Date());
		Vector<Vector> datas = changeDatas(records);
		setDatas(datas);
	}
	
	//将数据转换成主列表的数据格式
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
	
	//查看一条销售记录
	private void view() {
		String saleRecordId = getSelectId(getJTable());
		//得到商品的交易记录
		SaleRecord record = saleRecordService.get(saleRecordId);
		//设置当前商品销售数据
		this.goodSaleRecordDatas = record.getgoodSaleRecords();
		//刷新商品销售列表
		refreshgoodSaleRecordTableData();
		this.saleRecordId.setText(record.getID());
		this.totalPrice.setText(String.valueOf(record.getTotalPrice()));
		this.recordDate.setText(record.getRECORD_DATE());
		this.amount.setText(String.valueOf(record.getAmount()));
	}
	
	//将商品的销售记录转换成列表格式
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
	
	//刷新商品销售记录的列表
	private void refreshgoodSaleRecordTableData() {
		Vector<Vector> view = changegoodSaleRecordDate(this.goodSaleRecordDatas);
		DefaultTableModel tableModel = (DefaultTableModel)this.goodSaleRecordTable.getModel();
		//将数据设入表格Model中
		tableModel.setDataVector(view, this.goodSaleRecordColumns);
		//设置表格样式
		setgoodSaleRecordTableFace();
	}
	
	//设置商品销售记录的样式
	private void setgoodSaleRecordTableFace() {
		this.goodSaleRecordTable.setRowHeight(30);
		//隐藏销售记录id列
		this.goodSaleRecordTable.getColumn("id").setMinWidth(-1);
		this.goodSaleRecordTable.getColumn("id").setMaxWidth(-1);
		//隐藏对应的商品id列
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
		getJTable().getColumn("购买商品").setMinWidth(350);
		getJTable().setRowHeight(30);
	}

	//设置界面显示的交易时间
	private void setRecordDate() {
		//设置成交日期为当前时间
		Date now = new Date();
		timeFormat.format(now);
		this.recordDate.setText(timeFormat.format(now));
	}
}
