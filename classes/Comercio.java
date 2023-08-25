package classes;

import java.util.Scanner;

public class Comercio {
	private Itens produtos = new Itens();
	private Scanner t = new Scanner(System.in);
	
	public void mostrarLinha() {
		System.out.println("------------------------------------------------");
	}
	private int leInt(String msg) {
		System.out.print(msg);
		int num = Integer.parseInt(t.nextLine());
		return num;
	}
	public void menu() {
		while (true) {
			System.out.printf(
					"%s\n1) Listar carros cadastrados.\n"+ "2) Cadastrar novo carro.\n"+ "3) Adicionar estoque a um carro existente.\n"+ "4) Remover um produto do comércio.\n"
					+ "5) Vender algum produto existente.\n"+ "6) Sair\n", "MENU - Concessionária FUTURE");
			int esc = leInt("Sua escolha: ");
			mostrarLinha();
			if (esc == 1) {
				listar();
				mostrarLinha();
			}else if (esc == 2) {
				cadastrar();
				mostrarLinha();
			}else if (esc == 3) {
				mudaEstoque("adicionar");
				mostrarLinha();
			}else if (esc == 4) {
				remover();
				mostrarLinha();
			}else if (esc == 5) {
				mudaEstoque("vender");
				mostrarLinha();
			}else if (esc == 6) {
				System.out.println("Saindo do sistema...");
				break;
			}else {
				System.out.println("Opção inválida...");}
	}
}
	public void listar() {
		if (produtos.getProdutos().size() == 0) {
			System.out.println("Nenhum carro foi cadastrado à concessionária");
		}else {
			produtos.listar();
		}
	}
	public void cadastrar() {
		System.out.print("Digite o nome do carro: ");
		String nomedoCarro = t.nextLine();
		while (true) {
			int codigodoCarro = leInt("Digite o código do carro: ");
			if (produtos.getProdutos().size()>0) {
				if (produtos.codigoExiste(codigodoCarro)) {
					System.out.printf("Já temos o carro %s com esse código.\n1) Adicionar estoque\n2) Passar um novo código\n",
							produtos.findCod(codigodoCarro).getNome());
					int esc = leInt("Sua escolha: ");
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
			int codAcesso = leInt("Digite o código de acesso do carro: ");
			if (produtos.findCod(codAcesso) != null){
				System.out.printf("Digite quantas unidades você deseja %s: ", n.toLowerCase());
				int unidadesAModificar = Integer.parseInt(t.nextLine());
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
				int codigodoCarro = leInt("Digite o código do carro: ");
				if (produtos.findCod(codigodoCarro) != null) {
					if(produtos.findCod(codigodoCarro).getEstoque() > 0){
						System.out.printf("%s tem %d unidades em estoque. \nDeseja remover mesmo assim? ", 
								produtos.findCod(codigodoCarro).getNome(),produtos.findCod(codigodoCarro).getEstoque());
						String confirmacao = t.nextLine();
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
