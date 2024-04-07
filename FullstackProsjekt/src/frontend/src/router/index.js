import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EditQuizView from "@/components/shared/create-quiz/EditQuizView.vue";
import PlayQuizView from "@/views/PlayQuizView.vue";
import CreateQuizView from "@/components/shared/create-quiz/CreateQuizView.vue";

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
      component: CreateQuizView
    },
    {
      path: '/overviewQuiz',
      name: 'overview Quiz',
      component: () => import('../views/OverviewQuizView.vue')
    },
    {
      path: '/play-quiz/:quizId',
      name: 'playQuiz',
      component: PlayQuizView,
      params: true
    },
    {
      path: '/edit-quiz/:quizId',
      name: 'editQuiz',
      component: EditQuizView,
      params: true
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/ProfileView.vue')
    }
  ]
})

export default router
