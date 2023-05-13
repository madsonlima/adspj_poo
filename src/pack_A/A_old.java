package pack_A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import entities.Socio;

public class A_old {

	// Mostrar tabela de cadastro
	System.out.println("1 - Mostrar cadastros");
	
	try (BufferedReader br = new BufferedReader(new FileReader(pathSourceFile))) {
		
		//List<String> listaCelulasPrint = new ArrayList<>();
		String linhaCsv = br.readLine();
		while (linhaCsv != null) {
			String[] celulasPrint = linhaCsv.split(";");
			/*
			String celPrID = celulasPrint[0];
			String celPrName = celulasPrint[1];
			String celPrBirth = celulasPrint[2];
			*/
			System.out.print(String.format("|%8s|", celulasPrint[0]));
			System.out.print(String.format("%30s|", celulasPrint[1]));
			System.out.println(String.format("%10s|", celulasPrint[2]));
			/*
			listaCelulasPrint.add(celPrID);
			listaCelulasPrint.add(celPrName);
			listaCelulasPrint.add(celPrBirth);
			*/
			linhaCsv = br.readLine();
		}
		/*
		int auxBreak = 0;
		for (int aux = 0; aux < listaCelulasPrint.size(); aux++) {
		    if (auxBreak == 0) {
		    	System.out.print(String.format("|%4s|", listaCelulasPrint[aux]));
		    	auxBreak = 1;
		    } else if (auxBreak == 1) {
		    	System.out.print(String.format("%20s|", listaCelulasPrint[aux]));
		    	auxBreak = 2;
		    } else if (auxBreak == 2) {
		    	System.out.println(String.format("%10s|", listaCelulasPrint[aux]));
		    	auxBreak = 0;
		    }
		}
		*/
		br.close();
	} catch (Exception e) {
		System.out.println("Erro ao ler arquivo:" + e.getMessage());
	}
	
	
	// Inserir dados no dataframe --------------------------------------------------------------------------------------------------------------
	System.out.println("2 - Cadastrar sócio");

	try (BufferedReader br = new BufferedReader(new FileReader(pathSourceFile))) {
		
		List<String> listaTamanho = new ArrayList<>();
		String linhaCsv = br.readLine();
		while (linhaCsv != null) {
			String[] celulasTamanho = linhaCsv.split(";");
			
			listaTamanho.add(celulasTamanho[0]);
			listaTamanho.add(celulasTamanho[1]);
			listaTamanho.add(celulasTamanho[2]);
			
			linhaCsv = br.readLine();
		}
		int cadCount = (listaTamanho.size()/3) - 1;	// Contador de pessoas cadastradas.
		
		
		List<Socio> listaCadastro = new ArrayList<>();
		
		String cada = String.valueOf(cadCount);
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		System.out.println("Data de nascimento: [dia-mês-ano] ");
		String nasc = sc.nextLine();
		
		listaCadastro.add(new Socio(cada, nome, nasc));
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathSourceFile))) {
			
			for (Socio celulaAp: listaCadastro) {
				bw.append(celulaAp.getId() + ";" + celulaAp.getName() + ";" + celulaAp.getBirth());
				bw.newLine();
			}
			System.out.println("Cadastro efetuado com sucesso.");
			cadCount++;	// Se cadastrado com sucesso, adiciona 1 a contagem de cadastrados.
			
		} catch (Exception e) {
			System.out.println("Erro ao acessar arquivo: " + e.getMessage());
		}
		
		listaCadastro.clear();	// De qualquer maneira a lista precisa ser limpa.
		
	} catch (Exception e) {
		System.out.println("Erro ao ler arquivo:" + e.getMessage());
	}
	
}
