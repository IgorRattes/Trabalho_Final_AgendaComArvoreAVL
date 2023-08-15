package TrabalhoPráticoFinal;

public class ListaDupla {
	 // Declarando as variaveis
    No primeiro;
    No ultimo;
    //Construtor vazio
    public ListaDupla() {
        this.primeiro = null;
        this.ultimo = null;
    }
    // inserir no inicio da lista
    public void inserirInicio(Palavra palavra) {
        // Criacao de um novo no para insercao da palavra no inicio da lista
        No tmp = new No(palavra);
        // Condicao caso a lista esteja vazia
        if (primeiro == null) {
        	primeiro = tmp;
        	ultimo = tmp;
        } else { // Caso esteja preenchida, faz o devido encadeamento para o temporario virar o 1º termo
            tmp.dir = primeiro;
            primeiro.esq = tmp;
            primeiro = tmp;
        }
    }
    // Inserir no fim da lista
    public void inserirFim(Palavra palavra) {
        // Criacao de um novo no para insercao da palavra no fim da lista
        No tmp = new No(palavra);
        // Condicao caso a lista esteja vazia
        if (ultimo == null) {
        	primeiro = tmp;
        	ultimo = tmp;
        } else { // Caso esteja preenchida, faz o devido encadeamento para o temporario virar o ultimo termo
            tmp.esq = ultimo;
            ultimo.dir = tmp;
            ultimo = tmp;
        }
    }
    //Inserir antes do nó repassado
    void inserirpre(No noExist, Palavra palavra) {
        No Newno = new No(palavra);

        if (noExist == null) {
            // Caso o nó existente seja nulo, o novo nó será inserido como o primeiro elemento da lista
            Newno.dir = primeiro;
            if (primeiro != null) {
                primeiro.esq = Newno;
            }
            primeiro = Newno;
            if (ultimo == null) {
                ultimo = Newno;
            }
        } else {
            // Caso contrário, insere o novo nó antes do nó existente
            Newno.dir = noExist;
            Newno.esq = noExist.esq;
            if (noExist.esq != null) {
                noExist.esq.dir = Newno;
            } else {
                primeiro = Newno;
            }
            noExist.esq = Newno;
        }
    }
    // Remover no inicio da lista
    public void removerInicio() {
        //Caso a lista esteja vazia
        if (primeiro == null) {
            System.out.println("Não é possivel remover sem itens na lista.");
        } else if (primeiro == ultimo) { // Condicao caso haja apenas um no na lista
        	primeiro = null;
        	ultimo = null;
        } else { // Remove o nó no inicio e atualiza as ref.
        	primeiro = primeiro.dir;
        	primeiro.esq = null;
        }
    }
    // Remover no fim da lista
    public void removerFim() {
        //Caso a lista esteja vazia
        if (ultimo == null) {
            System.out.println("Não é possivel remover sem itens na lista.");
        } else if (primeiro == ultimo) { //Condicao caso haja apenas um no na lista
        	primeiro = null;
        	ultimo = null;
        } else { //Remove o no no fim e atualiza as ref.
        	ultimo = ultimo.esq;
        	ultimo.dir = null;
        }
    }
    //Metodo para mostrar a lista
    public void mostrarLista() {
        No atual = primeiro;//Passando a posição do primeiro para o No atual
        while (atual != null) {//Enquanto atual for diferente de null
            System.out.println("PALAVRA: "+atual.palavra + " - REPETE: " + atual.palavra.cont+" Vezes");//Imprime cada no da Lista
            atual = atual.dir;//Passa para o próximo nó da lista dupla
        }
        System.out.println(""); //Espaço
    }
}
