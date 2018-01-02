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

	// 로그인화면 세팅
	private void loginSetting() {
		// 라디오버튼 세팅(학생과 선생님 둘 중 하나 선택)
		rdbtnTeacher = new JRadioButton();
		rdbtnTeacher.setBackground(new Color(255,000,000,000));
		rdbtnTeacher.setFont(new Font("굴림", Font.BOLD, 16));
		rdbtnTeacher.setBounds(183, 426, 100,50);
		rdbtnTeacher.setText("선생님");
		loginFrame.getContentPane().add(rdbtnTeacher);
		rdbtnStudent = new JRadioButton();
		rdbtnStudent.setBackground(new Color(255,000,000,000));
		rdbtnStudent.setFont(new Font("굴림", Font.BOLD, 16));
		rdbtnStudent.setBounds(304, 426, 100, 50);
		rdbtnStudent.setText("학생");
		loginFrame.getContentPane().add(rdbtnStudent);
		ButtonGroup rbtnGroup = new ButtonGroup();
		rbtnGroup.add(rdbtnTeacher);
		rbtnGroup.add(rdbtnStudent);
		// 라벨 및 입력창 세팅
		JLabel lblId = new JLabel("아이디");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		loginFrame.getContentPane().add(lblId);
		lblId.setBounds(120, 490, 100, 25);

		tfId = new JTextField();
		loginFrame.getContentPane().add(tfId);
		tfId.setBounds(230, 490, 150, 25);

		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setHorizontalAlignment(SwingConstants.RIGHT);
		loginFrame.getContentPane().add(lblPw);
		lblPw.setBounds(410, 490, 100, 25);

		pfPw = new JPasswordField();
		loginFrame.getContentPane().add(pfPw);
		pfPw.setBounds(520, 490, 150, 25);
		// 로그인버튼세팅
		JButton btnLogin = new JButton();
		loginFrame.getContentPane().add(btnLogin);
		btnLogin.setBounds(700, 490, 100, 25);
		btnLogin.setText("로그인");
		
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

	// 로그인 알고리즘
	private void loginCheck() {
		HashMap<String, String> studentIdPw = dao.getStudentIdPw();
		HashMap<String, String> teacherIdPw = dao.getTeacherIdPw();
		String inputId = tfId.getText().toString();
		String inputPw = new String(pfPw.getPassword());

		
		if(!rdbtnStudent.isSelected() && !rdbtnTeacher.isSelected()) {
			JOptionPane.showMessageDialog(null, "로그인유형을  선택해주세요", "", JOptionPane.INFORMATION_MESSAGE);
		}else {
		if (rdbtnStudent.isSelected()) {
			if (studentIdPw.containsKey(inputId)) {
				if (studentIdPw.get(inputId).equals(inputPw)) {
					if (isCompleted()) {
						studentFrame.setVisible(true);
						studentSetting(inputId);
						loginFrame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "성적이 아직 입력되지 않았습니다.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호를 잘못 입력하였습니다.", "", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "아이디를 잘못 입력하였습니다.", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (rdbtnTeacher.isSelected()) {
			if (teacherIdPw.containsKey(inputId)) {
				if (teacherIdPw.get(inputId).equals(inputPw)) {
					teacherFrame.setVisible(true);
					teacherSetting(inputId);
					loginFrame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호를 잘못 입력하였습니다.", "", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "아이디를 잘못 입력하였습니다.", "", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		}
	}

	// 선생화면세팅
	private void teacherSetting(String inputId) {
		JTabbedPane teacherTab = new JTabbedPane();
		teacherFrame.getContentPane().setLayout(new CardLayout(0, 0));
		teacherFrame.getContentPane().add(teacherTab);
		teacher1 = new JPanel();
		teacher1Setting();
		teacher2 = new JPanel();
		teacher2Setting();
		teacher3 = new JPanel();

		
		teacherTab.addTab("학생관리", teacher1);
		teacherTab.addTab("성적입력", teacher2);
		teacherTab.addTab("성적분석", teacher3);
		
		

		teacherTab.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (teacherTab.getSelectedIndex() == 2 && isCompleted()) {
					teacher3Setting();

				} else if (teacherTab.getSelectedIndex() == 2) {
					JOptionPane.showMessageDialog(null, "성적이 아직 입력되지 않았습니다.", " ", JOptionPane.INFORMATION_MESSAGE);
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

		JLabel kor = new JLabel("국어");
		kor.setBounds(0, 0, 200, 100);
		chart1.add(kor);

		JLabel eng = new JLabel("영어");
		kor.setBounds(0, 0, 200, 100);
		chart2.add(eng);

		JLabel math = new JLabel("수학");
		kor.setBounds(0, 0, 200, 100);
		chart3.add(math);

		JLabel society = new JLabel("사회");
		kor.setBounds(0, 0, 200, 100);
		chart4.add(society);

		JLabel science = new JLabel("과학");
		kor.setBounds(0, 0, 200, 100);
		chart5.add(science);

		JLabel bg = new JLabel();
		bg.setBounds(0,0,980,570);
		teacher3.add(bg);
		bg.setIcon(new ImageIcon("img/wall1.jpg"));
		
	}

	// 성적입력화면세팅
	private void teacher2Setting() {
		JPanel gradePanel = new JPanel();
		teacher2.add(gradePanel);
		teacher2.setLayout(null);
		gradePanel.setBackground(Color.WHITE);
		gradePanel.setBounds(20, 20, 800, 490);
		gradePanel.setLayout(new CardLayout(0, 0));

		String[] header = { "학번", "이름", "국어", "영어", "수학", "사회", "과학" };

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
		btnAdd.setText("입력완료");
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

				JOptionPane.showMessageDialog(null, "성적입력이 완료되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
				teacher2.removeAll();
				teacher2Setting();

			}
		});
		
		JLabel bg = new JLabel();
		bg.setBounds(0,0,980,570);
		teacher2.add(bg);
		bg.setIcon(new ImageIcon("img/wall1.jpg"));
		

	}

	// 학생조회화면세팅
	private void teacher1Setting() {
		JPanel studentPanel = new JPanel();
		teacher1.add(studentPanel);
		teacher1.setLayout(null);
		studentPanel.setBackground(Color.WHITE);
		studentPanel.setBounds(20, 20, 800, 490);
		studentPanel.setLayout(new CardLayout(0, 0));

		String[] header = { "학번", "이름", "생년월일", "전화번호", "주소" };

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
		btnAdd.setText("학생추가");
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
		btnremove.setText("학생삭제");
		btnremove.setBounds(840, 70, 120, 30);
		teacher1.add(btnremove);
		btnremove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (studentTable.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "삭제할 학생을 선택해주세요", " ", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					int a = studentTable.getSelectedRow();

					if (dao.removeOne(studentTable.getValueAt(a, 0).toString()) == 1) {
						JOptionPane.showMessageDialog(null, "학생 정보가 삭제되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
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

	// 학생화면세팅
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

		JLabel l1 = new JLabel("학번 : " + student.getId());
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l1);
		JLabel l2 = new JLabel("이름 : " + student.getName());
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l2);
		JLabel l3 = new JLabel("생년월일 : " + student.getBirth());
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l3);
		JLabel l4 = new JLabel("전화번호 : " + student.getPhone());
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l4);
		JLabel l5 = new JLabel("주소 : " + student.getAddress());
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel2.add(l5);

		JButton btnChange = new JButton();
		studentFrame.getContentPane().add(btnChange);
		btnChange.setBounds(845, 390, 120, 30);
		btnChange.setText("개인정보수정");
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
		btnChangePw.setText("비밀번호변경");
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

	// 성적표세팅
	private void scorePanelSetting() {
		scorePanel.setLayout(new GridLayout(0, 1, 0, 0));
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(Color.WHITE);
		scorePanel.add(upperPanel);
		upperPanel.setLayout(new GridLayout(1, 0, 0, 0));
		JLabel u1 = new JLabel("국어");
		u1.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u1);
		JLabel u2 = new JLabel("영어");
		u2.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u2);
		JLabel u3 = new JLabel("수학");
		u3.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u3);
		JLabel u4 = new JLabel("사회");
		u4.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u4);
		JLabel u5 = new JLabel("과학");
		u5.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u5);
		JLabel u6 = new JLabel("평균");
		u6.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(u6);
		JLabel u8 = new JLabel("석차");
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

	// 개인정보수정화면세팅
	private void changeFrameSetting(String studentId) {

		changeFrame.getContentPane().setLayout(new CardLayout(0, 0));
		JPanel changePanel = new JPanel();
		changeFrame.getContentPane().add(changePanel);
		changePanel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel leftPanel = new JPanel();
		changePanel.add(leftPanel);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lb1 = new JLabel("학번 : ");
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb1);
		JLabel lb2 = new JLabel("이름 : ");
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb2);
		JLabel lb3 = new JLabel("생년월일 : ");
		lb3.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb3);
		JLabel lb4 = new JLabel("전화번호 : ");
		lb4.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb4);
		JLabel lb5 = new JLabel("주소 : ");
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
		r6.setText("수정하기");
		r6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dao.changePhone(r4.getText().toString(), studentId) == 1
						&& dao.changeAddress(r5.getText().toString(), studentId) == 1) {
					JOptionPane.showMessageDialog(null, "개인정보 수정이 완료되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
					studentSetting(studentId);
					changeFrame.dispose();
				}
				;

			}
		});

	}

	// 비밀번호변경화면세팅
	private void changePwFrameSetting(String studentId) {
		changePwFrame.getContentPane().setLayout(new CardLayout(0, 0));
		JPanel changePanel = new JPanel();
		changePwFrame.getContentPane().add(changePanel);
		changePanel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel leftPanel = new JPanel();
		changePanel.add(leftPanel);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lb1 = new JLabel("현재비밀번호 : ");
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb1);
		JLabel lb2 = new JLabel("새로운비밀번호 : ");
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		leftPanel.add(lb2);
		JLabel lb3 = new JLabel("재입력 : ");
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
		r4.setText("수정하기");
		r4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (r1.getText().toString().equals(student.getPassword())) {
					if (r2.getText().toString().equals(r3.getText().toString())) {
						if (dao.changePassword(r2.getText().toString(), studentId) == 1) {
							JOptionPane.showMessageDialog(null, "비밀번호 변경이 완료되었습니다.", "",
									JOptionPane.INFORMATION_MESSAGE);
							changePwFrame.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null, "새로운 비밀번호가 일치하지 않습니다.", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "현재 비밀번호를 확인해 주세요.", "", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

	}

	// 학생등록화면세팅
	private void addFrameSetting() {
		addFrame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JPanel upperPanel = new JPanel();
		addFrame.getContentPane().add(upperPanel);
		upperPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel u1 = new JLabel("학번");
		upperPanel.add(u1);
		JLabel u2 = new JLabel("이름");
		upperPanel.add(u2);
		JLabel u3 = new JLabel("생년월일");
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
		l7.setText("등록");

		l7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dao.addStudent(l1.getText().toString(), l2.getText().toString(),
						Integer.parseInt(l3.getText().toString())) == 1) {
					JOptionPane.showMessageDialog(null, "학생이 추가되었습니다.", " ", JOptionPane.INFORMATION_MESSAGE);

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
					JOptionPane.showMessageDialog(null, "이미 존재하는 학번입니다.", " ", JOptionPane.INFORMATION_MESSAGE);
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
