
import './App.css';
import { useState } from 'react';
import axios from 'axios';
import { api, host } from './config/config'
import useAuthStore from './store/store';
import { jwtDecode } from 'jwt-decode';


const Login = () => {
  const [user, setUser] = useState({ id: '', pw: '' });
  const { login } = useAuthStore();

  const handleLogin = () => {
    api.post(`/auth`, user).then(resp => {
      const token = resp.data;

      // token 값을 json 형식으로 추출
      const decoded = jwtDecode(token);
      console.log(decoded);

      sessionStorage.setItem("token", token);
      login(token);

    }).catch(resp => {
      alert("아이디 또는 패스워드 다시 확인")
    })
  }

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser(prev => ({ ...prev, [name]: value }))
  }

  return (

    <fieldset>
      <legend>Login</legend>
      <input type='text' name="id" placeholder='id' onChange={handleChange}></input><br></br>
      <input type='password' name="pw" placeholder='pw' onChange={handleChange}></input><br></br>
      <button onClick={handleLogin}>Login</button>
    </fieldset>

  );

}


const Home = () => {

  const { logout } = useAuthStore();

  // 로그아웃
  const handleLogout = () => {
    sessionStorage.removeItem("token");
    logout();
  }

  // 메세지 목록 (로그인된 사용자만 가능해야 함)
  const handleGetMessage = () => {
    const token = sessionStorage.getItem("token");
    api.get(`/messages`).then(resp => { // config에 api로 Bearer 설정 해뒀음
      console.log(resp);
    }).catch(resp => {
      console.log(resp);
    })
  }

  return (
    <fieldset>
      <legend>Home</legend>

      <button onClick={handleGetMessage}>메세지 목록</button>
      <button onClick={handleLogout}>로그아웃</button>
    </fieldset>
  )
}


function App() {
  const { isAuth } = useAuthStore();

  return (
    <div className="App">
      {isAuth ? <Home /> : <Login />}
    </div>
  );
}



export default App;
