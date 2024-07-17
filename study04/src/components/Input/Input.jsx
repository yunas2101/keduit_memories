import { useState } from "react";
import styles from "./Input.module.css";

const Input = ({ setPage, setMenus }) => {

  const [menu, setMenu] = useState({ id: 0, name: "", price: 0 });
  const handleChange = (e) => {
    const { name, value } = e.target;
    setMenu((prev) => {
      return {
        ...prev, [name]: value
      }
    });
  };

  const handleAdd = () => {
    setMenus(prev => [...prev, menu]);
    setPage("/");
  };

  return (
    <div className={styles.input}>
      <div className={styles.header}>
        <span>Input Form</span>
      </div>
      <div className={styles.body}>
        <div>
          <input type="text" name="id" onChange={handleChange} placeholder="input id"></input>
        </div>
        <div>
          <input type="text" name="name" onChange={handleChange} placeholder="input name"></input>
        </div>
        <div>
          <input type="text" name="price" onChange={handleChange} placeholder="input price"></input>
        </div>
      </div>
      <div className={styles.btns}>
        <button onClick={handleAdd}>추가</button>
        <button onClick={() => { setPage("/"); }}>
          취소
        </button>
      </div>
    </div>
  );
};

export default Input;
