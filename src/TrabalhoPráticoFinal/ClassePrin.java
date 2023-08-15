package TrabalhoPráticoFinal;
//Bibliotecas para utilizar o leitor de arquivo ja implementado
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/*
 * Trabalho Prático Final 
 * 
 * Alunos:
 * 
 * Igor Gomes Silva Rattes
 * Julia Guerra Ferreira
 * 
 * TURMA: 3110101
 *  
 */

public class ClassePrin {

	public static void main(String[] args) throws Exception{
		//Fazendo o programa abrir de forma que consiga ler arquivos txt externos
		//Declarando Variáveis
		//Mostrando o endereço do arquivo txt
		String CaminhoArquivo = "C:/Eclipse/ws-eclipse/TrabalhoFinalAEDsII/src/TrabalhoPráticoFinal/ArquivoTrabalhoFinal.txt";
		ArvoreAVL Avl = new ArvoreAVL();
		BufferedReader Leitor = null;
		//Bloco para executar as instruções
		try {
			//Leitor do arquivo
			Leitor = new BufferedReader (new FileReader(CaminhoArquivo));
			String linha;
			while((linha = Leitor.readLine() )!= null) {// Condicao para ler todo o arquivo
				linha=linha.replaceAll("[^a-zA-Z0-9\\sÀ-ú]", ""); //Despreza Caracteres especiais mantendo acentos, espaços
				linha = linha.toLowerCase(); //Converte as letras para minusculo
				//Condicao para inserir as palavras a cada espaco
                String[] PalavrasSeparadas = linha.split(" "); // Divide a linha em palavras com base no espaço
                //Percorrer cada elemento do array PalavrasSeparadas e processa cada palavra individualmente, atraves da leitura dos espacos
                for (String palavra : PalavrasSeparadas) {
                	palavra.replace(" ", ""); //Despreza os espacos, apos a insercao
                	Palavra P = new Palavra(palavra);
                    Avl.Inserir(P); // Insere as palavras na árvore
                }
			}
			Avl.Imprimir(); //Imprime a arvore
			ListaDupla lista = new ListaDupla();//Criando a lista dupla
			Avl.AddLista(lista);//Adiciona as palavras da arvore na lista
			System.out.println(" Imprimindo a Lista \n ");
			lista.mostrarLista();//Mostra a Lista dupla
		}catch (FileNotFoundException e) {//Caso o arquivo não seja encontrado
			e.printStackTrace();//Metodo usado para depurar e rastrear o fluxo de execucao do programa quando uma excecao é lançada
		}catch (IOException e) {//Tratamento para a exececao caso haja algum erro durante a leitura ou gravacao de dados de entrada ou saida
			e.printStackTrace();
		//Fechando o Arquivo
		}finally { // Bloco que sera executado independe de ser encontrada alguma excecao finalizando o 1º try
			if(Leitor != null) {
				try {
					Leitor.close(); //Fechando o Leitor de Arquivos
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

