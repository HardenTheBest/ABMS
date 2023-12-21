<template>
  <div>
<!--    <el-row :gutter="20">-->
<!--      <el-col :span="1">-->
<!--        <div style="width: 100% ;height: 300px;background-color: dodgerblue"></div>-->
<!--      </el-col>-->
<!--      <el-col :span="23">-->
<!--        <div style="width: 100% ;height: 300px;background-color:red"></div>-->
<!--      </el-col>-->
<!--    </el-row>-->
    <el-row :gutter="20">
      <el-col :span="6">
        <div style="padding: 10px; border: 1px solid #ccc;text-align: center">
          <img style="width: 100%" src="@/assets/logo.png" alt="">
          <div style="text-align: center">这是一段描述</div>
          <div style="color: red">价格 $99.00</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div style="padding: 10px; border: 1px solid #ccc;text-align: center">
          <img style="width: 100%" src="@/assets/logo.png" alt="">
          <div style="text-align: center">这是一段描述</div>
          <div style="color: red">价格 $99.00</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div style="padding: 10px; border: 1px solid #ccc;text-align: center">
          <img style="width: 100%" src="@/assets/logo.png" alt="">
          <div style="text-align: center">这是一段描述</div>
          <div style="color: red">价格 $99.00</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div style="padding: 10px; border: 1px solid #ccc;text-align: center">
          <img style="width: 100%" src="@/assets/logo.png" alt="">
          <div style="text-align: center">这是一段描述</div>
          <div style="color: red">价格 $99.00</div>
        </div>
      </el-col>

    </el-row>

    <el-row style="margin-top: 20px">
      <el-col :span="24">
        <el-button>默认按钮</el-button>
        <el-button type="primary">主要按钮</el-button>
        <el-button type="success">成功按钮</el-button>
        <el-button type="info">信息按钮</el-button>
        <el-button type="warning">警告按钮</el-button>
        <el-button type="danger">危险按钮</el-button>

        <el-button type="primary" plain>主要按钮</el-button>
        <el-button type="success" plain>成功按钮</el-button>
        <el-button type="info" plain>信息按钮</el-button>
        <el-button type="warning" plain>警告按钮</el-button>
        <el-button type="danger" plain>危险按钮</el-button>

        <el-button type="primary" plain round>主要的圆角按钮</el-button>
        <el-button circle icon="el-icon-edit" type="primary"></el-button>
        <el-button circle icon="el-icon-delete" type="danger"></el-button>

      </el-col>
    </el-row>

    <el-row style="margin-top: 20px">
      <el-col>
<!--        输入框强制必须有v-model-->
        <el-input style="width: 200px" v-model="value" placeholder="请输入内容"></el-input>
        <el-input type="textarea" style="width: 200px" v-model="value1"></el-input>
        <el-input show-password style="width: 200px" v-model="password" placeholder="请输入密码"></el-input>

        <el-input style="width: 200px" v-model="value2" placeholder="请输入内容" prefix-icon="el-icon-search"></el-input>
        <el-input clearable style="width: 200px" v-model="value2" placeholder="请输入内容" suffix-icon="el-icon-user"></el-input>
      </el-col>
    </el-row>

    <el-row style="margin-top: 20px">
      <el-select style="margin-top: 20px" v-model="select" @change="changeSelect">
        <el-option value="banana"></el-option>
        <el-option value="apple"></el-option>
        <el-option value="orange"></el-option>
      </el-select>
    </el-row>

    <el-row style="margin-top: 20px">
      <!--      multiple:多选-->
      <el-select v-model="select1" @change="changeSelect" multiple>
        <el-option v-for="item in fruits" :key="item.id" :value="item.name"></el-option>
      </el-select>
    </el-row>

    <el-row style="margin-top: 20px">

      <el-select v-model="select2" @change="changeSelectUser" filterable>
<!--        key:身份令牌，唯一的令牌;label:用户看到的下拉菜单选项;value:点击某个label后对应的值给v-model绑定的值-->
        <el-option v-for="item in users" :key="item.card" :label="item.label" :value="item.card"></el-option>
      </el-select>
    </el-row>

    <el-row>
      <el-radio-group v-model="radio" @change="selectRadio">
        <el-radio label="男"></el-radio>
        <el-radio label="女"></el-radio>
      </el-radio-group>

      <el-checkbox-group v-model="checkList" @change="selectCheckBox">
        <el-checkbox label="复选框 A"></el-checkbox>
        <el-checkbox label="复选框 B"></el-checkbox>
      </el-checkbox-group>

      <el-date-picker v-model="date" type="date" placeholder="选择日期" value-format="yyyy-MM-dd" @change="changeDate"></el-date-picker>
      <el-date-picker v-model="datetime" type="datetime" placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss" @change="changeDateTime"></el-date-picker>

      <el-date-picker v-model="daterange" type="daterange" value-format="yyyy-MM-dd" start-placeholder="开始日期" end-placeholder="结束日期" @change="changeDateRange"></el-date-picker>
    </el-row>

    <el-row style="margin-top: 20px">
      <el-table :data="tableData" border :header-cell-style="{background:'aliceblue',fontSize:'16px'}">
        <el-table-column label="序号" prop="id" align="center"></el-table-column>
        <el-table-column label="名称" prop="name" align="center"></el-table-column>
        <el-table-column label="年龄" prop="age" align="center"></el-table-column>
        <el-table-column label="地址" prop="address" align="center"></el-table-column>
        <el-table-column label="操作">
          <template v-slot="scope">
            <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
export default {
  name:'Element',
  data(){
    return{//把当前对象的内容return给vue的实例
      tableData:[
        {name:'张志喜',address:'安徽省合肥市',id:1,age:'30'},
      ],
      value:'',
      value1:'',
      value2:'',
      password:'',
      select:'',
      select1:'',
      select2:'',
      radio:'',
      checkList:[],
      date:'',
      datetime:'',
      daterange:'',
      fruits:[
        {name:'a',id:1},
        {name:'b',id:2},
        {name:'o',id:3},
        {name:'brother',id:4}
      ],
      users:[
        {label:'喜哥哥',card:'12345678910'},
        {label:'喜哥哥小弟',card:'123456789'},
        {label:'喜哥哥大哥哥',card:'12345678956'}
      ]
    }
  },
  methods:{
    edit(row){
      // alert(row.name)
      // this.$message.success(row.name)
      // this.$message.warning(row.name)
      // this.$notify.success(row.name)

      this.$confirm('这是个什么玩意','提示',{
        type:'warning'
      }).then(res => {
        this.$message.success("我点击了确认")
      }).catch(() => {
        this.$message.warning("我点击了取消")
      })
    },

    changeSelect() {
      console.log(this.select)
      console.log(this.select1)
    },
    changeSelectUser(){
      console.log(this.select2)
    },
    selectRadio(){
      alert(this.radio)
    },
    selectCheckBox(){
      console.log(this.checkList)
    },
    changeDate(){
      console.log(this.date)
    },
    changeDateTime(){
      console.log(this.datetime)
    },
    changeDateRange(){
      console.log(this.daterange)
    }
  }
}
</script>