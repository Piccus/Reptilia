package com.piccus.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.border.TitledBorder;

import com.piccus.core.AutoUpdateThread;
import com.piccus.core.BaiduNewsThread;
import com.piccus.core.HeadlineNewsThread;
import com.piccus.tools.DBControl;




public class AppUI {
	//主窗口frame
	private JFrame frame;
	//主窗口Panel
	private JPanel bodyPanel;
	//顶部空白Panel
	private JPanel topBlankPanel;

	//门户新闻爬取Panel
	private JPanel headlinePanel;
	//中间空白Panel
	private JPanel tPanel;
	//百度新闻搜索Panel
	private JPanel baiduPanel;
	//底部空白Panel
	private JPanel bottomBlankPanel;

	

	//门户网站新闻显示域
	private JList headlineList;
	//百度新闻显示域
	private JList baiduList;
	//空白显示数据
	private Vector blankVector = new Vector();
	
	private BaiduNewsThread baiduNewsThread;
	//搜索关键词域
	private JTextField searchTextField;
	//百度新闻总数标签
	private JLabel baiduNewsCountLabel;
	//分别为新浪 网易 搜狐爬取判断
	private int sinaThreadMark = 0;
	private int neteaseThreadMark = 0;
	private int sohuThreadMark = 0;
	//分别为新浪 网易 搜狐显示判断
	private int sinaShowThreadMark = 0;
	private int neteaseShowThreadMark = 0;
	private int sohuShowThreadMark = 0;
	//百度新闻搜索条目
	private int baiduNewsNumber = 10;
	//百度新闻页码
	private int page = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppUI window = new AppUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("热点财经新闻爬虫");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 810);
		frame.setResizable(false);
		initTopBlankPanel();

		initHeadlinePanel();
		initTBlankPanel();
		initBaiduPanel();
		initBottomBlankPanel();
		initBodyPanel();
		frame.setContentPane(bodyPanel);
		AutoUpdateThread autoUpdateThread = new AutoUpdateThread();
		Thread td = new Thread(autoUpdateThread);
		td.start();
	}
	
	//初始化主窗口Panel
	private void initBodyPanel(){
		bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		bodyPanel.add(topBlankPanel);
		bodyPanel.add(headlinePanel);
		bodyPanel.add(tPanel);
		bodyPanel.add(baiduPanel);
		bodyPanel.add(bottomBlankPanel);
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化顶部空白Panel
	 */
	private void initTopBlankPanel(){
		topBlankPanel = new JPanel();
		topBlankPanel.setLayout(new BoxLayout(topBlankPanel, BoxLayout.Y_AXIS));
		topBlankPanel.add(Box.createVerticalStrut(10));
	}
	
	
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化headlPanel
	 */
	private void initHeadlinePanel(){
		headlinePanel = new JPanel();
		headlinePanel.setLayout(new BoxLayout(headlinePanel, BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		headlinePanel.add(topPanel);
		headlinePanel.add(Box.createVerticalStrut(5));
		headlinePanel.add(middlePanel);
		headlinePanel.add(Box.createVerticalStrut(5));
		headlinePanel.add(bottomPanel);
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		JLabel info = new JLabel("手动更新爬取门户网站选择：");	
		topPanel.add(Box.createHorizontalStrut(20));
		topPanel.add(info);
		topPanel.add(Box.createHorizontalStrut(10));
		JCheckBox sinaCheckBox = new JCheckBox("新浪");
		JCheckBox neteaseCheckBox = new JCheckBox("网易");
		JCheckBox sohuCheckBox = new JCheckBox("搜狐");
		JButton headlineButton = new JButton("更新爬取数据库");
		topPanel.add(sinaCheckBox);
		topPanel.add(neteaseCheckBox);
		topPanel.add(sohuCheckBox);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.add(headlineButton);
		topPanel.add(Box.createHorizontalStrut(20));

		
		sinaCheckBox.addItemListener(new SinaCheckBoxHandle());
		neteaseCheckBox.addItemListener(new NeteaseCheckBoxHandle());
		sohuCheckBox.addItemListener(new SohuCheckBoxHandle());
		headlineButton.addActionListener(new HeadlineButtonHandle());
		
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		JLabel label = new JLabel("门户网站新闻显示选择：");	
		middlePanel.add(Box.createHorizontalStrut(20));
		middlePanel.add(label);
		middlePanel.add(Box.createHorizontalStrut(10));
		JCheckBox sinaShowCheckBox = new JCheckBox("新浪");
		JCheckBox neteaseShowCheckBox = new JCheckBox("网易");
		JCheckBox sohuShowCheckBox = new JCheckBox("搜狐");
		JButton headlineShowButton = new JButton("显示新闻");
		
		headlineShowButton.addActionListener(new HeadlineShowButtonHandle());
		sinaShowCheckBox.addItemListener(new SinaShowCheckBoxHandle());
		neteaseShowCheckBox.addItemListener(new NeteaseShowCheckBoxHandle());
		sohuShowCheckBox.addItemListener(new SohuShowCheckBoxHandle());
		
		middlePanel.add(sinaShowCheckBox);
		middlePanel.add(neteaseShowCheckBox);
		middlePanel.add(sohuShowCheckBox);
		middlePanel.add(Box.createHorizontalGlue());
		middlePanel.add(headlineShowButton);
		middlePanel.add(Box.createHorizontalStrut(20));
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		headlineList = new JList(blankVector);
		headlineList.setVisibleRowCount(17);
		JScrollPane scroll = new JScrollPane(headlineList); 
		//分别设置水平和垂直滚动条总是出现 
		scroll.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scroll.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		bottomPanel.add(Box.createHorizontalStrut(20));
		bottomPanel.add(scroll);
		bottomPanel.add(Box.createHorizontalStrut(20));
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化中间空白Panel
	 */
	private void initTBlankPanel() {
		tPanel = new JPanel();
		tPanel.setLayout(new BoxLayout(tPanel, BoxLayout.Y_AXIS));
		tPanel.add(Box.createVerticalStrut(10));
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化baiduPanel
	 */
	private void initBaiduPanel() {
		baiduPanel = new JPanel();
		baiduPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		baiduPanel.setLayout(new BoxLayout(baiduPanel, BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		baiduPanel.add(Box.createVerticalStrut(10));
		baiduPanel.add(topPanel);
		baiduPanel.add(middlePanel);
		baiduPanel.add(bottomPanel);
		baiduPanel.add(Box.createVerticalStrut(5));
		baiduPanel.add(buttonPanel);
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		JLabel info = new JLabel("百度新闻搜索爬虫：");
		searchTextField = new JTextField();
		JButton searchButton = new JButton("爬取新闻");
		searchButton.addActionListener(new SearchButtonHandle());
		topPanel.add(Box.createHorizontalStrut(20));
		topPanel.add(info);
		topPanel.add(searchTextField);
		topPanel.add(Box.createHorizontalStrut(150));
		topPanel.add(searchButton);
		topPanel.add(Box.createHorizontalStrut(20));
		
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		JLabel label = new JLabel("单页显示数量:");
		JRadioButton minRadioButton = new JRadioButton("10条");
		JRadioButton midRadioButton = new JRadioButton("20条");
		JRadioButton maxRadioButton = new JRadioButton("50条");
		ButtonGroup bg = new ButtonGroup();
		minRadioButton.addActionListener(new MinRadioButtonHandle());
		midRadioButton.addActionListener(new MidRadioButtonHandle());
		maxRadioButton.addActionListener(new MaxRadioButtonHandle());
		
		middlePanel.add(label);
		middlePanel.add(Box.createHorizontalStrut(10));
		middlePanel.add(minRadioButton);
		middlePanel.add(midRadioButton);
		middlePanel.add(maxRadioButton);
		minRadioButton.setSelected(true);
		bg.add(minRadioButton);
		bg.add(midRadioButton);
		bg.add(maxRadioButton);
		middlePanel.add(Box.createHorizontalStrut(20));
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		baiduList = new JList(blankVector);
		baiduList.setVisibleRowCount(17);

		JScrollPane scroll = new JScrollPane(baiduList); 
		//分别设置水平和垂直滚动条总是出现 
		scroll.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scroll.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		bottomPanel.add(Box.createHorizontalStrut(20));
		bottomPanel.add(scroll);
		bottomPanel.add(Box.createHorizontalStrut(20));
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JLabel showCountLabel = new JLabel("搜索到条数：");
		baiduNewsCountLabel = new JLabel("");
		JButton uppageButton = new JButton("上一页");
		JButton downpageButton = new JButton("下一页");
		uppageButton.addActionListener(new UppageButtonHandle());
		downpageButton.addActionListener(new DownpageButtonHandle());
		
		buttonPanel.add(Box.createHorizontalStrut(20));
		buttonPanel.add(showCountLabel);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(baiduNewsCountLabel);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(uppageButton);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(downpageButton);
		buttonPanel.add(Box.createHorizontalStrut(20));
		
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化底部空白Panel
	 */
	private void initBottomBlankPanel() {
		bottomBlankPanel = new JPanel();
		bottomBlankPanel.setLayout(new BoxLayout(bottomBlankPanel, BoxLayout.Y_AXIS));
		bottomBlankPanel.add(Box.createVerticalStrut(10));
	}
	
	/*
	 * @Author: Piccus
	 * @Description: sinaCheckBox触发器
	 */
	private class SinaCheckBoxHandle implements ItemListener {

		JCheckBox checkBox;
		@Override
		public void itemStateChanged(ItemEvent e) {
			checkBox = (JCheckBox) e.getSource();
			if(checkBox.isSelected()){
				sinaThreadMark = 1;
			}else{
				sinaThreadMark = 0;
			}
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: neteaseCheckBox触发器
	 */
	private class NeteaseCheckBoxHandle implements ItemListener {

		JCheckBox checkBox;
		@Override
		public void itemStateChanged(ItemEvent e) {
			checkBox = (JCheckBox) e.getSource();
			if(checkBox.isSelected()){
				neteaseThreadMark = 1;
			}else{
				neteaseThreadMark = 0;
			}	
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: sohuCheckBox触发器
	 */
	private class SohuCheckBoxHandle implements ItemListener {
		
		JCheckBox checkBox;
		@Override
		public void itemStateChanged(ItemEvent e) {
			checkBox = (JCheckBox) e.getSource();
			if(checkBox.isSelected()){
				sohuThreadMark = 1;
			}else{
				sohuThreadMark = 0;
			}	
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: sinaShowCheckBox触发器
	 */
	private class SinaShowCheckBoxHandle implements ItemListener {

		JCheckBox checkBox;
		@Override
		public void itemStateChanged(ItemEvent e) {
			checkBox = (JCheckBox) e.getSource();
			if(checkBox.isSelected()){
				sinaShowThreadMark = 1;
			}else{
				sinaShowThreadMark = 0;
			}
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: neteaseShowCheckBox触发器
	 */
	private class NeteaseShowCheckBoxHandle implements ItemListener {

		JCheckBox checkBox;
		@Override
		public void itemStateChanged(ItemEvent e) {
			checkBox = (JCheckBox) e.getSource();
			if(checkBox.isSelected()){
				neteaseShowThreadMark = 1;
			}else{
				neteaseShowThreadMark = 0;
			}	
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: sohuShowCheckBox触发器
	 */
	private class SohuShowCheckBoxHandle implements ItemListener {
		
		JCheckBox checkBox;
		@Override
		public void itemStateChanged(ItemEvent e) {
			checkBox = (JCheckBox) e.getSource();
			if(checkBox.isSelected()){
				sohuShowThreadMark = 1;
			}else{
				sohuShowThreadMark = 0;
			}	
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: headlineButton触发器
	 */
	private class HeadlineButtonHandle implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			HeadlineNewsThread headlineNewsThread = new HeadlineNewsThread(sinaThreadMark, neteaseThreadMark, sohuThreadMark);
			Thread thread = new Thread(headlineNewsThread);
			thread.start();
		}
	}
	
	
	/*
	 * @Author: Piccus
	 * @Description: minRadioButton触发器
	 */
	private class MinRadioButtonHandle implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			baiduNewsNumber = 10;
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: midRadioButton触发器
	 */
	private class MidRadioButtonHandle implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			baiduNewsNumber = 20;
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: axRadioButton触发器
	 */
	private class MaxRadioButtonHandle implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			baiduNewsNumber = 50;
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: searchButton触发器
	 */
	private class SearchButtonHandle implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			page = 0;
			baiduNewsThread = new BaiduNewsThread(searchTextField.getText(), baiduNewsNumber, page);
			baiduList.setListData(baiduNewsThread.getData());
			baiduNewsCountLabel.setText(baiduNewsThread.getAllCount());
		}
	}
	
	private class UppageButtonHandle implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(page > 0){
				page --;
				baiduNewsThread = new BaiduNewsThread(searchTextField.getText(), baiduNewsNumber, page);
				baiduList.setListData(baiduNewsThread.getData());
			}
		}
	}
	
	private class DownpageButtonHandle implements ActionListener{	

		@Override
		public void actionPerformed(ActionEvent arg0) {
		
			page ++;
			baiduNewsThread = new BaiduNewsThread(searchTextField.getText(), baiduNewsNumber, page);
			baiduList.setListData(baiduNewsThread.getData());
			
		}
	}
	
	private class HeadlineShowButtonHandle implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(sinaShowThreadMark != 0 || neteaseShowThreadMark != 0 || sohuShowThreadMark != 0)
			headlineList.setListData(DBControl.searchNews(sinaShowThreadMark, neteaseShowThreadMark, sohuShowThreadMark));
		}
		
	}
}
