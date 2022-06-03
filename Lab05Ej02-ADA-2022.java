import java.util.Random;
/*Laboratorio 05 - Análisis y dideño de algoritmos
	Integrantes:
	-Quispe Tito Juan Carlos
	-Arteaga Peña Carlos Fabián*/
public class BubbleSort {
	
	public static void main(String args[])
    {
		//Declaración de un nuevo objeto
        BubbleSort bubble = new BubbleSort();
        //Creando y llenando el array...
        int arr[] =new int[10];
        Random aleatorio = new Random(System.currentTimeMillis());
        for (int i = 0; i < arr.length; i++) {   
        	int intAletorio = aleatorio.nextInt(10);
        	arr[i]=intAletorio;
		    }
        //Fin del llenado del array
        
        /*Empezando a tomar el tiempo para medir
        el tiempo de ejecución de BubbleSort*/
        long inicio = System.nanoTime();
        
        //Ordenar el Array
        bubble.bubbleSort(arr);
        
        /*Fin del tiempo de 
        ejecución de BubbleSort*/
        long fin = System.nanoTime();
        
        System.out.println("El tiempo de ejecución de BubbleSort: "+ (fin-inicio)+" nanosegundos");
        
        bubble.printArray(arr);
    }
	
	void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {                    
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
	
	void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
