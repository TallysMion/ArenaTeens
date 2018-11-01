## Bibliotecas utilizadas ##


- [JSTL][1]
- [JDBC][2]
- [jQuery][3]
- [Bootstrap][4]
- [Gson][5]
- [Datepicker - jQuery Plugin][6]

[1]: https://jstl.java.net/
[2]: http://jdbc.postgresql.org/
[3]: http://jquery.com/
[4]: http://getbootstrap.com/
[5]: https://github.com/google/gson
[6]: https://github.com/eternicode/bootstrap-datepicker/



## Rodando o projeto ##


Definir as seguintes propriedades no arquivo `src/java/jdbc/datasource.properties`:

- **host:** endereço IP da máquina em que o serviço do PostgreSQL está executando
- **port:** porta em que o serviço do PostgreSQL está executando
- **name:** nome do banco de dados
- **user:** usuário de conexão ao banco de dados
- **password:** senha de conexão ao banco de dados

Criar a tabela `usuario` no banco de dados:

```sql
CREATE TABLE j2ee.usuario
(
  id serial NOT NULL,
  login VARCHAR(20) NOT NULL,
  senha CHAR(32) NOT NULL,
  nome VARCHAR(40) NOT NULL,
  nascimento DATE NOT NULL,
  avatar VARCHAR(100),
  CONSTRAINT pk_usuario PRIMARY KEY (id),
  CONSTRAINT uq_usuario_login UNIQUE (login)
); 
```

### Créditos ###

Este projeto foi desenvolvido e aprimorado por: Paulo H. Oliveira, Pedro S. Tanaka, Luis F. M. Oliveira e Daniel S. Kaster.