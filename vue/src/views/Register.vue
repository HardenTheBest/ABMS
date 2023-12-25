<template>
  <div style="height: 100vh;display: flex;align-items: center;justify-content: center;background-color: #669fef">
    <div style="display: flex;background-color: white ;width:50%;border-radius: 5px;overflow: hidden">
      <div style="flex: 1">
        <img src="@/assets/logo7.png" alt="" style="width: 100%;">
      </div>
      <div style="flex: 1;display: flex;align-items: center;justify-content: center;">
        <!--        ref,可以通过其赋值的名字调表单-->
        <el-form :model="form" style="width:80%" :rules="rules" ref="formRef">
          <div style="font-size: 20px;font-weight: bold;text-align: center;margin-bottom: 20px">欢迎注册</div>

          <el-form-item prop="username">
            <el-input size="medium" prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input size="medium" prefix-icon="el-icon-lock" show-password placeholder="请输入密码" v-model="form.password"></el-input>
          </el-form-item>

          <el-form-item prop="confirmPass">
            <el-input size="medium" prefix-icon="el-icon-lock" show-password placeholder="请确认密码" v-model="form.confirmPass"></el-input>
          </el-form-item>

          <el-form-item prop="type">
            <el-select v-model="form.type" placeholder="请选择角色" style="width: 100%">
              <el-option label="旅行社" value="agency"></el-option>
              <el-option label="旅客" value="passenger"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button style="color:white;background-color:#6e77f2;width: 100%" @click="register">注 册</el-button>
          </el-form-item>

          <div style="display: flex">
            <div style="flex: 1">已有账号？请 <span style="color: #6e77f2;cursor: pointer" @click="$router.push('/login')">登录</span></div>
          </div>
        </el-form>
      </div>

    </div>
  </div>
</template>


<script>
export default {
  name: "Register",
  data(){

    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('请确认密码'))
      } else if (confirmPass !== this.form.password) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    }
    return{
      form:{ },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirmPass: [
          { validator: validatePassword, trigger: 'blur' }
        ],
      }
    }
  },
  created() {

  },
  methods:{
    // 验证
    register(){
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          //验证通过
          this.$request.post('/register',this.form).then(res => {
            console.log(res)
            if(res.code === 200){
              this.$router.push('/login') //跳转登录界面
              this.$message.success('注册成功')
            } else {
              this.$message.error(res.msg?res.msg:'注册失败')
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