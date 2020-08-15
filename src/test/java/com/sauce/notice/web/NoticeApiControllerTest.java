package com.sauce.notice.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.sauce.notice.domain.member.Member;
import com.sauce.notice.domain.member.MemberRepository;
import com.sauce.notice.domain.notice.Notice;
import com.sauce.notice.domain.notice.NoticeRepository;
import com.sauce.notice.web.dto.NoticeSaveReqDto;
import com.sauce.notice.web.dto.NoticeUpdateReqDto;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NoticeApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private MemberRepository memberRepository;

    @After
    public void cleanup() throws Exception {
        noticeRepository.deleteAll();
        memberRepository.deleteAll();

    }

    @Test
    public void savedNotice() throws Exception {
        //given
        Member member = Member.builder()
            .loginId("tester")
            .loginPass("1234")
            .name("tester")
            .email("sauce0127@gmail.com")
            .build();
        String title = "test title";
        String content = "test content";
        NoticeSaveReqDto reqDto = NoticeSaveReqDto.builder()
            .member(member)
            .title(title)
            .content(content)
            .build();

        String url = "http://localhost:" + port + "/api/v1/notice";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, reqDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Notice> allNotice = noticeRepository.findAll();
        assertThat(allNotice.get(0).getMember().getName()).isEqualTo(member.getName());
        assertThat(allNotice.get(0).getTitle()).isEqualTo(title);
        assertThat(allNotice.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void updateNotice() throws Exception {
        //given
        Member member = Member.builder()
            .loginId("tester")
            .loginPass("1234")
            .name("tester")
            .email("sauce0127@gmail.com")
            .build();
        Notice savedNotice = noticeRepository.save(
            Notice.builder()
                .member(member)
                .title("title")
                .content("content")
                .build());
        Long updateId = savedNotice.getNoticeIdx();
        String expectedTitle = "update title";
        String expectedContent = "update content";
        NoticeUpdateReqDto reqDto = NoticeUpdateReqDto.builder()
            .title(expectedTitle)
            .content(expectedContent)
            .build();

        String url = "http://localhost:" + port + "/api/v1/notice/" + updateId;
        HttpEntity<NoticeUpdateReqDto> reqEntity = new HttpEntity<>(reqDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate
            .exchange(url, HttpMethod.PUT, reqEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Notice> allNotice = noticeRepository.findAll();
        assertThat(allNotice.get(0).getMember().getName()).isEqualTo(member.getName());
        assertThat(allNotice.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(allNotice.get(0).getContent()).isEqualTo(expectedContent);
    }
}