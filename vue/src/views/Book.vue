<template>
    <div class="home" >
        <div style="margin: 10px 0;">
            <el-button @click="addUser">新增</el-button>
        </div>
        <!--搜索区域-->
        <div>
            <el-input
                    v-model="search"
                    placeholder="请输入查询条件"
                    prefix-icon="el-icon-search"
                    style="width: 30%"
                    clearable
            />
            <el-button @click="load">查询</el-button>
        </div>
        <!--表格-->
        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="id" label="ID" sortable/>
            <el-table-column prop="bookname" label="书名" />
            <el-table-column prop="publish" label="出版社" />
            <el-table-column prop="price" label="价格" />
            <el-table-column prop="datetime" label="出版时间" />
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

        <!--分页-->
        <div>
            <el-pagination
                    v-model:currentPage="currentPage"
                    :page-sizes="[5, 10, 20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
            >
            </el-pagination>
        </div>

        <el-dialog
                v-model="centerDialogVisible"
                title="输入信息"
                width="30%"
                destroy-on-close
                center
        >
            <el-form label-width="120px">
                <el-form-item label="书名">
                    <el-input
                            v-model="ruleForm.bookname"
                            type="text"
                            autocomplete="off"
                            style="width: 80%"
                    ></el-input>
                </el-form-item>
                <el-form-item label="出版社">
                    <el-input
                            v-model="ruleForm.publish"
                            type="text"
                            autocomplete="off"
                            style="width: 80%"
                    ></el-input>
                </el-form-item>
                <el-form-item label="价格">
                    <el-input
                            v-model="ruleForm.price"
                            type="text"
                            autocomplete="off"
                            style="width: 80%"
                    ></el-input>
                </el-form-item>
                <el-form-item label="出版日期">
                    <el-date-picker
                            v-model="ruleForm.datetime"
                            type="date"
                            style="width: 80%"
                            placeholder="Pick one or more dates"
                            value-format="YYYY-MM-DD"
                    >
                    </el-date-picker>
                </el-form-item>

            </el-form>
            <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveUser">确认</el-button>
      </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import request from "../utils/request";

    export default {
        name: "Book",
        data(){
            return{
                search: '',
                tableData: [
                ],
                ruleForm:{},
                currentPage:1,
                pageSize:10,
                total:10,
                centerDialogVisible:false,
            }
        },
        created() {
            this.load()
        },
        methods:{
            load(){
                // get 请求需要加params
                request.get("/api/book",{
                    params:{
                        number:this.currentPage,
                        pagesize:this.pageSize,
                        search:this.search
                    }
                }).then(res=>{
                    this.tableData=res.data.records
                    this.total=res.data.total
                })
            },
            handleEdit(row){
                this.ruleForm=JSON.parse(JSON.stringify((row)))
                this.centerDialogVisible=true
            },
            handleDelete(id){
                request.delete('/api/book/'+id).then(res=>{
                    if(res.code==='0'){
                        this.$message({
                            type:'success',
                            message:"删除成功"
                        })
                    }else{
                        this.$message({
                            type:'error',
                            message:res.msg
                        })
                    }
                    this.load()
                })
            },
            handleSizeChange(pageSize){
                this.pageSize=pageSize
                this.load()
            },
            handleCurrentChange(number){
                this.currentPage=number
                this.load()
            },
            addUser(){
                this.ruleForm={}
                this.centerDialogVisible=true
            },
            saveUser(){
                if(this.ruleForm.id){
                    request.put("/api/book", this.ruleForm).then(res => {
                        if(res.code==='0'){
                            this.$message({
                                type:'success',
                                message:"更新成功"
                            })
                        }else{
                            this.$message({
                                type:'error',
                                message:res.msg
                            })
                        }
                        this.load()
                        this.centerDialogVisible=false
                    })
                }
                else {
                    request.post("/api/book", this.ruleForm).then(res => {
                        if(res.code==='0'){
                            this.$message({
                                type:'success',
                                message:"新增成功"
                            })
                        }else{
                            this.$message({
                                type:'error',
                                message:res.msg
                            })
                        }
                        this.load()
                        this.centerDialogVisible=false
                    })
                }
            },
        }
    }
</script>

<style scoped>

</style>