package MagicOfBooks;

import dao.BooksDaoImpl;
import dao.LogData;
import entities.Books;

import java.util.List;
import java.util.Scanner;

// This is our Main Class
public class Test {
    static Scanner sc = new Scanner(System.in);
    static Books books = new Books();
    static BooksDaoImpl booksDao = new BooksDaoImpl();
    static LogData log = new LogData();

    public static void main(String[] args) {
        //We can initialize given sample books by this method(To Be used for first time adding books to DB)
        System.out.println("Do you want to initialize books?(YES/NO)");
        String ans = sc.nextLine();
        if (ans.equalsIgnoreCase("yes")) {
            initializeBooks();
            log.storeData("Books are Initialized");
        }
        try {
            System.out.println("Please enter your Role(Admin/User):- ");
            String role = sc.nextLine();
            //When user enters anything other than admin or role
            while (!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("user")) {
                System.out.println("Invalid Role!");
                System.out.println("Please Re-enter");
                role = sc.nextLine();
            }
            if (role.equalsIgnoreCase("admin")) {
                adminMenu();//displays admin menu
            } else if (role.equalsIgnoreCase("user")) {
                userMenu();//displays user menu
            }
        } catch (Exception e) { // catches if any exceptions
            e.printStackTrace();
        }
    }

