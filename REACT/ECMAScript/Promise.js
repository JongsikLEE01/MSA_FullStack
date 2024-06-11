// promise 비동기 요청
function delay(ms){
    const promise = new Promise((resolve, reject) =>{
        // 타이머 설정  : ms 밀리초 후 실행하는 함수
        setTimeout(()=>{
            resolve(`${ms} ms 후, 데이터 전송 받음...`)
        }, ms)
    })
    return promise
}

// promise를 이용한 비동기 처리
delay(2000)
    .then((result) =>{
        console.log(result)
        // 서버로 받은 데이터를 사용해 추가 작업 처리
    })
    .catch((error) =>{
        console.error(error)
    })

console.log(`promise 비동기 처리`)