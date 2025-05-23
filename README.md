# Social Commerce Platform

> 무신사 스타일 쇼핑몰에 Instagram SNS 기능을 확장한 통합 소셜커머스 플랫폼

## Overview

본 프로젝트는 기존 이커머스의 한계를 극복하고자 **쇼핑과 소셜 네트워킹을 결합**한 플랫폼입니다. 무신사의 직관적인 쇼핑 경험에 Instagram의 소셜 기능을 접목하여, 사용자들이 상품을 구매할 뿐만 아니라 자신만의 스타일을 공유하고 소통할 수 있는 공간을 제공합니다.

## Tech Stack

### Backend
- **Java 8** - Core Language
- **JSP/Servlet** - Web Framework  
- **MyBatis** - ORM Framework
- **MySQL** - Database

### Frontend  
- **HTML5/CSS3** - Markup & Styling
- **JavaScript/jQuery** - Client-side Logic
- **Bootstrap** - UI Framework
- **Summernote** - WYSIWYG Editor

### Cloud Infrastructure
- **AWS EC2** - Database Hosting
- **AWS S3** - Image Storage & CDN
- **MySQL 8.0** - Remote Database

### Development Tools
- **Maven** - Dependency Management
- **Apache Tomcat 9** - Application Server
- **Git** - Version Control

## Architecture

### System Architecture
```
Client (Browser)
    ↓
Apache Tomcat Server
    ↓
Front Controller (Single Servlet)
    ↓
Command Pattern (Action Classes)
    ↓
MyBatis (Data Access Layer)
    ↓
AWS EC2 MySQL Database
    ↓
AWS S3 (Image Storage)
```

### Design Pattern
- **Front Controller Pattern**: 단일 진입점을 통한 모든 요청 처리
- **Command Pattern**: 비즈니스 로직의 캡슐화 및 확장성 확보
- **DAO Pattern**: 데이터 접근 로직 분리
- **MVC Pattern**: 관심사 분리를 통한 유지보수성 향상

## Key Features

### E-Commerce Core
- **상품 관리**: 카테고리별 상품 등록, 수정, 삭제
- **주문 시스템**: 장바구니, 결제, 배송 관리
- **회원 관리**: 등급별 혜택, 포인트 적립
- **쿠폰 시스템**: 할인 쿠폰 발행 및 관리

### Social Networking (SNS)
- **스냅 공유**: 상품 연동 스타일 포스팅
- **팔로우 시스템**: 사용자 간 구독 관계
- **실시간 채팅**: 1:1 다이렉트 메시지
- **댓글 시스템**: 스냅에 대한 실시간 피드백
- **좋아요 기능**: 스냅 및 상품 관심 표시

### Admin Dashboard
- **판매자 관리**: 입점 승인, 정산 관리
- **고객 관리**: 회원 등급, 포인트 관리  
- **상품 관리**: 카테고리, 재고 관리
- **통계 분석**: 매출, 방문자 통계

## Infrastructure Design

### Database Architecture
- **Remote Database**: AWS EC2 인스턴스에 MySQL 8.0 설치
- **Connection Pool**: HikariCP를 통한 연결 최적화
- **Security**: VPC 내 프라이빗 서브넷 구성

### Storage Strategy
- **Image Management**: AWS S3 버킷을 도메인별로 구조화
  ```
  my-home-shoppingmall-bucket/
  ├── product/{category}/{seller_no}/
  ├── snap/{user_id}/
  ├── profile/
  ├── review/
  └── seller-icons/
  ```
- **CDN**: S3를 통한 이미지 전송 최적화
- **Security**: IAM 역할 기반 접근 제어

## Project Structure

```
Project/
├── src/main/
│   ├── java/
│   │   ├── comm/
│   │   │   ├── control/Controller.java    # Front Controller
│   │   │   ├── dao/                       # Data Access Objects
│   │   │   ├── service/S3Uploader.java    # AWS S3 Service
│   │   │   └── vo/                        # Value Objects
│   │   ├── user/action/
│   │   │   ├── customer/                  # Customer Actions
│   │   │   └── Snap/                      # SNS Actions
│   │   ├── seller/action/                 # Seller Actions
│   │   ├── admin/action/                  # Admin Actions
│   │   └── service/FactoryService.java    # MyBatis Factory
│   ├── resources/
│   │   └── mybatis/
│   │       ├── config/config.xml          # MyBatis Configuration
│   │       └── mapper/                    # SQL Mappers
│   └── webapp/
│       ├── WEB-INF/
│       │   └── action.properties          # Action Mapping
│       ├── customer/                      # Customer JSPs
│       ├── seller/                        # Seller JSPs
│       ├── admin/                         # Admin JSPs
│       └── snap/                          # SNS JSPs
└── pom.xml                               # Maven Dependencies
```

