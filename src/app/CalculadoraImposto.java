package app;

public class CalculadoraImposto {
  private float base, imposto;

  public CalculadoraImposto(IRPF irpf) {
    base = irpf.getBaseCalculo();
    imposto = 0;
  }

  protected float calculaImposto() {
    if (base > 2259.20) {
			if (base > 2826.65) {
				imposto += 42.56;

			}else{
				imposto += (base - 2259.20) * 0.075;
			}
		}
		if (base > 2826.66) {
			if (base > 3751.05) {
				imposto += 138.66;

			}else{
				imposto += (base - 2826.66) * 0.15;
			}
		}
		if (base > 3751.06) {
			if (base > 4664.68) {
				imposto += 205.57;
			}else{
				imposto += (base - 3751.06) * 0.225;
			}
		}
		if (base > 4664.68) {
			
			imposto += (base - 4664.68) * 0.275;
		}


		return imposto;
  }
}
