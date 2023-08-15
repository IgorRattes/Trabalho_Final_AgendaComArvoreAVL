package TrabalhoPráticoFinal;

public class ArvoreAVL {
	//Declaracao de variavel raiz
	No Raiz;
	//Construtor vazio
	public ArvoreAVL() {
		Raiz = null;
	}						
	// Metodo para obter a altura de um no
	int altura(No n) {
		if (n == null) {
			return -1;
		}
		return n.altura;
	}
	//Rotacao simples para a esquerda
	No RotaçãoEsq(No a) {
		No b = a.dir;
		a.dir = b.esq;
		b.esq = a;
		//Aqui nos calculamos a altura no no a esquerda e do no a direita 
		//Função Math.max retorna o maior valor entre dois parametros
		a.altura = Math.max(altura(a.esq), altura(a.dir)) + 1;
		b.altura = Math.max(altura(b.dir), a.altura) + 1;
		return b;
	}
	//Rotacao simples para a direita
	No RotaçãoDir(No a) {
		No b = a.esq;
		a.esq = b.dir;
		b.dir = a;
		a.altura = Math.max(altura(a.esq), altura(a.dir)) + 1;
		b.altura = Math.max(altura(b.esq), a.altura) + 1;
		return b;
	}
	//Rotacao dupla esquerda-direita
	No RotDupEsqDir(No a) {
		a.esq = RotaçãoEsq(a.esq);
		return RotaçãoDir(a);
	}
	//Rotacao dupla direta-esquerda
	No RotDupDirEsq(No a) {
		a.dir = RotaçãoDir(a.dir);
		return RotaçãoEsq(a);
	    }			
	//Inserir balanceando (Metodo "simples não recursivo"
	void Inserir (Palavra x)throws Exception{
		Raiz = Inserir(x, Raiz);
	}
	//Metodo "Recursivo" do Inserir
	No Inserir(Palavra x, No i) throws Exception {
		if (i == null) {//Se o no for null ira criar um novo no com a palavra x
			i = new No(x);
		} 
		//Se nao for null ira entrar na próxima condição	
		else if (x.getPalavra().compareTo(i.palavra.getPalavra()) < 0) {//Se x for menor de forma lexicografica que a palavra no nó atual
			i.esq = Inserir(x, i.esq);//ira inserir na sub arvore a esquerda e verifica o balanceamento
			if (altura(i.esq) - altura(i.dir) == 2) {//Caso esteja desbalanceado ira calcular o fator de balanceamento
				if (x.getPalavra().compareTo(i.esq.palavra.getPalavra()) < 0) {
					i = RotaçãoDir(i);//realiza uma rotaçao simples a direita
				} 
				else {
					i = RotDupEsqDir(i);//Realiza uma rotacao dupla esquerda direita
				}
			}
		} 
		else if (x.getPalavra().compareTo(i.palavra.getPalavra()) > 0) {//Se x for maior de forma lexicografica que a palavra no nó atual
			i.dir = Inserir(x, i.dir);//ira inserir na sub arvore a direita
			if (altura(i.dir) - altura(i.esq) == 2) {//Caso esteja desbalanceado ira calcular o fator de balanceamento
				if (x.getPalavra().compareTo(i.dir.palavra.getPalavra()) > 0) {
					i = RotaçãoEsq(i);//realiza uma rotacao simples a esquerda
				} 
				else {
					i = RotDupDirEsq(i);//realiza uma rotacao dupla direita esquerda
				}
			}
		} 
		else { // Caso a palavra ja esteja na arvore, incrementa o contador
			if(Verificar(x.getPalavra()) == true)
				i.palavra.cont++;
		}
		//Atualizando a altura do no
		i.altura = Math.max(altura(i.esq), altura(i.dir)) + 1;
		return i;
	}		
	// Metodo para verificar se uma palavra ja esta inserida na arvore "Metodo não Recursivo"
	boolean Verificar(String palavra) {
		return Verificar(palavra, Raiz);
	}
	// Metodo para verificar se uma palavra ja esta inserida na arvore "Metodo Recursivo"
	boolean Verificar(String palavra, No i) {
		if (i == null) {
			return false;
		}
		//Metodo de Comparação para verificar se as palavras são iguais
		int comparacao = palavra.compareTo(i.palavra.getPalavra());
		//Implementacao das acões de acordo com o resultado da comparacao
		if (comparacao == 0) {
			// A palavra foi encontrada na sub-arvore analisada
			return true;
		} 
		else if (comparacao < 0) {
			// Verificar se a palavra está na sub-arvore da esquerda
			return Verificar(palavra, i.esq);
		} 
		else {
			// Verifica de a palavra está na sub-arvore da direita
			return Verificar(palavra, i.dir);
		}
	}		
	//Metodo Caminhar Central imprimindo na tela
	void Caminhar_Central(No i) {
		if (i != null) {
			//descendo na extrema esquerda para voltar mostrando os nós
			Caminhar_Central(i.esq);
			System.out.println("PALAVRA: "+i.palavra + " - REPETE: " + i.palavra.cont + " Vezes " );
			Caminhar_Central(i.dir);
		}
	}		
	//Metodo Remover mantendo ordenação "Metodo não Recursivo"
	void Remover (Palavra x) throws Exception{
		Raiz = Remover(x, Raiz);
	}
	//Metodo Remover mantendo ordenação "Metodo Recursivo"
	No Remover(Palavra x, No i) throws Exception {
		if (i == null) {//Se a arvore estiver vazia
			throw new Exception("!NÃO Á NADA A REMOVER!");
		} 
		//	Caso nao seja vazio, ira comparar 
		else if (x.getPalavra().compareTo(i.palavra.getPalavra()) < 0) {//Se x for menor de forma lexicografica a palavra no no atual
			i.esq = Remover(x, i.esq);//Remove o no na sub arvore a esquerda
			if (altura(i.dir) - altura(i.esq) == 2) {//Caso esteja desbalanceado ira calcular o fator de balanceamento
				if (altura(i.dir.esq) > altura(i.dir.dir)) {
					i = RotDupDirEsq(i);//Realiza uma rotacao dupla direita esquerda
				} 
				else {
					i = RotaçãoEsq(i);//Realiza uma rotacao simples pra esquerda
				}
			}
		} 
		else if (x.getPalavra().compareTo(i.palavra.getPalavra()) > 0) {//Se x for maior de forma lexicografica a palavra no no atual
			i.dir = Remover(x, i.dir);//Remove o no na sub arvore a direita
			if (altura(i.esq) - altura(i.dir) == 2) {//Caso esteja desbalanceado ira calcular o fator de balanceamento
				if (altura(i.esq.dir) > altura(i.esq.esq)) {
					i = RotDupEsqDir(i);//Realiza uma rotacao dupla esquerda direita
				} 
				else {
					i = RotaçãoDir(i);//Realiza uma rotacao simples a direita
				}
			}
		} 
		//Se o no x for encontrado
		else if (i.dir == null) {
			i = i.esq;//Ira remover o no atual se ele nao tiver filhos a direita
		} 
		else if (i.esq == null) {
	            i = i.dir;//Ira remover o no atual se ele nao tiver filhos a esquerda
		} 
		else {
			//Substitui a palavra no no atual pela maior palavra da sub arvore a esquerda
			i.esq = MaiorEsq(i, i.esq);
			if (altura(i.dir) - altura(i.esq) == 2) {
				if (altura(i.dir.esq) > altura(i.dir.dir)) {
					i = RotDupDirEsq(i);//Realiza uma rotacao dupla direita esquerda
				} 
				else {
					i = RotaçãoEsq(i);//Realiza uma rotacao simples a esquerda
				}
			}
		}
		//Atualiza a altura do no
		if (i != null) {
			i.altura = Math.max(altura(i.esq), altura(i.dir)) + 1;
		}
		return i;
	}
	//Metodo para retornar o maior no a Esquerda
	No MaiorEsq(No i, No j) {
		//Caso nao haja mais filhos na direita
		if (j.dir == null) { 
			i.palavra = j.palavra; j = j.esq;
		} 
		//Caso haja mais filhos na direita, verifica novamente qual é o maior
		else { 
			j.dir = MaiorEsq(i, j.dir);
		} 
		return j;
	}
	//Metodo para adicionar um no a lista informada "Metodo não Recursivo"
	void AddLista(ListaDupla lista) {
	    AddLista(Raiz, lista);
	}
	//Metodo para adicionar um no a lista informada "Metodo Recursivo"
	void AddLista(No no, ListaDupla lista) {
	    if (no != null) {
	        AddLista(no.dir, lista); // Percorre o nó da direita primeiro
	        // Encontra a posição correta para inserir a palavra na lista ordenada
	        No atualOrdenado = lista.primeiro;
	        while (atualOrdenado != null && atualOrdenado.palavra.cont >= no.palavra.cont) {
	            atualOrdenado = atualOrdenado.dir;
	        }

	        // Insere a palavra na posição correta da lista ordenada
	        if (atualOrdenado == null) {
	            lista.inserirFim(no.palavra); // Insere no fim se a lista estiver vazia ou se o contador for menor que todos os elementos
	        } else {
	            lista.inserirpre(atualOrdenado, no.palavra); // Insere antes do nó encontrado
	        }
	        AddLista(no.esq, lista); // Percorre o nó da esquerda em seguida
	    }
	}
	//Metodo Imprimir
	void Imprimir () {
		System.out.println(" Imprimindo - Arvore AVL: ");
		System.out.println(""); //Espaco
		Caminhar_Central(Raiz);
		System.out.println(""); //Espaco
	}
}
