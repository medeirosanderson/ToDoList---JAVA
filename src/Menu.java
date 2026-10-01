import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private List<Tarefa> minhasTarefas = new ArrayList<>();

    public List<Tarefa> getMinhasTarefas() {
        return minhasTarefas;
    }

    public void setMinhasTarefas(List<Tarefa> minhasTarefas) {
        this.minhasTarefas = minhasTarefas;
    }

    public void iniciarMenu(){
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

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
        opcao = sc.nextInt();

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
                System.out.println("Opção Invalida!");
                break;
        }

        sc.close();
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
        Scanner sc = new Scanner(System.in);
        System.out.println("================================================");
        System.out.println("\t\tInforme o ID da tarefa");
        System.out.println("================================================");
        int opcao = sc.nextInt();
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
            opcao = sc.nextInt();
            sc.nextLine();
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
        sc.close();
    }
                
    public void voltarMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja voltar ao menu? [S/N]");
        String opcao = sc.nextLine();
        if (opcao.toLowerCase().equals("s")) {
            iniciarMenu();
        } else {
            System.out.println("Você saiu do sistema...");
        }
        sc.close();
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
        Scanner sc = new Scanner(System.in);
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
        sc.close();
    }

}
