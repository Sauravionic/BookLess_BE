package io.bookless.BookLess.Repository;

import io.bookless.BookLess.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
