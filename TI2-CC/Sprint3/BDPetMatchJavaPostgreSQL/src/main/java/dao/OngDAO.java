package dao;

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
		               + usuario.getEmail() + "', '" + ong.getEmail() + "', '" + ong.getSite() + 
		               "', '" + usuario.getNome() "');";
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
			String sql = "SELECT * FROM ong WHERE cnpj = " + cnpj;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 artigo = new Ong(rs.getString("cnpj"), rs.getString("email"), rs.getString("site"), 
	                				   rs.getString("nome")); 
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
	
	
	public List<Ong> getOrderByEmail() {
		return get("email");		
	}
	
	
	public List<Ong> getOrderBySite() {
		return get("site");		
	}
	
	public List<Ong> getOrderByNome() {
		return get("nome");		
	}
	
	
	private List<Ong> get(String orderBy) {
		List<Artigo> ong = new ArrayList<Ong>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM ong" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Ong u = new Ong(rs.getString("cnpj"), rs.getString("email"), rs.getString("site"), 
	        			                rs.getString("nome"));
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
					   + "email = '" + ong.geEmail() + "', " 
					   + "site = '" + ong.getSite() + "',"
					   + "nome = '" + ong.getNome() + "', " 
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int Cnpj) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM cnpj WHERE cnpj = " + Cnpj);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}

}
