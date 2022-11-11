package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.OngDAO;
import model.Ong;
import spark.Request;
import spark.Response;


public class OngService {

	private OngDAO ongDAO = new OngDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_CNPJ = 1;
	private final int FORM_ORDERBY_NOME = 2;
	
	
	public OngService() {
		makeForm();
	}
	
	public void makeForm() {
		makeForm(FORM_INSERT, new Ong(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Ong(), orderBy);
	}

	
	public void makeForm(int tipo, Ong ong, int orderBy) {
		String nomeArquivo = "cadastro.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umONG = "";
		if(tipo != FORM_INSERT) {
			umONG += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umONG += "\t\t<tr>";
			umONG += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/faq/list/1\">Nova Pergunta</a></b></font></td>";
			umONG += "\t\t</tr>";
			umONG += "\t</table>";
			umONG += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/ong/";
			String nome, cnpj, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				nome = "ONGa";
				cnpj = "Inserir cnpj";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + ong.getCnpj();
				nome = "Atualizar Nome -- " + ong.getNome();
				cnpj = ong.getCnpj();
				buttonLabel = "Atualizar";
			}
			umONG += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umONG += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umONG += "\t\t<tr>";
			umONG += "\t\t\t<td colspan=\"3\" align=\"center\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + nome + "</b></font></td>";
			umONG += "\t\t</tr>";
			umONG += "\t\t<tr>";
			umONG += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umONG += "\t\t</tr>";
			umONG += "\t\t<tr>";
			umONG += "\t\t\t<td>&nbsp;Cnpj: <input class=\"input--register\" type=\"text\" name=\"cnpj\" value=\""+ cnpj +"\"></td>";
			umONG += "\t\t\t<td>Nome: <input class=\"input--register\" type=\"text\" name=\"name\" value=\""+ ong.getNome() +"\"></td>";
			umONG += "\t\t\t<td>Email: <input class=\"input--register\" type=\"text\" name=\"email\" value=\""+ ong.getEmail() +"\"></td>";
			umONG += "\t\t</tr>";
			umONG += "\t\t<tr>";
			umONG += "\t\t\t<td>Site: <input class=\"input--register\" type=\"text\" name=\"site\" value=\""+ ong.getSite() +"\"></td>";
			umONG += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umONG += "\t\t</tr>";	
			umONG += "\t</table>";
			umONG += "\t</form>";
		} else if (tipo == FORM_DETAIL){
			umONG += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umONG += "\t\t<tr>";
			umONG += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Ong (ID " + ong.getCnpj() + ")</b></font></td>";
			umONG += "\t\t</tr>";
			umONG += "\t\t<tr>";
			umONG += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umONG += "\t\t</tr>";
			umONG += "\t\t<tr>";
			umONG += "\t\t\t<td>&nbsp;Cnpj: "+ ong.getCnpj() +"</td>";
			umONG += "\t\t\t<td>&nbsp;Nome: "+ ong.getNome() +"</td>";
			umONG += "\t\t\t<td>Email: "+ ong.getEmail() +"</td>";
			umONG += "\t\t</tr>";
			umONG += "\t\t<tr>";
			umONG += "\t\t\t<td>&nbsp;Site: "+ ong.getSite() +"</td>";
			umONG += "\t\t</tr>";
			umONG += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo nãoo identificado " + tipo);
		}
		form = form.replaceFirst("<UM-ONG>", umONG);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação FAQ</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/faq/list/" + FORM_ORDERBY_CNPJ + "\"><b>CNPJ</b></a></td>\n" +
        		"\t<td><a href=\"/faq/list/" + FORM_ORDERBY_NOME + "\"><b>NOME</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Ong> ongs;
		if (orderBy == FORM_ORDERBY_CNPJ) {                 	ongs = ongDAO.getOrderByCnpj();
		} else if (orderBy == FORM_ORDERBY_NOME) {		ongs = ongDAO.getOrderByEmail();
		} else {											ongs = ongDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Ong f : ongs) {
			bgcolor = (i++ % 2 == 0) ? "#dcebff" : "#858fbb";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + f.getCnpj() + "</td>\n" +
            		  "\t<td>" + f.getNome() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/faq/" + f.getCnpj() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/faq/update/" + f.getCnpj() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteFaq('" + f.getCnpj() + "', '" + f.getNome() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-CNPJ>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String cnpj = request.queryParams("cnpj");
		String nome = request.queryParams("nome");
		String email = request.queryParams("email");
		String site = request.queryParams("site");
		
		String resp = "";
		
		Ong ong = new Ong(cnpj, nome, email, site);
		
		if(ongDAO.insert(ong) == true) {
            resp = "Ong (" + cnpj + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Ong (" + cnpj + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int cnpj = Integer.parseInt(request.params(":cnpj"));			
		Ong ong = (Ong) ongDAO.get(cnpj);
		
		if (ong != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, ong, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Ong " + cnpj + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int cnpj = Integer.parseInt(request.params(":cnpj"));	
		Ong ong = (Ong) ongDAO.get(cnpj);
		
		if (ong != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, ong, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Ong " + cnpj+ " não encontrado.";
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
        int cnpj = Integer.parseInt(request.params(":cnpj"));
		Ong ong = ongDAO.get(cnpj);
        String resp = "";       

        if (ong != null) {
			ong.setCnpj(request.queryParams("cnpj"));
			ong.setNome(request.queryParams("nome"));
			ong.setEmail(request.queryParams("email"));
			ong.setSite(request.queryParams("site"));
        	ongDAO.update(ong);
        	response.status(200); // success
            resp = "Ong (cnpj " + ong.getCnpj() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Ong (cnpj \" + ong.getCnpj() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
		int cnpj = Integer.parseInt(request.params(":cnpj"));
        Ong ong = ongDAO.get(cnpj);
        String resp = "";       

        if (ong != null) {
            ongDAO.delete(cnpj);
            response.status(200); // success
            resp = "Ong (" + cnpj + ") excluido!";
        } else {
            response.status(404); // 404 Not found
            resp = "Ong (" + cnpj + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}