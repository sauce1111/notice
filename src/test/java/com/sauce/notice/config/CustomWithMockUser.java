package com.sauce.notice.config;

import com.sauce.notice.domain.member.Role;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = CustomWithMockUserConfigFactory.class)
public @interface CustomWithMockUser {

    String memberName() default "tester";

    Role role() default Role.MEMBER;
}
