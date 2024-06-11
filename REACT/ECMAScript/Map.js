// Map 생성
const map = new Map()
// set(key, vlaue) : 요소 추가
map.set('국어', '90점')
map.set('수학', '50점')
map.set('영어', '60점')
map.set('과학', '70점')

// get(key) : 요소 가져오기
console.log(map);
console.log(map.get('국어'));
console.log(map.get('수학'));
console.log(map.get('영어'));
console.log(map.get('과학'));

// Map 초기화 생성
const map2 = new Map([
    ['영어', '80점'],
    ['사회', '75점']
])

console.log(map2)

// Map 반복
console.log('map의 반복')
map.forEach((value, key, map) =>{
    console.log(`${key} : ${value}`)
})

// Map의 key 반복
console.log('map의 key 반복')
for(const key of map.keys()){
    console.log(key)
}

// Map의 value 반복
console.log('map의 value 반복')
for(const value of map.values()){
    console.log(value)
}