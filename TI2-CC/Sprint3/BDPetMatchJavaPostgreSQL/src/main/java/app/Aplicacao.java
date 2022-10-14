package app;

import static spark.Spark.*;
import service.*;


public class Aplicacao {
	
	private static UsuarioService usuarioService = new UsuarioService();
	private static OngService ongService = new OngService();
	private static AnimalService animalService = new AnimalService();
	
	
	 public static void main(String[] args) {
	        port(6789);
	        
	        staticFiles.location("/public");
	 
	        
	        get("/", (request, response) -> animalService.getHome());
	        
	        get("/animal", (request, response) -> animalService.getHome());
	        
			post("/animal/insert", (request, response) -> animalService.insert(request, response));

	        get("/animal/:idUsuario", (request, response) -> animalService.get(request, response));
	        
	        get("/animal/list/:orderby", (request, response) -> animalService.getAll(request, response));

	        get("/animal/update/:idUsuario", (request, response) -> animalService.getToUpdate(request, response));
	        
	        post("/animal/update/:idUsuario", (request, response) -> animalService.update(request, response));
	           
	        get("/animal/delete/:idUsuario", (request, response) -> animalService.delete(request, response));
	        

	        
	        get("/usuario", (request, response) -> usuarioService.getHome());
	        
	        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));

	        get("/usuario/:idUsuario", (request, response) -> usuarioService.get(request, response));
	        
	        get("/usuario/list/:orderby", (request, response) -> usuarioService.getAll(request, response));

	        get("/usuario/update/:idUsuario", (request, response) -> usuarioService.getToUpdate(request, response));
	        
	        post("/usuario/update/:idUsuario", (request, response) -> usuarioService.update(request, response));
	           
	        get("/usuario/delete/:idUsuario", (request, response) -> usuarioService.delete(request, response));
	        
	        
	        
	        get("/ong", (request, response) -> ongService.getHome());
	        
	        post("/ong/insert", (request, response) -> ongService.insert(request, response));

	        get("/ong/:idUsuario", (request, response) -> ongService.get(request, response));
	        
	        get("/ong/list/:orderby", (request, response) -> ongService.getAll(request, response));

	        get("/ong/update/:idUsuario", (request, response) -> ongService.getToUpdate(request, response));
	        
	        post("/ong/update/:idUsuario", (request, response) -> ongService.update(request, response));
	           
	        get("/ong/delete/:idUsuario", (request, response) -> ongService.delete(request, response));
	    }
	}