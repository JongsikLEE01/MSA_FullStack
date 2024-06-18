export const formatDate = (dateString) =>{
    const date = new Date(dateString)

    // 자바스크립트 날짜 포맷
    // 1. 포맷 option으로 적용
    // toLocaleString : 년 월 일, 시,분, 초를 객체로 넣어주면 사용할 수 있음
    // const options = { year      : 'numeric'
    //                 , month     : 'numeric'
    //                 , day       : 'numeric'
    //                 , hour      : 'numeric'
    //                 , minute    : 'numeric'
    //                 , second    : 'numeric'
    //                 , hour12    : false
    //                 , timeZone  : 'Asia/Seoul' 
    //                 }
    // return date.toLocaleString('ko-Ko', options)

    // 2. 포맷 형식으로 적용
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${year}/${month}/${day} ${hours}:${minutes}:${seconds}`
}