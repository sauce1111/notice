package com.sauce.notice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeUpdateReqDto {

    private String title;
    private String content;

    @Builder
    public NoticeUpdateReqDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
