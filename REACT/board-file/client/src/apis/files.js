import axios from 'axios'

// 파일 업로드
export const upload = (formData, headers) => axios.post(`/files`, formData, headers)

// 파일 다운로드
// responseType: 'blob' : 파일 데이터를 응답받을 수 있는 타입
export const download = (no) => axios.get(`/files/${no}`, {responseType: 'blob'})

// 파일 삭제
export const remove = (no) => axios.delete(`/files/${no}`)