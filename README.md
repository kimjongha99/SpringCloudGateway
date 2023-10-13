로드 밸런싱: Eureka와 Ribbon 또는 Spring Cloud LoadBalancer를 함께 사용하여 클라이언트 측 로드 밸런싱을 수행할 수 있습니다. 이렇게 하면 요청을 여러 인스턴스에 분산시켜 처리할 수 있어 시스템의 내구성과 성능을 향상시킬 수 있습니다.
Hystrix 또는 Resilience4j를 사용한 회로 차단기 패턴 구현: 이 패턴은 장애가 전체 시스템으로 전파되는 것을 방지하는데 도움이 됩니다. 일반적으로 Hystrix는 Spring Cloud Netflix 프로젝트의 일부였지만 현재는 유지 보수 모드입니다. 따라서 Resilience4j가 좋은 대안일 수 있습니다.
Config Server 설정: 중앙에서 모든 마이크로서비스의 설정을 관리할 수 있는 Spring Cloud Config Server를 설정하면, 각 서비스의 환경 설정 변경 없이도 동적으로 설정 값을 변경할 수 있습니다.
Tracing: Sleuth와 Zipkin 등을 사용하여 마이크로서비스간 호출 추적 정보를 취합하고 분석하는 기능을 추가할 수 있습니다.
Security: Spring Security와 OAuth2/JWT 등 인증/인가 메커니즘이 필요합니다. API Gateway에서 이러한 보안 메커니즘이 중요한 역할을 합니다.
Containerization and Orchestration: Docker와 Kubernetes 같은 컨테이너화 및 오케스트레이션 도구를 사용하여 마이크로서비스 배포 및 관리 프로세스를 자동화 할 수 있습니다.








로드 밸런싱은 시스템의 부하를 분산시키는 기법입니다. 여기서는 Service A와 Service B 사이에 트래픽을 분산시키는 것이 목적입니다. 이를 위해 Spring Cloud에서 제공하는 Ribbon 또는 Spring Cloud LoadBalancer를 사용할 수 있습니다.

그러나, 단순한 로드 밸런싱만으로는 "Service A의 트래픽이 많이 발생하면 Service B로 보내게 하기"라는 조건을 만족시키기 어렵습니다. 이를 위해서는 서비스의 상태(예: 응답 시간, 에러율 등)를 모니터링하고 그에 따라 동적으로 로드 밸런싱 전략을 변경하는 메커니즘이 필요합니다.

서킷 브레이커 패턴은 한 서비스가 다른 서비스에 의존성을 가지고 있을 때, 해당 의존성 서비스가 장애 상태일 경우 이를 감지하고 장애 전파를 방지하기 위한 기법입니다. Hystrix나 Resilience4J 같은 라이브러리들은 이 패턴을 구현해주며, 실패한 요청에 대한 폴백(fallback) 로직도 제공합니다.

따라서 아래와 같은 방식으로 접근할 수 있습니다:

API Gateway에서 들어오는 요청들을 Ribbon 혹은 Spring Cloud LoadBalancer로 로드밸런싱하여 Service A와 Service B로 분산시킵니다.
Hystrix나 Resilience4J 등의 서킷 브레이커를 사용하여 각각의 서비스(Service A, Service B)의 상태를 모니터링합니다.
만약 Service A가 과부화되어 장애 상태로 진입한다면(예: 지연 시간 초과, 에러율 증가 등), 해당 요청들을 폴백(fallback) 로직으로 처리합니다.
폴백(fallback) 로직에서 요청들을 Service B로 전달하는 코드를 작성합니다.
이러한 방식으로 Service A의 트래픽이 과부하 상태일 때, API Gateway가 자동으로 요청을 Service B로 전달하도록 구현할 수 있습니다.
