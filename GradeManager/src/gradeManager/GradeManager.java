package gradeManager;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class GradeManager {

	private JFrame loginFrame;
	private JFrame studentFrame;
	private JFrame teacherFrame;
	private DAO dao = new DAO();
	private JRadioButton rdbtnTeacher;
	private JRadioButton rdbtnStudent;
	private JTextField tfId;
	private JPasswordField pfPw;
	private JFrame changeFrame;
	private StudentVO student;
	private JFrame changePwFrame;
	private JPanel scorePanel;
	private JPanel teacher1;
	private JFrame addFrame;
	private JPanel teacher2;
	private JTable scoreTable;
	private DefaultTableModel defaultTableModel;
	private DefaultTableModel model;
	private JPanel teacher3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradeManager window = new GradeManager();
					window.loginFrame.setVisible(true);
					// window.studentFrame.setVisible(false);
					// window.teacherFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GradeManager() {
		initialize();
		loginSetting();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new JFrame("Grade Manager");
		loginFrame.setBounds(100, 100, 1000, 600);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);

		studentFrame = new JFrame("Grade Manager for Student");
		studentFrame.setBounds(100, 100, 1000, 600);
		studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentFrame.getContentPane().setLayout(null);

		teacherFrame = new JFrame("Grade Manager for Teacher");
		teacherFrame.setBounds(100, 100, 1000, 600);
		teacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		teacherFrame.getContentPane().setLayout(null);
	}

	// �α���ȭ�� ����
	private void loginSetting() {
		// ������ư ����(�л��� ������ �� �� �ϳ� ����)
		rdbtnTeacher = new JRadioButton();
		rdbtnTeacher.setBackground(new Color(255,000,000,000));
		rdbtnTeacher.setFont(new Font("����", Font.BOLD, 16));
		rdbtnTeacher.setBounds(183, 426, 100,50);
		rdbtnTeacher.setText("������");
		loginFrame.getContentPane().add(rdbtnTeacher);
		rdbtnStudent = new JRadioButton();
		rdbtnStudent.setBackground(new Color(255,000,000,000));
		rdbtnStudent.setFont(new Font("����", Font.BOLD, 16));
		rdbtnStudent.setBounds(304, 426, 100, 50);
		rdbtnStudent.setText("�л�");
		loginFrame.getContentPane().add(rdbtnStudent);
		ButtonGroup rbtnGroup = new ButtonGroup();
		rbtnGroup.add(rdbtnTeacher);
		rbtnGroup.add(rdbtnStudent);
		// �� �� �Է�â ����
		JLabel lblId = new JLabel("���̵�");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		loginFrame.getContentPane().add(lblId);
		lblId.setBounds(120, 490, 100, 25);

		tfId = new JTextField();
		loginFrame.getContentPane().add(tfId);
		tfId.setBounds(230, 490, 150, 25);

		JLabel lblPw = new JLabel("��й�ȣ");
		lblPw.setHorizontalAlignment(SwingConstants.RIGHT);
		loginFrame.getContentPane().add(lblPw);
		lblPw.setBounds(410, 490, 100, 25);

		pfPw = new JPasswordField();
		loginFrame.getContentPane().add(pfPw);
		pfPw.setBounds(520, 490, 150, 25);
		// �α��ι�ư����
		JButton btnLogin = new JButton();
		loginFrame.getContentPane().add(btnLogin);
		btnLogin.setBounds(700, 490, 100, 25);
		btnLogin.setText("�α���");
		
		JLabel bg = new JLabel();
		bg.setIcon(new ImageIcon("img/wall2.jpg"));
		bg.setBounds(0, 0, 984, 562);
		loginFrame.getContentPane().add(bg);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}

		});

	

	}

	// �α��� �˰���
	private void loginCheck() {
		HashMap<String, String> studentIdPw = dao.getStudentIdPw();
		HashMap<String, String> teacherIdPw = dao.getTeacherIdPw();
		String inputId = tfId.getText().toString();
		String inputPw = new String(pfPw.getPassword());

		
		if(!rdbtnStudent.isSelected() && !rdbtnTeacher.isSelected()) {
			JOptionPane.showMessageDialog(null, "�α���������  �������ּ���", "", JOptionPane.INFORMATION_MESSAGE);
		}else {
		if (rdbtnStudent.isSelected()) {
			if (studentIdPw.containsKey(inputId)) {
				if (studentIdPw.get(inputId).equals(inputPw)) {
					if (isCompleted()) {
						studentFrame.setVisible(true);
						studentSetting(inputId);
						loginFrame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "������ ���� �Էµ��� �ʾҽ��ϴ�.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �߸� �Է��Ͽ����ϴ�.", "", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "���̵� �߸� �Է��Ͽ����ϴ�.", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (rdbtnTeacher.isSelected()) {
			if (teacherIdPw.containsKey(inputId)) {
				if (teacherIdPw.get(inputId).equals(inputPw)) {
					teacherFrame.setVisible(true);
					teacherSetting(inputId);
					loginFrame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �߸� �Է��Ͽ����ϴ�.", "", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "���̵� �߸� �Է��Ͽ����ϴ�.", "", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		}
	}

	// ����ȭ�鼼��
	private void teacherSetting(String inputId) {
		JTabbedPane teacherTab = new JTabbedPane();
		teacherFrame.getContentPane().setLayout(new CardLayout(0, 0));
		teacherFrame.getContentPane().add(teacherTab);
		teacher1 = new JPanel();
		teacher1Setting();
		teacher2 = new JPanel();
		teacher2Setting();
		teacher3 = new JPanel();

		
		teacherTab.addTab("�л�����", teacher1);
		teacherTab.addTab("�����Է�", teacher2);
		teacherTab.addTab("�����м�", teacher3);
		
		

		teacherTab.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (teacherTab.getSelectedIndex() == 2 && isCompleted()) {
					teacher3Setting();

				} else if (teacherTab.getSelectedIndex() == 2) {
					JOptionPane.showMessageDialog(null, "������ ���� �Էµ��� �ʾҽ��ϴ�.", " ", JOptionPane.INFORMATION_MESSAGE);
					teacherTab.setSelectedIndex(1);
				}

			}
		});

	}

	private void teacher3Setting() {

		teacher3.setLayout(null);
		TeacherChart chart1 = new TeacherChart(1);
		teacher3.add(chart1);
		chart1.setBackground(Color.WHITE);
		chart1.setBounds(15, 30, 300, 220);
		TeacherChart chart2 = new TeacherChart(2);
		teacher3.add(chart2);
		chart2.setBackground(Color.WHITE);
		chart2.setBounds(340, 30, 300, 220);
		TeacherChart chart3 = new TeacherChart(3);
		teacher3.add(chart3);
		chart3.setBackground(Color.WHITE);
		chart3.setBounds(665, 30, 300, 220);
		TeacherChart chart4 = new TeacherChart(4);
		teacher3.add(chart4);
		chart4.setBackground(Color.WHITE);
		chart4.setBounds(178, 280, 300, 220);
		TeacherChart chart5 = new TeacherChart(5);
		teacher3.add(chart5);
		chart5.setBackground(Color.WHITE);
		chart5.setBounds(502, 280, 300, 220);

		JLabel kor = new JLabel("����");
		kor.setBounds(0, 0, 200, 100);
		chart1.add(kor);

		JLabel eng = new JLabel("����");
		kor.setBounds(0, 0, 200, 100);
		chart2.add(eng);

		JLabel math = new JLabel("����");
		kor.setBounds(0, 0, 200, 100);
		chart3.add(math);

		JLabel society = new JLabel("��ȸ");
		kor.setBounds(0, 0, 200, 100);
		chart4.add(society);

		JLabel science = new JLabel("����");
		kor.setBounds(0, 0, 200, 100);
		chart5.add(science);

		JLabel bg = new JLabel();
		bg.setBounds(0,0,980,570);
		teacher3.add(bg);
		bg.setIcon(new ImageIcon("img/wall1.jpg"));
		
	}

	// �����Է�ȭ�鼼��
	private void teacher2Setting() {
		JPanel gradePanel = new JPanel();
		teacher2.add(gradePanel);
		teacher2.setLayout(null);
		gradePanel.setBackground(Color.WHITE);
		gradePanel.setBounds(20, 20, 800, 490);
		gradePanel.setLayout(new CardLayout(0, 0));

		String[] header = { "�й�", "�̸�", "����", "����", "����", "��ȸ", "����" };

		ArrayList<StudentVO> students = dao.getAll();
		String[][] info = new String[students.size()][header.length];
		int index = 0;
		for (StudentVO vo : students) {
			info[index][0] = vo.getId();
			info[index][1] = vo.getName();
			info[index][2] = vo.getKorean() + "";
			info[index][3] = vo.getEnglish() + "";
			info[index][4] = vo.getMath() + "";
			info[index][5] = vo.getSociety() + "";
			info[index][6] = vo.getScience() + "";
			index++;

		}
		model = new DefaultTableModel(info, header);
		scoreTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(scoreTable);
		gradePanel.add(scrollPane);

		for (int i = 0; i < scoreTable.getRowCount(); i++) {
			for (int j = 0; j < scoreTable.getColumnCount(); j++) {
				if (scoreTable.getValueAt(i, j).toString().equals("0")) {
					scoreTable.setValueAt("", i, j);
				}
			}
		}

		JButton btnAdd = new JButton();
		btnAdd.setText("�Է¿Ϸ�");
		btnAdd.setBounds(840, 480, 120, 30);
		teacher2.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] ids = new String[students.size()];
				int[][] scores = new int[students.size()][5];

				for (int i = 0; i < students.size(); i++) {
					ids[i] = scoreTable.getValueAt(i, 0).toString();
					for (int j = 2; j < 7; j++) {

						scores[i][j - 2] = Integer.parseInt(scoreTable.getValueAt(i, j).toString());

					}

					System.out.println(dao.insertScore(ids[i], scores[i][0], scores[i][1], scores[i][2], scores[i][3],
							scores[i][4]));

				}

				JOptionPane.showMessageDialog(null, "�����Է��� �Ϸ�Ǿ����ϴ�.", "", JOptionPane.INFORMATION_MESSAGE);
				teacher2.removeAll();
				teacher2Setting();

			}
		});
		
		JLabel bg = new JLabel();
		bg.setBounds(0,0,980,570);
		teacher2.add(bg);
		bg.setIcon(new ImageIcon("img/wall1.jpg"));
		

	}

	// �л���ȸȭ�鼼��
	private void teacher1Setting() {
		JPanel studentPanel = new JPanel();
		teacher1.add(studentPanel);
		teacher1.setLayout(null);
		studentPanel.setBackground(Color.WHITE);
		studentPanel.setBounds(20, 20, 800, 490);
		studentPanel.setLayout(new CardLayout(0, 0));

		String[] header = { "�й�", "�̸�", "�������", "��ȭ��ȣ", "�ּ�" };

		ArrayList<StudentVO> students = dao.getAll();
		String[][] info = new String[students.size()][header.length];
		int index = 0;
		for (StudentVO vo : students) {
			info[index][0] = vo.getId();
			info[index][1] = vo.getName();
			info[index][2] = vo.getBirth() + "";
			info[index][3] = vo.getPhone();
			info[index][4] = vo.getAddress();
			index++;

		}

		defaultTableModel = new DefaultTableModel(info, header);

		JTable studentTable = new JTable(defaultTableModel);

		JScrollPane scrollPane = new JScrollPane(studentTable);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		studentPanel.add(scrollPane);

		JButton btnAdd = new JButton();
		btnAdd.setText("�л��߰�");
		btnAdd.setBounds(840, 20, 120, 30);
		teacher1.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addFrame = new JFrame();
				addFrame.setBounds(100, 100, 1000, 100);
				addFrame.setVisible(true);

				addFrameSetting();
			}

		});

		JButton btnremove = new JButton();
		btnremove.setText("�л�����");
		btnremove.setBounds(840, 70, 120, 30);
		teacher1.add(btnremove);
		btnremove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (studentTable.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "������ �л��� �������ּ���", " ", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					int a = studentTable.getSelectedRow();

					if (dao.removeOne(studentTable.getValueAt(a, 0).toString()) == 1) {
						JOptionPane.showMessageDialog(null, "�л� ������ �����Ǿ����ϴ�.", "", JOptionPane.INFORMATION_MESSAGE);
						defaultTableModel.removeRow(a);
						model.removeRow(a);
					}

				}

			}
		});
		JLabel bg = new JLabel();
		bg.setBounds(0,0,980,570);
		teacher1.add(bg);
		bg.setIcon(new ImageIcon("img/wall1.jpg"));
		
		
		
	
	}

	// �л�ȭ�鼼��
	private void studentSetting(String studentId) {

		student = dao.selectOne(studentId);

		JPanel chartPanel = new JPanel();
		studentFrame.getContentPane().add(chartPanel);
		chartPanel.setBounds(20, 20, 680, 400);
		chartPanel.setBackground(Color.WHITE);
		chartPanel.setLayout(null);

		StudentChart chart = new StudentChart(studentId);
		chart.setBackground(Color.WHITE);
		chart.setBounds(0, 0, 680, 400);
		chartPanel.add(chart);

		JPanel infoPanel = new JPanel();
		studentFrame.getContentPane().add(infoPanel);
		infoPanel.setBounds(720, 20, 245, 350);
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(new CardLayout(0, 0));

		JPanel infoPanel2 = new JPanel();
		infoPanel2.setBackground(Color.WHITE);
		infoPanel.add(infoPanel2);
		infoPanel2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel l1 = new JLabel("�й� : " + student.getId());
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l1);
		JLabel l2 = new JLabel("�̸� : " + student.getName());
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l2);
		JLabel l3 = new JLabel("������� : " + student.getBirth());
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l3);
		JLabel l4 = new JLabel("��ȭ��ȣ : " + student.getPhone());
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l4);
		JLabel l5 = new JLabel("�ּ� : " + student.getAddress());
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l5);

		JButton btnChange = new JButton();
		studentFrame.getContentPane().add(btnChange);
		btnChange.setBounds(845, 390, 120, 30);
		btnChange.setText("������������");
		btnChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeFrame = new JFrame();
				changeFrame.setBounds(500, 200, 300, 200);
				changeFrame.setVisible(true);
				changeFrameSetting(studentId);

			}

		});

		JButton btnChangePw = new JButton();
		studentFrame.getContentPane().add(btnChangePw);
		btnChangePw.setBounds(720, 390, 120, 30);
		btnChangePw.setText("��й�ȣ����");
		btnChangePw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changePwFrame = new JFrame();
				changePwFrame.setBounds(500, 200, 300, 200);
				changePwFrame.setVisible(true);
				changePwFrameSetting(studentId);

			}

		});

		scorePanel = new JPanel();
		studentFrame.getContentPane().add(scorePanel);
		scorePanel.setBackground(Color.WHITE);
		scorePanel.setBounds(20, 440, 945, 100);
		scorePanelSetting();
		
		
		JLabel bg = new JLabel();
		bg.setIcon(new ImageIcon("img/wall.jpg"));
		bg.setBounds(0, 0, 984, 562);
		studentFrame.getContentPane().add(bg);

	}

	// ����ǥ����
	private void scorePanelSetting() {
		scorePanel.setLayout(new GridLayout(0, 1, 0, 0));
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(Color.WHITE);
		scorePanel.add(upperPanel);
		upperPanel.setLayout(new GridLayout(1, 0, 0, 0));
		JLabel u1 = new JLabel("����");
		u1.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u1);
		JLabel u2 = new JLabel("����");
		u2.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u2);
		JLabel u3 = new JLabel("����");
		u3.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u3);
		JLabel u4 = new JLabel("��ȸ");
		u4.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u4);
		JLabel u5 = new JLabel("����");
		u5.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u5);
		JLabel u6 = new JLabel("���");
		u6.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u6);
		JLabel u8 = new JLabel("����");
		u8.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u8);

		JPanel lowerPanel = new JPanel();
		lowerPanel.setBackground(Color.white);
		scorePanel.add(lowerPanel);
		lowerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		JLabel l1 = new JLabel(student.getKorean() + "");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		lowerPanel.add(l1);
		JLabel l2 = new JLabel(student.getEnglish() + "");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		lowerPanel.add(l2);
		JLabel l3 = new JLabel(student.getMath() + "");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		lowerPanel.add(l3);
		JLabel l4 = new JLabel(student.getSociety() + "");
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		lowerPanel.add(l4);
		JLabel l5 = new JLabel(student.getScience() + "");
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		lowerPanel.add(l5);
		JLabel l6 = new JLabel((student.getKorean() + student.getEnglish() + student.getMath() + student.getSociety()
				+ student.getScience()) / 5.0 + "");
		l6.setHorizontalAlignment(SwingConstants.CENTER);
		lowerPanel.add(l6);

		ArrayList<StudentVO> students = dao.getAll();
		double[] means = new double[students.size()];
		for (int i = 0; i < students.size(); i++) {
			means[i] = (students.get(i).getKorean() + students.get(i).getEnglish() + students.get(i).getMath()
					+ students.get(i).getSociety() + students.get(i).getScience()) / 5.0;
		}

		int rank = 1;

		for (int i = 0; i < means.length; i++) {
			if (means[i] > (student.getKorean() + student.getEnglish() + student.getMath() + student.getSociety()
					+ student.getScience()) / 5.0) {
				rank++;
			}
		}

		JLabel l7 = new JLabel(rank + "/" + means.length);
		l7.setHorizontalAlignment(SwingConstants.CENTER);
		lowerPanel.add(l7);

	}

	// ������������ȭ�鼼��
	private void changeFrameSetting(String studentId) {

		changeFrame.getContentPane().setLayout(new CardLayout(0, 0));
		JPanel changePanel = new JPanel();
		changeFrame.getContentPane().add(changePanel);
		changePanel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel leftPanel = new JPanel();
		changePanel.add(leftPanel);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lb1 = new JLabel("�й� : ");
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb1);
		JLabel lb2 = new JLabel("�̸� : ");
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb2);
		JLabel lb3 = new JLabel("������� : ");
		lb3.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb3);
		JLabel lb4 = new JLabel("��ȭ��ȣ : ");
		lb4.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb4);
		JLabel lb5 = new JLabel("�ּ� : ");
		lb5.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb5);
		JButton l6 = new JButton();
		leftPanel.add(l6);
		l6.setVisible(false);

		JPanel rightPanel = new JPanel();
		changePanel.add(rightPanel);
		rightPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel r1 = new JLabel(student.getId());
		rightPanel.add(r1);
		JLabel r2 = new JLabel(student.getName());
		rightPanel.add(r2);
		JLabel r3 = new JLabel(student.getBirth() + "");
		rightPanel.add(r3);
		JTextField r4 = new JTextField();
		r4.setText(student.getPhone());
		rightPanel.add(r4);
		JTextField r5 = new JTextField();
		r5.setText(student.getAddress());
		rightPanel.add(r5);
		JButton r6 = new JButton();
		rightPanel.add(r6);
		r6.setText("�����ϱ�");
		r6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dao.changePhone(r4.getText().toString(), studentId) == 1
						&& dao.changeAddress(r5.getText().toString(), studentId) == 1) {
					JOptionPane.showMessageDialog(null, "�������� ������ �Ϸ�Ǿ����ϴ�.", "", JOptionPane.INFORMATION_MESSAGE);
					studentSetting(studentId);
					changeFrame.dispose();
				}
				;

			}
		});

	}

	// ��й�ȣ����ȭ�鼼��
	private void changePwFrameSetting(String studentId) {
		changePwFrame.getContentPane().setLayout(new CardLayout(0, 0));
		JPanel changePanel = new JPanel();
		changePwFrame.getContentPane().add(changePanel);
		changePanel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel leftPanel = new JPanel();
		changePanel.add(leftPanel);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lb1 = new JLabel("�����й�ȣ : ");
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb1);
		JLabel lb2 = new JLabel("���ο��й�ȣ : ");
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb2);
		JLabel lb3 = new JLabel("���Է� : ");
		lb3.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb3);
		JButton l4 = new JButton();
		leftPanel.add(l4);
		l4.setVisible(false);

		JPanel rightPanel = new JPanel();
		changePanel.add(rightPanel);
		rightPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JTextField r1 = new JTextField();
		rightPanel.add(r1);
		JTextField r2 = new JTextField();
		rightPanel.add(r2);
		JTextField r3 = new JTextField();
		rightPanel.add(r3);
		JButton r4 = new JButton();
		rightPanel.add(r4);
		r4.setText("�����ϱ�");
		r4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (r1.getText().toString().equals(student.getPassword())) {
					if (r2.getText().toString().equals(r3.getText().toString())) {
						if (dao.changePassword(r2.getText().toString(), studentId) == 1) {
							JOptionPane.showMessageDialog(null, "��й�ȣ ������ �Ϸ�Ǿ����ϴ�.", "",
									JOptionPane.INFORMATION_MESSAGE);
							changePwFrame.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null, "���ο� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "���� ��й�ȣ�� Ȯ���� �ּ���.", "", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

	}

	// �л����ȭ�鼼��
	private void addFrameSetting() {
		addFrame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JPanel upperPanel = new JPanel();
		addFrame.getContentPane().add(upperPanel);
		upperPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel u1 = new JLabel("�й�");
		upperPanel.add(u1);
		JLabel u2 = new JLabel("�̸�");
		upperPanel.add(u2);
		JLabel u3 = new JLabel("�������");
		upperPanel.add(u3);

		JButton u7 = new JButton();
		upperPanel.add(u7);
		u7.setVisible(false);

		JPanel lowerPanel = new JPanel();
		addFrame.getContentPane().add(lowerPanel);
		lowerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		JTextField l1 = new JTextField();
		lowerPanel.add(l1);
		JTextField l2 = new JTextField();
		lowerPanel.add(l2);
		JTextField l3 = new JTextField();
		lowerPanel.add(l3);
		JButton l7 = new JButton();
		lowerPanel.add(l7);
		l7.setText("���");

		l7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dao.addStudent(l1.getText().toString(), l2.getText().toString(),
						Integer.parseInt(l3.getText().toString())) == 1) {
					JOptionPane.showMessageDialog(null, "�л��� �߰��Ǿ����ϴ�.", " ", JOptionPane.INFORMATION_MESSAGE);

					String[] row = { l1.getText().toString(), l2.getText().toString(), l3.getText().toString(), };
					String[] row2 = { l1.getText().toString(), l2.getText().toString(), "0", "0", "0", "0", "0" };

					defaultTableModel.addRow(row);
					model.addRow(row2);
					l1.setText("");
					l2.setText("");
					l3.setText("");
					for (int i = 0; i < scoreTable.getRowCount(); i++) {
						for (int j = 0; j < scoreTable.getColumnCount(); j++) {
							if (scoreTable.getValueAt(i, j).toString().equals("0")) {
								scoreTable.setValueAt("", i, j);
							}
						}
					}

					teacher2.removeAll();
					teacher2Setting();
					teacher3.removeAll();
					teacher3Setting();

				}else {
					JOptionPane.showMessageDialog(null, "�̹� �����ϴ� �й��Դϴ�.", " ", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

	}

	private boolean isCompleted() {
		ArrayList<StudentVO> students = dao.getAll();
		for (StudentVO student : students) {
			if (student.getKorean() == 0) {
				return false;
			}
			if (student.getEnglish() == 0) {
				return false;
			}
			if (student.getMath() == 0) {
				return false;
			}
			if (student.getSociety() == 0) {
				return false;
			}
			if (student.getScience() == 0) {
				return false;
			}

		}
		return true;
	}
}
