/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airportsystem;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author RAISSA
 */
public class GeradorPDF {
    
    private PassageiroDAO passageiroDAO;
    private VooDAO vooDAO;
    private TicketDAO ticketDAO;
    
    public GeradorPDF() {
        this.passageiroDAO = new PassageiroDAO();
        this.vooDAO = new VooDAO();
        this.ticketDAO = new TicketDAO();
    }
    
    public void gerarRelatorioPassageiros() {
        try {
            // Selecionar onde salvar
            File arquivo = selecionarArquivo("relatorio_passageiros.pdf");
            if (arquivo == null) return;
            
            // Criar documento PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(arquivo));
            
            document.open();
            
            // Título
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph titulo = new Paragraph("RELATÓRIO DE PASSAGEIROS", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);
            
            // Data de geração
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
            Paragraph data = new Paragraph("Gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), dataFont);
            data.setAlignment(Element.ALIGN_CENTER);
            data.setSpacingAfter(20);
            document.add(data);
            
            // Criar tabela
            PdfPTable table = new PdfPTable(5); // 5 colunas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            
            // Definir largura das colunas
            float[] columnWidths = {1f, 3f, 2f, 2f, 2f};
            table.setWidths(columnWidths);
            
            // Cabeçalho da tabela
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
            PdfPCell header1 = new PdfPCell(new Phrase("ID", headerFont));
            PdfPCell header2 = new PdfPCell(new Phrase("Nome", headerFont));
            PdfPCell header3 = new PdfPCell(new Phrase("Documento", headerFont));
            PdfPCell header4 = new PdfPCell(new Phrase("Login", headerFont));
            PdfPCell header5 = new PdfPCell(new Phrase("Nascimento", headerFont));
            
            // Estilo do cabeçalho
            BaseColor azulEscuro = new BaseColor(41, 128, 185);
            header1.setBackgroundColor(azulEscuro);
            header2.setBackgroundColor(azulEscuro);
            header3.setBackgroundColor(azulEscuro);
            header4.setBackgroundColor(azulEscuro);
            header5.setBackgroundColor(azulEscuro);
            
            header1.setHorizontalAlignment(Element.ALIGN_CENTER);
            header2.setHorizontalAlignment(Element.ALIGN_CENTER);
            header3.setHorizontalAlignment(Element.ALIGN_CENTER);
            header4.setHorizontalAlignment(Element.ALIGN_CENTER);
            header5.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            table.addCell(header1);
            table.addCell(header2);
            table.addCell(header3);
            table.addCell(header4);
            table.addCell(header5);
            
            // Buscar passageiros
            List<Passageiro> passageiros = passageiroDAO.mostrarTodos();
            
            if (passageiros.isEmpty()) {
                document.add(new Paragraph("Nenhum passageiro cadastrado."));
            } else {
                // Adicionar dados
                Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
                
                for (Passageiro p : passageiros) {
                    table.addCell(new Phrase(String.valueOf(p.getId()), cellFont));
                    table.addCell(new Phrase(p.getNome() != null ? p.getNome() : "", cellFont));
                    table.addCell(new Phrase(p.getDocumento() != null ? p.getDocumento() : "", cellFont));
                    table.addCell(new Phrase(p.getLogin() != null ? p.getLogin() : "", cellFont));
                    
                    String nascimento = "";
                    if (p.getNascimento() != null) {
                        nascimento = p.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    }
                    table.addCell(new Phrase(nascimento, cellFont));
                }
                
                document.add(table);
                
                // Adicionar total
                Paragraph total = new Paragraph("\nTotal de passageiros: " + passageiros.size(), 
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
                total.setSpacingBefore(10);
                document.add(total);
            }
            
            document.close();
            
            JOptionPane.showMessageDialog(null, 
                    "Relatório gerado com sucesso!\n\n" + arquivo.getAbsolutePath());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao gerar PDF:\n" + e.getMessage(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void gerarRelatorioVoos() {
        try {
            File arquivo = selecionarArquivo("relatorio_voos.pdf");
            if (arquivo == null) return;
            
            Document document = new Document(PageSize.A4.rotate()); // Paisagem
            PdfWriter.getInstance(document, new FileOutputStream(arquivo));
            
            document.open();
            
            // Título
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph titulo = new Paragraph("RELATÓRIO DE VOOS", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);
            
            // Data
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
            Paragraph data = new Paragraph("Gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), dataFont);
            data.setAlignment(Element.ALIGN_CENTER);
            data.setSpacingAfter(20);
            document.add(data);
            
            // Tabela
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            
            float[] columnWidths = {1f, 2f, 2f, 3f, 2f, 2f, 2f};
            table.setWidths(columnWidths);
            
            // Cabeçalho
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.WHITE);
            BaseColor verde = new BaseColor(39, 174, 96);
            
            String[] headers = {"ID", "Origem", "Destino", "Data/Hora", "Cia", "Capacidade", "Status"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setBackgroundColor(verde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            
            // Dados
            List<Voo> voos = vooDAO.mostrarTodos();
            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
            
            for (Voo v : voos) {
                table.addCell(new Phrase(String.valueOf(v.getId()), cellFont));
                table.addCell(new Phrase(v.getOrigem() != null ? v.getOrigem() : "", cellFont));
                table.addCell(new Phrase(v.getDestino() != null ? v.getDestino() : "", cellFont));
                
                String dataVoo = "";
                if (v.getData() != null) {
                    dataVoo = v.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                }
                table.addCell(new Phrase(dataVoo, cellFont));
                
                table.addCell(new Phrase(v.getCia() != null ? v.getCia() : "", cellFont));
                table.addCell(new Phrase(String.valueOf(v.getCapacidade()), cellFont));
                table.addCell(new Phrase(v.getStatus() != null ? v.getStatus() : "", cellFont));
            }
            
            document.add(table);
            
            Paragraph total = new Paragraph("\nTotal de voos: " + voos.size(), 
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            total.setSpacingBefore(10);
            document.add(total);
            
            document.close();
            
            JOptionPane.showMessageDialog(null, 
                    "Relatório gerado com sucesso!\n\n" + arquivo.getAbsolutePath());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao gerar PDF:\n" + e.getMessage(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void gerarRelatorioComImagens() {
        try {
            // Selecionar pasta com as imagens
            JFileChooser folderChooser = new JFileChooser();
            folderChooser.setDialogTitle("Selecione a pasta com as imagens dos passageiros");
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            int resultado = folderChooser.showOpenDialog(null);
            if (resultado != JFileChooser.APPROVE_OPTION) {
                return;
            }
            
            File pastaImagens = folderChooser.getSelectedFile();
            
            // Selecionar onde salvar o PDF
            File arquivo = selecionarArquivo("relatorio_passageiros_com_fotos.pdf");
            if (arquivo == null) return;
            
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(arquivo));
            
            document.open();
            
            // Título
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph titulo = new Paragraph("RELATÓRIO DE PASSAGEIROS COM FOTOS", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(30);
            document.add(titulo);
            
            // Buscar passageiros
            List<Passageiro> passageiros = passageiroDAO.mostrarTodos();
            
            if (passageiros.isEmpty()) {
                document.add(new Paragraph("Nenhum passageiro cadastrado."));
            } else {
                Font nomeFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
                Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 11);
                
                for (Passageiro p : passageiros) {
                    // Criar tabela para cada passageiro (foto + dados)
                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(90);
                    table.setWidths(new float[]{1, 2});
                    
                    // Coluna 1: Foto
                    PdfPCell cellFoto = new PdfPCell();
                    cellFoto.setBorder(Rectangle.BOX);
                    cellFoto.setPadding(5);
                    
                    // Tentar carregar imagem (nome do arquivo = documento.jpg)
                    String nomeImagem = p.getDocumento() + ".jpg";
                    File imagemFile = new File(pastaImagens, nomeImagem);
                    
                    if (imagemFile.exists()) {
                        try {
                            Image foto = Image.getInstance(imagemFile.getAbsolutePath());
                            foto.scaleToFit(100, 100);
                            cellFoto.addElement(foto);
                        } catch (Exception e) {
                            cellFoto.addElement(new Phrase("Imagem não carregada", infoFont));
                        }
                    } else {
                        // Foto não encontrada
                        Paragraph semFoto = new Paragraph("Sem foto", infoFont);
                        semFoto.setAlignment(Element.ALIGN_CENTER);
                        cellFoto.addElement(semFoto);
                    }
                    
                    table.addCell(cellFoto);
                    
                    // Coluna 2: Dados
                    PdfPCell cellDados = new PdfPCell();
                    cellDados.setBorder(Rectangle.BOX);
                    cellDados.setPadding(10);
                    
                    Paragraph nome = new Paragraph(p.getNome() != null ? p.getNome() : "N/A", nomeFont);
                    cellDados.addElement(nome);
                    
                    cellDados.addElement(new Paragraph("ID: " + p.getId(), infoFont));
                    cellDados.addElement(new Paragraph("Documento: " + (p.getDocumento() != null ? p.getDocumento() : "N/A"), infoFont));
                    cellDados.addElement(new Paragraph("Login: " + (p.getLogin() != null ? p.getLogin() : "N/A"), infoFont));
                    
                    if (p.getNascimento() != null) {
                        cellDados.addElement(new Paragraph("Nascimento: " + 
                                p.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), infoFont));
                    }
                    
                    table.addCell(cellDados);
                    
                    document.add(table);
                    document.add(new Paragraph("\n"));
                }
            }
            
            document.close();
            
            JOptionPane.showMessageDialog(null, 
                    "Relatório com fotos gerado com sucesso!\n\n" 
                    + arquivo.getAbsolutePath() + "\n\n"
                    + "IMPORTANTE: As fotos devem ter o nome igual ao CPF do passageiro.\n"
                    + "Exemplo: 12345678900.jpg");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao gerar PDF:\n" + e.getMessage(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    

    //Abre um diálogo para selecionar onde salvar o arquivo PDF

    private File selecionarArquivo(String nomeDefault) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar relatório PDF");
        fileChooser.setSelectedFile(new File(nomeDefault));
        
        int resultado = fileChooser.showSaveDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            
            // Garantir extensão .pdf
            if (!arquivo.getName().toLowerCase().endsWith(".pdf")) {
                arquivo = new File(arquivo.getAbsolutePath() + ".pdf");
            }
            
            return arquivo;
        }
        
        return null;
    }
}