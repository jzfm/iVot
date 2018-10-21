package com.iVot.Persistence.User;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create,Read,Update,Delete

import com.iVot.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

interface HelperUserRepository extends CrudRepository<User,Integer> {

    @Transactional
    void deleteByEmail(String email);

    boolean existsByEmail(String email);

    @Transactional
    User findByEmail (String email);

    @Transactional
    List<User> findAllByorganization_id(int organizationId);
}