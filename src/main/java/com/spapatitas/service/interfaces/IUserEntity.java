package com.spapatitas.service.interfaces;

import com.spapatitas.DTO.UserDTO;

public interface IUserEntity {

    void save(UserDTO userDTO);

    void updatePassword(String username, String oldPassword, String newPassword);
}
