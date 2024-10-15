import logo from './logo.svg';
import './App.css';
import { useState } from 'react';



function App() {
  const [datas, setDatas] = useState([
    {
      id: 1,
      title: "Inception",
      genre: "Science Fiction",
      publish: "2010-07-16"
    },
    {
      id: 2,
      title: "The Dark Knight",
      genre: "Action",
      publish: "2008-07-18"
    },
    {
      id: 3,
      title: "Parasite",
      genre: "Thriller",
      publish: "2019-05-30"
    },
    {
      id: 4,
      title: "La La Land",
      genre: "Musical",
      publish: "2016-12-09"
    },
    {
      id: 5,
      title: "Avatar",
      genre: "Science Fiction",
      publish: "2009-12-18"
    }
  ]);

  const [data, setData] = useState({ id: 0, title: '', genre: '', publish: '' });

  // 추가
  const handleAdd = (e) => {
    let { name, value } = e.target;
    setData(prev => ({ ...prev, [name]: value }));
  }
  // 추가버튼 
  const handleAddBtn = () => {
    setDatas((prev) => {
      const updated = [...prev, data];
      setFiltered(updated);
      return updated;
    });
    setData({ id: 0, title: '', genre: '', publish: '' });
  }


  // 삭제
  const [delId, setDelId] = useState(0);
  const handleDelId = (e) => {
    setDelId(parseInt(e.target.value));
  }
  // 삭제버튼
  const handleDelBtn = () => {
    setDatas(() => {
      const result = datas.filter((data) => data.id != delId);
      setFiltered(result);
      return result;
    });

    setDelId(0);
  }

  // 수정
  const [update, setUpdate] = useState({ id: 0, title: '', genre: '', publish: '' });
  const handleUpdate = (e) => {
    let { name, value } = e.target;
    if (name === "seq") { // seq => int형으로 변경
      value = parseInt(value, 10);
      if (isNaN(value)) value = 0;
    }
    setUpdate(prev => ({ ...prev, [name]: value })); // update에 값 setting
  }
  // 수정버튼
  const [prev, setPrev] = useState(datas);
  const handleUpdateBtn = () => {
    const updated = prev.map((data) => {
      if (parseInt(data.id) === parseInt(update.id)) {
        return { ...data, title: update.title, genre: update.genre, publish: update.publish };
      }
      return data;
    })

    setDatas(() => {
      setFiltered(updated);
      return updated;
    });
    setUpdate({ id: 0, title: '', genre: '', publish: '' });
  }


  // 검색
  const [filtered, setFiltered] = useState(datas);

  const handleSearch = (e) => {
    const result = datas.filter((data) => data.title.includes(e.target.value));
    setFiltered(result);
  }


  return (
    <div className="container">
      <table className="movies">
        <tbody>
          <tr>
            <th colSpan={4}>Movies</th>
          </tr>
          <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Genre</th>
            <th>publish</th>
          </tr>
          {filtered.map((movie, index) => {
            return (
              <tr key={index}>
                <td>{movie.id}</td>
                <td>{movie.title}</td>
                <td>{movie.genre}</td>
                <td>{movie.publish}</td>
              </tr>
            )
          })
          }
          <tr>
            <td colSpan={3}>
              <input type='text' name='id' onChange={handleAdd} value={data.id || ''} placeholder='id'></input>
              <input type='text' name='title' onChange={handleAdd} value={data.title} placeholder='title'></input>
              <input type='text' name='genre' onChange={handleAdd} value={data.genre} placeholder='genre'></input>
              <input type='date' name='publish' onChange={handleAdd} value={data.publish}></input>
            </td>
            <td>
              <button onClick={handleAddBtn}>추가</button>
            </td>
          </tr>
          <tr>
            <td colSpan={3}>
              <input type='text' onChange={handleDelId} value={delId || ''} placeholder='삭제할 id'></input>
            </td>
            <td>
              <button onClick={handleDelBtn}>삭제</button>
            </td>
          </tr>
          <tr>
            <td colSpan={3}>
              <input type='text' name='id' onChange={handleUpdate} value={update.id || ''} placeholder='수정할 대상의 id'></input>
              <input type='text' name='title' onChange={handleUpdate} value={update.title} placeholder='title 수정'></input>
              <input type='text' name='genre' onChange={handleUpdate} value={update.genre} placeholder='genre 수정'></input>
              <input type='date' name='publish' onChange={handleUpdate} value={update.publish} ></input>
            </td>
            <td>
              <button onClick={handleUpdateBtn}>수정</button>
            </td>
          </tr>
          <tr>
            <td colSpan={4}>
              <input type='text' onChange={handleSearch} placeholder='title 검색'></input>
            </td>
          </tr>

        </tbody>
      </table>
    </div>
  );
}

export default App;
