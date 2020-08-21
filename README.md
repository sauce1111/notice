# 공지사항 웹 애플리케이션


### 주요 기능

1. Google, naver 를 통한 로그인
2. 공지 사항 추가 및 수정/삭제
3. 로그인 시 전체 공지 목록 조회

### 실행 순서

1. src/main/java/com/sauce/notice/Application.java 내 main 메소드 실행

2. 로그인

![로그인](https://user-images.githubusercontent.com/44424542/90861080-2743b580-e3c6-11ea-9216-e885f5c54345.gif)

3. 공지 작성 : 로그인 시 권한은 MEMBER로 변경 되었으나 세션 최신화를 위해 로그아웃 이후 다시 로그인 하여 글 등록 진행

![글등록](https://user-images.githubusercontent.com/44424542/90862390-53f8cc80-e3c8-11ea-96d8-f842378e5a86.gif)

4. 수정 및 삭제

