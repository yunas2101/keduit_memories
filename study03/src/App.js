import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

// function App() {

// 모든 UI는 상태에서 파생된다 

//   let count = 1;

//   function plus() {
//     count++;
//     document.getElementById("counter").innerHTML = count;
//   }

//   function minus() {
//     count--;
//     document.getElementById("counter").innerHTML = count;
//   }


//   return (
//     <div className="App">
//       <div id="counter">{count}</div>
//       <hr></hr>
//       <button id="plus" onClick={plus}>+</button>
//       <button id="minus" onClick={minus}>-</button>
//     </div>
//   );
// }

//========================[Setter를 통해 값 변함주기]===================================

// function App() {

//   // mount - 최초에 Component가 처음으로 그려지는 경우
//   // unmount - Component가 특정 명령에 의해 브라우저에서 아예 제거되는 상황
//   // 새로고침 - unmount 후 mount 기능과 같음

//   let count2 = 10;
//   // Destructuring 문법 - 구조 분해 할당 
//   const [count, setCount] = useState(1);
//   // 상태변수 생성 - 한 번 선언되면 rerendering에 의해서 다시 선언되지 않음
//   // 상태값이 변경되는 경우 상태 변수르르 포함하는 함수가 재호출되는데 이를 rerendering 이라고 함.

//   const handlePlus = () => {
//     setCount(count + 1);
//     count2++;
//   }
//   const handleMinus = () => {
//     setCount(count - 1);
//     count2--;
//   }

//   return (
//     <div className="App">
//       <div>{count}, {count2}</div>
//       <hr></hr>
//       <button onClick={handlePlus}>+</button>
//       <button onClick={handleMinus}>-</button>

//     </div>
//   );

// }

// =====================[String 적용]===========================

// function App() {

//   const [str, setStr] = useState('기본 메세지');

//   const handleChange = (e) => {
//     setStr(e.target.value);
//   }

//   const handlePopup = () => {

//   }
//   return (
//     <div>
//       <h1>{str}</h1>
//       <hr></hr>
//       <div>
//         <input type='text' onChange={handleChange}></input>
//         <button onClick={() => { alert(str) }}>Popup!</button>
//       </div>
//     </div>
//   );
// }

//====================================================================
// function App() {

//   const [messages, setMessages] = useState(["Hello", "React", "State"]); // 문자열 목록 상태 저장
//   const [message, setMessage] = useState('');                              // 하나의 입력 상태 값

//   const handleChange = (e) => {
//     setMessage(e.target.value);
//   }

//   const handleSave = () => {
//     // spread 연산자 ...배열, ...객체
//     setMessages(prev => [...prev, message]);
//     setMessage('');
//   }


//   return (
//     <div>
//       <ul>
//         {/* <li>{messages[0]}</li>
//         <li>{messages[1]}</li>
//         <li>{messages[2]}</li> */}
//         {
//           messages.map((message, index) => {
//             return (<li key={index}>{message}</li>)
//           })
//         }
//       </ul>
//       <hr></hr>
//       <input type='text' onChange={handleChange} value={message}></input>
//       <button onClick={handleSave}>Save</button>
//     </div>
//   );
// }


function App() {
  const [data, setData] = useState([
    { seq: 1, writer: "tom", message: "Hello React" },
    { seq: 2, writer: "sara", message: "React State Practice" },
    { seq: 3, writer: "jack", message: "Object Array" }
  ]);

  const [seq, setSeq] = useState(4);
  const [writer, setWriter] = useState('');
  const [message, setMessage] = useState('');

  const handleWriter = (e) => {
    setWriter(e.target.value);
  }
  const handleMessage = (e) => {
    setMessage(e.target.value);
  }


  const handleInput = () => {
    const obj = { seq, writer, message };

    setData(prev => [...prev, obj]);

    setSeq(seq + 1);
    setWriter("");
    setMessage("");
  }

  return (
    <div className='container'>
      <table className='messages'>
        <tr>
          <th>글번호</th>
          <th>작성자</th>
          <th>메세지</th>
        </tr>

        {
          data.map((i, index) => {
            return (
              <tr key={index}>
                <td>{i.seq}</td>
                <td>{i.writer}</td>
                <td>{i.message}</td>
              </tr>
            )
          })
        }
        <tr>
          <td colSpan={3}>
            <input type='text' value={seq} placeholder='글번호'></input>
            <input type='text' onChange={handleWriter} value={writer} placeholder='작성자'></input>
            <input type='text' onChange={handleMessage} value={message} placeholder='내용'></input>
            <button onClick={handleInput}>추가</button>
          </td>
        </tr>
      </table>
    </div>
  );
}




export default App;
