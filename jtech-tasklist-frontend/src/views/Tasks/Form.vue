<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

enum TaskStatus {
  PENDING = 'PENDING',
  IN_PROGRESS = 'IN_PROGRESS',
  COMPLETED = 'COMPLETED'
}

interface Task {
  id?: string
  title: string
  description: string
  status: TaskStatus | ''
}

const route = useRoute()
const router = useRouter()
const taskId = route.params.id as string | undefined

const task = ref<Task>({
  title: '',
  description: '',
  status: ''
})

const titleError = ref<string | null>(null)
const statusError = ref<string | null>(null)
const saveError = ref<string | null>(null)

onMounted(async () => {
  if (taskId) {
    try {
      const response = await axios.get(`http://localhost:8080/api/v1/tasklists/${taskId}`)
      task.value = response.data
    } catch (error: any) {
      console.error('Error fetching task:', error)
      saveError.value = error.response?.data?.message || 'Failed to load task.'
    }
  }
})

const validateTask = (): boolean => {
  titleError.value = null
  statusError.value = null

  let isValid = true

  if (!task.value.title || task.value.title.trim() === '') {
    titleError.value = 'Title is required.'
    isValid = false
  }

  if (!task.value.status) {
    statusError.value = 'Status is required.'
    isValid = false
  }

  return isValid
}

const saveTask = async () => {
  if (!validateTask()) return

  saveError.value = null

  try {
    if (taskId) {
      await axios.put(`http://localhost:8080/api/v1/tasklists/${taskId}`, task.value)
      router.push({ path: '/', query: { success: 'updated' } })
    } else {
      await axios.post('http://localhost:8080/api/v1/tasklists', task.value)
      router.push({ path: '/', query: { success: 'created' } })
    }
  } catch (error: any) {
    console.error('Error saving task:', error)
    saveError.value = error.response?.data?.message || 'Failed to save task.'
  }
}

function formatStatus(status: string): string {
  return status
    .toLowerCase()
    .split('_')
    .map(word => word.charAt(0).toUpperCase() + word.slice(1))
    .join(' ')
}
</script>

<template>
  <main class="container">
    <h2 class="mb-4 text-center">{{ taskId ? 'Edit Task' : 'New Task' }}</h2>

    <div v-if="saveError" class="alert alert-danger alert-dismissible fade show" role="alert">
      {{ saveError }}
      <button type="button" class="btn-close" @click="saveError = null"></button>
    </div>

    <form @submit.prevent="saveTask">
      <div v-if="taskId" class="mb-3">
        <label for="id" class="form-label">ID</label>
        <input type="text" id="id" class="form-control" v-model="task.id" disabled />
      </div>

      <div class="mb-3">
        <label for="title" class="form-label">Title</label>
        <input
          type="text"
          id="title"
          class="form-control"
          v-model="task.title"
        />
        <div v-if="titleError" class="invalid-feedback">{{ titleError }}</div>
      </div>

      <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <textarea
          id="description"
          class="form-control"
          v-model="task.description"
          rows="3"
        ></textarea>
      </div>

      <div class="mb-3">
        <label for="status" class="form-label">Status</label>
        <select
          id="status"
          class="form-select"
          v-model="task.status"
          :class="{ 'is-invalid': statusError }"
        >
          <option value="" disabled>Select</option>
          <option v-for="status in Object.values(TaskStatus)" :key="status" :value="status">
            {{ formatStatus(status) }}
          </option>
        </select>
        <div v-if="statusError" class="invalid-feedback">{{ statusError }}</div>
      </div>

      <div class="d-flex justify-content-between gap-2 mt-4">
        <button type="submit" class="btn btn-primary">
          {{ taskId ? 'Update' : 'Save' }}
        </button>
        <button type="button" class="btn btn-secondary" @click="router.push('/')">
          Cancel
        </button>
      </div>
    </form>
  </main>
</template>

<style scoped>
main {
  max-width: 700px;
}

.container {
  max-width: 700px;
  margin: 0 auto;
}
</style>
