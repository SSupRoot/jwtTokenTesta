package com.example.jwtTokenTest.dto;

import com.example.jwtTokenTest.model.Authority;
import com.example.jwtTokenTest.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String email;
    private String password;
    private String memberName;

    public Member toMember(PasswordEncoder passwordEncoder, Set<Authority> authorities){
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .memberName(memberName)
                .authorities(authorities)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthenticaion() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
