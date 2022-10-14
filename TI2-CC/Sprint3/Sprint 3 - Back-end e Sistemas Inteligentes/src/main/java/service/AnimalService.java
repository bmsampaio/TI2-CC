package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.ArtigoDAO;
import model.Artigo;
import spark.Request;
import spark.Response;


public class AnimalService {

	private AnimalDAO AnimalDAO = new AnimalDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID_ANIMAL = 1;
	private final int FORM_ORDERBY_USUARIO_E_MAIL = 2;
	private final int FORM_ORDERBY_ONG_CNPJ = 3;
	
	
	public AnimalService() {
		makeForm();
	}
	
	public String getHome() {
		makeForm();
		return form;
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Animal(), FORM_ORDERBY_USUARIO_E_MAIL);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Animal(), orderBy);
	}

	
	public void makeForm(int tipo, Animal animal, int orderBy) {
		String nomeArquivo = "index.html";
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
			umAnimal += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/artigo/list/1\">Novo Artigo</a></b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";
			umArtigo += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/animal/";
			String name, titulo, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Animal";
				titulo = "";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + animal.getIdArtigo();
				name = "Atualizar Animal (ID " + animal.getId_Animal() + ")";
				titulo = animal.getTitulo();
				buttonLabel = "Atualizar";
			}
			umAnimal += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Nome do Animal: <input class=\"input--register\" type=\"text\" name=\"titulo\" value=\""+ nome +"\"></td>";
			umAnimal += "\t\t\t<td>Especie: <input class=\"input--register\" type=\"text\" name=\"categoria\" value=\""+ animal.getEspecie() +"\"></td>";
			umAnimal += "\t\t\t<td>Porte: <input class=\"input--register\" type=\"text\" name=\"tag\" value=\""+ animal.getPorte() +"\"></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Dono: <input class=\"input--register\" type=\"text\" name=\"epalavraChave\" value=\""+ animal() + "\"></td>";
			umAnimal += "\t\t\t<td>Ong: <input class=\"input--register\" type=\"text\" name=\"texto\" value=\""+ animal.getOng + "\"></td>";
			umAnimal += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";
			umAnimal += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Artigo (ID " + artigo.getIdArtigo() + ")</b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Nome do Animal: "+ anima.getNome() +"</td>";
			umAnimal += "\t\t\t<td>Especie: "+ animal.getAnimal() +"</td>";
			umAnimal += "\t\t\t<td>Porte: "+ animal.getPorte() +"</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Dono: "+ animal.getAnimal().toString() + "</td>";
			umAnimal += "\t\t\t<td>Ong: "+ animal.getOng() + "</td>";
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
        		"\t<td><a href=\"/artigo/list/" + FORM_ORDERBY_USUARIO_E_MAIL + "\"><b>Email do Usuario</b></a></td>\n" +
        		"\t<td><a href=\"/artigo/list/" + FORM_ORDERBY_ONG_CNPJ + "\"><b>CNPJ da Ong</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Artigo> artigos;
		if (orderBy == FORM_ORDERBY_ID_ANIMAL) {                 	animal = animalDAO.getOrderBygetId_Animal();
		} else if (orderBy == FORM_ORDERBY_USUARIO_E_MAIL) {		animal = animalDAO.getOrderByUsuario_e_mail();
		} else if (orderBy == FORM_ORDERBY_USUARIO_E_MAIL) {			animal = animalDAO.getOrderByOng_cnpj();
		} else {											animal = animalDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Animal a : animal) {
			bgcolor = (i++ % 2 == 0) ? "#dcebff" : "#858fbb";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + a.getId_Animal() + "</td>\n" +
            		  "\t<td>" + a.getUsuario_e_mail() + "</td>\n" +
            		  "\t<td>" + a.getOng_cnpj() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/animal/" + a.getId_Animal() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/animal/update/" + a.getId_Animal() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteUsuario('" + a.getId_Animal() + "', '" + a.getUsuario_e_mail() + "', '" + a.getOng_cnpj() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-ANIMAL>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String Animal = request.queryParams("animal"));
		String Email = request.queryParams("emailDoUsuario");
		String Ong = request.queryParams("cnpjOng");
		String porte = request.queryParams("porte");
		String especie = request.queryParams("especie");
		
		String resp = "";
		
		Artigo animal = new Animal(-1, categoria, palavraChave, tag, titulo, texto);
		
		if(animalDAO.insert(animal) == true) {
            resp = "Animal (" + titulo + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Animal (" + titulo + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		String Id_Animal = Integer.parseInt(request.params(":Id_Animal"));		
		Animal animal = (Animal) animalDAO.get(Id_Animal);
		
		if (artigo != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, animal, FORM_ORDERBY_CATEGORIA);
        } else {
            response.status(404); // 404 Not found
            String resp = "Animal " + Id_Animal + " Não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		String Id_Animal = Integer.parseInt(request.params(":id_Animal"));		
		Animal animal = (Animal) animalDAO.get(Id_Animal);
		
		if (artigo != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, artigo, FORM_ORDERBY_CATEGORIA);
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
        int id_Animal = request.params(":id_Animal");
		Animal animal = animalDao.get(id_Animal);
        String resp = "";       

        if (animal != null) {
        	animal.setId_Animal (request.queryParams("id_Animal"));
        	animal.setDono(request.queryParams("dono"));
        	animal.setEspecie(request.queryParams("especie"));
        	animal.setPorte(request.queryParams("porte"));
        	animal.setOng_cnpj(request.queryParams("ong"));
        	animalDAO.update(animal);
        	response.status(200); // success
            resp = "Animal (id_Animal " + animal.getId_Animal() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Artigo (id_Animal \" + animal.getId_Animal() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        String id_Animal = request.params("id_Animal");
        Animal animal = animalDAO.get(id_Animal);
        String resp = "";       

        if (artigo != null) {
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
