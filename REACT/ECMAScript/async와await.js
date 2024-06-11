// async는 함수 앞에 작성, 해당 함수에 비동기 요청이 있음을 알림
// await는 비동기 응답을 대기함
async function fetchData(){
    // fetch : promise 반환
    const response = await fetch('https://httpbin.org/get')
    console.log(response)

    // JSON 객체로 변환
    const data = await response.json()
    return data
}

// async, await가 없는 경우 then으로 반환받아야함
// function fetchData(){
//     const response = fetch('https://httpbin.org/get')
//     console.log(response)

//     response.then((result) =>{
//         console.log(result)
//     })
// }

// await 사용 불가능, 최종 결과인 data를 가져오기 위해선 then을 작성해야함
fetchData()
    .then((data) =>{
        console.log(data)
    })

console.log(`async await 비동기 요청 처리`)