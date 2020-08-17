package com.sauce.notice.service;

import com.sauce.notice.config.auth.dto.SessionMember;
import com.sauce.notice.domain.member.Member;
import com.sauce.notice.domain.member.MemberRepository;
import com.sauce.notice.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void updateRoleMember(SessionMember member) {
        Role updateRole = Role.MEMBER;
        Member roleMember = memberRepository
            .findByEmail(member.getEmail())
            .orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 없습니다. email=" + member.getEmail()));
        roleMember.updateRoleMember(updateRole);
    }
}