    private static void adminMenu() {
        String conti;
        do {
            System.out.println("************** MENU ***********");
            System.out.println("1. Add a Book");
            System.out.println("2. Delete a Book");
            System.out.println("3. Update a Book");
            System.out.println("4. Display the Books");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:   //For adding new book
                    System.out.println("Enter Book name");
                    books.setBookName(sc.nextLine());
                    System.out.println("Enter Book Genre");
                    books.setGenre(sc.nextLine());
                    System.out.println("Enter Book Author");
                    books.setAuthor(sc.nextLine());
                    System.out.println("Enter Book description");
                    books.setDescription(sc.nextLine());
                    System.out.println("Enter Book Price");
                    books.setPrice(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Enter Book Sales");
                    books.setSales(sc.nextInt());
                    sc.nextLine();
                    booksDao.addBook(books); // Adds book in DB
                    log.storeData(books.getBookName() + " book added successfully."); // stores the log
                    System.out.println("Book added successfully");
                    break;
                case 2:   // For deleting a book
                    try {
                        displayBookInfo();
                        System.out.println("Please Enter the Book name id you want to delete");
                        int tempBookId = sc.nextInt();
                        sc.nextLine();
                        booksDao.deleteBook(tempBookId); //Deletes book from DB if any

                    } catch (Exception e) {   // catches exception if any
                        e.printStackTrace();
                    }
                    break;
                case 3:   // For updating a book
                    displayBookInfo();
                    System.out.println("Please Enter the Book name id you want to update");
                    int tempBookId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Please Enter new information");
                    System.out.println("Enter Book Price");
                    books.setPrice(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Enter number of Copies sold");
                    books.setSales(sc.nextInt());
                    sc.nextLine();
                    booksDao.updateBook(tempBookId, books.getPrice(), books.getSales()); // updates book in DB
                    break;
                case 4:
                    display();  // For displaying all books
                    break;
                default:
                    System.out.println("Enter correct choice");

            }

            System.out.println("Do you want to continue?");
            conti = sc.nextLine();


        } while (conti.equalsIgnoreCase("yes"));  //displays menu again if user enters 'yes'
        if (conti.equalsIgnoreCase("no")) {
            toContinue(conti); // asks if user wants to continue as admin or user
        }
    }

    static void initializeBooks() { // Adds sample books to DB
        Books books0 = new Books("Ulysses", "Novel", 1100, 9000, "James Joyce", "Ulysses is a modernist novel by Irish writer James Joyce.");
        booksDao.addBook(books0);
        Books books1 = new Books("Don Quixote", "Novel", 1250, 2000, "Miguel de Cervantes", "Don Quixote or caballero, in Part 2) don Quijote de la Mancha).");
        booksDao.addBook(books1);
        Books books2 = new Books("One Hundred Years of Solitude", "Autobiography", 900, 1220, "Gabriel García Márquez,", "One Hundred Years of Solitude is a 1967 novel");
        booksDao.addBook(books2);
        Books books3 = new Books("Hamlet", "tragedy", 500, 1231, "William Shakespeare", "It is a tragedy written in sometime between 1599 and 1601.");
        booksDao.addBook(books3);
        Books books4 = new Books("In Search of Lost Time", "Autobiography", 1270, 1000, "Marcel Proust", " translated into English as Remembrance of Things Past,");
        booksDao.addBook(books4);
    }

    static void displayBookInfo() {
        System.out.println("Books Information");
        List<Books> books = booksDao.displayBooks();
        for (Books books1 : books) {
            System.out.println("Id: " + books1.getBookId() + "  Name: " + books1.getBookName());
        }
    }


    static void display() {  // Display all attributes of all books
        System.out.println("Books Information");
        List<Books> books = booksDao.displayBooks();
        for (Books books1 : books) {
            System.out.println("Book Id: " + books1.getBookId());
            System.out.println("Book Name: " + books1.getBookName());
            System.out.println("Book Genre: " + books1.getGenre());
            System.out.println("Book Author: " + books1.getAuthor());
            System.out.println("Book Price: " + books1.getPrice());
            System.out.println("Book Description: " + books1.getDescription());
            System.out.println("No of copies sold: " + books1.getSales());

        }
    }

    static void userMenu() {
        String conti;
        do {
            System.out.println("************** MENU ***********");
            System.out.println("1. Display Book count");
            System.out.println("2. Display via Genre");
            System.out.println("3. Book Information");
            System.out.println("4. Arrange price low to high ");
            System.out.println("5. Arrange price high to low ");
            System.out.println("6. Arrange best selling on Top");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:   // Display Book count
                    System.out.println("Book count is " + booksDao.countBooks());
                    break;
                case 2://Display books via Genre
                    System.out.println("Please Enter the Genre");
                    String tempGenre = sc.nextLine();
                    System.out.println("Books under Genre: " + tempGenre);
                    List<Books> books = booksDao.displayByGenre(tempGenre);
                    for (Books books1 : books) {
                        System.out.println("Id: " + books1.getBookId());
                        System.out.println("Name: " + books1.getBookName());
                    }
                    break;
                case 3:
                    displayBookInfo(); // display book by id
                    System.out.println("Please Enter the Book name id you want to Describe");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    Books books2 = booksDao.displayById(bookId);
                    if (books2 == null) System.out.println("Book Not Available!");
                    else {
                        System.out.println("Id: " + books2.getBookId());
                        System.out.println("Name: " + books2.getBookName());
                        System.out.println("Book Genre: " + books2.getGenre());
                        System.out.println("Book Author: " + books2.getAuthor());
                        System.out.println("Book Price: " + books2.getPrice());
                        System.out.println("Book Description: " + books2.getDescription());
                        System.out.println("No of copies sold: " + books2.getSales());
                    }
                    break;
                case 4:
                    System.out.println("Book According to price Low to High");
                    List<Books> books3 = booksDao.arrangeLowToHigh();
                    for (Books books1 : books3) {
                        System.out.println("Name: " + books1.getBookName() + "  price" + books1.getPrice());
                    }
                    break;
                case 5:
                    System.out.println("Book According to price High to Low");
                    List<Books> books4 = booksDao.arrangeHighToLow();
                    for (Books books1 : books4) {
                        System.out.println("Name: " + books1.getBookName() + "   price" + books1.getPrice());
                    }
                    break;
                case 6:
                    System.out.println("Book According to Best selling: ");
                    List<Books> books5 = booksDao.arrangeBestSelling();
                    for (Books books1 : books5) {
                        System.out.println("Name: " + books1.getBookName() + "  sales" + books1.getSales());
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");

            }
            System.out.println("Do you want to continue");
            conti = sc.nextLine();

            if (conti.equalsIgnoreCase("no")) {
                toContinue(conti);
            }

        } while (conti.equalsIgnoreCase("yes"));


    }

    static void toContinue(String conti) {
        if (conti.equalsIgnoreCase("no")) {
            System.out.println("Press 1 from Main Menu and 0 For Exit!");
            int c = sc.nextInt();
            sc.nextLine();
            if (c == 1) {
                main(null);
            } else System.exit(1);
        }
    }
}
