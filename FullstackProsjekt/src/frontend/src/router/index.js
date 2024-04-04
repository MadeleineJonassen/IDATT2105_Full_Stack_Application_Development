import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EditQuizView from "@/views/EditQuizView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: () => import('../views/DashboardView.vue')
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/feedback',
      name: 'feedback',
      component: () => import('../views/FeedbackView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
    path: '/signup',
    name: 'signup',
    component: () => import('../views/SignupView.vue')
    },
    {
      path: '/createQuiz',
      name: 'create Quiz',
      component: () => import('../views/EditQuizView.vue')
    },
    {
      path: '/overviewQuiz',
      name: 'overview Quiz',
      component: () => import('../views/OverviewQuizView.vue')
    },
    {
      path: '/play-quiz/:quizId',
      name: 'playQuiz',
      component: () => import('../views/PlayQuizView.vue'),
      params: true
    },
    {
      path: '/edit-quiz/:quizId',
      name: 'editQuiz',
      component: EditQuizView,
      params: true
    },
  ]
})

export default router
