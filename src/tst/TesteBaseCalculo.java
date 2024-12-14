
package tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import app.IRPF;


public class TesteBaseCalculo {
    IRPF irpf;

    @Before
    public void setup() {
		irpf= new IRPF();
	}
    
    @Test
    public void TesteCalcularBaseCalculo() {
        irpf.criarRendimento("Salario", true, 8000);
        irpf.criarRendimento("Aluguel", true, 2000);
        irpf.criarRendimento("Bolsa", false, 1500);

        irpf.cadastrarDependente("Pedro", "Filho");
        irpf.cadastrarContribuicaoPrevidenciaria(1500);
        irpf.cadastrarPensaoAlimenticia("Pedro", 1500);
        assertEquals(6810.41, irpf.getBaseCalculo(), 0.01);
    }

}
