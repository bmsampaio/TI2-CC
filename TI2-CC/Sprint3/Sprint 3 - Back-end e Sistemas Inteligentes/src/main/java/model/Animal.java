package model;

public class Animal {
	private String id_Animal, dono, especie, porte, idade, nome, caracteristicas, raca, usuario_e_mail, ONG_cnpj;
	
	public Usuario(){
		id_Animal = "";
		dono = "";
		especie = "";
		porte = "";
		idade = "";
		nome = "";
		caracteristicas = "";
		usuario_e_mail = "";
		ONG_cnpj = "";
	}
	
	public Usuario(String id_Animal, String dono, String especie, String porte, String idade, String nome, String caracteristicas, String usuario_e_mail, String ONG_cnpj){
		setId_Animal(id_Animal);
		setDono(dono);
		setEspecie(especie);
		setPorte(porte);
		setIdade(idade);
		setNome(nome);
		setCaracteristicas(caracteristicas);
		setUsuario_e_mail(usuario_e_mail);
		setOng_cnpj(ONG_cnpj);
	}
	
	public String getId_Animal() {
		return id_Animal;
	}
	
	public void setId_Animal(String id_Animal) {
		this.id_Animal = id_Animal;
	}
	
	public String getDono() {
		return dono;
	}
	
	public void setDono(String dono) {
		this.dono = dono;
	}
	
	public String getEspecie() {
		return especie;
	}
	
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	public String getPorte() {
		return porte;
	}
	
	public void setPorte(String porte) {
		this.porte = porte;
	}
	
	public String getIdade() {
		return idade;
	}
	
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCaracteristicas() {
		return caracteristicas;
	}
	
	public void setCaracteristicas (String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public String getUsuario_e_mail() {
		return caracteristicas;
	}
	
	public void setUsuario_e_mail (String usuario_e_mail) {
		this.usuario_e_mail = usuario_e_mail;
	}
	
	public String getOng_cnpj() {
		return ONG_cnpj;
	}
	
	public void setOng_cnpj (String ONG_cnpj) {
		this.ONG_cnpj = ONG_cnpj;
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
		return (this.getId_Animal() == ((Animal) obj).getId_Animal());
	}	
}