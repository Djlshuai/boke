<template>
<div>
  <Header></Header>
  <div class="mblog">
    <h2>{{ blog.title }}</h2>
    <el-link icon="el-icon-edit"  v-if="ownBlog">
      <router-link :to="{name :'BlogEdit',params:{blog :blog.id}}">
      编辑
      </router-link>
    </el-link>
    <el-divider></el-divider>
    <div class="markdown-body" v-html="blog.content"></div>
  </div>

</div>

</template>

<script>
import Header from "../components/Header"
import "github-markdown-css/github-markdown.css"
export default {
  name: "BlogDetail.vue",
  components:{Header},
  data(){
    return{
      blog:{
        id:"",
        title:"",
        content:"",
      },
      ownBlog:false,
    }
  },
  created() {
    const blogId = this.$route.params.blogId
    const _this = this
    this.$axios.get('/blog/' + blogId).then((res) => {
      const blog = res.data.data
      _this.blog.id = blog.id
      _this.blog.title = blog.title
      var MardowmIt = require("markdown-it")
      var md = new MardowmIt()
      let render = md.render(blog.content);
      _this.blog.content = render

      _this.ownBlog =(blog.userId === _this.$store.getters.getUser.id)
    })

  }

}
</script>

<style scoped>
.mblog{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width:100%;
  min-height: 700px ;

}
</style>