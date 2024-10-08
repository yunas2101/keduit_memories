import './App.css';
import { useState } from 'react';
import { useEffect } from 'react';
import { host } from './config/config.js'
import axios from 'axios';


axios.defaults.withCredentials = true;

function App() {

  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState('');
  const [sock, setSock] = useState(null); // 일반변수로 만들면 서버 돌때마다 초기화 돼서 스테이트 변수로 만듦


  useEffect(() => {
    const ws = new WebSocket(`ws://${host}/chat`);

    ws.onmessage = (e) => {
      setMessages(prev => [...prev, JSON.parse(e.data)]);
    }
    setSock(ws);
  }, []);

  const handleChange = (e) => {
    setMessage(e.target.value);
  }

  const handleSend = () => {
    sock.send(message);
    setMessage("");
  }

  const handleLogin = () => {
    axios.post(`http://${host}/auth`, { id: 'aaa', pw: 'aaa' });
  }


  return (
    <div className="App">
      <h1>WebSocket 메세지 연습</h1>
      <input type='text' placeholder='input message' name='message' value={message} onChange={handleChange}></input>
      <button onClick={handleSend}>전송</button>

      <div className='chatbox'>
        {
          messages.map((message, i) => {
            return (
              <div key={i}>
                {message.id} : {message.pw}
              </div>
            )
          })
        }
      </div>
      <div>
        <button onClick={handleLogin}>로그인</button>
      </div>
    </div>
  );
}

export default App;
