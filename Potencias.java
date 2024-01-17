import java.util.Scanner;

class Potencias extends Thread {
    private int exponent;
    private long resultado;

    public Potencias(int exponent) {
        this.exponent = exponent;
        this.resultado = -1; // Inicializar a un valor que indique que aún no se ha calculado la potencia de 2
    }

    @Override
    public void run() {
    	resultado = calcular(exponent);
    }

    public long getResult() {
        return resultado;
    }

    long calcular(long exponent) {
        return (long) Math.pow(2, exponent);
    }
}