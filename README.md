# 📋 Enterprise Todo Application

A modern, full-stack todo application with advanced task management features, built with Spring Boot and React.

## ✨ Features

### 🔐 **Authentication System**
- **User Registration & Login**: Username-based authentication with JWT tokens
- **Secure Sessions**: Token-based authentication with 24-hour expiration
- **User Isolation**: Each user sees only their own collections and tasks

### 📚 **Collection Management**
- **Organize Tasks**: Group related tasks into collections
- **CRUD Operations**: Create, read, update, and delete collections
- **User-Specific**: Collections are private to each user

### 📝 **Advanced Task Types**
- **📋 Standard Tasks**: Basic todo items with completion tracking
- **⏰ Deadline Tasks**: Tasks with due dates and failure detection
- **💰 Saving Goals**: Financial goals with progress tracking and money addition

### 🎯 **Smart Task Features**
- **Edit & Delete**: Full CRUD operations for all tasks
- **Completion Logic**: 
  - Manual completion via checkbox
  - Auto-completion for saving goals when target reached
  - Deadline failure detection with visual indicators
- **Visual Status**: Color-coded task states (failed, completed, in-progress)
- **Real-time Updates**: Live progress bars and status changes

### 🎨 **Modern UI/UX**
- **Responsive Design**: Works on desktop and mobile
- **Interactive Components**: Modals, forms, and confirmation dialogs
- **Visual Feedback**: Progress bars, status indicators, and animations
- **Intuitive Navigation**: Seamless flow between collections and tasks

## 🛠 Tech Stack

### Backend
- **Framework**: Spring Boot 3.1.5
- **Security**: Spring Security with JWT authentication
- **Database**: MySQL 8.0.33 (Production) / H2 (Development)
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven
- **Java Version**: 17+

### Frontend
- **Framework**: React 18 with Vite
- **Styling**: Tailwind CSS
- **HTTP Client**: Axios
- **State Management**: React Context API
- **Build Tool**: Vite

### Database Schema
- **Users**: Authentication and user management
- **TodoCollections**: Task organization
- **Todos**: Polymorphic task entities with type-specific fields

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- MySQL 8.0+ (for production) or H2 (for development)
- Maven 3.6+

### 🎬 Quick Start

**Option 1: Use Development Scripts**
```bash
# Windows PowerShell
.\start-dev.ps1

# Windows Batch
start-dev.bat
```

**Option 2: Manual Setup**

#### 🔧 Backend Setup

1. **Configure Database** (MySQL for production):
```sql
CREATE DATABASE todoapp_db;
CREATE USER 'todoapp_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON todoapp_db.* TO 'todoapp_user'@'localhost';
FLUSH PRIVILEGES;
```

2. **Update Configuration** (`backend/src/main/resources/application.properties`):
```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/todoapp_db
spring.datasource.username=todoapp_user
spring.datasource.password=your_password
```

3. **Start Backend**:
```bash
cd backend
./mvnw clean package -DskipTests
java -jar target/todoapp-backend-0.0.1-SNAPSHOT.jar
```

Backend runs on: **http://localhost:8080**

#### 🎨 Frontend Setup

1. **Install Dependencies**:
```bash
cd frontend
npm install
```

2. **Start Development Server**:
```bash
npm run dev
```

Frontend runs on: **http://localhost:3000**

## 🔌 API Documentation

### 🔐 Authentication Endpoints
```http
POST /api/auth/register
Content-Type: application/json
{
  "username": "john_doe",
  "name": "John Doe", 
  "email": "john@example.com",
  "password": "securepassword"
}

POST /api/auth/login
Content-Type: application/json
{
  "username": "john_doe",
  "password": "securepassword"
}
```

### 📚 Collection Endpoints
```http
GET    /api/collections              # Get user's collections
POST   /api/collections              # Create new collection
PUT    /api/collections/{id}         # Update collection
DELETE /api/collections/{id}         # Delete collection
```

### 📝 Task Endpoints
```http
GET    /api/collections/{id}/tasks   # Get tasks in collection
POST   /api/collections/{id}/tasks   # Create new task
PUT    /api/tasks/{id}               # Update task
DELETE /api/tasks/{id}               # Delete task
POST   /api/tasks/{id}/add-money     # Add money to saving goal
```

### 📋 Task Creation Examples

**Standard Task:**
```json
{
  "title": "Complete project documentation",
  "description": "Write comprehensive README",
  "type": "STANDARD"
}
```

**Deadline Task:**
```json
{
  "title": "Submit tax returns",
  "description": "Annual tax filing",
  "type": "DEADLINE",
  "dueDate": "2025-04-15"
}
```

