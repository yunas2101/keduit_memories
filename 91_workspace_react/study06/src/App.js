import logo from './logo.svg';
import './App.css';
import { useState } from 'react';
import { useArrayStore, useContactStore, useStore } from './store/store';

const Com2 = () => {

  const { str, setStr } = useStore(); //js 에 있는 객체를 리턴한 걸 여기로 받음 
  const { arr, add } = useArrayStore();
  const { contact, addContact, delContact } = useContactStore();

  return (
    <div>
      {str}
      <hr />
      <button onClick={() => { setStr("Hello zustand") }}>str 내용 변경</button>
      <hr></hr>

      <ul>
        {
          arr.map((item, index) => {
            return (
              <li key={index}>{item}</li>
            )
          })
        }
      </ul>
      <hr></hr>
      <button onClick={() => { add("Grape") }}>배열 내용 추가</button>

      <hr></hr>
      <ul>
        {
          contact.map((item, i) => {
            return (
              <li key={i}>{item.id}:{item.name}:{item.contact}</li>
            )
          })
        }
      </ul>
      <button onClick={() => { delContact(1001) }}>삭제</button>
    </div>

  )
}

const Com1 = () => {
  return (
    <div>
      <Com2 />
    </div>
  )
}


function App() {


  return (
    <div className="container">
      <Com1 />
    </div>
  );
}

export default App;
