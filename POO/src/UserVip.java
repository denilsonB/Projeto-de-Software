
public class UserVip extends User {
	private String vip = "Usuário VIP";
	
	public UserVip() {
		super();
	}

	public String getVip() {
		return vip;
	}

	@Override
	public void my_attributes() {
		System.out.println(this.getVip());
		super.my_attributes();
	}
	

}
