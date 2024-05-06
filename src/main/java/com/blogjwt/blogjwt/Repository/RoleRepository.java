package com.blogjwt.blogjwt.Repository;

import com.blogjwt.blogjwt.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
