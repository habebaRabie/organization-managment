package com.ntg.organization.organization.repository;


import com.ntg.organization.organization.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUserName(String name);

}
