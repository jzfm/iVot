package com.iVot.Domain;

import com.iVot.Application.DTO.UserDTO;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "userId")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "token")
    private String token;
    @Column(name = "email")
    private String email;
    /*@Column(name = "password")
    private String password;*/
    @Column(name = "icon")
    private String icon;
    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "organizationId")
    private Organization organization;

    public User(){}
/*
    public User (UserDTO user) throws InvalidParamException {
        if (user.getToken().equals("") || !user.getToken().contains("@") || !user.getToken().contains(".com"))
            throw new InvalidParamException();
        if(user.getPassword().equals(""))
            throw new InvalidParamException();
        if (user.getName().equals("") || user.getName().matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if (user.getLastName().equals("") || user.getLastName().matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();

        this.name = user.getName();
        this.lastName = user.getLastName();
        this.token = user.getToken();
        this.password = user.getPassword();
        this.icon = user.getIcon();
    }
*/
    public User (UserDTO user, Organization organization) throws InvalidParamException {
        if (user.getToken().equals(""))
            throw new InvalidParamException();
        /*if(user.getPassword().equals(""))
            throw new InvalidParamException();
            */
        if (user.getName().equals("") || user.getName().matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if (user.getLastName().equals("") || user.getLastName().matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if (user.getEmail().equals("") || !user.getEmail().contains("@") || !user.getEmail().contains(".com"))
            throw new InvalidParamException();
        if (organization == null)
            throw new InvalidParamException();

        this.name = user.getName();
        this.lastName = user.getLastName();
        this.token = user.getToken();
        //this.password = user.getPassword();
        this.icon = user.getIcon();
        this.organization = organization;
        this.email = user.getEmail();
    }
/*
    public void checkPasswordIsCorrect(String password) throws InvalidParamException {
        Encryptor.checkIfPasswordMatches(password, this.password);
    }
*/
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
/*
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
*/
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
