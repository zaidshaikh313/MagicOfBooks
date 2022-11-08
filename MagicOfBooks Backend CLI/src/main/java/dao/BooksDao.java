package dao;

import entities.Books;

import java.util.List;

interface BooksDao {
    void addBook(Books book);

    void deleteBook(int bookId);

    void updateBook(int bookId, int price, int sales);

    List<Books> displayBooks();

    int countBooks();

    List<Books> displayByGenre(String genre);

    List<Books> arrangeLowToHigh();

    List<Books> arrangeHighToLow();

    List<Books> arrangeBestSelling();

    public Books displayById(int bookId);

}
