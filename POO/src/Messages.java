
public class Messages {
	private int id_send;
	private int id_recieve;
	private String content;
	
	public Messages() {

	}
	
	public Messages(int id_send, int id_recieve, String content) {
		this.id_send = id_send;
		this.id_recieve = id_recieve;
		this.content = content;
	}
	
	public int getId_send() {
		return id_send;
	}
	public void setId_send(int id_send) {
		this.id_send = id_send;
	}
	public int getId_recieve() {
		return id_recieve;
	}
	public void setId_recieve(int id_recieve) {
		this.id_recieve = id_recieve;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
