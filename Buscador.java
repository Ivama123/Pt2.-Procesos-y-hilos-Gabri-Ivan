import java.util.Random;
import java.util.Scanner;
public class Buscador {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario los valores
        System.out.print("Ingrese el tamaño del vector: ");
        int tamañoVector = scanner.nextInt();

        System.out.print("Ingrese el valor a buscar: ");
        int valorABuscar = scanner.nextInt();

        System.out.print("Ingrese el número de hilos: ");
        int numeroDeHilos = scanner.nextInt();

        // Verificar límites de los argumentos
        if (tamañoVector <= 0 || numeroDeHilos <= 0 || numeroDeHilos > tamañoVector) {
            System.out.println("Por favor, ingrese valores válidos para los argumentos.");
            System.exit(1);
        }

        // Crear el vector con valores aleatorios
        int[] vector = new int[tamañoVector];
        Random random = new Random();
        for (int i = 0; i < tamañoVector; i++) {
            vector[i] = random.nextInt(1000); // Rango de valores aleatorios (ajustar según sea necesario)
            
        }

        // Calcular el rango de búsqueda para cada hilo
        int rangoPorHilo = 94;

        // Crear e iniciar los hilos
        BuscadorThread[] hilos = new BuscadorThread[numeroDeHilos];
        int start = 0;
        int end = rangoPorHilo;
        int ultimaPosicion = 0; // Ultima posicion encontrada por los hilos
        for (int i = 0; i < numeroDeHilos; i++) {
            hilos[i] = new BuscadorThread(vector, start, end, valorABuscar,numeroDeHilos);
            hilos[i].start();
            start += rangoPorHilo+1;
            end = start+rangoPorHilo;
         
            if(i == numeroDeHilos-1) {
            	ultimaPosicion = end;
            }
        }
        // Esperar a que todos los hilos terminen
        for (BuscadorThread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // El proceso principal busca las posiciones restantes
        for (int i = ultimaPosicion; i < tamañoVector; i++) {
            if (vector[i] == valorABuscar) {
                System.out.println("Proceso padre ha encontrado el valor en la posición: " + i);
            }
        }
        

       

        // Si no se encuentra el valor
        System.out.println("Ningún hilo o proceso padre ha encontrado el valor.");
    }
}
