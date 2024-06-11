// const input = input('이름? ')
let input

// input가 null일 경우 김조은을 대입 => null 병합
// const nickname = input ?? '김조은'
const nickname = input

if(nickname){
    console.log('이름이 존재합니다...')
}
if(!nickname){
    console.log('이름이 존재하지 않습니다...')
}

console.log(`이름은? ${nickname}`)

