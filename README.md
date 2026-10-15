# 🏥 Sistema de Gestão Hospitalar

Um sistema desktop completo em **Java Swing** com suporte a persistência em **MySQL/MariaDB** via **JDBC**, desenvolvido para gerenciar consultas, pacientes, médicos, funcionários e prontuários médicos com controle de acesso por perfil.

---

## 📋 Funcionalidades e Requisitos Atendidos

* **Controle de Acesso e Autenticação**: Sistema de login com validação de credenciais e separação por perfis (Paciente, Médico e Funcionário)[cite: 14, 16].
* **Gestão de Cadastros (CRUD Completo)**:
  * **Pacientes**: Cadastro de dados pessoais, contato e histórico de usuário[cite: 11, 15].
  * **Médicos**: Cadastro com CRM, especialidade e dados de contato[cite: 10].
  * **Funcionários**: Cadastro de colaboradores com cargos e permissões administrativas[cite: 9].
* **Agendamento de Consultas**: Marcação, remarcação e cancelamento com validação automática para impedir choque de horários para o mesmo médico[cite: 1, 7, 13].
* **Prontuário Eletrônico**: Registro de diagnósticos, observações clínicas e prescrições médicas por parte dos médicos[cite: 5, 12].

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem**: Java 17+
* **Interface Gráfica (GUI)**: Java Swing (Desktop Nativo)
* **Persistência de Dados**: JDBC (Java Database Connectivity)
* **Banco de Dados**: MySQL / MariaDB
* **Driver JDBC**: `mysql-connector-j`

---

## 📊 Diagrama de Casos de Uso

```mermaid
graph TD
    subgraph Sistema de Gestao Hospitalar
        UC1[Realizar Login]
        UC2[Cadastrar Novo Usuário]
        UC3[Gerenciar Pacientes - CRUD]
        UC4[Gerenciar Médicos - CRUD]
        UC5[Gerenciar Funcionários - CRUD]
        UC6[Agendar e Cancelar Consultas]
        UC7[Manter Prontuários Médicos]
    end

    U_NaoAut[Usuário Não Autenticado] --> UC1
    U_NaoAut --> UC2

    Func[Funcionário] --> UC1
    Func --> UC3
    Func --> UC4
    Func --> UC5
    Func --> UC6

    Med[Médico] --> UC1
    Med --> UC6
    Med --> UC7

    Pac[Paciente] --> UC1
    Pac --> UC6

sistema-hospitalar/
├── .gitignore
├── README.md
├── sql/
│   └── script_hospital.sql
└── src/
    ├── ConexaoDB.java
    ├── MapeadorUtil.java
    ├── model/
    │   ├── Consulta.java
    │   ├── Consultorio.java
    │   ├── Funcionario.java
    │   ├── Medico.java
    │   ├── Paciente.java
    │   ├── Prontuario.java
    │   ├── StatusConsulta.java
    │   └── Usuario.java
    ├── dao/
    │   ├── ConsultaDAO.java
    │   ├── ConsultorioDAO.java
    │   ├── FuncionarioDAO.java
    │   ├── MedicoDAO.java
    │   ├── PacienteDAO.java
    │   ├── ProntuarioDAO.java
    │   └── UsuarioDAO.java
    └── view/
        └── TelaPrincipal.java

