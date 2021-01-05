package service;

public class FerramentasPi {

	public static int[][] filtroMediana(int matriz[][]) {

		int mask[] = new int[9];
		int matrizMediana[][] = new int[matriz.length][matriz[0].length];

		for (int linha = 0; linha < matriz.length; linha++) {
			for (int coluna = 0; coluna < matriz[0].length; coluna++) {
						if ((matriz.length - 1) > linha && linha > 0 && (matriz[0].length - 1) > coluna && coluna > 0) {
							mask[0] = matriz[linha - 1][coluna - 1];
							mask[1] = matriz[linha - 1][coluna];
							mask[2] = matriz[linha - 1][coluna + 1];
							mask[3] = matriz[linha][coluna - 1];
							mask[4] = matriz[linha][coluna];
							mask[5] = matriz[linha][coluna + 1];
							mask[6] = matriz[linha + 1][coluna - 1];
							mask[7] = matriz[linha + 1][coluna];
							mask[8] = matriz[linha + 1][coluna + 1];

							
							insertionSort(mask);
							matrizMediana[linha][coluna] = mask[5];

						} else
							matrizMediana[linha][coluna] = 0;
					}
				}
			
		

		return matrizMediana;
	}
	
	public static void insertionSort(int[] vet) { 
		for (int i = 0; i < vet.length; i++) { 
			
			int num = vet[i]; 
			
			for (int j = i - 1; j >= 0 && vet[j] > num; j--) { 
				
				vet[j + 1] = vet[j];
				vet[j] = num;
				
				}
			} 
		}
	
	
	public static int[][] dilatacao(int matriz[][]) {
		
		int novaMatriz[][] = new int[matriz.length][matriz[0].length];
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if ((i - 1) > 0 && (i + 1) < matriz.length) {
					if (matriz[i - 1][j] == 1 || matriz[i][j] == 1 || matriz[i + 1][j] == 1)
						novaMatriz[i][j] = 1;
					else
						novaMatriz[i][j] = 0;
				} else if ((i - 1) < 0) {
					if (matriz[i][j] == 1 || matriz[i + 1][j] == 1)
						novaMatriz[i][j] = 1;
					else
						novaMatriz[i][j] = 0;
				} else if ((i + 1) > matriz.length)
					if (matriz[i - 1][j] == 1 || matriz[i][j] == 1)
						novaMatriz[i][j] = 1;
					else
						novaMatriz[i][j] = 0;
			}
		}

		return novaMatriz;
	}
	
	// aqui ele destaca uma linha de texto com a ajuda da contagem de linhas,
	//e depois e feito um feito um recorte das palavras circulando cada uma separadamente,
	//utiliza uma contagem de coluna para verificar a separação das palavras
    public static int[][] palavras(int matriz[][], int vetor[]) {
    	int aux = 0;
    	int x = 0;
    	for(int i = 0; i < (vetor.length - 1); i++) {
    		if(vetor[i] == 0 && vetor[i+1] == 1) {
    			aux = i;
    		} if(i > 0) {
    			if(vetor[i] == 0 && vetor[i-1] == 1 && aux != 0) {
    				int vetorCol[] = new int[matriz[0].length];
    				
    				for(int j = 1; j < (vetorCol.length - 1); j++) {
    					for(int k = aux; k <= i; k++) {
    						if(matriz[k][j] == 1) vetorCol[j] = 1;    						
    					}
    				}
    				    				
    				for(int j = aux; j <= i; j++) {
    					for(int k = 5; k < (vetorCol.length - 5); k++) {
    						if(vetorCol[k] == 0 && vetorCol[k-1] == 0
    								&& vetorCol[k-4] == 0 && vetorCol[k-5] == 0 
    								&& vetorCol[k-3] == 0 && vetorCol[k-2] == 0 
    								&& vetorCol[k+1] == 1) {
    							matriz[j][k] = 1;
    							x = k;
    						} if(k > 0) {
    							if(vetorCol[k] == 0 && vetorCol[k+1] == 0 
    									&& vetorCol[k+4] == 0 
    									&& vetorCol[k+3] == 0 
    									&& vetorCol[k+5] == 0 
    									&& vetorCol[k+2] == 0 
    									&& vetorCol[k-1] == 1) {
    								matriz[j][k] = 1;
    								
    								for(int l = x; l <= k; l++) {
    									matriz[aux][l] = 1;
    									matriz[i][l] = 1;
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	return matriz;
    }

}
