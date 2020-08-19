package com.sauce.notice.web.dto;

import com.sauce.notice.domain.notice.Notice;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class NoticeResponceDto {

    private Long noticeIdx;
    private String memberName;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public NoticeResponceDto(Notice entity) {
        this.noticeIdx = entity.getNoticeIdx();
        this.memberName = entity.getMemberName();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
