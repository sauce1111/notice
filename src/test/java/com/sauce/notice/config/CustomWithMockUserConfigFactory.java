package com.sauce.notice.config;

import com.sauce.notice.config.auth.CustomOAuth2UserService;
import com.sauce.notice.domain.member.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class CustomWithMockUserConfigFactory implements
    WithSecurityContextFactory<CustomWithMockUser> {

    @Override
    public SecurityContext createSecurityContext(CustomWithMockUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Member member = Member.builder()
            .name(customUser.memberName())
            .email("test@test.com")
            .role(customUser.role())
            .build();
        context.setAuthentication((Authentication) member);
        return context;
    }

}
