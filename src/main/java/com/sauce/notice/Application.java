package com.sauce.notice;

import com.sauce.notice.domain.member.Member;
import com.sauce.notice.domain.member.MemberRepository;
import com.sauce.notice.domain.member.Role;
import com.sauce.notice.domain.notice.Notice;
import com.sauce.notice.domain.notice.NoticeRepository;
import java.util.stream.IntStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner initData(MemberRepository memberRepository,
        NoticeRepository noticeRepository) {
        return args ->
            IntStream.rangeClosed(1, 111).forEach(i -> {
                Member member = Member.builder()
                    .name("tester" + i)
                    .email("test@test" + i)
                    .role(Role.MEMBER)
                    .build();

                memberRepository.save(member);

                Notice notice = Notice.builder()
                    .memberName("tester" + i)
                    .title("test" + i)
                    .content("test content" + i)
                    .build();

                noticeRepository.save(notice);
            });
    }
}
