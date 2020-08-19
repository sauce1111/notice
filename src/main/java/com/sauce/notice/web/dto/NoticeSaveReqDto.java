package com.sauce.notice.web.dto;

import com.sauce.notice.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeSaveReqDto {

    private String memberName;
    private String title;
    private String content;

    @Builder
    public NoticeSaveReqDto(String memberName, String title, String content) {
        this.memberName = memberName;
        this.title = title;
        this.content = content;
    }

    public Notice toEntity() {
        return Notice.builder()
            .memberName(memberName)
            .title(title)
            .content(content)
            .build();
    }
}
