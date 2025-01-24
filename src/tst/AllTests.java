package tst;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
				TesteBaseCalculo.class, // ok
				TesteCadastrarDependente.class, //ok 
				TesteCadastroContribuicaoPrevidenciaria.class, // ok  
				TesteCadastroOutrasDeducoes.class, // ok
				TesteCadastroPensaoAlimenticia.class, // ok
				TesteAliquotaEfetiva.class, // nOk
				TesteCalculosDeducoesDependentes.class, // ok 
				TesteRendimentos.class, // ok
				TesteTotalFaixasImposto.class, // Teste faixas ??
			})
public class AllTests {

}
