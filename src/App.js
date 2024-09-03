import axios from 'axios';
import './App.css';
import { useState } from 'react';

function App() {

  const [data, setData] = useState([]);
  const [message, setMessage] = useState({ writer: "", message: "" });
  const [updateMessage, setUpdateMessage] = useState({ seq: 0, writer: "", message: "" });
  const [delSeq, setDelSeq] = useState(0);


  const handleGet = () => {
    axios.get(`http://192.168.1.15/messages`).then(resp => {
      console.log("get : ", resp.data);
      setData(resp.data);
    })
  }

  // 작성
  const handlePost = () => {
    axios.post(`http://192.168.1.15/messages`, message).then(resp => {
      console.log("post : ", resp.data);
    })
  }
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setMessage(prev => ({ ...prev, [name]: value }))
  }

  // 수정
  const handleIModifyChange = (e) => {
    const { name, value } = e.target;
    setUpdateMessage(prev => ({ ...prev, [name]: value }))
  }
  const handleUpdate = () => {
    axios.put(`http://192.168.1.15/messages/${updateMessage.seq}`, updateMessage).then(resp => {
      console.log(resp)
    })
  }

  // 삭제
  const handleDelete = () => {
    axios.delete(`http://192.168.1.15/messages/${delSeq}`).then(resp => {
      // handleGet();

      // setData((prev) => {
      //   prev.filter((data) => data.seq != delseq)
      // })
    })
  }




  return (
    <div className="App">
      <input type="text" name="writer" placeholder='writer' onChange={handleInputChange}></input>
      <input type="text" name="message" placeholder='message' onChange={handleInputChange}></input>
      <button onClick={handlePost}>Post</button>

      <hr></hr>
      <button onClick={handleGet}>Get</button>
      <fieldset>
        <legend>Messages</legend>
        {data.map((item, i) => {
          return (
            <div key={i} style={{ border: "1px solid black" }}>
              <p>{item.seq}.{item.writer}</p>
              <p>{item.message}</p>
              <p>{item.writeDate}</p>
            </div>

          )
        })}
      </fieldset>
      <hr></hr>

      <input type="text" name="seq" placeholder='seq' onChange={handleIModifyChange}></input>
      <input type="text" name="writer" placeholder='writer' onChange={handleIModifyChange}></input>
      <input type="text" name="message" placeholder='message' onChange={handleIModifyChange}></input>
      <button onClick={handleUpdate}>Update</button>
      <hr></hr>

      <input type="text" onChange={(e) => { setDelSeq(e.target.value) }}></input>
      <button onClick={handleDelete}>Delete</button>

    </div>
  );
}


export default App;
