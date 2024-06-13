import { useEffect, useState } from 'react'
import TodoHeader from './TodoHeader'
import TodoInput from './TodoInput'
import TodoList from './TodoList'
import TodoFooter from './TodoFooter'

const TodoContainer = () => {
  // state 정의
  const [todoList, setTodoList] = useState([])

  // 데이터 가져오기
  // hook
  useEffect(()=>{
    // 비동기 요청 - 목록
    fetch('http://localhost:8080/todo')
    .then((response) => response.json() )
    .then((data)=> {
      setTodoList(data)
    })
    .catch((e) => console.log(e))
  }, [])

  // TodoItem 체크 박스 토글
  const onToggle = (todo) => {
    // console.log("체크박스 toggle!")
    const data ={
      no      : todo.no,
      name    : todo.name,
      status  : todo.status ? 0 : 1,
    }

    // 해당 번호만 데이터 변경
    const updateTodoList = todoList.map((item)=>{
      // status: !item.status 바뀐 status만 변경
      return item.no == todo.no ? {...item, status: !item.status} : item
    })

    setTodoList(updateTodoList)
  }

  return (
    <>
      <div className="container">
          <TodoHeader/>
          <TodoInput/>
          <TodoList todoList={todoList} onToggle={onToggle}/>
          <TodoFooter/>
      </div>
    </>
  )
}

export default TodoContainer