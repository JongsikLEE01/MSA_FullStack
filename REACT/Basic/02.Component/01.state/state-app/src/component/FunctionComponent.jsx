// 함수형 컴포넌트 자동완성
// fafce : React Arrow Function Component Export

import React, { useState } from 'react'

const FunctionComponent = () => {
    // 함수형 컴포넌트에서 state 정의
    // const[상태, 상태설정함수] = useState('초기값')

    // 배열 구조 분해 할당
    const [name, setName] = useState('김조은')
    const [msg, setMsg] = useState('')

    // 입사지원 클릭
    const handleApply = () => {
        console.log('입사 지원 클릭!')
        // 상태 업데이트
        setMsg('입사 지원을 했습니다....')
    }

    // 중도 포기 클릭
    const handleStop = () => {
        console.log('중도 포기 클릭!')
        // 상태 업데이트
        setMsg('중도 포기를 했습니다....')
    }


    return (
        <div>
            <h1>함수형 컴포넌트</h1>
            <h1>Hello {name}</h1>
            <p>{msg}</p>
            <button onClick={handleApply}>입사 지원</button>
            <button onClick={handleStop}>중도 포기</button>
        </div>
    )
}

export default FunctionComponent