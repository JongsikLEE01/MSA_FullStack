import React from "react";

class CalssComponent extends React.Component{
    // 클래스컴포넌트에서 상태(state) 정의
    // 1. 생성자 정의
    // 2. 생성자 안에서 state 속성 정의
    constructor(props){
        super(props)

        // 상태 정의
        this.state = {
            name : "김조은",
            msg : ""
        }

        // this 바인딩
        // this.handleApply의 this.handleApply.bind(this)를 통해 객체를 가르키는 this로 설정
        this.handleApply = this.handleApply.bind(this)
        this.handleStop = this.handleStop.bind(this)
        this.handleNoMsg = this.handleNoMsg.bind(this)
        this.handleName = this.handleName.bind(this)
        this.handleOrgin = this.handleOrgin.bind(this)
        this.handleEverything = this.handleEverything.bind(this)
    }

    // 입사지원 클릭
    handleApply(){
        console.log('입사 지원 클릭!')
        // 상태 업데이트
        this.setState({msg : '입사 지원을 했습니다...'})
    }

    // 중도 포기 클릭
    handleStop(){
        console.log('중도 포기 클릭!')
        // 상태 업데이트
        this.setState({msg : '중도 포기 했습니다...'})
    }

    // 삭제 클릭
    handleNoMsg(){
        console.log('삭제 클릭!')
        // 상태 업데이트
        this.setState({msg : ''})
    }

    // 이름변경 클릭
    handleName(){
        console.log('이름변경 클릭!')
        // 상태 업데이트
        this.setState({name : '이종식'})
    }

    // 원래대로 클릭
    handleOrgin(){
        console.log('원래대로 클릭!')
        // 상태 업데이트
        this.setState({name : '김조은'})
    }

    // 모두 변경 클릭
    handleEverything(){
        console.log('모두 변경 클릭!')
        // 상태 업데이트
        this.setState({name : '홍길동', msg : '집가고싶다'})
    }



    render(){
        // 구조 분해 할당
        const {name, msg} = this.state
        return(
            <div>
                <h1>클래스 컴포넌트</h1>
                <h1>Hello {name}</h1>
                <p>{msg}</p>
                <button onClick={this.handleApply}>입사 지원</button>
                <button onClick={this.handleStop}>중도 포기</button>
                <button onClick={this.handleNoMsg}>삭제</button>
                <br/><br/>
                <button onClick={this.handleName}>이름 변경</button>
                <button onClick={this.handleOrgin}>원래 대로</button>
                <br/><br/>
                <button onClick={this.handleEverything}>모두 변경</button>
            </div>
        )
    }
}

export default CalssComponent