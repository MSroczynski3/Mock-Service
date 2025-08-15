# Mock Service

A Spring Boot application for creating and managing HTTP mocks using WireMock and MongoDB.

## 🚀 Tech Stack

- **Java 24** - Latest Java features and performance improvements
- **Spring Boot 3.5.4** - Modern Spring framework with enhanced capabilities
- **Gradle (Kotlin DSL)** - Type-safe build automation
- **WireMock 3.13.1** - Powerful HTTP service virtualization
- **MongoDB** - Flexible document storage for mock configurations
- **Spring Boot Actuator** - Production-ready monitoring and health checks
- **Lombok** - Reduced boilerplate code
- **Testcontainers** - Integration testing with real services

## 📋 Prerequisites

- **Java 24** or higher
- **MongoDB** (optional for basic testing - can be configured later)
- **Git** for version control

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

Run the test suite:
```bash
./gradlew test
```

The project includes:
- Unit tests for core functionality
- Integration tests with Testcontainers
- MongoDB integration testing

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
- Ensure Java 24 is installed and `JAVA_HOME` is set correctly

**Application won't start - MongoDB connection error:**
- MongoDB connection is optional for basic functionality
- Configure `spring.data.mongodb.uri` in application.properties if needed

**Port 8080 already in use:**
- Change the port in application.properties: `server.port=8081`

---

*Happy Mocking! 🎭*
