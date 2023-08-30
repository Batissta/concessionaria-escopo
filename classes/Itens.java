package classes;

import java.util.ArrayList;

public class Itens{
	private ArrayList<Item> produtos = new ArrayList<Item>();
	
	public Item findCod(long cod) {
		Item selecionado = null;
		for (Item item : produtos) {
			if (item.getCodigo() == cod) {
				selecionado = item;
			}
		}
		return selecionado;
	}
	public void vender(long cod, int aVender) {
		Item selecionado = findCod(cod);
		if (selecionado.getEstoque() < aVender) {
			System.out.printf("Impossível vender %d unidades de %s.\n%d unidades disponivéis.\n",
					aVender, selecionado.getNome(), selecionado.getEstoque());
		}else {
			selecionado.venderEstoque(aVender);
			System.out.printf("%d unidades de %s foram vendidas!\n", aVender, selecionado.getNome());
	}
	}
	public void listar() {
		for (int i = 0; i < produtos.size();i++) {
			System.out.printf("%d) %12s (cód.: %10d | estoque: %4d)\n",
					i+1,
					produtos.get(i).getNome(),
					produtos.get(i).getCodigo(),
					produtos.get(i).getEstoque());
		}
	}
	public Item novoItem(String nome, int cod) {
		Item novo = new Item(nome, cod);
		return novo;
	}
	public void cadastrar(Item i) {
		produtos.add(i);
		System.out.printf("%s cadastrado com sucesso!\n", i.getNome());
	}
	
	public void adicionar(long cod, int adiciona) {
			Item selecionado = findCod(cod);
			selecionado.addEstoque(adiciona);
			System.out.printf("Estoque do %s foi adicionado com sucesso!\n", selecionado.getNome());
		
	}
	public void remover(long cod) {
			produtos.remove(findCod(cod));
	}

	public ArrayList<Item> getProdutos() {
		return produtos;
	}

class Item {
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
}
