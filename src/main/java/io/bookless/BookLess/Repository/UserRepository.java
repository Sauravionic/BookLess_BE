package io.bookless.BookLess.Repository;

import io.bookless.BookLess.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
