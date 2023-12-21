<template>
  <div>
    <el-container>
<!--      侧边栏-->
<!--      //100vh,浏览器窗口高度-->
      <el-aside :width="asideWidth" style="min-height: 100vh; background-color: #001529">
        <div style="height: 60px;color: white;display: flex;align-items: center;justify-content: center">
          <img src="@/assets/logo2.png" alt="" style="width:40px;height:40px">
          <span class="logo-title" v-show="!isCollapse">ABMS</span>
        </div>

<!--$route.path 是当前浏览器正在访问的路由;router属性可以使点击菜单栏切换页面-->
        <el-menu :collapse="isCollapse" :collapse-transition="false" router background-color="#001529" text-color="rgba(255,255,255,0.65)" active-text-color="#fff" style="border:none" :default-active="$route.path">

          <el-menu-item index="/">
            <i class="el-icon-house"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>

<!--这里的index需要和index.js中页面的path一致-->


          <el-submenu>
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>信息管理</span>
            </template>
            <el-menu-item>旅客信息</el-menu-item>
            <el-menu-item>旅行社信息</el-menu-item>
            <el-menu-item>管理员信息</el-menu-item>
            <el-menu-item>订单信息</el-menu-item>
            <el-menu-item>航班信息</el-menu-item>
          </el-submenu>


        </el-menu>
      </el-aside>

      <el-container>
<!--        头部区域-->
        <el-header>

<!--          菜单伸缩按钮；面包屑-->
          <i :class="collapseIcon" style="font-size: 26px"  @click="handleCollapse"></i>
          <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-left: 20px">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/user' }">信息管理</el-breadcrumb-item>
          </el-breadcrumb>

<!--          头像部分：因其本身所在的el-header是flex布局所以自适应一下；也把这个div设为flex布局,justify-content:div的内容靠右排列-->
          <div style="flex:1;width:0;display: flex;align-items: center; justify-content: flex-end">
            <i class="el-icon-zoom_out_map-o" style="font-size: 26px" @click="handleFulls"></i>
<!--            placement="bottom"使得下拉菜单和头像对齐-->
            <el-dropdown placement="bottom">
<!--             cursor: default 使得鼠标放管理员上是箭头而不是I -->
              <div style="display: flex;align-items: center;cursor: default">
                <img src="@/assets/logo2.png" alt="" style="width:40px;height:40px;margin: 0 5px">
                <span>管理员</span>
              </div>

              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item >个人信息</el-dropdown-item>
                <el-dropdown-item @click="">修改密码</el-dropdown-item>
                <el-dropdown-item >退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>

        </el-header>

<!--        主体区域-->
        <el-main>

          <div style="box-shadow: 0 0 10px rgba(0,0,0,.1); padding: 10px 20px; border-radius: 5px; margin-bottom: 10px">
            欢迎来到机票预订后台管理系统！！！
          </div>

          <div style="display: flex">
            <el-card style="width: 50%;margin-right: 10px">
              <div slot="header" class="clearfix">
                <span>HELLO！！！！！！！！！！</span>
              </div>

              <div>
                WELCOME！！！！！！！！！！！
                <div style="margin-top: 20px" >
                  <div style="margin: 10px 0"><strong>主题色</strong></div>
                  <el-button type="primary">按钮</el-button>
                  <el-button type="success">按钮</el-button>
                  <el-button type="warning">按钮</el-button>
                  <el-button type="danger">按钮</el-button>
                  <el-button type="info">按钮</el-button>
                </div>
              </div>
            </el-card>

            <el-card style="width: 50%">
              <div slot="header" class="clearfix">
                <span>渲染数据</span>
              </div>

              <div>
                <el-table :data="passengers">
<!--                  prop:属性名-->
                  <el-table-column label="ID" prop="id"></el-table-column>
                  <el-table-column label="姓名" prop="name"></el-table-column>
                  <el-table-column label="性别" prop="sex"></el-table-column>
                  <el-table-column label="电话" prop="phone"></el-table-column>
                </el-table>
              </div>
            </el-card>
          </div>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

import request from "@/utils/request";
export default {
  name: 'HomeView',
  data(){
    return {
      isCollapse:false ,// 默认不收缩
      asideWidth:'200px',
      collapseIcon:'el-icon-s-fold',
      passengers:[]
    }
  },
  mounted() {//mounted含义是：页面加载完后触发，请求数据
    // axios.get("https://localhost:9090/user/selectALL").then(res =>{
    //   console.log(res.data)//后台返回的数据
    //   this.users = res.data.data
    // })
    request.get('/admin/selectAllPassenger').then(res =>{
      this.passengers = res.data
    })
  },
  methods:{
    handleFulls(){//点击全屏按钮响应函数
      document.documentElement.requestFullscreen()
    },
    handleCollapse(){//点击收缩按钮响应函数
      this.isCollapse = !this.isCollapse
      this.asideWidth = this.isCollapse ? '64px' : '200px'
      this.collapseIcon = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'
    }
  }
}
</script>


<style>
.el-menu--inline{
  background-color: #000c17 !important;
}
.el-menu .el-menu--inline .el-menu-item {
  background-color: #000c17 !important;
  padding-left: 49px !important;
}
/*鼠标悬停，菜单和菜单选项变亮*/
.el-menu-item:hover, .el-submenu__title:hover{
  color: #fff !important;
}
/*鼠标悬停,i随一级菜单变亮*/
.el-submenu__title:hover i{
  color: #fff !important;
}

/*涉及收缩菜单图标对齐问题看1:24:51*/
.el-menu-item.is-active{
  background-color: #1677ff !important;
  border-radius: 5px !important;
  width: calc(100% - 8px);
  margin-left: 4px;
}

/*收缩前后，i在不同的类下*/
.el-menu-item.is-active i, .el-menu-item.is-active .el-tooltip{
  margin-left: -4px;
}

.el-menu-item{
  height: 40px !important;
  line-height: 40px !important;
}
.el-submenu__title{
  height: 40px !important;
  line-height: 40px !important;
}

/*这是考虑高亮系统页面在二级菜单的情况，进行修正*/
.el-submenu .el-menu-item{
  min-width: 0 !important;
}
.el-menu--inline .el-menu-item.is-active{
  padding-left: 45px !important;
}

/*收缩动画*/
.el-aside{
  transition: width .3s;
  box-shadow: 2px 0 6px rgba(0,21,41,.35);/*阴影*/
}

.logo-title{
  margin-left: 5px;
  font-size: 20px;
  transition: all .10s; /*0.3s*/
}

.el-header{
  box-shadow: 2px 0 6px rgba(0,21,41,.35);/*阴影*/
  display: flex;
  align-items: center;
}
</style>