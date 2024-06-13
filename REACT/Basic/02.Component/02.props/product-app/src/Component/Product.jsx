import React from 'react'

const Product = ({product}) => {
  return (
    <div className='card'>
      <img src={product.img} alt={product.name} />
      <span>{product.name}</span>
      <p>{product.price}</p>
    </div>
  )
}

export default Product