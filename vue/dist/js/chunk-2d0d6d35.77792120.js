(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0d6d35"],{"73cf":function(e,t,o){"use strict";o.r(t);var r=o("7a23"),c={style:{width:"100%",height:"100vh","background-color":"darkslateblue",overflow:"hidden"}},n={style:{width:"400px",margin:"calc(30vh) auto"}},a=Object(r["createElementVNode"])("div",{style:{color:"#cccccc","font-size":"30px","text-align":"center",padding:"30px 0"}},"欢迎注册",-1),l=Object(r["createTextVNode"])("注册");function s(e,t,o,s,u,d){var i=Object(r["resolveComponent"])("el-input"),m=Object(r["resolveComponent"])("el-form-item"),p=Object(r["resolveComponent"])("el-button"),f=Object(r["resolveComponent"])("el-form");return Object(r["openBlock"])(),Object(r["createElementBlock"])("div",c,[Object(r["createElementVNode"])("div",n,[a,Object(r["createVNode"])(f,{ref:"form",model:u.form,size:"normal",rules:u.rules},{default:Object(r["withCtx"])((function(){return[Object(r["createVNode"])(m,{prop:"username"},{default:Object(r["withCtx"])((function(){return[Object(r["createVNode"])(i,{"prefix-icon":"el-icon-user-solid",modelValue:u.form.username,"onUpdate:modelValue":t[0]||(t[0]=function(e){return u.form.username=e})},null,8,["modelValue"])]})),_:1}),Object(r["createVNode"])(m,{prop:"password"},{default:Object(r["withCtx"])((function(){return[Object(r["createVNode"])(i,{"prefix-icon":"el-icon-lock",modelValue:u.form.password,"onUpdate:modelValue":t[1]||(t[1]=function(e){return u.form.password=e}),type:"password",autocomplete:"off"},null,8,["modelValue"])]})),_:1}),Object(r["createVNode"])(m,{prop:"checkpassword"},{default:Object(r["withCtx"])((function(){return[Object(r["createVNode"])(i,{"prefix-icon":"el-icon-lock",modelValue:u.form.checkpassword,"onUpdate:modelValue":t[2]||(t[2]=function(e){return u.form.checkpassword=e}),type:"password",autocomplete:"off"},null,8,["modelValue"])]})),_:1}),Object(r["createVNode"])(m,null,{default:Object(r["withCtx"])((function(){return[Object(r["createVNode"])(p,{style:{width:"100%"},type:"primary",onClick:d.login},{default:Object(r["withCtx"])((function(){return[l]})),_:1},8,["onClick"])]})),_:1})]})),_:1},8,["model","rules"])])])}var u=o("b775"),d={name:"Login",data:function(){return{form:{},rules:{username:[{required:!0,message:"请输入用户名",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"}],checkpassword:[{required:!0,message:"请确认密码",trigger:"blur"}]}}},methods:{login:function(){var e=this;this.$refs["form"].validate((function(t){t&&(e.form.password!==e.form.checkpassword?e.$message({type:"error",message:"两次密码输入不一致"}):u["a"].post("/api/user/register",e.form).then((function(t){"0"===t.code?(e.$message({type:"success",message:"注册成功"}),e.$router.push("/login")):e.$message({type:"error",message:t.msg})})))}))}}},i=o("6b0d"),m=o.n(i);const p=m()(d,[["render",s]]);t["default"]=p}}]);
//# sourceMappingURL=chunk-2d0d6d35.77792120.js.map