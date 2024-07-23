import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Index } from './components/Index/Index';
import { Signup } from './components/Signup/Signup';
import { useAuthStore } from './store/store';
import { useEffect } from 'react';

function App() {

  // useEffect <-- 중요도 높음
  // 마운팅 되는 시점, 인마운팅 되는 시점, 그리고 개발자가 정하는 특정 시점에만 실행되는 코드를 작성


  const { setLoginID } = useAuthStore();

  useEffect(() => {
    // 마운팅 되는 시점에 실행하고 싶은 코드

    const loginID = sessionStorage.getItem("loginID"); // sessionStorage 에서 아이디를 꺼내서 zustand store로 이전
    setLoginID(loginID);

    return () => {
      // 언마운팅 되는 시점에 실행하고 싶은 코드  (필요할때만 실행)
    }
  }, []);






  return (
    <div className="container">
      <Router>
        <Routes>
          <Route path='/' element={<Index />} />
          <Route path='/member/signup' element={<Signup />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
