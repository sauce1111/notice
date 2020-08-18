package com.sauce.notice.web;

import com.sauce.notice.config.auth.dto.SessionMember;
import com.sauce.notice.domain.notice.Notice;
import com.sauce.notice.service.MemberService;
import com.sauce.notice.service.NoticeService;
import com.sauce.notice.web.dto.NoticeListResponseDto;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final NoticeService noticeService;
    private final MemberService memberService;
    private final HttpSession httpSession;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String index(Model model, @PageableDefault Pageable pageable) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        if (member != null) {
            memberService.updateRoleMember(member);
            Page<NoticeListResponseDto> noticeAllListPageable = noticeService.findAll(pageable);
            model.addAttribute("memberName", member.getName());
            model.addAttribute("notice", noticeAllListPageable);
            return "index";
        } else {
            return "login";
        }
    }

    @GetMapping("/notice/save")
    public String noticeSave(Model model) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        model.addAttribute("member", member);
        return "notice-save";
    }

}
