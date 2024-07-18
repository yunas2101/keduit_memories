import logo from './logo.svg';
import './App.css';
import Content from './components/Content/Content.jsx'

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';


function App() {


  return (
    <Router>
      <AppContent />
    </Router>
  );
}

const AppContent = () => {
  const navi = useNavigate();
  return (
    <div className="container">
      <div className='header'>
        header
      </div>
      <div className='body'>
        <div className='sidemenu'>
          <div className='menu' onClick={() => { navi("menu1") }}>회원관리</div>
          <div className='menu' onClick={() => { navi("menu2") }}>게시물관리</div>
          <div className='menu' onClick={() => { navi("menu3") }}>광고관리</div>
        </div>
        <div className='content'>
          <Routes>
            <Route path="/" element={<Content content="내용1" />} />
            <Route path="/menu1/*" element={<Content content="내용1" />} />
            <Route path="/menu2/*" element={<Content content="내용2" />} />
            <Route path="/menu3/*" element={<Content content="내용3" />} />
          </Routes>
        </div>
      </div>
    </div>
  )
}

export default App;
