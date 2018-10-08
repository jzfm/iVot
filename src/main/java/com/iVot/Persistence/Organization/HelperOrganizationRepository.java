package com.iVot.Persistence.Organization;

import com.iVot.Domain.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

interface HelperOrganizationRepository extends CrudRepository<Organization, Integer> {

    @Transactional
    void deleteByEmail(String email);

    boolean existsByEmail(String email);

    @Transactional
    Organization findByEmail(String email);

}
