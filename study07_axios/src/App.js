import './App.css';
import { useState } from 'react';
import axios from 'axios';

function App() {

  const [musics, setMusics] = useState([]);
  const [music, setMusic] = useState({ id: 0, title: '', singer: '', publish: '' });
  const [modifyMusic, setModifyMusic] = useState({ id: 0, title: '', singer: '', publish: '' });

  // 추가
  const handleAddChange = (e) => {
    const { name, value } = e.target;
    // if (name == "id") value = parseInt(value);

    setMusic((prev) => {
      return (
        { ...prev, [name]: value }
      )
    })
  }
  // 추가 버튼
  const handleAdd = () => {
    // C - post
    // R - get
    // U - put
    // D - delete
    axios.post(`http://192.168.1.15/music`, music).then((resp) => {
      setMusic({ id: 0, title: '', singer: '', publish: '' });
      handleGetAll();
    });

  }

  // 출력 
  const handleGetAll = () => {
    axios.get(`http://192.168.1.15/music`).then((resp) => { // 딱히 전달할 값 없기때문에 두번째 인자값 없음. .then() : 받을 값
      setMusics(resp.data);
    });
    // 응답이 나가고 그리고 나서 then으로 resp값을 받아올거야. 
  }

  // 수정
  const handleModifyChange = (e) => {
    const { name, value } = e.target;
    // if (name == "id") value = parseInt(value);
    setModifyMusic((prev) => {
      return (
        { ...prev, [name]: value }
      )
    })
  }
  // 수정버튼 
  const handleModify = () => {
    axios.put(`http://192.168.1.15/music/${modifyMusic.id}`, modifyMusic).then(resp => {
      handleGetAll();
      setModifyMusic({ id: 0, title: '', singer: '', publish: '' });
    })
  }

  // 삭제
  const [delID, setDelID] = useState(0);
  const handleDelIDChange = (e) => {
    setDelID(e.target.value);
  }
  const handleDelBtn = () => {
    axios.delete(`http://192.168.1.15/music/${delID}`).then(resp => {
      handleGetAll();
      setDelID(0);
    })
  }

  // 검색 
  const [searchTitle, setSearchTitle] = useState('');
  const handleTitleChange = (e) => {
    setSearchTitle(e.target.value)
  }
  const handleSearch = () => {

    // if (searchTitle == null) {
    //   alert("검색할 제목을 입력하세요.")
    //   return;
    // }

    axios.get(`http://192.168.1.15/music`, { params: { title: searchTitle } }).then(resp => {
      setMusics(resp.data);
    })
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
      <hr></hr>
      <div>
        <input type="text" onChange={handleModifyChange} placeholder="ID" name="id" value={modifyMusic.id || ''}></input><br></br>
        <input type="text" onChange={handleModifyChange} placeholder="Title" name="title" value={modifyMusic.title}></input><br></br>
        <input type="text" onChange={handleModifyChange} placeholder="Singer" name="singer" value={modifyMusic.singer}></input><br></br>
        <input type="date" onChange={handleModifyChange} placeholder="Publish" name="publish" value={modifyMusic.publish}></input><br></br>
        <button onClick={handleModify}>수정</button>
      </div>
      <hr></hr>
      <input type='text' placeholder='ID' name='id' onChange={handleDelIDChange} value={delID || ''}></input>
      <button onClick={handleDelBtn}>삭제</button>
      <hr></hr>
      <div>
        <input type='text' placeholder='Title' name='title' onChange={handleTitleChange} value={searchTitle}></input>
        <button onClick={handleSearch}>검색</button>
      </div>
    </div>
  );
}

export default App;
