// Set 생성
const set = new Set()

// 요소 추가
set.add('상하이스파이시 버거')
set.add('타바스코 버거')
set.add('1955 버거')
set.add('빅맥')

// 조회
console.log(set.has('타바스코 버거'))

// 원소 삭제
set.delete('빅맥')

// 크기 확인
console.log(set.size)

// 초기화 생성
const set2 = new Set(['라이스 버거','핫크리스피 버거','불고기 버거','돈까스 버거'])

// set 반복
console.log('set 반복')
set.forEach((value) =>{
    console.log(value)
})

console.log('set2 반복')
for(const value of set2){
    console.log(value)
}