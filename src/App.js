
import './App.css';
import { useState } from 'react';
import { host } from './config/config'
import axios from 'axios'

function App() {

  const [file, setFile] = useState(null);
  const [files, setFiles] = useState([{ seq: 0, oriname: '', sysname: '' }]);

  const handleUpload = () => {
    const formData = new FormData();
    formData.append('file', file);
    axios.post(`${host}/file`, formData);
  }

  // 파일 선택
  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  }

  // 파일 목록 출력
  const handleList = () => {
    axios.get(`${host}/file`).then(resp => {
      setFiles(resp.data);
    })
  }

  const handleFileDelete = (seq) => {
    axios.delete(`${host}/file/${seq}`).then(resp => {

    })
    setFiles((prev) => prev.filter((file) => file.seq !== seq));

  }


  return (
    <div className="App">
      <fieldset>
        <legend>File Upload</legend>
        <input type="file" onChange={handleFileChange}></input>
        <button onClick={handleUpload}>전송</button>
      </fieldset>

      <fieldset>
        <legend>File List</legend>
        <button onClick={handleList}>파일리스트</button>
        {
          files.map((f, i) => {
            return (
              <div key={i}>
                {f.seq}. <a href={`${host}/file/${f.sysname}/${f.oriname}`} download={f.oriname} >{f.oriname}</a>
                <button onClick={() => handleFileDelete(f.seq)}>X</button>
              </div>
            );
          })
        }
      </fieldset>
    </div>
  );
}

export default App;
