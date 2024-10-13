package com.example.zadanie2.api.model.dto;

import com.example.zadanie2.api.model.entity.UserEntity;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

/**
 * A projection interface for the {@link UserEntity} entity.
 * This projection exposes a subset of the {@link UserEntity}'s fields,
 * providing access to its ID, first name, last name, and email.
 *
 * The projection is named "withId" and can be used in Spring Data REST
 * to project the selected fields of a {@link UserEntity} in the responses.
 */
@Projection(name = "withId", types = { UserEntity.class })
public interface UserEntityDto {

    /**
     * Returns the unique identifier (UUID) of the user.
     *
     * @return the UUID of the user
     */
    UUID getId();

    /**
     * Returns the first name of the user.
     *
     * @return the first name of the user
     */
    String getFirstName();

    /**
     * Returns the last name of the user.
     *
     * @return the last name of the user
     */
    String getLastName();

    /**
     * Returns the email address of the user.
     *
     * @return the email of the user
     */
    String getEmail();
}
