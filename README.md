# Phorest Tech Test

## Note

This isn't a complete solution. I'm going to stop now for transparency as we're around the 4-hour mark. 

Here's some of the things that I would have done if I had more time:
- Implemented the rest of the requirements, specifically the config endpoints and Part 3
- Add additional tests for edge cases and error handling
- Add API documentation using Swagger/OpenAPI
- Added repository with persistence to a database (PostgreSQL) and Flyway for database migrations/versioning
- Added some sort of authentication/authorization to the APIs, (there seems two be two distinct roles of ADMIN and PLAYER)
- @Validation around the config and payloads for the APIs
- Dockerised and added docker-compose for local development and testing
- Current implementation doesn't support multiple machines.

## Development approach

I'm going to develop the solution using TDD and outside in approach. I'll start by writing the tests for the APIs, then
I'll implement the service layer, then the repository layer (if we get that far :)).

Here's some decisions I took around development:
- Layered architecture with controller, service and repository layers.
- TDD
- Added GlobalExceptionHandler as a catch-all so we don't leak any system info (common security vulnerability) and to provide a consistent error response to the client.
- Versioned API (actual "v1" could be refactored to a constant in the future)

## AI Usage

Most of the AI usage for this was for code completion and some suggestions via Google around best practices and testing.


## Running the Application

I'm using Java 27 and Maven 3.9.10.

To run the application, you can use the following command:

```bash
./mvnw spring-boot:run
```

To test the endpoint using curl:

```bash
curl -X POST http://localhost:8080/api/v1/fruit-machine/play
```

Example:

```bash
> curl -sX POST http://localhost:8080/api/v1/fruit-machine/play | jsonlint
{
  "slots": [
    "BLACK",
    "BLACK",
    "BLACK",
    "WHITE"
  ],
  "result": "SMALL_WIN",
  "currentFloat": 25,
  "spinsRemaining": 100,
  "spinCost": 5,
  "winnings": 75
}
```

To run the tests, you can use the following command:

```bash
./mvnw test
```

Example:

