import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Objects;

public class User {
    //User should have a list of books
    //User should have a username and a password
    private final String username;
    private String password;
    private final HashSet<Integer> books;
    public User (String name ,String password)
    {
        this.username = name;
        this.password=HashPassword(password);
        books = new HashSet<>();
    }
    private String HashPassword(String passwordToHash)
    {
        String generatedPassword = null;
        try
        {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToHash.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //System.out.println(generatedPassword);
        return generatedPassword;
    }
    public boolean setPassword(String password , String oldPassword)
    {
        if(!Objects.equals(HashPassword(oldPassword),this.password))
            return false;
        this.password=HashPassword(password);
        return true;
    }
    public boolean checkPassword(String password)
    {
        String genPass=HashPassword(password);
        return Objects.equals(this.password, genPass);
    }
    public boolean rentBook(int ISBN)
    {
        if(books.contains(ISBN))
            return false;
        books.add(ISBN);
        return true;
    }

    public boolean returnBook(int ISBN){
        if(!books.contains(ISBN))
            return false;
        books.remove(ISBN);
        return true;
    }
    public void getBooks()
    {
        StringBuilder out= new StringBuilder();
        int j=1;
        for(int i : books) {
            out.append(j).append(" : ").append(i).append("\n");
            j++;
        }
        if(out!=null)
            JOptionPane.showMessageDialog(null,"list of "+this.username+"'s books\n"+out);
        else
            JOptionPane.showMessageDialog(null, this.username+" doesn't have any book");
    }
}
