package com.example.jwtTokenTest.dto;

import com.example.jwtTokenTest.model.Authority;
import com.example.jwtTokenTest.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private String email;
    private String memberName;
    private String phone;
    private Set<Authority> authoritites;

    public static MemberResponseDto of(Member member){
        return new MemberResponseDto(member.getEmail(), member.getMemberName(), member.getPhone(), member.getAuthorities());
    }
}
