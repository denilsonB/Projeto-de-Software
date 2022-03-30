import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	private int id_user;
	private String login;
	private String password;
	private String name;
	List<String> my_comunities = new ArrayList<String>();
	List<User> friends = new ArrayList<User>();
	List<Messages> my_messages = new ArrayList<Messages>();
	
	List<Entry<String,String>> attributes = new ArrayList<Entry<String,String>>();
	
	public User() {
		this.id_user = count.incrementAndGet();
	}
	
	public User(String login, String password, String name) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.id_user = count.incrementAndGet();
	}

	public void enter_comunity(String comunity_name) {
		this.my_comunities.add(comunity_name);
	}
	
	public void my_info() {
		//retona as informações do usuario
		System.out.println("Login: " + this.getLogin());
		System.out.println("Senha: " + this.getPassword());
		System.out.println("Nome: " + this.getName());
		System.out.println("Minhas comunidades:");
		for(String comun : this.getMy_comunities()) {
			System.out.println("-"+comun);
		}
		System.out.println("Amigos:");
		for(User friend : this.getFriends()) {
			System.out.println(friend.getName());
		}
		System.out.println("Messagens:");
		for(Messages message : this.getMy_messages()) {
			System.out.println(message.getContent());
		}		
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId_user() {
		return id_user;
	}

	public List<String> getMy_comunities() {
		return my_comunities;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<Messages> getMy_messages() {
		return my_messages;
	}

	public void setMy_messages(List<Messages> my_messages) {
		this.my_messages = my_messages;
	}

	public List<Entry<String, String>> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Entry<String, String>> attributes) {
		this.attributes = attributes;
	}

	
}
