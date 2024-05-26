# Projeto de Importação e Somatório de Arquivos CSV

## Descrição
Este projeto é um **importador de arquivos CSV** que realiza o somatório das colunas especificadas. Ele foi desenvolvido em **Java** e utiliza o **Tomcat Server** como servidor para hospedar a aplicação web.

## Dependências
As principais dependências do projeto são:

1. **javax.faces (2.2.13)**
   - Framework para construção de interfaces de usuário baseadas em componentes (JavaServer Faces - JSF).

2. **primefaces (13.0.0)**
   - Biblioteca de componentes de interface de usuário para JSF.

3. **javax.servlet-api (3.0.1)**
   - Fornece classes e interfaces para desenvolvimento de aplicações web Java.

4. **javax.inject (1)**
   - Relacionada à injeção de dependência (DI) no Java.

5. **commons-csv (1.10.0)**
   - Biblioteca para manipulação de arquivos CSV.

6. **pdfbox (3.0.0)**
   - Biblioteca para trabalhar com arquivos PDF.

## Configuração do Tomcat
Para configurar o **Tomcat Server**, siga os passos abaixo:

1. Baixe e instale o **Apache Tomcat** em sua máquina.
2. Configure o Tomcat no seu ambiente de desenvolvimento (por exemplo, no IntelliJ IDEA ou Eclipse).
3. Crie um novo projeto web no Tomcat e adicione suas classes e recursos.

## Funcionalidades do Projeto
O projeto realiza as seguintes tarefas:

1. **Importação de Arquivos CSV:**
   - O usuário pode fazer o upload de arquivos CSV contendo dados.
   - Os dados são processados e armazenados para posterior análise.

2. **Somatório de Colunas:**
   - O usuário especifica quais colunas deseja somar.
   - O projeto calcula o somatório dessas colunas e exibe o resultado.

## Como Usar
1. Clone este repositório.
2. Configure o Tomcat conforme descrito acima.
3. Execute o projeto no servidor Tomcat.
4. Acesse a aplicação no navegador e realize a importação dos arquivos CSV.
5. Especifique as colunas desejadas para o somatório.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.
