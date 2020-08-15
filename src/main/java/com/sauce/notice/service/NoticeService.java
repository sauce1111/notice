package com.sauce.notice.service;

import com.sauce.notice.domain.notice.Notice;
import com.sauce.notice.domain.notice.NoticeRepository;
import com.sauce.notice.web.dto.NoticeResponceDto;
import com.sauce.notice.web.dto.NoticeSaveReqDto;
import com.sauce.notice.web.dto.NoticeUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveReqDto reqDto) {
        return noticeRepository.save(reqDto.toEntity()).getNoticeIdx();
    }

    @Transactional
    public Long update(Long id, NoticeUpdateReqDto reqDto) {
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id= " + id));
        notice.update(reqDto.getTitle(), reqDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public NoticeResponceDto findById(Long id) {
        Notice entity = noticeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. Id= " + id));
        return new NoticeResponceDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 없는 게시글 입니다. id = " + id));
        noticeRepository.deleteById(notice.getNoticeIdx());
    }
}
