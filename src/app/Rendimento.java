package app;
import java.util.ArrayList;
import java.util.List;

public class Rendimento {
    private List<String> nome;
    private List<Boolean> tributavel;
    private List<Float> valor;
    private int numRendimentos;
    private float totalRendimentos;

    public Rendimento(){
        this.nome = new ArrayList<String>();
        this.tributavel = new ArrayList<Boolean>();
        this.valor = new ArrayList<Float>();
        numRendimentos = 0;
        totalRendimentos = 0;
    }

    /**
	 * Cadastra um rendimento na base do contribuinte, informando o nome do 
	 * rendimento, seu valor e se ele é tributável ou não. 
	 * @param nome nome do rendimento a ser cadastrado
	 * @param tributavel true caso seja tributável, false caso contrário
	 * @param valor valor do rendimento a ser cadastrado
	 */
	public void criarRendimento(String nome, boolean tributavel, float valor) {

		this.nome.add(nome);
        this.tributavel.add(tributavel);
        this.valor.add(valor);
		
		this.numRendimentos += 1;
		this.totalRendimentos += valor;
		
	}

    public int getNumRendimentos() {
		return this.numRendimentos;
	}

    public float getTotalRendimentos() {
		return this.totalRendimentos;
	}

    public float getTotalRendimentosTributaveis() {
        float totalRendimentosTributaveis = 0;
        for (int i = 0; i < this.valor.size(); i++) {
            if (this.tributavel.get(i)) {
                totalRendimentosTributaveis += this.valor.get(i);
            }
        }
        return totalRendimentosTributaveis;
    }
}


