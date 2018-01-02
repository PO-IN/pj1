package gradeManager;

public class StudentVO {
	private String id;
	private String name;
	private int birth;
	private String phone;
	private String address;
	private String password;
	
	private int korean;
	private int english;
	private int math;
	private int society;
	private int science;
	

	@Override
	public String toString() {
		return "StudentVO [id=" + id + ", name=" + name + ", birth=" + birth + ", phone=" + phone + ", address="
				+ address + ", password=" + password + ",  korean=" + korean + ", english="
				+ english + ", math=" + math + ", society=" + society + ", science=" + science + "]";
	}

	public StudentVO(String id, String name, int birth, String phone, String address, String password, int classNum) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.address = address;
		this.password = password;
		

	}

	public StudentVO(String id, String name, int birth, String phone, String address, String password, 
			int korean, int english, int math, int society, int science) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.address = address;
		this.password = password;
		
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.society = society;
		this.science = science;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirth() {
		return birth;
	}

	public void setBirth(int birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSociety() {
		return society;
	}

	public void setSociety(int society) {
		this.society = society;
	}

	public int getScience() {
		return science;
	}

	public void setScience(int science) {
		this.science = science;
	}

}
