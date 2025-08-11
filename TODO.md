# Mock Server Project — TODO List (Spring Boot + WireMock + MongoDB + Angular)

This is a self-guided learning and implementation plan for building a dynamic mocking tool. The goal is to use **Spring Boot**, **embedded WireMock**, **MongoDB**, and an **Angular frontend**.

---

## 🚀 PHASE 1: Backend Foundation (Spring Boot + WireMock)

### ✅ Setup
- [ ] Initialize a Spring Boot project using [https://start.spring.io](https://start.spring.io)
  - Add dependencies: `Spring Web`, `Spring Data MongoDB`, `Lombok`, `Spring Boot DevTools`
- [ ] Add WireMock as a dependency (`wiremock-jre8-standalone`)
- [ ] Learn basic project structure and `application.yml`

### 🧠 Milestone 1: Run the app and return a hardcoded mock
- [ ] Create a simple controller: `GET /hello` → returns `"Hello World"`
- [ ] Programmatically configure and start WireMock in Spring Boot
- [ ] Register a mock: `GET /mocked/user` → returns static JSON
- [ ] Route incoming request `GET /external/user` to this mocked endpoint

---

## 🗃️ PHASE 2: MongoDB Integration

### ✅ Setup
- [ ] Connect Spring Boot to MongoDB (local or [MongoDB Atlas](https://www.mongodb.com/cloud/atlas))
- [ ] Verify connection via `application.yml` and test startup logs

### 🧠 Milestone 2: Store and manage mocks from DB
- [ ] Define a `MockDefinition` Java class with fields:
  - `id`, `serviceId`, `method`, `urlPattern`, `response` (status, body, headers)
- [ ] Create `MockDefinitionRepository` using `MongoRepository`
- [ ] Create `MockService` to:
  - Load mocks from MongoDB on startup
  - Register mocks dynamically with WireMock
- [ ] Create REST API:
  - `GET /mocks` → list all mocks
  - `POST /mocks` → create a new mock and sync it to WireMock

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
- [ ] Add basic error handling for invalid input
- [ ] Write integration tests using JUnit and MockMvc

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
