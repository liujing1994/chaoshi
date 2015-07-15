package com.good.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import com.good.dao.goodDao;
import com.good.dao.goodInRecordDao;
import com.good.dao.goodSaleRecordDao;
import com.good.dao.ConcernDao;
import com.good.dao.InRecordDao;
import com.good.dao.SaleRecordDao;
import com.good.dao.TypeDao;
import com.good.dao.impl.goodDaoImpl;
import com.good.dao.impl.goodInRecordDaoImpl;
import com.good.dao.impl.goodSaleRecordDaoImpl;
import com.good.dao.impl.ConcernDaoImpl;
import com.good.dao.impl.InRecordDaoImpl;
import com.good.dao.impl.SaleRecordDaoImpl;
import com.good.dao.impl.TypeDaoImpl;
import com.good.service.goodService;
import com.good.service.ConcernService;
import com.good.service.InRecordService;
import com.good.service.SaleRecordService;
import com.good.service.TypeService;
import com.good.service.impl.goodServiceImpl;
import com.good.service.impl.ConcernServiceImpl;
import com.good.service.impl.InRecordServiceImpl;
import com.good.service.impl.SaleRecordServiceImpl;
import com.good.service.impl.TypeServiceImpl;

/**
 * 主界面的JFrame
 */
public class MainFrame extends JFrame{
	
	SalePanel salePanel;
	
	RepertoryPanel repertoryPanel;
	
	goodPanel goodPanel;
	
	ConcernPanel concernPanel;
	
	TypePanel typePanel;
	
	CommonPanel currentPanel;
	
	//业务接口
	TypeService typeService;
	
	ConcernService concernService;
	
	goodService goodService;
	
	SaleRecordService saleRecordService;
	
	InRecordService inRecordService;
	
	private Action sale = new AbstractAction("销售管理", new ImageIcon("images/sale.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(salePanel);
		}
	};
	
	private Action repertory = new AbstractAction("库存管理", new ImageIcon("images/repertory.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(repertoryPanel);
		}
	};

	private Action good = new AbstractAction("商品管理", new ImageIcon("images/good.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(goodPanel);
			goodPanel.initImage();
			goodPanel.repaint();
		}
	};
	
	private Action type = new AbstractAction("种类管理", new ImageIcon("images/type.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(typePanel);
		}
	};
	
	private Action concern = new AbstractAction("商家管理", new ImageIcon("images/concern.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(concernPanel);
		}
	};
	
	public MainFrame() {
		TypeDao typeDao = new TypeDaoImpl();
		ConcernDao concernDao = new ConcernDaoImpl();
		goodDao goodDao = new goodDaoImpl();
		SaleRecordDao saleRecordDao = new SaleRecordDaoImpl();
		goodSaleRecordDao goodSaleRecordDao = new goodSaleRecordDaoImpl();
		InRecordDao inRecordDao = new InRecordDaoImpl();
		goodInRecordDao goodInRecordDao = new goodInRecordDaoImpl();
		this.typeService = new TypeServiceImpl(typeDao);
		this.concernService = new ConcernServiceImpl(concernDao);
		this.goodService = new goodServiceImpl(goodDao, typeDao, concernDao);
		this.saleRecordService = new SaleRecordServiceImpl(saleRecordDao, 
				goodSaleRecordDao, goodDao);
		this.inRecordService = new InRecordServiceImpl(inRecordDao, goodInRecordDao, goodDao);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("销售管理");
		JMenu menu2 = new JMenu("库存管理");
		JMenu menu3 = new JMenu("商品管理");
		JMenu menu4 = new JMenu("种类管理");
		JMenu menu5 = new JMenu("商家管理");
	
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		menuBar.add(menu5);

		menu1.add(sale).setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.ALT_MASK));
		menu2.add(repertory).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK));
		menu3.add(good).setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.CTRL_MASK));
		menu4.add(type).setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_MASK));
		menu5.add(concern).setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_MASK));
		
		//让界面作为第一显示界面
		this.salePanel = new SalePanel(this.goodService, this.saleRecordService);
		this.add(salePanel);
		this.currentPanel = salePanel;
		//初始化销售界面的数据
		this.salePanel.initData();
		
		//初始化库存管理界面
		repertoryPanel = new RepertoryPanel(this.inRecordService, this.goodService);
		//初始化商品管理界面
		goodPanel = new goodPanel(this.goodService, this.typeService, 
				this.concernService);
		//初始化商家管理界面
		concernPanel = new ConcernPanel(this.concernService);
		//初始化种类管理界面
		typePanel = new TypePanel(this.typeService);
		 
		this.setJMenuBar(menuBar);
		this.setTitle("商品进存销管理系统");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	
		Dimension scSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fSize = this.getSize();    //设置窗体大小
		if (fSize.height > scSize.height) {
			fSize.height = scSize.height;
		}
		if (fSize.width > scSize.width) {
			fSize.width = scSize.width;
		}
		this.setLocation((scSize.width - fSize.width) / 3,
				(scSize.height - fSize.height) / 3);
 		
	}
	
	//切换各个界面
	private void changePanel(CommonPanel commonPanel) {
		//移除当前显示的JPanel
		this.remove(currentPanel);
		//添加需要显示的JPanel
		this.add(commonPanel);
		//设置当前的JPanel
		this.currentPanel = commonPanel;
		this.repaint();
		this.setVisible(true);
		//重新读取数据
		commonPanel.setViewDatas();
		//刷新列表
		commonPanel.clear();
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
