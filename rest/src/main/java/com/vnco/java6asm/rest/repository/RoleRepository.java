package com.vnco.java6asm.rest.repository;

import com.vnco.java6asm.rest.entity.user.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Long> {
}
