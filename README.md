# Entidades do Sistema:

## Entidade Book.

A entidade `Book` representa um livro na aplicação e possui os seguintes atributos e relacionamentos:

| Campo         | Tipo                | Anotações                                          | Descrição                                     |
|---------------|---------------------|---------------------------------------------------|-----------------------------------------------|
| `id`          | `String`            | `@Id`, `@GeneratedValue(strategy = GenerationType.UUID)` | Identificador único do livro                   |
| `title`       | `String`            | `@Column(nullable = false, unique = true)`        | Título do livro                                |
| `publisher`   | `Publisher`         | `@ManyToOne`, `@JoinColumn(name = "publisher_id")`| Editora que publicou o livro                   |
| `authors`     | `Set<Author>`       | `@ManyToMany`, `@JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))` | Autores do livro                               |
| `review`      | `Review`            | `@OneToOne(mappedBy = "book", cascade = CascadeType.ALL)` | Avaliação do livro                             |

## Entidade Author.

A entidade `Author` representa um autor na aplicação e possui os seguintes atributos e relacionamentos:

| Campo         | Tipo                | Anotações                                          | Descrição                                     |
|---------------|---------------------|---------------------------------------------------|-----------------------------------------------|
| `id`          | `String`            | `@Id`, `@GeneratedValue(strategy = GenerationType.UUID)` | Identificador único do autor                   |
| `name`        | `String`            | `@Column(nullable = false, unique = true)`        | Nome do autor                                  |
| `books`       | `Set<Book>`         | `@ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)`, `@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)` | Livros associados ao autor                     |

## Entidade Publisher

A entidade `Publisher` representa uma editora na aplicação e possui os seguintes atributos e relacionamentos:

| Campo         | Tipo                | Anotações                                          | Descrição                                     |
|---------------|---------------------|---------------------------------------------------|-----------------------------------------------|
| `id`          | `String`            | `@Id`, `@GeneratedValue(strategy = GenerationType.UUID)` | Identificador único da editora                 |
| `name`        | `String`            | `@Column(nullable = false, unique = true)`        | Nome da editora                                |
| `books`       | `Set<Book>`         | `@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)`, `@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)` | Livros publicados pela editora                 |

## Entidade Review

A entidade `Review` representa uma avaliação de um livro na aplicação e possui os seguintes atributos e relacionamentos:

| Campo         | Tipo                | Anotações                                          | Descrição                                     |
|---------------|---------------------|---------------------------------------------------|-----------------------------------------------|
| `id`          | `String`            | `@Id`, `@GeneratedValue(strategy = GenerationType.UUID)` | Identificador único da avaliação               |
| `comment`     | `String`            | `@Column(nullable = false)`                       | Comentário da avaliação                        |
| `book`        | `Book`              | `@OneToOne`, `@JoinColumn(name = "book_id")`, `@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)` | Livro associado à avaliação                    |

