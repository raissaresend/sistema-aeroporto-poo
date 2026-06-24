/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airportsystem;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author RAISSA
 */
public class Programa {

    private PassageiroDAO passageiroDAO = new PassageiroDAO();
    private AeroportoDAO aeroportoDAO = new AeroportoDAO();
    private CiaDAO ciaDAO = new CiaDAO();
    private VooDAO vooDAO = new VooDAO();
    private TicketDAO ticketDAO = new TicketDAO();
    private VooAssentosDAO vooAssentosDAO = new VooAssentosDAO();
    private CheckinDAO checkinDAO = new CheckinDAO();
    private DespachoBagagemDAO despachoBagagemDAO = new DespachoBagagemDAO();
    private BoardingPassDAO boardingPassDAO = new BoardingPassDAO();
    private ImportadorPassageiro importadorPassageiro = new ImportadorPassageiro();
    private GeradorPDF geradorPDF = new GeradorPDF();

    public Programa() {
        menuPerfil();
    }

    private void menuPerfil() {
        int opcao = 0;
        do {
            String menu = "Bem-vindo ao Sistema de Aeroporto\n\n"
                    + "Você é:\n"
                    + "1. Administrador\n"
                    + "2. Cliente\n"
                    + "3. Sair do Sistema\n\n"
                    + "Escolha uma opção:";

            String opcaoStr = JOptionPane.showInputDialog(null, menu);

            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 3;
                } else {
                    opcao = 0;
                }
            }

