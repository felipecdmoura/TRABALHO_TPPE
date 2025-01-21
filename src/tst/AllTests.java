package tst;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
				TesteBaseCalculo.class,
				TesteCadastrarDependente.class, 
				TesteCadastroContribuicaoPrevidenciaria.class, 
				TesteCadastroOutrasDeducoes.class,
				TesteCadastroPensaoAlimenticia.class,
				TesteCalculoImposto.class,
				TesteCalculosDeducoesDependentes.class, 
				TesteRendimentos.class,
				TesteCalculosDeducoesDependentes.class, 
			})
public class AllTests {

}
