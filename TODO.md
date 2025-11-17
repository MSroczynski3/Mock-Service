# Mock Server Project — TODO List (Spring Boot + WireMock + MongoDB + Angular)

This is a self-guided learning and implementation plan for building a dynamic mocking tool. The goal is to use **Spring Boot**, **embedded WireMock**, **MongoDB**, and an **Angular frontend**.

---

## 🚀 PHASE 1: Backend Foundation (Spring Boot + WireMock)

### ✅ Setup
- [x] Initialize a Spring Boot project using [https://start.spring.io](https://start.spring.io)
  - Add dependencies: `Spring Web`, `Spring Data MongoDB`, `Lombok`, `Spring Boot DevTools`
- [x] Add WireMock as a dependency (`wiremock-standalone` - using standalone version)
- [x] Learn basic project structure and `application.properties`

### 🧠 Milestone 1: Run the app and return a hardcoded mock
- [x] Programmatically configure and start WireMock in Spring Boot (`WireMockConfig.java`)
- [x] Register a mock: `GET /mocked/user` → returns static JSON
- [x] Route incoming request `GET /external/user` to this mocked endpoint (`ExternalController.java`)

---

## 🗃️ PHASE 2: MongoDB Integration

### ✅ Setup
- [x] Spring Data MongoDB dependency added to `build.gradle.kts`
- [ ] Connect Spring Boot to MongoDB (local or [MongoDB Atlas](https://www.mongodb.com/cloud/atlas))
  - ⚠️ **TODO**: Add MongoDB connection string to `application.properties`
- [ ] Verify connection via `application.properties` and test startup logs

### 🧠 Milestone 2: Store and manage mocks from DB
- [ ] Define a `MockDefinition` Java class with fields:
  - `id`, `serviceId`, `method`, `urlPattern`, `response` (status, body, headers)
- [ ] Create `MockDefinitionRepository` using `MongoRepository`
- [x] Create `MockService` (exists but currently works with WireMock directly, not MongoDB)
  - [ ] Load mocks from MongoDB on startup
  - [x] Register mocks dynamically with WireMock (working, but not persisted)
- [x] Create REST API:
  - [x] `GET /api/mocks` → list all mocks (returns WireMock stubs, not DB records)
  - [x] `POST /api/mocks` → create a new mock and sync it to WireMock (not persisted to DB)

---

## 🖥️ PHASE 3: Angular Frontend

### ✅ Setup
- [ ] Install Node.js and Angular CLI
- [ ] Create a new app: `ng new mock-manager --routing=true --style=css`

### 🧠 Milestone 3: Create UI to manage mocks
- [ ] Create Angular service for backend API:
  - `getMocks()`, `createMock()`
- [ ] Create form to submit a new mock definition
- [ ] Create list view of all existing mocks

---

## ⚙️ PHASE 4: Workflow & Polish

- [ ] Optional: Dockerize backend + MongoDB using `docker-compose.yml`
- [ ] Add `dev` and `test` Spring profiles to control mocking behavior
- [x] Add basic error handling for invalid input (`GlobalExceptionHandler.java` exists)
- [x] Write integration tests using JUnit and MockMvc (test files exist: `MockControllerTest.java`, etc.)
- [ ] Decide how to handle multiple mocks with the same URL pattern

---

## 📘 Learning Resources

| Topic                  | Link |
|------------------------|------|
| Spring Boot Basics     | https://www.baeldung.com/spring-boot-start |
| WireMock Java API      | https://wiremock.org/docs/ |
| MongoDB with Spring    | https://www.baeldung.com/spring-data-mongodb |
| Angular Fundamentals   | https://angular.io/start |

---

## 🧭 Pro Tips

- Keep sessions focused on one milestone or feature
- Use `git commit` after each working feature
- Ask for help when stuck — describe your issue with specific input/output
- Favor progress over polish early on
