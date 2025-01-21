package tst;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.IRPF;

@RunWith(Parameterized.class)
public class TesteBaseCalculo {

    private IRPF irpf;
    private String[] rendimentos;
    private boolean[] rendimentosTributaveis;
    private float[] valoresRendimentos;
    private String[] dependentes;
    private String[] tiposDependentes;
    private float contribuicaoPrevidenciaria;
    private float pensaoAlimenticia;
    private double baseCalculo;

    public TesteBaseCalculo(String[] rendimentos, boolean[] rendimentosTributaveis, float[] valoresRendimentos,
            String[] dependentes, String[] tiposDependentes, float contribuicaoPrevidenciaria, float pensaoAlimenticia, double baseCalculo) {
        this.rendimentos = rendimentos;
        this.rendimentosTributaveis = rendimentosTributaveis;
        this.valoresRendimentos = valoresRendimentos;
        this.dependentes = dependentes;
        this.tiposDependentes = tiposDependentes;
        this.contribuicaoPrevidenciaria = contribuicaoPrevidenciaria;
        this.pensaoAlimenticia = pensaoAlimenticia;
        this.baseCalculo = baseCalculo;
    }

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> getParameters() {
        String[][] nomesRendimentos = {
            {"Salário", "Aluguel", "Bolsa de valores"},
            {"RendimentoX"},
            {"RendimentoZ", "RendimentoW"},
            {"RendimentoV"}
        };
        boolean[][] rendimentosTributaveis = {
            {true, true, true},
            {true},
            {false, false},
            {true}
        };
        float[][] valorRendimentos = {
            {8000.0f, 2000.0f, 1500.0f},
            {1.0f},
            {-1000.0f},
            {10500.0f},
        };
        String[][] nomesDependentes = {
            {"Pedro", "Marcelo", "Henrique"},
            {"João"},
            {"Mateus"},
            {"Walace"}
        };
        String[][] parentescosDependentes = {
            {"Filho", "Neto", "Sobrinho"},
            {"Sobrinho"},
            {"Filho"},
            {"Pai"},
        };
        float[] contribPrevidenciaria = {
            1000.0f, 0.0f, -500.0f, 2540.0f
        };
        float[] pensoesAlim = {
            500.0f, 0.0f, -200.0f, 2500.0f
        };
        float[] basesCalculo = {
            5500.0f, 1.0f, 0.0f, 7770.41f
        };

        return Arrays.asList(new Object[][]{
            {
                nomesRendimentos[0],
                rendimentosTributaveis[0],
                valorRendimentos[0],
                nomesDependentes[0],
                parentescosDependentes[0],
                contribPrevidenciaria[0],
                pensoesAlim[0],
                basesCalculo[0]
            },
            {
                nomesRendimentos[1],
                rendimentosTributaveis[1],
                valorRendimentos[1],
                nomesDependentes[1],
                parentescosDependentes[1],
                contribPrevidenciaria[1],
                pensoesAlim[1],
                basesCalculo[1]
            },
            {
                nomesRendimentos[2],
                rendimentosTributaveis[2],
                valorRendimentos[2],
                nomesDependentes[2],
                parentescosDependentes[2],
                contribPrevidenciaria[2],
                pensoesAlim[2],
                basesCalculo[2]
            },
            {
                nomesRendimentos[3],
                rendimentosTributaveis[3],
                valorRendimentos[3],
                nomesDependentes[3],
                parentescosDependentes[3],
                contribPrevidenciaria[3],
                pensoesAlim[3],
                basesCalculo[3]
            },
        });
    }

    @Before
    public void setup() {
        irpf = new IRPF();
        for (int i = 0; i < rendimentos.length; i++) {
            irpf.criarRendimento(rendimentos[i], rendimentosTributaveis[i], valoresRendimentos[i]);
        }
    }

    @Test
    public void testCalculoBaseCalculo() {


        for (int i = 0; i < dependentes.length; i++) {
            irpf.cadastrarDependente(dependentes[i], tiposDependentes[i]);
        }

        irpf.cadastrarContribuicaoPrevidenciaria(contribuicaoPrevidenciaria);
        irpf.cadastrarPensaoAlimenticia(dependentes[0], pensaoAlimenticia);
        
        System.out.println("Base calculada pelo método: " + irpf.getBaseCalculo());
        System.out.println("Valor esperado: " + baseCalculo);

        assertEquals(baseCalculo, irpf.getBaseCalculo(), 0.01);
    }
}
