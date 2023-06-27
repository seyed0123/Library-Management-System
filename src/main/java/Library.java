    import javax.swing.*;
    import java.util.HashMap;

    public class Library {
        /*
        * The library should have a list of books.
        * The library should have a map of books ISBNs which is linked to the amount of book
        -> (for example: harry potter -> 4 means there are currently 4 harry potter books)
        * The library should have a list of users and a list of librarians.
         */
        private final HashMap <Integer, Book> books;
        private final HashMap <String , User> users;
        private final HashMap <String , Librarian > librarians;
        public Library()
        {
            books = new HashMap<>();
            users = new HashMap<>();
            librarians = new HashMap<>();
        }
        //book related functions
        public void addBook(String name, String author , int year , int num , int ISBN) {
            if(!doesBookExist(ISBN))
            {
                Book book = new Book(name , author , year ,num , ISBN);
                books.put(ISBN , book);
                return;
            }
            JOptionPane.showMessageDialog(null,"this book already added.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        public void removeBook(int ISBN){
            if(doesBookExist(ISBN))
                books.remove(ISBN);
            else
                JOptionPane.showMessageDialog(null,"this book doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        public void searchBook(int ISBN){
            if(doesBookExist(ISBN))
               books.get(ISBN).printStatus();
            else
                JOptionPane.showMessageDialog(null,"here are 0 book with this ISBN (" +ISBN+") in the library");
        }

        public void updateBook(int ISBN , int year){
            if(doesBookExist(ISBN))
                books.get(ISBN).updateBook(year);
            else
                JOptionPane.showMessageDialog(null,"this book doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        public boolean doesBookExist(int ISBN){
            return books.containsKey(ISBN);
        }

        public void increaseBook(int ISBN , int num){
            if(doesBookExist(ISBN)) {
                books.get(ISBN).increaseBook(num);
                JOptionPane.showMessageDialog(null, "done!\nnow we have " + books.get(ISBN).getNum() +" with ISBN ("+ ISBN + ") in the library");
            }
            else
                JOptionPane.showMessageDialog(null,"this book doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        public void decreaseBook(int ISBN , int num){

            if(doesBookExist(ISBN)) {
                if(books.get(ISBN).decreaseBook(num))
                    JOptionPane.showMessageDialog(null, "done\nnow we have " + books.get(ISBN).getNum() +" book with this ISBN ("+ ISBN + ") in the library");
                else
                    JOptionPane.showMessageDialog(null, "oops\nwe only have " + books.get(ISBN).getNum() +" book with this ISBN ("+ ISBN + ") in the library","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null,"this book doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        //user related functions

        public void addUser(String name , String password){
            if(doesUserExist(name) || doesLibrarianExist(name)) {
                JOptionPane.showMessageDialog(null, "this username already in use.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            User user = new User(name, password);
            users.put(name , user);
        }

        public void removeUser(String name){
            if(doesUserExist(name))
                users.remove(name);
            else
                JOptionPane.showMessageDialog(null,"this user doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        public void searchUser(String name){

            if(doesUserExist(name))
                users.get(name).getBooks();
            else
                JOptionPane.showMessageDialog(null,"this user doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        public void updateUser(String name , String oldPassword , String password){
            if(doesUserExist(name)) {
                if(users.get(name).setPassword(password , oldPassword))
                    JOptionPane.showMessageDialog(null, "done\nyour password changed.");
                else
                    JOptionPane.showMessageDialog(null,"old password is wrong.","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null,"this user doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        public boolean doesUserExist(String name ){
            return users.containsKey(name);
        }

        //librarian related functions

        public void addLibrarian(String name , String password){
            if(doesLibrarianExist(name) || doesUserExist(name)) {
                JOptionPane.showMessageDialog(null, "this username already in use.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            Librarian librarian = new Librarian (name, password);
            librarians.put(name , librarian);
        }

        public void removeLibrarian(String name){
            if(doesLibrarianExist(name))
                librarians.remove(name);
            else
                JOptionPane.showMessageDialog(null,"this user doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        public void searchLibrarian(String name){

            if(doesLibrarianExist(name)) {
                if(librarians.get(name).checkActivity())
                    JOptionPane.showMessageDialog(null, "this librarian is active.");
                else
                    JOptionPane.showMessageDialog(null, "this librarian isn't active.");
            }
            else
                JOptionPane.showMessageDialog(null,"this librarian doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        public void updateLibrarian(String name){
            if(doesLibrarianExist(name))
            {
                librarians.get(name).changeActivity();
                JOptionPane.showMessageDialog(null, "done\nyour password changed.");
            }
            else
                JOptionPane.showMessageDialog(null,"this Librarian doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        public boolean doesLibrarianExist(String name){
            return librarians.containsKey(name);
        }
        public int login (String username , String password)
        {
            if(doesUserExist(username))
                if (users.get(username).checkPassword(password))
                    return 2;
                else
                    return 1;
            else if(doesLibrarianExist(username))
                if(librarians.get(username).checkPassword(password))
                    return -2;
                else
                    return -1;
            else
                return 0;
        }
        public void rentBook(String username , int ISBN)
        {
            if(!doesBookExist(ISBN))
            {
                JOptionPane.showMessageDialog(null,"this book doesn't exist.","ERROR",JOptionPane.ERROR_MESSAGE);
            }if(books.get(ISBN).getNum()<=0)
            {
                JOptionPane.showMessageDialog(null,"There are not enough of these books available.","ERROR",JOptionPane.ERROR_MESSAGE);
            }if(!users.get(username).rentBook(ISBN))
            {
                JOptionPane.showMessageDialog(null,"you already have this book.","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            decreaseBook(ISBN,1);
            JOptionPane.showMessageDialog(null,"done!");
        }
        public void returnBook(String username , int ISBN)
        {
            if(users.get(username).returnBook(ISBN)) {
                JOptionPane.showMessageDialog(null, "done!");
                increaseBook(ISBN,1);
            }
            else
                JOptionPane.showMessageDialog(null,"you haven't ever rented this book","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
