/**
 *
 * @author Daniel Gutierrez
 */
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Encriptar {

 /*Variables de la clase*/
 char MatrizMadre[][];
 int Primero[],Segundo[];
 /*Funcion base que permite crear la matriz de codigo*/
 void CompletarMatriz(){
 int CaracterEntero = 65, MAX = 5 ;
 this.MatrizMadre = new char[MAX][MAX];

    for (int x = 0; x < MAX; x++)
        for(int y = 0; y < MAX; y++)
        {
            if (CaracterEntero == 75 ) CaracterEntero++;
            this.MatrizMadre[x][y] = (char)CaracterEntero;
            CaracterEntero++;
        }
 }

 /*Funcion que permite desplegar la matriz de codigo*/
 void MostrarMatriz(){
 System.out.println("\n  Esta es la matriz del codigo K");
 System.out.println("  _____ _____ _____ _____ _____");
 System.out.print(" |");
    for (int x = 0; x < 5; x++)
    {
        for(int y = 0; y < 5; y++)
            System.out.print("  " + this.MatrizMadre[x][y] + "  |");
        System.out.println("\n  _____ _____ _____ _____ _____ ");
        System.out.print(" |");
    }

 }

 /*Funcion para crear un archivo*/
 void CrearArchivo(String Cadena_Nombre) throws IOException
 {
    File fichero = new File (Cadena_Nombre);
    try {
    // A partir del objeto File creamos el fichero físicamente
    if (fichero.createNewFile())
    System.out.println("El fichero " + Cadena_Nombre + " se ha creado correctamente");
    else
    System.out.println("No ha podido ser creado el fichero, posiblemente ya existe");
    } catch (IOException ioe) {
    ioe.printStackTrace();
    }
 }

 /*Funcion que permite encriptar el codigo*/
 void LeerEncriptar(String in, String out) throws IOException
 {
    String renglon;
    char Palabra[],aux;
    int AuxPiv=0, Pivote = 0, Num_MaxCar = 100;
    Palabra = new char[Num_MaxCar];
    File Entrada = new File(in);
    File Salida = new File(out);
    BufferedReader entrada = new BufferedReader(new FileReader(Entrada));
    BufferedWriter  salida = new BufferedWriter (new FileWriter (Salida));
    PrintWriter wr = new PrintWriter(salida);
    while ((renglon = entrada.readLine()) != null) {
            for (int i = 0; i < renglon.length(); i++)
          {
            if (renglon.charAt(i) != ' ')
            {
              Palabra[Pivote] = renglon.charAt(i);
              Pivote++;
            }
	    if(renglon.charAt(i) == ' ' || (i+1) == renglon.length())
	    {
	    /*Aqui empieza algoritmo de encriptacion*/
                this.Primero = new int [2];
                this.Segundo = new int [2];
                int aux_piv = Pivote;
                aux = Palabra[Pivote-1];
                if(Pivote%2!=0) Pivote--;
                  AuxPiv = 0;
                  while (AuxPiv < Pivote)
                    {
                      for(int x = 0; x < 5; x++)
                       {
                       for(int y = 0; y < 5; y++)
                       {
                            if(Palabra[AuxPiv] == this.MatrizMadre[x][y])
                            {
                                this.Primero[0] = x;
                                this.Primero[1] = y;
                            }
                            if(Palabra[AuxPiv+1] == this.MatrizMadre[x][y])
                            {
                                this.Segundo[0] = x;
                                this.Segundo[1] = y;
                            }
                       }
                       }
                    /*Aqui se escribe la palabra en el archivo nuevo*/
                    wr.write(this.MatrizMadre[this.Primero[0]][this.Segundo[1]]);
                    wr.write(this.MatrizMadre[this.Segundo[0]][this.Primero[1]]);
                    /*Aqui se termina el escribir la palabra en el archivo nuevo*/
                    AuxPiv = AuxPiv + 2;
                    }                  
                  if(aux_piv%2 != 0)wr.write(aux);
                  wr.write(" ");
                  /*Aqui termina algoritmo de encriptacion*/                
                Palabra = new char[Num_MaxCar];
		Pivote = 0;
		}
          }
wr.write("\r\n");
}
entrada.close();
salida.close();
   //funcion
 }

 /*Funcion que permite mostrar los archivos*/
 void LeerArchivos(String Entrada) throws IOException
 {
    String LINE;
    File pFILE = new File(Entrada);
    BufferedReader recorrer = new BufferedReader(new FileReader(pFILE));
    while ((LINE = recorrer.readLine()) != null) System.out.println(LINE);
 }

 /*Funcion main*/

public static void main( String args[]) throws IOException
{

int Opcion;
String NOM_ENT = "entrada.txt", NOM_SAL = "salida.txt", texto, linea, arch_entr, arch_sal;
Encriptar Prueba = new Encriptar();
BufferedReader leer_opcion = new BufferedReader (new InputStreamReader(System.in));
File File_Aux = new File(NOM_ENT);
do{
System.out.println("\n\nMenu de selección");
System.out.println("Seleccione una opcion");
System.out.println("1. Crear Archivos de [Emisor] y [Receptor]");
System.out.println("2. Crear la matriz del sistema[del tipo K en nuestro caso]");
System.out.println("3. Codificar el archivo de entrada[Emisor]");
System.out.println("4. Leer y desplegar el contenido de los archivos [Emisor] - [Receptor]");
System.out.println("5. Mostrar la matriz del sistema[del tipo K en nuestro caso]");
System.out.println("6. encriptar el archivo entrada directamente");
System.out.println("7. Salir");
System.out.println("\n\n Ingrese su opcion: ");
linea = "";
linea = leer_opcion.readLine();
Opcion = Integer.parseInt(linea);
switch(Opcion)
{
    case 1:
    Prueba.CrearArchivo(NOM_SAL);
    Prueba.CrearArchivo(NOM_ENT);
    break;

    case 2:
    Prueba.CompletarMatriz();
    System.out.println("Se ha llenado la matriz [codigo X]");
    break;

    case 3:
    if (!File_Aux.exists())
    {
        System.out.println("No se ha creado los archivos de [Emisor]-[Receptor]");
        break;
    }
    Prueba.CompletarMatriz();
    BufferedWriter  esc_entr = new BufferedWriter (new FileWriter(NOM_ENT));
    System.out.println("Ingrese el texto a codificar:\n");
    texto = leer_opcion.readLine();
    texto = texto.toUpperCase();
    esc_entr.write(texto);
    esc_entr.close();
    System.out.println("\nSe ha ingresado el texto para encriptar");
    Prueba.LeerEncriptar(NOM_ENT,NOM_SAL);
    System.out.println("Se ha encriptado con exito");
    esc_entr.close();
    break;

    case 4:
    if (!File_Aux.exists())
    {
        System.out.println("No se ha creado los archivos de [Emisor]-[Receptor]");
        break;
    }
    System.out.println("El archivo original:");
    Prueba.LeerArchivos(NOM_ENT);
    System.out.println("El archivo encriptado:");
    Prueba.LeerArchivos(NOM_SAL);
    break;

    case 5:
    Prueba.CompletarMatriz();
    Prueba.MostrarMatriz();
    break;

    case 6:
    Prueba.CompletarMatriz();
    System.out.println("Indique nombre del archivo a encriptar[sugerencia: prueba.txt]");
    arch_entr = leer_opcion.readLine();
    arch_entr = arch_entr.toLowerCase();
    System.out.println("Indique el nombre del archivo de salida [sugerencia: out.txt o .doc]");
    arch_sal = leer_opcion.readLine(); 
    arch_sal = arch_sal.toLowerCase();
    Prueba.CrearArchivo(arch_sal);
    Prueba.LeerEncriptar(arch_entr, arch_sal);
    System.out.println("Se ha encriptado con exito");
    break;
    
    case 7:
    System.out.println("Usted ha salido del programa");
    System.out.println("Programado por Daniel Gutierrez");
    break;

    default:
    System.out.println("Ha digitado opcion no valida");
    break;
}
}while(Opcion != 7);

}

}// fin de la clase principal