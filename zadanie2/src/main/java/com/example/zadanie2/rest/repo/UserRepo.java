package com.example.zadanie2.rest.repo;

import com.example.zadanie2.api.model.entity.UserEntity;
import com.example.zadanie2.api.model.dto.UserEntityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for {@link UserEntity}, providing access to user-related
 * data. This interface extends {@link JpaRepository} to provide basic CRUD operations
 * and includes custom queries for additional operations.
 *
 * The repository uses Spring Data REST to expose these operations as REST endpoints.
 * The default projection is {@link UserEntityDto}.
 */
@RepositoryRestResource(excerptProjection = UserEntityDto.class)
public interface UserRepo extends JpaRepository<UserEntity, UUID> {

    /**
     * Finds users whose first names contain the given string, ignoring case.
     * This query is exposed as a REST endpoint at `/userEntities/search/byFirstName`.
     *
     * @param firstName the string to search for in user first names
     * @return a list of users whose first names contain the given string
     */
    @RestResource(path = "byFirstName")
    List<UserEntity> findByFirstNameContainingIgnoreCase(@Param("firstName") String firstName);

    /**
     * Finds users whose last names contain the given string, ignoring case.
     * This query is exposed as a REST endpoint at `/userEntities/search/byLastName`.
     *
     * @param lastName the string to search for in user last names
     * @return a list of users whose last names contain the given string
     */
    @RestResource(path = "byLastName")
    List<UserEntity> findByLastNameContainingIgnoreCase(@Param("lastName") String lastName);

    /**
     * Finds users whose email addresses contain the given string, ignoring case.
     * This query is exposed as a REST endpoint at `/userEntities/search/byEmail`.
     *
     * @param email the string to search for in user email addresses
     * @return a list of users whose email addresses contain the given string
     */
    @RestResource(path = "byEmail")
    List<UserEntity> findByEmailContainingIgnoreCase(@Param("email") String email);
}
