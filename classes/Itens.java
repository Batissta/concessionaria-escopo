package classes;

import java.util.ArrayList;

public class Itens{
	private ArrayList<Item> produtos = new ArrayList<Item>();
	
	public boolean codigoExiste(long cod) {
		boolean verdade = false;
		for (Item item : produtos) {
			if ((long)item.getCodigo() == (long) cod) {
				verdade = true;
			}
		}
		return verdade;
	}
	
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
			System.out.printf("Impossível vender %d unidades de %s.\t-%d unidades disponivéis.\n",
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
}
