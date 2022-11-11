package dao;

import model.Ong;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OngDAO extends DAO {
	
	public OngDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Ong ong) {
		boolean status = false;
		try {
			String sql = "INSERT INTO ong (cnpj, email, site, nome) "
		               + "VALUES ('" + ong.getCnpj() + "', '"
		               + ong.getEmail() + "', '" + ong.getNome() + "', " + ong.getEmail() + "', '" + ong.getSite() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Ong get(int CnpjOng) {
		Ong ong = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM ong WHERE cnpj = " + CnpjOng + "'";
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	ong = new Ong(rs.getString("cnpj"), rs.getString("nome"), rs.getString("email"), rs.getString("site")); 
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return ong;
	}
	
	
	public List<Ong> get() {
		return get("");
	}

	
	public List<Ong> getOrderByCnpj() {
		return get("cnpj");		
	}

	public List<Ong> getOrderByNome() {
		return get("nome");		
	}
	
	public List<Ong> getOrderByEmail() {
		return get("email");		
	}
	
	
	public List<Ong> getOrderBySite() {
		return get("site");		
	}

	
	
	private List<Ong> get(String orderBy) {
		List<Ong> ong = new ArrayList<Ong>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM ong" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	    	while(rs.next()) {	            	
	        	Ong u = new Ong(rs.getString("cnpj"), rs.getString("nome"), rs.getString("email"), rs.getString("site"));
	        	ong.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return ong;
	}
	
	
	public boolean update(Ong ong) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET cnpj = '" + ong.getCnpj() + "', "
					+"nome = '" + ong.getNome() + "'," 
					+ "email = '" + ong.getEmail() + "'," 
					+ "site = '" + ong.getSite() + "'";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int cnpj) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM cnpj WHERE cnpj = " + cnpj + "'");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
