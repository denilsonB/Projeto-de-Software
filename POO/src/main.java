import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		List<User> accounts = new ArrayList<User>();
		List<Comunity> comunities = new ArrayList<Comunity>();
		Scanner scanner = new Scanner(System.in).useDelimiter("\n");
		User user = new User();
		Comunity comunity = new Comunity();
		
		List<Messages> feed_friends = new ArrayList<Messages>();
		List<Messages> feed_general = new ArrayList<Messages>();
		
		while (true){
			System.out.println("0-Sair\n1-Criar Conta\n2-Criar comunidade\n3-Entrar numa comunidade\n4-listar membros das comunidades"
					+ "\n5-Adicionar amigo\n6-Minhas informações\n7-Adicionar novo atributo\n8-Editar perfil\n9-Log in\n"
					+ "10-Notificações\n11-Mandar mensagem\n12-Ver feed da comunidade\n"
					+ "13-Postar no feed de noticias\n14-vizualisar feed\n15-Apagar conta");
			String op = scanner.nextLine();
			
			if(op.equals("0")) {
				System.out.print("Até a proxima =p");
				break;
			}
			else if(op.equals("1")) {
		        System.out.print("Digite seu Login: ");
		        String login = scanner.nextLine();
		        
		        System.out.print("Digite sua senha: ");
		        String password = scanner.nextLine();
		        
		        System.out.print("Digite seu nome: ");
		        String name = scanner.nextLine();
		        
		        user = new User();
		        
		        user.addAttribute("login",login);
		        user.addAttribute("senha", password);
		        user.addAttribute("nome",name);
		        
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

		        user.enter_comunity(comunity);
		        
		        
		        System.out.println("Comunidade criada com sucesso!!: ");
		        
			}else if(op.equals("3")) {
				System.out.println("Lista de comunidades: ");
				for(Comunity comunit : comunities) {
					System.out.println(comunit.getId_comunity() +" "+comunit.getName() );
				}
		        System.out.print("Digite o numero da comunidade que deseja entrar: ");
		        String choice = scanner.nextLine();
		        int com = Integer.parseInt(choice);
		        
		        comunities.get(com-2).new_member(user);
		        user.enter_comunity(comunities.get(com-2));
		        
			}else if(op.equals("4")) {
				for( Comunity comuniti : comunities) {
					System.out.println("Lista de membros da comunidade "+comuniti.getName());
					for (User usr : comuniti.getMembers()) {
						System.out.print(usr.attributes.get("nome"));
					}
				}
			}else if(op.equals("5")) {
				if(accounts.size()>1) {
					System.out.println("Lista de usuarios: ");
					for( User usr : accounts) {
						if(!usr.attributes.get("login").equals(user.attributes.get("login")))
							System.out.println(usr.getId_user() + "-"+usr.attributes.get("nome"));
					}
			        System.out.print("Digite o id do usuario que você quer ser amigo: ");
			        String id = scanner.nextLine();
			        User u = accounts.get(Integer.parseInt(id)-2);
			        u.newNotfication(user);
				}else {
					System.out.println("Infelizmente só você cricou conta até o momento");
				}

			}else if(op.equals("6")) {
				user.my_info();
			}else if(op.equals("7")) {
		        System.out.print("Digite o nome do atributo ");
		        String key = scanner.nextLine();
		        
		        System.out.print("Digite as informações do atributo: ");
		        String value = scanner.nextLine();
		        
		        user.addAttribute(key, value);
			}
			else if(op.equals("8")) {
				user.my_attributes();
		        System.out.print("Digite o nome do atributo para ser editado ");
		        String key = scanner.nextLine();
		        
		        System.out.print("Digite o novo valor do atributo: ");
		        String value = scanner.nextLine();
		        
		        user.editAttribute(key, value);
			}else if(op.equals("9")) {
		        System.out.print("Login: ");
		        String login = scanner.nextLine();
		        
		        System.out.print("Senha: ");
		        String password = scanner.nextLine();
		        
		        for(User usr : accounts) {
		        	if(usr.attributes.get("login").equals(login)) {
		        		if(usr.attributes.get("senha").equals(password)) {
		        			user = usr;
		        			System.out.print("Seja bem vindo!! ");
		        			break;
		        		}else {
		        			System.out.print("Login ou senha incorreto");
		        			break;		        			
		        		}
		        	}
		        }
			}else if(op.equals("10")) {
				System.out.println("Solicitações de amizade:");
				user.listNotifications();
				System.out.print("1-Aceitar\n2-Recusar\n3-Voltar:");
				String choice = scanner.nextLine();
				if(choice.equals("1")) {
					System.out.print("Digite o Id da notificação que você quer aceitar: ");
					String id = scanner.nextLine();
					User usr = user.notifications.remove(Integer.parseInt(id)-1);
					user.addFriend(usr);
					accounts.set(accounts.indexOf(usr), usr).addFriend(user);
				}else if(choice.equals("2")) {
					System.out.print("Digite o Id da notificação que você quer rejeitar: ");
					String id = scanner.nextLine();
					user.notifications.remove(Integer.parseInt(id)-1);
				}
			}else if(op.equals("11")) {
				System.out.print("1-Amigo\n2-Comunidade\n3-voltar:");
				String choice = scanner.nextLine();
				if(choice.equals("1")) {
					if(user.friends.size()>0) {
						user.my_friends();
						System.out.print("Digite o Id do amigo que deseja mandar mensagem: ");
						String id = scanner.nextLine();		
						System.out.print("Digite o conteudo da mensagem: ");
						String content = scanner.nextLine();	
						
						Messages message = new Messages(user,content);
						user.newMessage(message);
						accounts.get(Integer.parseInt(id)-2).newMessage(message);
					}else {
						System.out.println("Você não tem nenhum amigo :/ ");
					}
				}else if(choice.equals("2")) {
					if(user.my_comunities.size()>0) {
						user.my_comunities();
						System.out.print("Digite o Id da comunidade que deseja mandar mensagem: ");
						String id = scanner.nextLine();		
						System.out.print("Digite o conteudo da mensagem: ");
						String content = scanner.nextLine();	
						
						Messages message = new Messages(user,content);
						comunities.get(Integer.parseInt(id)-2).newMessage(message);
					}else {
						System.out.println("Você não faz parte de nenhuma comunidade :/ ");
					}
					
				}
			}else if(op.equals("12")) {
				if(user.my_comunities.size()>0) {
					user.my_comunities();
					System.out.print("Digite o Id da comunidade que deseja ver o feed: ");
					String id = scanner.nextLine();		
					System.out.print("Mensagens na "+comunities.get(Integer.parseInt(id)-2).getName());
					comunities.get(Integer.parseInt(id)-2).my_messages();
				}else {
					System.out.print("Você não faz parte de nenhuma comunidade :/ ");
				}
			}else if(op.equals("13")) {
				System.out.print("Digite o conteudo da mensagem: ");
				String content = scanner.nextLine();	
				
				Messages message = new Messages(user,content);
				
				System.out.print("Quem pode ver essa mensagem?\n1-Geral\n2-Só amigos ");
				String choice = scanner.nextLine();	
				if(choice.equals("1")) {
					feed_general.add(message);
				}else {
					feed_friends.add(message);
				}
			}else if(op.equals("14")) {
				System.out.println("Feed Geral: ");
				for(int i=0;i<feed_general.size();i++) {
					System.out.println(feed_general.get(i).getSender().attributes.get("nome")+": "+feed_general.get(i).getContent());
				}
				System.out.println("Feed Amigos: ");
				for(Messages m : feed_friends) {
					if(user.getFriends().contains(m.getSender()) || m.getSender().equals(user)) {
						System.out.println(m.getSender().attributes.get("nome")+": "+m.getContent());
					}
				}
			}else if(op.equals("15")) {
				System.out.print("Tem certeza que seja apagar a conta?\n1-Sim\n2-Não ");
				String choice = scanner.nextLine();
				if(choice.equals("1")) {
					for(int i=0;i<feed_general.size();i++) {
						if(feed_general.get(i).getSender().equals(user)){
							feed_general.remove(i);
						}
					}
					for(int i=0;i<feed_friends.size();i++) {
						if(feed_friends.get(i).getSender().equals(user)){
							feed_friends.remove(i);
						}
					}
					for(User friend : user.getFriends()) {
						for(int i=0;i<friend.my_messages.size();i++) {
							if(friend.my_messages.get(i).getSender().equals(user)) {
								friend.my_messages.remove(i);
							}
						}
					}	
					for(User friend : user.getFriends()) {
						friend.friends.remove(user);
					}
					for(Comunity comun : user.getMy_comunities()) {
						for(int i=0;i<comun.my_messages.size();i++) {
							if(comun.my_messages.get(i).getSender().equals(user)) {
								comun.my_messages.remove(i);
							}
						}
					}
					accounts.remove(user);
					user = new User();
				}
			}
		}
	}

}
