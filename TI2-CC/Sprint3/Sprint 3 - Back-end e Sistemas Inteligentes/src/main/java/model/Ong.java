package model;

public class Ong {
	private String cnpj, email, site, nome;
	
	public Usuario(){
		cnpj = "";
		email = "";
		site = "";
		nome = "";
	}
	
	public Usuario(String cnpj, String email, String site, String nome){
		setCnpj(cnpj);
		setEmail(email);
		setSite(site);
		setNome(nome);
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Cnpj: " + cnpj + "   Email: " + email + "  Site: " + site + "   Nome: "
				+ nome  + "   Telefone: " + telefone + "   Senha: " + senha;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getCnpj() == ((Ong) obj).getCnpj());
	}	
}