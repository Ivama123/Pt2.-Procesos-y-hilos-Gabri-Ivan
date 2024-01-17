import java.util.Scanner;

public class Potencias2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número hasta el cual desea calcular las potencias de 2: ");
        int numThreads = scanner.nextInt();

        Potencias[] threads = new Potencias[numThreads];

        // Crear hilos para cada potencia de 2
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Potencias(i);
            threads[i].start();
        }

        // Esperar a que todos los hilos terminen y luego imprimir los resultados en orden ascendente
        for (Potencias thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Resultados en orden ascendente de potencias de 2:");
        for (Potencias thread : threads) {
            System.out.println("2^" + thread.getResult() + " = " + thread.calcular( thread.getResult()));
        }

        scanner.close();
    }
}