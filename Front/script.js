// Função para carregar os livros existentes na página inicial
function loadBooks() {
    fetch('http://127.0.0.1:8080/store/book')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch books');
            }
            return response.json();
        })
        .then(books => {
            const booksContainer = document.getElementById('booksContainer');
            if (booksContainer) {
                booksContainer.innerHTML = ''; // Limpa o conteúdo atual
                books.forEach(book => {
                    const card = createBookCard(book);
                    booksContainer.appendChild(card);
                });
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to fetch books');
        });
}

// Função para criar um card de livro
function createBookCard(book) {
    const card = document.createElement('div');
    card.classList.add('book-card');

    const title = document.createElement('h3');
    title.textContent = book.title;
    card.appendChild(title);

    const publisher = document.createElement('p');
    publisher.textContent = `Publisher: ${book.publisher ? book.publisher.name : 'Unknown'}`;
    card.appendChild(publisher);

    const authors = document.createElement('p');
    authors.textContent = `Authors: ${book.authors.map(author => author.name).join(', ')}`;
    card.appendChild(authors);

    const review = document.createElement('p');
    review.textContent = `Review: ${book.review ? book.review.comment : 'No review'}`;
    card.appendChild(review);

    return card;
}

// Função para lidar com o envio do formulário de criação de livro
function submitForm(event) {
    event.preventDefault(); // Previne o comportamento padrão de envio do formulário
    
    // Coleta os dados do formulário
    const title = document.getElementById('title').value;
    const publisherId = document.getElementById('publisherId').value;
    const author1Id = document.getElementById('author1').value;
    const author2Id = document.getElementById('author2').value;
    const review = document.getElementById('review').value;
    
    // Monta o objeto JSON com os dados
    const data = {
        title: title,
        publisherId: publisherId,
        authorsIds: [author1Id, author2Id].filter(id => id.trim() !== ''), // Filtra IDs vazios
        review: review
    };
    
    // Envia os dados para o backend usando fetch API
    fetch('http://127.0.0.1:8080/store/book', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to create book');
        }
        alert('Book created successfully!');
        // Limpa o formulário após o envio bem-sucedido, se desejado
        document.getElementById('bookForm').reset();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to create book');
    });
}

// Adiciona um listener para o evento de submit do formulário
document.addEventListener('DOMContentLoaded', function() {
    // Carrega os livros existentes ao carregar a página inicial
    loadBooks();

    // Adiciona um listener para o evento de clique no botão Create New Book
    const createBookBtn = document.getElementById('createBookBtn');
    if (createBookBtn) {
        createBookBtn.addEventListener('click', function() {
            window.location.href = 'create-book.html'; // Redireciona para a página de criação
        });
    }

    // Adiciona um listener para o evento de submit do formulário
    const bookForm = document.getElementById('bookForm');
    if (bookForm) {
        bookForm.addEventListener('submit', submitForm);
    }
});
