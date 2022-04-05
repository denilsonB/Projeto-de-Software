
public class Messages {
	private User sender;
	private String content;
	
	public Messages() {

	}
	
	public Messages(User sender, String content) {
		this.sender = sender;
		this.content = content;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
}
