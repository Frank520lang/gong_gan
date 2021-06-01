<template>
  <div id="upload">
    <el-upload
      class="upload-demo"
      action="http://localhost:8001/eduservice/fruit/readExcel"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      multiple
      :limit="1"
      :on-exceed="handleExceed"
      :file-list="fileList"
    >
      <el-button size="larger" type="primary">点击上传</el-button>
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <div slot="tip" class="el-upload__tip" id="tip">选择要添加的果实信息Excel表格</div>
    </el-upload>
  </div>
</template>
<script>
//import fruit from '@/api/fruit/fruit'
export default {
  data () {
    return {
      fileList: []
    };
  },
  methods: {
    handleRemove (file, fileList) {
      console.log(file, fileList);
    },
    handlePreview (file) {
      console.log(file);
    },
    handleExceed (files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove (file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },

  }
}
</script>
<style>
#upload {
  text-align: center;
  padding: 180px;
}
#tip {
  font-size: 50px;
}
</style>