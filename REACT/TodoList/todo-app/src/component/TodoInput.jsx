import React from 'react'

const TodoInput = () => {
  return (
    <div>
      <form className='form'>
        <input type="text" placeholder='할 일 입력'
              name='name'
              className="input"
              // onChange={onChange}
              // value={input}
              />
        <button className='btn insert'
                // onClick={onSubmit}
                type='button'>추가</button>
      </form>
    </div>
  )
}

export default TodoInput