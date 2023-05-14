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
		
		String[] arrayTitulos = new String[7];		// Quantidade de colunas do dataframe.
		
		arrayTitulos[0] = "ID";						// Intância do título de cada coluna do dataframe.
		arrayTitulos[1] = "Nome";
		arrayTitulos[2] = "Data de nascimento";
		arrayTitulos[3] = "Estado, Cidade";
		arrayTitulos[4] = "Telefone";
		arrayTitulos[5] = "Email";
		arrayTitulos[6] = "Github";

		ArrayList<String> arraylist_0 = new ArrayList<String>();		// Instância do arraylist para os valores de cada linha em sua determinada coluna.
		ArrayList<String> arraylist_1 = new ArrayList<String>();
		ArrayList<String> arraylist_2 = new ArrayList<String>();
		ArrayList<String> arraylist_3 = new ArrayList<String>();
		ArrayList<String> arraylist_4 = new ArrayList<String>();
		ArrayList<String> arraylist_5 = new ArrayList<String>();
		ArrayList<String> arraylist_6 = new ArrayList<String>();
		

// Abrir o arquivo -------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathSourceFile))) {


// Inserção do dataframe nos arraylists ----------------------------------------------------------------------------------------------------------------------------------------

			int countID = 0;
			String linhaCsv	= br.readLine();

			while (linhaCsv != null) {

				String[] celulasLinha = linhaCsv.split(";");	// Da split em cada linha no passo do while.
				
				arraylist_0.add(celulasLinha[0]);
				arraylist_1.add(celulasLinha[1]);
				arraylist_2.add(celulasLinha[2]);
				arraylist_3.add(celulasLinha[3]);
				arraylist_4.add(celulasLinha[4]);
				arraylist_5.add(celulasLinha[5]);
				arraylist_6.add(celulasLinha[6]);
				
				countID++;					// Checa o ID da última pessoa cadastrada.
				linhaCsv = br.readLine();	// Lê a próxima linha.
				
			}

			countID--;		// Diminui 1 quando sai do laço para ficar de acordo com o id do último cadastrado..

// Mostrar tabela de cadastro --------------------------------------------------------------------------------------------------------------------------------------------------

			for (String titulo: arrayTitulos) {											// Imprime o cabeçalho do dataframe.

				if (titulo == arrayTitulos[arrayTitulos.length - 1]) {

					System.out.println(" | " + String.format("%10s", titulo) + " |");	// Se for a última célula.
					
				} else {
											
					System.out.print(" | " + String.format("%10s", titulo));			// Se não for a última célula.
					
				}

			}

			for (int lin = 0; lin <= countID; lin++) {

				for (int col = 0; col < arrayTitulos.length; col++) {

					switch (col) {			// A cada laço da coluna, pega o arraylist especifico e imprime.

						case 0:
							System.out.print(" | " + String.format("%10s", arraylist_0.get(lin)));
							break;
						case 1:
							System.out.print(" | " + String.format("%10s", arraylist_1.get(lin)));
							break;
						case 2:
							System.out.print(" | " + String.format("%18s", arraylist_2.get(lin)));		// Ajusta o tamanho.
							break;
						case 3:
							System.out.print(" | " + String.format("%14s", arraylist_3.get(lin)));		// Ajusta o tamanho.
							break;
						case 4:
							System.out.print(" | " + String.format("%10s", arraylist_4.get(lin)));
							break;
						case 5:
							System.out.print(" | " + String.format("%10s", arraylist_5.get(lin)));
							break;
						case 6:
							System.out.println(" | " + String.format("%10s", arraylist_6.get(lin)) + " |");
							break;

					}

				}
				
			}


// Inserir dados nos arrays ----------------------------------------------------------------------------------------------------------------------------------------------------
// Erro: Não está lendo o nome.
			System.out.println("CountID == " + countID);	// Confirmando coundID.

			System.out.println("Adicionar currículo? [0 = Não][1 = Sim]");
			int inserirCurriculo = sc.nextInt();

			if (inserirCurriculo >= 1) {

				countID++;			// Gera o ID para mais uma pessoa.

				for (int col = 0; col < arrayTitulos.length; col++) {
					
					switch (col) {

						case 0:
							System.out.println(arrayTitulos[col] + ": " + countID);
							arraylist_0.add(String.valueOf(countID));				// Insere o ID como string.							
							break;
						case 1:
							System.out.println(arrayTitulos[col] + ": ");			// Mostra o título da coluna.
							arraylist_1.add(sc.nextLine());							// Lê e insere na coluna determinada.
							break;
						case 2:
							System.out.println(arrayTitulos[col] + ": ");
							arraylist_2.add(sc.nextLine());
							break;
						case 3:
							System.out.println(arrayTitulos[col] + ": ");
							arraylist_3.add(sc.nextLine());
							break;
						case 4:
							System.out.println(arrayTitulos[col] + ": ");
							arraylist_4.add(sc.nextLine());
							break;
						case 5:
							System.out.println(arrayTitulos[col] + ": ");
							arraylist_5.add(sc.nextLine());
							break;
						case 6:
							System.out.println(arrayTitulos[col] + ": ");
							arraylist_6.add(sc.nextLine());
							break;

					}
					
				}

				inserirCurriculo = 0;		// Reseta inserirCurriculo.

			}


// Sobrescrever arquivo com o array --------------------------------------------------------------------------------------------------------------------------------------------


			try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathSourceFile))) {		// Função pra escrever no arquivo.

				for (int lin = 0; lin <= countID; lin++) {

					bw.write(arraylist_0.get(lin) + ";" + arraylist_1.get(lin) + ";" + arraylist_2.get(lin) + ";" + arraylist_3.get(lin) + ";" + arraylist_4.get(lin) + ";" + arraylist_5.get(lin) + ";" + arraylist_6.get(lin));
					bw.newLine();		// Quebra a linha dentro do arquivo.
					
				}

			} catch (Exception e) {

				System.out.println("Erro 2 ao acessar arquivo: " + e.getMessage());

			}

// Fechamento ------------------------------------------------------------------------------------------------------------------------------------------------------------------

		} catch (Exception e) {
			
			System.out.println("Erro 1 ao acessar o arquivo: " + e.getMessage());
			
		}

		sc.close();

	}

}