## Database Schema

### Core Tables
- **customer**: 고객 정보 및 등급 관리
- **seller**: 판매자 정보 및 브랜드 관리
- **product**: 상품 정보 및 재고 관리
- **order**: 주문 및 결제 정보
- **cart**: 장바구니 관리

### SNS Tables  
- **board**: 스냅 게시물 정보
- **follow**: 팔로우 관계 관리
- **chat**: 실시간 채팅 메시지
- **reply**: 댓글 및 대댓글
- **like**: 좋아요 관계 관리

## Installation & Setup

### Prerequisites
- Java 8 or higher
- Apache Tomcat 9
- Maven 3.6+
- MySQL 8.0
- AWS Account (EC2, S3)

### Environment Configuration
1. **Database Setup**
   ```sql
   CREATE DATABASE shop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

2. **AWS Credentials**
   ```bash
   export AWS_ACCESS_KEY_ID=your_access_key
   export AWS_SECRET_ACCESS_KEY=your_secret_key
   ```

3. **MyBatis Configuration**
   ```xml
   <!-- config.xml -->
   <property name="url" value="jdbc:mysql://your-ec2-ip/shop"/>
   <property name="username" value="your_username"/>
   <property name="password" value="your_password"/>
   ```

### Build & Deploy
```bash
# Clone repository
git clone https://github.com/your-repo/social-commerce-platform.git

# Build project
mvn clean compile package

# Deploy to Tomcat
cp target/shop.war $TOMCAT_HOME/webapps/
```

## Technical Challenges & Solutions

### Challenge 1: Single Servlet Routing
**Problem**: 다양한 도메인의 요청을 하나의 서블릿으로 처리

**Solution**: 
- Properties 파일 기반 동적 Action 매핑
- Reflection을 활용한 런타임 객체 생성
- Command 패턴으로 비즈니스 로직 캡슐화

### Challenge 2: Image Management at Scale
**Problem**: 대용량 이미지 파일의 효율적 관리

**Solution**:
- AWS S3를 통한 스토리지 분리
- 도메인별 디렉토리 구조화
- UUID 기반 파일명 충돌 방지

### Challenge 3: Real-time Social Features
**Problem**: 실시간 채팅 및 알림 시스템 구현

**Solution**:
- AJAX 기반 비동기 통신
- 폴링 방식을 통한 실시간성 구현
- 읽음 상태 관리를 통한 UX 개선

### Challenge 4: Cloud Infrastructure Security
**Problem**: 민감한 정보의 안전한 관리

**Solution**:
- 환경변수 기반 설정 관리
- VPC 내 프라이빗 서브넷 DB 배치
- IAM 역할 기반 S3 접근 제어

## Performance Optimization

### Database Optimization
- **Connection Pooling**: MyBatis 내장 풀링 활용
- **Query Optimization**: 동적 쿼리 최적화
- **Indexing**: 자주 조회되는 컬럼에 인덱스 적용

### Image Delivery Optimization
- **CDN**: S3 CloudFront를 통한 전역 배포
- **Lazy Loading**: 스크롤 기반 이미지 지연 로딩
- **Image Compression**: 업로드 시 자동 압축

## Team & Contribution

### Team Structure
- **Project Leader & Infrastructure**: 클라우드 아키텍처 설계, SNS 기능 개발
- **Frontend Developer**: UI/UX 설계 및 구현
- **Backend Developer**: 쇼핑몰 핵심 기능 개발
- **QA Engineer**: 테스트 및 품질 관리

### Personal Contribution
- **Infrastructure Design**: AWS EC2/S3 기반 클라우드 아키텍처 구축
- **SNS Feature Development**: 팔로우, 채팅, 댓글 시스템 전체 구현
- **Technical Leadership**: 팀원 대상 AWS 기술 교육 및 MyBatis 구조 설계
- **Performance Optimization**: S3 CDN 활용 이미지 전송 최적화

## Future Enhancements

### Technical Roadmap
- **Microservices Architecture**: 도메인별 서비스 분리
- **Container Orchestration**: Docker/Kubernetes 도입
- **Real-time Communication**: WebSocket 기반 실시간 통신
- **Search Optimization**: Elasticsearch 도입

### Business Features
- **AI Recommendation**: 머신러닝 기반 상품 추천
- **Live Commerce**: 실시간 스트리밍 쇼핑
- **Mobile App**: React Native 기반 모바일 앱
- **Global Expansion**: 다국가 서비스 지원

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Contact**: [your-email@example.com](mailto:your-email@example.com)  
**LinkedIn**: [Your LinkedIn Profile](https://linkedin.com/in/yourprofile)  
**Portfolio**: [Your Portfolio Website](https://yourportfolio.com)
