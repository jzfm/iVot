package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Event;
import com.iVot.Domain.Organization;
import com.iVot.Utilities.NotFoundException;

import java.util.Calendar;

public class EventDTO {

    @Expose
    private Integer id;
    @Expose
    private String name, description, icon, pdfFile, eventDate;
    @Expose
    private Calendar date;
    @Expose
    private boolean post, close;
    @Expose
    private Organization organization;

    public EventDTO(Event event) throws NotFoundException {
        if (event == null)
            throw new NotFoundException();

        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.icon = event.getIcon();
        this.pdfFile = event.getPdfFile();
        this.date = event.getDate();
        this.post = event.isPost();
        this.close = event.isClose();
        this.organization = event.getOrganization();
        this.eventDate = event.getEventDate();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    public String getDescription() {
        if (description == null)
            return "";
        return description;
    }

    public String getIcon() {
        if (icon == null)
            return "";
        return icon;
    }

    public String getPdfFile() {
        if (pdfFile == null)
            return "";
        return pdfFile;
    }

    public Calendar getDate() {
        return date;
    }

    public boolean isPost() {
        return post;
    }

    public boolean isClose() {
        return close;
    }

    public Organization getOrganization() {
        return organization;
    }

    public int getOrganizationId() {
        return organization.getId();
    }

    public String getEventDate() {
        return eventDate;
    }
}
