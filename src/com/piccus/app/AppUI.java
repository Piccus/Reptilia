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
	//������frame
	private JFrame frame;
	//������Panel
	private JPanel bodyPanel;
	//�����հ�Panel
	private JPanel topBlankPanel;
	//���ݿ���������Panel
	private JPanel dbNamePanel;
	//�м�հ�Panel
	private JPanel sBlankPanel;
	//�Ż�������ȡPanel
	private JPanel headlinePanel;
	//�м�հ�Panel
	private JPanel tPanel;
	//�ٶ���������Panel
	private JPanel baiduPanel;
	//�ײ��հ�Panel
	private JPanel bottomBlankPanel;

	//���ݿ�����������
	private JTextField dbNameTextField;
	//�����ؼ�����
	private JTextField searchTextField;
	
	//�ֱ�Ϊ���� ���� �Ѻ���ȡ�ж�
	private int sinaThreadMark = 0;
	private int neteaseThreadMark = 0;
	private int sohuThreadMark = 0;
	//�ٶ�����������Ŀ
	private int baiduNewsNumber = 10;
	//���ݿ�����
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
		frame.setTitle("�ȵ�ƾ���������");
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
	
	//��ʼ��������Panel
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
	 * @Description: ��ʼ�������հ�Panel
	 */
	private void initTopBlankPanel(){
		topBlankPanel = new JPanel();
		topBlankPanel.setLayout(new BoxLayout(topBlankPanel, BoxLayout.Y_AXIS));
		topBlankPanel.add(Box.createVerticalStrut(10));
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ��dbNamePanel
	 */
	private void initDbNamePanel(){
		dbNamePanel = new JPanel();
		dbNamePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		dbNamePanel.setLayout(new BoxLayout(dbNamePanel, BoxLayout.X_AXIS));
		dbNamePanel.setSize(320, 20);
		JLabel info = new JLabel("�������ݿ����ƣ�Ĭ��Ϊnews��:");
		dbNameTextField = new JTextField();
		JButton dbButton = new JButton("����");
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
	 * @Description: ��ʼ���м�հ�Panel
	 */
	private void initSBlankPanel(){
		sBlankPanel = new JPanel();
		sBlankPanel.setLayout(new BoxLayout(sBlankPanel, BoxLayout.Y_AXIS));
		sBlankPanel.add(Box.createVerticalStrut(10));
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ��headlPanel
	 */
	private void initHeadlinePanel(){
		headlinePanel = new JPanel();
		headlinePanel.setLayout(new BoxLayout(headlinePanel, BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		headlinePanel.add(topPanel);
		headlinePanel.add(bottomPanel);
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		JLabel info = new JLabel("ѡ��Ҫ��ȡ���Ż���վ��");	
		topPanel.add(Box.createHorizontalStrut(20));
		topPanel.add(info);
		topPanel.add(Box.createHorizontalGlue());
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		JCheckBox sinaCheckBox = new JCheckBox("����");
		JCheckBox neteaseCheckBox = new JCheckBox("����");
		JCheckBox sohuCheckBox = new JCheckBox("�Ѻ�");
		sinaCheckBox.addItemListener(new SinaCheckBoxHandle());
		neteaseCheckBox.addItemListener(new NeteaseCheckBoxHandle());
		sohuCheckBox.addItemListener(new SohuCheckBoxHandle());
		
		JButton headlineButton = new JButton("��ȡ����");
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
	 * @Description: ��ʼ���м�հ�Panel
	 */
	private void initTBlankPanel() {
		tPanel = new JPanel();
		tPanel.setLayout(new BoxLayout(tPanel, BoxLayout.Y_AXIS));
		tPanel.add(Box.createVerticalStrut(10));
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ��baiduPanel
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
		JLabel info = new JLabel("�����������棺");
		searchTextField = new JTextField();
		
		JRadioButton minRadioButton = new JRadioButton("10��");
		JRadioButton midRadioButton = new JRadioButton("20��");
		JRadioButton maxRadioButton = new JRadioButton("50��");
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
		JButton searchButton = new JButton("��ȡ����");
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
	 * @Description: ��ʼ���ײ��հ�Panel
	 */
	private void initBottomBlankPanel() {
		bottomBlankPanel = new JPanel();
		bottomBlankPanel.setLayout(new BoxLayout(bottomBlankPanel, BoxLayout.Y_AXIS));
		bottomBlankPanel.add(Box.createVerticalStrut(10));
	}
	
	/*
	 * @Author: Piccus
	 * @Description: sinaCheckBox������
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
	 * @Description: neteaseCheckBox������
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
	 * @Description: sohuCheckBox������
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
	 * @Description: headlineButton������
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
	 * @Description: dbButton������
	 */
	private class DbButtonHandle implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dbName = dbNameTextField.getText();
		}
		
	}
	
	/*
	 * @Author: Piccus
	 * @Description: minRadioButton������
	 */
	private class MinRadioButtonHandle implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			baiduNewsNumber = 10;
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: midRadioButton������
	 */
	private class MidRadioButtonHandle implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			baiduNewsNumber = 20;
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: axRadioButton������
	 */
	private class MaxRadioButtonHandle implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			baiduNewsNumber = 50;
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: searchButton������
	 */
	private class SearchButtonHandle implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			BaiduNewsThread baiduNewsThread = new BaiduNewsThread(searchTextField.getText(), baiduNewsNumber, dbName);
			baiduNewsThread.start();
		}
		
	}
}
