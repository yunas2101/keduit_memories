import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./Input.module.css";

const Input = ({ setMenus, setSearch }) => {

  const navi = useNavigate();


  const [menu, setMenu] = useState({ id: 0, name: "", price: 0 });
  const handleChange = (e) => {
    let { name, value } = e.target;

    if (name == "id" || name == "price") {
      value = parseInt(value)
    }

    setMenu((prev) => {
      return ({ ...prev, [name]: value })
    });
  };

  const handleAdd = () => {
    setMenus((prev) => {
      setSearch([...prev, menu])
      return [...prev, menu]
    });
    navi("/");
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
        <button onClick={() => { navi("/") }}>
          취소
        </button>
      </div>
    </div>
  );
};

export default Input;
