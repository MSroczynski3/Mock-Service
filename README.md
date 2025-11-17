# Mock Service

A Spring Boot application for creating and managing HTTP mocks using WireMock and MongoDB.

## 🚀 Tech Stack

- **Java 21 (LTS)** - Long-term support Java version with modern features
- **Spring Boot 3.5.4** - Modern Spring framework with enhanced capabilities
- **Gradle 9.0 (Kotlin DSL)** - Type-safe build automation
- **WireMock 3.13.1** - Powerful HTTP service virtualization
- **MongoDB** - Flexible document storage for mock configurations
- **Spring Boot Actuator** - Production-ready monitoring and health checks
- **Lombok** - Reduced boilerplate code
- **Testcontainers** - Integration testing with real services

## 📋 Prerequisites

- **Java 21 (LTS)** - Required for building and running the application
- **Docker Desktop** - Required for running tests (uses Testcontainers for MongoDB)
- **MongoDB** (optional for basic testing - can be configured later)
- **Git** for version control

### Java Installation
The project uses Java 21 via Gradle's toolchain feature. Gradle will automatically detect Java 21 if installed. If you have multiple Java versions installed, ensure Java 21 is available in your system PATH or set `JAVA_HOME` to point to your Java 21 installation.

### Docker Setup
The project uses Testcontainers for integration testing, which requires Docker to be running:

1. **Install Docker Desktop** for your operating system
2. **Start Docker Desktop** and ensure it's running
3. **Verify Docker is running**: `docker info` should not show connection errors

⚠️ **Important**: Tests will fail if Docker is not running, as they use MongoDB containers for testing.

## 🏃‍♂️ Quick Start

### 1. Clone the Repository
```bash
git clone <your-repo-url>
cd Mock-Service
```

### 2. Build the Project
```bash
./gradlew build
```

### 3. Run the Application
```bash
./gradlew bootRun
```

### 4. Verify It's Working
Open another terminal and run:
```bash
curl http://localhost:8080/actuator/health
```

Expected response:
```json
{"status":"UP"}
```

## 🛠️ Development Commands

| Command | Description |
|---------|-------------|
| `./gradlew build` | Build the project and run tests |
| `./gradlew bootRun` | Start the application with hot reload |
| `./gradlew test` | Run all tests |
| `./gradlew clean` | Clean build artifacts |
| `./gradlew clean build` | Clean and build from scratch |

## 🔗 API Endpoints

### Health & Monitoring
- **Health Check**: `GET /actuator/health`
- **Application Info**: `GET /actuator/info`
- **Metrics**: `GET /actuator/metrics`

### Mock Management
*Coming soon - Mock CRUD operations*

## 🏗️ Project Structure

```
Mock-Service/
├── src/
│   ├── main/
│   │   ├── java/com/example/mockservice/
│   │   │   └── MockServiceApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/example/mockservice/
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## ⚙️ Configuration

The application uses `application.properties` for configuration. Key settings:

- **Server Port**: `8080` (default)
- **MongoDB**: Auto-configuration (when available)
- **Actuator**: Health endpoints enabled

## 🧪 Testing

### Prerequisites for Testing
⚠️ **Docker must be running** before executing tests, as they use Testcontainers to spin up MongoDB containers.

### Running Tests
```bash
# Ensure Docker Desktop is running first
docker info

# Run all tests
./gradlew test
```

### Test Structure
The project includes:
- Unit tests for core functionality
- Integration tests with Testcontainers
- MongoDB integration testing using Docker containers

### Troubleshooting Tests
If tests fail with Docker-related errors:
1. Ensure Docker Desktop is installed and running
2. Verify Docker connectivity: `docker info`
3. On Windows: Check that Docker Desktop is using the correct engine (WSL2 or Hyper-V)

## 🐳 Docker Support

*Coming soon - Docker containerization*

## 📚 Features Roadmap

- [ ] Basic WireMock integration
- [ ] MongoDB mock storage
- [ ] REST API for mock management
- [ ] Web UI for mock configuration
- [ ] Request/Response logging
- [ ] Mock templates and scenarios
- [ ] API documentation with Swagger

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin feature-name`
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Troubleshooting

### Common Issues

**Build fails with Java version error:**
- Ensure Java 21 (LTS) is installed
- Gradle will automatically use Java 21 for compilation (configured in `build.gradle.kts`)
- If Java 21 is not detected, verify it's installed and in your system PATH
- You can check installed Java versions: `java -version`
- Note: Gradle 9.0 can run with Java 17+ (including Java 25), but the project code requires Java 21

**Tests fail with Docker/Testcontainers errors:**
- Ensure Docker Desktop is installed and running
- Run `docker info` to verify Docker connectivity
- On Windows: Make sure Docker Desktop is using the correct engine

**WireMock HttpServerFactory errors:**
- This project uses `wiremock-standalone` for better Java 21+ compatibility
- If you see "Jetty 11 is not present" errors, ensure you're using the standalone version
- The standalone version includes all necessary HTTP server dependencies

**Application won't start - MongoDB connection error:**
- MongoDB connection is optional for basic functionality
- Configure `spring.data.mongodb.uri` in application.properties if needed

**Port 8080 already in use:**
- Change the port in application.properties: `server.port=8081`

---

*Happy Mocking! 🎭*
