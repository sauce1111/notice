package com.sauce.notice.web.dto;

import com.sauce.notice.domain.notice.Notice;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class NoticeListResponseDto {

    private Long noticeIdx;
    private String title;
    private String content;
    private LocalDateTime modifiedDate;

    public NoticeListResponseDto(Notice entity) {
        this.noticeIdx = entity.getNoticeIdx();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }
}
