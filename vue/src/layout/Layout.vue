<template>
    <div>
        <!--头部-->
        <Header></Header>
        <!--    <Home></Home>-->
        <div class="main">
            <ASide></ASide> <!--侧边栏-->
        </div>
        <!--主体-->
        <router-view style="display: flex;flex-direction: column"></router-view>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import ASide from "../components/ASide";
    export default {
        name: "Layout",
        components:{
            Header,
            ASide
        }
    }
</script>

<style scoped>

</style>