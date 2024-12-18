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
public class TesteBaseCalculoParametrizado {

    private IRPF irpf;
    private String[] rendimentos;
    private String[] tiposRendimentos;
    private float[] valoresRendimentos;
    private String[] dependentes;
    private String[] tiposDependentes;
    private float contribuicaoPrevidenciaria;
    private float pensaoAlimenticia;
    private double baseCalculo;

    public TesteBaseCalculoParametrizado(String[] rendimentos, String[] tiposRendimentos, float[] valoresRendimentos,
            String[] dependentes, String[] tiposDependentes, float contribuicaoPrevidenciaria, float pensaoAlimenticia, double baseCalculo) {
        this.rendimentos = rendimentos;
        this.tiposRendimentos = tiposRendimentos;
        this.valoresRendimentos = valoresRendimentos;
        this.dependentes = dependentes;
        this.tiposDependentes = tiposDependentes;
        this.contribuicaoPrevidenciaria = contribuicaoPrevidenciaria;
        this.pensaoAlimenticia = pensaoAlimenticia;
        this.baseCalculo = baseCalculo;
    }

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
            {new String[]{"Salario", "Aluguel", "Bolsa"}, new String[]{"true", "true", "false"}, new float[]{8000, 2000, 1500}, new String[]{"Pedro"}, new String[]{"Filho"}, 1500, 1500, 6810.41},
            {new String[]{"Salario", "Aluguel"}, new String[]{"true", "true"}, new float[]{5000, 2000}, new String[]{"Ana"}, new String[]{"Filha"}, 1000, 1000, 4810.41},
        });
    }

    @Test
    public void testCalculoBaseCalculo() {
        for (int i = 0; i < rendimentos.length; i++) {
            irpf.criarRendimento(rendimentos[i], Boolean.parseBoolean(tiposRendimentos[i]), valoresRendimentos[i]);
        }


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
