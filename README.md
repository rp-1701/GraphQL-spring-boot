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
    userId: ID!
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
      content
      userId
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

3. **Get Posts by User ID**
```graphql
query {
  getPostByUserId(userId: "1") {
    id
    title
    content
    userId
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

4. **Create Post**
```graphql
mutation {
  createPost(postDto: {
    title: "My First Post"
    content: "This is the content"
    userId: "1"
  }) {
    id
    title
    content
    userId
  }
}
```

5. **Update Post**
```graphql
mutation {
  updatePost(postDto: {
    id: "1"
    title: "Updated Title"
    content: "Updated content"
    userId: "1"
  }) {
    id
    title
    content
    userId
  }
}
```

6. **Delete Post**
```graphql
mutation {
  deletePost(id: "1")
}
```







