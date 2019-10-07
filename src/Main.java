import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;

public class Main {
//test add comment

	private static ArrayList<Student> students = new ArrayList<>();
	private static ArrayList<Login> logins = new ArrayList<>();
	private static int numberOfAssignments;
	
	public static void main(String[] args) {
		
		int input = 0;
		int id = 0;
		
		while(input != JOptionPane.CANCEL_OPTION) {
			
			input = welcome();
			
			if (input == JOptionPane.YES_OPTION) {
				
				id = createUser(id);
				
				for (int i = 0; i < logins.size(); i++) {
					System.out.println(logins.get(i).getUsername());
					System.out.println(logins.get(i).getPassword());
					System.out.println(logins.get(i).getID());
					System.out.println("\n");
				}

				
			} else if (input == JOptionPane.NO_OPTION) {
				
				login();
				break;
				
			} else if (input == JOptionPane.CLOSED_OPTION) {
				System.exit(0);  
			}
	
		}
		
		
		// This is a test push
		
		// Instructor sets parameters by adding students to the group and setting the number of assignments
		addStudents();
		
		// Notify instructor of project creation
		JOptionPane.showMessageDialog(null, "The group project has been created");
		
		// Notify students of group project creation
		notifyStudents();
		
		// Prompt students for real-time feedback
		ArrayList<Double> scoreForEach = promptForFeedback(students);
		
		JOptionPane.showMessageDialog(null, "Prompting students for end-of-project feedback");
		
		System.out.println();
		
		// Prompt students for end-of-project feedback
		ArrayList<Double> scoreForEach2 = promptForFeedback(students);
		
		System.out.println();
		
		// Display final score for each student
		double score;
		for (int i = 0; i < scoreForEach.size(); i++) {
			score = (scoreForEach.get(i) + scoreForEach2.get(i)) / 2;			// 2 prompts
			System.out.println("Score for " + students.get(i).getName() + ": " + score);
		}
		
		System.exit(0);
	}
	
