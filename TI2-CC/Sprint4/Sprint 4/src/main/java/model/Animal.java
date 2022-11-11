package model;

public class Animal {
	private int id_Animal, idade;
	private String dono, especie, porte, nome, caracteristicas, raca, usuario_e_mail, ong_cnpj;
	
	public Animal(){
		id_Animal = 0;
		dono = "";
		especie = "";
		porte = "";
		idade = 0;
		nome = "";
		caracteristicas = "";
		raca = "";
		usuario_e_mail = "";
		ong_cnpj = "";
	}
	
	public Animal(int id_Animal, String dono, String especie, String porte, int idade, String nome, String caracteristicas, String raca, String usuario_e_mail, String ong_cnpj){
		setId_Animal(id_Animal);
		setDono(dono);
		setEspecie(especie);
		setPorte(porte);
		setIdade(idade);
		setNome(nome);
		setCaracteristicas(caracteristicas);
		setRaca(raca);
		setUsuario_e_mail(usuario_e_mail);
		setOng_cnpj(ong_cnpj);
	}
	
	public int getId_Animal() {
		return id_Animal;
	}
	
	public void setId_Animal(int id_Animal) {
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
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
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

	public String getRaca(){
		return raca;
	}

	public void setRaca (String raca){
		this.raca = raca;
	}
	
	public String getUsuario_e_mail() {
		return caracteristicas;
	}
	
	public void setUsuario_e_mail (String usuario_e_mail) {
		this.usuario_e_mail = usuario_e_mail;
	}
	
	public String getOng_cnpj() {
		return ong_cnpj;
	}
	
	public void setOng_cnpj (String ong_cnpj) {
		this.ong_cnpj = ong_cnpj;
	}
	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Id: " + id_Animal + "Dono: " + dono + "Especie: " + especie + "Porte: " + porte + "Idade: " + idade + "Nome: " + nome + "Caracteristicas: " + caracteristicas + "Raça: " + raca + "Email do dono: " + usuario_e_mail + "Ong Cjnp: " + ong_cnpj;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId_Animal() == ((Animal) obj).getId_Animal());
	}	
}