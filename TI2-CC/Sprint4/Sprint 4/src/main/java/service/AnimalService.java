package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.AnimalDAO;
import model.Animal;
import spark.Request;
import spark.Response;


public class AnimalService {

	private AnimalDAO animalDAO = new AnimalDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID_ANIMAL = 1;
	private final int FORM_ORDERBY_NOME = 2;
	
	
	public AnimalService() {
		makeForm();
	}
	
	public String getHome() {
		makeForm();
		return form;
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Animal(), FORM_ORDERBY_ID_ANIMAL);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Animal(), orderBy);
	}

	
	public void makeForm(int tipo, Animal animal, int orderBy) {
		String nomeArquivo = "cadastro.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umAnimal = "";
		if(tipo != FORM_INSERT) {
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/artigo/list/1\">Novo Animal</a></b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";
			umAnimal += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/animal/";
			int idAnimal;
			String nome, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				nome = "Inserir Nome";
				idAnimal = 0;
				buttonLabel = "Inserir";
			} else {
				action += "update/" + animal.getId_Animal();
				nome = "Atualizar Nome " + animal.getNome();
				idAnimal = animal.getId_Animal();
				buttonLabel = "Atualizar";
			}
			umAnimal += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + nome + "</b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Id do animal: <input class=\"input--register\" type=\"text\" name=\"id\" value=\""+ idAnimal +"\"></td>";
			umAnimal += "\t\t\t<td>Dono: <input class=\"input--register\" type=\"text\" name=\"dono\" value=\""+ animal.getDono() +"\"></td>";
			umAnimal += "\t\t\t<td>Especie: <input class=\"input--register\" type=\"text\" name=\"especie\" value=\""+ animal.getEspecie() +"\"></td>";
			umAnimal += "\t\t\t<td>Porte: <input class=\"input--register\" type=\"text\" name=\"porte\" value=\""+ animal.getPorte() +"\"></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Idade: <input class=\"input--register\" type=\"text\" name=\"idade\" value=\""+ animal.getIdade() + "\"></td>";
			umAnimal += "\t\t\t<td>Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ animal.getNome() +"\"></td>";
			umAnimal += "\t\t\t<td>Caracteristicas: <input class=\"input--register\" type=\"text\" name=\"caracteristicas\" value=\""+ animal.getCaracteristicas() +"\"></td>";
			umAnimal += "\t\t\t<td>Raça: <input class=\"input--register\" type=\"text\" name=\"raca\" value=\""+ animal.getRaca() +"\"></td>";
			umAnimal += "\t\t\t<td>E-mail do usuario: <input class=\"input--register\" type=\"text\" name=\"email\" value=\""+ animal.getUsuario_e_mail() +"\"></td>";
			umAnimal += "\t\t\t<td>Ong: <input class=\"input--register\" type=\"text\" name=\"cnpj\" value=\""+ animal.getOng_cnpj() + "\"></td>";
			umAnimal += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";
			umAnimal += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Artigo (ID " + animal.getId_Animal() + ")</b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Id: "+ animal.getId_Animal() + "</td>";
			umAnimal += "\t\t\t<td>&nbsp;Dono: "+ animal.getDono() + "</td>";
			umAnimal += "\t\t\t<td>Especie: "+ animal.getEspecie() +"</td>";
			umAnimal += "\t\t\t<td>Porte: "+ animal.getPorte() +"</td>";
			umAnimal += "\t\t\t<td>Idade: "+ animal.getIdade() +"</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Nome do Animal: "+ animal.getNome() +"</td>";
			umAnimal += "\t\t\t<td>Caracteristicas: "+ animal.getCaracteristicas() +"</td>";
			umAnimal += "\t\t\t<td>Raca: "+ animal.getRaca() +"</td>";
			umAnimal += "\t\t\t<td>E-mail do Usuario: "+ animal.getUsuario_e_mail() +"</td>";
			umAnimal += "\t\t\t<td>Cnpj da Ong: "+ animal.getOng_cnpj() + "</td>";
			umAnimal += "\t\t\t<td>&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-ANIMAL>", umAnimal);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Catálogo de Artigos</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/artigo/list/" + FORM_ORDERBY_ID_ANIMAL + "\"><b>Id Animal</b></a></td>\n" +
        		"\t<td><a href=\"/artigo/list/" + FORM_ORDERBY_NOME + "\"><b>Email do Usuario</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Animal> animais;
		if (orderBy == FORM_ORDERBY_ID_ANIMAL) {                 	animais = animalDAO.getOrderById_Animal();
		} else if (orderBy == FORM_ORDERBY_NOME) {		animais = animalDAO.getOrderByNome();
		} else {											animais = animalDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Animal a : animais) {
			bgcolor = (i++ % 2 == 0) ? "#dcebff" : "#858fbb";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + a.getId_Animal() + "</td>\n" +
            		  "\t<td>" + a.getNome() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/animal/" + a.getId_Animal() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/animal/update/" + a.getId_Animal() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteUsuario('" + a.getId_Animal() + "', '" + a.getNome() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-ANIMAL>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		int id = Integer.parseInt(request.queryParams("id"));
		String dono = request.queryParams("dono");
		String especie = request.queryParams("especie");
		String porte = request.queryParams("porte");
		int idade = Integer.parseInt(request.queryParams("idade"));
		String nome = request.queryParams("nome");
		String caracteristicas = request.queryParams("caracteristicas");
		String raca = request.queryParams("raca");
		String emailDono = request.queryParams("email");
		String ongAnimal = request.queryParams("cnpj");
		
		String resp = "";
		
		Animal animal = new Animal(id, dono, especie, porte, idade, nome, caracteristicas, raca, emailDono, ongAnimal);
		
		if(animalDAO.insert(animal) == true) {
            resp = "Animal (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Animal (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int Id_Animal = Integer.parseInt(request.params(":Id_Animal"));		
		Animal animal = (Animal) animalDAO.get(Id_Animal);
		
		if (animal != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, animal, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Animal " + Id_Animal + " Não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int Id_Animal = Integer.parseInt(request.params(":id_Animal"));		
		Animal animal = (Animal) animalDAO.get(Id_Animal);
		
		if (animal != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, animal, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Animal " + Id_Animal + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id_Animal = Integer.parseInt(request.params(":id_Animal"));
		Animal animal = animalDAO.get(id_Animal);
        String resp = "";       

        if (animal != null) {

        	animal.setDono(request.queryParams("dono"));
        	animal.setEspecie(request.queryParams("especie"));
        	animal.setPorte(request.queryParams("porte"));
			animal.setNome(request.queryParams("nome"));
			animal.setCaracteristicas(request.queryParams("caracteristicas"));
			animal.setRaca(request.queryParams("raca"));
			animal.setUsuario_e_mail(request.queryParams("email"));
        	animal.setOng_cnpj(request.queryParams("cnpj"));
        	animalDAO.update(animal);
        	response.status(200); // success
            resp = "Animal (id_Animal " + animal.getId_Animal() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Animal (id_Animal \" + animal.getId_Animal() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id_Animal = Integer.parseInt(request.params(":id_Animal"));
        Animal animal = animalDAO.get(id_Animal);
        String resp = "";       

        if (animal != null) {
        	animalDAO.delete(id_Animal);
            response.status(200); // success
            resp = "Animal (" + id_Animal + ") excluido!";
        } else {
            response.status(404); // 404 Not found
            resp = "Animal (" + id_Animal + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
