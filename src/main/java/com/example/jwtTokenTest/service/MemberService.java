package com.example.jwtTokenTest.service;

import com.example.jwtTokenTest.dto.MemberResponseDto;
import com.example.jwtTokenTest.dto.MemberUpdateDto;
import com.example.jwtTokenTest.model.Member;
import com.example.jwtTokenTest.repository.MemberRepository;
import com.example.jwtTokenTest.repository.RefreshTokenRepository;
import com.example.jwtTokenTest.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberResponseDto getMyInfo(){

        return memberRepository.findById(SecurityUtil.getLoginMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Transactional
    public void updateMyInfo(MemberUpdateDto dto){
        Member member = memberRepository.findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));

        member.updateMember(dto, passwordEncoder);
    }

    @Transactional
    public void logout(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);

        refreshTokenRepository.deleteByKey(String.valueOf(SecurityUtil.getLoginMemberId()))
                .orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Transactional
    public void deletMember() {
        Long loginMemberId = SecurityUtil.getLoginMemberId();
        if(loginMemberId == null){
            throw new RuntimeException("로그인 유저 정보가 없습니다.");
        }
        memberRepository.deleteById(loginMemberId);
    }
}
