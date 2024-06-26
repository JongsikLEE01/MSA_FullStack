import React, { createContext, useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import Cookies from 'js-cookie'
import api from '../apis/api'
import * as auth from '../apis/auth'
import * as Swal from '../apis/alert'

// Context 생성
export const LoginContext = createContext()

const LoginContextProvider = ({ children }) => {
  const navigate = useNavigate()

  /* -----------------------[State]-------------------------- */
  const [isLogin, setLogin] = useState(false);                          // 로그인 여부
  const [userInfo, setUserInfo] = useState(null)                        // 유저 정보
  const [roles, setRoles] = useState({isUser : false, isAmdin : false}) // 권한 정보
  /* -------------------------------------------------------- */

  // 쿠키 -> jwt
  // 로그인 체크
  const loginCheck = async () => {
    // accessToken 쿠키 확인
    const accessToken = Cookies.get('accessToken')
    console.log(`accessToken? ${accessToken}`);

    // JWT Not In Cookie ❌
    if(!accessToken){
      console.log(`쿠키에 accessToken(jwt)가 없음...`);
      // 로그아웃 세팅
      logoutSetting()
      return
    }

    // JWT In Cookie ⭕
    console.log(`쿠키에 JWT(accessToken) 존재...`);

    // axios common header에 등록
    api.defaults.headers.common.Authorization = `Bearer ${accessToken}`

    // 사용자 정보 요청
    let response
    let data

    try {
      response = await auth.info()
    } catch (e) {
      console.log(`e? ${e}`);
      console.log(`status : ${response.status}`);
      return
    }

    data = response.data  // data = 사용자 정보
    console.log(`data? ${data}`);

    // 인증 실패
    if(data == 'UNAUTHORIZED' || response.status == 401){
      console.log(`accessToken(jwt)이 만료되거나 인증에 실패...`);
      return
    }

    // 인증 성공
    console.log(`accessToken(jwt) 토큰으로 사용자 정보 요청 성공...`);

    // 로그인 세팅
    loginSetting(data, accessToken) 
  }

  // 로그인
  const login = async (username, password) => {
    console.log(`login-username? ${username}`)
    console.log(`login-password? ${password}`)

    try {
      const response = await auth.login(username, password)
      const data = response.data
      const status = response.status
      const headers = response.headers
      const authorization = headers.authorization
      // accessToken : JWT
      const accessToken = authorization.replace("Bearer ", "")

      console.log(`data : ${data}`);
      console.log(`status : ${status}`);
      console.log(`headers : ${headers}`);
      console.log(`jwt : ${accessToken}`);

      // 로그인 성공 시
      if(status == 200){
        Cookies.set('accessToken', accessToken)

        // 로그인 체크
        loginCheck()

        // alert
        Swal.alert("로그인 성공", "메인 화면으로 이동합니다","success", (
          () => {navigate("/")}
        ))
      }
    } catch (e) {
      console.log(`로그인 실패...`);
      // alert
      Swal.alert("로그인 실패", "아이디 또는 비밀번호가 일치하지 않습니다","error")
    }
  }

  // 로그인 세팅
  // userData, accessToken(jwt)
  const loginSetting = (userData, accessToken) => {
    const {no, userId, authList } = userData            // users(DTO)의 JSON 형태
    const roleList = authList.map((auth) => auth.auth)  // [ROLE_USER, ROLE_ADMIN] 
    
    console.log(`no : ${no}`);
    console.log(`userId : ${userId}`);
    console.log(`authList : ${authList}`);
    console.log(`roleList : ${roleList}`);

    // axios common header - Authorizaion 헤더에 jwt 등록
    api.defaults.headers.common.Authorization = `Bearer ${accessToken}`

    // Context에 정보 등록
    // 로그인 여부 세팅
    setLogin(true)
    // 유저 정보 세팅
    const updateUserInfo = {no, userId, roleList}
    setUserInfo(updateUserInfo)
    // 권한 정보 세팅
    const updatedRoles = { isUser : false, isAmdin : false }
    roleList.forEach((role) => {
      if(role == 'ROLE_USER') updatedRoles.isUser = true
      if(role == 'ROLE_ADMIN') updatedRoles.isAdmin = true
    })
  }

  // 로그아웃 세팅
  const logoutSetting = () => {
    // axios 헤더 초기화
    api.defaults.headers.common.Authorization = undefined;

    // 쿠키 초기화
    Cookies.remove("accessToken")

    // 로그인 여부 : false
    setLogin(false)
    // 유저 정보 초기화
    setUserInfo(null)
    // 권한 정보 초기화
    setRoles(null)
  }

  // 로그아웃
  const logout = () => {
    // const check = window.confirm("정말 로그아웃 하시겠습니까?")
    // if (check) {
    //   // 로그아웃 세팅 호출
    //   logoutSetting()
    // }

    Swal.confirm("로그아웃하시겠습니까?", "로그아웃을 진행합니다", "warning",
      (result) => {
        // isConfirmed : 확인 버튼 클릭 여부
        if(result.isConfirmed){
          Swal.alert("로그아웃 성공","success")
          // 로그아웃 세팅 호출
          logoutSetting()
          navigate("/")
        }
      }
    )
  }

  // Mount / Update
  useEffect(()=>{
    // 로그인 체크
    loginCheck()
    // 1. 쿠키에서 JWT를 꺼내기
    // 2. JWT가 있으면 서버에 사용자 정보 받아옴
    // 3. 로그인 세팅 (Context의 로그인 여부, 사용자 정보, 권한 정보 등록)
  }, [])

  return (
    // 컨텍스트 값 지정 -> value={ ?, ? }
    <LoginContext.Provider value={{ isLogin, login,logout }}>
      { children }
    </LoginContext.Provider>
  )
}

export default LoginContextProvider