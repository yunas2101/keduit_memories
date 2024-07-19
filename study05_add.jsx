import styles from "./Add.module.css";
import { useState } from "react";

const Add = ({ datas, setDatas }) => {
  const [data, setData] = useState({ id: 0, name: "", email: "", role: "" });

  const handleAdd = (e) => {
    let { name, value } = e.target;

    if (name == "id") {
      value = parseInt(value);
    }

    setData((prev) => {
      return { ...prev, [name]: value };
    });
  };

  const handleAddBtn = () => {
    setDatas((prev) => [...prev, data]);
    setData({ id: 0, name: "", email: "", role: "" });
  };

  return (
    <div className={styles.container}>
      <div>
        <input
          type="text"
          name="id"
          value={data.id || ""}
          onChange={handleAdd}
          placeholder="추가할 id"
        ></input>
        <input
          type="text"
          name="name"
          value={data.name}
          onChange={handleAdd}
          placeholder="추가할 name"
        ></input>
        <input
          type="text"
          name="email"
          value={data.email}
          onChange={handleAdd}
          placeholder="추가할 email"
        ></input>
        <input
          type="text"
          name="role"
          value={data.role}
          onChange={handleAdd}
          placeholder="추가할 role"
        ></input>
        <button onClick={handleAddBtn}>추가</button>
      </div>
    </div>
  );
};

export default Add;
