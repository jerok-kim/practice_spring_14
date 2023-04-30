## [최주호] 스프링 강의 - Spring Boot Repository (JDBC)

### JDBC

JDBC API를 사용하여 데이터베이스와 상호 작용하는 일반적인 절차

1. JDBC 드라이버 로드
2. 데이터베이스 연결
3. SQL 쿼리 실행
4. 쿼리 실행 결과 처리
5. 데이터베이스 연결 해제

### DataSource

DataSource는 일반적으로 커넥션 풀링(connection pooling)을 구현하며, 데이터베이스와의 연결을 미리 만들어두고 필요할 때마다 커넥션을 재사용하여 성능을 향상시킨다.

또한 DataSource는 데이터베이스와의 연결을 설정하는 데 필요한 정보를 포함하며, 데이터베이스 종류, 호스트, 포트, 사용자 이름, 비밀번호 등의 정보를 설정할 수 있다.

Java에서는 javax.sql.DataSource 인터페이스를 제공한다.
