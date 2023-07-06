package com.example.jwtTokenTest.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Authority {

    @Id @Column(name = "authority_status")
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authorityStatus;

    public String getAuthorityStatus() {
        return this.authorityStatus.toString();
    }

    @Builder
    public Authority(AuthorityEnum authorityStatus) {
        this.authorityStatus = authorityStatus;
    }
}
