package maven.demo;

public class Filme {

	private int id;
	private String nome;
	
	private String categoria;
	private int classificacao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	public Filme(int id, String nome, String categoria, int classificacao) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.classificacao = classificacao;
	}
	
}
