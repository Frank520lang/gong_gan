import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },
  { path: '/register', component: () => import('@/views/register/index'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '主页',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },
  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/teacher',
    name: 'teacher',
    meta: { title: '果农管理模块', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'list',
        component: () => import('@/views/teacher/list'),
        meta: { title: '果农信息列表', icon: 'table' }
      },
      {
        path: 'add',
        name: 'add',
        component: () => import('@/views/teacher/add'),
        meta: { title: '添加果农信息', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'edit',
        component: () => import('@/views/teacher/add'),
        meta: { title: '编辑果农信息', icon: 'tree' },
        hidden: true
      }
    ]
  },
  {
    path: '/fruit',
    component: Layout,
    redirect: '/fruit/fruit',
    name: 'fruit',
    meta: { title: '果实管理模块', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'list',
        component: () => import('@/views/fruit/list'),
        meta: { title: '果实列表', icon: 'table' }
      },
      {
        path: 'add',
        name: 'add',
        component: () => import('@/views/fruit/add'),
        meta: { title: '添加果实信息', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'edit',
        component: () => import('@/views/fruit/add'),
        meta: { title: '编辑果实信息', icon: 'tree' },
        hidden: true
      },
      {
        path: 'upload',
        name: 'upload',
        component: () => import('@/views/fruit/upload'),
        meta: { title: '批量添加', icon: 'tree' }
      },
      {
        path: 'download',
        name: 'download',
        component: () => import('@/views/fruit/download'),
        meta: { title: '下载表格', icon: 'tree' }
      }
    ]
  },
  {
    path: '/fruittree',
    component: Layout,
    redirect: '/fruittree/fruittree',
    name: 'fruit',
    meta: { title: '果树管理模块', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'list',
        component: () => import('@/views/fruittree/list'),
        meta: { title: '果树列表', icon: 'table' }
      },
      {
        path: 'add',
        name: 'add',
        component: () => import('@/views/fruittree/add'),
        meta: { title: '添加果树信息', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'edit',
        component: () => import('@/views/fruittree/add'),
        meta: { title: '编辑果树信息', icon: 'tree' },
        hidden: true
      },
    ]
  },
  {
    path: '/fruitissue',
    component: Layout,
    redirect: '/fruitissue/fruitissue',
    name: 'fruit',
    meta: { title: '事务管理模块', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'list',
        component: () => import('@/views/fruitissue/list'),
        meta: { title: '事务列表', icon: 'table' }
      },
      {
        path: 'add',
        name: 'add',
        component: () => import('@/views/fruitissue/add'),
        meta: { title: '添加事务信息', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'edit',
        component: () => import('@/views/fruitissue/add'),
        meta: { title: '编辑事务信息', icon: 'tree' },
        hidden: true
      },
    ]
  },
  {
    path: '/fruitnotice',
    component: Layout,
    redirect: '/fruitnotice/fruitnotice',
    name: 'fruit',
    meta: { title: '公告管理模块', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'list',
        component: () => import('@/views/fruitnotice/list'),
        meta: { title: '公告详情', icon: 'table' }
      },
      {
        path: 'add',
        name: 'add',
        component: () => import('@/views/fruitnotice/add'),
        meta: { title: '添加新公告', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'edit',
        component: () => import('@/views/fruitnotice/add'),
        meta: { title: '编辑公告信息', icon: 'tree' },
        hidden: true
      },
    ]
  },
  {
    path: '/count',
    component: Layout,
    redirect: '/count/count',
    name: 'count',
    meta: { title: '统计预测模块', icon: 'example' },
    children: [
      {
        path: 'price',
        name: 'price',
        component: () => import('@/views/count/price'),
        meta: { title: '市场价格', icon: 'tree' }
      },
      {
        path: 'pay',
        name: 'pay',
        component: () => import('@/views/count/pay'),
        meta: { title: '支出统计', icon: 'tree' }
      },
      {
        path: 'other',
        name: 'other',
        component: () => import('@/views/count/other'),
        meta: { title: '环境因素', icon: 'tree' },
      },
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
