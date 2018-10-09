package com.iVot.Application.Controller;

import com.iVot.Domain.Organization;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Application.DTO.OrganizationDTO;
import com.iVot.Persistence.Organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrganizationController {
    @Autowired
    private OrganizationRepository organizationRepository;

    public OrganizationDTO registerOrganization(OrganizationDTO organizationDTO) throws InvalidParamException, NotFoundException {
        Organization organization = new Organization(organizationDTO);
        organizationRepository.save(organization);
        return new OrganizationDTO(organization);
    }

    public OrganizationDTO organizationLogging(OrganizationDTO organizationDTO) throws InvalidParamException, NotFoundException {
        Organization organization = organizationRepository.getOrganizationByEmail(organizationDTO.getEmail());
        organization.checkPasswordIsCorrect(organizationDTO.getPassword());
        return new OrganizationDTO(organization);
    }

    public OrganizationDTO getOrganizationByEmail(String email) throws InvalidParamException, NotFoundException {
        Organization organization = organizationRepository.getOrganizationByEmail(email);
        return new OrganizationDTO(organization);
    }

    public OrganizationDTO getOrganizationById(int organizationId) throws NotFoundException, InvalidParamException {
        Organization organization = organizationRepository.getOrganizationById(organizationId);
        return new OrganizationDTO(organization);
    }

    public List<OrganizationDTO> listOfOrganizations() throws NotFoundException {
        List<OrganizationDTO> organizationDTOList = new ArrayList<>();
        for (Organization organization : organizationRepository.getAllOrganization()) {
            OrganizationDTO userDTO = new OrganizationDTO(organization);
            organizationDTOList.add(userDTO);
        }
        return organizationDTOList;
    }

    public OrganizationDTO updateOrganizationById(int organizationId, OrganizationDTO organizationToUpdate)
    throws NotFoundException, InvalidParamException {
        Organization organization = organizationRepository.getOrganizationById(organizationId);
        if (!organizationToUpdate.getEmail().equals("") && organizationToUpdate.getEmail().contains("@")
                && organizationToUpdate.getEmail().contains(".com"))
            organization.setEmail(organizationToUpdate.getEmail());
        if (!organizationToUpdate.getName().equals(""))
            organization.setName(organizationToUpdate.getName());
        if (!organizationToUpdate.getDescription().equals(""))
            organization.setDescription(organizationToUpdate.getDescription());
        if (!organizationToUpdate.getIcon().equals(""))
            organization.setIcon(organizationToUpdate.getIcon());
        if (!organizationToUpdate.getPassword().equals(""))
            organization.setPassword(organizationToUpdate.getPassword());
        organizationRepository.save(organization);
        return new OrganizationDTO(organization);
    }

    public OrganizationDTO updateOrganizationByEmail(String email, OrganizationDTO organizationToUpdate)
            throws NotFoundException, InvalidParamException {
        Organization organization = organizationRepository.getOrganizationByEmail(email);
        if (!organizationToUpdate.getEmail().equals("") && organizationToUpdate.getEmail().contains("@")
                && organizationToUpdate.getEmail().contains(".com"))
            organization.setEmail(organizationToUpdate.getEmail());
        if (!organizationToUpdate.getName().equals(""))
            organization.setName(organizationToUpdate.getName());
        if (!organizationToUpdate.getDescription().equals(""))
            organization.setDescription(organizationToUpdate.getDescription());
        if (!organizationToUpdate.getIcon().equals(""))
            organization.setIcon(organizationToUpdate.getIcon());
        if (!organizationToUpdate.getPassword().equals(""))
            organization.setPassword(organizationToUpdate.getPassword());
        organizationRepository.save(organization);
        return new OrganizationDTO(organization);
    }

    public OrganizationDTO removeOrganizationById(int organizationId) throws NotFoundException, InvalidParamException {
        Organization organization = organizationRepository.getOrganizationById(organizationId);
        organizationRepository.removeOrganization(organizationId);
        return new OrganizationDTO(organization);
    }

    public OrganizationDTO removeOrganizationByEmail(String email) throws InvalidParamException, NotFoundException {
        Organization organization = organizationRepository.getOrganizationByEmail(email);
        organizationRepository.removeOrganizationByEmail(email);
        return new OrganizationDTO(organization);
    }
}
