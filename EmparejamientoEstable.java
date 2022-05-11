public class EmparejamientoEstable
{
	public static void main(String args[]) {
		
		String h[]= {"Victor","William","Xavier","Yancey","Zeus"};//Victor[0],William[1],Xavier[2],Yancey[3],Zeus[4]
		int prefH[][]= {{1,0,3,4,2},{3,1,0,2,4},{1,4,2,3,0},{0,3,2,1,4},{1,3,0,4,2}};

		String m[]= {"Amy","Bertha","Claire","Diane","Erika"};//Amy[0],Bertha[1],Claire[2],Diane[3],Erika[4]
		int prefM[][]= {{4,0,1,3,2},{2,1,3,0,4},{1,2,3,4,0},{0,4,3,2,1},{3,1,4,2,0}};

		int empP[][] = galeShapley(prefH,prefM);//retorna la matris de emparejamiento Perfecto

		System.out.println("Las parejas son: ");
		for(int i = 0; i <h.length;i++) {
			System.out.println(h[empP[i][0]]+" y "+m[empP[i][1]]);
		}
	}
	public static int[][] galeShapley(int[][] prefH, int[][] prefM){
		int parejas = prefH.length;

		boolean HconPareja[] = new boolean[parejas];//si hn tiene pareja
		boolean MconPareja[] = new boolean[parejas];//si mn tiene pareja
		int j[] = new int[parejas];//matris de intentos para los 4 hombres

		int resultadoH[][] = new int [parejas][2];//hombre con su pareja
		int resultadoM[][] = new int [parejas][2];//mujer con su pareja


		for(int i = 0; i<parejas;i++) {//rellenando porque al inicio nadie tiene pareja
			HconPareja[i]=false;
			MconPareja[i]=false;
			j[i]=0;
		}

		int h=0;//hombres con pareja
		int hn,mn;//un hombre, una mujer

		int i = 0;//numero de hombre

		while(h<parejas) {//bucle de gale Shapley
			while(HconPareja[i]!=false) {//Buscando un hombre sin pareja
				i++;
			}
			hn = i;//un hombre sin pareja
			mn=prefH[hn][j[hn]];//mujer de la lista de preferencias
			if(MconPareja[mn]==false) {//si la mujer no tiene pareja
				resultadoH[i][0]=hn;
				resultadoH[i][1]=mn;
				HconPareja[hn]=true;
				resultadoM[mn][0]=mn;
				resultadoM[mn][1]=hn;
				MconPareja[mn]=true;
				h++;//se creo una nueva pareja
				j[hn]+=1;//se aumenta el intento en su lista de preferencias
				i=0;

			}
			else if(mPrefiereAH(mn,hn,prefM,resultadoM)==true) {//si la mujer prefiere al hombre antes que a su pareja
				int hSinPareja = resultadoM[mn][1];
				resultadoH[hn][0]=hn;
				resultadoH[hn][1]=mn;
				HconPareja[hn]=true;
				resultadoM[mn][0]=mn;
				resultadoM[mn][1]=hn;
				MconPareja[mn]=true;
				HconPareja[hSinPareja]=false;//dejando sin pareja a la antigua pareja de mn
				j[hn]+=1;//se aumenta el intento en su lista de preferencias
				i=0;
			}
			else {//la mujer rechaza al hombre
				j[hn]+=1;//se aumenta el intento en su lista de preferencias
			}
		}
		return resultadoH;
	}
	private static boolean mPrefiereAH(int mn, int hn, int[][] prefM, int[][]resultadoM) {
		int i = 0;
		while(hn-prefM[mn][i]!=0) {//mientras que el hombre sea diferente a el i de la lista de preferencia de la mujer
			if(resultadoM[mn][1]==prefM[mn][i]) {//si la pareja actual esta antes que el hombre retorna falso
				return false;
			}
			i++;
		}
		return true;//retorna verdadero si no se encontro a la pareja antes que el hombre
	}
}
