package com.piccus.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class AppUI {

	private JFrame frame;
	
	private JPanel bodyPanel;
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel thirdPanel;
	private JPanel fouthPanel;
	private JPanel fifthPanel;
	private JPanel sixthPanel;
	private JPanel seventhPanel;
	private JPanel eighthPanel;
	
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
		initFirstPanel();
		initSecondPanel();
		initThirdPanel();
		initFouthPanel();
		initFifthPanel();
		initSixthPanel();
		initSeventhPanel();
		initEighthPanel();
		initBodyPanel();
		frame.setContentPane(bodyPanel);
	}
	
	private void initBodyPanel(){
		bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		bodyPanel.add(firstPanel);
		bodyPanel.add(secondPanel);
		bodyPanel.add(thirdPanel);
		bodyPanel.add(fouthPanel);
		bodyPanel.add(fifthPanel);
		bodyPanel.add(sixthPanel);
		bodyPanel.add(seventhPanel);
		bodyPanel.add(eighthPanel);
	}
	
	private void initFirstPanel(){
		firstPanel = new JPanel();
		firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
		firstPanel.add(Box.createVerticalStrut(10));
	}
	
	private void initSecondPanel(){
		secondPanel = new JPanel();
		secondPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
		secondPanel.setSize(320, 20);
		JLabel info = new JLabel("设置数据库名称（默认为news）:");
		JButton button = new JButton("保存");
		JTextField  dbNameTextField = new JTextField();
		secondPanel.add(Box.createHorizontalStrut(20));
		secondPanel.add(info);
		secondPanel.add(dbNameTextField);
		secondPanel.add(Box.createHorizontalStrut(20));
		secondPanel.add(button);
		secondPanel.add(Box.createHorizontalStrut(20));
	}
	
	private void initThirdPanel(){
		thirdPanel = new JPanel();
		thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
		thirdPanel.add(Box.createVerticalStrut(10));
	}
	
	private void initFouthPanel(){
		fouthPanel = new JPanel();
		fouthPanel.setLayout(new BoxLayout(fouthPanel, BoxLayout.X_AXIS));
		JLabel info = new JLabel("选择要爬取的门户网站：");	
		fouthPanel.add(Box.createHorizontalStrut(20));
		fouthPanel.add(info);
		fouthPanel.add(Box.createHorizontalGlue());
	}
	
	private void initFifthPanel(){
		fifthPanel = new JPanel();
		fifthPanel.setLayout(new BoxLayout(fifthPanel, BoxLayout.X_AXIS));
		JCheckBox sinaCheckBox = new JCheckBox("新浪");
		JCheckBox neteaseCheckBox = new JCheckBox("网易");
		JCheckBox sohuCheckBox = new JCheckBox("搜狐");
		JButton button = new JButton("爬取新闻");
		fifthPanel.add(Box.createHorizontalStrut(20));
		fifthPanel.add(sinaCheckBox);
		fifthPanel.add(neteaseCheckBox);
		fifthPanel.add(sohuCheckBox);
		fifthPanel.add(Box.createHorizontalGlue());
		fifthPanel.add(button);
		fifthPanel.add(Box.createHorizontalStrut(20));
	}
	
	private void initSixthPanel() {
		sixthPanel = new JPanel();
		sixthPanel.setLayout(new BoxLayout(sixthPanel, BoxLayout.Y_AXIS));
		sixthPanel.add(Box.createVerticalStrut(10));
	}
	
	private void initSeventhPanel() {
		seventhPanel = new JPanel();
		seventhPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		seventhPanel.setLayout(new BoxLayout(seventhPanel, BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		seventhPanel.add(topPanel);
		seventhPanel.add(bottomPanel);
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		JLabel info = new JLabel("新闻搜索爬虫：");
		JTextField searchTextField = new JTextField();
		JRadioButton minRadioButton = new JRadioButton("10条");
		JRadioButton midRadioButton = new JRadioButton("20条");
		JRadioButton maxRadioButton = new JRadioButton("50条");
		topPanel.add(Box.createHorizontalStrut(20));
		topPanel.add(info);
		topPanel.add(searchTextField);
		topPanel.add(minRadioButton);
		topPanel.add(midRadioButton);
		topPanel.add(maxRadioButton);
		topPanel.add(Box.createHorizontalStrut(20));
		JLabel poweredBy = new JLabel("Powered By Piccus!");
		JButton button = new JButton("爬取新闻");
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(Box.createHorizontalStrut(20));
		bottomPanel.add(poweredBy);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(button);
		bottomPanel.add(Box.createHorizontalStrut(20));
	}
	
	private void initEighthPanel() {
		eighthPanel = new JPanel();
		eighthPanel.setLayout(new BoxLayout(eighthPanel, BoxLayout.Y_AXIS));
		eighthPanel.add(Box.createVerticalStrut(10));
	}
}
