package model;
import java.time.LocalDate;

public class Prontuario {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDate data;
    private String diagnostico;
    private String observacoes;
    private String prescricao;

    public Prontuario(int id, Paciente paciente, Medico medico, LocalDate data, String diagnostico, String observacoes, String prescricao){
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.diagnostico = diagnostico;
        this.observacoes = observacoes;
        this.prescricao = prescricao;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }
}
