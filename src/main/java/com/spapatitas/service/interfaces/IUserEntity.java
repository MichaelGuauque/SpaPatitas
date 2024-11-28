package com.spapatitas.service.interfaces;

import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.UserEntity;

import java.util.Optional;

public interface IUserEntity {

    void save(UserEntity userEntity);

    void updatePassword(String username, String oldPassword, String newPassword);

    UserEntity cambioUserDTO(UserDTO userDTO);

    Optional<UserEntity> findByEmail(UserDTO userDTO);

    Optional<UserEntity> findById(long id);
}
