public class Librarian extends User{
    /*
    * The librarian should have a username and a password
    * The librarian should be able to search users, librarians and books
    * The librarian should be able to add\remove\ update user add\remove\ update_
    _ librarian and add\remove\ update book
     */
    private boolean isActive;
    public Librarian(String name, String password) {
        super(name,password);
        this.isActive=true;
    }
    public boolean checkActivity()
    {
        return this.isActive;
    }
    public void changeActivity()
    {
        this.isActive= !this.isActive;
    }
    public boolean checkPassword(String password)
    {
        boolean res = super.checkPassword(password);
        return res && this.isActive;
    }
}
