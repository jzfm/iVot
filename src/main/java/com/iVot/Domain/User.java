package com.iVot.Domain;

import com.iVot.Utilities.Encryptor;
import com.iVot.Utilities.InvalidParamException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "icon")
    private String icon;
    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name ="organizationId")
    private Organization organization;

    public User(){}

    public User (String name, String lastName, String email, String password,
    String icon) throws InvalidParamException {
        if (email.equals("") || !email.contains("@") || !email.contains(".com"))
            throw new InvalidParamException();
        if(password.equals(""))
            throw new InvalidParamException();
        if (name.equals("") || name.matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if (lastName.equals("") || lastName.matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.icon = icon;
    }

    public User (String name, String lastName, String email, String password, String icon,
    Organization organization) throws InvalidParamException {
        if (email.equals("") || !email.contains("@") || !email.contains(".com"))
            throw new InvalidParamException();
        if(password.equals(""))
            throw new InvalidParamException();
        if (name.equals("") || name.matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if (lastName.equals("") || lastName.matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if(organization == null)
            throw new InvalidParamException();

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.icon = icon;
        this.organization = organization;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setOrganization(Organization organization) { this.organization = organization; }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganizationId(Organization organization) {
        this.organization = organization;
    }
}
