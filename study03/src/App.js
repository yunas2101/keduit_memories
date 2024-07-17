import logo from "./logo.svg";
import "./App.css";
import { useState, useEffect } from "react";

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
  const [datas, setDatas] = useState([
    { seq: 1, writer: "tom", message: "Hello React" },
    { seq: 2, writer: "sara", message: "React State Practice" },
    { seq: 3, writer: "jack", message: "Object Array" },
  ]);

  const [data, setData] = useState({ seq: 0, writer: "", message: "" });

  // * setter 함수의 비동기 처리에 대응하는 방법 2가지
  // 1. useEffect -> 안배운 방법
  // 2. 함수베이스 setter에서 return 전에 갱신 로직을 동작시킨다.

  /* 추가 */
  const handleChange = (e) => {
    let { name, value } = e.target;
    if (name === "seq") {
      value = parseInt(value, 10);
      if (isNaN(value)) {
        value = 0;
      }
    }
    setData((prev) => ({ ...prev, [name]: value }));
  };
  const handleInputBtn = () => {
    setDatas((prev) => {
      const updated = [...prev, data];
      setFiltered(updated);
      return updated;
    });
    // state에 대한 setter는 비동기로 실행되므로, 즉각적인 피드백을 받지 못하는 경우가 생길 수 있다.
    setData({ seq: 0, writer: "", message: "" });
  };

  /* 삭제 */
  const [delSeq, setDelSeq] = useState(0);
  const handleDelSeq = (e) => {
    setDelSeq(parseInt(e.target.value));
  };
  const handleDeleteBtn = (e) => {
    setDatas(() => {
      const result = datas.filter((data) => data.seq != delSeq);
      setFiltered(result);
      return result;
    });
    setDelSeq(0);
  };

  /* 수정 */
  // const [update, setUpdate] = useState({ seq: 0, writer: "", message: "" });
  // const handleUpdate = (e) => {
  //   let { name, value } = e.target;
  //   if (name === "seq") {
  //     value = parseInt(value, 10);
  //     if (isNaN(value)) value = 0;
  //   }
  //   setUpdate((prev) => ({ ...prev, [name]: value }));
  // };
  // const handleUpdateBtn = () => {
  //   setDatas((prev) =>
  //     prev.map((data) => {
  //       if (data.seq === update.seq) {
  //         return { ...data, writer: update.writer, message: update.message };
  //       }
  //       return data;
  //     })
  //   );
  //   setUpdate({ seq: 0, writer: "", message: "" });
  // };
  const [update, setUpdate] = useState({ seq: 0, writer: "", message: "" });
  const handleUpdate = (e) => {
    let { name, value } = e.target;
    setUpdate((prev) => ({ ...prev, [name]: value }));
  };
  const handleUpdateBtn = () => {
    const updated = datas.map((data) => {
      if (parseInt(data.seq) === parseInt(update.seq)) {
        return update;
      }
      return data;
    });
    setDatas(() => {
      setFiltered(updated);
      return updated;
    });
    setUpdate({ seq: 0, writer: "", message: "" });
  };

  /* 검색 */
  // const [search, setSearch] = useState(datas);

  // // 검색 기능 추가
  // const handleSearch = (e) => {
  //   setSearch(datas);
  //   // setDatas를 통해 Datas 값에서 검색 된 결과만 출력될 수 있도록 변경
  //   // includes를 사용하여 검색어를 포함하고 있으면 출력
  //   console.log(e.target.value);
  //   setSearch((prev) =>
  //     prev.filter((data) => data.message.includes(e.target.value))
  //   );
  // };

  const [filtered, setFiltered] = useState(datas);

  const handleSearch = (e) => {
    const keyword = e.target.value;
    const result = datas.filter((data) => data.message.includes(keyword));
    setFiltered(result);
  };

  return (
    <div className="container">
      <table className="messages">
        <tbody>
          <tr>
            <th>글번호</th>
            <th>작성자</th>
            <th>메세지</th>
          </tr>
          {filtered.map((message, index) => {
            return (
              <tr key={index}>
                <td>{message.seq}</td>
                <td>{message.writer}</td>
                <td>{message.message}</td>
              </tr>
            );
          })}
          <tr>
            <td colSpan={3}>
              {["seq", "writer", "message"].map((item) => {
                return (
                  <input
                    type="text"
                    placeholder={item}
                    name={item}
                    onChange={handleChange}
                    value={data[item] || ""}
                  />
                );
              })}
              <button onClick={handleInputBtn}>추가</button>

              {/* <input type='text' name="seq" onChange={handleChange} value={data.seq} placeholder='글번호'></input>
            <input type='text' name='writer' onChange={handleChange} value={data.writer} placeholder='작성자'></input>
            <input type='text' name='message' onChange={handleChange} value={data.message} placeholder='내용'></input>
            <button onClick={handleInput}>추가</button> */}
            </td>
          </tr>
          <tr>
            <td colSpan={3}>
              <input
                type="text"
                placeholder="input seq to delete"
                onChange={handleDelSeq}
                value={delSeq || ""}
              />
              <button onClick={handleDeleteBtn}>삭제</button>
            </td>
          </tr>
          <tr>
            <td colSpan={3}>
              {/* <input
                type="text"
                name="seq"
                onChange={handleUpdate}
                value={update.seq || ""}
                placeholder="수정 대상의 seq"
              />
              <input
                type="text"
                name="writer"
                onChange={handleUpdate}
                value={update.writer}
                placeholder="수정할 writer"
              />
              <input
                type="text"
                name="message"
                onChange={handleUpdate}
                value={update.message}
                placeholder="수정할 message"
              /> */}
              {["seq", "writer", "message"].map((item, i) => {
                return (
                  <input
                    key={i}
                    type="text"
                    placeholder={item}
                    name={item}
                    onChange={handleUpdate}
                    value={update[item] || ""}
                  ></input>
                );
              })}

              <button onClick={handleUpdateBtn}>수정</button>
            </td>
          </tr>
          <tr>
            <td colSpan={3}>
              <input
                type="text"
                name="message"
                placeholder="내용검색"
                onChange={handleSearch}
              />
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default App;
