import { useEffect, useState } from "react";
import Todo from "./Todo";

const TodoList = () => {
    // 초기값으로 빈배열 설정
    const [list, setList] = useState([])

    useEffect(()=>{
        async function fetchData(){
            try {
                const response = await fetch('http://localhost:8080/todo');
                const data = await response.json();

                setList(data)
                console.log(data)
            } catch (error) {
                console.log(error)
            }
        }
    
        fetchData()
    }, [])


  return (
    <>
        {list.map((todo, index) =>(
          <Todo todo={todo} key={todo.no}/>
        ))}
    </>
  )
}

export default TodoList