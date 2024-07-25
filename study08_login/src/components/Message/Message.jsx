import styles from "./Message.module.css";
import { useState, useEffect } from 'react';

export const Message = () => {


    // onst[messages, setMessages] = useState([]);
    const [message, setMessage] = useState('');
    const [sock, setSock] = useState(null); // 일반변수로 만들면 서버 돌때마다 초기화 돼서 스테이트 변수로 만듦


    // useEffect(() => {
    //     const ws = new WebSocket(`ws://${host}/message`);

    //     ws.onmessage = (e) => {
    //         // setMessages(prev => [...prev, JSON.parse(e.data)]);
    //     }
    //     setSock(ws);
    // }, []);

    const handleChange = (e) => {
        setMessage(e.target.value);
    }

    const handleSend = () => {
        sock.send(message);
        setMessage("");
    }





    return (
        <div className={styles.container}>
            <h1>채팅</h1>
            <input type='text' placeholder='메세지 입력' name='message' value={message} onChange={handleChange}></input>
            <button onClick={handleSend}>전송</button>

            <div className='chatbox'>
                {/* {
                    messages.map((message, i) => {
                        return (
                            <div key={i}>
                                {message.id} : {message.pw}
                            </div>
                        )
                    })
                } */}
            </div>
        </div>


    )
}