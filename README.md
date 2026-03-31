[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=23350689)
# CISC 191 — Module 4 Lab
## JDBC Basics with Maven and Embedded H2

### Goal
Learn JDBC basics using:
- exceptions
- file / database persistence concepts
- embedded H2
- repository pattern
- Maven project structure
- a one-to-many relationship

### Required Package Prefix
All classes must use packages under:
`edu.sdccd.cisc191`

### Architecture
This project uses a structure that can later be replaced with JPA:

- `model` → plain Java objects
- `repository` → persistence contracts + JDBC implementations
- `service` → business logic
- `util` → database configuration / initialization
- `app` → application entry point

### Relationship
A `Student` has many `Course` objects.

Database tables:
- `students`
- `courses` with `student_id` foreign key → `students.id`

### Your Tasks
Implement:
- `Student`
- `Course`
- `StudentRepository`
- `CourseRepository`
- `JdbcStudentRepository`
- `JdbcCourseRepository`
- `StudentService`
- `DatabaseConfig`
- `DatabaseInitializer`
- `Main`

### Required Features
Your application must:
1. create both tables if they do not exist
2. insert at least 3 students
3. insert at least 3 courses linked to students
4. list all students
5. find one student by ID
6. list courses for a student
7. update one student GPA
8. delete one student
9. print results before and after changes

### Validation Rules
Student:
- `id > 0`
- `name` is not null/blank
- `gpa` is between `0.0` and `4.0`

Course:
- `id > 0`
- `title` is not null/blank
- `studentId > 0`

### Run Tests
```bash
mvn test
```

### Notes
- Use `PreparedStatement` instead of building SQL by string concatenation
- Use `try-with-resources`
- Throw `IllegalArgumentException` for invalid model data
- Runtime wrapping of `SQLException` is acceptable for this lab
