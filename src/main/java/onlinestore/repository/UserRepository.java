package onlinestore.repository;

import onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query(value = "select * from user where username =: username", nativeQuery = true)
//    Optional<User> findByUsername (@Param("username") String username);

    Optional<User> findByUsername(String username);
}
