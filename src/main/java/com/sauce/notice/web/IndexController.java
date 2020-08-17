package com.sauce.notice.web;

import com.sauce.notice.config.auth.dto.SessionMember;
import com.sauce.notice.service.MemberService;
import com.sauce.notice.service.NoticeService;
import com.sauce.notice.web.dto.NoticeListResponseDto;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final NoticeService noticeService;
    private final MemberService memberService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        if (member != null) {
            List<NoticeListResponseDto> noticeAllList = noticeService.findAll();
            memberService.updateRoleMember(member);
            model.addAttribute("memberName", member.getName());
            model.addAttribute("notice", noticeAllList);
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
