import './App.css';
import Content from './component/Content/Content.jsx'
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';

function App() {

  return (
    <Router>
      <Add />
    </Router>
  )
}

const Add = () => {
  const navi = useNavigate();
  return (
    <div className='container'>
      <div className='left'>List</div>
      <div className='right'>
        <div className='navi'>
          <div className='add' onClick={() => { navi("/add") }}>Add</div>
          <div className='delete' onClick={() => { navi("/delete") }}>Delete</div>
          <div className='modify' onClick={() => { navi("/modify") }}>Modify</div>
        </div>
        <div className='body'>
          <Routes>
            <Route path='/' element={<Content />} />
            <Route path='/add/*' element={<Content />} />
            <Route path='/delete/*' element={<Content />} />
            <Route path='/modify/*' element={<Content />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}

export default App;
