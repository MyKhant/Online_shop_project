package ds;

import java.sql.Date;

public class Staff {
	
	private int id;
	private String name;
	private String password;
	private String question;
	private String answer;
	private Date date;
	public Staff(int id, String name, String password, String question, String answer, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.question = question;
		this.answer = answer;
		this.date = date;
	}
	
	
	public Staff(int id, String name, String password, String answer) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.answer = answer;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", password=" + password + ", question=" + question + ", answer="
				+ answer + ", date=" + date + "]";
	}
	
	
	

}