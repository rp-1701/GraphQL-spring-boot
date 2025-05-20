# GraphQL Spring Boot Demo

This project demonstrates the implementation of a GraphQL API using Spring Boot, showcasing User-Post management.

## Getting Started

### Prerequisites
- JDK 17 or later
- Maven 3.x

### Installation Steps
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd graphQL-springBoot
   ```

2. Build the project:
   ```bash
   ./mvnw clean install
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Accessing the Application
- 🎮 GraphQL Playground: http://localhost:8080/graphiql
- 🗄 H2 Database Console: http://localhost:8080/h2-console
  ```properties
  JDBC URL: jdbc:h2:mem:userDb
  Username: sa
  Password: [leave empty]
  ```

## API Documentation

### GraphQL Schema Types

#### UserDto
```graphql
type UserDto {
    id: ID
    firstname: String
    lastname: String
    email: String
    posts: [PostDto]
}
```

#### PostDto
```graphql
type PostDto {
    id: ID
    title: String!
    content: String!
    user: UserDto
}
```

### Available Operations

#### Queries

1. **Get All Users**
```graphql
query {
  getAllUser {
    id
    firstname
    lastname
    email
    posts {
      id
      title
    }
  }
}
```

2. **Get User by ID**
```graphql
query {
  getUserById(id: "1") {
    id
    firstname
    lastname
    email
    posts {
      title
      content
    }
  }
}
```

#### Mutations

1. **Create User**
```graphql
mutation {
  createUser(userDto: {
    firstname: "John"
    lastname: "Doe"
    email: "john@example.com"
  }) {
    id
    firstname
    lastname
    email
  }
}
```

2. **Update User**
```graphql
mutation {
  updateUser(userDto: {
    id: "1"
    firstname: "Johnny"
    email: "johnny@example.com"
  }) {
    id
    firstname
    lastname
    email
  }
}
```

3. **Delete User**
```graphql
mutation {
  deleteUser(id: "1") {
    message
  }
}
```





