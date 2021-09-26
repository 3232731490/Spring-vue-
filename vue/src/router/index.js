import { createRouter, createWebHistory } from 'vue-router'
import Layout from "../layout/Layout";
import Book from "../views/Book";
const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/user",
    children:[
      {
        path:'user',
        name:'user',
        component:() => import("@/views/User")
      },
      {
        path: 'book',
        name: 'book',
        component: Book,
      },
    ]
  },

  {
    path: '/login',
    name: 'Loginin',
    component: ()=>import("@/views/Login"),
  },
  {
    path: '/register',
    name: 'Register',
    component: ()=>import("@/views/Register"),
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
