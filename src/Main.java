import model.*;
import services.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioService usuarioService = new UsuarioService(); //
    private static final PacienteService pacienteService = new PacienteService(); //
    private static final MedicoService medicoService = new MedicoService(); //
    private static final FuncionarioService funcionarioService = new FuncionarioService(); //
    private static final ConsultaService consultaService = new ConsultaService(); //
    private static final ProntuarioService prontuarioService = new ProntuarioService(); //[cite: 5]

    private static Usuario usuarioLogado = null; //[cite: 14]

    public static void main(String[] args) {
        // Inicializar com um usuário padrão
        usuarioService.cadastrarUsuario(new Usuario(1, "admin", "123456", "FUNCIONARIO")); //

        while (true) {
            if (usuarioLogado == null) {
                menuAutenticacao();
            } else {
                menuPrincipal();
            }
        }
    }

    private static void menuAutenticacao() {
        System.out.println("\n=== SISTEMA DE GESTÃO HOSPITALAR ===");
        System.out.println("1. Login");
        System.out.println("2. Cadastrar Novo Usuário");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        String opcao = scanner.nextLine();
        switch (opcao) {
            case "1" -> efetuarLogin();
            case "2" -> cadastrarUsuario();
            case "0" -> {
                System.out.println("Encerrando o sistema...");
                System.exit(0);
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void efetuarLogin() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario u = usuarioService.realizarLogin(login, senha); //
        if (u != null) {
            usuarioLogado = u; //[cite: 14]
            System.out.println("\nLogin realizado com sucesso! Bem-vindo(a), perfil: " + u.getPerfil()); //[cite: 14]
        } else {
            System.out.println("\nCredenciais inválidas! Se você não possui login, cadastre-se no menu inicial.");
        }
    }

    private static void cadastrarUsuario() {
        System.out.print("Informe o novo Login: ");
        String login = scanner.nextLine();
        System.out.print("Informe a Senha: ");
        String senha = scanner.nextLine();
        System.out.println("Perfil (1: PACIENTE | 2: MEDICO | 3: FUNCIONARIO): ");
        String perfilOp = scanner.nextLine();

        String perfil = switch (perfilOp) {
            case "2" -> "MEDICO";
            case "3" -> "FUNCIONARIO";
            default -> "PACIENTE";
        };

        int id = usuarioService.listarUsuarios().size() + 1; //[cite: 6]
        boolean sucesso = usuarioService.cadastrarUsuario(new Usuario(id, login, senha, perfil)); //[cite: 6, 14]

        if (sucesso) {
            System.out.println("Usuário cadastrado com sucesso! Realize o login.");
        } else {
            System.out.println("Falha ao cadastrar: Login já em uso.");
        }
    }

    private static void menuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL (" + usuarioLogado.getPerfil() + ") ==="); //[cite: 14]

        switch (usuarioLogado.getPerfil()) { //[cite: 14]
            case "FUNCIONARIO" -> menuFuncionario();
            case "MEDICO" -> menuMedico();
            case "PACIENTE" -> menuPaciente();
            default -> usuarioLogado = null;
        }
    }

    private static void menuFuncionario() {
        System.out.println("1. Cadastrar Paciente"); //[cite: 4]
        System.out.println("2. Listar Pacientes"); //[cite: 4]
        System.out.println("3. Cadastrar Médico"); //[cite: 3]
        System.out.println("4. Listar Médicos"); //[cite: 3]
        System.out.println("5. Cadastrar Funcionário"); //[cite: 2]
        System.out.println("6. Agendar Consulta"); //
        System.out.println("7. Cancelar Consulta"); //[cite: 1]
        System.out.println("0. Logout");
        System.out.print("Opção: ");

        String op = scanner.nextLine();
        switch (op) {
            case "1" -> {
                Paciente p = new Paciente(); //[cite: 11]
                p.setId(pacienteService.listarPacientes().size() + 1); //[cite: 4]
                System.out.print("Nome: "); p.setNome(scanner.nextLine()); //[cite: 11]
                System.out.print("CPF: "); p.setCpf(scanner.nextLine()); //[cite: 11]
                System.out.print("Telefone: "); p.setTelefone(scanner.nextLine()); //[cite: 11]
                if (pacienteService.cadastrarPaciente(p)) { //[cite: 4]
                    System.out.println("Paciente cadastrado!");
                } else {
                    System.out.println("Erro: CPF já existe.");
                }
            }
            case "2" -> {
                System.out.println("\n--- LISTA DE PACIENTES ---");
                for (Paciente p : pacienteService.listarPacientes()) { //[cite: 4]
                    System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | CPF: " + p.getCpf()); //[cite: 11]
                }
            }
            case "3" -> {
                Medico m = new Medico(); //[cite: 10]
                m.setId(medicoService.listarMedicos().size() + 1); //[cite: 3]
                System.out.print("Nome: "); m.setNome(scanner.nextLine()); //[cite: 10]
                System.out.print("CRM: "); m.setCrm(scanner.nextLine()); //[cite: 10]
                System.out.print("Especialidade: "); m.setEspecialidade(scanner.nextLine()); //[cite: 10]
                if (medicoService.cadastrarMedico(m)) { //[cite: 3]
                    System.out.println("Médico cadastrado!");
                } else {
                    System.out.println("Erro: CRM já cadastrado.");
                }
            }
            case "4" -> {
                System.out.println("\n--- LISTA DE MÉDICOS ---");
                for (Medico m : medicoService.listarMedicos()) { //[cite: 3]
                    System.out.println("ID: " + m.getId() + " | Nome: " + m.getNome() + " | CRM: " + m.getCrm()); //[cite: 10]
                }
            }
            case "5" -> {
                Funcionario f = new Funcionario(); //[cite: 9]
                f.setId(funcionarioService.listarFuncionarios().size() + 1); //[cite: 2]
                System.out.print("Nome: "); f.setNome(scanner.nextLine()); //[cite: 9]
                System.out.print("CPF: "); f.setCpf(scanner.nextLine()); //[cite: 9]
                System.out.print("Cargo: "); f.setCargo(scanner.nextLine()); //[cite: 9]
                if (funcionarioService.cadastarFuncionario(f)) { //[cite: 2]
                    System.out.println("Funcionário cadastrado!");
                }
            }
            case "6" -> agendarConsultaForm();
            case "7" -> {
                System.out.print("ID da Consulta a Cancelar: ");
                int id = Integer.parseInt(scanner.nextLine());
                if (consultaService.cancelarConsulta(id)) { //[cite: 1]
                    System.out.println("Consulta cancelada com sucesso!");
                } else {
                    System.out.println("Consulta não encontrada.");
                }
            }
            case "0" -> usuarioLogado = null;
        }
    }

    private static void menuMedico() {
        System.out.println("1. Minhas Consultas"); //[cite: 1]
        System.out.println("2. Registrar Prontuário"); //[cite: 5]
        System.out.println("0. Logout");
        System.out.print("Opção: ");

        String op = scanner.nextLine();
        switch (op) {
            case "1" -> {
                System.out.print("Informe seu ID de Médico: ");
                int medId = Integer.parseInt(scanner.nextLine());
                List<Consulta> lista = consultaService.buscarPorMedico(medId); //[cite: 1]
                System.out.println("Total de consultas: " + lista.size());
            }
            case "2" -> {
                System.out.print("ID do Paciente: ");
                int pId = Integer.parseInt(scanner.nextLine());
                Paciente pac = pacienteService.buscarPorId(pId); //[cite: 4]

                System.out.print("Seu ID de Médico: ");
                int mId = Integer.parseInt(scanner.nextLine());
                Medico med = medicoService.buscarPorId(mId); //[cite: 3]

                if (pac == null || med == null) {
                    System.out.println("Paciente ou Médico não encontrado!");
                    return;
                }

                System.out.print("Diagnóstico: ");
                String diag = scanner.nextLine();
                System.out.print("Observações: ");
                String obs = scanner.nextLine();
                System.out.print("Prescrição: ");
                String presc = scanner.nextLine();

                Prontuario prontuario = new Prontuario(
                        prontuarioService.listarProntuarios().size() + 1, //[cite: 5]
                        pac, med, LocalDate.now(), diag, obs, presc //[cite: 12]
                );

                prontuarioService.cadastrarProntuario(prontuario); //[cite: 5]
                System.out.println("Prontuário registrado com sucesso!");
            }
            case "0" -> usuarioLogado = null;
        }
    }

    private static void menuPaciente() {
        System.out.println("1. Minhas Consultas"); //[cite: 1]
        System.out.println("2. Solicitante de Agendamento"); //[cite: 1]
        System.out.println("0. Logout");
        System.out.print("Opção: ");

        String op = scanner.nextLine();
        switch (op) {
            case "1" -> {
                System.out.print("Informe seu ID de Paciente: ");
                int pId = Integer.parseInt(scanner.nextLine());
                List<Consulta> lista = consultaService.buscarPorPaciente(pId); //[cite: 1]
                System.out.println("Você possui " + lista.size() + " consulta(s).");
            }
            case "2" -> agendarConsultaForm();
            case "0" -> usuarioLogado = null;
        }
    }

    private static void agendarConsultaForm() {
        Consulta c = new Consulta(); //
        c.setId(consultaService.listarConsultas().size() + 1); //[cite: 1]

        System.out.print("ID do Paciente: ");
        c.setPacienteId(Integer.parseInt(scanner.nextLine())); //[cite: 7]

        System.out.print("ID do Médico: ");
        c.setMedicoId(Integer.parseInt(scanner.nextLine())); //[cite: 7]

        System.out.print("Data (YYYY-MM-DD): ");
        c.setDataConsulta(LocalDate.parse(scanner.nextLine())); //[cite: 7]

        System.out.print("Hora (HH:MM): ");
        c.setHoraConsulta(LocalTime.parse(scanner.nextLine())); //[cite: 7]

        if (consultaService.agendarConsulta(c)) { //[cite: 1]
            System.out.println("Consulta agendada com sucesso!");
        } else {
            System.out.println("Erro: O médico já possui agendamento neste horário.");
        }
    }
}