import './App.css';
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import Home from './components/board/Home';
import Read from './components/board/Read';
import Update from './components/board/Update';
import Insert from './components/board/Insert';
import List from './components/board/List';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>}></Route>
        <Route path='/board' element={<List/>}></Route>
        <Route path='/board/:no' element={<Read/>}></Route>
        <Route path='/board/insert' element={<Insert/>}></Route>
        <Route path='/board/update/:no' element={<Update/>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
