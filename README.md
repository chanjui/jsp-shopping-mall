# Social Commerce Platform

> 무신사 스타일 쇼핑몰에 Instagram SNS 기능을 확장한 통합 소셜커머스 플랫폼

## 프로젝트 소개

이 프로젝트는 2023년 무신사가 모바일 앱으로 전환하면서 PC 버전이 사라진 것을 계기로 시작했습니다. 과거 무신사의 직관적인 PC 쇼핑 경험을 재현하면서, Instagram의 소셜 기능을 접목하여 새로운 형태의 커머스 플랫폼을 구축했습니다. 단순한 상품 구매를 넘어 사용자들이 자신만의 스타일을 공유하고 소통할 수 있는 공간을 만들고자 했습니다.

### 개인 기여
- AWS EC2/S3 기반 클라우드 아키텍처 구축
- 스냅샷(팔로우, 채팅, 댓글 시스템 구현) 구현
- git 최종 관리 
- S3 CDN 활용 이미지 전송 최적화
- 관리자 기능 멘토

## 시스템 아키텍처

### MVC 패턴 구현
```
[Client Request]
      ↓
[Front Controller (Controller/UploadController)]
      ↓
[Action Interface] ← [Action Factory]
      ↓
[Service Layer]
      ↓
[DAO Layer] ← [MyBatis]
      ↓
[Database]
```

### Action Interface 구조
```java
public interface Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException;
}
```

### 요청 처리 흐름
1. **Front Controller**
   - Controller: 일반 HTTP 요청 처리
   - UploadController: 파일 업로드 요청 처리 (최대 10MB)
   - URL 패턴에 따른 Action 매핑
   - Properties 파일 기반 동적 Action 객체 생성

2. **Action Interface**
   - 비즈니스 로직의 표준화된 인터페이스 정의
   - Command 패턴을 통한 요청 처리 캡슐화
   - 각 도메인별 Action 구현체 분리
   - JSP 뷰 경로 반환

3. **Service Layer**
   - 비즈니스 로직 처리
   - 트랜잭션 관리
   - 데이터 유효성 검증

4. **DAO Layer**
   - MyBatis를 통한 데이터 접근
   - SQL 매핑 및 실행
   - Connection Pool 관리

## 기술 스택

### 백엔드
- Java 8
- Spring Framework
- MyBatis
- MySQL
- AWS (EC2, S3)

### 프론트엔드
- HTML5/CSS3
- JavaScript
- JSP

### 개발 도구
- Eclipse
- Git
- Maven

## 주요 기능

### 쇼핑몰 기능
- 회원 관리 (가입, 로그인, 정보 수정)
- 상품 관리 (CRUD)
- 장바구니
- 주문/결제
- 포인트/쿠폰 시스템
- 리뷰/평가

### SNS 기능
- 팔로우/팔로잉
- 실시간 채팅
- 댓글/좋아요
- 프로필 관리

### 관리자 기능
- 회원 관리
- 상품 관리
- 주문 관리
- 통계/리포트

## 프로젝트 구조
```
src/
├── main/
│   ├── java/
│   │   ├── comm/          # 공통 유틸리티
│   │   ├── user/          # 사용자 관련 기능
│   │   ├── seller/        # 판매자 관련 기능
│   │   └── admin/         # 관리자 관련 기능
│   ├── resources/
│   │   ├── mybatis/       # MyBatis 설정 및 매퍼
│   │   └── properties/    # 설정 파일
│   └── webapp/
│       ├── WEB-INF/       # 웹 설정
│       └── views/         # JSP 뷰
└── test/                  # 테스트 코드
```

## 프로젝트 경험

### 아키텍처 설계
- MVC 패턴을 활용한 명확한 계층 분리
- Action 인터페이스를 통한 요청 처리 표준화
- MyBatis를 활용한 효율적인 데이터 접근

### 성능 최적화
- Connection Pool을 통한 DB 연결 관리
- S3 CDN을 활용한 이미지 전송 최적화
- 효율적인 SQL 쿼리 설계

### 보안
- 세션 기반 인증/인가
- SQL 인젝션 방지
- XSS 방어

