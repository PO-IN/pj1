package gradeManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String id = "dj";
	private String pw = "123456";

	// �й��� �̿��Ͽ� �Ѹ��� ������ ��� �޼ҵ�
	public StudentVO selectOne(String index) {
		ResultSet resultSet;
		StudentVO student = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "SELECT * FROM STUDENT WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, index);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String studentId = resultSet.getString(1);
				String name = resultSet.getString(2);
				int birth = resultSet.getInt(3);
				String phone = resultSet.getString(4);
				String address = resultSet.getString(5);
				String password = resultSet.getString(6);
				
				int korean = resultSet.getInt(7);
				int english = resultSet.getInt(8);
				int math = resultSet.getInt(9);
				int society = resultSet.getInt(10);
				int science = resultSet.getInt(11);

				student = new StudentVO(studentId, name, birth, phone, address, password,  korean, english,
						math, society, science);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return student;
	}

	// ��� �����͸� ��� �޼ҵ�
	public ArrayList<StudentVO> getAll() {
		ResultSet resultSet;

		ArrayList<StudentVO> students = new ArrayList<>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "SELECT * FROM STUDENT";
			pstmt = conn.prepareStatement(sql);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String studentId = resultSet.getString(1);
				String name = resultSet.getString(2);
				int birth = resultSet.getInt(3);
				String phone = resultSet.getString(4);
				String address = resultSet.getString(5);
				String password = resultSet.getString(6);
				int korean = resultSet.getInt(7);
				int english = resultSet.getInt(8);
				int math = resultSet.getInt(9);
				int society = resultSet.getInt(10);
				int science = resultSet.getInt(11);

				StudentVO student = new StudentVO(studentId, name, birth, phone, address, password,  korean,
						english, math, society, science);

				students.add(student);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return students;
	}

	// ��ü �������� ���̵�� ��й�ȣ�� ��� �޼ҵ�
	public HashMap<String, String> getTeacherIdPw() {
		ResultSet resultSet;
		HashMap<String, String> teacherIdPw = new HashMap<>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "SELECT ID, PASSWORD FROM TEACHER";
			pstmt = conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String teacherId = resultSet.getString(1);
				String pw = resultSet.getString(2);
				teacherIdPw.put(teacherId, pw);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return teacherIdPw;
	}

	// ��ü �л����� ���̵�� ��й�ȣ�� ��� �޼ҵ�
	public HashMap<String, String> getStudentIdPw() {
		ResultSet resultSet;
		HashMap<String, String> studentIdPw = new HashMap<>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "SELECT ID, PASSWORD FROM STUDENT";
			pstmt = conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String studentId = resultSet.getString(1);
				String pw = resultSet.getString(2);
				studentIdPw.put(studentId, pw);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return studentIdPw;
	}
	
	//��ȭ��ȣ ���� �޼ҵ�
	public int changePhone(String phone, String studentId) {
		int num = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "UPDATE STUDENT SET PHONE = ? WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, studentId);
			num = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;
	}

	//�ּ� ���� �޼ҵ�
	public int changeAddress(String address, String studentId) {
		int num = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "UPDATE STUDENT SET ADDRESS = ? WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setString(2, studentId);
			num = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;
	}
	
	//��й�ȣ ���� �޼ҵ�
	public int changePassword(String password, String studentId) {
		int num = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "UPDATE STUDENT SET PASSWORD = ? WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, studentId);
			num = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;
	}

	//�л��߰� �޼ҵ�
	public int addStudent(String studentNum, String name, int birth) {
		
		int num = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "INSERT INTO STUDENT (ID, NAME, BIRTH, PASSWORD) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentNum);
			pstmt.setString(2, name);
			pstmt.setInt(3, birth);
			pstmt.setString(4, birth+"");
			
			
			num = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;		
	}

	//�����Է� �޼ҵ�
	public int insertScore(String studentNum, int korean, int english, int math, int society, int science) {
		int num = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "UPDATE STUDENT SET KOREAN = ?, ENGLISH = ?, MATH = ?, SOCIETY = ?, SCIENCE = ? WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, korean);
			pstmt.setInt(2, english);
			pstmt.setInt(3, math);
			pstmt.setInt(4, society);
			pstmt.setInt(5, science);
			pstmt.setString(6, studentNum);
					
			num = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;	
	}

	public int removeOne(String studentId) {
		int num = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);

			String sql = "DELETE STUDENT  WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentId);
								
			num = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;	
		
	}
}
