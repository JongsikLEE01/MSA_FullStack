// 모듈이 설치 되어야함
// npm install axios
// axios는 라이브러리로 포함시켜야함
const axios = require('axios')

const url = 'http://httpbin.org/post'
const data = {
    name : 'jslee',
    age : 25
}
const headers = {
    'ContentType' : 'application/json'
}

// axios 요청
axios.post(url, data, headers)
    .then(response =>{
        console.log(`data? ${response.data}`)
        console.dir(response.data)
    })
    .catch(error =>{
        console.log(`error? ${error}`);
    })