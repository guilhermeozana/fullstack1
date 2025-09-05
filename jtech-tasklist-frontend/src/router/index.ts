import { createRouter, createWebHistory } from 'vue-router'
import TaskView from '../views/Tasks/View.vue'
import TaskForm from '../views/Tasks/Form.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'list',
      component: TaskView,
    },
    {
      path: '/form/:id?',
      name: 'form',
      component: TaskForm,
    }
  ],
})

export default router
