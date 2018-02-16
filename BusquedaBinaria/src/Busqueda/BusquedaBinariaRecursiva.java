/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Busqueda;

/**
 *
 * @author John
 */
import java.util.*;
 
class BusquedaBinariaRecursiva
{
private static int CantidadDeOperaciones = 0;

 static int[] inicializa(int n)
 {
  int i,j,a[]=new int[n];
  for(i=0;i<n;i++)
  {
   a[i]=randomxy(1,50);   
  }
 
  return a;
 }
 
 static int busquedaBinariaRecursion(int a[],int n,int Iabajo,int Iarriba)
 {
  int Icentro,indice=-1;
  CantidadDeOperaciones++;
  if(Iarriba<Iabajo)
  {
   return -1;
  }
 
  else
  {
   Icentro=(Iabajo+Iarriba)/2;
   if (n<a[Icentro])
   {
    return(busquedaBinariaRecursion(a,n,Iabajo,Icentro-1));
   }
 
   else
   {
     if (n>a[Icentro])
     {
      return(busquedaBinariaRecursion(a,n,Icentro+1,Iarriba));
     }    
     else
     {
      return Icentro+1;
     }
 
   }
  }
 }
 
 static int[] ordenarArreglo(int a[], int n)
 {
    int i,j,t;
 
  for(i=0;i<n-1;i++)
  {
   for(j=0;j<n-1;j++)
   {
    if(a[j]>a[j+1])
    {
     t=a[j];
     a[j]=a[j+1];
     a[j+1]=t;
    }
   }
  }
  return a;  
 } 
 
 static void muestra(int a[])
 {
  int n=a.length;
  for(int i=0;i<n;i++)
  {   
   System.out.print(a[i]+" ");   
  }
  System.out.print("nn");  
 
 }
 
 static int randomxy(int x,int y)
 {   
  int ran=(int) (Math.floor(Math.random()*(y-x+1))+x);
 
  return ran;
 }

 public static void main(String[] args)
 {
  int a[],n,n1,indice,Iabajo,Iarriba; 
  Scanner sc=new Scanner(System.in);
 
  System.out.print("Ingresa tamaño de arreglo: ");
  n=sc.nextInt();
 
  a=new int[n];
  a=inicializa(n);
  a=ordenarArreglo(a,n);
  muestra(a);
  Iabajo=0;
  Iarriba=n-1;
 
  System.out.print("Ingresa numero a buscar: ");
  n1=sc.nextInt();
 
  indice=busquedaBinariaRecursion(a,n1,Iabajo,Iarriba);
 
  if(indice==-1)
  {
   System.out.println("tu número no esta en la lista");
  }
  else
  {
     System.out.println("La cantidad de busquedas fueron: " + CantidadDeOperaciones);
     System.out.println("tu número esta en el indice: " + indice);
  }
 }

}