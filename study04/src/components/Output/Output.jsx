import { useState } from "react";
import styles from "./Output.module.css";
import { useNavigate } from "react-router-dom";

const Output = ({ menus, setMenus, search, setSearch }) => {

  const navi = useNavigate();

  // 삭제버튼
  const handleDelBtn = (targetId) => {
    const result = menus.filter((menu) => menu.id != targetId);
    setSearch(result);
    setMenus(result);
  }

  // 수정
  const [update, setUpdate] = useState({ id: 0, name: "", price: 0 });
  const handleUpdate = (e) => {
    let { name, value } = e.target;

    if (name == "id" || name == "price") {
      value = parseInt(value)
    }

    setUpdate((prev) => {
      return { ...prev, [name]: value }
    });

  }
  // 수정버튼
  const handleUpdateBtn = () => {
    setMenus((prev) => {
      let newArr = prev.map((item) => {
        if (item.id === update.id) {
          return update
        } else {
          return item
        }
      })
      setSearch(newArr);
      return newArr;
    });
    setUpdate({ id: 0, name: "", price: 0 });
  }

  // 검색
  const handleSearch = (e) => {
    let keyword = e.target.value;
    const result = menus.filter((menu) => {
      return menu.name.includes(keyword);
    })

    setSearch(result);
  }

  return (
    <div className={styles.output}>
      <div className={styles.header}>
        <span>Output</span>
      </div>
      <div className={styles.body}>
        <table border={1}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Price</th>
              <th>del</th>
            </tr>
          </thead>
          <tbody>
            {
              search.map((menu, i) => {
                return (
                  <tr key={i}>
                    <td>{menu.id}</td>
                    <td>{menu.name}</td>
                    <td>{menu.price}</td>
                    <td><button onClick={() => { handleDelBtn(menu.id) }}>X</button></td>
                  </tr>
                )
              })
            }
            <tr>
              <td colSpan={3}>
                <input type="text" name="id" value={update.id || ''} onChange={handleUpdate} placeholder="수정할 대상의 id"></input>
                <input type="text" name="name" value={update.name} onChange={handleUpdate} placeholder="name 수정"></input>
                <input type="text" name="price" value={update.price || ''} onChange={handleUpdate} placeholder="price 수정"></input>
              </td>
              <td>
                <button onClick={handleUpdateBtn}>수정</button>
              </td>
            </tr>
            <tr>
              <td colSpan={4}>
                <input type="text" onChange={handleSearch} placeholder="검색할 name"></input>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div className={styles.footer}>
        <button onClick={() => { navi("/") }}>
          홈으로
        </button>
      </div>
    </div>
  );
};

export default Output;
