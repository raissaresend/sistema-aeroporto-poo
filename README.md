#️ Sistema de Gestão Aeroportuária

Projeto final da disciplina de **Programação Orientada a Objetos**, desenvolvido para gerenciar as operações internas de um aeroporto com persistência de dados em banco MySQL.

## Funcionalidades
O sistema oferece uma estrutura completa para a administração aeroportuária:

* **Gerenciamento (CRUDs Completos):** Cadastro, listagem, atualização e remoção de Passageiros, Aeroportos, Companhias Aéreas e Voos.
* **Operações Operacionais:**
    * Realização de **Check-in**.
    * Despacho de **Bagagem**.
    * Fluxo de compra de passagens.
* **Relatórios e Documentação:**
    * Geração de relatórios em **PDF** (Voos e Passageiros).
    * Relatório especial de passageiros com fotos.
    * Importação de passageiros via arquivo **TXT**.
    * Exportação de arquivo de exemplo TXT.

## Tecnologias e Requisitos
* **Linguagem:** Java (JDK 8 ou superior).
* **Banco de Dados:** MySQL 8.0+.
* **Bibliotecas Externas:** 
    * `mysql-connector-j.jar` (Driver de conexão).
    * `itextpdf.jar` (Geração de relatórios PDF).
* **Ambiente:** IDE compatível (NetBeans, Eclipse).

## Configuração e Instalação

### 1. Banco de Dados
1. Acesse seu gerenciador de banco de dados (ex: MySQL Workbench).
2. Crie um banco de dados chamado `aeroporto`.
3. Importe e execute o arquivo `backup_aeroporto.sql` incluso no projeto para criar as tabelas.

### 2. Conexão
A classe `ConnectionFactory.java` está configurada com os seguintes padrões:
* **Usuário:** `root`
* **Senha:** `root`
* **URL:** `jdbc:mysql://localhost/aeroporto`

*Caso suas credenciais sejam diferentes, altere a classe em `src/airportsystem/ConnectionFactory.java`.*

### 3. Execução
1. Adicione os arquivos `.jar` mencionados na seção de bibliotecas ao `CLASSPATH` do seu projeto na IDE.
2. Localize a classe principal: `src/airportsystem/Programa.java`.
3. Execute o projeto.

## Acesso Administrativo
Para acessar as funcionalidades de gestão, utilize as credenciais padrão:
* **Login:** `admin`
* **Senha:** `admin`

## Observações Adicionais
* **Fotos de Passageiros:** Para o relatório com fotos, certifique-se de que a pasta de fotos contenha arquivos `.jpg` nomeados com o CPF do passageiro (ex: `12345678900.jpg`).
* **Importação TXT:** O arquivo de importação deve seguir o formato delimitado por ponto e vírgula: `nome;documento;login;senha;nascimento`.

---
*Desenvolvido por Raissa Resende | 04/12/2025*
