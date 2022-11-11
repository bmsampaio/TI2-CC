package model;

public class Usuario {
	private String email, nome, sobrenome, endereco, telefone, senha;
	
	public Usuario(){
		email = "";
		nome = "";
		sobrenome = "";
		endereco = "";
		telefone = "";
		senha = "";
	}
	
	public Usuario(String email, String nome, String sobrenome, String endereco, String telefone, String senha){
		setEmail(email);
		setNome(nome);
		setSobrenome(sobrenome);
		setEndereco(endereco);
		setTelefone(telefone);
		setSenha(senha);
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha (String senha) {
		this.senha = senha;
	}
	
	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Email: " + email + "   Nome: " + nome + "   Sobrenome: " + sobrenome + "   Endereco: "
				+ endereco  + "   Telefone: " + telefone + "   Senha: " + senha;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getEmail() == ((Usuario) obj).getEmail());
	}	
}