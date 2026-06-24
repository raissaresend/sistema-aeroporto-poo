========================================================================
             TRABALHO FINAL POO - SISTEMA DE GESTÃO AEROPORTUÁRIA
========================================================================
ALUNA: Raissa Resende
DISCIPLINA: Programação Orientada a Objetos (ADS - 3º Período)
DATA DE ENTREGA: 04/12/2025

------------------------------------------------------------------------
1. DESCRIÇÃO DO PROJETO
------------------------------------------------------------------------
Sistema desenvolvido em Java para a gestão da parte interna de um aeroporto.
O software contempla CRUDs completos, operações de check-in, despacho de 
bagagem, compra de passagens, geração de relatórios em PDF e persistência
de dados em banco MySQL.

------------------------------------------------------------------------
2. PRÉ-REQUISITOS E DEPENDÊNCIAS
------------------------------------------------------------------------
Para executar este projeto, é necessário ter instalado:
- Java JDK 8 ou superior.
- Banco de Dados MySQL 8.0+.
- IDE (NetBeans, Eclipse).

Bibliotecas Externas (Inclusas na pasta /lib ou /dist):
- mysql-connector-j.jar (Driver de conexão MySQL)
- itextpdf.jar (Para geração dos relatórios PDF)

IMPORTANTE: Certifique-se de adicionar esses .jar ao CLASSPATH/Bibliotecas 
do projeto na sua IDE antes de executar.

------------------------------------------------------------------------
3. CONFIGURAÇÃO DO BANCO DE DADOS
------------------------------------------------------------------------
1. Acesse o seu gerenciador de banco de dados (MySQL Workbench).
2. Crie um schema/banco de dados chamado 'aeroporto'.
3. Importe/Execute o arquivo 'script_banco.sql' (anexo a este projeto) 
   para criar as tabelas necessárias.

Configuração de Conexão (Classe ConnectionFactory.java):
- Usuário padrão definido: root
- Senha padrão definida: root
- URL: jdbc:mysql://localhost/aeroporto

Caso seu MySQL tenha senha diferente, altere a classe:
src/trabalho2/ConnectionFactory.java

------------------------------------------------------------------------
4. COMO EXECUTAR
------------------------------------------------------------------------
1. Abra o projeto na IDE.
2. Localize a classe principal: src/trabalho2/Programa.java
3. Execute o arquivo.

------------------------------------------------------------------------
5. DADOS DE ACESSO (LOGIN)
------------------------------------------------------------------------
Para acessar o Painel Administrativo, utilize:

Login: admin
Senha: admin

------------------------------------------------------------------------
6. FUNCIONALIDADES ESPECIAIS (FASE 2)
------------------------------------------------------------------------
- Geração de PDF: Os relatórios são salvos no local escolhido pelo usuário.
- Fotos dos Passageiros: Para o relatório com fotos, certifique-se de ter
  uma pasta contendo imagens .jpg onde o nome do arquivo é o CPF do 
  passageiro (ex: 12345678900.jpg).
- Importação de TXT: O arquivo deve seguir o padrão:
  nome;documento;login;senha;nascimento