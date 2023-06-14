package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Metodos {

    public static final Scanner sc = new Scanner(System.in);

    // Cores para o terminal ---------------------------------------------------------------------------------------------------------------------------------------------------
    public static final String ANSI_RESET = "\u001B[0m";    // Resetar cor
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";     // Erros
    public static final String ANSI_GREEN = "\u001B[32m";   // Confirmações
    public static final String ANSI_YELLOW = "\u001B[33m";  // Atenção
    public static final String ANSI_BLUE = "\u001B[34m";    // Alterando
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";    // Confirmações com alteração de arquivo
    public static final String ANSI_WHITE = "\u001B[37m";

    // Caminhos ----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static String pathWorkbench = "././";                                              // Caminho da pasta do projeto
        public static String pathFolder_dataframes = pathWorkbench + "/dataframes";           // Caminho da pasta dataframes
            public static String pathFile_current = pathFolder_dataframes + "/allcad.csv";    // Caminho do arquivo csv
    
    public static String pathSourceFile = pathFile_current;       // Recebe o caminho do arquivo csv a ser trabalhado

    /*
	//Caminho caso aberto em alguma ide no windows. No github codespaces não é preciso usar "\\".

	public static final String pathWorkbench = ".\\.\\";
		public static final String pathFolder_dataframes = pathWorkbench + "\\dataframes";
			public static final String pathFile_current = pathFolder_dataframes + "\\allcad.csv";
	*/

    // Confirmar existência do dataframe ---------------------------------------------------------------------------------------------------------------------------------------
    static public void arqInicializar() {

        try {       // Tenta ler e fechar o arquivo para confirmar que ele existe.
			
			BufferedReader br = new BufferedReader(new FileReader(pathSourceFile));     
			br.close();
			System.out.println(ANSI_GREEN + "[SUCESSO] ARQUIVO LIDO: " + pathSourceFile + ANSI_RESET);
			
		} catch (Exception e) {     // Caso o arquivo não exista, entra na exceção para criar a pasta e o arquivo.

			try {       // Tenta criar a pasta
				
				boolean pastaCriada = new File(pathFolder_dataframes).mkdir();		// Método para criar uma pasta de caminho 'pathFolder_dataframes'.

                if (pastaCriada) {

                    System.out.println(ANSI_CYAN + "[SUCESSO] " + pastaCriada + ": PASTA CRIADA: " + pathFolder_dataframes + ANSI_RESET);

                } else {

                    System.out.println(ANSI_GREEN + "[SUCESSO] PASTA IDENTIFICADA: " + pathFolder_dataframes + ANSI_RESET);

                }
				
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathSourceFile))) {      // Tenta criar o arquivo de caminho 'pathSourceFile', neste caso 'allcad.csv'.
					
                    // Escreve a linha dos títulos.
					bw.write("Nome;Ano de Nascimento;Telefone;Email;GitHub;Cidade;Formação;Experiência;Nível de Inglês;Conhecimentos Específicos\n");
					System.out.println(ANSI_CYAN + "[SUCESSO] ARQUIVO CSV CRIADO: " + pathSourceFile + ANSI_RESET);
					
				} catch (Exception e2) {
					
					System.out.println(ANSI_RED + "[ERRO 02] NÃO FOI POSSÍVEL CRIAR O ARQUIVO CSV: " + pathSourceFile + "\n" + e2.getMessage());
					
				}

			} catch (Exception e1) {
				
				System.out.println(ANSI_RED + "[ERRO 01] NÃO FOI POSSÍVEL CRIAR UMA PASTA: " + pathSourceFile + "\n" + e1.getMessage() + ANSI_RESET);
				
			}

		}

    }

    static public ArrayList<ModeloCadastro> inserirModelo(ArrayList<ModeloCadastro> lista, ModeloCadastro modelo) {
        
        lista.add(modelo);
        return lista;

    }


    // Ler o csv e retornar em um araylist -------------------------------------------------------------------------------------------------------------------------------------

    static public ArrayList<ModeloCadastro> arqLerArray() {

        //ModeloCadastro modeloAtual = new ModeloCadastro();							// https://stackoverflow.com/questions/4580728/adding-items-from-a-while-loop-to-an-arraylist-java
        ArrayList<ModeloCadastro> arrayCadastros = new ArrayList<ModeloCadastro>();		// Arraylist para inserção dos cadastros.

        try (BufferedReader br = new BufferedReader(new FileReader(pathSourceFile))) {

            // Inserção do dataframe nos arraylists ----------------------------------------------------------------------------------------------------------------------------
            String linhaCsv	= br.readLine();		    	    	// Lê a primeira linha (títulos).

			while (linhaCsv != null) {							    // Começa a ler os cadastros.

				String[] celulasLinha = linhaCsv.split(";");    	// Da split na linha e põe cada célula em um array.
                ModeloCadastro modeloAtual = new ModeloCadastro();  // Modelo para receber dados de cadastro.
				
				modeloAtual.setNome(celulasLinha[0]);			    // Seta no cadastro modelo os dados dessa linha.
				modeloAtual.setAno(celulasLinha[1]);
				modeloAtual.setTelefone(celulasLinha[2]);
				modeloAtual.setEmail(celulasLinha[3]);
				modeloAtual.setGithub(celulasLinha[4]);
				modeloAtual.setCidade(celulasLinha[5]);
				modeloAtual.setFormacao(celulasLinha[6]);
				modeloAtual.setExperiencia(celulasLinha[7]);
				modeloAtual.setNivIngles(celulasLinha[8]);
				modeloAtual.setConhecimentos(celulasLinha[9]);

				arrayCadastros.add(modeloAtual);				// Seta este cadastro no arrayCadastros.

				linhaCsv = br.readLine();						// Lê a próxima linha.
				
			}

            return arrayCadastros;                              // Retorna um array com todos os cadastros lidos.

        } catch (Exception e3) {
			
			System.out.println(ANSI_RED + "[ERRO 03] NÃO FOI POSSÍVEL ACESSAR O ARQUIVO: " + pathSourceFile + "\n" + e3.getMessage() + ANSI_RESET);
            return arrayCadastros;                              // Retorna um array nulo.

		}

    }
    
    // Ler o array e mostrar seu conteúdo --------------------------------------------------------------------------------------------------------------------------------------

    static public void arqMostrarArray(ArrayList<ModeloCadastro> arrayCadastros) {

        for (ModeloCadastro modeloLido: arrayCadastros) {		// Para cada modelo lido em arrayCadastros...
			
			System.out.printf(String.format("| %20s |", modeloLido.getNome()));			// Formatando a impressão das stings.
			System.out.printf(String.format(" %17s |", modeloLido.getAno()));			// www.javatpoint.com/java-string-format
			System.out.printf(String.format(" %10s |", modeloLido.getTelefone()));
			System.out.printf(String.format(" %25s |", modeloLido.getEmail()));
			System.out.printf(String.format(" %15s |", modeloLido.getGithub()));
			System.out.printf(String.format(" %15s |", modeloLido.getCidade()));
			System.out.printf(String.format(" %15s |", modeloLido.getFormacao()));
			System.out.printf(String.format(" %15s |", modeloLido.getExperiencia()));
			System.out.printf(String.format(" %15s |", modeloLido.getNivIngles()));
			System.out.printf(String.format(" %25s |\n", modeloLido.getConhecimentos()));

		}

    }

    // Retorna um novo cadastro ------------------------------------------------------------------------------------------------------------------------------------------------

    static public ModeloCadastro arqInserirCadastro(ModeloCadastro Titulos) {

		ModeloCadastro modeloAtual = new ModeloCadastro();

        System.out.printf("%s: ", Titulos.getNome());
        modeloAtual.setNome(sc.nextLine());
        System.out.printf("%s: ", Titulos.getAno());
        modeloAtual.setAno(sc.nextLine());
        System.out.printf("%s: ", Titulos.getTelefone());
        modeloAtual.setTelefone(sc.nextLine());
        System.out.printf("%s: ", Titulos.getEmail());
        modeloAtual.setEmail(sc.nextLine());
        System.out.printf("%s: ", Titulos.getGithub());
        modeloAtual.setGithub(sc.nextLine());
        System.out.printf("%s: ", Titulos.getCidade());
        modeloAtual.setCidade(sc.nextLine());
        System.out.printf("%s: ", Titulos.getFormacao());
        modeloAtual.setFormacao(sc.nextLine());
        System.out.printf("%s: ", Titulos.getExperiencia());
        modeloAtual.setExperiencia(sc.nextLine());
        System.out.printf("%s: ", Titulos.getNivIngles());
        modeloAtual.setNivIngles(sc.nextLine());
        System.out.printf("%s: ", Titulos.getConhecimentos());
        modeloAtual.setConhecimentos(sc.nextLine());

		return modeloAtual;

    }

    // Retorna o array modificado ----------------------------------------------------------------------------------------------------------------------------------------------

    static public ArrayList<ModeloCadastro> arqAlterarCadastro(ArrayList<ModeloCadastro> arrayCadastros) {

        System.out.println(ANSI_BLUE + "[ALTERANDO] ALTERAR DADOS DE QUE PESSOA? " + ANSI_RESET);
        String altObjeto = sc.nextLine();
        int indexObj = 0;       // Auxiliar para detectar o index de modeloAtual em arrayCadastros.

        for (ModeloCadastro modeloAtual: arrayCadastros) {

            if (modeloAtual.getNome().contains(altObjeto)) {

                System.out.println(ANSI_BLUE + "[ALTERANDO] ALTERAR QUAL DADO? ");
                System.out.printf("------------------------------\n[1] Nome\n[2] Ano de Nascimento\n[3] Telefone\n[4] Email\n[5] GitHub\n[6] Cidade\n[7] Formação\n[8] Experiência\n[9] Nível de Inglês\n[10] Conhecimentos Específicos\n------------------------------\n" + ANSI_RESET);
                
                String w = sc.nextLine();
                int altAtributo = Integer.valueOf(w);
                while ((altAtributo < 1) || (altAtributo > 10)) {

                    System.out.println(ANSI_RED + "[ERRO] DIGITE UMA OPÇAO VÁLIDA." + ANSI_RESET);
                    w = sc.nextLine();
                    altAtributo = Integer.valueOf(w);

                }

                switch (altAtributo) {

                    case 1:
                        System.out.printf("%s: ", arrayCadastros.get(0).getNome());     // Mostra o primeiro modelo (título).
                        arrayCadastros.get(indexObj).setNome(sc.nextLine());                // Seta no ModeloCadastro do índice específico do arrayCadastros recebido.
                        return arrayCadastros;
                    case 2:
                        System.out.printf("%s: ", arrayCadastros.get(0).getAno());
                        arrayCadastros.get(indexObj).setAno(sc.nextLine());
                        return arrayCadastros;
                    case 3:
                        System.out.printf("%s: ", arrayCadastros.get(0).getTelefone());
                        arrayCadastros.get(indexObj).setTelefone(sc.nextLine());
                        return arrayCadastros;
                    case 4:
                        System.out.printf("%s: ", arrayCadastros.get(0).getEmail());
                        arrayCadastros.get(indexObj).setEmail(sc.nextLine());
                        return arrayCadastros;
                    case 5:
                        System.out.printf("%s: ", arrayCadastros.get(0).getGithub());
                        arrayCadastros.get(indexObj).setGithub(sc.nextLine());
                        return arrayCadastros;
                    case 6:
                        System.out.printf("%s: ", arrayCadastros.get(0).getCidade());
                        arrayCadastros.get(indexObj).setCidade(sc.nextLine());
                        return arrayCadastros;
                    case 7:
                        System.out.printf("%s: ", arrayCadastros.get(0).getFormacao());
                        arrayCadastros.get(indexObj).setFormacao(sc.nextLine());
                        return arrayCadastros;
                    case 8:
                        System.out.printf("%s: ", arrayCadastros.get(0).getExperiencia());
                        arrayCadastros.get(indexObj).setExperiencia(sc.nextLine());
                        return arrayCadastros;
                    case 9:
                        System.out.printf("%s: ", arrayCadastros.get(0).getNivIngles());
                        arrayCadastros.get(indexObj).setNivIngles(sc.nextLine());
                        return arrayCadastros;
                    case 10:
                        System.out.printf("%s: ", arrayCadastros.get(0).getConhecimentos());
                        arrayCadastros.get(indexObj).setConhecimentos(sc.nextLine());
                        return arrayCadastros;

                }


            }

            indexObj++;

        }

        System.out.println(ANSI_RED + "[ERRO 04] NÃO HÁ NENHUMA PESSOA CADASTRADA COMO '" + altObjeto + "'.\n" + ANSI_RESET);
        return arrayCadastros;

    }
    
    // Deleta um cadastro ------------------------------------------------------------------------------------------------------------------------------------------------------
    
    static public ArrayList<ModeloCadastro> arqDeletarCadastro(ArrayList<ModeloCadastro> arrayCadastros) {

        System.out.println(ANSI_BLUE + "[DELETANDO] DELETAR DADOS DE QUE PESSOA? " + ANSI_RESET);
        String delObjeto = sc.nextLine();
        int indexObj = 0;       // Auxiliar para detectar o index de modeloAtual em arrayCadastros.

        for (ModeloCadastro modeloAtual: arrayCadastros) {

            if (modeloAtual.getNome().contains(delObjeto)) {

                System.out.printf(ANSI_BLUE + "[DELETANDO] DELETAR DADOS DE '%s'? [1=Sim][0=Não] " + ANSI_RESET, modeloAtual.getNome());
                
                String w = sc.nextLine();
                int delConfirmar = Integer.valueOf(w);

                if (delConfirmar > 0) {

                    arrayCadastros.remove(indexObj);
                    System.out.println(ANSI_CYAN + "[SUCESSO] CADASTRO DELETADO." + ANSI_RESET);
                    return arrayCadastros;

                } else {

                    System.out.println(ANSI_CYAN + "[DELETANDO] OPERAÇÃO CANCELADA." + ANSI_RESET);
                    return arrayCadastros;

                }                

            }

            indexObj++;

        }

        System.out.println(ANSI_RED + "[ERRO 05] NÃO HÁ NENHUMA PESSOA CADASTRADA COMO '" + delObjeto + "'.\n" + ANSI_RESET);
        return arrayCadastros;

    }

    // Sobrescrever csv --------------------------------------------------------------------------------------------------------------------------------------------------------

    static public void arqSobrescrever(ArrayList<ModeloCadastro> arrayCadastros) {

        try (BufferedReader br = new BufferedReader(new FileReader(pathSourceFile))) {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathSourceFile))) {		// Função pra escrever no arquivo.
            
                for (ModeloCadastro cadastro: arrayCadastros) {

                    bw.write(cadastro.getNome() + ";" + cadastro.getAno() + ";" + cadastro.getTelefone() + ";" + cadastro.getEmail() + ";" + cadastro.getGithub() + ";" + cadastro.getCidade() + ";" + cadastro.getFormacao() + ";" + cadastro.getExperiencia() + ";" + cadastro.getNivIngles() + ";" + cadastro.getConhecimentos());
                    bw.newLine();		// Quebra a linha dentro do arquivo.

                }
            
            } catch (Exception e2) {
            
                System.out.println(ANSI_RED + "[ERRO 07] NÃO FOI POSSÍVEL MODIFICAR O ARQUIVO: " + pathSourceFile + "\n" + e2.getMessage() + ANSI_RESET);
            
            }
            
        } catch (Exception e1) {
            
            System.out.println(ANSI_RED + "[ERRO 06] NÃO FOI POSSÍVEL ACESSAR O ARQUIVO: " + pathSourceFile + "\n" + e1.getMessage() + ANSI_RESET);
                        
        }

    }


    /*
     * 
     */

}
