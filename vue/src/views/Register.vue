<template>
    <div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden;">
        <div style="width: 400px;margin:calc(30vh) auto;">
            <div style="color:#cccccc;font-size:30px;text-align: center;padding:30px 0">欢迎注册</div>
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
                    checkpassword: [{ required:true,message:"请确认密码" , trigger: 'blur' }],
                }
            }
        },
        methods:{
            login(){
                this.$refs["form"].validate((valid)=>{
                    if(valid){
                        if(this.form.password!==this.form.checkpassword){
                            this.$message({
                                type:'error',
                                message:"两次密码输入不一致"
                            })
                        }else{
                            request.post('/api/user/register',this.form).then(res=>{
                                if(res.code==='0'){
                                    this.$message({
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

<style scoped>

</style>