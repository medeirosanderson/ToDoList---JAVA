import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import Exceptions.OpcaoInvalidaException;
import Exceptions.TarefaNaoEncontradaException;

public class Menu {
    static Scanner sc = new Scanner(System.in);

    private List<Tarefa> minhasTarefas = new ArrayList<>();

    public List<Tarefa> getMinhasTarefas() {
        return minhasTarefas;
    }

    public void setMinhasTarefas(List<Tarefa> minhasTarefas) {
        this.minhasTarefas = minhasTarefas;
    }

    public void iniciarMenu() {
        int opcao = -1;
        
        while (opcao!=0) {
        System.out.println("================================================");
        System.out.println("\t\tLista de Tarefas");
        System.out.println("================================================");
        System.out.println("\t\tTarefas Pententes");
        tarefasPendetes();


        System.out.println("[1] - Abrir Tarefa");
        System.out.println("[2] - Adicionar Nova Tarefa");        
        System.out.println("[3] - Exibir Tarefas Concluidas");
        System.out.println("[0] - Sair");

        System.out.println("Digite sua opção: ");
        
        try {
            opcao = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Digite apenas números!");
            sc.nextLine();
            continue;
        }
            try{
                switch (opcao) {
                    case 1:
                        abrirTarefaMenu();
                        break;
                    case 2:
                        adicionarTarefaMenu();
                        break;
                    case 3:
                        mostrarConcluidas();
                        break;
                    case 0:
                        System.out.println("Você saiu do programa... Até mais!");
                        break;
                    default:
                        throw new OpcaoInvalidaException("Opção Invalida, tente novamente.");
                }
            } catch(OpcaoInvalidaException e){
                System.out.println(e.getMessage());
            }  
        }
    }

    public void inicializarLista(){
        minhasTarefas.add(new Tarefa(1, "Estudar Espanhol", false));
        minhasTarefas.add(new Tarefa(2, "Lavar Louça", false));
        minhasTarefas.add(new Tarefa(3, "Limpar Caixa de Areia", true));
        minhasTarefas.add(new Tarefa(4, "Academia", false));
        minhasTarefas.add(new Tarefa(5, "Estudar Java", true));
        minhasTarefas.add(new Tarefa(6, "Estudar Espanhol", false));
    }

    public void tarefasPendetes(){
        System.out.println();
        int contadorPendentes = 0;
        for (Tarefa tarefa : minhasTarefas) {
            if (!tarefa.isConcluida()) {
                contadorPendentes++;
                System.out.println("Tarefa " + tarefa.getId());
                System.out.println("Descrição : " + tarefa.getDescricao());
                System.out.println("Concluida : " + tarefa.isConcluida());
                System.out.println();
            }
        }
        if (contadorPendentes == 0) {
            System.out.println("Não existem tarefas pendentes");
        } else {
            System.out.println("Existem " + contadorPendentes + " Tarefas pendentes!");
        }
        System.out.println();
    }

    public void abrirTarefaMenu(){
        System.out.println("================================================");
        System.out.println("\t\tInforme o ID da tarefa");
        System.out.println("================================================");
        int opcao;

        try {
            opcao = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Digite apenas Números!");
            sc.nextLine();
            return;
        }

        int posição = opcao -1;
        boolean idVerificado = false;
        int tamanhoLista = minhasTarefas.size();

        if (opcao > tamanhoLista || opcao <= 0) {
            System.out.println("#### ID Incorreto... ####");
        } else {
            idVerificado = true;
        }
        
        System.out.println("================================================");
        if (!idVerificado) {

        } else {
            System.out.println("Tarefa " + minhasTarefas.get(posição).getId());
            System.out.println("Descrição : " + minhasTarefas.get(posição).getDescricao());
            System.out.println("Concluida : " + minhasTarefas.get(posição).isConcluida());
            System.out.println();
            System.out.println("[1] - Editar Tarefa \t [2] - Concluir Tarefa \t [3] - Excluir Tarefa");
            System.out.println("[0] - Voltar");
            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas Números!");
                sc.nextLine();
                return;
            }
            switch (opcao) {
                case 1:
                    System.out.println("Digite a nova descrição: ");
                    String descricao = sc.nextLine();
                    minhasTarefas.get(posição).setDescricao(descricao);
                    System.out.println("================================================");
                        System.out.println("Descrição Atualizada!");
                        System.out.println(minhasTarefas.get(posição).getDescricao());
                        System.out.println("================================================");
                        voltarMenu();
                    break;
                case 2: 
                        System.out.println("================================================");
                        minhasTarefas.get(posição).concluirTarefa(minhasTarefas, posição);
                        System.out.println("================================================");
                        voltarMenu();
                    break;
                case 3: 
                        System.out.println("================================================");
                        minhasTarefas.remove(posição);
                        System.out.println("Tarefa Removida da Lista!");
                        System.out.println("================================================");
                        voltarMenu();
                    break;
                default:
                        System.out.println("Opção Incorreta!");
                    break;
            }
        }
    }
                
    public void voltarMenu (){
        
        boolean respostaValida = false;
        
        while (!respostaValida) {

            System.out.println("Deseja voltar ao menu? [S/N]");
            String opcao = sc.nextLine();

            try {
                if (opcao.equalsIgnoreCase("s")) {
                    respostaValida = true;
                    iniciarMenu();
                }
                else if (opcao.equalsIgnoreCase("n")) {
                    System.out.println("Você saiu do sistema...");
                    sc.close();
                    System.exit(0);
                }
    
                else {
                    throw new OpcaoInvalidaException("Opção Invalida, tente novamente.");
                }
    
            } catch (OpcaoInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void mostrarConcluidas(){
        int contadorConcluida = 0;
        System.out.println("================================================");
        System.out.println("\t\t Tarefas Concluidas");
        System.out.println("================================================");
        for (Tarefa tarefa : minhasTarefas) {
            if (tarefa.isConcluida()) {
                System.out.println("Tarefa " + tarefa.getId());
                System.out.println("Descrição : " + tarefa.getDescricao());
                System.out.println("Concluida : " + tarefa.isConcluida());
                System.out.println();
                contadorConcluida++;
            }
        }
        System.out.println("================================================");       
        System.out.println("Existem " + contadorConcluida + " Tarefas Concluidas");
        System.out.println("================================================");   
        voltarMenu();
    }

    public void adicionarTarefaMenu(){
        System.out.println("================================================");
        System.out.println("\t Adicionar Nova Tarefa");
        System.out.println("================================================");
        System.out.println();
        System.out.println("Insira a descrição da Tarefa: ");
        String descricao = sc.nextLine();
        minhasTarefas.add(new Tarefa(descricao, false));
        System.out.println("------------------------------------------------");
        System.out.println("Tarefa Adicionada com sucesso!");
        System.out.println();
        voltarMenu();
    }

    public Tarefa buscarTarefa(List<Tarefa> minhasTarefas, Integer id)
    throws TarefaNaoEncontradaException {
        for (Tarefa tarefa : minhasTarefas) {
            if (tarefa.getId() == id) {
                return tarefa;
            }
        }
        throw new TarefaNaoEncontradaException("Tarefa não Encontrada!");
    }
}
