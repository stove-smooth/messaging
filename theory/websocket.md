# Web Socket
- 웹 소켓 프로토콜인 RFC 6455는 단일 TCP 연결을 통해 클라이언트와 서버 사이에 양방향 통신 채널을 설정하는 표준화된 방법을 제공한다.
- HTTP와는 다른 TCP 프로토콜이지만 포트 80 및 443을 사용해 HTTP를 통해 작동한다.
- 기존 방화벽 규칙을 재사용할 수 있도록 설계됨
- 웹 소켓의 상호작용은 HTTP upgrade 헤더를 사용하여 업그레이드하거나 웹 소켓 프로토콜로 전환하는 HTTP 요청으로부터 시작된다.

    ```
    GET /spring-websocket-portfolio/portfolio HTTP/1.1
    Host: localhost:8080
    Upgrade: websocket 1️⃣
    Connection: Upgrade 2️⃣
    Sec-WebSocket-Key: Uc9l9TMkWGbHFD2qnFHltg==
    Sec-WebSocket-Protocol: v10.stomp, v11.stomp
    Sec-WebSocket-Version: 13
    Origin: http://localhost:8080
    ```
    
    - 1️⃣ : Header
    - 2️⃣ : Connection 사용

<br>
  
- 웹 소켓 지원 서버는 일반적인 200 상태 코드 대신 다음과 같이 요청과 유사한 response를 반환한다.

    ```
    HTTP/1.1 101 Switching Protocols 1️⃣
    Upgrade: websocket
    Connection: Upgrade
    Sec-WebSocket-Accept: 1qVdfYHU9hPOl4JYYNXF623Gzn0=
    Sec-WebSocket-Protocol: v10.stomp
    ```

    - 1️⃣ : 프로토콜 스위치

- handshake 성공 후, HTTP upgrade 요청의 기반이 되는 TCP 소켓은 클라이언트와 서버가 모두 메세지를 주고 받을 수 있도록 열린 상태를 유지한다.

## HTTP vs Web Socket
- WebSocket은 HTTP 호환 가능하도록 설계되었고 HTTP 요청으로 시작하지만 두 프로토콜의 아키텍처와 어플리케이션 프로그래밍 모델이 매우 다르다.
- 일반적인 HTTP + REST의 Application은 여러 URL로 모델링하고 서버는 URL, HTTP Method, Header를 기반으로 요청을 라우팅한다.
- 반면 Web socket은 초기 연결을 위한 하나의 URL만 존재, HTTP와 달리 메세지 내용에 의미를 규정하지 않는 저수준 전송 프로토콜이다.

    - c.f.
        - 웹 소켓은 연결 요청에 대해 HTTP를 통해 Switching 및 HandShaking이 이루어진다.
        - TCP는 이진(Binary)데이터만 주고 받을 수 있으나, 웹 소켓은 Binary와 Text 데이터도 주고 받을 수 있다.

## When to Use Web Socket
- Web socket을 활용하면 동적이고 실시간 대화를 만들어낼 수 있다. 하지만 무조건 Web socket을 쓸 필요는 없고 Ajax, HTTP 스트리밍, polling, Long polling과 같은 방법으로 효과적인 서비스를 제공 할 수 있다.

    - <b>Polling</b> : 
        
        ![polling](https://user-images.githubusercontent.com/59307414/145519769-11b7d026-156a-4ae3-8275-a69cd169f9b2.png)
        <i>출처 : https://rubberduck-debug.tistory.com/123</i>
        
        클라이언트가 평범한 HTTP Request를 서버로 계속 요청해 이벤트 내용을 전달받는 방식. 가장 쉬운 방법이지만 클라이언트가 지속적으로 Request를 요청하기 때문에 클라이언트의 수가 많아지면 서버의 부담이 급증한다. HTTP Request Connection을 맺고 끊는 것 자체가 부담이 많은 방식이고, 클라이언트에서 실시간 정도의 빠른 응답을 기대하기 어렵다.

    - <b>Long Polling</b> : 

        ![long polling](https://user-images.githubusercontent.com/59307414/145519899-0eabd854-8962-40aa-b02b-caf83f290e63.png)
        <i>출처 : https://rubberduck-debug.tistory.com/123</i>
        
        클라이언트에서 서버로 일단 HTTP Request를 요청한다. 이 상태로 계속 기다리다가 서버에서 해당 클라이언트로 전달할 이벤트가 있다면 그 순간 Response 메세지를 전달하며 연결이 종료된다. 곧이어 클라이언트가 다시 HTTP Request를 요청해 서버의 다음 이벤트를 기다리는 방식. polling보다 서버의 부담이 줄겠으나, 클라이언트로 보내는 이벤트들의 시간간격이 좁다면 polling과 별 차이 없게 되며, 다수의 클라이언트에게 동시에 이벤트가 발생될 경우 서버의 부담이 급증한다.

    - <b>Streaming</b> : 

        ![streaming](https://user-images.githubusercontent.com/59307414/145519932-94409e8e-d68a-4838-80a9-76ae66046c38.png)
        <i>출처 : https://rubberduck-debug.tistory.com/123</i>
        
        Long Polling과 마찬가지로 클라이언트에서 서버로 HTTP Request를 요청한다. 서버에서 클라이언트로 이벤트를 전달할 때 해당 요청을 해제하지 않고 필요한 메세지만 보내기(Flush)를 반복하는 방식. Long Polling과 비교하여 서버에 메세지를 보내지 않고도 다시 HTTP Request 연결을 하지 않아도 되어 부담이 경감된다고 한다.

- 예를 들어 뉴스나 메일, SNS 피드는 동적으로 업데이트하는 것은 맞지만 몇 분마다 업데이트하는 것이 좋다. 반면에 협업, 게임, 금융 앱은 훨씬 더 실시간으로 작용해야 한다.
- 대기 시간만이 결정적인 요소는 아니다. 메세지의 크기가 상대적으로 적은 경우 (예. 네트워크 장애 모니터링) HTTP streaming이나 polling이 효과적이 솔루션이 될 수 있다. Web socket을 사용하는데 가장 적합한 경우는 <b>짧은 대기 시간</b>, <b>고주파수</b>, <b>대용량</b>의 조합인 경우이다.
- 실시간으로 반응하기 위해 무지성으로 Web socket을  사용하는 것은 효과적이지 않을 수 있다.

## Reference
- [Spring Docs WebSockets](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket)
- [Spring Web Socket 설명 포스트](https://velog.io/@hanblueblue/%EB%B2%88%EC%97%AD-Spring-4-Spring-WebSocket)
- [Spring MVC, 웹 소켓](https://dev-gorany.tistory.com/212)
- [Polling, Long polling, websocket](https://rubberduck-debug.tistory.com/123)