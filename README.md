## 📚 Sistema de Biblioteca Virtual

### 📖 Descrição do Projeto

O **Sistema de Biblioteca Virtual** é uma aplicação desenvolvida para gerenciar bibliotecas acadêmicas. O sistema permite o cadastro de usuários (alunos e professores) e livros, além de realizar operações como empréstimos, devoluções e reservas. Ele foi projetado com base nos princípios da Programação Orientada a Objetos (POO), utilizando Java como linguagem principal.

---

### 🚀 Funcionalidades

- **Cadastro de Usuários**:
  - **Alunos**: Nome, matrícula, e-mail, curso e ano de ingresso.
  - **Professores**: Nome, matrícula, e-mail, departamento e ano de contratação.
- **Cadastro de Livros**:
  - Informações como título, autor, ano de lançamento e tema.
- **Listagens**:
  - Usuários cadastrados.
  - Livros disponíveis, com filtros por título, autor ou tema.
  - Empréstimos realizados.
- **Operações**:
  - Empréstimo de livros.
  - Devolução de livros.
  - Reserva de livros.
- **Relatórios**:
  - Estatísticas sobre livros cadastrados, emprestados e reservados.
- **Persistência de Dados**:
  - Salvamento e carregamento de dados em arquivos para garantir a continuidade do sistema.

---

### 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java
- **Paradigma**: Programação Orientada a Objetos (POO)
- **Persistência**: Serialização de objetos com `Serializable`
  - Os dados são salvos automaticamente no arquivo dados_biblioteca.dat ao sair do sistema.
- **Ferramentas**: IntelliJ IDEA

---

### 📂 Estrutura do Projeto

```plaintext
src/
├── Aluno.java                  # Classe que representa um aluno
├── Professor.java              # Classe que representa um professor
├── Usuario.java                # Classe abstrata base para usuários
├── Livro.java                  # Classe que representa um livro
├── Emprestimo.java             # Classe que gerencia empréstimos
├── SistemaBiblioteca.java      # Classe principal do sistema
├── Menu.java                   # Classe responsável pela interação com o usuário
├── Utilidades.java             # Classe para salvar e carregar dados
├── Emprestavel.java            # Interface para livros emprestáveis
├── Reservavel.java             # Interface para livros reserváveis
├── LivroNaoEncontradoException.java  # Exceção personalizada para livros
├── UsuarioNaoEncontradoException.java # Exceção personalizada para usuários
├── EmprestimoNaoPermitidoException.java # Exceção para empréstimos inválidos
├── LivroReservadoException.java # Exceção para livros reservados
└── Main.java                   # Classe principal que inicializa o sistema
```

---
#### Trabalho desenvolvido na disciplina de Programação Orientada a Objetos

_Faculdade de Computação (FACOM)_

_Universidade Federal de Uberlândia (UFU)_

**GRUPO 2 - Integrantes**
1. Felipe Sergio Da Silva Freitas - 12221BSI223
2. Italo Carlos de Paula Gomes - 12221BSI264
3. Pedro Henrique Da Silva Freitas - 12411BSI377
4. Tainá Souza Peixoto - 12311BSI316
