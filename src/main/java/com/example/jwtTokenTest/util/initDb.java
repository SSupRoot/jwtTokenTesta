package com.example.jwtTokenTest.util;

import com.example.jwtTokenTest.model.Authority;
import com.example.jwtTokenTest.model.AuthorityEnum;
import com.example.jwtTokenTest.repository.AuthorityRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final AuthorityRepository authorityRepository;

        public void dbInit(){
            authorityRepository.save(new Authority(AuthorityEnum.ROLE_USER));
            authorityRepository.save(new Authority(AuthorityEnum.ROLE_ADMIN));
        }
    }
}
