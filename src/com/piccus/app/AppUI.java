package com.piccus.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
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

import javax.swing.border.TitledBorder;

import com.piccus.core.BaiduNewsThread;
import com.piccus.core.NeteaseNewsThread;
import com.piccus.core.SinaNewsThread;
import com.piccus.core.SohuNewsThread;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class AppUI {
	//主窗口frame
	private JFrame frame;
	//主窗口Panel
	private JPanel bodyPanel;
	//顶部空白Panel
	private JPanel topBlankPanel;
	//数据库名称设置Panel
	private JPanel dbNamePanel;
	//中间空白Panel
	private JPanel sBlankPanel;
	//门户新闻爬取Panel
	private JPanel headlinePanel;
	//中间空白Panel
	private JPanel tPanel;
	//百度新闻搜索Panel
	private JPanel baiduPanel;
	//底部空白Panel
	private JPanel bottomBlankPanel;

	//数据库名称设置域
	private JTextField dbNameTextField;
	//搜索关键词域
	private JTextField searchTextField;
	
	//分别为新浪 网易 搜狐爬取判断
	private int sinaThreadMark = 0;
	private int neteaseThreadMark = 0;
	private int sohuThreadMark = 0;
	//百度新闻搜索条目
	private int baiduNewsNumber = 10;
	//数据库名称
	private String dbName = "news";
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
		frame.setSize(400, 220);
		frame.setResizable(false);
		initTopBlankPanel();
		initDbNamePanel();
		initSBlankPanel();
		initHeadlinePanel();
		initTBlankPanel();
		initBaiduPanel();
		initBottomBlankPanel();
		initBodyPanel();
		frame.setContentPane(bodyPanel);
	}
	
	//初始化主窗口Panel
	private void initBodyPanel(){
		bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		bodyPanel.add(topBlankPanel);
		bodyPanel.add(dbNamePanel);
		bodyPanel.add(sBlankPanel);
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
	 * @Description: 初始化dbNamePanel
	 */
	private void initDbNamePanel(){
		dbNamePanel = new JPanel();
		dbNamePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		dbNamePanel.setLayout(new BoxLayout(dbNamePanel, BoxLayout.X_AXIS));
		dbNamePanel.setSize(320, 20);
		JLabel info = new JLabel("设置数据库名称（默认为news）:");
		dbNameTextField = new JTextField();
		JButton dbButton = new JButton("保存");
		dbButton.addActionListener(new DbButtonHandle());
		dbNamePanel.add(Box.createHorizontalStrut(20));
		dbNamePanel.add(info);
		dbNamePanel.add(dbNameTextField);
		dbNamePanel.add(Box.createHorizontalStrut(20));
		dbNamePanel.add(dbButton);
		dbNamePanel.add(Box.createHorizontalStrut(20));
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化中间空白Panel
	 */
	private void initSBlankPanel(){
		sBlankPanel = new JPanel();
		sBlankPanel.setLayout(new BoxLayout(sBlankPanel, BoxLayout.Y_AXIS));
		sBlankPanel.add(Box.createVerticalStrut(10));
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
		headlinePanel.add(topPanel);
		headlinePanel.add(bottomPanel);
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		JLabel info = new JLabel("选择要爬取的门户网站：");	
		topPanel.add(Box.createHorizontalStrut(20));
		topPanel.add(info);
		topPanel.add(Box.createHorizontalGlue());
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		JCheckBox sinaCheckBox = new JCheckBox("新浪");
		JCheckBox neteaseCheckBox = new JCheckBox("网易");
		JCheckBox sohuCheckBox = new JCheckBox("搜狐");
		sinaCheckBox.addItemListener(new SinaCheckBoxHandle());
		neteaseCheckBox.addItemListener(new NeteaseCheckBoxHandle());
		sohuCheckBox.addItemListener(new SohuCheckBoxHandle());
		
		JButton headlineButton = new JButton("爬取新闻");
		headlineButton.addActionListener(new HeadlineButtonHandle());
		bottomPanel.add(Box.createHorizontalStrut(20));
		bottomPanel.add(sinaCheckBox);
		bottomPanel.add(neteaseCheckBox);
		bottomPanel.add(sohuCheckBox);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(headlineButton);
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
		JPanel bottomPanel = new JPanel();
		baiduPanel.add(topPanel);
		baiduPanel.add(bottomPanel);
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		JLabel info = new JLabel("新闻搜索爬虫：");
		searchTextField = new JTextField();
		
		JRadioButton minRadioButton = new JRadioButton("10条");
		JRadioButton midRadioButton = new JRadioButton("20条");
		JRadioButton maxRadioButton = new JRadioButton("50条");
		ButtonGroup bg = new ButtonGroup();
		minRadioButton.addActionListener(new MinRadioButtonHandle());
		midRadioButton.addActionListener(new MidRadioButtonHandle());
		maxRadioButton.addActionListener(new MaxRadioButtonHandle());
		topPanel.add(Box.createHorizontalStrut(20));
		topPanel.add(info);
		topPanel.add(searchTextField);
		topPanel.add(minRadioButton);
		topPanel.add(midRadioButton);
		topPanel.add(maxRadioButton);
		minRadioButton.setSelected(true);
		bg.add(minRadioButton);
		bg.add(midRadioButton);
		bg.add(maxRadioButton);
		topPanel.add(Box.createHorizontalStrut(20));
		JLabel poweredBy = new JLabel("Powered By Piccus!");
		JButton searchButton = new JButton("爬取新闻");
		searchButton.addActionListener(new SearchButtonHandle());
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(Box.createHorizontalStrut(20));
		bottomPanel.add(poweredBy);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(searchButton);
		bottomPanel.add(Box.createHorizontalStrut(20));
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
	 * @Description: headlineButton触发器
	 */
	private class HeadlineButtonHandle implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(sinaThreadMark == 1){
				SinaNewsThread sinaNewsThread = new SinaNewsThread(dbName);
				sinaNewsThread.start();
			}
			if(neteaseThreadMark == 1){
				NeteaseNewsThread neteaseNewsThread = new NeteaseNewsThread(dbName);
				neteaseNewsThread.start();
			}
			if(sohuThreadMark == 1){
				SohuNewsThread sohuNewsThread = new SohuNewsThread(dbName);
				sohuNewsThread.start();
			}
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: dbButton触发器
	 */
	private class DbButtonHandle implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dbName = dbNameTextField.getText();
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
			BaiduNewsThread baiduNewsThread = new BaiduNewsThread(searchTextField.getText(), baiduNewsNumber, dbName);
			baiduNewsThread.start();
		}
		
	}
}
