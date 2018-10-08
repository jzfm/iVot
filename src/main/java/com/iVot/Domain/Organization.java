package com.iVot.Domain;

import com.iVot.Utilities.Encryptor;
import com.iVot.Utilities.InvalidParamException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "organizationId")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "icon")
    private String icon;
    @Column(name = "description")
    private String description;


    public Organization() { }

    public Organization (String name, String email, String password,
    String icon, String description) throws InvalidParamException {
        if (email.equals("") || !email.matches("@"))
            throw new InvalidParamException();
        if(password.equals(""))
            throw new InvalidParamException();
        if (name.equals("") || name.matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if (description.equals("") || description.matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();

        this.name = name;
        this.description = description;
        this.email = email;
        this.password = password;
        this.icon = icon;
        this.description = description;
    }

    public void checkPasswordIsCorrect(String password) throws InvalidParamException {
        Encryptor.checkIfPasswordMatches(password, this.password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
