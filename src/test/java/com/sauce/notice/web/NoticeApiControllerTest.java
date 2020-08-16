package com.sauce.notice.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
    .springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sauce.notice.domain.notice.Notice;
import com.sauce.notice.domain.notice.NoticeRepository;
import com.sauce.notice.web.dto.NoticeSaveReqDto;
import com.sauce.notice.web.dto.NoticeUpdateReqDto;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @After
    public void cleanup() throws Exception {
        noticeRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "MEMBER")
    public void savedNotice() throws Exception {
        //given
        String title = "test title";
        String content = "test content";
        NoticeSaveReqDto reqDto = NoticeSaveReqDto.builder()
            .title(title)
            .content(content)
            .build();
        String url = "http://localhost:" + port + "/api/v1/notice";

        //when
        mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(reqDto)))
            .andExpect(status().isOk());

        //then
        List<Notice> allNotice = noticeRepository.findAll();
        assertThat(allNotice.get(0).getTitle()).isEqualTo(title);
        assertThat(allNotice.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles = "MEMBER")
    public void updateNotice() throws Exception {
        //given
        Notice savedNotice = noticeRepository.save(
            Notice.builder()
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

        //when
        mvc.perform(put(url)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(reqDto)))
            .andExpect(status().isOk());

        //then
        List<Notice> allNotice = noticeRepository.findAll();
        assertThat(allNotice.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(allNotice.get(0).getContent()).isEqualTo(expectedContent);
    }
}