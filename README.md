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
│   │       ├── controller/  # REST controllers
│   │       ├── service/     # Business logic
│   │       ├── repository/  # Data access
│   │       ├── entity/      # JPA entities
│   │       ├── dto/         # Data transfer objects
│   │       └── config/      # Configuration classes
│   └── src/main/resources/
│       └── application.properties
├── frontend/                # React application
│   ├── src/
│   │   ├── components/      # React components
│   │   ├── context/         # State management
│   │   ├── services/        # API services
│   │   └── models/          # Business logic
│   └── public/
└── README.md
```

### 🧪 Testing the Application

1. **Register a new user**
2. **Create collections** for different projects
3. **Add various task types** to test functionality
4. **Test deadline tasks** with past due dates
5. **Test saving goals** by adding money
6. **Try editing and deleting** tasks and collections

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