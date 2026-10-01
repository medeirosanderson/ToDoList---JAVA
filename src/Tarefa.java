import java.util.List;

public class Tarefa {
    private int id;
    private String descricao;
    private boolean concluida;
    private int proximoId = 7;

    
    public Tarefa(String descricao, boolean concluida) {
        this.id = proximoId;
        this.descricao = descricao;
        this.concluida = false;
        proximoId++;
    }

    

    public Tarefa(int id, String descricao, boolean concluida) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = false;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean isConcluida() {
        return concluida;
    }
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public void buscarTarefa(List<Tarefa> lista , int id){
        System.out.println(lista.get(id));
    }

    @Override
    public boolean equals(Object obj) {
        boolean tarefaIgual = false;
        if(obj instanceof Tarefa){
            Tarefa novaTarefa = (Tarefa) obj;
            tarefaIgual = this.getDescricao().toLowerCase()
            .equals(novaTarefa.getDescricao().toLowerCase());
        }
        return tarefaIgual;
    }

    public void concluirTarefa(List<Tarefa> lista, int id){
        if (!lista.get(id).isConcluida()) {
            concluida = true;
            System.out.println("Tarefa Concluida");
        } else {
            System.out.println("Essa tarefa já foi finalizada");
        }
    }
}
