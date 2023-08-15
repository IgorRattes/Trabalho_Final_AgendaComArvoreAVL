package TrabalhoPráticoFinal;

public class Palavra {
	 // Declarando as variaveis
    String nome;
    Integer cont;

    // Metodo construtor padrao
    public Palavra(String nome) {
        this.nome = nome;
        this.cont = 1;//Contador para saber quantas vezes ela se repete
    }
    //Metodo get
	public String getPalavra() {
		return nome;
	}
	//Metodo para fornecer uma representacao em string, de um objeto do tipo palavra
	@Override
	public String toString () {
		return nome;
	}
}
