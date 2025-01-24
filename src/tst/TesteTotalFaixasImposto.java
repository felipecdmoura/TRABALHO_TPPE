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

    private float sal, alu, bol, prev, pen;
    private double res;

    private IRPF irpf;

    public TesteTotalFaixasImposto(float salario, float aluguel, float bolsa, float previdencia, float pensao, double result){
        this.sal = salario;
        this.alu = aluguel;
        this.bol = bolsa;
        this.prev = previdencia;
        this.pen = pensao;
        this.res = result;

    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parametros = new Object[][] {
            {8000, 2000, 1500, 1500, 1500,  976.8},
            {38000, 12000, 1500, 3000, 5000, 10601.8},
        };

        return Arrays.asList(parametros);
    }

    @Test
    public void testImpostoTotal() {
        irpf= new IRPF();
        irpf.criarRendimento("Salario", true, sal);
        irpf.criarRendimento("Aluguel", true,  alu);
        irpf.criarRendimento("Bolsa", false,  bol);

        irpf.cadastrarDependente("Pedro", "Filho");
        irpf.cadastrarContribuicaoPrevidenciaria(prev);
        irpf.cadastrarPensaoAlimenticia("Pedro",  pen);
        assertEquals(res, irpf.getTotalImposto(), 0.1);
    }

}