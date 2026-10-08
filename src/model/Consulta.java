package model;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
    // representa uma consulta marcada

        private int id;
        private LocalDate dataConsulta;
        private LocalTime horaConsulta;
        // quando a consulta e criada ja entra como agendada
        private StatusConsulta status = StatusConsulta.Agendada;
        private int pacienteId;
        private int medicoId;
        private int consultorioId;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public LocalDate getDataConsulta() { return dataConsulta; }
        public void setDataConsulta(LocalDate dataConsulta) { this.dataConsulta = dataConsulta; }

        public LocalTime getHoraConsulta() { return horaConsulta; }
        public void setHoraConsulta(LocalTime horaConsulta) { this.horaConsulta = horaConsulta; }

        public StatusConsulta getStatus() { return status; }
        public void setStatus(StatusConsulta status) { this.status = status; }

        public int getPacienteId() { return pacienteId; }
        public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }

        public int getMedicoId() { return medicoId; }
        public void setMedicoId(int medicoId) { this.medicoId = medicoId; }

        public int getConsultorioId() { return consultorioId; }
        public void setConsultorioId(int consultorioId) { this.consultorioId = consultorioId; }
}
