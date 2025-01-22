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
    private float[] valoresRendimentos;
    private boolean[] rendimentosTributaveis;
    private String[] nomesDeducoes;
    private float[] valoresDeducoes;
    private String[] dependentes;
    private String[] tiposDependentes;
    private float[] valoresPensaoAlim;
    private float[] contribuicoesPrevidenciaria;
    private double baseCalculo;

    public TesteBaseCalculo(String[] rendimentos, float[] valoresRendimentos, boolean[] rendimentosTributaveis,
        String[] nomesDeducoes, float[] valoresDeducoes, String[] dependentes,
        String[] tiposDependentes, float[] valoresPensaoAlim, float[] contribuicoesPrevidenciaria,
        float baseCalculo) {

        this.rendimentos = rendimentos;
        this.valoresRendimentos = valoresRendimentos;
        this.rendimentosTributaveis = rendimentosTributaveis;
        this.nomesDeducoes = nomesDeducoes;
        this.valoresDeducoes = valoresDeducoes;
        this.dependentes = dependentes;
        this.tiposDependentes = tiposDependentes;
        this.valoresPensaoAlim = valoresPensaoAlim;
        this.contribuicoesPrevidenciaria = contribuicoesPrevidenciaria;
        this.baseCalculo = baseCalculo;

    }

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> getParameters() {
        String[][] nomesRendimentos = {
            {"Salário", "Aluguel"},
            {"Salário"},
            {"Bolsa de estudos"},
            {"Salário", "Bolsa de estudos"},
            {"Salário"}
        };
        float[][] valorRendimentos = {
            {5000.0f, 2000.0f},
            {8000.0f},
            {3000.0f},
            {6000.0f, 3000.0f},
            {10000.0f}
        };
        boolean[][] rendimentosTributaveis = {
            {true, true},
            {true},
            {false},
            {true, false},
            {true}
        };
        String[][] nomesDeducoes = {
            {},
            {"Previdência"},
            {},
            {},
            {"Saúde"}
        };
        float[][] valoresDeducoes = {
            {},
            {1500.0f},
            {},
            {},
            {2000.0f}
        };
        String[][] nomesDependentes = {
            {},
            {"Jhosefer"},
            {},
            {},
            {"Pedro"}
        };
        String[][] parentescosDependentes = {
            {},
            {"Filho"},
            {},
            {},
            {"Filho"}
        };
        float[][] valoresPensaoAlim = {
            {},
            {500.0f},
            {},
            {},
            {1000.0f}
        };
        float[][] contribPrevidenciaria = {
            {},
            {500.0f},
            {},
            {},
            {1500.0f}
        };
        float[] basesCalculo = {
            7000.0f, 6810.41f, 0.0f, 6000.0f, 5650.41f
        };

        return Arrays.asList(new Object[][]{
            {
                nomesRendimentos[0],
                valorRendimentos[0],
                rendimentosTributaveis[0],
                nomesDeducoes[0],
                valoresDeducoes[0],
                nomesDependentes[0],
                parentescosDependentes[0],
                valoresPensaoAlim[0],
                contribPrevidenciaria[0],
                basesCalculo[0]
            },
            {
                nomesRendimentos[1],
                valorRendimentos[1],
                rendimentosTributaveis[1],
                nomesDeducoes[1],
                valoresDeducoes[1],
                nomesDependentes[1],
                parentescosDependentes[1],
                valoresPensaoAlim[1],
                contribPrevidenciaria[1],
                basesCalculo[1]
            },
            {
                nomesRendimentos[2],
                valorRendimentos[2],
                rendimentosTributaveis[2],
                nomesDeducoes[2],
                valoresDeducoes[2],
                nomesDependentes[2],
                parentescosDependentes[2],
                valoresPensaoAlim[2],
                contribPrevidenciaria[2],
                basesCalculo[2]
            },
            {
                nomesRendimentos[3],
                valorRendimentos[3],
                rendimentosTributaveis[3],
                nomesDeducoes[3],
                valoresDeducoes[3],
                nomesDependentes[3],
                parentescosDependentes[3],
                valoresPensaoAlim[3],
                contribPrevidenciaria[3],
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

        for (int i = 0; i < nomesDeducoes.length; i++) {
            irpf.cadastrarDeducaoIntegral(nomesDeducoes[i], valoresDeducoes[i]);
        }

        for (int i = 0; i < dependentes.length; i++) {
            irpf.cadastrarDependente(dependentes[i], tiposDependentes[i]);
        }

        for (int i = 0; i < dependentes.length; i++) {
            irpf.cadastrarPensaoAlimenticia(dependentes[i], valoresPensaoAlim[i]);
        }

        for (int i = 0; i < contribuicoesPrevidenciaria.length; i++) {
            irpf.cadastrarContribuicaoPrevidenciaria(contribuicoesPrevidenciaria[i]);
        }
    }

    @Test
    public void testCalculoBaseCalculo() {
        // System.out.println("Base calculada pelo método: " + irpf.getBaseCalculo());
        // System.out.println("Valor esperado: " + baseCalculo);

        assertEquals(baseCalculo, irpf.getBaseCalculo(), 0.01f);
    }
}
