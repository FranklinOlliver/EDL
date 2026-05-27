public class testePilha {

	public static void main(String[] args) {	
		// 1. Registrar o tempo de início
		long inicio = System.currentTimeMillis(); 

		// 2. Executar o algoritmo/código a ser medidoficado
		Integer[] b = new Integer[1];		
		PilhaArray pp=new PilhaArray(1,0);
		System.out.println("inserindo");
		for(int f=0; f<100; f++){
		  System.out.println(f);		  
		  pp.push(new Integer(f));
		}
		// System.out.println("retirando");
		// for(int f=0;f<10;f++){
		// 	  System.out.print(f);
		// 	  System.out.println(" - "+pp.pop());
		// }

		// 3. Registrar o tempo de término
		long fim = System.currentTimeMillis(); 

		// 4. Calcular e exibir a diferença
		System.out.println("Tempo de execução: " + (fim - inicio) + " ms"); 

		
	}
}
