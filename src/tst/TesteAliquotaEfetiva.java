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

/**
 *
 * @author moura
 */
@RunWith(Parameterized.class)
public class TesteAliquotaEfetiva {
    IRPF irpf;
    String[] nomesRendimentos;
    boolean[] rendimentosTributaveis;
    float[] valoresRendimentos;
    float[] contribuicoesPrevidenciarias;
    String[] nomesDependentes;
    String[] parentescosDependentes;
    float[] valoresPensaoAlim;
    float aliquota;

    public TesteAliquotaEfetiva(String[] nomesRendimentos, boolean[] rendimentosTributaveis, float[] valorRendimentos,
        float[] contribuicoesPrevidenciarias, String[] nomesDependestes, String[] parentescosDependentes,
        float[] valoresPensaoAlim, float aliquota) {
        
        this.nomesRendimentos = nomesRendimentos;
        this.rendimentosTributaveis = rendimentosTributaveis;
        this.valoresRendimentos = valorRendimentos;
        this.contribuicoesPrevidenciarias = contribuicoesPrevidenciarias;
        this.nomesDependentes = nomesDependestes;
        this.parentescosDependentes = parentescosDependentes;
        this.valoresPensaoAlim = valoresPensaoAlim;
        this.aliquota = aliquota;
    }
    
    @Parameters
    public static Collection<Object[]> getParameters() {
        String[][] nomesRendimentos = {
            {"Salário", "Aluguel", "Bolsa de estudos"},
            {"Salário"},
            {"Bolsa de estudos", "Salário"},
            {"Salário"}
        };
        boolean[][] rendimentosTributaveis = {
            {IRPF.TRIBUTAVEL, IRPF.TRIBUTAVEL, IRPF.NAOTRIBUTAVEL},
            {IRPF.TRIBUTAVEL},
            {IRPF.NAOTRIBUTAVEL, IRPF.TRIBUTAVEL},
            {IRPF.TRIBUTAVEL}
        };
        float[][] valoresRendimentos = {
            {8000.0f, 2000.0f, 1500.0f},
            {2250.0f},
            {1250.50f, 10000.0f},
            {22847.76f}
        };
        float[][] contribuicoesPrevidenciarias = {
            {500.0f, 1000.0f},
            {250.0f},
            {},
            {}
        };
        String[][] nomesDependentes = {
            {"João"},
            {},
            {"Cassio"},
            {}
        };
        String[][] parentescosDependentes = {
            {"Filho"},
            {},
            {"Filho"},
            {}
        };
        float[][] valoresPensaoAlim = {
            {1500.0f},
            {},
            {2000.0f},
            {}
        };
        float[] aliquotas = {0.097f, 0.0f, 0.12f, 0.23f};

        return Arrays.asList(new Object[][] {
            {
                nomesRendimentos[0],
                rendimentosTributaveis[0],
                valoresRendimentos[0],
                contribuicoesPrevidenciarias[0],
                nomesDependentes[0],
                parentescosDependentes[0],
                valoresPensaoAlim[0],
                aliquotas[0]
            },
            {
                nomesRendimentos[1],
                rendimentosTributaveis[1],
                valoresRendimentos[1],
                contribuicoesPrevidenciarias[1],
                nomesDependentes[1],
                parentescosDependentes[1],
                valoresPensaoAlim[1],
                aliquotas[1]
            },
            {
                nomesRendimentos[2],
                rendimentosTributaveis[2],
                valoresRendimentos[2],
                contribuicoesPrevidenciarias[2],
                nomesDependentes[2],
                parentescosDependentes[2],
                valoresPensaoAlim[2],
                aliquotas[2]
            },
            {
                nomesRendimentos[3],
                rendimentosTributaveis[3],
                valoresRendimentos[3],
                contribuicoesPrevidenciarias[3],
                nomesDependentes[3],
                parentescosDependentes[3],
                valoresPensaoAlim[3],
                aliquotas[3]
            }
        });
    }

    @Before
    public void setup() {
		irpf= new IRPF();
        for (int i = 0; i < valoresRendimentos.length; i++) {
            irpf.criarRendimento(nomesRendimentos[i], rendimentosTributaveis[i], valoresRendimentos[i]);
        }
        for (int i = 0; i < nomesDependentes.length; i++) {
            irpf.cadastrarDependente(nomesDependentes[i], parentescosDependentes[i]);
        }
        for (int i = 0; i < nomesDependentes.length; i++) {
            irpf.cadastrarPensaoAlimenticia(nomesDependentes[i], valoresPensaoAlim[i]);
        }
        for (int i = 0; i < contribuicoesPrevidenciarias.length; i++) {
            irpf.cadastrarContribuicaoPrevidenciaria(contribuicoesPrevidenciarias[i]);
        }
	}
    
    @Test
    public void testeAliquotaEfetiva() {
        assertEquals(aliquota, irpf.getAliquotaEfetiva(), 0.01f);
    }

}