package gradeManager;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class TeacherChart extends JPanel {

	DAO dao = new DAO();
	int sub;

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		ArrayList<StudentVO> students = dao.getAll();
		int[] cnt = new int[11];
		if (sub == 1) {
			for (StudentVO student : students) {
				cnt[student.getKorean() / 10 ]++;
			}
		}
		if (sub == 2) {
			for (StudentVO student : students) {
				cnt[student.getEnglish() / 10 ]++;
			}
		}
		if (sub == 3) {
			for (StudentVO student : students) {
				cnt[student.getMath() / 10 ]++;
			}
		}
		if (sub == 4) {
			for (StudentVO student : students) {
				cnt[student.getSociety() / 10 ]++;
			}
		}
		if (sub == 5) {
			for (StudentVO student : students) {
				cnt[student.getScience() / 10 ]++;
			}
		}
		g.setColor(Color.BLACK);
		g.drawLine(10, 190, 290, 190);
		g.drawString("0    10     20     30     40     50     60     70     80    90   100", 8, 202);

		g.setColor(new Color(210,105,30));
		g.fillRect(11, 190 - cnt[0] * 15, 26, cnt[0] * 15);
		g.fillRect(39, 190 - cnt[1] * 15, 26, cnt[1] * 15);
		g.fillRect(67, 190 - cnt[2] * 15, 26, cnt[2] * 15);
		g.fillRect(95, 190 - cnt[3] * 15, 26, cnt[3] * 15);
		g.fillRect(123, 190 - cnt[4] * 15, 26, cnt[4] * 15);
		g.fillRect(151, 190 - cnt[5] * 15, 26, cnt[5] * 15);
		g.fillRect(179, 190 - cnt[6] * 15, 26, cnt[6] * 15);
		g.fillRect(207, 190 - cnt[7] * 15, 26, cnt[7] * 15);
		g.fillRect(235, 190 - cnt[8] * 15, 26, cnt[8] * 15);
		g.fillRect(263, 190 - (cnt[9]+cnt[10]) * 15, 28, (cnt[9]+cnt[10]) * 15);

	}

	public TeacherChart(int sub) {
		super();
		this.sub = sub;
	}

}
