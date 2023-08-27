const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: '../src/main/resources/static', //build경로
    devServer: {
      proxy: {
        '/api' : {
          target: 'http://localhost:8083', //spring boot server
          changeOrigin: true //cross origin허용
        }
      }
    },
  chainWebpack: config => {  
    const svgRule = config.module.rule("svg");    
    svgRule.uses.clear();    
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");  
  }  
})
