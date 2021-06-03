/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv5;

/**
 *
 * @author Radek
 */
public class Worker {
    private Integer id;
    private String name;
    private String surname;
    private Integer year;

    public Worker(Integer id, String name, String surname, Integer year) {
        this.name = name;
        this.id = id;
        this.surname = surname;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getYear() {
        return year;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYear(Integer year) {
        this.year = year;
    }



    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Worker{" + "name=" + name + '}';
    }
    
}
