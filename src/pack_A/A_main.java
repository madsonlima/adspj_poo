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

		// Mostrar tabela de cadastro ------------------------------------------------------------------------------------------------------------------------------------------
		Metodos.arqMostrarArray(arrayCadastros);

		// Inserir dados no array ----------------------------------------------------------------------------------------------------------------------------------------------
		ModeloCadastro novoCadastro = Metodos.arqInserirCadastro(arrayCadastros.get(0));	// Retorna um ModeloCadastro para inserir em novoCadastro. arrayCadastros.get(0) é
		arrayCadastros.add(novoCadastro);													// recebido para verificar os títulos das colunas.
		System.out.println(Metodos.ANSI_CYAN + "[SUCESSO] CADASTRO INCLUÍDO: '" + novoCadastro.getNome() + "'\n" + Metodos.ANSI_RESET);

		// Alterar dados de cadastros ------------------------------------------------------------------------------------------------------------------------------------------
		// acho que talvez tenha que dar clear pra depois inserir de um por um
		arrayCadastros = Metodos.arqAlterarCadastro(arrayCadastros);

		// Deletar cadastro ----------------------------------------------------------------------------------------------------------------------------------------------------
		arrayCadastros = Metodos.arqDeletarCadastro(arrayCadastros);

		// Sobrescrever arquivo csv --------------------------------------------------------------------------------------------------------------------------------------------
		Metodos.arqSobrescrever(arrayCadastros);



		sc.close();

	}

}
