![Jtech Logo](http://www.jtech.com.br/wp-content/uploads/2015/06/logo.png)

# jtech-tasklist (Frontend)

## What is

**jtech-tasklist Frontend** is a Vue 3 application that serves as the user interface for managing tasks. It consumes the jtech-tasklist API built in Spring Boot and provides a clean, responsive interface for creating, editing, listing, and deleting tasks.

The project uses **Composition API**, **TypeScript**, **Axios**, and **Bootstrap 5**, and follows a modular structure for scalability and maintainability.

---

## Composite by

- **Views**
  - `TaskList.vue`: Displays the list of tasks with actions to edit and delete.
  - `Form.vue`: Form for creating or editing a task.

- **Router**
  - Configured with Vue Router to handle navigation between list and form views.

- **Types**
  - `Task.ts`: Defines the task model used across components.

- **Services**
  - `axios`: Handles HTTP requests to the backend API.

- **Styles**
  - Scoped Bootstrap-based styling for layout and responsiveness.

---

## Services

- **Task API Integration**
  - `GET /api/v1/tasklists`: Fetch all tasks
  - `POST /api/v1/tasklists`: Create a new task
  - `PUT /api/v1/tasklists/{id}`: Update a task
  - `DELETE /api/v1/tasklists/{id}`: Delete a task

- **Validation**
  - Client-side validation for required fields (`title`, `status`)
  - Displays API validation errors returned from the backend

- **Feedback**
  - Success and error alerts using Bootstrap
  - Confirmation modal for deletions

---

## Helper

- **Status Formatter**
  - Converts enum values like `PENDING` to `Pending` for display

- **Modal Confirmation**
  - Custom modal for confirming task deletion

- **Tooltips**
  - Native tooltips for action buttons (edit, delete, add)

---

## How to use

### Create a task

1. Click the ➕Add button in the task list view
2. Fill in the title, description, and status
3. Click the Save button

### Edit a task

1. Click the ✏️ icon next to a task
2. Modify the fields
3. Save changes

### Delete a task

1. Click the 🗑️ icon
2. Confirm deletion in the modal

---

## Sample

Example of a task displayed in the list:

| Title            | Description                             | Status  | Actions         |
|------------------|-----------------------------------------|---------|-----------------|
| Create unit test | Write unit tests for TasklistRequest    | Pending | ✏️ 🗑️            |

---

## How to run

1. Clone the repository:

```bash
git clone https://github.com/guilhermeozana/fullstack1.git
cd jtech-tasklist-frontend
npm install
npm run dev
