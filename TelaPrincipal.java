import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Gestão Hospitalar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel com Abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Aba 1: Pacientes
        JPanel painelPacientes = new JPanel(new BorderLayout());
        String[] colunasPacientes = {"ID", "Nome", "CPF", "Telefone"};
        Object[][] dadosPacientes = {
            {1, "João da Silva", "111.222.333-44", "(31) 98765-4321"}
        };
        JTable tabelaPacientes = new JTable(dadosPacientes, colunasPacientes);
        painelPacientes.add(new JScrollPane(tabelaPacientes), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(new JButton("Cadastrar Paciente"));
        painelBotoes.add(new JButton("Excluir Paciente"));
        painelPacientes.add(painelBotoes, BorderLayout.SOUTH);

        // Aba 2: Consultas
        JPanel painelConsultas = new JPanel(new GridLayout(5, 2, 10, 10));
        painelConsultas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelConsultas.add(new JLabel("ID Paciente:"));
        painelConsultas.add(new JTextField());
        painelConsultas.add(new JLabel("ID Médico:"));
        painelConsultas.add(new JTextField());
        painelConsultas.add(new JLabel("Data (YYYY-MM-DD):"));
        painelConsultas.add(new JTextField());
        painelConsultas.add(new JLabel("Hora (HH:MM):"));
        painelConsultas.add(new JTextField());
        JButton btnAgendar = new JButton("Agendar Consulta");
        painelConsultas.add(btnAgendar);

        // Adicionando as abas
        tabbedPane.addTab("Pacientes", painelPacientes);
        tabbedPane.addTab("Agendamentos", painelConsultas);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}