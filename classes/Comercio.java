package classes;

import util.Uteis;

public class Comercio {
	private Itens produtos = new Itens();
	
	public void menu() {
		int esc = 6;
		do {
			System.out.printf(
					"%s\n1) Listar carros cadastrados.\n"+ "2) Cadastrar novo carro.\n"+ "3) Adicionar estoque a um carro existente.\n"+ "4) Remover um produto do comércio.\n"
					+ "5) Vender algum produto existente.\n"+ "6) Sair\n", "MENU - Concessionária FUTURE");
			esc = Uteis.leInt("Sua escolha: ");
			Uteis.mostrarLinha();
			switch (esc) {
			case 1:
				listar();
				Uteis.mostrarLinha();
				break;
			case 2:
				cadastrar();
				Uteis.mostrarLinha();
				break;
			case 3:
				mudaEstoque("adicionar");
				Uteis.mostrarLinha();
				break;
			case 4:
				remover();
				Uteis.mostrarLinha();
				break;
			case 5:
				mudaEstoque("vender");
				Uteis.mostrarLinha();
				break;
			case 6:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida...");
			}
	} while (esc !=6);
}
	public void listar() {
		if (produtos.getProdutos().size() == 0) {
			System.out.println("Nenhum carro foi cadastrado à concessionária");
		}else {
			produtos.listar();
		}
	}
	public void cadastrar() {
		String nomedoCarro = Uteis.leString("Digite o nome do carro: ");
		while (true) {
			int codigodoCarro = Uteis.leInt("Digite o código do carro: ");
			if (produtos.getProdutos().size()>0) {
				if (produtos.findCod(codigodoCarro) != null) {
					System.out.printf("Já temos o carro %s com esse código.\n1) Adicionar estoque\n2) Passar um novo código\n",
							produtos.findCod(codigodoCarro).getNome());
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
					produtos.cadastrar(new Item(nomedoCarro,codigodoCarro));
					break;}
			}else{
				produtos.cadastrar(new Item(nomedoCarro,codigodoCarro));
				break;}		
			}
	}
	public void mudaEstoque(String n) {
		if(produtos.getProdutos().size() >0) {
			int codAcesso = Uteis.leInt("Digite o código de acesso do carro: ");
			if (produtos.findCod(codAcesso) != null){
				int unidadesAModificar = Uteis.leInt("Digite quantas unidades você deseja "+ n.toLowerCase()+": ");
				if (n.toLowerCase().equals("adicionar")) produtos.adicionar(codAcesso, unidadesAModificar);
				else if (n.toLowerCase().equals("vender")) produtos.vender(codAcesso, unidadesAModificar);
			}else {
				System.out.println("Código inválido. Não temos nenhum carro com esse acesso.");
			}
		}else {
			System.out.println("Nenhum carro foi cadastrado à concessionária");
		}
	}
	public void remover() {
		if(produtos.getProdutos().size() >0) {
			while (true) {
				int codigodoCarro = Uteis.leInt("Digite o código do carro: ");
				if (produtos.findCod(codigodoCarro) != null) {
					if(produtos.findCod(codigodoCarro).getEstoque() > 0){
						String confirmacao = Uteis.leString(produtos.findCod(codigodoCarro).getNome()+" tem "+
						produtos.findCod(codigodoCarro).getEstoque()+ " unidades em estoque.\nDeseja remover mesmo assim? ");
						if ((confirmacao.toLowerCase()).contains("s")) {
							System.out.printf("%s e todo o seu estoque foi removido.\n", produtos.findCod(codigodoCarro).getNome());
		
							produtos.remover(codigodoCarro);
							break;
						}
						else {
							System.out.printf("Remoção cancelada. Você será redirecionado ao menu...");
							break;
						}
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
}
