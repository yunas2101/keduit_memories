import logo from './logo.svg';
import './App.css';
import { useState } from 'react';
import useAuthStore from './store/store';
import { api } from './config/config'
import { jwtDecode } from 'jwt-decode';


const Login = () => {
  const [user, setUser] = useState({ id: '', pw: '' });
  const { login } = useAuthStore();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser(prev => ({ ...prev, [name]: value }))
  }

  const handleLogin = () => {
    api.post(`/auth`, user).then(resp => {
      console.log("결과 : ", resp.data)

      const token = resp.data;

      sessionStorage.setItem("token", token);
      login(token);

    })
      .catch(resp => {
        alert("아이디 또는 패스워드 다시 확인")
      })
  }

  return (
    <div className="App">
      <fieldset>
        <legend>Login</legend>
        <input type='text' name="id" placeholder='id' onChange={handleChange}></input><br></br>
        <input type='password' name="pw" placeholder='pw' onChange={handleChange}></input><br></br>
        <button onClick={handleLogin}>Login</button>
      </fieldset>

    </div>
  );

}

//===============================================
const Home = () => {

  const { logout } = useAuthStore();
  const [board, setBoard] = useState([]);

  const handleGetData = () => {

    const token = sessionStorage.getItem("token");

    api.get(`/admin`).then(resp => {
      console.log(resp.data)
      setBoard(resp.data);

    }).catch(resp => {

    })
  }

  // 삭제
  const [delSeq, setDelSeq] = useState(0);
  const handleDelSeq = (e) => {
    setDelSeq(e.target.value);
  }

  const handleDelSeqBtn = () => {

    console.log("delSeq : ", delSeq);

    api.delete(`/admin/${delSeq}`).then(resp => {

    }).catch(resp => {
      console.log(resp);
    })
  }


  // 로그아웃
  const handleLogout = () => {
    sessionStorage.removeItem("token");
    logout();
  }

  return (
    <fieldset>
      <legend>Home</legend>
      <button onClick={handleGetData}>데이터 불러오기</button><br></br>
      {
        board.map((b, i) => {

          return (
            <div key={i}>
              seq : {b.seq} / {b.title} / {b.contents}
            </div>
          )
        })
      }
      <input type='text' onChange={handleDelSeq} placeholder='삭제할 seq'></input>
      <button onClick={handleDelSeqBtn}>삭제</button><br></br>
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
