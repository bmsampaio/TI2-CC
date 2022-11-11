package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO {
	
	public UsuarioDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Usuario usuario) {
		boolean status = false;
		try {
			String sql = "INSERT INTO usuario (email, nome, sobrenome, endereco, telefone, senha) "
		               + "VALUES ('" + usuario.getEmail() + "', '"
		               + usuario.getEmail() + "', '" + usuario.getNome() + "', '" + usuario.getSobrenome() + 
		               "', '" + usuario.getEndereco() +  "', '" + usuario.getTelefone() + "', '" + usuario.getSenha() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Usuario get(int email) {
		Usuario usuario = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE email = " + email + "'";
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	usuario = new Usuario(rs.getString("email"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("endereco"), rs.getString("telefone"), rs.getString("senha"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public List<Usuario> get() {
		return get("");
	}

	
	public List<Usuario> getOrderByEmail() {
		return get("email");		
	}
	
	
	public List<Usuario> getOrderByNome() {
		return get("nome");		
	}
	
	public List<Usuario> getOrderBySobrenome() {
		return get("sobrenome");		
	}
	
	public List<Usuario> getOrderByTelefone() {
		return get("telefone");		
	}
	
	public List<Usuario> getOrderBySenha() {
		return get("senha");		
	}
	
	
	private List<Usuario> get(String orderBy) {
		List<Usuario> usuario = new ArrayList<Usuario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Usuario u = new Usuario(rs.getString("email"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("endereco"), rs.getString("telefone"), rs.getString("senha"));
	        	usuario.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public boolean update(Usuario usuario) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET email = '" + usuario.getEmail() + "', "
					   + "nome = '" + usuario.getNome() + "', " 
					   + "sobrenome = '" + usuario.getSobrenome() + "',"
					   + "endereco = '" + usuario.getEndereco() + "', " 
					   + "telefone = '" + usuario.getTelefone() + "WHERE senha = " + usuario.getSenha() + "'";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int email) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM email WHERE email = " + email + "'");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
