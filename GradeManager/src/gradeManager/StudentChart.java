package gradeManager;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class StudentChart extends JPanel {
	DAO dao = new DAO();
	private String studentId;

	public StudentChart(String studentId) {
		super();
		this.studentId = studentId;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		StudentVO student = dao.selectOne(studentId);
		int koreanScore = student.getKorean();
		int englishScore = student.getEnglish();
		int mathScore = student.getMath();
		int societyScore = student.getSociety();
		int scienceScore = student.getScience();
		ArrayList<StudentVO> students = dao.getAll();
		int koreanMean = 0;
		int englishMean = 0;
		int mathMean = 0;
		int societyMean = 0;
		int scienceMean = 0;

		for (StudentVO m : students) {
			koreanMean += m.getKorean();
			englishMean += m.getEnglish();
			mathMean += m.getMath();
			societyMean += m.getSociety();
			scienceMean += m.getScience();

		}

		g.setColor(Color.BLACK);
		g.drawLine(50, 40, 50, 350);
		g.drawLine(50, 350, 650, 350);
		g.drawString("본인 점수", 615, 20);
		g.drawString("평균 점수", 615, 35);
		g.drawString("100", 25, 55);
		g.drawString("50", 30, 205);
		g.drawString("0", 35, 355);
		g.drawString("국어", 95, 370);
		g.drawString("영어", 215, 370);
		g.drawString("수학", 335, 370);
		g.drawString("사회", 455, 370);
		g.drawString("과학", 575, 370);

		g.setColor(Color.GRAY);
		for (int i = 50; i < 350; i += 30) {
			for (int j = 50; j < 650; j += 10) {
				g.drawLine(j, i, j + 5, i);
			}
		}

		g.setColor(Color.RED);
		g.fillRect(65, 350 - 3 * koreanScore, 40, 3 * koreanScore);
		g.fillRect(185, 350 - 3 * englishScore, 40, 3 * englishScore);
		g.fillRect(305, 350 - 3 * mathScore, 40, 3 * mathScore);
		g.fillRect(425, 350 - 3 * societyScore, 40, 3 * societyScore);
		g.fillRect(545, 350 - 3 * scienceScore, 40, 3 * scienceScore);
		
		g.fillRect(580, 10, 30, 10);

		g.setColor(Color.BLUE);
		g.fillRect(115, 350 - 3 * ((int) ((double) koreanMean / students.size())), 40,
				3 * (int) ((double) koreanMean / students.size()));
		g.fillRect(235, 350 - 3 * ((int) ((double) englishMean / students.size())), 40,
				3 * (int) ((double) englishMean / students.size()));
		g.fillRect(355, 350 - 3 * ((int) ((double) mathMean / students.size())), 40,
				3 * (int) ((double) mathMean / students.size()));
		g.fillRect(475, 350 - 3 * ((int) ((double) societyMean / students.size())), 40,
				3 * (int) ((double) societyMean / students.size()));
		g.fillRect(595, 350 - 3 * ((int) ((double) scienceMean / students.size())), 40,
				3 * (int) ((double) scienceMean / students.size()));
		
		g.fillRect(580, 25, 30, 10);

	}

}
