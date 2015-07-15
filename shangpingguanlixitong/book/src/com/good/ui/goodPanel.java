package com.good.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collection;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.good.commons.ImageUtil;
import com.good.commons.UploadException;
import com.good.service.goodService;
import com.good.service.ConcernService;
import com.good.service.TypeService;
import com.good.vo.good;
import com.good.vo.Concern;
import com.good.vo.Type;

/**
 * 商品界面
 * 

 */
public class goodPanel extends CommonPanel {

	private Vector columns;
	
	goodService goodService;
	
	TypeService typeService;
	
	ConcernService concernService;
	
	//种类下拉框
	JComboBox typeComboBox;
	
	//商家下拉框
	JComboBox concernComboBox;
	
	//商品id
	JTextField goodId;
	
	//商品名称
	JTextField goodName;
	
	//商品价格
	JTextField price;
	
	//商品介绍
	JTextArea intro;
	
	JButton clearButton;
	
	JButton saveButton;
	
	//查询中的商品名称
	JTextField nameQueryTextField;
	
	//查询按钮
	JButton queryButton;
	
	//图片上传按钮
	JButton imageButton;
	
	//文件选择器
	FileChooser chooser;
	
	//当前界面所显示的图片
	ImageIcon currentImage;
	
	//当前界面所显示图片的路径
	String currentImagePath;
	
	//显示图片的JLabel
	JLabel imageLabel;
	
	JTextField author;
	
	private final static String DEFAULT_File_Path = "upload/no_pic.gif";
	
	//查看大图的JFrame
	ImageFrame imageFrame;
	
	private void initColumns() {
		this.columns = new Vector();
		this.columns.add("id");
		this.columns.add("商品名称");
		this.columns.add("简介");
		this.columns.add("备注");
		this.columns.add("所属种类");
		this.columns.add("商家");
		this.columns.add("库存");
		this.columns.add("价格");
	}
	
	public void initImage() {
		this.currentImage = new ImageIcon(DEFAULT_File_Path);
		this.currentImagePath = DEFAULT_File_Path;
		refreshImage();
	}
	
