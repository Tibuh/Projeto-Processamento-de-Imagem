package service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManipuladorDeArquivos {

	public static int[][] geraMatriz(String path) throws IOException {
		int linha = 0;
		int coluna = 0;
		int i = 0;
		int[][] matriz = null;

		String[] dados;

		BufferedReader arquivo = new BufferedReader(new FileReader(path));
		String info = "";
		while (true) {
			if (info == null) break;
			else if (info.equals(""))
				info = arquivo.readLine();
			else {
				if (i == 2) {
					dados = info.split(" ");

					coluna = Integer.parseInt(dados[0]);
					linha = Integer.parseInt(dados[1]);

					matriz = new int[linha][coluna];

					linha = coluna = 0;

				}
				else if (i > 2) {
					for (int j = 0; j < info.length(); j++) {

						info = info.replaceAll("\n", "").replace("\r", "");
						matriz[linha][coluna] = Integer.parseInt(String.valueOf(info.charAt(j)));

						coluna++;

						if (coluna == (matriz[0].length)) {
							coluna = 0;
							linha++;
						}
					}
				}
			}
			
			info = arquivo.readLine();
			i++;
			
		}
		arquivo.close();
		return matriz;
	}

    public static void escritor(int matriz[][]) throws IOException {
    	
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("novaImagem.pbm"));
        buffWrite.append("P1 \n");
        buffWrite.append("# CREATOR: GIMP PNM Filter Version 1.1\n");
        buffWrite.append(matriz[0].length + " " + matriz.length + "\n");
        for(int i = 0; i < matriz.length; i++) {
        	for(int j = 0; j < matriz[0].length; j++) {
        		buffWrite.append(Integer.toString(matriz[i][j]));
        	}
        	buffWrite.append("\n");
        }
        
        buffWrite.close();
        
    }

}