	public static int welcome() {
		
        Object[] options1 = { "Create User", "Login", "Quit" };

    	JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to the GGS™"));
    	
    	int result = JOptionPane.showOptionDialog(null, panel, "Welcome",
        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
    	
    	return result;
		
	}
	
	public static void login() {
		
		JFrame frame = new JFrame();

	    JPanel panel = new JPanel(new BorderLayout(5, 5));

	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("E-Mail", SwingConstants.RIGHT));
	    label.add(new JLabel("Password", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);

	    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField username = new JTextField();
	    controls.add(username);
	    JPasswordField password = new JPasswordField();
	    controls.add(password);
	    panel.add(controls, BorderLayout.CENTER);

		String temp2 = null;
		Boolean found = false;
		Boolean success = false;
		
		while (success == false) {
			
		    int result = JOptionPane.showConfirmDialog(frame, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
			
		    if(result == JOptionPane.OK_OPTION) {
		    	
				String user = username.getText();
				String pass = new String(password.getPassword());
				
				for (int i = 0; i < logins.size(); i++) {
					if (user.compareToIgnoreCase(logins.get(i).getUsername()) == 0) {
						found = true;
						temp2 = logins.get(i).getPassword();
					} 
				}
				
				if (found) {
					if (pass.compareTo(temp2) == 0) {
						JOptionPane.showMessageDialog(null, "You have logged in");
						success = true;
					} else {
						JOptionPane.showMessageDialog(null, "Wrong password!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Username not found");
				}
		    } else if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION){
		    	success = true;		// break out of while loop
		    }
		    
		}
	    
		
	}
	
	public static int createUser(int id) {
		
		JFrame frame = new JFrame();

	    JPanel panel = new JPanel(new BorderLayout(5, 5));

	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("E-Mail", SwingConstants.RIGHT));
	    label.add(new JLabel("Password", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);

	    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField username = new JTextField();
	    controls.add(username);
	    JPasswordField password = new JPasswordField();
	    controls.add(password);
	    panel.add(controls, BorderLayout.CENTER);

	    JOptionPane.showConfirmDialog(
	            frame, panel, "Create New User", JOptionPane.OK_CANCEL_OPTION);
		
		String user = username.getText();
		String pass = new String(password.getPassword());
		
		Login login = new Login(id, user, pass);
		logins.add(login);
	    
		id++;
	    return id;
	}
	
	public static void addStudents() {
		
		int numStudents = Integer.parseInt(JOptionPane.showInputDialog("How many students are in this group?"));
		
		String name;
		String email;
		for (int i = 0; i < numStudents; i++) {
			name = JOptionPane.showInputDialog("Enter name for student " + (i + 1));
			email = JOptionPane.showInputDialog("Enter email for student " + (i + 1));
			students.add(new Student(name, email));
		}
		
		//numberOfAssignments = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of Assignments in the group project"));
			
	}
	
	public static void notifyStudents() {
		
		for (int i = 0; i < students.size(); i++) {
			
			System.out.println(students.get(i).getName() + ", you have been added to the group with the following partners:");
			
			int j = i;
			while (j != 0) {
				j--;
				System.out.println("Name: " + students.get(j).getName());
				System.out.println("Email: " + students.get(j).getEmail() + "\n");	
			}

			int k = i;
			while (k != students.size() - 1) {
				k++;
				System.out.println("Name: " + students.get(k).getName());
				System.out.println("Email: " + students.get(k).getEmail() + "\n");	
			}

			System.out.println("---------------------------------------------");
			
		}
		
	   System.out.println("Press \"ENTER\" to continue...");
	   Scanner scanner = new Scanner(System.in);
	   scanner.nextLine();
		
	}
	
	public static ArrayList<Double> promptForFeedback(ArrayList<Student> students) {

		// Create 2d array to hold scores
		// For example
		//      A  B  C
		//  A  AA AB AC
		//  B  BA BB BC
		//  C  CA CB CC
		// In any cell, XY represents X's grade of Y
		int[][] scores = new int[students.size()][students.size()];
		
		// Let i represent the rows
		// Let j represent the columns
		int temp = 0;
		String temp2;
		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < students.size(); j++) {
				if (i == j) {
					temp2 = JOptionPane.showInputDialog(students.get(i).getName() + ", enter your score for yourself (between 0 and 100)");
					while (!isValid(temp2)) {
						temp2 = JOptionPane.showInputDialog(students.get(i).getName() + ", enter a VALID score for yourself (BETWEEN 0 AND 100)");
					}
					temp = Integer.parseInt(temp2);
				} else {
					temp2 = JOptionPane.showInputDialog(students.get(i).getName() + ", enter your score for " + students.get(j).getName() + " (between 0 and 100)");
					while(!isValid(temp2))
						temp2 = JOptionPane.showInputDialog(students.get(i).getName() + ", enter a VALID score for " + students.get(j).getName() + " (BETWEEN 0 AND 100)");
					temp = Integer.parseInt(temp2);
				}
				scores[i][j] = temp;
			}
		}
		
		System.out.println("FOR DEBUGGING ONLY: Score matrix:");
		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < students.size(); j++) {
				System.out.print(scores[i][j] + "  ");
			}
			System.out.println();
		}
		
		System.out.println();
		
		// Compute current scores for each student by going down each column
		int scoreTemp = 0;
		// New arraylist will be in parallel with the students arraylist
		ArrayList<Double> scoreForEach = new ArrayList<>();
		for (int j = 0; j < students.size(); j++) {
			for (int i = 0; i < students.size(); i++) {
				scoreTemp = scoreTemp + scores[i][j];
			}
			scoreForEach.add((double) scoreTemp / (double) students.size());
			scoreTemp = 0;
		}
		
		System.out.println("FOR DEBUGGING ONLY: total score matrix:");
		for (int i = 0; i < scoreForEach.size(); i++) {
			System.out.println("Name: " + students.get(i).getName() + "		Current Score: " + scoreForEach.get(i));
		}
		
		return scoreForEach;
		
	}
	
	public static boolean isValid(String string) {
		
		for (int i = 0; i < string.length(); i++ ) {
			if (Character.isDigit(string.charAt(i)) == false)
				return false;
			
			if(Integer.parseInt(string) < 0 || Integer.parseInt(string) > 100)
				return false;
		}
		return true;
	}

}
