package com.iVot.Domain;

import com.iVot.Utilities.InvalidParamException;

import javax.persistence.*;
import java.util.Calendar;

@Entity(name="Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "eventId")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "icon")
    private String icon;
    @Column(name = "description")
    private String description;
    @Column(name = "pdfFile")
    private String pdfFile;
    @Column(name = "date")
    private Calendar date;
    @Column(name = "post")
    private boolean post;
    @Column(name = "close")
    private boolean close;
    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "organizationId")
    private Organization organization;

    public Event() { }

    public Event(String name, String description, Organization organization) throws Exception {
        if (name.equals("") || name.matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if (description.equals("") || description.matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if (organization == null)
            throw new InvalidParamException();

        this.name = name;
        this.description = description;
        this.icon = "";
        this.pdfFile= "";
        this.date = Calendar.getInstance();
        this.post = false;
        this.close = false;
        this.organization = organization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public boolean isPost() {
        return post;
    }

    public void setPost(boolean post) {
        this.post = post;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}