import java.util.Scanner;
import java.util.concurrent.*;

public class factorial implements Callable<Integer> {
    private int number;

    public factorial(int number) {
        this.number = number;
    }

    @Override
    public Integer call() {
        // Método para calcular el factorial del número
        int factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario la cantidad de números
        System.out.print("Ingrese la cantidad de números para los cuales desea calcular el factorial: ");
        int numCount = scanner.nextInt();

        // Crear un pool de hilos
        ExecutorService executorService = Executors.newFixedThreadPool(numCount);
        // Arreglo para almacenar los resultados de los hilos
        Future<Integer>[] futures = new Future[numCount];

        // Iterar sobre la cantidad de números especificada
        for (int i = 0; i < numCount; i++) {
            // Solicitar al usuario el número actual
            System.out.print("Número " + (i + 1) + ": ");
            int num = scanner.nextInt();

            // Crear un objeto FactorialCalculator con el número actual
            factorial calculator = new factorial(num);
            // Enviar el objeto al pool de hilos y almacenar el Future resultante
            futures[i] = executorService.submit(calculator);

            try {
                // Obtener el resultado del cálculo del hilo actual
                int result = futures[i].get();
                // Mostrar el resultado
                System.out.println("El factorial de " + num + " es: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Apagar el pool de hilos
        executorService.shutdown();
        // Cerrar el escáner
        scanner.close();
    }
}
