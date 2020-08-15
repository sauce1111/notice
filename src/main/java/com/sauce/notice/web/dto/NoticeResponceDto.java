package com.sauce.notice.web.dto;

import com.sauce.notice.domain.member.Member;
import com.sauce.notice.domain.notice.Notice;
import lombok.Getter;

@Getter
public class NoticeResponceDto {

    private Long noticeIdx;
    private Member member;
    private String title;
    private String content;

    public NoticeResponceDto(Notice entity) {
        this.noticeIdx = entity.getNoticeIdx();
        this.member = entity.getMember();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
