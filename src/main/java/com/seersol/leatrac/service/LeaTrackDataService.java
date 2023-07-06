package com.seersol.leatrac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seersol.leatrac.entity.AppUser;
import com.seersol.leatrac.entity.Company;
import com.seersol.leatrac.entity.Contact;
import com.seersol.leatrac.entity.Status;
import com.seersol.leatrac.entity.UserStatus;
import com.seersol.leatrac.repository.AppUserRepository;
import com.seersol.leatrac.repository.CompanyRepository;
import com.seersol.leatrac.repository.ContactRepository;
import com.seersol.leatrac.repository.StatusRepository;
import com.seersol.leatrac.repository.UserStatusRepository;

@Service
public class LeaTrackDataService {

    private final ContactRepository contactRepository;
    private final AppUserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;
    private final UserStatusRepository userStatusRepository;

    public LeaTrackDataService(ContactRepository contactRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository,
                      AppUserRepository userRepository,
                      UserStatusRepository userStatusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.userStatusRepository=userStatusRepository;
    }

    public List<Contact> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(stringFilter);
        }
    }

    public long countContacts() {
        return contactRepository.count();
    }

    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    public void saveContact(Contact contact) {
        if (contact == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        contactRepository.save(contact);
    }
    
    public void saveUser(AppUser user) {
        if (user == null) {
            System.err.println("User is null. Are you sure you have connected your form to the application?");
            return;
        }
        userRepository.save(user);
    }
    public List<AppUser> findAllUsers(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return userRepository.findAll();
        } else {
            return userRepository.search(stringFilter);
        }
    }
    
    public void deleteUser(AppUser user) {
        userRepository.delete(user);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }
    
    public List<AppUser> findAllUsers(){
    	return userRepository.findAll();
    }
    public List<UserStatus> findAllUserStatus(){
    	return userStatusRepository.findAll();
    }
}
