/**
 * 
 */
package pack_A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import entities.Socio;

public class A_main {

	public static void main(String[] args) /*throws ParseException */{		// Alterar tipo da variável. //import java.text.ParseException;
		
		Scanner sc = new Scanner(System.in);
		
// Caminhos ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		String pathWorkbench = ".\\.\\";
			String pathFolder_dataframes = pathWorkbench + "\\dataframes";
				String pathFolder_cadastros = pathFolder_dataframes + "\\cadastros";
				String path_allcad = pathFolder_dataframes + "\\allcad.csv";			//file
				
		String pathSourceFile = path_allcad;
		
		//String pathSourceFolder = "C:\\Users\\202208687334\\Desktop\\dataframes\\cadastros\\Cadastros.csv"; // Obtém o caminho desprezando o nome do arquivo.
			
		File csvCadastro = new File(pathSourceFile);					// Arquivo paratrabalhar.
		System.out.println("Arquivo identificado: " + csvCadastro);
		
		
// Confirmar existência do dataframe ---------------------------------------------------------------------------------------------------------------------------------------------------------
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(pathSourceFile));	// Lê e fecha o arquivo para confirmar que ele existe.
			br.close();
			System.out.println("Arquivo lido com sucesso");
			
		} catch (Exception e) {														// Caso não, entra na exceção para cariar a pasta e o arquivo.
			try {
				
				boolean pastaCriada = new File(pathFolder_dataframes).mkdir();		// Cria uma pasta.
				System.out.println(pastaCriada + ": Pasta 'dataframes' criada.");
				
				pastaCriada = new File(pathFolder_cadastros).mkdir();				// Dentro dela cria outra pasta.
				System.out.println(pastaCriada + ": Pasta 'cadastros' criada.");
				
				// String path_cad = pathFolder_cadastros + "'id'";					// Futuro Target file para cada um que se inscrever.
				
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathSourceFile))) {
					
					bw.write("");													// Cria o arquivo allcad.csv
					System.out.println("Arquivo 'allcad.csv' criado em: " + pathSourceFile);
					
				} catch (Exception e3) {
					
					System.out.println("Erro 2 ao criar arquivo: " + e3.getMessage());
					
				}
			} catch (Exception e2) {
				
				System.out.println("Erro 1 ao criar arquivo: " + e2.getMessage());
				
			}
		}

		
// Colunas e linhas ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		String[] arrayColunas = new String[7];		// Quantidade de colunas do dataframe.
		int arrayLinhas = 10;						// Quantidade de pessoas que podem ser cadastradas.
		
		arrayColunas[0] = "ID";						// Intância de cada coluna do dataframe.
		arrayColunas[1] = "Nome";
		arrayColunas[2] = "Data de nascimento";
		arrayColunas[3] = "Estado e Cidade";
		arrayColunas[4] = "Telefone";
		arrayColunas[5] = "Email";
		arrayColunas[6] = "Github";
		
		
// Mostrar tabela de cadastro ----------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathSourceFile))) {
			
			String[][] arraySF = new String[arrayColunas.length][arrayLinhas];
			
			String	linhaCsv	= br.readLine();
			int		lin			= 0;
			int		col			= 0;
					
			while ((linhaCsv != null) && (lin < arrayLinhas)) {	// (lin < arrayLinhas) pode ser deletado?
				
				String[] celulasLinha = linhaCsv.split(";");	// Da split em cada linha no passo do while.
				
				for (String celula: celulasLinha) {		// A cada laço do for, celula recebe 1 dos valores splitados em celulasLinha.
					
					arraySF[lin][col] = celula;			// Esse valor é inserido na posição correspondente no array.
					
					if (col < (arrayColunas.length) - 1) {

						System.out.print(" | " + String.format("%10s", arraySF[lin][col]));
						
					} else {
						
						System.out.println(" | " + String.format("%10s", arraySF[lin][col]) + " |");
						
					}
					
					col++;					// Adciona 1 à col para trocar de coluna.
					
				}
				
				col = 0;					// Reseta o auxiliar do array para a primeira coluna.
				lin++;						// Pula o auxiliar do array para a próxima linha.
				linhaCsv = br.readLine();	// Lê a próxima linha.
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Erro ao acessar o arquivo: " + e.getMessage());
			
		}

		
		sc.close();

	}

}
