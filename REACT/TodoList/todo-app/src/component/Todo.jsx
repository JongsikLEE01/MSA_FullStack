import React from 'react'

const Todo = ({todo}) => {
  return (
    <>
        <div className="todo">
            <input type="checkbox" name={todo.states} id={todo.states} />
            <p>할 일 : {todo.name}</p>
            <button className='btn'>삭제</button>
        </div>
    </>
  )
}

export default Todo