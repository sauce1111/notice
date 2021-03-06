package com.sauce.notice.service;

import com.sauce.notice.domain.notice.Notice;
import com.sauce.notice.domain.notice.NoticeRepository;
import com.sauce.notice.web.dto.NoticeListResponseDto;
import com.sauce.notice.web.dto.NoticeResponceDto;
import com.sauce.notice.web.dto.NoticeSaveReqDto;
import com.sauce.notice.web.dto.NoticeUpdateReqDto;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    @Transactional(readOnly = true)
    public List<NoticeListResponseDto> findAll() {
        return noticeRepository.findAll().stream()
            .map(NoticeListResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<NoticeListResponseDto> findAll(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Direction.DESC, "noticeIdx"));
        NoticeListResponseDto convertedNoticeListDto = null;
        Page<Notice> pagedNoticeAllList = noticeRepository.findAll(pageable);
        return convertedNoticeListDto.of(pagedNoticeAllList);
    }
}
