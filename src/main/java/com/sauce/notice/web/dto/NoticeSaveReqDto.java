package com.sauce.notice.web.dto;

import com.sauce.notice.domain.member.Member;
import com.sauce.notice.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeSaveReqDto {

    private Member member;
    private String title;
    private String content;

    @Builder
    public NoticeSaveReqDto(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public Notice toEntity() {
        return Notice.builder()
            .member(member)
            .title(title)
            .content(content)
            .build();
    }
}
