<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import axios from 'axios'
import type { Task } from '@/types/Task'

const tasks = ref<Task[]>([])
const router = useRouter()
const route = useRoute()

const deleteSuccess = ref<string | null>(null)
const deleteError = ref<string | null>(null)
const showModal = ref(false)
const taskToDelete = ref<Task | null>(null)

const successMessage = computed(() => {
  if (route.query.success === 'created') return 'Task created successfully.'
  if (route.query.success === 'updated') return 'Task updated successfully.'
  return null
})

onMounted(async () => {
  await fetchTasks()
})

async function fetchTasks() {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/tasklists')
    tasks.value = response.data
  } catch (error) {
    console.error('Error fetching tasks:', error)
  }
}

function confirmDelete(task: Task) {
  taskToDelete.value = task
  showModal.value = true
}

async function deleteTaskConfirmed() {
  if (!taskToDelete.value?.id) return

  deleteSuccess.value = null
  deleteError.value = null
  showModal.value = false

  try {
    await axios.delete(`http://localhost:8080/api/v1/tasklists/${taskToDelete.value.id}`)
    tasks.value = tasks.value.filter(task => task.id !== taskToDelete.value?.id)
    deleteSuccess.value = 'Task deleted successfully.'
  } catch (error: any) {
    console.error('Error deleting task:', error)
    deleteError.value = error.response?.data?.message || 'Failed to delete task.'
  }
}

function formatStatus(status: string): string {
  if (!status) return ''
  return status
    .toLowerCase()
    .split('_')
    .map(word => word.charAt(0).toUpperCase() + word.slice(1))
    .join(' ')
}
</script>

<template>
  <main class="container">
    <h2 class="my-4 text-center">Task List</h2>

    <!-- Success Alert from query -->
    <div v-if="successMessage" class="alert alert-success alert-dismissible fade show" role="alert">
      {{ successMessage }}
      <button type="button" class="btn-close" @click="router.replace({ query: {} })"></button>
    </div>

    <!-- Success Alert from delete -->
    <div v-if="deleteSuccess" class="alert alert-success alert-dismissible fade show" role="alert">
      {{ deleteSuccess }}
      <button type="button" class="btn-close" @click="deleteSuccess = null"></button>
    </div>

    <!-- Error Alert -->
    <div v-if="deleteError" class="alert alert-danger alert-dismissible fade show" role="alert">
      {{ deleteError }}
      <button type="button" class="btn-close" @click="deleteError = null"></button>
    </div>

    <div class="table-responsive">
      <table class="table table-bordered align-middle">
        <thead class="table-light">
          <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Status</th>
            <th scope="col" class="text-center" style="width: 100px;">Actions</th>
          </tr>
        </thead>
        <tbody v-if="tasks.length > 0">
          <tr v-for="(task, index) in tasks" :key="task.id">
            <th scope="row">{{ index + 1 }}</th>
            <td>{{ task.title }}</td>
            <td>{{ task.description }}</td>
            <td>{{ formatStatus(task.status) }}</td>
            <td class="text-center align-middle" style="width: 100px;">
              <RouterLink
                :to="`/form/${task.id}`"
                class="btn btn-primary btn-sm rounded-circle me-2"
                title="Edit task"
              >
                <i class="bi bi-pencil"></i>
              </RouterLink>

              <button
                type="button"
                class="btn btn-danger btn-sm rounded-circle"
                @click="confirmDelete(task)"
                title="Delete task"
              >
                <i class="bi bi-trash"></i>
              </button>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr>
            <td colspan="5" class="text-center">No tasks found.</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="mt-4">
      <button
        type="button"
        class="btn btn-primary"
        @click="router.push('/form')"
        title="Create new task"
      >
        <i class="bi bi-plus-lg"></i>
        Add
      </button>
    </div>

    <!-- Delete Confirmation Modal -->
    <div
      class="modal fade"
      tabindex="-1"
      :class="{ show: showModal }"
      style="display: block; background-color: rgba(0, 0, 0, 0.5);"
      v-if="showModal"
    >
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Confirm Deletion</h5>
            <button type="button" class="btn-close" @click="showModal = false"></button>
          </div>
          <div class="modal-body">
            <p>Are you sure you want to delete <strong>{{ taskToDelete?.title }}</strong>?</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="showModal = false">Cancel</button>
            <button type="button" class="btn btn-danger" @click="deleteTaskConfirmed">Delete</button>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped>
.container {
  max-width: 900px;
  margin: 0 auto;
}

.table {
  margin: 20px 0 5px 0;
}


.text-nowrap {
  white-space: nowrap;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1050;
  width: 100%;
  height: 100%;
  overflow: hidden;
  outline: 0;
}
</style>
