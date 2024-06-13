// rafce
import React from 'react'
import { useState } from 'react'

const ProductDetail = ({product}) => {
    // state 추가
    // const [상태, 상태설정함수] = useState(초기값)
    const [quantity, setQuantity] = useState(1)

    // 최종 가격 계산
    const totalPrice = product.price * quantity

    // 이벤트, 수량 증가
    const onIncrease = () =>{
        console.log(quantity)
        setQuantity(quantity+1)
    }

    // 이벤트, 수량 감소
    const onDecrease = () =>{
        console.log(quantity)
        if(quantity == 1) {
            alert("상품은 1개 이하로 설정할 수 없습니다")
            return setQuantity(1)
        }
        setQuantity(quantity-1)
    }

    return (
        <div className="product-detail">
            <div className="item img">
                <img src={product.img} alt="상품 이미지1" />
            </div>
  
           <div className="item info">
                <div className="title">
                    <h1>{product.name}</h1>
                </div>
                <p>
                    <span className="txt-pt">INFO</span><br/>
                    - 시원한 선풍기 360도 회전 가능<br/>
                    - 타이머 예약 기능
                </p>
                <p>
                    <span className="txt-pt">Color & Size</span><br/>
                    White, Black, Navy<br/>
                    S, M, L
                </p>
  
               <span className="line-lg"></span>
  
               <div className="text-group">
                    <div className="item">
                        <span className="txt-pt">판매가</span>
                    </div>
                    <div className="item">
                        {/* toLocaleString() : 1000단위 구분기호 지정 */}
                        <div className="txt-pt">{product.price.toLocaleString()} 원</div>
                    </div>
                </div>
  
               <span className="line-lg"></span>
  
               <div className="text-group">
                    <div className="item">
                        <span>Color</span>
                    </div>
                    <div className="item">
                        <select name="color" id="color">
                            <option value="white">white</option>
                            <option value="Black">Black</option>
                            <option value="Navy">Navy</option>
                        </select>
                    </div>
                </div>
                <div className="text-group">
                    <div className="item">
                        <span>수량</span>
                    </div>
                    <div className="item flex">
                        <input type="number" className='quantity' value={quantity} min={1} max={100}/>
                        <button className="btn btn-xs" onClick={onIncrease}>+</button>
                        <button className="btn btn-xs" onClick={onDecrease}>-</button>
                    </div>
                </div>
                <span className="line-lg"></span>
                <div className="text-group">
                    <div className="item">
                        <span className="txt-pt">최종 가격</span>
                    </div>
                    <div className="item">
                        <div className="txt-pt">{totalPrice}</div>
                    </div>
                </div>
                <div className="text-group flex gap-1">
                    <div className="item">
                        <button className="btn btn-lg">구매하기</button>
                    </div>
                    <div className="item flex">
                        <button className="btn btn-lg btn-outline">장바구니</button>
                        <button className="btn btn-lg btn-outline">관심상품</button>
                    </div>
                </div>
  
           </div>
        </div>
    )  
}
export default ProductDetail