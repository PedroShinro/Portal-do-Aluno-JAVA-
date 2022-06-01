
package portaldoaluno;
import java.util.Scanner;

public class PortalDoAluno {
    static Scanner console = new Scanner(System.in);

    static final int TOTAL_AVALIACOES = 3;
    static final String[] NOMES_AVALIACOES = { "A1", "A2", "A3" };
    static final double[] NOTA_MAX_AVALIACOES = { 30.00, 30.00, 40.00 };
    
    static double[] notas = new double [TOTAL_AVALIACOES];
    
    /**
	   * Ler uma nota do usuário
	   * @param mensagem O texto que aparecerá na tela
	   * @return um número double representando a nota.
	  */
    
    static double lerNota(String mensagem, double notaMaxima) {
        double nota = 0.0;

        do {
            System.out.printf("%s = ", mensagem);
            nota = console.nextDouble();
        } 
        while (nota < 0.00 || nota > notaMaxima);
        return nota;
    }// Fim do método  lerNota
    
    /**
     * Atualiza o valor da respectiva nota do estudante
     * @param indiceNota um número inteiro representando o índice (posição) da nota no vetor
    */
  
    static void atualizarNota(int indiceNota) {
        System.out.println();
        notas[indiceNota] = lerNota(NOMES_AVALIACOES[indiceNota], NOTA_MAX_AVALIACOES[indiceNota]);
    } // Fim do método atualizarNota
  
    /**
     * @return uma string representando o status final do estudante, são eles: APROVADO, REPROVADO, EM RECUPERAÇÃO.
    */
    
    static String avaliarSituacao(double[] notas) {
        if(somarNotas(notas) < 30)
            return "REPROVADO";
        else if (somarNotas(notas) < 70)
            return "EM RECUPERAÇÃO";
        else
            return "APROVADO";
    } // Fim do método avaliarSituacao()
  
    /**
     * A soma de todas as avalições feita pelo estudante ao longo do semestre.
     * @return valor da soma.
    */
    
    static double somarNotas(double[]notas){
        double soma= (PortalDoAluno.notas[0] + PortalDoAluno.notas[1] + PortalDoAluno.notas[2]);
        return soma;
    }// Fim do método somarNotas
    
    /**
     * A soma de todas as avalições feita pelo estudante ao longo do semestre dividido pelo número de avaliações.
     * @return valor da média.
    */
  
    public static double calcularMedia(double[] notas){
        double calcMedia= somarNotas(notas)/3;
        return calcMedia;
    }// Fim do método calcularMedia
  
    /**
     * Verificar qual avaliação teve a maior nota
     * @return A1 ou A2 ou A3
    */
  
    static String maiorNota(double[] notas){
        String maiorNota= "";
        double verificarMaior= 0;
      
        for (int i= TOTAL_AVALIACOES - 1; i>= 0; i--){
            if (notas[i] > verificarMaior){
                verificarMaior= notas[i];
                maiorNota= NOMES_AVALIACOES[i];
            }
          
        }
        return maiorNota;
    }// Fim do método maiorNota
    
    /**
     * solicita a nota da AI (RECUPERAÇÃO) e substitui A1 ou A2 
    */  
  
    static void notaAI(double[] notas) {
        if(somarNotas(notas) < 70 && somarNotas(notas) >= 30) {
            System.out.printf("\n Insira a nota da AI: ");
	          double AI = console.nextDouble();
        
            if(AI > PortalDoAluno.notas[0] || AI > PortalDoAluno.notas[1]){
                if(AI > PortalDoAluno.notas[0] && PortalDoAluno.notas[0] < PortalDoAluno.notas[1]){
                PortalDoAluno.notas[0] = AI;
                }
                else{
                    PortalDoAluno.notas[1] = AI;
                }
            }    
        }
    }// Fim do método notaAI
  
    /**
     * Mostra na tela um relatório das notas do estudante
    */
  
    static void mostrarNotas() {
        System.out.printf("\n\n ===========================================");
        System.out.printf("\n\t\t   NOTAS");
        System.out.println();

        for (int i = 0; i < TOTAL_AVALIACOES; i++) {

            System.out.printf("          Avaliação %s = %.2f pts", NOMES_AVALIACOES[i], notas[i]);
            System.out.println();
        }
        System.out.printf("\n             Média = %.2f pts", calcularMedia(notas));
        System.out.printf("\n              Maior nota: "+ maiorNota(notas));
        System.out.printf("\n          Nota Final = %.2f pts", somarNotas(notas));
        System.out.printf("\n            Situação = %s", avaliarSituacao(notas));
        System.out.printf("\n =========================================== \n");
    } // Fim do método mostrarNotas()
    
    /**
     * Exibe o menu principal da aplicação
    */
    static void mostrarMenu() {
        System.out.println("\n\n");
        System.out.println("\t\tMENU");
        System.out.println();
    
        System.out.println("[1] Cadastrar Notas A1");
        System.out.println("[2] Cadastrar Nota A2");
        System.out.println("[3] Cadastrar Nota A3");
        System.out.println("[4] Mostrar Notas");
        System.out.println("[0] SAIR");

        System.out.print("\nDigite uma opção:  ");
        byte opcao = console.nextByte();

        switch(opcao) {

            case 0:
                System.exit(0);
                break;
      
            case 1:
                atualizarNota(0);
                break;
            
            case 2:
            atualizarNota(1);
            break;

            case 3:
                atualizarNota(2);
                break;

            case 4:
                mostrarNotas();
                notaAI(notas);
                break;

            default:
                mostrarMenu();
                break;
        }
        mostrarMenu();
    } // Fim do método mostrarMenu()

    public static void main(String[] args) {
        mostrarMenu();
    } // Fim do método main();
} // Fim da classe Main