```bash
➜  phorest-techtest-paulnulty git:(main) ✗ ./mvnw test
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::staticFieldBase has been called by com.google.inject.internal.aop.HiddenClassDefiner (file:/Users/paul/.m2/wrapper/dists/apache-maven-3.9.10/e5402a93/lib/guice-5.1.0-classes.jar)
WARNING: Please consider reporting this to the maintainers of class com.google.inject.internal.aop.HiddenClassDefiner
WARNING: sun.misc.Unsafe::staticFieldBase will be removed in a future release
WARNING: Final field _cipher in class org.sonatype.plexus.components.sec.dispatcher.DefaultSecDispatcher has been mutated reflectively by class org.eclipse.sisu.bean.BeanPropertyField in unnamed module @433d61fb (file:/Users/paul/.m2/wrapper/dists/apache-maven-3.9.10/e5402a93/lib/org.eclipse.sisu.inject-0.9.0.M4.jar)
WARNING: Use --enable-final-field-mutation=ALL-UNNAMED to avoid a warning
WARNING: Mutating final fields will be blocked in a future release unless final field mutation is enabled
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< com.phorest:phorest >-------------------------
[INFO] Building  0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.5.0:resources (default-resources) @ phorest ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO]
[INFO] --- compiler:3.15.0:compile (default-compile) @ phorest ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.5.0:testResources (default-testResources) @ phorest ---
[INFO] skip non existing resourceDirectory /Users/paul/workspace/phorest-techtest-paulnulty/src/test/resources
[INFO]
[INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ phorest ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- surefire:3.5.6:test (default-test) @ phorest ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.phorest.controller.FruitMachineControllerIntegrationTests
16:05:36.007 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.phorest.controller.FruitMachineControllerIntegrationTests]: FruitMachineControllerIntegrationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
16:05:36.060 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.phorest.PhorestApplication for test class com.phorest.controller.FruitMachineControllerIntegrationTests
16:05:36.090 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.phorest.controller.FruitMachineControllerIntegrationTests]: FruitMachineControllerIntegrationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
16:05:36.091 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.phorest.PhorestApplication for test class com.phorest.controller.FruitMachineControllerIntegrationTests

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.1.3)

2026-11-23T16:05:36.213Z  INFO 3967 --- [phorest] [           main] c.FruitMachineControllerIntegrationTests : Starting FruitMachineControllerIntegrationTests using Java 27 with PID 3967 (started by paul in /Users/paul/workspace/phorest-techtest-paulnulty)
2026-11-23T16:05:36.214Z  INFO 3967 --- [phorest] [           main] c.FruitMachineControllerIntegrationTests : No active profile set, falling back to 1 default profile: "default"
2026-11-23T16:05:36.488Z  INFO 3967 --- [phorest] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 0 (http)
2026-11-23T16:05:36.492Z  INFO 3967 --- [phorest] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-11-23T16:05:36.492Z  INFO 3967 --- [phorest] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.27]
2026-11-23T16:05:36.505Z  INFO 3967 --- [phorest] [           main] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 280 ms
2026-11-23T16:05:36.791Z  INFO 3967 --- [phorest] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring TestDispatcherServlet ''
2026-11-23T16:05:36.791Z  INFO 3967 --- [phorest] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-11-23T16:05:36.792Z  INFO 3967 --- [phorest] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2026-11-23T16:05:36.811Z  INFO 3967 --- [phorest] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 61359 (http) with context path '/'
2026-11-23T16:05:36.814Z  INFO 3967 --- [phorest] [           main] c.FruitMachineControllerIntegrationTests : Started FruitMachineControllerIntegrationTests in 0.704 seconds (process running for 1.177)
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.967 s -- in com.phorest.controller.FruitMachineControllerIntegrationTests
[INFO] Running com.phorest.controller.FruitMachineControllerUnitTests
2026-11-23T16:05:36.910Z  INFO 3967 --- [phorest] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [com.phorest.controller.FruitMachineControllerUnitTests]: FruitMachineControllerUnitTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-11-23T16:05:36.914Z  INFO 3967 --- [phorest] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration com.phorest.PhorestApplication for test class com.phorest.controller.FruitMachineControllerUnitTests
2026-11-23T16:05:36.914Z  INFO 3967 --- [phorest] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [com.phorest.controller.FruitMachineControllerUnitTests]: FruitMachineControllerUnitTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-11-23T16:05:36.916Z  INFO 3967 --- [phorest] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration com.phorest.PhorestApplication for test class com.phorest.controller.FruitMachineControllerUnitTests

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.1.3)

2026-11-23T16:05:36.932Z  INFO 3967 --- [phorest] [           main] c.p.c.FruitMachineControllerUnitTests    : Starting FruitMachineControllerUnitTests using Java 27 with PID 3967 (started by paul in /Users/paul/workspace/phorest-techtest-paulnulty)
2026-11-23T16:05:36.933Z  INFO 3967 --- [phorest] [           main] c.p.c.FruitMachineControllerUnitTests    : No active profile set, falling back to 1 default profile: "default"
2026-11-23T16:05:37.010Z  INFO 3967 --- [phorest] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-11-23T16:05:37.010Z  INFO 3967 --- [phorest] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-11-23T16:05:37.010Z  INFO 3967 --- [phorest] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
2026-11-23T16:05:37.014Z  INFO 3967 --- [phorest] [           main] c.p.c.FruitMachineControllerUnitTests    : Started FruitMachineControllerUnitTests in 0.094 seconds (process running for 1.377)
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.121 s -- in com.phorest.controller.FruitMachineControllerUnitTests
[INFO] Running com.phorest.model.FruitMachineTests
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.026 s -- in com.phorest.model.FruitMachineTests
[INFO] Running com.phorest.service.FruitMachineServiceTests
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.025 s -- in com.phorest.service.FruitMachineServiceTests
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 26, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.968 s
[INFO] Finished at: 2026-11-23T16:05:37Z
[INFO] ------------------------------------------------------------------------
```

## Gaps According to Copilot

- **Play accounting:** The play cost is deducted only on a loss and is not added to the machine’s float.
- **Free plays:** Shortfall credits need a defined unit and conversion to free plays. The response does not separately report free plays credited for a play.
- **Configuration and state:** The configuration endpoints are placeholders and return HTTP 500. There is no endpoint to read the current machine state.
- **Generalisation:** Slot count can be configured at startup, but the available colours and adjacent-match threshold are fixed.
- **Concurrency and persistence:** Machine state is held in memory, resets on restart and is not protected against concurrent requests.
- **Error handling:** Insufficient credits and other errors currently receive a generic HTTP 500 response.
- **Testing:** Additional coverage is needed for winning-play costs, repeated plays, shortfalls with a play cost greater than one, and concurrent requests.
- **Build:** The Maven wrapper is incomplete; an installed Maven is required.