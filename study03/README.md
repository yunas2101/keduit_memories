# React

- 모든 UI는 상태에서 파생된다. <br></br>

## 메신저 기능

입력, 출력, 수정, 삭제, 검색

### 코드 축약 방식

```
    <input type='text' name="seq" onChange={handleChange} value={data.seq} placeholder='글번호'></input>
    <input type='text' name='writer' onChange={handleChange} value={data.writer} placeholder='작성자'></input>
    <input type='text' name='message' onChange={handleChange} value={data.message} placeholder='내용'></input>
    <button onClick={handleInput}>추가</button>
```

```
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
```

### 기존 내코드

```
function App() {
  const [datas, setDatas] = useState([
    { seq: 1, writer: "tom", message: "Hello React" },
    { seq: 2, writer: "sara", message: "React State Practice" },
    { seq: 3, writer: "jack", message: "Object Array" },
  ]);

  const [data, setData] = useState({ seq: 0, writer: "", message: "" });

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
    setDatas((prev) => [...prev, data]);

    setSearch((prev) => [...prev, data]);
    setData({ seq: 0, writer: "", message: "" });
  };

  /* 삭제 */
  const [delSeq, setDelSeq] = useState(0);
  const handleDelSeq = (e) => {
    setDelSeq(parseInt(e.target.value));
  };
  const handleDeleteBtn = (e) => {
    const del = datas.filter((data) => data.seq != delSeq);
    setDatas(del); // delSeq 가 삭제된 목록 세팅
    setDelSeq(0);
  };

  /* 수정 */
  const [update, setUpdate] = useState({ seq: 0, writer: "", message: "" });
  const handleUpdate = (e) => {
    let { name, value } = e.target;
    if (name === "seq") {
      value = parseInt(value, 10);
      if (isNaN(value)) value = 0;
    }
    setUpdate((prev) => ({ ...prev, [name]: value }));
  };
  const handleUpdateBtn = () => {
    setDatas((prev) =>
      prev.map((data) => {
        if (parseInt(data.seq) === parseInt(update.seq)) {
          return { ...data, writer: update.writer, message: update.message };
        }
        return data;
      })
    );
    setUpdate({ seq: 0, writer: "", message: "" });
  };

  /* 검색 */
  const [search, setSearch] = useState(datas);

  // 검색 기능 추가
  const handleSearch = (e) => {
    setSearch(datas);
    // setDatas를 통해 Datas 값에서 검색 된 결과만 출력될 수 있도록 변경
    // includes를 사용하여 검색어를 포함하고 있으면 출력
    setSearch((prev) =>
      prev.filter((data) => data.message.includes(e.target.value))
    );
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
          {search.map((message, index) => {
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
              <input
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
              />
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
```

## 강사풀이

```
function App() {
  const [datas, setDatas] = useState([
    { seq: 1, writer: "tom", message: "Hello React" },
    { seq: 2, writer: "sara", message: "React State Practice" },
    { seq: 3, writer: "jack", message: "Object Array" },
  ]);

  const [data, setData] = useState({ seq: 0, writer: "", message: "" });

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
              <input
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
              />
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

```
