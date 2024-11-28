package com.spapatitas.service.implementation;

import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.PermissionEntity;
import com.spapatitas.persistence.model.RoleEntity;
import com.spapatitas.persistence.model.RoleEnum;
import com.spapatitas.persistence.model.UserEntity;
import com.spapatitas.persistence.repository.ClienteRepository;
import com.spapatitas.persistence.repository.RoleRepository;
import com.spapatitas.persistence.repository.UserRepository;
import com.spapatitas.service.interfaces.IUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService, IUserEntity {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder() ;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));

        //permisos
        List<SimpleGrantedAuthority> authorityList= new ArrayList<>();

        //Obtiene los roles del usuario
        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + role)));

        //Obtiene los permisos de los roles
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }

    @Override
    public UserEntity cambioUserDTO(UserDTO userDTO) {
        RoleEntity userRole = roleRepository.findByRoleEnum(RoleEnum.USER);
        UserEntity user = UserEntity.builder()
                .username(userDTO.username())
                .password(bCryptPasswordEncoder.encode(userDTO.password()))
                .roles(Set.of(userRole))
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .isEnabled(true)
                .build();
        return user;
    }

    @Override
    public Optional<UserEntity> findByEmail(UserDTO userDTO) {
        return userRepository.findUserEntityByUsername(userDTO.username());
    }

    @Override
    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        Optional<UserEntity> userOptional = userRepository.findUserEntityByUsername(username);
        if (userOptional.isPresent()){
            UserEntity userEntity = userOptional.get();
            if(userEntity.getPassword().equals(bCryptPasswordEncoder.encode(oldPassword))){
                userEntity.setPassword(newPassword);
                userRepository.save(userEntity);
            }

        }
    }


}
