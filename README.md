
# Projeto Spring Boot - Gerenciamento de Pilotos de F1 e Times

Este projeto é uma aplicação RESTful desenvolvida com Spring Boot que permite gerenciar **Pilotos de Fórmula 1** e seus respectivos **Times**. Utiliza MariaDB para persistência dos dados, e incorpora as boas práticas com JPA, Lombok, Spring Web e DevTools.

---

## 📋 Objetivo

Implementar uma API que permite:
- Cadastrar, consultar, atualizar e deletar **Pilotos** e **Times**
- Gerenciar o relacionamento entre Pilotos e Times (muitos pilotos pertencem a um time)

---

## 🧰 Tecnologias Utilizadas

- Java + Spring Boot
- Spring Web
- Spring Data JPA
- Lombok
- MariaDB
- DevTools
- Postman/Bruno (para testes dos endpoints)

---

## 🛠️ Configuração do Ambiente

### 1. Banco de Dados

Instale e configure o **XAAMP** para usar o **MariaDB**.  
Crie um banco com o nome, por exemplo:

```sql
CREATE DATABASE f1db;
```

### 2. application.properties

Configure as credenciais e URL do banco no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/f1db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🧩 Estrutura das Entidades

### 🏎️ Pilot.java

```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "team")
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private int racingNumber;
    private int wins;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
```

### 🏁 Team.java

```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "pilots")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String headquarters;
    private int foundedYear;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pilot> pilots;

    public void addPilot(Pilot pilot) {
        this.pilots.add(pilot);
        pilot.setTeam(this);
    }
}
```

---

## 📦 Repositórios

Interfaces que estendem `JpaRepository` para CRUD automático:

```java
public interface PilotRepository extends JpaRepository<Pilot, Long> {}
public interface TeamRepository extends JpaRepository<Team, Long> {}
```

---

## 🌐 Endpoints REST

Exemplo de mapeamento nos Controllers:

### PilotController

```java
@RestController
@RequestMapping("/api/pilots")
public class PilotController {

    @Autowired
    private PilotRepository pilotRepository;

    @GetMapping
    public List<Pilot> list() {
        return pilotRepository.findAll();
    }

    @PostMapping
    public Pilot create(@RequestBody Pilot pilot) {
        return pilotRepository.save(pilot);
    }

    // Métodos para update, delete etc.
}
```

### TeamController

```java
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<Team> list() {
        return teamRepository.findAll();
    }

    @PostMapping
    public Team create(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    // Métodos para update, delete etc.
}
```

---

## 🧪 Testando com Postman/Bruno

Exemplos:

- `GET /api/pilots` – Lista todos os pilotos
- `POST /api/teams` – Cria um time com JSON no body
- `POST /api/pilots` – Cria um piloto e associa a um time (usando o ID do time)

---

## ✅ Funcionalidades Implementadas

- Relacionamento **Many-to-One** entre `Pilot` e `Team`
- Mapeamento completo com JPA
- Uso de Lombok para simplificação do código
- CRUD completo para ambas as entidades
- Persistência no banco MariaDB

---

## 📌 Considerações

- Os relacionamentos foram cuidadosamente mapeados para evitar loops de serialização com `@ToString(exclude = ...)`.
- A conveniência de adicionar piloto no time foi implementada com `addPilot(Pilot)` dentro da classe `Team`.

---

## 🚀 Como Executar

1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/pilotos-f1-api.git
```

2. Configure o banco MariaDB conforme descrito acima

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

4. Acesse via Postman/Bruno em:  
`http://localhost:8080/api/pilots`  
`http://localhost:8080/api/teams`

---

## 📎 Licença

Este projeto é de uso acadêmico para fins de avaliação da disciplina de persistência com Spring Boot.
