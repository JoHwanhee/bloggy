# Bloggy
- 오픈 API를 이용한 "블로그 검색 서비스"
- [download jar](https://github.com/JoHwanhee/bloggy/releases/download/20231129124840/bloggy-server-20231129124840.jar)

## 개요
이 프로젝트는 헥사고날 아키텍처(포트와 어댑터 아키텍처)를 따릅니다.

![](https://blog.kakaocdn.net/dn/mXkmd/btrZbrdN2fy/ViqnJnt0KBq9BPDczezOAK/img.png)


## API 명세
### 1. 블로그 검색 API
- Endpoint: GET /blogs
- Description: 키워드를 통해 블로그를 검색합니다.
- Query Parameters:
  - query: 검색할 키워드 (필수)
  - sort: 정렬 방식 (accuracy - 정확도순, recency - 최신순)
  - page: 페이지 번호 (기본값: 1)
  - size: 페이지당 항목 수 (기본값: 10)
- Response:
  - 200 OK: 성공
  - 400 Bad Request: 잘못된 요청 (예: 유효하지 않은 페이지 번호)
  - 500 Internal Server Error: 서버 오류
- 예시
```json
GET /blogs?query=test query&sort=accuracy&page=1&size=10

{
    "meta": {
        "totalCount": 153436,
        "pageableCount": 1,
        "end": false
    },
    "documents": [
        {
            "title": "블로그 차트 저품질 발생, 조회수 방문자수 감소, <b>테스트 33</b>... ",
            "contents": "<b>33</b>~34주차 블로그 상황 요약 7657, 10560, 7562, 108,425 전반적인 하락 블로그 차트 사이트 점검으로... 3m 26s 받은 공감: 14 -&gt; 30 달린 댓글: 2 -&gt; 11 이웃 증감: 3 -&gt; 1 (매주 블로그지수 <b>테스트</b> 결과는 아래 링크)",
            "url": "https://blog.naver.com/h_shuue/222973190503",
            "blogName": "흥미 따라 일상기록소",
            "thumbnailUrl": "",
            "datetime": "20230102"
        },
        // ... 
    ]
}
```
   
### 2. 인기 검색어 목록 API
- Endpoint: GET /keywords
- Description: 사용자들이 많이 검색한 키워드 목록을 제공합니다. (최대 10개, 검색 횟수 내림차순)
- Response:
  - 200 OK: 성공
  - 각 검색어와 검색된 횟수가 포함된 목록
- 예시
```json
GET /keywords
```
   
   


## 프로젝트 구조
멀티모듈 프로젝트로, 각 모듈은 다음과 같은 역할을 합니다:

- 코어
  - Domain: 애플리케이션의 비즈니스 로직과 도메인 모델을 포함합니다.
  - 아무 의존성도 가지지 않습니다.
- 포트
  - Application: 애플리케이션의 사용 사례를 정의하며, 애플리케이션의 의도된 기능과 비즈니스 규칙을 담당합니다.
  - 스프링 DI 의존성만 가집니다.
  - Domain 모듈을 의존합니다.
- 어댑터 (스프링과 각 어댑터의 의존성을 가집니다.)
  - Rest: REST API를 통한 드라이빙 어댑터로서 외부 요청을 처리합니다.
  - Retrofit: 외부 API와의 통신을 위한 드라이븐 어댑터입니다.
  - JPA: 데이터 영속성을 관리하는 드라이븐 어댑터입니다.
- 부트스트랩
  - Bootstrap: 애플리케이션의 시작점을 제공합니다.
  - ApplicationScheduler: 검색어 통계를 위한 스케줄러입니다.


## 블로그 검색 방식
- 카카오와 네이버의 블로그 검색 API를 사용합니다.
- 카카오를 1순위로 검색하며, 카카오 검색 결과가 없을 경우 네이버 검색 결과를 반환합니다.
- 모두 없을경우 빈 목록을 반환합니다.

## 어플리케이션 이벤트 사용
- 검색어 통계를 위해 검색어 쿼리 때마다 이벤트를 발생시킵니다.

```java
@Service
class BlogSearchService implements BlogSearchUsecase {

    private final BlogSearchServicePort blogSearchServicePort;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BlogSearchService(@Qualifier("blogSearchServiceContainer") BlogSearchServicePort blogSearchServicePort, ApplicationEventPublisher applicationEventPublisher) {
        this.blogSearchServicePort = blogSearchServicePort;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public BlogSearchResult search(BlogSearchCommand searchCommand) {
        BlogSearchResult result = blogSearchServicePort.search(searchCommand.getQuery(), searchCommand.getSort(), searchCommand.getPage(), searchCommand.getSize());
        applicationEventPublisher.publishEvent(new SearchEvent(searchCommand.getQuery()));

        return result;
    }
}

@Component
@AllArgsConstructor
public class SearchEventListener {

    private final SearchKeywordCommandUsecase searchedUsecase;

    @EventListener
    public void eventListener(SearchEvent searchEvent) {
        searchedUsecase.fireSearchEvent(searchEvent);
    }
}
```

## 검색어 통계 방식
- 기본적으로 통계는 View 용도의 테이블 데이터만 호출 합니다.
- 검색어는 검색어 쿼리마다 이벤트를 발생시켜 검색어 로깅을합니다.
- 스케줄러를 통해 통계 데이터를 View 용도의 테이블로 분리하여 업데이트 됩니다.
- 통계 동작 방식이 큐 방식이기때문에 기본적으로 Thread-safe 하지만, 뷰 테이블에 업데이트 시 혹시 모를 상황을 대비하여 Optimistic Locking을 사용합니다.

## 인수 테스트
- RestAssured를 사용하여 인수 테스트를 진행합니다.

## 빌드 및 실행
```
# 환경변수 세팅
export VERSION=1.0.0
export NAVER_CLIENT_ID=YOUR_NAVER_CLIENT_ID
export NAVER_CLIENT_SECRET=YOUR_NAVER_CLIENT_SECRET
export KAKAO_API_KEY=YOUR_KAKAO_API_KEY

# 전체 빌드
./gradlew clean build test

# bootJar 빌드
./gradlew -Pversion=$VERSION :bootstrap:bootJar --no-build-cache

# 실행
java -jar bootstrap/build/libs/bootstrap-$VERSION.jar
```

## 실행환경 
- JDK 17

