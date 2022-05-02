import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class main {
	public static User login(List<User> accounts, String login, String senha) {
        for(User usr : accounts) {
        	if(usr.attributes.get("login").equals(login)) {
        		if(usr.attributes.get("senha").equals(senha)) {
        			System.out.print("Seja bem vindo!! ");
        			return usr;
 
        		}else {
        			System.out.print("Login ou senha incorreto");
        			return null;		        			
        		}
        	}
        }
        System.out.print("Usuario não encontrado");
        return null;
	}
	
	public static void main(String[] args) {
		List<User> accounts = new ArrayList<User>();
		List<Comunity> comunities = new ArrayList<Comunity>();
		Scanner scanner = new Scanner(System.in).useDelimiter("\n");
		User user = new User();
		user=null;
		Comunity comunity = new Comunity();
		
		List<Message> feed_friends = new ArrayList<Message>();
		List<Message> feed_general = new ArrayList<Message>();
		
		String op = "1";
		while (!op.equals("3")) {
			System.out.print("\n1-Logar\n2-criar conta\n3-Sair\n");
			op = scanner.nextLine();
			if(op.equals("1")) {
		        System.out.print("Login: ");
		        String login = scanner.nextLine();
		        
		        System.out.print("Senha: ");
		        String password = scanner.nextLine();
		        
		        user = login(accounts,login,password);
			}else if(op.equals("2")) {
		        System.out.print("Digite seu Login: ");
		        String login = scanner.nextLine();
		        
		        System.out.print("Digite sua senha: ");
		        String password = scanner.nextLine();
		        
		        System.out.print("Digite seu nome: ");
		        String name = scanner.nextLine();
		        
		        System.out.print("Você é VIP?\n1-SIM\n2-NÃO");
		        String eVip = scanner.nextLine();
		        if(eVip.equals("1")) {
		        	user = new UserVip();
		        }else {
		        	user = new User();
		        }
		        
		        user.addAttribute("login",login);
		        user.addAttribute("senha", password);
		        user.addAttribute("nome",name);
		        
		        accounts.add(user);
		        System.out.println("Conta criada com sucesso!!: ");
			}
			while (!Objects.isNull(user)){
				System.out.println("\n0-Sair\n1-Criar comunidade\n2-Entrar numa comunidade\n3-listar membros das comunidades"
						+ "\n4-Adicionar amigo\n5-Minhas informações\n6-Adicionar novo atributo\n7-Editar perfil\n"
						+ "8-Notificações\n9-Mandar mensagem\n10-Ver feed da comunidade\n"
						+ "11-Postar no feed de noticias\n12-vizualisar feed\n13-Apagar conta\n");
				op = scanner.nextLine();
				
				if(op.equals("0")) {
					user = null;
					System.out.print("Até a proxima =p");
					break;
				}
				else if(op.equals("1")) {
					if(!(user instanceof UserVip)) {
						System.out.println("Somente VIP's podem criar comunidades ");
						continue;
					}
			        System.out.print("Digite o nome da comunidade: ");
			        String comunity_name = scanner.next();
			        
			        System.out.print("Digite a descrição da comunidade: ");
			        String comunity_description = scanner.next();
			        
			        comunity = new Comunity(user.getId_user(),comunity_name,comunity_description);
			        
			        comunity.new_member(user);
			        
			        comunities.add(comunity);

			        user.enter_comunity(comunity);
			        
			        
			        System.out.println("Comunidade criada com sucesso!!: ");
			        
				}else if(op.equals("2")) {
					System.out.println("Lista de comunidades: ");
					for(Comunity comunit : comunities) {
						System.out.println(comunit.getId_comunity() +" "+comunit.getName() );
					}
			        System.out.print("Digite o numero da comunidade que deseja entrar: ");
			        String choice = scanner.nextLine();
			        int com = Integer.parseInt(choice);
			        
			        comunities.get(com-2).new_member(user);
			        user.enter_comunity(comunities.get(com-2));
			        
				}else if(op.equals("3")) {
					for( Comunity comuniti : comunities) {
						System.out.println("Lista de membros da comunidade "+comuniti.getName());
						for (User usr : comuniti.getMembers()) {
							System.out.print(usr.attributes.get("nome"));
							System.out.print("\n");
						}
					}
				}else if(op.equals("4")) {
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

				}else if(op.equals("5")) {
					user.my_info();
				}else if(op.equals("6")) {
			        System.out.print("Digite o nome do atributo ");
			        String key = scanner.nextLine();
			        
			        System.out.print("Digite as informações do atributo: ");
			        String value = scanner.nextLine();
			        
			        user.addAttribute(key, value);
				}
				else if(op.equals("7")) {
					user.my_attributes();
			        System.out.print("Digite o nome do atributo para ser editado ");
			        String key = scanner.nextLine();
			        
			        System.out.print("Digite o novo valor do atributo: ");
			        String value = scanner.nextLine();
			        
			        user.editAttribute(key, value);
				}else if(op.equals("8")) {
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
				}else if(op.equals("9")) {
					System.out.print("1-Amigo\n2-Comunidade\n3-voltar:");
					String choice = scanner.nextLine();
					if(choice.equals("1")) {
						if(user.friends.size()>0) {
							user.my_friends();
							System.out.print("Digite o Id do amigo que deseja mandar mensagem: ");
							String id = scanner.nextLine();		
							System.out.print("Digite o conteudo da mensagem: ");
							String content = scanner.nextLine();	
							
							Message message = new Message(user,content);
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
							
							Message message = new Message(user,content);
							comunities.get(Integer.parseInt(id)-2).newMessage(message);
						}else {
							System.out.println("Você não faz parte de nenhuma comunidade :/ ");
						}
						
					}
				}else if(op.equals("10")) {
					if(user.my_comunities.size()>0) {
						user.my_comunities();
						System.out.print("Digite o Id da comunidade que deseja ver o feed: ");
						String id = scanner.nextLine();		
						System.out.print("Mensagens na "+comunities.get(Integer.parseInt(id)-2).getName());
						comunities.get(Integer.parseInt(id)-2).my_messages();
					}else {
						System.out.print("Você não faz parte de nenhuma comunidade :/ ");
					}
				}else if(op.equals("11")) {
					System.out.print("Digite o conteudo da mensagem: ");
					String content = scanner.nextLine();	
					
					Message message = new Message(user,content);
					
					System.out.print("Quem pode ver essa mensagem?\n1-Geral\n2-Só amigos ");
					String choice = scanner.nextLine();	
					if(choice.equals("1")) {
						feed_general.add(message);
					}else {
						feed_friends.add(message);
					}
				}else if(op.equals("12")) {
					System.out.println("Feed Geral: ");
					for(int i=0;i<feed_general.size();i++) {
						System.out.println(feed_general.get(i).getSender().attributes.get("nome")+": "+feed_general.get(i).getContent());
					}
					System.out.println("Feed Amigos: ");
					for(Message m : feed_friends) {
						if(user.getFriends().contains(m.getSender()) || m.getSender().equals(user)) {
							System.out.println(m.getSender().attributes.get("nome")+": "+m.getContent());
						}
					}
				}else if(op.equals("13")) {
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
						user = null;
					}
				}
		}
	
		}
	System.out.print("Obrigado por ter usado nosso sistems <3");
	}
}


	

