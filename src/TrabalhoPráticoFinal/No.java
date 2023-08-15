package TrabalhoPráticoFinal;

public class No {
	  // Declarando as variaveis
    Palavra palavra;
    No esq;
    No dir;
    int altura;

    // Metodo construtor padrao
    public No(Palavra p) {
        this.palavra = p;
        this.esq = null;
        this.dir = null;
        this.altura = 1;
    }	
}
