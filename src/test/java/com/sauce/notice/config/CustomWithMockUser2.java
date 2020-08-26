package com.sauce.notice.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(value = "tester", roles = "MEMBER")
public @interface CustomWithMockUser2 {

}
