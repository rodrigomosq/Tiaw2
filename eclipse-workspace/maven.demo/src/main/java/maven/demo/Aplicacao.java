package maven.demo;

import java.util.Scanner;

public class Aplicacao {

	public static void main(String[] args) {
		FilmeDAO dao = new FilmeDAO();
		dao.conectar();
		Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione uma opção:");
         System.out.println("1 - Inserir novo filme");
         System.out.println("2 - Listar filmes");
        System.out.println("3 - Atualizar filme");
        System.out.println("4 - Excluir filme");
         System.out.println("5 - Sair");
int opcao = scanner.nextInt();
	switch(opcao) {
	case 1:
		System.out.println("Digite o ID do filme:");
	    int id = scanner.nextInt();
	    scanner.nextLine();
	    System.out.println("Digite o nome do filme:");
	    String nome = scanner.nextLine();
	    System.out.println("Digite a categoria do filme:");
	    String categoria = scanner.nextLine();
	    System.out.println("Digite a classificação do filme:");
	    int classificacao = scanner.nextInt();
	    scanner.nextLine(); 

	    Filme filme = new Filme(id, nome, categoria, classificacao);
	    dao.inserirFilme(filme);
        break;
	case 2:
		Filme [] filmes= dao.getFilme();
		for(int i= 0;i<filmes.length;i++) {
			System.out.println("Id: " + filmes[i].getId());
			System.out.println("Nome: " + filmes[i].getNome());
			System.out.println("Categoria: " + filmes[i].getCategoria());
			System.out.println("Classificação: " + filmes[i].getClassificacao());
			System.out.print("\n");
		}
		break;
	case 3:
		System.out.println("Digite o ID do filme:");
	    int id1 = scanner.nextInt();
	    scanner.nextLine();
	    System.out.println("Digite o nome do filme:");
	    String nome1 = scanner.nextLine();
	    System.out.println("Digite a categoria do filme:");
	    String categoria1 = scanner.nextLine();
	    System.out.println("Digite a classificação do filme:");
	    int classificacao1 = scanner.nextInt();
	    scanner.nextLine();
	    Filme filme1 = new Filme(id1, nome1, categoria1, classificacao1);
	    dao.atualizarFilme(filme1);
		break;
	case 4:
		System.out.println("Digite o ID do filme:");
	    int id2 = scanner.nextInt();
	    scanner.nextLine();
	    dao.excluirFilme(id2);
	    break;
	    default:
	    	System.out.println("Saida.");
	    	break;
	}
	dao.close();
	System.out.println("Sucesso!!");
	}

}
