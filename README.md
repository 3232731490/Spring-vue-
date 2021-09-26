# 写在前面

本笔记仅为本人学习笔记记录，笔记也是个人理解



# Ⅰ 搭建环境

## 一、Vue环境搭建

### Vue脚手架

1.  **在桌面创建根文件夹**

   `mkdir spring-boot-demp`

2.  **打开命令行窗口**

   - `vue create hello-world`

     ![image-20210925212244564](C:\Users\32327\AppData\Roaming\Typora\typora-user-images\image-20210925212244564.png)

   - 选择一种安装方式，可以手动选择一些特性，也可以使用默认的`Vue2` `Vue3`特性

     ![image-20210925212452447](C:\Users\32327\AppData\Roaming\Typora\typora-user-images\image-20210925212452447.png)

     >  自定义安装时通过空格键选择安装哪些特性

   - 选择完毕后等待脚手架下载一些包和依赖即可完成脚手架的配置

   - 启动服务

     `npm run serve`

   - 打包构建项目，当项目全部完成后，通过此命令将`vue`项目构建成浏览器可识别的`html+css+js`

     `npm run build`

3. **整理`main.js`**

   ```js
   import ElementPlus from 'element-plus'
   import 'element-plus/dist/index.css'
   import zhCn from 'element-plus/es/locale/lang/zh-cn'
   createApp(App).use(store).use(router).use(ElementPlus,{locale:zhCn,size:"small"}).mount('#app')
   ```

   > 引入Element-plus的ui库，后期直接去官网复制想要的ui样式即可
   
4. **此处整理本项目使用到的所有组件样式**

   - `el-dropdown` 下拉列表框

     ```vue
     <el-dropdown>
         <span class="el-dropdown-link">
           菜单<i class="el-icon-arrow-down el-icon--right"></i>
         </span>
         <template #dropdown>
             <el-dropdown-menu>
                 <el-dropdown-item>个人信息</el-dropdown-item>
                 <el-dropdown-item @click="exitSystem">退出系统</el-dropdown-item>
             </el-dropdown-menu>
         </template>
     </el-dropdown>
     ```

     

   - `el-menu` 导航栏

     ```html
     <el-menu
             style="width:200px;min-height:calc(100vh-50px)"
             :default-active="path"
             class="el-menu-vertical-demo"
             :default-openeds="[1]"
             router
     >
         <el-sub-menu index="1" >
             <template #title>
                 <i class="el-icon-location"></i>
                 <span>系统管理</span>
             </template>
             <el-menu-item index="/user">
                 <template #title>用户管理</template>
             </el-menu-item>
         </el-sub-menu>
         <el-menu-item index="/book" >
             <template #title>
                 <i class="el-icon-menu"></i>
                 <span>书籍管理</span>
             </template>
         </el-menu-item>
     </el-menu>
     ```

   - `el-form`表单

     ```html
     <el-form
             ref="form"
             :model="form"
             size="normal"
             :rules="rules"
     >
         <el-form-item prop="username">
             <el-input prefix-icon="el-icon-user-solid" v-model="form.username"></el-input>
         </el-form-item>
         <el-form-item prop="password">
             <el-input
                     prefix-icon="el-icon-lock"
                     v-model="form.password"
                     type="password"
                     autocomplete="off"
             ></el-input>
         </el-form-item>
         <el-form-item>
             <el-button style="width: 100%" type="primary" @click="login">注册</el-button>
         </el-form-item>
     </el-form>
     ```
     
     
     
   - `el-button`按钮
   
     ```html
     <el-input
                     prefix-icon="el-icon-lock"
                     v-model="form.checkpassword"
                     type="password"
                     autocomplete="off"
             ></el-input>
     ```
     
     
     
   - `el-table`表格
   
     ```html
     <el-table :data="tableData" stripe style="width: 100%">	
       <el-table-column prop="id" label="ID" sortable/>
       <el-table-column prop="username" label="用户名" />
       <el-table-column prop="nickname" label="昵称" />
       <el-table-column prop="age" label="年龄" />
       <el-table-column prop="sex" label="性别" />
       <el-table-column prop="address" label="地址" />
       <el-table-column label="操作">
         <template #default="scope">
           <el-button size="mini" @click="handleEdit(scope.row)"
           >Edit</el-button
           >
           <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.id)">
             <template #reference>
               <el-button
                       size="mini"
                       type="danger"
               >Delete</el-button>
             </template>
           </el-popconfirm>
         </template>
       </el-table-column>
     </el-table>
     ```
     
     
     
   - `el-popconfirm`弹出确认框
   
     ```html
     <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.id)">
             <template #reference>
               <el-button
                       size="mini"
                       type="danger"
               >Delete</el-button><!--删除按钮-->
             </template>
     </el-popconfirm>
     ```
     
   -  `el-dailog`对话框
   
     ```html
     <el-dialog
             v-model="centerDialogVisible"
             title="输入信息"
             width="30%"
             destroy-on-close
             center
     >
       <el-form label-width="120px">
         <el-form-item label="用户名"   >
           <el-input
                   v-model="ruleForm.username"
                   type="text"
                   autocomplete="off"
                   style="width: 80%"
           ></el-input>
         </el-form-item>
         
       <template #footer>
       <span class="dialog-footer">
         <el-button @click="centerDialogVisible = false">取消</el-button>
         <el-button type="primary" @click="saveUser">确认</el-button>
       </span>
       </template>
     </el-dialog>
     ```
   
     
   
   - `el-date-picker`日期选择器
   
     ```html
     <el-date-picker
             v-model="ruleForm.datetime"
             type="date"
             style="width: 80%"
             placeholder="Pick one or more dates"
             value-format="YYYY-MM-DD"	// 日期格式化
     >
     </el-date-picker>
     ```
   
     


