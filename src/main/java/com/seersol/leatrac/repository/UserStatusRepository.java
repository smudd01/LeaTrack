package com.seersol.leatrac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seersol.leatrac.entity.UserStatus;

public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {

}
