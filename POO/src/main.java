import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		List<User> accounts = new ArrayList<User>();
		List<Comunity> comunities = new ArrayList<Comunity>();
		Scanner scanner = new Scanner(System.in).useDelimiter("\n");
		User user = new User();
		Comunity comunity = new Comunity();
		
		while (true){
			System.out.print("0-Sair\n1-Criar Conta\n2-Criar comunidade\n3-Entrar numa comunidade\n4-listar membros das comunidades\n5-Listar usuarios\n6-Minhas informações\n");
			String op = scanner.next();
			if(op.equals("0")) {
				System.out.print("Atá a proxima =p");
				break;
			}
			else if(op.equals("1")) {
		        System.out.print("Digite seu Login: ");
		        String login = scanner.next();
		        
		        System.out.print("Digite sua senha: ");
		        String password = scanner.next();
		        
		        System.out.print("Digite seu nome: ");
		        String name = scanner.next();
		        
		        user = new User(login,password,name);
		        accounts.add(user);
		        System.out.println("Conta criada com sucesso!!: ");
			}
			else if(op.equals("2")) {
		        System.out.print("Digite o nome da comunidade: ");
		        String comunity_name = scanner.next();
		        
		        System.out.print("Digite a descrição da comunidade: ");
		        String comunity_description = scanner.next();
		        
		        comunity = new Comunity(user.getId_user(),comunity_name,comunity_description);
		        
		        comunity.new_member(user);
		        
		        comunities.add(comunity);

		        user.enter_comunity(comunity_name);
		        
		        
		        System.out.println("Comunidade criada com sucesso!!: ");
		        
			}else if(op.equals("3")) {
				System.out.println("Lista de comunidades: ");
				for(Comunity comunit : comunities) {
					System.out.println(comunit.getId_comunity() +" "+comunit.getName() );
				}
		        System.out.print("Digite o numero da comunidade que deseja entrar: ");
		        int com = scanner.nextInt();
		        comunities.get(com-2).new_member(user);
		        user.enter_comunity(comunities.get(com-2).getName());
		        
			}else if(op.equals("4")) {
				for( Comunity comuniti : comunities) {
					System.out.println("Lista de membros da comunidade "+comuniti.getName());
					for (User usr : comuniti.getMembers()) {
						System.out.println(usr.getName());
					}
				}
			}else if(op.equals("5")) {
				System.out.println("Lista de usuarios: ");
				for( User usr : accounts) {
					System.out.println(usr.getName());
				}
			}else if(op.equals("6")) {
				user.my_info();
			}
	        

	        
	
		}
	}

}
