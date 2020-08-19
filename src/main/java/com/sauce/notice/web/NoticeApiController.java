package com.sauce.notice.web;

import com.sauce.notice.service.NoticeService;
import com.sauce.notice.web.dto.NoticeResponceDto;
import com.sauce.notice.web.dto.NoticeSaveReqDto;
import com.sauce.notice.web.dto.NoticeUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping("/api/v1/notice")
    public Long save(@RequestBody NoticeSaveReqDto reqDto) {
        return noticeService.save(reqDto);
    }

    @PutMapping("/api/v1/notice/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticeUpdateReqDto reqDto) {
        return noticeService.update(id, reqDto);
    }

    @GetMapping("/api/v1/notice/{id}")
    public NoticeResponceDto findById(@PathVariable Long id) {
        return noticeService.findById(id);
    }

    @DeleteMapping("/api/v1/notice/{id}")
    public Long delete(@PathVariable Long id) {
        noticeService.delete(id);
        return id;
    }
}
