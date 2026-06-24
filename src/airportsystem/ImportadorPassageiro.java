/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airportsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author RAISSA
 */
public class ImportadorPassageiro {

    private PassageiroDAO passageiroDAO;

    public ImportadorPassageiro() {
        this.passageiroDAO = new PassageiroDAO();
    }

    // Abre uma janela para selecionar o arquivo TXT e importa os passageiros
    //retorna: número de passageiros importados com sucesso
    public int importarDeArquivo() {
        // Criar o selecionador de arquivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione o arquivo TXT com os passageiros");

        // Filtrar apenas arquivos .txt
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        // Mostrar a janela
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            return importarPassageiros(arquivo);
        }

        return 0;
    }

    // Importa passageiros do arquivo especificado
    // retorna: número de passageiros importados
    private int importarPassageiros(File arquivo) {
        int importados = 0;
        int erros = 0;
        StringBuilder mensagemErros = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int numeroLinha = 0;

            while ((linha = br.readLine()) != null) {
                numeroLinha++;

                // Ignorar linhas vazias
                if (linha.trim().isEmpty()) {
                    continue;
                }

                // Ignorar linhas de comentário (começam com #)
                if (linha.trim().startsWith("#")) {
                    continue;
                }

                try {
                    // Processar a linha
                    Passageiro p = processarLinha(linha);

                    if (p != null) {
                        boolean sucesso = passageiroDAO.adiciona(p);
                        if (sucesso) {
                            importados++;
                        } else {
                            erros++;
                            mensagemErros.append("Linha ").append(numeroLinha)
                                    .append(": Erro ao salvar no banco\n");
                        }
                    }

                } catch (Exception e) {
                    erros++;
                    mensagemErros.append("Linha ").append(numeroLinha)
                            .append(": ").append(e.getMessage()).append("\n");
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao ler o arquivo:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        // Mostrar resultado da importação
        String mensagem = "Importação concluída!\n\n"
                + "✓ Passageiros importados: " + importados + "\n";

        if (erros > 0) {
            mensagem += "✗ Erros encontrados: " + erros + "\n\n"
                    + "Detalhes dos erros:\n" + mensagemErros.toString();
        }

        JOptionPane.showMessageDialog(null, mensagem);

        return importados;
    }

    // Processa uma linha do arquivo e cria um objeto Passageiro Formato:
    // nome;documento;login;senha;nascimento(dd/MM/yyyy)
    // retorna: objeto Passageiro criado
    // Exception: se houver erro no formato
    private Passageiro processarLinha(String linha) throws Exception {
        // Separar os campos por ponto-e-vírgula
        String[] campos = linha.split(";");

        if (campos.length < 4) {
            throw new Exception("Formato inválido. Esperado: nome;documento;login;senha;nascimento(opcional)");
        }

        Passageiro p = new Passageiro();

        // Campo 0: Nome
        p.setNome(campos[0].trim());

        // Campo 1: Documento
        p.setDocumento(campos[1].trim());

        // Campo 2: Login
        p.setLogin(campos[2].trim());

        // Campo 3: Senha
        p.setSenha(campos[3].trim());

        // Campo 4 (opcional): Data de Nascimento
        if (campos.length >= 5 && !campos[4].trim().isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate nascimento = LocalDate.parse(campos[4].trim(), formatter);
                p.setNascimento(nascimento);
            } catch (Exception e) {
                throw new Exception("Data de nascimento inválida. Use o formato dd/MM/yyyy");
            }
        }

        return p;
    }

    // Gera um arquivo TXT de exemplo para o usuário
    public void gerarArquivoExemplo() {
        String conteudo = "# Arquivo de exemplo para importação de passageiros\n"
                + "# Formato: nome;documento;login;senha;nascimento(dd/MM/yyyy)\n"
                + "#\n"
                + "# Exemplos:\n"
                + "João Silva;12345678900;joao;senha123;15/03/1990\n"
                + "Maria Santos;98765432100;maria;senha456;20/07/1985\n"
                + "Pedro Oliveira;11122233344;pedro;senha789;10/12/1995\n"
                + "Ana Costa;55566677788;ana;senha321\n"
                + "#\n"
                + "# Cole novos passageiros abaixo:\n";

        // Salvar arquivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar arquivo de exemplo");
        fileChooser.setSelectedFile(new File("passageiros_exemplo.txt"));

        int resultado = fileChooser.showSaveDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();

            try {
                java.nio.file.Files.write(arquivo.toPath(), conteudo.getBytes());
                JOptionPane.showMessageDialog(null,
                        "Arquivo de exemplo criado com sucesso!\n\n" + arquivo.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao criar arquivo:\n" + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
