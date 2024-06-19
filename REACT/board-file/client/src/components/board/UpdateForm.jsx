import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import styles from '../board/css/update.module.css'
import { formatDate } from '../../apis/format'
import * as format from '../../apis/format'

const UpdateForm = ({ no, board, fileList, onUpdate
                    , onRemove, isLoading, onDownload
                    , onDeleteFile }) => {

  // state 등록
  // 랜더링 될때 넣기 -> Line 47
  const [title, setTitle] = useState('')
  const [writer, setWriter] = useState('')
  const [content, setContent] = useState('')

  // 함수
  // 데이터 세팅
  const handleChangeTitle = (e) =>{
    setTitle(e.target.value)
  }
  const handleChangeWriter = (e) =>{
    setWriter(e.target.value)
  }
  const handleChangeContent = (e) =>{
    setContent(e.target.value)
  }

  // 수정한 데이터 전송
  const onSubmit = () =>{
    // 유효성 검사
    if(title == null || title == '' ){
      alert('제목을 입력해주세요')
      return
    }

    if(writer == null || writer == '' ){
      alert('작성자를 입력해주세요')
      return
    }

    if(content == null || content == '' ){
      alert('내용을 입력해주세요')
      return
    }
    onUpdate(no, title, writer, content)
  }

  // 마운트 된 후 입력값 세팅
  useEffect(()=>{
    if(board){
      setTitle(board.title)
      setWriter(board.writer)
      setContent(board.content)
    }
  },[board])
  // 의존성 배열에 board 추가
  // [의존하는 객체]
  // : 지정한 객체가 변환했을 때, 다시 useEffect를 실행

  
  // 삭제 전송
  const onSubmitRemove =() =>{
    // window.confirm : DOM을 조작하는 경우 window를 붙여야함
    
    // DOM은 웹 페이지의 요소들을 구조화하여 표현
    // window 객체는 브라우저 창(Window)에 대한 정보와 제어를 제공
    // document 객체는 현재 로드된 문서(Document)에 대한 정보와 제어를 제공
    // JavaScript에서 window 객체를 통해 document 객체에 접근,
    // document 객체를 통해 DOM을 조작하는 방식으로 웹 페이지를 동적으로 제어
    let check = window.confirm('정말 삭제하시겠습니까?') 
    if(!check){
      return
    }
    else
      onRemove(no)  
  }

  // 파일 다운로드
  const handleDownload = (no, fileName) =>{
    // 중간 처리 가능
    onDownload(no, fileName)
  }

  // 파일 삭제
  const handleDeleteFile = (no) =>{
    const check = window.confirm("정말로 삭제하시겠습니까?")
    if(check)
      onDeleteFile(no)
  }

  return (
    <div className="contrainer">
      <h1 className="title">게시글 수정</h1>
      <hr />

      {
        // 로딩중인 경우
        isLoading && 
        <div>
          <img src="/img/loading.webp" alt="loading" width="100%" />
        </div>
      }
      {
        // 로딩이 끝난 경우
        !isLoading && board && (
          <table className={styles.table}>
            <tbody>
              <tr>
                <td>번호</td>
                <td>
                  <input type="text" className={styles['form-input-none']} value={no} readOnly/>
                </td>
              </tr>
              <tr>
                <td>등록 일자</td>
                <td>
                  <input type="text" className={styles['form-input-none']} value={formatDate(board.regDate)}/>
                </td>
              </tr>
              <tr>
                <td>제목</td>
                <td>
                  <input type="text" className={styles['form-input']} value={title} onChange={handleChangeTitle}/>
                </td>
              </tr>
              <tr>
                <td>작성자</td>
                <td>
                  <input type="text" className={styles['form-input']} value={writer} onChange={handleChangeWriter}/>
                </td>
              </tr>
              <tr>
                <td colSpan={2}>내용</td>
              </tr>
              <tr>
                <td colSpan={2}>
                  <textarea cols="50" className={styles['form-input']} rows="10" value={content} onChange={handleChangeContent}></textarea>
                </td>
              </tr>
              <tr>
                <td colSpan={2}>파일</td>
              </tr>
              <tr>
                <td>
                  {/* 파일 선택 */}
                  <input type="checkbox" />
                </td>
                <td>
                  { fileList.map( (file) => (
                      <div className='flex-box' key={file.no}>
                        <div className="item">
                          <img src={`/files/img/${file.no}`} alt={file.fileName} />
                          <span>{file.originName} ({format.byteToUnit(file.fileSize)})</span>
                        </div>

                        <div className="item">
                          <button className='btn' onClick={() => handleDownload(file.no, file.originName)}>다운로드</button>
                          <button className="btn" onClick={() => handleDeleteFile(file.no) }>삭제</button>
                        </div>
                      </div>
                  ))}
                </td>
              </tr>
            </tbody>
          </table>
        )
      }
      
      <hr />
      <div className="btn-box">
        <div className="item">
          <Link to="/boards" className="btn">목록</Link>
        </div>
        <div className={styles.item}>
          <button className="btn" onClick={onSubmit}>수정</button>
          <button className="btn" onClick={onSubmitRemove}>삭제</button>
        </div>
      </div>
    </div>
  )
}

export default UpdateForm