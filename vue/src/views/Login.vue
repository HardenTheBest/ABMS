<template>
  <div style="height: 100vh;display: flex;align-items: center;justify-content: center;background-color: #c8c3b8">
    <div style="display: flex;background-color: white ;width:50%;border-radius: 5px;overflow: hidden">
      <div style="flex: 1">
        <img src="@/assets/logo6.png" alt="" style="width: 100%;">
      </div>
      <div style="flex: 1;display: flex;align-items: center;justify-content: center;">
<!--        ref,可以通过其赋值的名字调表单-->
        <el-form :model="form" style="width:80%" :rules="rules" ref="formRef">
          <div style="font-size: 20px;font-weight: bold;text-align: center;margin-bottom: 20px">欢迎登录机票预订系统</div>

          <el-form-item prop="username">
            <el-input size="medium" prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input size="medium" prefix-icon="el-icon-lock" show-password placeholder="请输入密码" v-model="form.password"></el-input>
          </el-form-item>

          <el-form-item prop="type">
            <el-select v-model="form.type" placeholder="请选择角色" style="width: 100%">
              <el-option label="管理员" value="admin"></el-option>
              <el-option label="旅行社" value="agency"></el-option>
              <el-option label="旅客" value="passenger"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="width: 100%" @click="login">登 录</el-button>
          </el-form-item>

          <div style="display: flex">
            <div style="flex: 1">还没有账号？请 <span style="color: rgb(64 158 255);cursor: pointer" @click="$router.push('/register')">注册</span></div>
<!--            <div style="flex: 1;text-align: right"><span style="color: rgb(64 158 255);cursor: pointer">忘记密码</span></div>-->
          </div>
        </el-form>
      </div>

    </div>
  </div>
</template>


<script>
export default {
  name: "Login",
  data(){
    return{
      form: {
        username:"",
        password:"",
        type:""
      },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
      }
    }
  },
  created() {

  },
  methods:{
    // 验证
    login(){
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          //验证通过
          console.log(this.$request)
          this.$request.post('/login',this.form).then(res => {
            console.log(res)
            if(res.code === 200){
              this.$router.push('/')
              this.$message.success('登录成功')
              localStorage.setItem("abms-user", JSON.stringify(res.data))  // 存储用户数据
            } else {
              this.$message.error(res.msg?res.msg:'登录失败')
            }
          })
        }
      })
    }
  }
}
</script>


<style scoped>

</style>