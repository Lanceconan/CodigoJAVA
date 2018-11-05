/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permutacion;

/**
 *
 * @author Daniel Gutierrez
 */
public class Permutacion {

    private static long permutacionProcesada = 1;
    
    /**
     *
     * @param vector
     */
    private static void printProcesada(long[] vector) {
        int flag = 1;        
        
        for (int i = 0; i < vector.length; i++) {
            
            if(flag == 1){
                System.out.print("Permutación Procesada N°: " + permutacionProcesada + " --> | ");
                permutacionProcesada++;
            }
            
            System.out.print(Permutacion.sumaDigitos(0, vector[i]) + " | ");
            
            if (flag == vector.length) {
                System.out.print("\n");
                flag = 1;
            }
            flag++;
        }
    }
    
    /**
     * 
     * @param valor
     * @return 
     */
    private static long factorial(long acumulado, long valor){
        if(valor == 0) return acumulado;
        return Permutacion.factorial(acumulado * valor, --valor);
    }

    /**
     *
     * @return
     */
    private static long sumaDigitos(long acumulado, long valor) {        
        if(valor == 0) return acumulado;
        return Permutacion.sumaDigitos(acumulado + (valor % 10), valor/10);        
    }

    /**
     *
     * @param vector
     * @param posicion
     * @param tamanho
     */
    private static void permutacion(
        long[] vector,
        int posicion,
        int tamanho
    ) {

        long ref;

        if (posicion >= tamanho) {
            
            Permutacion.printProcesada(vector);
        
        } else {
            
            for (int i = posicion; i < tamanho; i++) {
                
                ref = vector[posicion];
                vector[posicion] = vector[i];
                vector[i] = ref;

                Permutacion.permutacion(
                        vector,
                        posicion + 1,
                        tamanho
                ); 
                
                ref = vector[posicion];
                vector[posicion] = vector[i];
                vector[i] = ref;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        
        long[] vector = {10, 11, 12, 13, 14, 15};
        
        System.out.print("El vector es --> | ");
        
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + " | ");
        }
        System.out.println("\nLa cantidad de permutaciones deberian ser: " + Permutacion.factorial(1, vector.length) + "\n");

        Permutacion.permutacion(
                vector,
                0,
                vector.length
        );
        
        System.out.println("");
    }

}