**Saving Goal:**
```json
{
  "title": "Vacation Fund",
  "description": "Save for summer vacation",
  "type": "SAVING",
  "targetAmount": 5000,
  "currentAmount": 0
}
```

## 🎯 Usage Examples

### Creating Your First Collection
1. **Register/Login** to the application
2. **Click "New Collection"** on the dashboard
3. **Enter collection details** (name and description)
4. **Start adding tasks** to your collection

### Managing Tasks
1. **Select a collection** from the dashboard
2. **Add tasks** using the "+ Add Task" button
3. **Choose task type**: Standard, Deadline, or Saving Goal
4. **Edit tasks** using the ✏️ icon
5. **Delete tasks** using the 🗑️ icon
6. **Mark complete** using the checkbox

### Saving Goals
1. **Create a saving goal** with target amount
2. **Add money** using the "+ Add Money" button
3. **Track progress** with the visual progress bar
4. **Auto-completion** when target is reached

## 🚀 Deployment Guide

### 🌩️ **Option 1: Railway + Vercel (Recommended)**

**Backend on Railway:**
1. Push code to GitHub
2. Connect Railway to your repository
3. Deploy backend folder
4. Add environment variables:
   ```
   JWT_SECRET=your-256-bit-secret-key
   DATABASE_URL=your-mysql-connection-string
   ```

**Frontend on Vercel:**
1. Connect Vercel to your repository
2. Deploy frontend folder
3. Set environment variable:
   ```
   REACT_APP_API_URL=https://your-backend.railway.app/api
   ```

### 🐳 **Option 2: Docker Deployment**

```bash
# Clone and build
git clone <your-repo>
cd todoapp2

# Start with Docker Compose
docker-compose up --build -d
```

### ☁️ **Option 3: AWS Deployment**

**Backend**: Elastic Beanstalk with RDS MySQL
**Frontend**: S3 + CloudFront
**Database**: RDS MySQL instance

## 🔧 Development

### 🏗️ Project Structure
```
todoapp2/
├── backend/                 # Spring Boot application
│   ├── src/main/java/
│   │   └── com/todoapp/
│   │       ├── controller/  # REST controllers (SRP)
│   │       ├── service/     # Business logic services (SRP, DIP)
│   │       │   └── strategy/# Strategy pattern implementations (OCP, LSP)
│   │       ├── repository/  # Data access layer (ISP, DIP)
│   │       ├── entity/      # JPA entities (SRP)
│   │       ├── dto/         # Data transfer objects (SRP)
│   │       └── config/      # Configuration classes (SRP)
│   └── src/main/resources/
│       └── application.properties
├── frontend/                # React application
│   ├── src/
│   │   ├── components/      # React components (SRP)
│   │   ├── context/         # State management (SRP)
│   │   ├── services/        # API services (SRP)
│   │   └── models/          # Business logic (SRP, OCP)
│   └── public/
└── README.md
```

### 🎯 **SOLID Implementation Highlights**

- **Strategy Factory**: `service/strategy/TodoStrategyFactory.java`
- **Strategy Interface**: `service/strategy/TodoCompletionStrategy.java`  
- **Concrete Strategies**: `StandardTodoStrategy.java`, `DeadlineTodoStrategy.java`, `SavingTodoStrategy.java`
- **Calculation Service**: `service/TodoCalculationService.java`
- **Dependency Injection**: Throughout all service and controller classes

### 🧪 Testing the Application

1. **Register a new user**
2. **Create collections** for different projects
3. **Add various task types** to test functionality
4. **Test deadline tasks** with past due dates
5. **Test saving goals** by adding money
6. **Try editing and deleting** tasks and collections

## 🏗️ SOLID Architecture Principles

This application implements all five SOLID principles to ensure maintainable, extensible, and testable code:

### 🎯 **Single Responsibility Principle (SRP)**

**Applied to:**
- **`Todo` Entity**: Removed business logic methods like `getProgress()` to focus solely on data representation
- **`TodoCalculationService`**: Extracted calculation logic for progress, goal completion, and deadline checks
- **Controller Classes**: Each controller handles only one domain (Auth, Todo, Collection)
- **Service Classes**: Focused responsibilities (Auth, JWT, Todo operations)

**Example:**
```java
// Before SRP: Entity had business logic
public BigDecimal getProgress() {
    if (type == TodoType.SAVING && targetAmount != null) {
        return currentAmount.divide(targetAmount, 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }
    return BigDecimal.ZERO;
}

// After SRP: Extracted to dedicated service
@Service
public class TodoCalculationService {
    public BigDecimal calculateProgress(Todo todo) { /* logic */ }
    public boolean isGoalReached(Todo todo) { /* logic */ }
}
```

### 🔓 **Open-Closed Principle (OCP)**

