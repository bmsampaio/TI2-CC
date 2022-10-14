package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class UsuarioService {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_EMAIL = 1;
	private final int FORM_ORDERBY_NOME_USUARIO = 2;
	private final int FORM_ORDERBY_SOBRENOME = 3;
	
	
	public UsuarioService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Usuario(), FORM_ORDERBY_NOME_USUARIO);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Usuario(), orderBy);
	}

	
	public void makeForm(int tipo, Usuario usuario, int orderBy) {
		String nomeArquivo = "index.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umUsuario = "";
		if(tipo != FORM_INSERT) {
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/usuario/list/1\">Novo Usuario</a></b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";
			umUsuario += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/usuario/";
			String name, nomeUsuario, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Usuario";
				nomeUsuario = "User ";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + usuario.getEmail();
				name = "Atualizar Usuário -- " + usuario.getNomeUsuario();
				nomeUsuario = usuario.getNomeUsuario();
				buttonLabel = "Atualizar";
			}
			umUsuario += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Nome do Usuário: <input class=\"input--register\" type=\"text\" name=\"nomeUsuario\" value=\""+ nomeUsuario +"\"></td>";
			umUsuario += "\t\t\t<td>Endereco: <input class=\"input--register\" type=\"text\" name=\"primeiroNome\" value=\""+ usuario.getEndereco() +"\"></td>";
			umUsuario += "\t\t\t<td>Sobrenome: <input class=\"input--register\" type=\"text\" name=\"sobrenome\" value=\""+ usuario.getSobrenome() +"\"></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Email: <input class=\"input--register\" type=\"text\" name=\"email\" value=\""+ usuario.getEmail().toString() + "\"></td>";
			umUsuario += "\t\t\t<td>Senha: <input class=\"input--register\" type=\"text\" name=\"senha\" value=\""+ usuario.getSenha().toString() + "\"></td>";
			umUsuario += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";
			umUsuario += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Usuario -- " + usuario.getNomeUsuario() + "</b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Nome Usuario: "+ usuario.getNomeUsuario() +"</td>";
			umUsuario += "\t\t\t<td>Endereco: "+ usuario.getEndereco() +"</td>";
			umUsuario += "\t\t\t<td>Sobrenome: "+ usuario.getSobrenome() +"</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Email: "+ usuario.getEmail().toString() + "</td>";
			umUsuario += "\t\t\t<td>Senha: "+ usuario.getSenha().toString() + "</td>";
			umUsuario += "\t\t\t<td>&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-USUARIO>", umUsuario);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Usuários</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_EMAIL + "\"><b>Email</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_NOME_USUARIO + "\"><b>Nome Usuário</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_SOBRENOME + "\"><b>Sobrenome</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Usuario> usuarios;
		if (orderBy == FORM_ORDERBY_EMAIL) {                 	usuarios = usuarioDAO.getOrderByEmail();
		} else if (orderBy == FORM_ORDERBY_NOME_USUARIO) {		usuarios = usuarioDAO.getOrderByNomeUsuario();
		} else if (orderBy == FORM_ORDERBY_SOBRENOME) {			usuarios = usuarioDAO.getOrderBySobrenome();
		} else {											usuarios = usuarioDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Usuario u : usuarios) {
			bgcolor = (i++ % 2 == 0) ? "#dcebff" : "#858fbb";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + u.getEmail() + "</td>\n" +
            		  "\t<td>" + u.getNomeUsuario() + "</td>\n" +
            		  "\t<td>" + u.getSobrenome() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/" + u.getEmail() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/update/" + u.getEmail() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteUsuario('" + u.getEmail() + "', '" + u.getNomeUsuario() + "', '" + u.getSobrenome() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-USUARIO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String nomeUsuario = request.queryParams("nomeUsuario");
		String endereco = request.queryParams("endereco");
		String sobrenome = request.queryParams("sobrenome");
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		
		String resp = "";
		
		Usuario usuario = new Usuario(-1, email, nomeUsuario, senha, primeiroNome, sobrenome);
		
		if(usuarioDAO.insert(usuario) == true) {
            resp = "Usuário (" + nomeUsuario + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Usuário (" + nomeUsuario + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int email = Integer.parseInt(request.params(":email"));		
		Usuario usuario = (Usuario) usuarioDAO.get(email);
		
		if (usuario != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, usuario, FORM_ORDERBY_NOME_USUARIO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuário " + email + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	public String getHome() {
		makeForm();
		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int email = Integer.parseInt(request.params(":email"));		
		Usuario usuario = (Usuario) usuarioDAO.get(email);
		
		if (usuario != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, usuario, FORM_ORDERBY_NOME_USUARIO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuário " + email + " não encontrado.";
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
        int email = Integer.parseInt(request.params(":email"));
		Usuario usuario = usuarioDAO.get(email);
        String resp = "";       

        if (usuario != null) {
        	usuario.setEmail(request.queryParams("email"));
        	usuario.setNomeUsuario(request.queryParams("nomeUsuario"));
        	usuario.setSenha(request.queryParams("senha"));
        	usuario.setEndereco(request.queryParams("endereco"));
        	usuario.setSobrenome(request.queryParams("sobrenome"));
        	usuarioDAO.update(usuario);
        	response.status(200); // success
            resp = "Usuário (EMAIL " + usuario.getEmail() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuário (EMAIL \" + usuario.setEmail() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int email = Integer.parseInt(request.params("email"));
        Usuario usuario = usuarioDAO.get(email);
        String resp = "";       

        if (usuario != null) {
            usuarioDAO.delete(email);
            response.status(200); // success
            resp = "Usuário (" + email + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuário (" + email + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}