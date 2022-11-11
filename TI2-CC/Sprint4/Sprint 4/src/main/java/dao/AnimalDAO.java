package dao;

import model.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends DAO {
	
	public AnimalDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Animal animal) {
		boolean status = false;
		try {
			String sql = "INSERT INTO animal (id_animal, dono, especie, porte, idade, nome, caracteristicas, raca, usuario_e_mail, ong_cnpj) " + "VALUES ('" + animal.getId_Animal() + "', '" + animal.getDono() + "', '" + animal.getEspecie() + "', '" + animal.getPorte() +  "', '" + animal.getIdade() +  "', '" + animal.getNome() + "', '" + animal.getCaracteristicas() + "', '" + animal.getRaca() + "', '" + animal.getUsuario_e_mail() + "', '" + animal.getOng_cnpj() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Animal get(int Id_Animal) {
		Animal animal = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM animal WHERE id_animal = " + Id_Animal;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	animal = new Animal(rs.getInt("id_animal"), rs.getString("dono"), rs.getString("especie"), rs.getString("porte"), rs.getInt("idade"), rs.getString("nome"), rs.getString("caracteristicas"), rs.getString("raca"), rs.getString("usuario_e_mail"), rs.getString("ONG_cnpj"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return animal;
	}
	
	
	public List<Animal> get() {
		return get("");
	}

	
	public List<Animal> getOrderById_Animal() {
		return get("id_animal");		
	}
	
	
	public List<Animal> getOrderByDono() {
		return get("dono");		
	}
	
	public List<Animal> getOrderByEspecie() {
		return get("especie");		
	}
	
	public List<Animal> getOrderByPorte() {
		return get("porte");		
	}
	
	public List<Animal> getOrderByIdade() {
		return get("idade");		
	}
	
	public List<Animal> getOrderByNome() {
		return get("nome");		
	}
	
	public List<Animal> getOrderByCaracteristicas() {
		return get("caracteristicas");		
	}
	
	public List<Animal> getOrderByRaca() {
		return get("raca");		
	}
	
	public List<Animal> getOrderByUsuario_e_mail() {
		return get("usuario_e_mail");		
	}
	
	public List<Animal> getOrderByOng_cnpj() {
		return get("ONG_cnpj");		
	}
	
	
	private List<Animal> get(String orderBy) {
		List<Animal> animal = new ArrayList<Animal>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM animal" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Animal u = new Animal(rs.getInt("id_animal"), rs.getString("dono"), rs.getString("especie"), rs.getString("porte"), rs.getInt("idade"), rs.getString("nome"), rs.getString("caracteristicas"), rs.getString("raca"), rs.getString("usuario_e_mail"), rs.getString("ONG_cnpj"));
	        	animal.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return animal;
	}
	
	
	public boolean update(Animal animal) {
		boolean status = false;
		try {  
			String sql = "UPDATE animal SET id_animal = '" + animal.getId_Animal() + "', "
					   + "dono = '" + animal.getDono() + "', " 
					   + "especie = '" + animal.getEspecie() + "',"
					   + "porte = '" + animal.getPorte() + "', " 
					   + "idade = '" + animal.getIdade() + "', " 
					   + "nome = '" + animal.getNome() + "', " 
					   + "caracteristicas = '" + animal.getCaracteristicas() + "', " 
					   + "raca = '" + animal.getRaca() + "', " 
					   + "usuario_e_mail = '" + animal.getUsuario_e_mail() + "WHERE ONG_cnpj = " + animal.getOng_cnpj() + "'";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id_animal) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM id_animal WHERE id_animal = " + id_animal + "'");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
