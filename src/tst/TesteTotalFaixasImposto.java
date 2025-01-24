package tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.IRPF;

@RunWith(Parameterized.class)
public class TesteTotalFaixasImposto {

    private float baseCalculo, valorEsperado;


    private IRPF irpf;

    public TesteTotalFaixasImposto(float baseCalculo, float valorEsperado) {
        this.baseCalculo = baseCalculo;
        this.valorEsperado = valorEsperado;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        float[] basesCalculo = {
            2259.20f, 2800.0f, 2826.66f, 5585.8f
        };

        float[] valoresEsperados = {
            0.0f, 40.56f, 42.56f, 640.09f
        };

        return Arrays.asList(new Object[][] {
            {basesCalculo[0], valoresEsperados[0]},
            {basesCalculo[1], valoresEsperados[1]},
            {basesCalculo[2], valoresEsperados[2]},
            {basesCalculo[3], valoresEsperados[3]}
        });
    }

    @Before
    public void setup() {
        this.irpf = new IRPF();
        this.irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, this.baseCalculo);
    }

    @Test
    public void testImpostoFaixa() {
        assertEquals(this.valorEsperado, this.irpf.getTotalImposto(), 0.01f);
    }

}