## 二、SpringBoot环境

1. **起步依赖**

   选择`Idea`工具中的`File->new->module`,选择`Spring Initialer`,输入自己的环境基本信息，选择`Spring Web`，`Lombok`,`JDBC`，`Mysql Driver`起步依赖，开始构建SpringBoot项目

2. **整理Pom文件**

   引入`mybatis-plus`起步依赖，此处为我第一次使用该工具，按我理解应该是与hibernate类似的持久层框架,虽然目前我也还没有学习`hibernate`

   ```xml
   <dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>mybatis-plus-boot-starter</artifactId>
       <version>3.4.3.3</version>
   </dependency>
   ```

   引入`hutool`依赖，里面有很多`java`工具类

   ```xml
   <dependency>
       <groupId>cn.hutool</groupId>
       <artifactId>hutool-all</artifactId>
       <version>5.7.13</version>
   </dependency>
   ```

   [hutool官网](https://hutool.cn/)

3. `SpringBoot`的`application.properties`配置内容

   ```properties
   # 配置端口
   server.port=9090	
   # 配置数据库链接信息
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.password=20021112nl
   spring.datasource.username=root
   spring.datasource.url=jdbc:mysql://localhost:3306/springboot_vue
   ```



# Ⅱ 正式开发
## 一、Vue基本组件

> 基本组件即，`Vue`主体页面的ui样式以及基本交互效果，大部分ui样式都可以从`element-plus`官网上找到较好的效果，不过还需要部分细微修改

### 1. Header头部导航栏
```vue
<template>
    <div class="nav">
        <div class="Back">后台管理</div>
        <div style="flex:1"></div>
        <div style="width:100px">
            <el-dropdown>	<!-- 下拉列表框样式 -->
                <span class="el-dropdown-link">
                  {{username}}<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item>个人信息</el-dropdown-item>
                        <el-dropdown-item @click="exitSystem">退出系统</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>
```

```js
<script>
    export default {
        name: "Header",
        data(){
            return{
                username:''	// 用于展示
            }
        },
        mounted() {
            // 从会话存储区中读取当前登录用户的信息
            this.username=JSON.parse(sessionStorage.getItem('user')).username
        },
        methods:{
            // 退出系统的回调
            exitSystem(){
                sessionStorage.removeItem('user')	// 清除会话存储区的缓存
                this.$router.push('/login')	// 路由到登陆页面
            }
        }
    }
</script>
```

### 2. ASide侧边导航栏

```html
<template>
    <div>
        <el-col :span="12">
            <el-menu	<!--侧边导航栏样式-->
                    style="width:200px;min-height:calc(100vh-50px)"
                    :default-active="path"	<!--默认高亮-->
                    class="el-menu-vertical-demo"	<!--垂直排列-->
                    :default-openeds="[1]"	<!--默认展开-->
                    router	<!--路由，根据菜单项的index从路由中找到对应组件并加载-->
            >
                <el-sub-menu index="1" >	<!--包含子导航栏的导航栏-->
                    <template #title>
                        <i class="el-icon-location"></i>
                        <span>系统管理</span>
                    </template>
                    <el-menu-item index="/user">	<!--单独的导航-->
                        <template #title>用户管理</template>
                    </el-menu-item>
                </el-sub-menu>
                <el-menu-item index="/book" >	<!--单独的导航-->
                    <template #title>
                        <i class="el-icon-menu"></i>
                        <span>书籍管理</span>
                    </template>
                </el-menu-item>
            </el-menu>
        </el-col>
    </div>
</template>
```

```js
export default {
        name: "ASide",
        data(){
            return {
                path:this.$route.path	// 获取当前访问的页面，通过这个来控制哪个导航高亮
            }
        }
    }
```

### 3. 主体布局

```html
<template>
    <div>
        <!--头部-->
        <Header></Header>
        <div class="main">
            <ASide></ASide> <!--侧边栏-->
        </div>
        <!--主体-->
        <!--路由的组件放置在这里-->
        <router-view style="display: flex;flex-direction: column"></router-view>
    </div>
</template>
```

```js
import Header from "../components/Header";	// 引入组件
import ASide from "../components/ASide";
export default {
    name: "Layout",
    components:{	// 注册组件
        Header,
        ASide
    }
}
```

> 效果图
>
> ![image-20210926075242370](C:\Users\32327\AppData\Roaming\Typora\typora-user-images\image-20210926075242370.png)
>
> 主体部分的主体组件展示将在后面提到

## 二、Vue路由

> 路由定义了一系列规则，规定当收到了一个url后要使用哪一个组件来响应该url，即使用哪个组件放置到`router-view`位置

```js
import { createRouter, createWebHistory } from 'vue-router'
import Layout from "../layout/Layout";	// 第一种引入组件的方式
import Book from "../views/Book";
const routes = [
  {
    path: '/',	// 配置路由路径
    name: 'Layout',	// 路由名
    component: Layout,	// 路由组件
    redirect:"/user",	// 重定向，默认导航到user组件
    children:[	// 子组件
      {
        path:'user',	// 子组件的路径不用写/
        name:'user',	// 路由名，通过这个找到这个组件
        component:() => import("@/views/User")	// 第二种引入组件的方式
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

```

## 三、功能页面

### 1. 注册界面

**1.  前端**

> 当用户第一次使用时，先进入到注册页面注册账号，注册成功后自动跳转到登陆页面登录

```html
<template>
    <div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden;">
        <div style="width: 400px;margin:calc(30vh) auto;">
            <div style="color:#cccccc;font-size:30px;text-align: center;padding:30px 0">欢迎注册</div>
            <el-form
                    ref="form"  <!--引用别名，相当于组件id，可以通过 refs函数得到这个组件-->
                    :model="form"
                    size="normal"
                    :rules="rules"	<!--制定表单提交规则-->
            >
                <el-form-item prop="username">	<!--表单项-->
                    <el-input prefix-icon="el-icon-user-solid" v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input	<!--输入框-->
                            prefix-icon="el-icon-lock"
                            v-model="form.password"
                            type="password"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
                <el-form-item prop="checkpassword">
                    <el-input
                            prefix-icon="el-icon-lock"
                            v-model="form.checkpassword"
                            type="password"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button style="width: 100%" type="primary" @click="login">注册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
```

> 小Tip： calc()为计算函数

```js
<script>
    import request from "../utils/request";	// 引入发送Ajax请求的工具类
    export default {
        name: "Login",
        data(){
            return {
                form:{

                },rules:{	// 表单提交规则	required：true标识必须填写，message表示验证失败时的提示，trigger：blur表示失去焦点时触发检测
                    username: [{ required:true,message:"请输入用户名" , trigger: 'blur' }],
                    password: [{ required:true,message:"请输入密码" , trigger: 'blur' }],
                    checkpassword: [{ required:true,message:"请确认密码" , trigger: 'blur' }],
                }
            }
        },
        methods:{
            login(){
                this.$refs["form"].validate((valid)=>{	// 首先通过ref名获取到表单组件判断是否合法
                    if(valid){
                        if(this.form.password!==this.form.checkpassword){
                            this.$message({
                                type:'error',
                                message:"两次密码输入不一致"
                            })
                        }else{
                            // 向后台发送Ajax请求，传入表单项
                            request.post('/api/user/register',this.form).then(res=>{
                                if(res.code==='0'){	// 如果code为0则表示成功
                                    this.$message({	// 弹出一个注册成功提示框
                                        type:'success',
                                        message:"注册成功"
                                    })
                                    this.$router.push("/login")  // 注册成功之后页面跳转到登录页面
                                }else{
                                    this.$message({
                                        type:'error',
                                        message:res.msg
                                    })
                                }
                            })
                        }
                    }
                })

            }
        }
    }
</script>
```

**2. 后端**

```java
    @PostMapping("/register")	// 接受的路径
    public Result<?> Register(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));	// 查询后台是否用同样用户名的用户
        if(res!=null){
            return Result.error("-1","用户名重复");
        }
        userMapper.insert(user);	// 插入个人信息
        return Result.success();
    }
```

> 此处出现了几个未知的类，它们定义如下

```java
User
@TableName("user")	// 表名  mybatis-plus比mybatis好就好在这，不用手动写sql语句，只需要提供表名，然后就会给你一些基本的sql，很方便，面向对象的持久层框架
@Data	// Lomback插件功能，生成getter、setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)	// 标识主键，type为自增
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String sex;
    private Integer age;
    private String address;
}
```

```java
Result
// 此类用于返回结果
@Configuration	// 标识是个配置类
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    /*
    	getter,setter
    	...
    */

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }
	// 成功返回不带返回结果
    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }
	// 成功返回并带返回结果
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }
	// 错误返回
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
```

```java
userMapper
// 持久层接口，集成BaseMapper可以提供一些默认的sql语句，可满足大部分需要，也在这里面可以定义一些自己的sql语句
public interface UserMapper extends BaseMapper<User> {
}
```

![image-20210926082809993](C:\Users\32327\AppData\Roaming\Typora\typora-user-images\image-20210926082809993.png)

![image-20210926082836895](C:\Users\32327\AppData\Roaming\Typora\typora-user-images\image-20210926082836895.png)

### 2. 登录页面

**1. 前端**

> 当用户还未登录且已注册成功时进入此页面，页面与注册基本类似，此处就不详细注释

```html
<template>
    <div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden;">
        <div style="width: 400px;margin:calc(30vh) auto;">
            <div style="color:#cccccc;font-size:30px;text-align: center;padding:30px 0">欢迎登陆</div>
            <el-form
                    ref="form"
                    :model="form"
                    size="normal"
                    :rules="rules"
            >
                <el-form-item prop="username">
                    <el-input prefix-icon="el-icon-user-solid" v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%" type="primary" @click="login">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
```

```js
<script>
    import request from "../utils/request";
    export default {
        name: "Login",
        data(){
            return {
                form:{

                },rules:{
                    username: [{ required:true,message:"请输入用户名" , trigger: 'blur' }],
                    password: [{ required:true,message:"请输入密码" , trigger: 'blur' }],
                }
            }
        },
        methods:{
            login(){
                this.$refs["form"].validate((valid)=>{
                    if(valid){
                        request.post('/api/user/login',this.form).then(res=>{
                            if(res.code==='0'){
                                this.$message({
                                    type:'success',
                                    message:"登录成功"
                                })
                                // 登陆成功将用户保存到会话存储区中
                                sessionStorage.setItem('user',JSON.stringify(this.form))
                                this.$router.push("/")  // 登录成功之后页面跳转
                            }else{
                                this.$message({
                                    type:'error',
                                    message:res.msg
                                })
                            }
                        })
                    }
                })
            }
        }
    }
</script>
```

**2. 后端**

```java
    @PostMapping("/login")	// 访问路径为/login时此方法处理请求
    public Result<?> Loginin(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));	// 匹配是否存在该用户
        if(res==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success();
    }
```

![image-20210926082857766](C:\Users\32327\AppData\Roaming\Typora\typora-user-images\image-20210926082857766.png)

### 3. 用户操作页面

> 由于页面结构稍微有些复杂，功能较多，以下内容将用户页面拆分，单独说明各个功能

#### ① 展示用户

> 展示所有用户基本信息，并且可以对用户信息编辑修改删除等

**1. 前端**

```html
<!--表格-->
<!--:data为表格中展示的数据源-->
<el-table :data="tableData" stripe style="width: 100%">	
   <!--prop为属性名，这个名字要与数据源中的名字对应 label为表的列名 sortable指定可排序-->
  <el-table-column prop="id" label="ID" sortable/>
  <el-table-column prop="username" label="用户名" />
  <el-table-column prop="nickname" label="昵称" />
  <el-table-column prop="age" label="年龄" />
  <el-table-column prop="sex" label="性别" />
  <el-table-column prop="address" label="地址" />
  <el-table-column label="操作">
    <template #default="scope">	
      <el-button size="mini" @click="handleEdit(scope.row)"
      >Edit</el-button><!--编辑按钮-->
      <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.id)">
        <template #reference>
          <el-button
                  size="mini"
                  type="danger"
          >Delete</el-button><!--删除按钮-->
        </template>
      </el-popconfirm>
    </template>
  </el-table-column>
</el-table>
```

> @confirm="handleDelete(scope.row.id) 这个配置项指定点击确认删除后的回调函数

```js
load(){	 // 初次进入界面则加载数据
  // get 请求需要加params
  request.get("/api/user",{
    params:{
      // 如下为分页和查询的基本信息
      number:this.currentPage,	
      pagesize:this.pageSize,
      search:this.search
    }
  }).then(res=>{
    // 返回结果后将数据赋值给表格
    this.tableData=res.data.records
    this.total=res.data.total
  })
}
// 点击删除时的回调
handleDelete(id){
   // 传入id，将对应id的用户从数据库中删除，删除发送delete请求
  request.delete('/api/user/'+id).then(res=>{
    if(res.code==='0'){
      this.$message({	// 删除成功则弹出消息提示框
        type:'success',
        message:"删除成功"
      })
    }else{
      this.$message({
        type:'error',
        message:res.msg
      })
    }
    this.load()	// 重新加载页面
  })
},
```

> 由于编辑操作稍微复杂，后面单独谈

**2. 后端**

```java
    @GetMapping
	// 查找用户
    public Result<?> findUser(@RequestParam(defaultValue = "1") Integer number,@RequestParam(defaultValue = "10") Integer pagesize ,@RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StrUtil.isNotBlank((search))){	// 如果查询条件不为空则设置查询条件
            wrapper.like(User::getNickname, search);
        }
        // 从数据库中查询数据，此处涉及到分页操作，下面介绍分页的基本配置
        Page<User> userPage = userMapper.selectPage(new Page<>(number, pagesize), wrapper);
        return Result.success(userPage);
    }

	// Delete操作通过这种方式接收参数
    @DeleteMapping("/{id}")
	// 使用@PathVariable注解给属性赋值
    public Result<?> deleteUser(@PathVariable Long id){
        userMapper.deleteById(id);	// 通过id删除用户
        return Result.success();
    }
```

```java
//分页插件配置
// 新建一个MybatisPlusConfig的java文件
@Configuration
@MapperScan("com.nilu.mapper")	// 扫描mapper接口文件，生成对应的sql语句
public class MybatisPlusConfig {
    /**
     * 分页插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```



#### ② 编辑用户

> 当用户点击编辑用户时，会弹出一个消息对话框，让用户更新对应信息，之后确认更新后发送请求到后端更新数据库

**1. 前端**

```html
<!--对话框-->
<el-dialog
        v-model="centerDialogVisible"	<!--对话框可见性-->
        title="输入信息"
        width="30%"
        destroy-on-close	<!--关闭时则销毁-->
        center
>
  <el-form label-width="120px">	<!--嵌入表单-->
    <el-form-item label="用户名"   >
      <el-input
              v-model="ruleForm.username"
              type="text"
              autocomplete="off"
              style="width: 80%"
      ></el-input>
    </el-form-item>
    <el-form-item label="昵称"   >
      <el-input
              v-model="ruleForm.nickname"
              type="text"
              autocomplete="off"
              style="width: 80%"
      ></el-input>
    </el-form-item>
    <el-form-item label="年龄"   >
      <el-input
              v-model="ruleForm.age"
              type="text"
              autocomplete="off"
              style="width: 80%"
      ></el-input>
    </el-form-item>
    <el-form-item label="性别"   >
      <el-radio v-model="ruleForm.sex" label="男">男</el-radio>
      <el-radio v-model="ruleForm.sex" label="女">女</el-radio>
      <el-radio v-model="ruleForm.sex" label="未知">未知</el-radio>
    </el-form-item>
    <el-form-item label="地址"   >
      <el-input
              v-model="ruleForm.address"
              type="textarea"
              autocomplete="off"
              style="width: 80%"
      ></el-input>
    </el-form-item>
  </el-form>
  <template #footer>	
  <span class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取消</el-button>
    <el-button type="primary" @click="saveUser">确认</el-button>
  </span>
  </template>
</el-dialog>
```

```js
// 点击编辑按钮的回调
handleEdit(row){
      this.ruleForm=JSON.parse(JSON.stringify((row)))	// 将表格中的对应行信息付给弹出框内的表单
      this.centerDialogVisible=true	// 设置弹出框可见性
},
// 编辑完成点击确认按钮的回调
saveUser(){
    // 更新发送put请求
    request.put("/api/user", this.ruleForm).then(res => {
      if(res.code==='0'){
        this.$message({	// 弹出消息提示框
          type:'success',
          message:"更新成功"
        })
      }else{
        this.$message({
          type:'error',
          message:res.msg
        })
      }
      this.load()	// 重新加载页面
      this.centerDialogVisible=false	// 设置弹出框可见性
    })
}
```

**2. 后端**

```java
    @PutMapping
    public Result<?> updateUser(@RequestBody User user){
        userMapper.updateById(user);	// 通过id更新用户
        return Result.success();
    }
```

#### ③ 新增用户

> 用户点击新增按钮后，弹出对话框输入用户信息，发送请求给后台新增用户，此处的对话框与编辑用户使用的是同一个

**1. 前端**

```html
div style="margin: 10px 0;">
      <el-button @click="addUser">新增</el-button>
      <el-button>导入</el-button>
      <el-button>导出</el-button>
</div>
```

```js
addUser(){
  this.ruleForm={}	// 将上次的对话框中的表单信息清空
  this.centerDialogVisible=true	// 设置对话框可见性
}

saveUser(){
    // 发送post请求，并携带表单中的数据
    request.post("/api/user", this.ruleForm).then(res => {
      if(res.code==='0'){
        this.$message({	// 弹出消息提示框
          type:'success',
          message:"新增成功"
        })
      }else{
        this.$message({
          type:'error',
          message:res.msg
        })
      }
      this.load()	// 更新页面
      this.centerDialogVisible=false	// 设置对话框可见性
    })
},
```

**2. 后端**

```java
    @PostMapping
    public Result<?> addUser(@RequestBody User user){
        if(user.getPassword()==null){	// 如果密码为空则设置默认密码
            user.setPassword("123456");
        }
        userMapper.insert(user);	// 插入用户
        return Result.success();
    }
```

#### ④ 分页功能

**1. 前端**

```html
<!--分页展示组件-->
<el-pagination
              v-model:currentPage="currentPage"
              :page-sizes="[5, 10, 20]"	// 可选的页面大小
              :page-size="pageSize"	// 当前页的页面大小
              layout="total, sizes, prev, pager, next, jumper"	//猜测应该是布局
              :total="total"	//总条数
              @size-change="handleSizeChange"	//页面条数发生变化时的回调
              @current-change="handleCurrentChange"	//当前页面发生变化时的回调
      >
</el-pagination>
```

```js
// 当页面大小发生变化时更新当前页面大小并重新加载页面
handleSizeChange(pageSize){
  this.pageSize=pageSize
  this.load()
},
// 当当前页面发生变化时，更新当前页并重新加载页面
handleCurrentChange(number){
  this.currentPage=number
  this.load()
}
```

> 后端使用了分页插件，可以读出分页的数据

### 4. 书籍操作页面

> 书籍管理页面与用户管理页面基本一致，以下仅为书籍的特别点
>
> 书籍管理功能较少

#### ① 日期选择功能

**1. 前端**

```html
<el-date-picker
        v-model="ruleForm.datetime"
        type="date"
        style="width: 80%"
        placeholder="Pick one or more dates"
        value-format="YYYY-MM-DD"	// 日期格式化
>
</el-date-picker>
```

**2. 后端**

```java
Book类的定义
@TableName("book")	// 表明
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @TableId(type = IdType.AUTO)	// 主键，自动递增
    private Integer id;
    private String bookname;
    private String publish;
    private Float price;
    //@JsonFormat(pattern = "yyyy-mm-dd" ,timezone = "GMT+8")	时间格式化此处我选择的是在springboot配置文件中设置
    private String datetime;
}
```

```properties
# 配置日期格式化模式
spring.jackson.date-format=yyyy-MM-dd 
```

```java
BookMapper
public interface BookMapper extends BaseMapper<Book> {
}
```

```java
BookController
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    BookMapper BookMapper;

    @PostMapping
    public Result<?> addBook(@RequestBody Book book){
        BookMapper.insert(book);
        return Result.success();
    }
    @GetMapping
    public Result<?> findBook(@RequestParam(defaultValue = "1") Integer number,@RequestParam(defaultValue = "10") Integer pagesize ,@RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if(StrUtil.isNotBlank((search))){
            wrapper.like(Book::getBookname, search);
        }
        Page<Book> BookPage = BookMapper.selectPage(new Page<>(number, pagesize), wrapper);
        return Result.success(BookPage);
    }
    @PutMapping
    public Result<?> updateBook(@RequestBody Book Book){
        BookMapper.updateById(Book);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> deleteBook(@PathVariable Long id){
        BookMapper.deleteById(id);
        return Result.success();
    }
}
```

## 四、部署与上传

### 1. 安装配置nginx

1. 从网上下载nginx的压缩包，移动至云服务器的/usr/local/nginx目录

2.  依次使用以下命令
    1.  `tar -zxvf xxxnginx.tar.gz`
    2.  `cd xxxnginx`
    3.  `make`
    4.  `make install`
    
3.  安装成功后配置nginx

   1. `vim /usr/local/nginx/conf/nginx.conf`

   2. 修改配置如下

      > 由于还未学习nginx，所以还不懂这些配置是干嘛的

      ```
          server {	// 一个server节点就是一个虚拟主机
              listen       8089;	// 监听端口
              server_name  106.14.196.77;	// 本机host
      
              location / {	// vue
                  root   /usr/local/server/dist;
                  index  index.html index.htm;
                  try_files $uri $uri/ /index.html;
              }
      
              location /api {	// springboot
                proxy_pass http://localhost:9090/;
      
              }
              error_page   500 502 503 504  /50x.html;
              location = /50x.html {
                  root   html;
              }
      }
      ```

      

4.  重启nginx

   `/usr/local/nginx/sbin/nginx -s reload`

5.  开启端口 `firewall-cmd --zone=public --add-port=8089/tcp --permanent`

6. 目前通过外网已经可以访问到程序

7. nginx常用命令

   1.   `./nginx -s stop` |  `./nginx -s quit` 关闭服务
   2.  `./nginx -s reload` 重新加载配置文件

 ## 五、待开发

### 1.  用户头像上传与展示

> 此处需要用到文件的上传于下载

### 2. 富文本编辑器的引入

> 这个放置于一个新的新闻页面中，需要引入wangEditor工具

  ### 3. 待

## 六、 总结

> 本次开发学习到了很多东西，后续会将待开发功能都开发出来，目前还有很多不合理的地方，以后也会逐一完善
