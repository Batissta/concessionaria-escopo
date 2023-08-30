package classes;

public class Item {
	private String nome;
	private long codigo;
	private int estoque;
	
	public Item(String nome, int cod){
		this.setNome(nome);
		this.setCodigo((long)cod);
		this.setEstoque(0);
	}
	
	protected String getNome() {
		return nome;
	}
	protected void setNome(String nome) {
		this.nome = nome;
	}
	protected long getCodigo() {
		return codigo;
	}
	protected void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	protected int getEstoque() {
		return estoque;
	}
	protected void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	
	protected void addEstoque(int n) {
		this.setEstoque(getEstoque() + n);
	}
	protected void venderEstoque(int n) {
			this.setEstoque(getEstoque() - n);
	}
	
}

