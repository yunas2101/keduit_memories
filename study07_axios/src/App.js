import './App.css';
import { useState } from 'react';
import axios from 'axios';

function App() {

  const [musics, setMusics] = useState([]);
  const [music, setMusic] = useState({ id: 0, title: '', singer: '', publish: '' });

  // 추가
  const handleAddChange = (e) => {
    // let {name, value} = e.target;
    const { name, value } = e.target;
    // if (name == "id") value = parseInt(value);

    setMusic((prev) => {
      return (
        { ...prev, [name]: value }
      )
    })
  }

  const handleAdd = () => {
    // C - post
    // R - get
    // U - put
    // D - delete

    axios.post(`http://192.168.1.15/music`, music);
  }

  // 출력 
  const handleGetAll = () => {
    axios.get(`http://192.168.1.15/music`).then((resp) => { // 딱히 전달할 값 없기때문에 두번째 인자값 없음. .then() : 받을 값
      console.log(resp);
      setMusics((prev) => {
        return [...prev, music]
      })


    });



  }


  return (
    <div className="App">

      <input type="text" onChange={handleAddChange} placeholder="ID" name="id" value={music.id || ''}></input><br></br>
      <input type="text" onChange={handleAddChange} placeholder="Title" name="title" value={music.title}></input><br></br>
      <input type="text" onChange={handleAddChange} placeholder="Singer" name="singer" value={music.singer}></input><br></br>
      <input type="date" onChange={handleAddChange} placeholder="Publish" name="publish" value={music.publish}></input><br></br>
      <button onClick={handleAdd}>Add New</button>
      <hr></hr>
      <div style={{ display: "flex", justifyContent: "center" }}>
        <table style={{ border: "1px solid black" }}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Singer</th>
            </tr>
          </thead>
          <tbody>
            {
              musics.map((item, i) => {
                return (
                  <tr key={i}>
                    <td>{item.id}</td>
                    <td>{item.title}</td>
                    <td>{item.singer}</td>
                  </tr>
                )
              })
            }
          </tbody>

        </table>
      </div>
      <button onClick={handleGetAll}>get All Music</button>

    </div>
  );
}

export default App;
