package de.hhz.bpm;

public class WelcomeMessage {
	
	private final String message;
	private final String gender;
	private final String first;
	private final String last;
	
	public WelcomeMessage(String message, String gender, String first, String last) {
		super();
		this.message = message;
		this.gender = gender;
		this.first = first;
		this.last = last;
	}
	public String getGender() {
		return gender;
	}
	public String getFirst() {
		return first;
	}
	public String getLast() {
		return last;
	}
	public String getMessage() {
		return message;
	}
	
}
