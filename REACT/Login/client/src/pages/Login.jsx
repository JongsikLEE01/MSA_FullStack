import React from 'react'
import Header from '../Components/Header'
import LoginForm from '../Components/Login/LoginForm'

const Login = () => {
  return (
    <>
      <Header></Header>
      <div className="container">
        <h1>Login</h1>
        <hr />
        <h2>로그인 페이지</h2>
        <LoginForm />
      </div>
    </>
  )
}

export default Login