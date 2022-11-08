package entities;

import javax.persistence.*;
 // Books POJO Class
@Entity
@Table(name = "books")
public class Books {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookId;
    @Column(name = "name")
    String bookName;
    @Column(name = "genre")
    String genre;
    @Column(name = "price")
    int price;
    @Column(name = "sales")
    int sales;
    @Column(name = "author")
    String author;
    @Column(name = "description")
    String description;

    public Books() {

    }

    public Books(String bookName, String genre, int price, int sales, String author , String description) {
        this.bookName = bookName;
        this.genre = genre;
        this.price = price;
        this.sales = sales;
        this.author = author;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getBookId() {
        return bookId;
    }
}
