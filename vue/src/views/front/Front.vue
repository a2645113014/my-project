<template>
  <div>
    <div class="header">
      <div class="front-header">
        <a href="/front/home">
          <div class="front-header-left">
            <img src="@/assets/logo.svg" alt="">
            <div class="title">精致选品平台</div>
          </div>
        </a>

        <div class="front-header-center">
          <div @click="goPage(item.path)" class="menu-item" v-for="item in menuList" :key="item.text" :class="{'menu-item-active' : item.path === $route.path }">{{ item.text }}</div>
        </div>

        <div class="front-header-right">
          <div v-if="!user.username" class="front-header-right-button">
            <el-button type="primary" plain @click="$router.push('/login')">登录</el-button>
            <el-button type="success" plain @click="$router.push('/register')">注册</el-button>
          </div>
          <!-- 登录展示 -->
          <div v-else>
            <el-dropdown>
              <div class="front-header-dropdown">
                <img :src="user.avatar" alt="">
              </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <div style="color: #333">{{user.name}}</div>
                </el-dropdown-item>
                <el-dropdown-item>
                  <a style="color: #333" href="/front/person"><div>个人信息</div></a>
                </el-dropdown-item>
                <el-dropdown-item>
                  <a style="color: #333" href="/front/collect"><div>我的收藏</div></a>
                </el-dropdown-item>
                <el-dropdown-item>
                  <a style="color: #333" href="/front/orders"><div>我的订单</div></a>
                </el-dropdown-item>
                <el-dropdown-item>
                  <div @click="logout">退出</div>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>
    </div>

    <div class="main-body">
      <router-view ref="child" @update:user="updateUser" />
    </div>

    <Footer />
  </div>
</template>

<script>

import Footer from "@/conponents/Footer.vue";

export default {
  name: "FrontLayout",
  components: {Footer},
  data () {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      menuList: [
        {text: "首页", path: '/front/home'},
        {text: "个人中心", path: '/front/person'},
        {text: "全部商品", path: '/front/goods'},
      ],
    }
  },
  created() {

  },
  methods: {
    goPage(path) {
      location.href = path;
    },
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('user') || '{}')   // 重新获取下用户的最新信息
    },
    logout() {
      localStorage.removeItem("user");
      location.href = '/front/home'
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/front.css";

</style>
