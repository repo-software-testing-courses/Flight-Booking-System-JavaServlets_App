# Sistema de Reserva de Voos - Turkish Airlines ✈️

Sistema web completo de reserva de voos para a Turkish Airlines, desenvolvido em **Java Servlets** e **JSP** seguindo a arquitetura **MVC**. A aplicação inclui autenticação e autorização baseada em roles do Tomcat, além de proteções contra **SQL Injection** e **Cross-Site Scripting (XSS)**.

## 🎯 O que a Aplicação Faz

Este é um sistema de reserva de voos onde uma companhia aérea vende assentos aos clientes via internet. A aplicação possui **3 tipos de usuários** com diferentes funcionalidades:

### 👥 Tipos de Usuários

1. **Admin (Administrador)**
   - Gerencia recursos da aplicação
   - Altera funcionalidades
   - Define assentos disponíveis

2. **Manager (Gerente)**
   - Aprova ou desaprova recursos
   - Aprova ou desaprova assentos
   - Gerencia aprovações

3. **Customer (Cliente)**
   - Pesquisa voos disponíveis
   - Faz reservas de voos
   - Visualiza reservas atuais
   - Escolhe assentos

## 🛠️ Tecnologias Utilizadas

- **Frontend:** HTML, CSS, JavaScript, jQuery, Bootstrap, JSP, AJAX
- **Backend:** Java Servlets, Java Models
- **Banco de Dados:** Microsoft Access
- **Web Services:** SOAP (para preços e número de assentos)
- **Segurança:** Proteção contra SQL Injection e XSS

## 🚀 Como Executar 

### Pré-requisitos
- Docker instalado no computador

### Passos para Executar

1. **Clone o repositório** (se ainda não fez)
2. **Execute no terminal:**
   ```bash
   docker-compose up -d
   ```
3. **Aguarde alguns segundos** para a aplicação inicializar
4. **Acesse no navegador:** `http://localhost:8080/TurkishAirlines/`

### Passos para Parar Execução

1. **Execute no terminal:**
   ```bash
   docker-compose down
   ```

### 🔑 Credenciais para Teste

Use estas credenciais para fazer login e testar as diferentes funcionalidades:

| Tipo de Usuário | Email | Senha | Funcionalidade |
|----------------|-------|-------|----------------|
| **Admin** | `haris@admin.com` | `a` | Gerenciar recursos |
| **Manager** | `haris@manager.com` | `m` | Aprovar recursos |
| **Customer** | `shariq@customer.com` | `c` | Fazer reservas |

## 📝 Notas

- A aplicação usa **autenticação baseada em roles** do Tomcat
- Todas as páginas protegidas redirecionam para login automaticamente
- O sistema possui validações de entrada para prevenir ataques
- Os dados são persistidos em banco Microsoft Access
- Web Services SOAP são utilizados para consultas de preços

---

**Desenvolvido por:** Haris Muneer
