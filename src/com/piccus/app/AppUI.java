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
	//������frame
	private JFrame frame;
	//������Panel
	private JPanel bodyPanel;
	//�����հ�Panel
	private JPanel topBlankPanel;

	//�Ż�������ȡPanel
	private JPanel headlinePanel;
	//�м�հ�Panel
	private JPanel tPanel;
	//�ٶ���������Panel
	private JPanel baiduPanel;
	//�ײ��հ�Panel
	private JPanel bottomBlankPanel;

	

	//�Ż���վ������ʾ��
	private JList headlineList;
	//�ٶ�������ʾ��
	private JList baiduList;
	//�հ���ʾ����
	private Vector blankVector = new Vector();
	
	private BaiduNewsThread baiduNewsThread;
	//�����ؼ�����
	private JTextField searchTextField;
	//�ٶ�����������ǩ
	private JLabel baiduNewsCountLabel;
	//�ֱ�Ϊ���� ���� �Ѻ���ȡ�ж�
	private int sinaThreadMark = 0;
	private int neteaseThreadMark = 0;
	private int sohuThreadMark = 0;
	//�ֱ�Ϊ���� ���� �Ѻ���ʾ�ж�
	private int sinaShowThreadMark = 0;
	private int neteaseShowThreadMark = 0;
	private int sohuShowThreadMark = 0;
	//�ٶ�����������Ŀ
	private int baiduNewsNumber = 10;
	//�ٶ�����ҳ��
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
		frame.setTitle("�ȵ�ƾ���������");
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
	
	//��ʼ��������Panel
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
	 * @Description: ��ʼ�������հ�Panel
	 */
	private void initTopBlankPanel(){
		topBlankPanel = new JPanel();
		topBlankPanel.setLayout(new BoxLayout(topBlankPanel, BoxLayout.Y_AXIS));
		topBlankPanel.add(Box.createVerticalStrut(10));
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
		JPanel middlePanel = new JPanel();
		headlinePanel.add(topPanel);
		headlinePanel.add(Box.createVerticalStrut(5));
		headlinePanel.add(middlePanel);
		headlinePanel.add(Box.createVerticalStrut(5));
		headlinePanel.add(bottomPanel);
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		JLabel info = new JLabel("�ֶ�������ȡ�Ż���վѡ��");	
		topPanel.add(Box.createHorizontalStrut(20));
		topPanel.add(info);
		topPanel.add(Box.createHorizontalStrut(10));
		JCheckBox sinaCheckBox = new JCheckBox("����");
		JCheckBox neteaseCheckBox = new JCheckBox("����");
		JCheckBox sohuCheckBox = new JCheckBox("�Ѻ�");
		JButton headlineButton = new JButton("������ȡ���ݿ�");
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
		JLabel label = new JLabel("�Ż���վ������ʾѡ��");	
		middlePanel.add(Box.createHorizontalStrut(20));
		middlePanel.add(label);
		middlePanel.add(Box.createHorizontalStrut(10));
		JCheckBox sinaShowCheckBox = new JCheckBox("����");
		JCheckBox neteaseShowCheckBox = new JCheckBox("����");
		JCheckBox sohuShowCheckBox = new JCheckBox("�Ѻ�");
		JButton headlineShowButton = new JButton("��ʾ����");
		
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
		//�ֱ�����ˮƽ�ʹ�ֱ���������ǳ��� 
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
		JLabel info = new JLabel("�ٶ������������棺");
		searchTextField = new JTextField();
		JButton searchButton = new JButton("��ȡ����");
		searchButton.addActionListener(new SearchButtonHandle());
		topPanel.add(Box.createHorizontalStrut(20));
		topPanel.add(info);
		topPanel.add(searchTextField);
		topPanel.add(Box.createHorizontalStrut(150));
		topPanel.add(searchButton);
		topPanel.add(Box.createHorizontalStrut(20));
		
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		JLabel label = new JLabel("��ҳ��ʾ����:");
		JRadioButton minRadioButton = new JRadioButton("10��");
		JRadioButton midRadioButton = new JRadioButton("20��");
		JRadioButton maxRadioButton = new JRadioButton("50��");
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
		//�ֱ�����ˮƽ�ʹ�ֱ���������ǳ��� 
		scroll.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scroll.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		bottomPanel.add(Box.createHorizontalStrut(20));
		bottomPanel.add(scroll);
		bottomPanel.add(Box.createHorizontalStrut(20));
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JLabel showCountLabel = new JLabel("������������");
		baiduNewsCountLabel = new JLabel("");
		JButton uppageButton = new JButton("��һҳ");
		JButton downpageButton = new JButton("��һҳ");
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
	 * @Description: sinaShowCheckBox������
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
	 * @Description: neteaseShowCheckBox������
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
	 * @Description: sohuShowCheckBox������
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
	 * @Description: headlineButton������
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
