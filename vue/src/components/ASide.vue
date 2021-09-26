<template>
    <div>
        <el-col :span="12">
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
        </el-col>
    </div>
</template>

<script>
    export default {
        name: "ASide",
        data(){
            return {
                path:this.$route.path
            }
        }
    }
</script>
<style scoped>

</style>