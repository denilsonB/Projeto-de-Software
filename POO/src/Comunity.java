import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Comunity {
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	private int id_comunity;
	List<User> members = new ArrayList<User>();
	private int creator;
	private String name;
	private String description;
	List<Messages> my_messages = new ArrayList<Messages>();
	
	public Comunity() {
		this.id_comunity = count.incrementAndGet();
	}
	
	public Comunity(int creator, String name, String description) {
		this.creator = creator;
		this.name = name;
		this.description = description;
		this.id_comunity = count.incrementAndGet();
	}
	
	public void new_member(User new_user) {
		this.members.add(new_user);
	}
	
	public void newMessage(Messages message) {
		this.my_messages.add(message);
	}
	
	public void my_messages() {
		for(Messages message : this.getMy_messages()) {
			System.out.println(message.getSender().attributes.get("nome")+": "+message.getContent());
		}			
	}
	
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId_comunity() {
		return id_comunity;
	}

	public List<Messages> getMy_messages() {
		return my_messages;
	}

	public void setMy_messages(List<Messages> my_messages) {
		this.my_messages = my_messages;
	}


	
}