            switch (opcao) {
                case 1:
                    this.fazerLoginAdmin();
                    break;
                case 2:
                    this.menuCliente();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        } while (opcao != 3);
    }

    // ========================================
    // LOGIN DO ADMINISTRADOR
    // ========================================
    private void fazerLoginAdmin() {
        String login = JOptionPane.showInputDialog(null, "Login (admin):");
        if (login == null) {
            return;
        }
        String senha = JOptionPane.showInputDialog(null, "Senha (admin):");
        if (senha == null) {
            return;
        }

        if (login.equals("admin") && senha.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Bem-vindo, Administrador!");
            menuAdministrador();
        } else {
            JOptionPane.showMessageDialog(null, "Login ou senha de admin inválidos.");
        }
    }

    // ========================================
    // MENU DO ADMINISTRADOR
    // ========================================
    private void menuAdministrador() {
        int opcao = 0;
        do {
            String menu = "Painel do Administrador\n\n"
                    + "--- Cadastros ---\n"
                    + "1. Gerenciar Passageiros\n"
                    + "2. Gerenciar Aeroportos\n"
                    + "3. Gerenciar Companhias Aéreas\n"
                    + "4. Gerenciar Voos\n"
                    + "5. Gerenciar Assentos de Voos\n"
                    + "6. Gerenciar Tickets\n"
                    + "7. Gerenciar Boarding Pass\n"
                    + "\n--- Operações ---\n"
                    + "8. Realizar Check-in (Operacional)\n"
                    + "9. Despachar Bagagem (Operacional)\n"
                    + "10. Buscar Voos (Operacional)\n"
                    + "\n--- Importação/Exportação ---\n"
                    + "11. Importar Passageiros de TXT\n"
                    + "12. Gerar Arquivo TXT de Exemplo\n"
                    + "13. Gerar Relatórios em PDF\n"
                    + "\n--- Sistema ---\n"
                    + "14. Relatórios Gerenciais\n"
                    + "15. Logout (Voltar ao menu principal)\n\n"
                    + "Escolha uma opção:";

            String opcaoStr = JOptionPane.showInputDialog(null, menu);

            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (NumberFormatException e) {
                if (opcaoStr == null) {
                    opcao = 15;
                } else {
                    opcao = 0;
                }
            }

            switch (opcao) {
                case 1:
                    this.menuPassageiros();
                    break;
                case 2:
                    this.menuAeroportos();
                    break;
                case 3:
                    this.menuCias();
                    break;
                case 4:
                    this.menuVoos();
                    break;
                case 5:
                    this.menuVooAssentos();
                    break;
                case 6:
                    this.menuTickets();
                    break;
                case 7:
                    this.menuBoardingPass();
                    break;
                case 8:
                    this.operacaoCheckin();
                    break;
                case 9:
                    this.operacaoDespachoBagagem();
                    break;
                case 10:
                    this.operacaoBuscarVoos();
                    break;
                case 11:
                    this.importarPassageirosDeTXT();
                    break;
                case 12:
                    this.gerarArquivoExemploTXT();
                    break;
                case 13:
                    this.menuRelatoriosPDF();
                    break;
                case 14:
                    this.menuRelatorios();
                    break;
                case 15:
                    JOptionPane.showMessageDialog(null, "Fazendo logout...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 15);
    }

    // ========================================
    // MENU DO CLIENTE
    // ========================================
    private void menuCliente() {
        int opcao = 0;
        do {
            String menu = "Painel do Cliente\n\n"
                    + "O que você deseja fazer?\n\n"
                    + "1. Cadastrar-me (Novo Passageiro)\n"
                    + "2. Buscar Voos (por data)\n"
                    + "3. Comprar Passagem\n"
                    + "4. Fazer Check-in (Precisa do Cód. Ticket)\n"
                    + "5. Despachar Bagagem (Precisa do Cód. Ticket)\n"
                    + "6. Voltar ao menu principal\n\n"
                    + "Escolha uma opção:";

            String opcaoStr = JOptionPane.showInputDialog(null, menu);

            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 6;
                } else {
                    opcao = 0;
                }
            }

            switch (opcao) {
                case 1:
                    this.cadastrarPassageiro();
                    break;
                case 2:
                    this.operacaoBuscarVoosPorData();
                    break;
                case 3:
                    this.operacaoBuscarVoos();
                    break;
                case 4:
                    this.operacaoCheckin();
                    break;
                case 5:
                    this.operacaoDespachoBagagem();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 6);
    }

    // ========================================
    // SUBMENU: PASSAGEIROS
    // ========================================
    private void menuPassageiros() {
        int opcao = 0;
        do {
            String menu = "Gerenciar Passageiros\n\n"
                    + "1. Cadastrar Novo Passageiro\n"
                    + "2. Listar Todos os Passageiros\n"
                    + "3. Alterar Passageiro (p/ nome)\n"
                    + "4. Remover Passageiro (p/ nome)\n"
                    + "5. Voltar ao Menu Admin\n\n"
                    + "Escolha uma opção:";
            String opcaoStr = JOptionPane.showInputDialog(null, menu);
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 5;
                } else {
                    opcao = 0;
                }
            }
            switch (opcao) {
                case 1:
                    this.cadastrarPassageiro();
                    break;
                case 2:
                    this.listarPassageiros();
                    break;
                case 3:
                    this.alterarPassageiro();
                    break;
                case 4:
                    this.removerPassageiro();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 5);
    }

    private void cadastrarPassageiro() {
        listarPassageiros();
        String nome = JOptionPane.showInputDialog(null, "Digite o nome:");
        if (nome == null || nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado. Nome é obrigatório.");
            return;
        }
        String doc = JOptionPane.showInputDialog(null, "Digite o documento (CPF):");
        String login = JOptionPane.showInputDialog(null, "Digite o login:");
        String senha = JOptionPane.showInputDialog(null, "Digite a senha:");
        String nascStr = JOptionPane.showInputDialog(null, "Data de Nascimento (dd/mm/aaaa):");

        Passageiro p = new Passageiro();
        p.setNome(nome);
        p.setDocumento(doc);
        p.setLogin(login);
        p.setSenha(senha);

        if (nascStr != null && !nascStr.isEmpty()) {
            try {
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                p.setNascimento(LocalDate.parse(nascStr, formatador));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de data '" + nascStr + "' inválido! Nascimento não foi salvo.");
            }
        }

        boolean sucesso = passageiroDAO.adiciona(p);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Passageiro cadastrado com sucesso!\nID: " + p.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível cadastrar.");
        }
    }

    private void listarPassageiros() {
        List<Passageiro> lista = passageiroDAO.mostrarTodos();
        String textoLista = "Lista de Passageiros:\n\n";

        if (lista.isEmpty()) {
            textoLista += "Nenhum passageiro cadastrado.";
        } else {
            for (Passageiro p : lista) {
                textoLista += p.toString() + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoLista));
    }

    private void alterarPassageiro() {
        listarPassageiros();
        String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome do passageiro a alterar:");
        if (nomeBusca == null) {
            return;
        }
        if (passageiroDAO.buscaNome(nomeBusca) == null) {
            JOptionPane.showMessageDialog(null, "Erro: Passageiro '" + nomeBusca + "' não encontrado.");
            return;
        }
        String novoNome = JOptionPane.showInputDialog(null, "Digite o NOVO nome:");
        if (novoNome == null) {
            return;
        }
        boolean sucesso = passageiroDAO.alterarNome(nomeBusca, novoNome);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao alterar nome.");
        }
    }

    private void removerPassageiro() {
        listarPassageiros();
        String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome do passageiro a REMOVER:");
        if (nomeBusca == null) {
            return;
        }
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            boolean sucesso = passageiroDAO.remover(nomeBusca);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover.");
            }
        }
    }

    // ========================================
    // SUBMENU: AEROPORTOS
    // ========================================
    private void menuAeroportos() {
        int opcao = 0;
        do {
            String menu = "Gerenciar Aeroportos\n\n"
                    + "1. Cadastrar Novo Aeroporto\n"
                    + "2. Listar Todos os Aeroportos\n"
                    + "3. Alterar Aeroporto (p/ nome)\n"
                    + "4. Remover Aeroporto (p/ nome)\n"
                    + "5. Voltar ao Menu Admin\n\n"
                    + "Escolha uma opção:";
            String opcaoStr = JOptionPane.showInputDialog(null, menu);
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 5;
                } else {
                    opcao = 0;
                }
            }
            switch (opcao) {
                case 1:
                    this.cadastrarAeroporto();
                    break;
                case 2:
                    this.listarAeroportos();
                    break;
                case 3:
                    this.alterarAeroporto();
                    break;
                case 4:
                    this.removerAeroporto();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 5);
    }

    private void cadastrarAeroporto() {
        listarAeroportos();
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do Aeroporto:");
        if (nome == null || nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            return;
        }
        String abrev = JOptionPane.showInputDialog(null, "Digite a abreviação (Ex: CGH):");
        String cidade = JOptionPane.showInputDialog(null, "Digite a cidade:");

        Aeroporto a = new Aeroporto();
        a.setNome(nome);
        a.setAbreviacao(abrev);
        a.setCidade(cidade);

        boolean sucesso = aeroportoDAO.adiciona(a);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Aeroporto cadastrado com sucesso!\nID: " + a.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível cadastrar.");
        }
    }

    private void listarAeroportos() {
        List<Aeroporto> lista = aeroportoDAO.mostrarTodos();
        String textoLista = "Lista de Aeroportos:\n\n";

        if (lista.isEmpty()) {
            textoLista += "Nenhum aeroporto cadastrado.";
        } else {
            for (Aeroporto a : lista) {
                textoLista += a.toString() + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoLista));
    }

    private void alterarAeroporto() {
        listarAeroportos();
        String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome do aeroporto a alterar:");
        if (nomeBusca == null) {
            return;
        }
        if (aeroportoDAO.buscaNome(nomeBusca) == null) {
            JOptionPane.showMessageDialog(null, "Erro: Aeroporto '" + nomeBusca + "' não encontrado.");
            return;
        }
        String novoNome = JOptionPane.showInputDialog(null, "Digite o NOVO nome:");
        if (novoNome == null) {
            return;
        }
        boolean sucesso = aeroportoDAO.alterarNome(nomeBusca, novoNome);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao alterar.");
        }
    }

    private void removerAeroporto() {
        listarAeroportos();
        String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome do aeroporto a REMOVER:");
        if (nomeBusca == null) {
            return;
        }
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            boolean sucesso = aeroportoDAO.remover(nomeBusca);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover.");
            }
        }
    }

    // ========================================
    // SUBMENU: COMPANHIAS AÉREAS
    // ========================================
    private void menuCias() {
        int opcao = 0;
        do {
            String menu = "Gerenciar Companhias Aéreas\n\n"
                    + "1. Cadastrar Nova Companhia\n"
                    + "2. Listar Todas as Companhias\n"
                    + "3. Alterar Companhia (p/ nome)\n"
                    + "4. Remover Companhia (p/ nome)\n"
                    + "5. Voltar ao Menu Admin\n\n"
                    + "Escolha uma opção:";
            String opcaoStr = JOptionPane.showInputDialog(null, menu);
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 5;
                } else {
                    opcao = 0;
                }
            }
            switch (opcao) {
                case 1:
                    this.cadastrarCia();
                    break;
                case 2:
                    this.listarCias();
                    break;
                case 3:
                    this.alterarCia();
                    break;
                case 4:
                    this.removerCia();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 5);
    }

    private void cadastrarCia() {
        listarCias();
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da Companhia:");
        if (nome == null || nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            return;
        }
        String abrev = JOptionPane.showInputDialog(null, "Digite a abreviação (Ex: G3):");

        Cia c = new Cia();
        c.setNome(nome);
        c.setAbreviacao(abrev);

        boolean sucesso = ciaDAO.adiciona(c);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Companhia cadastrada com sucesso!\nID: " + c.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível cadastrar.");
        }
    }

    private void listarCias() {
        List<Cia> lista = ciaDAO.mostrarTodos();
        String textoLista = "Lista de Companhias Aéreas:\n\n";

        if (lista.isEmpty()) {
            textoLista += "Nenhuma companhia cadastrada.";
        } else {
            for (Cia c : lista) {
                textoLista += c.toString() + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoLista));
    }

    private void alterarCia() {
        listarCias();
        String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome da companhia a alterar:");
        if (nomeBusca == null) {
            return;
        }
        if (ciaDAO.buscaPorNomeOuAbreviacao(nomeBusca) == null) {
            JOptionPane.showMessageDialog(null, "Erro: Companhia '" + nomeBusca + "' não encontrada.");
            return;
        }
        String novoNome = JOptionPane.showInputDialog(null, "Digite o NOVO nome:");
        if (novoNome == null) {
            return;
        }
        boolean sucesso = ciaDAO.alterarNome(nomeBusca, novoNome);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao alterar.");
        }
    }

    private void removerCia() {
        listarCias();
        String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome da companhia a REMOVER:");
        if (nomeBusca == null) {
            return;
        }
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            boolean sucesso = ciaDAO.remover(nomeBusca);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Removida com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover.");
            }
        }
    }

    // ========================================
    // SUBMENU: VOOS
    // ========================================
    private void menuVoos() {
        int opcao = 0;
        do {
            String menu = "Gerenciar Voos\n\n"
                    + "1. Cadastrar Novo Voo\n"
                    + "2. Listar Todos os Voos\n"
                    + "3. Remover Voo (p/ ID)\n"
                    + "4. Voltar ao Menu Admin\n\n"
                    + "Escolha uma opção:";
            String opcaoStr = JOptionPane.showInputDialog(null, menu);
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 4;
                } else {
                    opcao = 0;
                }
            }
            switch (opcao) {
                case 1:
                    this.cadastrarVoo();
                    break;
                case 2:
                    this.listarVoos();
                    break;
                case 3:
                    this.removerVoo();
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 4);
    }

    private void cadastrarVoo() {
        listarVoos();
        JOptionPane.showMessageDialog(null, "A seguir, veja os Aeroportos disponíveis para Origem/Destino.");
        listarAeroportos();
        JOptionPane.showMessageDialog(null, "A seguir, veja as Companhias Aéreas disponíveis.");
        listarCias();

        String origem = JOptionPane.showInputDialog(null, "Digite a Origem (Ex: CGH ou nome):");
        if (origem == null) {
            return;
        }
        String destino = JOptionPane.showInputDialog(null, "Digite o Destino (Ex: SDU ou nome):");
        if (destino == null) {
            return;
        }

        Cia ciaSelecionada = null;
        long ciaId = 0;
        try {
            String ciaIdStr = JOptionPane.showInputDialog(null, "Digite o ID da Companhia Aérea:");
            if (ciaIdStr == null) {
                return;
            }
            ciaId = Long.parseLong(ciaIdStr);
            ciaSelecionada = ciaDAO.buscaPorId(ciaId);
            if (ciaSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Erro: Companhia com ID " + ciaId + " não encontrada.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID de Companhia inválido.");
            return;
        }

        long capacidade = 0;
        try {
            String capacidadeStr = JOptionPane.showInputDialog(null, "Capacidade de passageiros (Ex: 15):");
            if (capacidadeStr != null) {
                capacidade = Long.parseLong(capacidadeStr);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Capacidade inválida.");
        }

        String[] estadosPossiveis = {"Programado", "Embarque", "Decolado", "Atrasado", "Cancelado"};
        String estado = (String) JOptionPane.showInputDialog(null, "Selecione o estado do voo:", "Estado do Voo",
                JOptionPane.QUESTION_MESSAGE, null, estadosPossiveis, estadosPossiveis[0]);
        if (estado == null) {
            estado = "Programado";
        }

        LocalDateTime dataVoo = null;
        String dataVooStr = JOptionPane.showInputDialog(null, "Data e hora do Voo (dd/MM/yyyy HH:mm):");
        if (dataVooStr != null && !dataVooStr.isEmpty()) {
            try {
                dataVoo = LocalDateTime.parse(dataVooStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de data/hora inválido.");
            }
        }

        LocalTime duracaoVoo = null;
        String duracaoVooStr = JOptionPane.showInputDialog(null, "Duração do Voo (HH:mm):");
        if (duracaoVooStr != null && !duracaoVooStr.isEmpty()) {
            try {
                duracaoVoo = LocalTime.parse(duracaoVooStr, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de duração inválido.");
            }
        }

        Voo v = new Voo();
        v.setOrigem(origem);
        v.setDestino(destino);
        v.setCia(ciaSelecionada.getAbreviacao());
        v.setCapacidade(capacidade);
        v.setStatus(estado);
        v.setData(dataVoo);
        v.setDuracao(duracaoVoo);

        boolean sucesso = vooDAO.adiciona(v);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Voo cadastrado com sucesso!\nID: " + v.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível cadastrar.");
        }
    }

    private void listarVoos() {
        List<Voo> lista = vooDAO.mostrarTodos();
        String textoLista = "Lista de Voos:\n\n";

        if (lista.isEmpty()) {
            textoLista += "Nenhum voo cadastrado.";
        } else {
            for (Voo v : lista) {
                textoLista += v.toString() + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoLista));
    }

    private void removerVoo() {
        listarVoos();
        long idBusca = 0;
        try {
            String idStr = JOptionPane.showInputDialog(null, "Digite o ID do voo a REMOVER:");
            if (idStr == null) {
                return;
            }
            idBusca = Long.parseLong(idStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
            return;
        }
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o Voo ID " + idBusca + "?",
                "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            boolean sucesso = vooDAO.removerPorId(idBusca);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Voo removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover voo.");
            }
        }
    }

    // ========================================
    // SUBMENU: VOO ASSENTOS
    // ========================================
    private void menuVooAssentos() {
        int opcao = 0;
        do {
            String menu = "Gerenciar Assentos de Voos\n\n"
                    + "1. Listar Todos os Assentos Ocupados\n"
                    + "2. Remover Assento (p/ ID)\n"
                    + "3. Voltar ao Menu Admin\n\n"
                    + "Escolha uma opção:";
            String opcaoStr = JOptionPane.showInputDialog(null, menu);
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 3;
                } else {
                    opcao = 0;
                }
            }
            switch (opcao) {
                case 1:
                    this.listarVooAssentos();
                    break;
                case 2:
                    this.removerVooAssento();
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 3);
    }

    private void listarVooAssentos() {
        List<VooAssentos> lista = vooAssentosDAO.mostrarTodos();
        String textoLista = "Lista de Assentos Ocupados:\n\n";

        if (lista.isEmpty()) {
            textoLista += "Nenhum assento ocupado.";
        } else {
            for (VooAssentos va  : lista) {
                textoLista += va.toString() + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoLista));
    }

    private void removerVooAssento() {
        listarVooAssentos();
        long idBusca = 0;
        try {
            String idStr = JOptionPane.showInputDialog(null, "Digite o ID do registro de Assento a REMOVER:");
            if (idStr == null) {
                return;
            }
            idBusca = Long.parseLong(idStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
            return;
        }
        boolean sucesso = vooAssentosDAO.removerPorId(idBusca);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Assento removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao remover.");
        }
    }

    // ========================================
    // SUBMENU: TICKETS
    // ========================================
    private void menuTickets() {
        int opcao = 0;
        do {
            String menu = "Gerenciar Tickets\n\n"
                    + "1. Listar Todos os Tickets Vendidos\n"
                    + "2. Remover Ticket (p/ ID)\n"
                    + "3. Voltar ao Menu Admin\n\n"
                    + "Escolha uma opção:";
            String opcaoStr = JOptionPane.showInputDialog(null, menu);
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 3;
                } else {
                    opcao = 0;
                }
            }
            switch (opcao) {
                case 1:
                    this.listarTickets();
                    break;
                case 2:
                    this.removerTicket();
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 3);
    }

    private void listarTickets() {
        List<Ticket> lista = ticketDAO.mostrarTodos();
        String textoLista = "Lista de Tickets Vendidos:\n\n";

        if (lista.isEmpty()) {
            textoLista += "Nenhum ticket vendido.";
        } else {
            for (Ticket t : lista) {
                textoLista += t.toString() + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoLista));
    }

    private void removerTicket() {
        listarTickets();
        long idBusca = 0;
        try {
            String idStr = JOptionPane.showInputDialog(null, "Digite o ID do Ticket a REMOVER:");
            if (idStr == null) {
                return;
            }
            idBusca = Long.parseLong(idStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
            return;
        }
        boolean sucesso = ticketDAO.removerPorId(idBusca);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Ticket removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao remover.");
        }
    }

    // ========================================
    // SUBMENU: BOARDING PASS
    // ========================================
    private void menuBoardingPass() {
        int opcao = 0;
        do {
            String menu = "Gerenciar Boarding Pass\n\n"
                    + "1. Listar Todos os Boarding Pass Gerados\n"
                    + "2. Remover Boarding Pass (p/ ID)\n"
                    + "3. Voltar ao Menu Admin\n\n"
                    + "Escolha uma opção:";
            String opcaoStr = JOptionPane.showInputDialog(null, menu);
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 3;
                } else {
                    opcao = 0;
                }
            }
            switch (opcao) {
                case 1:
                    this.listarBoardingPass();
                    break;
                case 2:
                    this.removerBoardingPass();
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 3);
    }

    private void listarBoardingPass() {
        List<BoardingPass> lista = boardingPassDAO.mostrarTodos();
        String textoLista = "Lista de Boarding Pass Gerados:\n\n";

        if (lista.isEmpty()) {
            textoLista += "Nenhum Boarding Pass gerado.";
        } else {
            for (BoardingPass bp : lista) {
                Ticket ticket = ticketDAO.buscaPorId(bp.getTicketId());
                Passageiro p = passageiroDAO.buscaPorId(bp.getPassageiroId());
                Voo v = vooDAO.buscaPorId(bp.getVooId());
                VooAssentos va  = vooAssentosDAO.buscaPorId(bp.getAssentoId());

                String ticketCodigo = (ticket != null) ? String.valueOf(ticket.getCodigo()) : "N/A";
                String pNome = (p != null) ? p.getNome() : "N/A";
                String vInfo = "N/A";
                if (v != null) {
                    vInfo = "ID " + v.getId() + ": " + v.getOrigem() + "->" + v.getDestino();
                }
                String assentoCod = (va  != null) ? String.valueOf(va.getCodAssento()) : "N/A";

                textoLista += "BP ID: " + bp.getId() + " | Ticket: " + ticketCodigo
                        + " | Passageiro: " + pNome + " | Voo: " + vInfo
                        + " | Assento: " + assentoCod + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoLista));
    }

    private void removerBoardingPass() {
        listarBoardingPass();
        long idBusca = 0;
        try {
            String idStr = JOptionPane.showInputDialog(null, "Digite o ID do Boarding Pass a REMOVER:");
            if (idStr == null) {
                return;
            }
            idBusca = Long.parseLong(idStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
            return;
        }
        boolean sucesso = boardingPassDAO.removerPorId(idBusca);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Boarding Pass removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao remover.");
        }
    }

    // ========================================
    // OPERAÇÃO: CHECK-IN
    // ========================================
    private void operacaoCheckin() {
        Ticket ticket = null;
        Passageiro passageiro = null;
        Voo voo = null;
        long codigoTicket = 0;

        try {
            String ticketStr = JOptionPane.showInputDialog(null, "Digite o CÓDIGO do Ticket:");
            if (ticketStr == null) {
                return;
            }
            codigoTicket = Long.parseLong(ticketStr);
            ticket = ticketDAO.buscaPorCodigo(codigoTicket);
            if (ticket == null) {
                JOptionPane.showMessageDialog(null, "Erro: Ticket com código " + codigoTicket + " não encontrado.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Código de ticket inválido.");
            return;
        }

        String documento = JOptionPane.showInputDialog(null, "Digite o DOCUMENTO do passageiro (CPF):");
        if (documento == null) {
            return;
        }

        passageiro = passageiroDAO.buscaPorId(ticket.getPassageiroId());
        if (passageiro == null) {
            JOptionPane.showMessageDialog(null, "Erro: Passageiro não encontrado.");
            return;
        }

        if (passageiro.getDocumento() == null || !passageiro.getDocumento().equals(documento)) {
            JOptionPane.showMessageDialog(null, "Erro: O documento não pertence ao dono deste ticket.");
            return;
        }

        voo = vooDAO.buscaPorId(ticket.getVooId());
        if (voo == null) {
            JOptionPane.showMessageDialog(null, "Erro: Voo não encontrado.");
            return;
        }

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataVoo = voo.getData();
        if (dataVoo == null) {
            JOptionPane.showMessageDialog(null, "Erro: Este voo não possui uma data de partida cadastrada.");
            return;
        }

        LocalDateTime inicioCheckin = dataVoo.minusHours(24);
        if (agora.isBefore(inicioCheckin)) {
            JOptionPane.showMessageDialog(null, "Check-in falhou.\nO check-in para este voo só abre em "
                    + inicioCheckin.format(DateTimeFormatter.ofPattern("dd/MM HH:mm")));
            return;
        }

        if (agora.isAfter(dataVoo)) {
            JOptionPane.showMessageDialog(null, "Check-in falhou.\nEste voo já partiu em "
                    + dataVoo.format(DateTimeFormatter.ofPattern("dd/MM HH:mm")));
            return;
        }

        Checkin checkin = new Checkin();
        checkin.setTicketId(ticket.getId());
        checkin.setDocumento(documento);
        boolean sucessoCheckin = checkinDAO.adiciona(checkin);

        if (!sucessoCheckin) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o registro de check-in.");
            return;
        }

        VooAssentos assento = vooAssentosDAO.buscaPorPassageiroEVoo(passageiro.getId(), voo.getId());
        if (assento == null) {
            JOptionPane.showMessageDialog(null, "Erro: Não foi encontrado um assento para este passageiro/voo.");
            return;
        }

        BoardingPass bp = new BoardingPass();
        bp.setTicketId(ticket.getId());
        bp.setPassageiroId(passageiro.getId());
        bp.setVooId(voo.getId());
        bp.setAssentoId(assento.getId());

        boolean sucessoBP = boardingPassDAO.adiciona(bp);
        if (!sucessoBP) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o Boarding Pass.");
            return;
        }

        String boardingPassTexto = "=== BOARDING PASS (ID: " + bp.getId() + ") ===\n\n"
                + "PASSAGEIRO: " + passageiro.getNome() + "\n"
                + "DOCUMENTO: " + passageiro.getDocumento() + "\n"
                + "TICKET: " + ticket.getCodigo() + "\n\n"
                + "VOO: " + voo.getId() + " (" + voo.getCia() + ")\n"
                + "DE: " + voo.getOrigem() + " -> PARA: " + voo.getDestino() + "\n"
                + "DATA: " + voo.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n"
                + "ASSENTO: " + assento.getCodAssento() + "\n"
                + "STATUS: CHECK-IN REALIZADO\n";

        JOptionPane.showMessageDialog(null, new JTextArea(boardingPassTexto));
    }

    // ========================================
    // OPERAÇÃO: DESPACHO DE BAGAGEM
    // ========================================
    private void operacaoDespachoBagagem() {
        Ticket ticket = null;
        Passageiro passageiro = null;
        long codigoTicket = 0;

        try {
            String ticketStr = JOptionPane.showInputDialog(null, "Digite o CÓDIGO do Ticket:");
            if (ticketStr == null) {
                return;
            }
            codigoTicket = Long.parseLong(ticketStr);
            ticket = ticketDAO.buscaPorCodigo(codigoTicket);
            if (ticket == null) {
                JOptionPane.showMessageDialog(null, "Erro: Ticket com código " + codigoTicket + " não encontrado.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Código de ticket inválido.");
            return;
        }

        String documento = JOptionPane.showInputDialog(null, "Confirme o DOCUMENTO do passageiro (CPF):");
        if (documento == null) {
            return;
        }

        passageiro = passageiroDAO.buscaPorId(ticket.getPassageiroId());
        if (passageiro == null) {
            JOptionPane.showMessageDialog(null, "Erro: Passageiro não encontrado.");
            return;
        }

        if (passageiro.getDocumento() == null || !passageiro.getDocumento().equals(documento)) {
            JOptionPane.showMessageDialog(null, "Erro: O documento não pertence ao dono deste ticket.");
            return;
        }

        DespachoBagagem despacho = new DespachoBagagem();
        despacho.setTicketId(ticket.getId());
        despacho.setDocumento(documento);

        boolean sucesso = despachoBagagemDAO.adiciona(despacho);
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Bagagem despachada com sucesso!\n\n"
                    + "Passageiro: " + passageiro.getNome() + "\n"
                    + "Ticket: " + ticket.getCodigo() + "\n"
                    + "ID Despacho: " + despacho.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o despacho.");
        }
    }

    // ========================================
    // OPERAÇÃO: BUSCAR VOOS (para compra)
    // ========================================
    private void operacaoBuscarVoos() {
        String origem = JOptionPane.showInputDialog(null, "Digite a cidade ou sigla de ORIGEM:");
        if (origem == null || origem.isEmpty()) {
            return;
        }
        String destino = JOptionPane.showInputDialog(null, "Digite a cidade ou sigla de DESTINO:");
        if (destino == null || destino.isEmpty()) {
            return;
        }

        List<Voo> voosEncontrados = vooDAO.buscarVoos(origem, destino);

        String textoBusca = "Voos encontrados de " + origem + " para " + destino + ":\n\n";

        if (voosEncontrados.isEmpty()) {
            textoBusca += "Nenhum voo encontrado para este trajeto.";
            JOptionPane.showMessageDialog(null, new JTextArea(textoBusca));
            return;
        }

        for (Voo v : voosEncontrados) {
            textoBusca += "ID: " + v.getId()
                    + " | Cia: " + v.getCia()
                    + " | Data: " + ((v.getData() != null) ? v.getData().format(DateTimeFormatter.ofPattern("dd/MM HH:mm")) : "N/A")
                    + " | Status: " + v.getStatus()
                    + " | Vagas: " + v.getCapacidade()
                    + "\n";
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoBusca));

        int confirma = JOptionPane.showConfirmDialog(null,
                "Deseja comprar uma passagem para um desses voos?",
                "Comprar Itinerário",
                JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            this.comprarPassagem(voosEncontrados);
        }
    }

    // ========================================
    // OPERAÇÃO: BUSCAR VOOS POR DATA
    // ========================================
    private void operacaoBuscarVoosPorData() {
        String dataStr = JOptionPane.showInputDialog(null, "Digite a data da busca (dd/MM/yyyy):");
        if (dataStr == null || dataStr.isEmpty()) {
            return;
        }

        LocalDate dataBusca;
        try {
            dataBusca = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        List<Voo> voosEncontrados = vooDAO.buscarVoosPorData(dataBusca);

        String textoBusca = "Voos encontrados para " + dataStr + ":\n\n";

        if (voosEncontrados.isEmpty()) {
            textoBusca += "Nenhum voo encontrado para esta data.";
        } else {
            for (Voo v : voosEncontrados) {
                textoBusca += v.toString() + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoBusca));
    }

    // ========================================
    // COMPRAR PASSAGEM
    // ========================================
    private void comprarPassagem(List<Voo> voosDisponiveis) {
        Voo vooEscolhido = null;
        long vooId = 0;

        try {
            String vooIdStr = JOptionPane.showInputDialog(null, "Digite o ID do Voo escolhido:");
            if (vooIdStr == null) {
                return;
            }
            vooId = Long.parseLong(vooIdStr);

            for (Voo v : voosDisponiveis) {
                if (v.getId() == vooId) {
                    vooEscolhido = v;
                    break;
                }
            }

            if (vooEscolhido == null) {
                JOptionPane.showMessageDialog(null, "ID de voo inválido ou não pertence à busca.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID de voo inválido.");
            return;
        }

        listarPassageiros();
        Passageiro passageiroEscolhido = null;
        try {
            String pasIdStr = JOptionPane.showInputDialog(null, "Digite o ID do Passageiro:");
            if (pasIdStr == null) {
                return;
            }
            long pasId = Long.parseLong(pasIdStr);
            passageiroEscolhido = passageiroDAO.buscaPorId(pasId);
            if (passageiroEscolhido == null) {
                JOptionPane.showMessageDialog(null, "ID de passageiro não encontrado.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID de passageiro inválido.");
            return;
        }

        if (vooEscolhido.getCapacidade() <= 0) {
            JOptionPane.showMessageDialog(null, "Desculpe, este voo (" + vooEscolhido.getId() + ") está lotado!");
            return;
        }

        // Atualizar capacidade do voo
        vooDAO.atualizarCapacidade(vooEscolhido.getId(), vooEscolhido.getCapacidade() - 1);

        // Criar ticket
        Ticket ticket = new Ticket();
        ticket.setValor(500.00);
        ticket.setVooId(vooEscolhido.getId());
        ticket.setPassageiroId(passageiroEscolhido.getId());

        // Gerar código único
        long codigo = System.currentTimeMillis() % 1000000;
        ticket.setCodigo(codigo);

        boolean ticketOk = ticketDAO.adiciona(ticket);

        // Criar assento
        VooAssentos assento = new VooAssentos();
        assento.setVooId(vooEscolhido.getId());
        assento.setPassageiroId(passageiroEscolhido.getId());
        assento.setCodAssento(System.currentTimeMillis() % 100);

        boolean assentoOk = vooAssentosDAO.adiciona(assento);

        if (ticketOk && assentoOk) {
            JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!\n\n"
                    + "Passageiro: " + passageiroEscolhido.getNome() + "\n"
                    + "Voo: " + vooEscolhido.getOrigem() + " -> " + vooEscolhido.getDestino() + "\n"
                    + "TICKET CÓDIGO: " + ticket.getCodigo() + "\n"
                    + "ASSENTO CÓDIGO: " + assento.getCodAssento());
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o ticket ou assento.");
        }
    }

    // ========================================
    // SUBMENU: RELATÓRIOS
    // ========================================
    private void menuRelatorios() {
        int opcao = 0;
        do {
            String menu = "Menu Relatórios Gerenciais\n\n"
                    + "1. Passageiros que SAÍRAM de um aeroporto\n"
                    + "2. Passageiros que CHEGARAM a um aeroporto\n"
                    + "3. Receita por Companhia Aérea (por período)\n"
                    + "4. Voltar ao Menu Admin\n\n"
                    + "Escolha uma opção:";
            String opcaoStr = JOptionPane.showInputDialog(null, menu);
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 4;
                } else {
                    opcao = 0;
                }
            }
            switch (opcao) {
                case 1:
                    this.relatorioPassageirosPorAeroporto(true);
                    break;
                case 2:
                    this.relatorioPassageirosPorAeroporto(false);
                    break;
                case 3:
                    this.relatorioReceitaPorCia();
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 4);
    }

    private void relatorioPassageirosPorAeroporto(boolean porOrigem) {
        String titulo = porOrigem ? "SAÍRAM" : "CHEGARAM";
        listarAeroportos();
        String aeroportoBusca = JOptionPane.showInputDialog(null, "Digite a sigla ou nome do Aeroporto:");
        if (aeroportoBusca == null || aeroportoBusca.isEmpty()) {
            return;
        }

        String textoLista = "Relatório: Passageiros que " + titulo + " em '" + aeroportoBusca + "'\n\n";
        boolean temItem = false;

        List<Ticket> tickets = ticketDAO.mostrarTodos();
        for (Ticket ticket : tickets) {
            Voo voo = vooDAO.buscaPorId(ticket.getVooId());
            if (voo != null) {
                String localDoVoo = porOrigem ? voo.getOrigem() : voo.getDestino();
                if (localDoVoo != null && localDoVoo.toLowerCase().contains(aeroportoBusca.toLowerCase())) {
                    Passageiro p = passageiroDAO.buscaPorId(ticket.getPassageiroId());
                    if (p != null) {
                        textoLista += "Passageiro: " + p.getNome() + " (Doc: " + p.getDocumento()
                                + ") | Voo ID: " + voo.getId() + "\n";
                        temItem = true;
                    }
                }
            }
        }

        if (!temItem) {
            textoLista += "Nenhum passageiro encontrado para este critério.";
        }

        JOptionPane.showMessageDialog(null, new JTextArea(textoLista));
    }

    private void relatorioReceitaPorCia() {
        listarCias();
        Cia ciaSelecionada = null;
        long ciaId = 0;

        try {
            String ciaIdStr = JOptionPane.showInputDialog(null, "Digite o ID da Companhia Aérea:");
            if (ciaIdStr == null) {
                return;
            }
            ciaId = Long.parseLong(ciaIdStr);
            ciaSelecionada = ciaDAO.buscaPorId(ciaId);
            if (ciaSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Erro: Companhia com ID " + ciaId + " não encontrada.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID de Companhia inválido.");
            return;
        }

        String ciaAbreviacaoPadrao = ciaSelecionada.getAbreviacao();

        String dataInicioStr = JOptionPane.showInputDialog(null, "Data de INÍCIO (dd/MM/yyyy):");
        String dataFimStr = JOptionPane.showInputDialog(null, "Data de FIM (dd/MM/yyyy):");

        LocalDate dataInicio, dataFim;
        try {
            dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            dataFim = LocalDate.parse(dataFimStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        LocalDateTime inicioPeriodo = dataInicio.atStartOfDay();
        LocalDateTime fimPeriodo = dataFim.plusDays(1).atStartOfDay();

        double valorTotal = 0;
        String textoLista = "Relatório de Receita para '" + ciaSelecionada.getNome()
                + "' (" + ciaAbreviacaoPadrao + ")\n";
        textoLista += "Período: " + dataInicioStr + " até " + dataFimStr + "\n\n";

        List<Ticket> tickets = ticketDAO.mostrarTodos();
        for (Ticket ticket : tickets) {
            LocalDateTime dataTicket = ticket.getDataCriacao();
            if (dataTicket != null && !dataTicket.isBefore(inicioPeriodo) && dataTicket.isBefore(fimPeriodo)) {
                Voo voo = vooDAO.buscaPorId(ticket.getVooId());
                if (voo != null && voo.getCia() != null && voo.getCia().equalsIgnoreCase(ciaAbreviacaoPadrao)) {
                    valorTotal += ticket.getValor();
                }
            }
        }

        textoLista += "Valor total arrecadado: R$ " + String.format("%.2f", valorTotal);
        JOptionPane.showMessageDialog(null, new JTextArea(textoLista));
    }

    // ========================================
    // IMPORTAÇÃO DE PASSAGEIROS DE TXT
    // ========================================
    private void importarPassageirosDeTXT() {
        String mensagem = "IMPORTAÇÃO DE PASSAGEIROS DE ARQUIVO TXT\n\n"
                + "Deseja continuar?";

        int confirma = JOptionPane.showConfirmDialog(null, mensagem,
                "Importar Passageiros", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (confirma == JOptionPane.YES_OPTION) {
            importadorPassageiro.importarDeArquivo();
        }
    }

    private void gerarArquivoExemploTXT() {
        String mensagem = "Esta opção irá gerar um arquivo TXT de exemplo\n\n"
                + "Deseja continuar?";

        int confirma = JOptionPane.showConfirmDialog(null, mensagem,
                "Gerar Arquivo Exemplo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (confirma == JOptionPane.YES_OPTION) {
            importadorPassageiro.gerarArquivoExemplo();
        }
    }

    // ========================================
    // MENU DE RELATÓRIOS PDF
    // ========================================
    private void menuRelatoriosPDF() {
        int opcao = 0;
        do {
            String menu = "Gerar Relatórios em PDF\n\n"
                    + "1. Relatório de Passageiros (Tabela)\n"
                    + "2. Relatório de Voos (Tabela)\n"
                    + "3. Relatório de Passageiros com Fotos\n"
                    + "4. Voltar ao Menu Admin\n\n"
                    + "Escolha uma opção:";

            String opcaoStr = JOptionPane.showInputDialog(null, menu);
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (Exception e) {
                if (opcaoStr == null) {
                    opcao = 4;
                } else {
                    opcao = 0;
                }
            }

            switch (opcao) {
                case 1:
                    geradorPDF.gerarRelatorioPassageiros();
                    break;
                case 2:
                    geradorPDF.gerarRelatorioVoos();
                    break;
                case 3:
                    String msg = "RELATÓRIO COM FOTOS\n\n"
                            + "Este relatório incluirá as fotos dos passageiros.\n\n"
                            + "Deseja continuar?";

                    int conf = JOptionPane.showConfirmDialog(null, msg,
                            "Relatório com Fotos", JOptionPane.YES_NO_OPTION);

                    if (conf == JOptionPane.YES_OPTION) {
                        geradorPDF.gerarRelatorioComImagens();
                    }
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 4);
    }

    public static void main(String[] args) {
        new Programa();
    }
}