	public goodPanel(goodService goodService, TypeService typeService, 
			ConcernService concernService) {
		this.goodService = goodService;
		this.typeService = typeService;
		this.concernService = concernService;
		setViewDatas();
		initColumns();
		
		//设置列表
		DefaultTableModel model = new DefaultTableModel(getDatas(), this.columns);
		JTable table = new CommonJTable(model);
		setJTable(table);
		
		JScrollPane upPane = new JScrollPane(table);
		upPane.setPreferredSize(new Dimension(1000, 350));
		
		//设置添加, 修改的界面
		JPanel downPane = new JPanel();
		downPane.setLayout(new BoxLayout(downPane, BoxLayout.X_AXIS));

		Box downBox1 = new Box(BoxLayout.X_AXIS);
		//添加id隐藏域
		goodId = new JTextField(10);
		goodId.setVisible(false);
		downBox1.add(goodId);
		//列表下面的box
		downBox1.add(new JLabel("商品名称："));
		downBox1.add(Box.createHorizontalStrut(10));
		goodName = new JTextField(10);
		downBox1.add(goodName);
		downBox1.add(Box.createHorizontalStrut(30));
		
		downBox1.add(new JLabel("价格："));
		downBox1.add(Box.createHorizontalStrut(10));
		price = new JTextField(10);
		downBox1.add(price);
		downBox1.add(Box.createHorizontalStrut(30));
		
		downBox1.add(new JLabel("备注："));
		downBox1.add(Box.createHorizontalStrut(10));
		author = new JTextField(10);
		downBox1.add(author);
		downBox1.add(Box.createHorizontalStrut(30));
		
		/***************************************************/
		Box downBox4 = new Box(BoxLayout.X_AXIS);

		downBox4.add(new JLabel("所属种类："));
		downBox4.add(Box.createHorizontalStrut(10));
		typeComboBox = new JComboBox();
		addTypes();
		downBox4.add(typeComboBox);
		downBox4.add(Box.createHorizontalStrut(30));
		
		downBox4.add(new JLabel("商家："));
		concernComboBox = new JComboBox();
		addConcerns();
		downBox4.add(concernComboBox);
		downBox4.add(Box.createHorizontalStrut(30));
		
		downBox4.add(new JLabel("商品图片："));
		this.chooser = new FileChooser(this);
		this.imageButton = new JButton("请选择文件");
		downBox4.add(this.imageButton);
		downBox4.add(Box.createHorizontalStrut(30));
		
		/*******************************************************/
		Box downBox2 = new Box(BoxLayout.X_AXIS);
		downBox2.add(new JLabel("商品简介："));
		downBox2.add(Box.createHorizontalStrut(10));

		intro = new JTextArea("", 5, 5);
		JScrollPane introScrollPane = new JScrollPane(intro);
		intro.setLineWrap(true);
		downBox2.add(introScrollPane);
		downBox2.add(Box.createHorizontalStrut(30));
		/*******************************************************/
		Box downBox3 = new Box(BoxLayout.X_AXIS);
		
		saveButton = new JButton("保存");
		downBox3.add(saveButton);
		downBox3.add(Box.createHorizontalStrut(30));
		
		clearButton = new JButton("清空");
		downBox3.add(clearButton);
		downBox3.add(Box.createHorizontalStrut(30));
		
		/*******************************************************/
		Box downLeftBox = new Box(BoxLayout.Y_AXIS);
		
		downLeftBox.add(getSplitBox());
		downLeftBox.add(downBox1);
		downLeftBox.add(getSplitBox());
		downLeftBox.add(downBox4);
		downLeftBox.add(getSplitBox());
		downLeftBox.add(downBox2);
		downLeftBox.add(getSplitBox());
		downLeftBox.add(downBox3);
		
		Box downRightBox = new Box(BoxLayout.Y_AXIS);
		this.imageLabel = new JLabel();
		this.imageLabel.setPreferredSize(new Dimension(200, 200));
		this.currentImage = new ImageIcon(DEFAULT_File_Path);
		this.currentImagePath = DEFAULT_File_Path;
		this.imageLabel.setIcon(this.currentImage);
		JScrollPane p = new JScrollPane(this.imageLabel);
		downRightBox.add(p);
		
		downPane.add(downLeftBox);
		downPane.add(downRightBox);
		
		/*******************查询******************/
		JPanel queryPanel = new JPanel();
		Box queryBox = new Box(BoxLayout.X_AXIS);
		queryBox.add(new JLabel("商品名："));
		queryBox.add(Box.createHorizontalStrut(30));
		nameQueryTextField = new JTextField(20);
		queryBox.add(nameQueryTextField);
		queryBox.add(Box.createHorizontalStrut(30));
		queryButton = new JButton("查询");
		queryBox.add(queryButton);
		queryPanel.add(queryBox);
		this.add(queryPanel);
		
		//列表为添加界面
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upPane, downPane);
		split.setDividerSize(5);
		this.add(split);
		//添加监听器
		initListeners();
	}
	
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
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		//保存按钮监听器
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//验证空值
				if (goodName.getText().trim().equals("")) {
					showWarn("请输入商品的名称");
					return;
				}
				save();
			}
		});
		queryButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		//图片上传按钮
		imageButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				addImage();
			}
		});
		this.imageLabel.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				showImageFrame();
			}
		});
	}
	
	//打开显示图片的JFrame
	private void showImageFrame() {
		if (this.imageFrame == null) {
			this.imageFrame = new ImageFrame(this.currentImage);
		}
		this.imageFrame.refresh(getBigImage());
	}
	
	//获取大图片的路径
	private ImageIcon getBigImage() {
		String smallImagePath = this.currentImagePath;
		if (smallImagePath.equals(this.DEFAULT_File_Path)) {
			return this.currentImage;
		}
		//获取无后缀文件名(带路径)
		String temp = smallImagePath.substring(0, smallImagePath.lastIndexOf("."));
		//拼装大图路径全文件名
		String bigImagePath = temp + "-big" + smallImagePath.substring(smallImagePath.lastIndexOf("."), 
				smallImagePath.length());
		return new ImageIcon(bigImagePath);
	}
	
	//添加文件的方法
	private void addImage() {
		chooser.showOpenDialog(this);
	}
	
	//上传图片
	public void upload(File selectFile) {
		try {
			//使用uuid生成文件名，保证文件名唯一
			String uuid =  ImageUtil.getUUID();
			//缩略图的url
			String smallFilePath = "upload/" + uuid + ".jpg";
			//原图的url
			String bigFilePath = "upload/" + uuid + "-big.jpg";
			//生成缩略图
			File file = ImageUtil.makeImage(selectFile, smallFilePath, "jpg", true);
			//生成原图
			File source = ImageUtil.makeImage(selectFile, bigFilePath, "jpg", false);
			//设置界面显示的图片对象
			this.currentImage = new ImageIcon(file.getAbsolutePath());
			//设置界面显示的图片url
			this.currentImagePath = smallFilePath;
			//刷新图片显示区
			refreshImage();
		} catch (UploadException e) {
			e.printStackTrace();
			showWarn(e.getMessage());
		}
	}
	
	//刷新图片显示的JLabel
	private void refreshImage() {
		this.imageLabel.setIcon(this.currentImage);
	}
	
	//根据名称查询商品
	private void query() {
		String name = this.nameQueryTextField.getText();
		Vector<good> goods = (Vector<good>)goodService.find(name);
		Vector<Vector> datas = changeDatas(goods);
		setDatas(datas);
		refreshTable();
	}
	
	//清空表单, 刷新列表
	public void clear() {
		refreshTable();
		this.goodId.setText("");
		this.goodName.setText("");
		this.price.setText("");
		this.intro.setText("");
		this.author.setText("");
		this.typeComboBox.removeAllItems();
		this.concernComboBox.removeAllItems();
		addTypes();
		addConcerns();
		//清空图片域
		this.currentImage = new ImageIcon(DEFAULT_File_Path);
		refreshImage();
		this.currentImagePath = DEFAULT_File_Path;
	}
	
	//保存
	private void save() {
		//如果goodId的文本框(隐藏)的值为空, 则是新增, 否则为修改
		if (this.goodId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}
	
	//新增商品
	private void add() {
		if (!validatePrice()) {
			JOptionPane.showMessageDialog(this, "请输入正确的价格");
			return;
		}
		//新增商品时库存为0
		good good = getgood();
		goodService.add(good);
		//重新读取数据
		setViewDatas();
		//刷新列表, 清空表单
		clear();
	}
	
	//验证输入
	private boolean validatePrice() {
		String price = this.price.getText();
		try {
			Integer p = Integer.parseInt(price);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//修改商品, 修改时不需要修改库存, 因为库存取决于销售与入货
	private void update() {
		good good = getgood();
		//由于是修改, 因此需要设置id
		good.setID(this.goodId.getText());
		goodService.update(good);
		//重新读取数据
		setViewDatas();
		//刷新列表, 清空表单
		clear();
	}
	
	//从界面中获取数据并封装成good对象
	private good getgood() {
		String goodName = this.goodName.getText();
		String price = this.price.getText();
		String intro = this.intro.getText();
		String author = this.author.getText();
		Type type = (Type)this.typeComboBox.getSelectedItem();
		Concern concern = (Concern)this.concernComboBox.getSelectedItem();
		return new good(null, goodName, intro, price, type.getID(), 
				concern.getID(), String.valueOf(0), this.currentImagePath, author);
	}
	
	//查看商品
	private void view() {
		String id = getSelectId(getJTable());
		good good = goodService.get(id);
		this.goodId.setText(good.getID());
		this.goodName.setText(good.getgood_NAME());
		this.price.setText(good.getgood_PRICE());
		this.intro.setText(good.getgood_INTRO());
		this.author.setText(good.getAUTHOR());
		this.typeComboBox.setSelectedItem(makeType(good.getType()));
		this.concernComboBox.setSelectedItem(makeConcern(good.getConcern()));
		this.currentImage = new ImageIcon(good.getIMAGE_URL());
		this.currentImagePath = good.getIMAGE_URL();
		refreshImage();
	}
	
	/*
	 * 实现父类方法, 查询数据库并返回对应的数据格式, 调用父类的setDatas方法设置数据集合
	 */
	public void setViewDatas() {
		//查找对应的数据
		Vector<good> goods = (Vector<good>)goodService.getAll();
		//转换显示格式
		Vector<Vector> datas =  changeDatas(goods);
		//调用父类方法设置结果集合
		setDatas(datas);
	}
	
	//将数据转换成视图表格的格式
	private Vector<Vector> changeDatas(Vector<good> datas) {
		Vector<Vector> view = new Vector<Vector>();
		for (good good : datas) {
			Vector v = new Vector();
			v.add(good.getID());
			v.add(good.getgood_NAME());
			v.add(good.getgood_INTRO());
			v.add(good.getAUTHOR());
			v.add(good.getType().getTYPE_NAME());
			v.add(good.getConcern().getPUB_NAME());
			v.add(good.getREPERTORY_SIZE());
			v.add(good.getgood_PRICE());
			view.add(v);
		}
		return view;
	}

	@Override
	public Vector<String> getColumns() {
		return this.columns;
	}

	@Override
	public void setTableFace() {
		//隐藏id列
		getJTable().getColumn("id").setMinWidth(-1);
		getJTable().getColumn("id").setMaxWidth(-1);
		getJTable().getColumn("简介").setMinWidth(350);
		getJTable().setRowHeight(30);
	}
	
	//从数据库中获取全部的种类并添加到下拉框中
	private void addTypes() {
		//调用种类业务接口取得全部的种类
		Collection<Type> types = this.typeService.getAll();
		for (Type type : types) {
			//typeComboBox是种类下拉框对象
			this.typeComboBox.addItem(makeType(type));
		}
	}
	
	//从数据库中获取全部的商家并添加到下拉框中
	private void addConcerns() {
		Collection<Concern> concers = this.concernService.getAll();
		for (Concern c : concers) {
			this.concernComboBox.addItem(makeConcern(c));
		}
	}

	//创建一个Type对象, 用于添加到下拉框中, 该方法中创建的Type对象重写了toString和equals方法
	private Type makeType(final Type source) {
		Type type = new Type(){
			public String toString(){
				return source.getTYPE_NAME();
			}
			public boolean equals(Object obj) {
				if (obj instanceof Type) {
					Type t = (Type)obj;
					if (getID().equals(t.getID())) return true;
				}
				return false;
			}
		};
		type.setID(source.getID());
		return type;
	}
	
	//创建一个Concern对象, 用于添加到下拉框中, 该方法中创建的Concern对象重写了toString和equals方法
	private Concern makeConcern(final Concern c) {
		Concern concern = new Concern() {
			public String toString(){
				return c.getPUB_NAME();
			}
			public boolean equals(Object obj) {
				if (obj instanceof Concern) {
					Concern co = (Concern)obj;
					if (getID().equals(co.getID())) return true;
				}
				return false;
			}
		};
		concern.setID(c.getID());
		return concern;
	}
	
	
}

class FileChooser extends JFileChooser {
	//商品管理界面对象
	goodPanel goodPanel;
	public FileChooser(goodPanel goodPanel){
		this.goodPanel = goodPanel;
	}
	//选择了文件后触发
	public void approveSelection() {
		//获得选择的文件
		File file = getSelectedFile();
		//调用商品管理界面对象的upload方法
		this.goodPanel.upload(file);
		super.approveSelection();
	}
}