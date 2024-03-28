package com.spring.bank.service;

import com.spring.bank.dao.AuthorityRepository;
import com.spring.bank.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void save(Authority theAuthority) {

        authorityRepository.save(theAuthority);
    }
}
