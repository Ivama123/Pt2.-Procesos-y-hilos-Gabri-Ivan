import java.util.Random;
import java.util.Scanner;
class BuscadorThread extends Thread {
    private int[] vector;
    private int start;
    private int end;
    private int numHilos;
    private int target;

    public BuscadorThread(int[] vector, int start, int end, int target,int numHilos) {
        this.vector = vector;
        this.start = start;
        this.end = end;
        this.target = target;
        this.numHilos = numHilos;
    }

    @Override
    public void run() {
    
        for (int j = start; j <= end; j++) {
            if (vector[j] == this.target) {
            	
            		System.out.println("Hilo " + Thread.currentThread().getName() + " ha encontrado el valor en la posición: " + j);
            	
            	
                return;
            }
        }
    }
}