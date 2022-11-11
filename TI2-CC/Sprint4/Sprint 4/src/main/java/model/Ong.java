package model;

public class Ong {
	private String cnpj, nome, email, site;
	
	public Ong(){
		cnpj = "";
		nome = "";
		email = "";
		site = "";

	}
	
	public Ong(String cnpj, String nome, String email, String site){
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
		return "Cnpj: " + cnpj +  "	Nome: " + nome + " Email: " + email + "  Site: " + site;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getCnpj() == ((Ong) obj).getCnpj());
	}	
}