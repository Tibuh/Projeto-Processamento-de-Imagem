package entity;
import java.io.IOException;
import java.util.Scanner;

import service.FerramentasPi;
import service.ManipuladorDeArquivos;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		int[][] matrizImagem = null;
		int vetorLinhas[] = null;
		int vetorColunas[] = null;
		int linhas = 0;
		int colunas = 0;
		
		try {
			System.out.println("Digite o nome do arquivo: ");
			String path = input.next();
			
			matrizImagem = ManipuladorDeArquivos.geraMatriz(path);
			vetorLinhas = new int[matrizImagem.length];
			vetorColunas = new int[matrizImagem[0].length];
			
			matrizImagem = FerramentasPi.filtroMediana(matrizImagem);
			matrizImagem = FerramentasPi.filtroMediana(matrizImagem);
			
			matrizImagem = FerramentasPi.dilatacao(matrizImagem);
			
			// Aqui é feito a procura das linhas do arquivo que tem 0 e que tem pelo menos um 1 na linha inteira
			for(int i = 0; i < matrizImagem.length; i++) {
				for(int j = 0; j < matrizImagem[0].length; j++) {
					if(matrizImagem[i][j] == 1) vetorLinhas[i] = 1;
				}
			}
			
			// O mesmo é feito aqui, só que por coluna ele procura qual coluna tem apenas 0 e qual coluna tem pelo menos um 1
			for(int i = 0; i < matrizImagem[0].length; i++) {
				for(int j = 0; j < matrizImagem.length; j++) {
					if(matrizImagem[j][i] == 1) vetorColunas[i] = 1;
				}
			}
			
			// contabilizando uma sequencia de 1 terminando com uma linha em zero no vetor, significa que tem uma linha de texto
			for(int i = 1; i < vetorLinhas.length; i++) {
				if(vetorLinhas[i] == 1 && vetorLinhas[i - 1] == 0) linhas++;
			}
			
			// o mesmo feito para a contagem de colunas de texto, só que com um intervalo maior
			for(int i = 7; i < vetorColunas.length; i++) {
				if(vetorColunas[i] == 1 && vetorColunas[i - 1] == 0 && vetorColunas[i - 2] == 0 && vetorColunas[i - 3] == 0 &&
						vetorColunas[i - 4] == 0 && vetorColunas[i - 5] == 0 && vetorColunas[i - 6] == 0 && vetorColunas[i - 7] == 0) {
					colunas++;
				}
			}
			
			matrizImagem = FerramentasPi.palavras(matrizImagem, vetorLinhas);
			
			ManipuladorDeArquivos.escritor(matrizImagem);
			
			System.out.println("A imagem tem " + linhas + " linhas e " + colunas + " colunas de texto");
			
		} catch(java.io.FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado");
		}
		
		input.close();
	}

}
