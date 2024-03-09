package com.wamazon.app.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
//A repository allows CRUD operations. This is a simple one the needs no custom operations.
public interface UserRepository extends JpaRepository<UserModel, Long>{
    @Query("SELECT u FROM UserModel u WHERE u.username = :username AND u.password = :password")
    UserModel findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
