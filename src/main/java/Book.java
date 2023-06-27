import javax.swing.*;

public class Book {
    private final String name;
    private final String author;
    private int yearOfPublish;
    private final int ISBN;
    private int num;
    public Book(String name , String author , int yearOfPublish , int num ,int ISBN)
    {
        this.ISBN=ISBN;
        this.name=name;
        this.author=author;
        this.yearOfPublish=yearOfPublish;
        this.num=num;
    }
    public boolean decreaseBook(int number)
    {
        if(number>this.num)
            return false;
        else
            this.num-=number;
        return true;
    }
    public void increaseBook(int number)
    {
        this.num+=number;
    }
    public void updateBook(int year){
        this.yearOfPublish = year;
    }
    public int getNum()
    {
        return this.num;
    }
    public void printStatus()
    {
        String out = "name :" +this. name +"\nauthor : "+this.author+"\nyear of publish :"+this.yearOfPublish+"\nISBN : "+this.ISBN+"\nnumber :"+this.num;
        JOptionPane.showMessageDialog(null,out);
    }
}
