import React from 'react'

const TodoInput = () => {
  return (
    <div>
        <input type="text" name='name' placeholder='할 일 입력' className="work"/>
        <button className='btn insert'>추가</button>
    </div>
  )
}

export default TodoInput