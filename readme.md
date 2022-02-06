# TCP 못하겠어~

## Overview
- 마이크로서비스 아키텍처에서 서비스간 통신을 하기 위함
- 상태관리 서버를 구현하기 위해서 <strong>연결이 유지되는 통신 방법</strong>을 찾기 위해 아래 방법을 구현 및 확인 진행
    - 현재 클라이언트 사이드에서 상태관리 서버와 직접 연결되어 있지 않은 상태에서 채팅서버만 연결
    - 채팅서버가 클라이언트의 접속 상태를 상태관리 서버에 계속해서 전달

## Content

- [Spring integration](./spring-integration/)
    - spring integration TCP/UDP 지원 기능 활용
    - cf. [Spring integration을 통해 살펴본 메시징 세계](https://www.slideshare.net/WangeunLee/spring-integration-47185594)

- [Vert.X](./Vertx/)
    - vertx는 원래 비동기 네트워크 프레임워크인데 모듈처럼 활용해서 서비스간 연결을 웹소켓을 통해 유지하고자 함
        - Vert.X Documentation : https://vertx.io/docs/
        - Vert.X 간단 설명 : https://bcho.tistory.com/860
    - <i>시도는 해봤지만 잘 안됨.. 시간이 더 필요함</i>

- [Websocket](./websocket/)
    - 순수 자바 websocket을 통해 연결해보자
    - cf. https://github.com/TooTallNate/Java-WebSocket


---
## cf.
- [web socket](./theory/websocket.md)
- [netty](./theory/netty.md)
- 프레임워크 성능 측정 지표 : https://www.techempower.com/benchmarks/#section=data-r9&hw=peak&test=plaintext