**Applied to:**
- **Strategy Pattern for Todo Completion**: New todo types can be added without modifying existing completion logic
- **`TodoCompletionStrategy` Interface**: Extensible completion behaviors
- **Concrete Strategies**: `StandardTodoStrategy`, `DeadlineTodoStrategy`, `SavingTodoStrategy`

**Example:**
```java
// Interface allows extension without modification
public interface TodoCompletionStrategy {
    void complete(Todo todo);
    boolean canComplete(Todo todo);
}

// Easy to add new types without changing existing code
public class PriorityTodoStrategy implements TodoCompletionStrategy {
    @Override
    public void complete(Todo todo) {
        // New completion logic for priority tasks
    }
}
```

### 🔄 **Liskov Substitution Principle (LSP)**

**Applied to:**
- **Strategy Implementations**: All concrete strategies can be substituted for the interface without breaking functionality
- **Repository Interfaces**: JPA repository implementations are interchangeable
- **Service Layer**: Interface-based design allows for implementation substitution

**Example:**
```java
// Any strategy can be substituted without breaking the service
public void completeTodo(Todo todo) {
    TodoCompletionStrategy strategy = strategyFactory.getStrategy(todo);
    strategy.complete(todo); // Works with any implementation
}
```

### 🔀 **Interface Segregation Principle (ISP)**

**Applied to:**
- **Focused Interfaces**: Small, specific interfaces rather than large ones
- **`TodoCompletionStrategy`**: Contains only completion-related methods
- **Repository Interfaces**: Specific to each entity without unnecessary methods
- **Service Interfaces**: Focused on specific business capabilities

**Example:**
```java
// Specific interface for completion logic
public interface TodoCompletionStrategy {
    void complete(Todo todo);
    boolean canComplete(Todo todo);
}

// Separate interface for calculations
public interface TodoCalculationService {
    BigDecimal calculateProgress(Todo todo);
    boolean isGoalReached(Todo todo);
    boolean isOverdue(Todo todo);
}
```

### ⬇️ **Dependency Inversion Principle (DIP)**

**Applied to:**
- **Constructor Injection**: High-level modules depend on abstractions, not concretions
- **Strategy Factory**: Service depends on factory interface, not concrete implementations
- **Service Layer**: Controllers depend on service interfaces
- **Repository Layer**: Services depend on repository interfaces, not implementations

**Example:**
```java
// High-level TodoService depends on abstractions
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoCalculationService calculationService;
    private final TodoStrategyFactory strategyFactory;
    
    // Constructor injection ensures DIP compliance
    public TodoService(TodoRepository todoRepository,
                      TodoCalculationService calculationService,
                      TodoStrategyFactory strategyFactory) {
        this.todoRepository = todoRepository;
        this.calculationService = calculationService;
        this.strategyFactory = strategyFactory;
    }
}
```

### 🎭 **Design Patterns Used**

1. **Strategy Pattern**: 
   - **Purpose**: Encapsulate completion algorithms for different todo types
   - **Implementation**: `TodoCompletionStrategy` with concrete strategies
   - **Benefit**: Easy to add new todo types without modifying existing code

2. **Factory Pattern**:
   - **Purpose**: Create appropriate strategy instances based on todo type
   - **Implementation**: `TodoStrategyFactory`
   - **Benefit**: Centralized strategy creation and type mapping

3. **Dependency Injection**:
   - **Purpose**: Loose coupling between components
   - **Implementation**: Constructor injection throughout the application
   - **Benefit**: Testability and flexibility

### 📊 **Architecture Benefits**

- **✅ Maintainability**: Changes to one component don't affect others
- **✅ Testability**: Easy to mock dependencies and test in isolation
- **✅ Extensibility**: New features can be added without modifying existing code
- **✅ Readability**: Clear separation of concerns and focused responsibilities
- **✅ Scalability**: Modular design supports team development and growth

## 🛡️ Security Features

- **JWT Authentication**: Secure token-based authentication
- **Password Encryption**: BCrypt password hashing
- **CORS Configuration**: Controlled cross-origin requests
- **User Isolation**: Data separation between users
- **Input Validation**: Server-side validation for all inputs

## 🎨 UI Features

- **Responsive Design**: Mobile and desktop optimized
- **Modal Dialogs**: Clean forms and confirmations
- **Visual Feedback**: Progress bars and status indicators
- **Color Coding**: Task states (failed=red, complete=blue, in-progress=default)
- **Interactive Elements**: Hover effects and smooth transitions

## 🤝 Contributing

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🙋‍♂️ Support

If you have any questions or need help with deployment, please open an issue or contact the development team.

---

**Built with ❤️ using Spring Boot & React**