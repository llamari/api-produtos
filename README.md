# API Produtos

## 📋 Resumo
API para gerenciamento de produtos, vendas e entidades relacionadas.

## 🔧 Requisitos
- **JDK**: 17+ (verifique em `pom.xml`)
- **Maven**: use o wrapper incluído (`mvnw` / `mvnw.cmd`)
- **Banco de dados**: configure credenciais em `src/main/resources/application.properties`

## 🚀 Como executar
```

.\mvnw.cmd spring-boot:run

```

## 🔌 Endpoints

### Produtos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/produtos` | Lista todos os produtos |
| GET | `/api/produtos/{id}` | Obtém um produto por ID |
| POST | `/api/produtos` | Cria um novo produto |
| PUT | `/api/produtos/{id}` | Atualiza um produto |
| DELETE | `/api/produtos/{id}` | Deleta um produto |

### Fornecedores
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/fornecedores` | Lista todos os fornecedores |
| GET | `/api/fornecedores/{id}` | Obtém um fornecedor por ID |
| POST | `/api/fornecedores` | Cria um novo fornecedor |
| PUT | `/api/fornecedores/{id}` | Atualiza um fornecedor |
| DELETE | `/api/fornecedores/{id}` | Deleta um fornecedor |

### Vendas
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/vendas` | Lista todas as vendas |
| GET | `/api/vendas/{id}` | Obtém uma venda por ID |
| POST | `/api/vendas` | Cria uma nova venda |
| PUT | `/api/vendas/{id}` | Atualiza uma venda |
| DELETE | `/api/vendas/{id}` | Deleta uma venda |

### Clientes
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/clientes` | Lista todos os clientes |
| GET | `/api/clientes/{id}` | Obtém um cliente por ID |
| POST | `/api/clientes` | Cria um novo cliente |
| PUT | `/api/clientes/{id}` | Atualiza um cliente |
| DELETE | `/api/clientes/{id}` | Deleta um cliente |

## ⚙️ Configuração
- **Arquivo principal**: `src/main/resources/application.properties`
- Ajuste URL, usuário e senha do banco conforme seu ambiente

## 📁 Estrutura importante
- `pom.xml` - Dependências e build
- `src/main/java` - Controllers, services e modelos
- `src/main/resources/application.properties` - Configurações