import './App.css';
import { useState } from 'react';
import axios from 'axios';

function App() {
  const [data, setData] = useState({ seq: 15, title: 'didi', contents: 'pupu' })
  const [modifyData, setModifyData] = useState({ seq: 18, title: 'didi', contents: 'pupu' })


  const handlePost = () => {
    axios.post(`http://localhost/board`, data).then((resp) => {
      console.log(resp.data);
    })
  }

  const handleGetAll = () => {
    axios.get(`http://localhost/board`).then((resp) => {
      console.log(resp.data);
    })
  }

  const handleGetOne = () => {
    axios.get(`http://localhost/board/${data.seq}`).then((resp) => {
      console.log(resp.data);
    })
  }

  const handleDelete = () => {
    axios.delete(`http://localhost/board/${data.seq}`).then((resp) => {
      console.log(resp.data);
    })
  }

  const handlePut = () => {
    axios.put(`http://localhost/board`, modifyData).then((resp) => {
      console.log(resp.data);
    })
  }

  return (
    <div className="App">
      <input type="text" placeholder="title"></input>
      <input type="text" placeholder="contents"></input><br />
      <button onClick={handlePost}>Post</button>
      <button onClick={handleGetAll}>GetAll</button>
      <button onClick={handleGetOne}>GetOne</button>
      <button onClick={handleDelete}>delete</button>
      <button onClick={handlePut}>put</button>

    </div>
  );
}

export default App;
