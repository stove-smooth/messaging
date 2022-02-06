# Spring integration TCP server-client
- spring integration reference guide : https://docs.spring.io/spring-integration/reference/html/
- spring integration tcp/udp support : https://docs.spring.io/spring-integration/reference/html/ip.html#ip
- spring integration introduction (baeldung) : https://www.baeldung.com/spring-integration
    - adapter (endpoint summary) : https://docs.spring.io/spring-integration/docs/5.1.0.M1/reference/html/endpoint-summary.html
    - tcp adapter : https://docs.spring.io/spring-integration/docs/5.1.0.M1/reference/html/ip.html#tcp-adapters
    - web socket adapter : https://docs.spring.io/spring-integration/docs/5.1.0.M1/reference/html/web-sockets.html#web-socket-inbound-adapter
- spring integration tutorial : https://github.com/eugenp/tutorials/tree/master/spring-integration

- spring integration keyword : https://www.linkedin.com/pulse/spring-boot-spring-integration-baki-hayat
- spring integration example : https://github.com/spring-projects/spring-integration-samples
    - https://github.com/spring-projects/spring-integration-samples/blob/main/advanced/dynamic-tcp-client/src/main/java/org/springframework/integration/samples/dynamictcp/DynamicTcpClientApplication.java
- spring integration transaction : https://www.baeldung.com/spring-integration-transaction
- TCP 서버 만들기
    - server : https://gogo-jjm.tistory.com/57
    - client : https://gogo-jjm.tistory.com/58?category=854015
- TcpSendingMessageHandler 예제 : http://useof.org/java-open-source/org.springframework.integration.ip.tcp.TcpSendingMessageHandler
- 패킷 유실 시 TCP/UDP 현상 : https://thebook.io/006884/ch02/07/
---
- MessageTimeoutException: Timed out 에러
    ```
    o.s.i.ip.tcp.TcpOutboundGateway          : Tcp Gateway exception
    org.springframework.integration.MessageTimeoutException: Timed out waiting for response
    ```
    - https://stackoverflow.com/questions/42667403/spring-integration-tcp