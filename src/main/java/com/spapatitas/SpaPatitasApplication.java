package com.spapatitas;

import com.spapatitas.persistence.model.PermissionEntity;
import com.spapatitas.persistence.model.RoleEntity;
import com.spapatitas.persistence.model.RoleEnum;
import com.spapatitas.persistence.model.UserEntity;
import com.spapatitas.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpaPatitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaPatitasApplication.class, args);
	}

//	@Bean
//	CommandLineRunner init(UserRepository userRepository) {
//		return args -> {
//			//crear permisos
//			PermissionEntity createPermission = PermissionEntity.builder()
//					.name("CREATE")
//					.build();
//			PermissionEntity readPermission = PermissionEntity.builder()
//					.name("READ")
//					.build();
//			PermissionEntity updatePermission = PermissionEntity.builder()
//					.name("UPDATE")
//					.build();
//			PermissionEntity deletePermission = PermissionEntity.builder()
//					.name("DELETE")
//					.build();
//
//			//crear roles
//			RoleEntity adminRole = RoleEntity.builder()
//					.roleEnum(RoleEnum.ADMIN)
//					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
//					.build();
//			RoleEntity userRole = RoleEntity.builder()
//					.roleEnum(RoleEnum.USER)
//					.permissions(Set.of(createPermission, readPermission))
//					.build();
//
//			//Crear usuarios
//			UserEntity userAdmin = UserEntity.builder()
//					.username("admin")
//					.password("$2a$10$eeTu3yyhB9G8J1ZzFTEF8ORHLLh4XV9iKq0nhOHSPP5gt2zOi42dy")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialNoExpired(true)
//					.roles(Set.of(adminRole))
//					.build();
//
//			UserEntity userMaigu = UserEntity.builder()
//					.username("MaiGu")
//					.password("$2a$10$eeTu3yyhB9G8J1ZzFTEF8ORHLLh4XV9iKq0nhOHSPP5gt2zOi42dy")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialNoExpired(true)
//					.roles(Set.of(userRole))
//					.build();
//
//			userRepository.saveAll(List.of(userAdmin, userMaigu));
//		};
//	}
}
