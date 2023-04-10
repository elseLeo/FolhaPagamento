public class CalculoFolha  {
    public Double aliqINSS;
    public Double aliqIRPF;


    public void calcularINSS(Double salarioBruto) {
        if (salarioBruto < 1302) {
            aliqINSS = salarioBruto * 0.075;
        }
        if (salarioBruto > 1302.01 && salarioBruto < 2571.29) {
            aliqINSS = salarioBruto * 0.09;
        }
        if (salarioBruto > 2571.30 && salarioBruto < 3856.94) {
            aliqINSS = salarioBruto * 0.12;
        }
        if (salarioBruto > 3856.95) {
            aliqINSS = salarioBruto * 0.14;
        } else {
            System.out.println("Informe um valor válido");
        }
    }
    public void calcularIRPF(Double salarioBruto) {
        if (salarioBruto < 1903.98) {
            aliqIRPF = 0.00;
        }
        if (salarioBruto > 1903.99 && salarioBruto < 2826.65) {
            aliqIRPF = salarioBruto * 0.075;
        }
        if (salarioBruto > 2826.66 && salarioBruto < 3751.05) {
            aliqIRPF = salarioBruto * 0.15;
        }
        if (salarioBruto > 3751.06 && salarioBruto < 4664.68) {
            aliqIRPF = salarioBruto * 0.225;
        }
        if (salarioBruto > 4664.69) {
            aliqIRPF = salarioBruto * 0.275;
        } else {
            System.out.println("Informe um valor válido");
        }
    }
}


