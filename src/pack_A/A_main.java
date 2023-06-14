/**
 * 
 */
package pack_A;

import entities.Metodos;
import entities.ModeloCadastro;
import java.util.ArrayList;
import java.util.Scanner;


public class A_main {

	public static void main(String[] args) /*throws ParseException */{		// Alterar tipo da variável. //import java.text.ParseException;
		
		Scanner sc = new Scanner(System.in);
		ArrayList<ModeloCadastro> arrayCadastros = new ArrayList<ModeloCadastro>();		// Cria um arraylist do tipo ModeloCadasatro

		// Confirmar existência do dataframe -----------------------------------------------------------------------------------------------------------------------------------
		Metodos.arqInicializar();

		// Ler o csv e armazenar em um araylist --------------------------------------------------------------------------------------------------------------------------------
		arrayCadastros = Metodos.arqLerArray();

		while (true) {

			int opcao = 0;
			while ((opcao != 1)||(opcao != 2)||(opcao != 3)||(opcao != 4)||(opcao != 5)){

				System.out.println("\nDigite a Opção:\n1- Mostrar Cadastros.\n2- Inserir Cadastro.\n3- Alterar Cadastro.\n4- Deletar Cadastro.\n5- Salvar e Sair.\n");
				opcao = sc.nextInt();
				break;

			}

            switch (opcao) {
                case 1:
					// Mostrar tabela de cadastro -------------------------------------------------------------------------------------------------------------------------------
                    Metodos.arqMostrarArray(arrayCadastros);
                    break;

                case 2:
					// Inserir dados no array -----------------------------------------------------------------------------------------------------------------------------------
					ModeloCadastro novoCadastro = Metodos.arqInserirCadastro(arrayCadastros.get(0));	// Retorna um ModeloCadastro para inserir em novoCadastro. arrayCadastros.get(0) é
					arrayCadastros.add(novoCadastro);													// recebido para verificar os títulos das colunas.
					System.out.println(Metodos.ANSI_CYAN + "[SUCESSO] CADASTRO INCLUÍDO: '" + novoCadastro.getNome() + "'\n" + Metodos.ANSI_RESET);
                    break;

                case 3:
					// Alterar dados de cadastros -------------------------------------------------------------------------------------------------------------------------------
                    arrayCadastros = Metodos.arqAlterarCadastro(arrayCadastros);
                    break;

                case 4:
					// Deletar cadastro -----------------------------------------------------------------------------------------------------------------------------------------
                    arrayCadastros = Metodos.arqDeletarCadastro(arrayCadastros);
                    break;

                case 5:
                    System.out.println("SALVANDO E ENCERRANDO!");
                    break;

            }

			if (opcao == 5) {

				break;

			}

        }

		// Sobrescrever arquivo csv --------------------------------------------------------------------------------------------------------------------------------------------
		Metodos.arqSobrescrever(arrayCadastros);

		sc.close();

	}

}
