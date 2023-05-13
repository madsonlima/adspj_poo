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
//import java.util.Iterator;
//import java.util.List;
import java.util.Scanner;

//import entities.Socio;

public class A_main {

	public static void main(String[] args) /*throws ParseException */{		// Alterar tipo da variável. //import java.text.ParseException;
		
		Scanner sc = new Scanner(System.in);
		
// Caminhos --------------------------------------------------------------------------------------------------------------------------------------------------------------------
		/*
		Caminho caso aberto em alguma ide no windows. No github codespaces não é preciso usar "\\".

		String pathWorkbench = ".\\.\\";
			String pathFolder_dataframes = pathWorkbench + "\\dataframes";
				String pathFolder_cadastros = pathFolder_dataframes + "\\cadastros";
				String path_allcad = pathFolder_dataframes + "\\allcad.csv";			//file
		*/
		
		String pathWorkbench = "././";
			String pathFolder_dataframes = pathWorkbench + "/dataframes";
				String pathFolder_cadastros = pathFolder_dataframes + "/cadastros";
				String path_allcad = pathFolder_dataframes + "/allcad.csv";				//file
				
		String pathSourceFile = path_allcad;

		File csvCadastro = new File(pathSourceFile);					// Localiza o arquivo para trabalhar.
		System.out.println("Arquivo identificado: " + csvCadastro);
		
		
// Confirmar existência do dataframe -------------------------------------------------------------------------------------------------------------------------------------------
		
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

		
// Colunas e linhas ------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		String[] arrayColunas = new String[7];		// Quantidade de colunas do dataframe.
		int countLinhas = 10;						// Quantidade de pessoas que podem ser cadastradas.
		
		arrayColunas[0] = "ID";						// Intância o título de cada coluna do dataframe.
		arrayColunas[1] = "Nome";
		arrayColunas[2] = "Data de nascimento";
		arrayColunas[3] = "Estado, Cidade";
		arrayColunas[4] = "Telefone";
		arrayColunas[5] = "Email";
		arrayColunas[6] = "Github";
/*
		ArrayList<String> arraylistId = new ArrayList<String>();		// Instância do arraylist para os valores de cada linha em sua determinada coluna.
		ArrayList<String> arraylistNome = new ArrayList<String>();
		ArrayList<String> arraylistNasc = new ArrayList<String>();
		ArrayList<String> arraylistEnd = new ArrayList<String>();
		ArrayList<String> arraylistTel = new ArrayList<String>();
		ArrayList<String> arraylistMail = new ArrayList<String>();
		ArrayList<String> arraylistGit = new ArrayList<String>();
		*/
		

// Abrir o arquivo -------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathSourceFile))) {


// Mostrar tabela de cadastro (deve sempre rodar apenas 1 vez) -----------------------------------------------------------------------------------------------------------------

			for (String celula: arrayColunas) {

				if (celula == arrayColunas[arrayColunas.length - 1]) {					// Imprime o cabeçalho do dataframe.

					System.out.println(" | " + String.format("%10s", celula) + " |");	// Se for a última célula.
					
				} else if (celula == arrayColunas[2]) {

					System.out.print(" | " + String.format("%18s", celula));			// Se for "Data de nascimento".

				} else {
											
					System.out.print(" | " + String.format("%10s", celula));			// Se não for a última célula.
					
				}

			}

			String[][] arraySF = new String[arrayColunas.length][countLinhas];
// **************
			
			String	linhaCsv	= br.readLine();
			int		lin			= 0;
			int		col			= 0;

			int		countID		= 0;

			while ((linhaCsv != null) && (lin < countLinhas)) {			// (lin < arrayLinhas) pode ser deletado?		// Deve ser rodado 1 vez!!!!!!!!!!!

				String[] celulasLinha = linhaCsv.split(";");	// Da split em cada linha no passo do while.
				
				for (String celula: celulasLinha) {		// A cada laço do for, celula recebe 1 dos valores splitados em celulasLinha.
					
					arraySF[lin][col] = celula;			// Esse valor é inserido na posição correspondente no array.
					
					if (col == 2) {

						System.out.print(" | " + String.format("%18s", arraySF[lin][col]));				// Se for a coluna 3, "Data de nascimento".
						
					} else if (col == 3) {

						System.out.print(" | " + String.format("%14s", arraySF[lin][col]));				// Se for a coluna 4, "Estado, Cidade".

					} else if (col < (arrayColunas.length) - 1) {

						System.out.print(" | " + String.format("%10s", arraySF[lin][col]));				// Se não for a última coluna.
	
					} else {
						
						System.out.println(" | " + String.format("%10s", arraySF[lin][col]) + " |");	// Se for a última coluna.
						
					}
					
					col++;					// Adciona 1 à col para trocar de coluna.
					
				}
				
				countID++;					// Checa o ID da última pessoa cadastrada.

				col = 0;					// Reseta o auxiliar do array para a primeira coluna.
				lin++;						// Pula o auxiliar do array para a próxima linha.
				linhaCsv = br.readLine();	// Lê a próxima linha.
				
			}


// Inserir dados no array ------------------------------------------------------------------------------------------------------------------------------------------------------
// Index 8 out of bounds for length 7

			System.out.println("CountID == " + countID);	// Confirmando coundID.

			System.out.println("Adicionar currículo? [0 = Não][1 = Sim]");
			int inserirCurriculo = sc.nextInt();

			if (inserirCurriculo >= 1) {

				if (countID == (countLinhas - 2)) {			// Verifica se a tabela está cheia. // Conto -2 porque sempre deve haver um espaço vazio na tabela, ela nunca pode estar realmente cheia.
					
					System.out.println("Tabela cheia! Será adicionado mais um espaço.");		
					countLinhas++;				// Adiciona +1 à quantidade de linhas no array.
	
				}

				countID++;			// Gera o ID para mais uma pessoa. Só funciona se "Mostrar tabela de cadastro" tiver rodado 1 vez.

				arraySF[countID][0] = String.valueOf(countID);			// Insere o ID como string.

				for (col = 1; col < arrayColunas.length; col++) {
	
					System.out.printf(arrayColunas[col] + ": ");		// Mostra o título da coluna.
					arraySF[countID][col] = sc.nextLine();				// Lê e insere na coluna determinada.
					
				}

				inserirCurriculo = 0;		// Reseta inserirCurriculo.

			}
			

// Sobrescrever arquivo com o array --------------------------------------------------------------------------------------------------------------------------------------------
// Cannot invoke "String.length()" because "<parameter1>" is null

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathSourceFile))) {		// Função pra escrever no arquivo.
				// [arrayColunas.length][countLinhas]
				for (lin = 0; lin <= countID; lin++) {					// 2 for pra acessar as linhas e colunas do array.

					for (col = 0; col < arrayColunas.length; col++) {

						if (col == (arrayColunas.length - 1)){			// Se for a última coluna, não adciona ';'.

							bw.write(arraySF[lin][col]);

						} else {										// Se não for a última coluna, adiciona ';'.

							bw.write(arraySF[lin][col] + ";");

						}

					}

					bw.newLine();										// Quebra a linha dentro do arquivo.

				}

			} catch (Exception e) {

				System.out.println("Erro 2 ao acessar arquivo: " + e.getMessage());

			}

// Exceções e fechamento -------------------------------------------------------------------------------------------------------------------------------------------------------

		} catch (Exception e) {
			
			System.out.println("Erro 1 ao acessar o arquivo: " + e.getMessage());
			
		}

		sc.close();

	}

}
