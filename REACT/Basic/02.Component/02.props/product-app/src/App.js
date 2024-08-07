import './App.css';
import ProductDetail from './Component/ProductDetail';
import ProductList from './Component/ProductList';

function App() {
  // ✔ 객체 가져오기
  const product = {
    productId : 'P0001',
    name : '360도 회전 선풍기',
    price : 5000,
    quantity : 1,
    img : 'https://i.imgur.com/1vpSkbW.png'
  }

  return (
    <div>
      {/* product 객체를 전달 */}
      {/* <ProductDetail product={product}/> */}

      <ProductList/>
    </div>
  );
}

export default App;
