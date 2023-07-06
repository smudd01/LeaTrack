package com.seersol.leatrac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seersol.leatrac.entity.AppUser;
import com.seersol.leatrac.entity.Company;
import com.seersol.leatrac.entity.Contact;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	
	 @Query("select u from AppUser u " +
		        "where lower(u.userName) like lower(concat('%', :searchTerm, '%')) ")
		    List<AppUser> search(@Param("searchTerm") String searchTerm);
}
