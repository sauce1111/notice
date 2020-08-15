package com.sauce.notice.domain.notice;

import com.sauce.notice.domain.member.Member;
import com.sauce.notice.domain.member.MemberRepository;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeRepositoryTest extends TestCase {

    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanup() {
        noticeRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void noticeSaveAndFindAll() {
        //given
        Member member = Member.builder()
            .loginId("tester")
            .loginPass("1234")
            .name("tester")
            .email("sauce0127@gmail.com")
            .build();
        String title = "test title";
        String content = "test content";

        noticeRepository.save(Notice.builder()
            .member(member)
            .title(title)
            .content(content)
            .build());

        //when
        List<Notice> noticeList = noticeRepository.findAll();

        //then
        Notice notice = noticeList.get(0);
        assertThat(notice.getMember().getLoginId()).isEqualTo("tester");
        assertThat(notice.getMember().getLoginPass()).isEqualTo("1234");
        assertThat(notice.getMember().getName()).isEqualTo("tester");
        assertThat(notice.getMember().getEmail()).isEqualTo("sauce0127@gmail.com");
        assertThat(notice.getTitle()).isEqualTo("test title");
        assertThat(notice.getContent()).isEqualTo("test content");
    }
}