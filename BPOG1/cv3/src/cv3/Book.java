/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv3;

/**
 *
 * @author Radek
 */
public class Book {
    private String name;
    private String author;
    private String genre;
    private Integer price;
    private Integer numberOfPieces;

    public Book(String name, String author, String genre, Integer price, Integer numberOfPieces) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.numberOfPieces = numberOfPieces;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setNumberOfPieces(Integer numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }

    @Override
    public String toString() {
        return  name + " - " + author + ", " + genre + ", " + price + " Kc, " + numberOfPieces + " ks";
    }
    
    
    
}
