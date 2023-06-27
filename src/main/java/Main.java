import javax.swing.*;
import java.util.Objects;
public class Main {
    /*
    * make a functional library app using oop
    * run the main program in Main.java and code the oop part in other classes
    * don't forget to add at least 1 librarian to the library to make it functionable.
    * *  *** don't limit yourself to our template ***
     */
    public static void main(String[] args)
    {
        lib.addLibrarian("temp","changeMe");
        login();
    }
    static Library lib = new Library();
    static public String getNumberString(String dialog)
    {
        String input;
        while(true)
        {
            input =JOptionPane.showInputDialog(dialog);
            String num ="1234567890";
            boolean flag=true;
            for(int i = 0 ; i < input.length() ; i++)
            {
                if (!num.contains("" + input.charAt(i))) {
                    flag = false;
                    break;
                }
            }if(flag)
                break;
            else
                JOptionPane.showMessageDialog(null, "Was machst du bro?.");
        }
        return input;
    }
    public static void login()
    {
        int res;
        String name;
        while (true) {
            name = JOptionPane.showInputDialog("Enter the your username");
            String password = JOptionPane.showInputDialog("Enter the password");
            if((name==null || password==null))
                System.exit(0);
            res = lib.login(name, password);
            if ((Math.abs(res)) == 2) {
                break;
            } else
                JOptionPane.showMessageDialog(null, "the username or the password is wrong.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        if(res==2) {
            while (true)
                if (userMenu(name))
                    break;
        }
        else
            while (true)
                if (librarianMenu())
                    break;

        login();
    }
    static String userInput()
    {
        return JOptionPane.showInputDialog("1-change password\n2-rent book\n3-return book\n4-borrow book list\n5-log out");
    }
    static boolean userMenu(String username)
    {
        String command = userInput();
        if(Objects.equals(command, "1"))
        {
            String oldPassword = JOptionPane.showInputDialog("Enter the old password");
            String password = JOptionPane.showInputDialog("Enter the new password");
            lib.updateUser(username, oldPassword , password);
        }else if (Objects.equals(command, "2"))
        {
            int ISBN = Integer.parseInt(getNumberString("Enter the book ISBN book code (Write numerically)"));
            lib.rentBook(username,ISBN);
        }else if (Objects.equals(command, "3"))
        {
            int ISBN = Integer.parseInt(getNumberString("Enter the book ISBN book code (Write numerically)"));
            lib.returnBook(username , ISBN);
        }else if (Objects.equals(command, "4"))
        {
            lib.searchUser(username);
        }else if (Objects.equals(command, "5") ||command == null)
        {
            return true;
        }else
        {
            JOptionPane.showMessageDialog(null, "Was machst du bro?.");
        }
        return false;
    }
    static String librarianInput()
    {
        return JOptionPane.showInputDialog("1-add book\n2-remove book\n3-search Book\n4-update Book\n5-increase Book\n6-decrease Book\n7-addUser\n8-removeUser\n9-searchUser\n10-updateUser\n11-addLibrarian\n12-removeLibrarian\n13-searchLibrarian\n14-updateLibrarian\n15-logout");
    }
    static boolean librarianMenu()
    {
        String commend = librarianInput();
        if(Objects.equals(commend, "1"))
        {
            String name = JOptionPane.showInputDialog("Enter the book name");
            String author = JOptionPane.showInputDialog("Enter the book author");
            int year = Integer.parseInt(getNumberString("Enter the book year (Write numerically)"));
            int num = Integer.parseInt(getNumberString("Enter the book initial value (Write numerically)"));
            int ISBN = Integer.parseInt(getNumberString("Enter the book ISBN book code (Write numerically)"));
            lib.addBook(name,author ,year , num ,ISBN);
        }else if(Objects.equals(commend, "2"))
        {
            int ISBN = Integer.parseInt(getNumberString("Enter the book ISBN book code (Write numerically)"));
            lib.removeBook(ISBN);
        }else if (Objects.equals(commend, "3"))
        {
            int ISBN = Integer.parseInt(getNumberString("Enter the book ISBN book code (Write numerically)"));
            lib.searchBook(ISBN);
        }else if (Objects.equals(commend, "4"))
        {
            String input;
            int ISBN = Integer.parseInt(getNumberString("Enter the book ISBN book code (Write numerically)"));
            int year = Integer.parseInt("Enter the book year (Write numerically)");
            lib.updateBook(ISBN,year);
        }else if(Objects.equals(commend, "5"))
        {
            int ISBN = Integer.parseInt(getNumberString("Enter the book ISBN book code (Write numerically)"));
            int num = Integer.parseInt(getNumberString("Enter the number of books added (Write numerically)"));
            lib.increaseBook(ISBN, num);
        }else if(Objects.equals(commend, "6"))
        {
            int ISBN = Integer.parseInt(getNumberString("Enter the book ISBN book code (Write numerically)"));
            int num = Integer.parseInt(getNumberString("Enter the number of books removed (Write numerically)"));
            lib.decreaseBook(ISBN,num);
        }else if (Objects.equals(commend, "7"))
        {
            String name = JOptionPane.showInputDialog("Enter the username");
            String password =JOptionPane.showInputDialog("Enter the password");
            lib.addUser(name , password);
        }else if (Objects.equals(commend, "8"))
        {
            String name = JOptionPane.showInputDialog("Enter the user name");
            lib.removeUser(name);
        }else if (Objects.equals(commend, "9"))
        {
            String name = JOptionPane.showInputDialog("Enter the user name");
            lib.searchUser(name);
        }else if(Objects.equals(commend, "10"))
        {
            String name = JOptionPane.showInputDialog("Enter the user name");
            String oldPassword = JOptionPane.showInputDialog("Enter the old password");
            String password = JOptionPane.showInputDialog("Enter the new password");
            lib.updateUser(name , oldPassword, password);
        }else if (Objects.equals(commend, "11"))
        {
            String name = JOptionPane.showInputDialog("Enter the username");
            String password =JOptionPane.showInputDialog("Enter the password");
            lib.addLibrarian(name , password);
        }else if (Objects.equals(commend, "12"))
        {
            String name = JOptionPane.showInputDialog("Enter the user name");
            lib.removeLibrarian(name);
        }else if (Objects.equals(commend, "13"))
        {
            String name = JOptionPane.showInputDialog("Enter the user name");
            lib.searchLibrarian(name);
        }else if (Objects.equals(commend, "14"))
        {
            String name = JOptionPane.showInputDialog("Enter the user name");
            lib.updateLibrarian(name);
        }else if (Objects.equals(commend, "15") || commend==null)
        {
            return true;
        }else
        {
            JOptionPane.showMessageDialog(null, "Was machst du bro?.");
        }
        return false;
    }

}
