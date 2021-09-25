<template>
    <div class="nav">
        <div class="Back">后台管理</div>
        <div style="flex:1"></div>
        <div style="width:100px">
            <el-dropdown>
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

<script>
    export default {
        name: "Header",
        data(){
            return{
                username:''
            }
        },
        mounted() {
            this.username=JSON.parse(sessionStorage.getItem('user')).username
        },
        methods:{
            exitSystem(){
                sessionStorage.removeItem('user')
                this.$router.push('/login')
            }
        }
    }
</script>

<style scoped>
    .nav{
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid #ccc;
        display: flex;
    }
    .Back{
        width:200px;
        color:skyblue;
        font-weight: bolder;
    }
    .el-dropdown-link {
        cursor: pointer;
        color: #409eff;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }
</style>