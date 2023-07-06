package com.example.jwtTokenTest.model;

import com.example.jwtTokenTest.dto.MemberUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String memberName;

    private String phone;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name="member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name="authority_status", referencedColumnName = "authority_status")}
    )
    private Set<Authority> authorities = new HashSet<>();

    @Builder
    public Member(Long id, String email, String memberName, String phone, String password, Set<Authority> authorities) {
        this.id = id;
        this.email = email;
        this.memberName = memberName;
        this.phone = phone;
        this.password = password;
        this.authorities = authorities;
    }

    public void updateMember(MemberUpdateDto dto, PasswordEncoder passwordEncoder){
        if(dto.getPassword() != null) this.password = passwordEncoder.encode(dto.getPassword());
        if(dto.getMembername() != null) this.memberName = dto.getMembername();
        if(dto.getPhone() != null) this.phone = dto.getPhone();
    }

}
