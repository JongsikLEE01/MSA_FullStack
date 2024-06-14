import React from 'react'
import { Link } from 'react-router-dom'

const Home = () => {
  // <Link to="/경로">경로 이름</Link>
  // : 실제로는 a 태그로 렌더링

  return (
    <div>
        <h1>Home</h1>
        <Link to="/About">moveToAbout</Link>
    </div>
  )
}

export default Home