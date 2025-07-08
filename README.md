# Java Console Chat Server 💬

**간단한 콘솔 기반 채팅 서버 & 클라이언트 애플리케이션**

- 멀티 클라이언트 동시 접속 지원 ✅  
- 브로드캐스트 메시지 전송 ✅  
- 클라이언트 고유 이름 부여 (예: `client 1`, `client 2`...) ✅  
- `exit` 입력 시 종료 가능 ✅  

---

## 📁 프로젝트 구조

```
.
├── Server.java       # 서버 코드 (멀티스레드 브로드캐스트)
├── Client.java       # 클라이언트 코드 (입출력 스레드 분리)
└── README.md         # 프로젝트 설명
```

---

## 🚀 실행 방법

### 1. 컴파일

```bash
javac Server.java Client.java
```

### 2. 서버 실행

```bash
java Server
```

### 3. 클라이언트 실행 (새 터미널에서)

```bash
java Client
```

> 클라이언트는 여러 개 동시에 실행 가능  
> 예: 터미널 여러 개 열어서 각각 `java Client` 입력

---

## 🖥️ 사용 방법

- 클라이언트에 메시지를 입력하면 서버가 **모든 클라이언트에게 브로드캐스트**합니다.
- 메시지를 입력 후 `Enter`를 누르세요.
- `exit`을 입력하면 연결이 종료됩니다.

---

## 📌 예시

```
send message : 안녕
client 1: 안녕

send message : 반가워요
client 2: 반가워요
```

---

## 🛠 개발 환경

- Java 8 이상
- 텍스트 기반 콘솔

---

## 📄 라이선스

MIT License
