# Social Commerce Platform

> 무신사 스타일 쇼핑몰에 Instagram SNS 기능을 확장한 통합 소셜커머스 플랫폼

## 프로젝트 소개

이 프로젝트는 2023년 무신사가 모바일 앱으로 전환하면서 PC 버전이 사라진 것을 계기로 시작했습니다. 과거 무신사의 직관적인 PC 쇼핑 경험을 재현하면서, Instagram의 소셜 기능을 접목하여 새로운 형태의 커머스 플랫폼을 구축했습니다. 단순한 상품 구매를 넘어 사용자들이 자신만의 스타일을 공유하고 소통할 수 있는 공간을 만들고자 했습니다.


### 개인 기여
- AWS EC2/S3 기반 클라우드 아키텍처 구축
- 스냅샷(팔로우, 채팅, 댓글 시스템 구현) 구현
- git 최종 관리 
- S3 CDN 활용 이미지 전송 최적화

## 시스템 아키텍처

### MVC 패턴 구현
```
[Client Request]
      ↓
[Front Controller (Single Servlet)]
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
    public void execute(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException;
}
```

### 요청 처리 흐름
1. **Front Controller (Single Servlet)**
   - 모든 HTTP 요청을 단일 진입점으로 처리
   - URL 패턴에 따른 Action 매핑
   - Properties 파일 기반 동적 Action 객체 생성

2. **Action Interface**
   - 비즈니스 로직의 표준화된 인터페이스 정의
   - Command 패턴을 통한 요청 처리 캡슐화
   - 각 도메인별 Action 구현체 분리

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
- JSP/Servlet
- MyBatis
- MySQL

### 프론트엔드
- HTML5/CSS3
- JavaScript/jQuery
- Bootstrap
- Summernote

### 인프라
- AWS EC2
- AWS S3
- MySQL 8.0

### 개발 도구
- Maven
- Apache Tomcat 9
- Git

## 주요 기능

### 쇼핑몰 핵심 기능
- 상품 관리: 카테고리별 상품 등록, 수정, 삭제
- 주문 시스템: 장바구니, 결제, 배송 관리
- 회원 관리: 등급별 혜택, 포인트 적립
- 쿠폰 시스템: 할인 쿠폰 발행 및 관리

### SNS 기능
- 스냅 공유: 상품 연동 스타일 포스팅
- 팔로우 시스템: 사용자 간 구독 관계
- 실시간 채팅: 1:1 다이렉트 메시지
- 댓글 시스템: 스냅에 대한 실시간 피드백
- 좋아요 기능: 스냅 및 상품 관심 표시

### 관리자 기능
- 판매자 관리: 입점 승인, 정산 관리
- 고객 관리: 회원 등급, 포인트 관리
- 상품 관리: 카테고리, 재고 관리
- 통계 분석: 매출, 방문자 통계

## 프로젝트 구조

```
Project/
├── src/main/
│   ├── java/
│   │   ├── comm/
│   │   │   ├── control/Controller.java    # Front Controller
│   │   │   ├── dao/                      # Data Access Objects
│   │   │   ├── service/S3Uploader.java   # AWS S3 Service
│   │   │   └── vo/                       # Value Objects
│   │   ├── user/action/
│   │   │   ├── customer/                 # Customer Actions
│   │   │   └── Snap/                     # SNS Actions
│   │   ├── seller/action/                # Seller Actions
│   │   ├── admin/action/                 # Admin Actions
│   │   └── service/FactoryService.java   # MyBatis Factory
│   ├── resources/
│   │   └── mybatis/
│   │       ├── config/config.xml         # MyBatis Configuration
│   │       └── mapper/                   # SQL Mappers
│   └── webapp/
│       ├── WEB-INF/
│       │   └── action.properties         # Action Mapping
│       ├── customer/                     # Customer JSPs
│       ├── seller/                       # Seller JSPs
│       ├── admin/                        # Admin JSPs
│       └── snap/                         # SNS JSPs
└── pom.xml                              # Maven Dependencies
```

## 데이터베이스 구조

### 핵심 테이블
- customer: 고객 정보 및 등급 관리
- seller: 판매자 정보 및 브랜드 관리
- product: 상품 정보 및 재고 관리
- order: 주문 및 결제 정보
- cart: 장바구니 관리

### SNS 테이블
- board: 스냅 게시물 정보
- follow: 팔로우 관계 관리
- chat: 실시간 채팅 메시지
- reply: 댓글 및 대댓글
- like: 좋아요 관계 관리

## 설치 및 설정

### 필수 요구사항
- Java 8 이상
- Apache Tomcat 9
- Maven 3.6+
- MySQL 8.0
- AWS 계정 (EC2, S3)

### 환경 설정
1. 데이터베이스 설정
   ```sql
   CREATE DATABASE shop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

2. AWS 인증 정보 설정
   ```bash
   export AWS_ACCESS_KEY_ID=your_access_key
   export AWS_SECRET_ACCESS_KEY=your_secret_key
   ```

3. MyBatis 설정
   ```xml
   <!-- config.xml -->
   <property name="url" value="jdbc:mysql://your-ec2-ip/shop"/>
   <property name="username" value="your_username"/>
   <property name="password" value="your_password"/>
   ```

## 프로젝트 경험

### 도전과 해결
1. **단일 서블릿 라우팅**
   - 문제: 다양한 도메인의 요청을 하나의 서블릿으로 처리
   - 해결: Properties 파일 기반 동적 Action 매핑 구현

2. **이미지 관리**
   - 문제: 대용량 이미지 파일의 효율적 관리
   - 해결: AWS S3를 통한 스토리지 분리 및 구조화

3. **실시간 소셜 기능**
   - 문제: 실시간 채팅 및 알림 시스템 구현
   - 해결: AJAX 기반 비동기 통신 및 폴링 방식 적용

4. **클라우드 보안**
   - 문제: 민감한 정보의 안전한 관리
   - 해결: 환경변수 기반 설정 관리 및 VPC 구성

### 성능 최적화
- 데이터베이스 연결 풀링
- 쿼리 최적화
- 이미지 전송 최적화
- 인덱스 적용

## 팀 구성 및 기여
