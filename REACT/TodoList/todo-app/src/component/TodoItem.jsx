import React from 'react'

const Todo = ({todo, onToggle}) => {
  let {no, name, status} = todo
  status = status == 1 ? true : false

  return (
    <>
      <li className="todoItem" key={todo.no}>
        <div className="item">
          {/* 체크박스 */}
          <input type="checkbox" name="" id={todo.no} 
                checked={todo.status}
                onChange={() => onToggle(todo)}/>
          <label htmlFor={todo.no}></label>
          <span>{todo.name}</span>
        </div>
        <div className="item">
          <button className="btn">삭제</button>
        </div>
      </li>
    </>
  )
}

export default Todo