import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Index } from './components/Index/Index';
import { Signup } from './components/Signup/Signup';
import { useAuthStore } from './store/store';
import { useEffect } from 'react';
import { Loading } from './commons/Loading/Loading';
import { useState } from 'react';
import { Mypage } from './components/Mypage/Mypage';
import axios from 'axios';

axios.defaults.withCredentials = true;
// axios가 요청을 보낼 때 쿠키값을 포함해서 전송하는 설정

function App() {

  // useEffect <-- 중요도 높음
  // 마운팅 되는 시점, 인마운팅 되는 시점, 그리고 개발자가 정하는 특정 시점에만 실행되는 코드를 작성
  const { loginID, setLoginID } = useAuthStore();
  const [isLoading, setIsLoading] = useState(true);
  const [myInfo, setMyInfo] = useState({ id: '', pw: '', name: '' }); // 마이페이지 

  useEffect(() => {
    // 마운팅 되는 시점에 실행하고 싶은 코드
    const loginID = sessionStorage.getItem("loginID"); // sessionStorage 에서 아이디를 꺼내서 zustand store로 이전
    setLoginID(loginID);
  }, []);

  useEffect(() => {
    setIsLoading(false);
  }, [loginID])


  if (isLoading) {
    return <Loading />
  } else {
    return (
      <div className="container">
        <Router>
          <Routes>
            <Route path='/' element={<Index setMyInfo={setMyInfo} />} />
            <Route path='/member/signup' element={<Signup />} />
            <Route path='/member/mypage' element={<Mypage myInfo={myInfo} />} />
          </Routes>
        </Router>
      </div>
    );
  }


}

export default App;
