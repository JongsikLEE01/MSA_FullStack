import React from 'react'

const ProductList = async () => {

    const response = await fetch('http://localhost:8080/product')
    const data = await response.json()
    console.log(data);

  return (
    <div>ProductList</div>
  )
}

export default ProductList