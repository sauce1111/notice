package com.sauce.notice.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "방문자"),
    MEMBER("ROLE_MEMBER", "회원");

    private final String key;
    private final String title;
}
