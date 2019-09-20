
public class Student {
	
	private String name;
	private String email;
	private double totalPoints;
	
	
	public Student(String name, String email) {

		this.name = name;
		this.email = email;
		totalPoints = 0;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}

}
