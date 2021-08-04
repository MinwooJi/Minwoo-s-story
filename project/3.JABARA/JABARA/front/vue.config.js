module.exports = {
  transpileDependencies: ["vuetify"],

configureWebpack: {

     devServer: {
        // 서버
        //  host: 'i3d207.p.ssafy.io',
        // 로컬
         host: 'localhost',
         port: '8080'

     }

}
};