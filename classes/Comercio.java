package classes;

import java.util.ArrayList;
import util.Uteis;

public class Comercio {
	private ArrayList<Item> produtos = new ArrayList<>();
	
	public void menu() {
		System.out.println("Concessionária FUTURE");
		int esc = 6;
		do {
			System.out.printf(
					"-----------------------%s-----------------------\n1) Listar carros cadastrados.\n"+ "2) Cadastrar novo carro.\n"+ "3) Adicionar estoque a um carro existente.\n"+ "4) Remover um produto do comércio.\n"
					+ "5) Vender algum produto existente.\n"+ "6) Sair\n", "Menu");
			esc = Uteis.leInt("Sua escolha: ");
			Uteis.mostrarLinha();
			switch (esc) {
			case 1:
				listar();
				break;
			case 2:
				cadastrar();
				break;
			case 3:
				mudaEstoque("adicionar");
				break;
			case 4:
				remover();
				break;
			case 5:
				mudaEstoque("vender");
				break;
			case 6:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida...");
			}
	} while (esc !=6);
}
	private void listar() {
		if (produtos.size() == 0) {
			System.out.println("Nenhum carro foi cadastrado à concessionária");
		}else {
			for (int i = 0; i < produtos.size();i++) {
				System.out.printf("%d) %15s (cód.: %10d | estoque: %2d)\n",
						i+1,
						produtos.get(i).getNome(),
						produtos.get(i).getCodigo(),
						produtos.get(i).getEstoque());
			}
		}
	}
	private void novoItem(String nome, int cod) {
		Item i = new Item(nome, cod);
		produtos.add(i);
		System.out.printf("%s cadastrado com sucesso!\n", i.getNome());
	}
	
	private void cadastrar() {
		String nomedoCarro = Uteis.leString("Digite o nome do carro: ");
		while (true) {
			int codigodoCarro = Uteis.leInt("Digite o código do carro: ");
			if (produtos.size()>0) {
				if (findCod(codigodoCarro) != null) {
					System.out.printf("Já temos o carro %s com esse código.\n1) Adicionar estoque\n2) Passar um novo código\n",
							findCod(codigodoCarro).getNome());
					int esc = Uteis.leInt("Sua escolha: ");
					if (esc == 1)
						{mudaEstoque("adicionar");
						break;}
					else if (esc == 2) {
						continue;
					}else {
						System.out.println("Opção inválida. Você será redirecionado ao menu...");
						break;}
				}else {
					novoItem(nomedoCarro, codigodoCarro);
					break;}
			}else{
				novoItem(nomedoCarro, codigodoCarro);
				break;}		
			}
	}
	private void mudaEstoque(String n) {
		if(produtos.size() >0) {
			int codAcesso = Uteis.leInt("Digite o código de acesso do carro: ");
			if (findCod(codAcesso) != null){
				int unidadesAModificar = Uteis.leInt("Digite quantas unidades você deseja "+ n.toLowerCase()+": ");
				if (n.toLowerCase().equals("adicionar")) adicionar(codAcesso, unidadesAModificar);
				else if (n.toLowerCase().equals("vender")) vender(codAcesso, unidadesAModificar);
			}else {
				System.out.println("Código inválido. Não temos nenhum carro com esse acesso.");
			}
		}else {
			System.out.println("Nenhum carro foi cadastrado à concessionária");
		}
	}
	private void remover() {
		if(produtos.size() >0) {
			while (true) {
				int codigodoCarro = Uteis.leInt("Digite o código do carro: ");
				if (findCod(codigodoCarro) != null) {
					if( findCod(codigodoCarro).getEstoque() > 0){
						String confirmacao = Uteis.leString(findCod(codigodoCarro).getNome()+" tem "+
						findCod(codigodoCarro).getEstoque()+ " unidades em estoque.\nDeseja remover mesmo assim? ");
						if ((confirmacao.toLowerCase()).contains("s")) {
							System.out.printf("%s e todo o seu estoque foi removido.\n", findCod(codigodoCarro).getNome());
							produtos.remove(findCod(codigodoCarro));
							break;
						}
						else {
							System.out.println("Remoção cancelada. Você será redirecionado ao menu...");
							break;
						}
					}else {
						System.out.printf("%s foi removido da Concessionária.\n", findCod(codigodoCarro).getNome());
						produtos.remove(findCod(codigodoCarro));
						break;
					}
				}else {
					System.out.println("Código inválido.");
					break;
					}
				}
	}else {
		System.out.println("Nenhum carro foi cadastrado à concessionária");
	}
	}
	private Item findCod(long cod) {
		Item selecionado = null;
		for (Item item : produtos) {
			if (item.getCodigo() == cod) {
				selecionado = item;
			}
		}
		return selecionado;
	}
	private void vender(long cod, int aVender) {
		Item selecionado = findCod(cod);
		if (selecionado.getEstoque() < aVender) {
			System.out.printf("Impossível vender %d unidade(s) do %s.\n",
					aVender, selecionado.getNome());
			if (selecionado.getEstoque() > 0) {
				System.out.printf("%d unidades disponivéis.\n",selecionado.getEstoque());
			}else {
				System.out.println("Nenhuma unidade disponível.");
			}
		}else {
			selecionado.venderEstoque(aVender);
			System.out.printf("%d unidades de %s foram vendidas!\n", aVender, selecionado.getNome());
	}
	}
	private void adicionar(long cod, int adiciona) {
		Item selecionado = findCod(cod);
		selecionado.addEstoque(adiciona);
		System.out.printf("Estoque do %s foi adicionado com sucesso!\n", selecionado.getNome());
}
}
