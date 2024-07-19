import { useState } from "react";
import styles from "./Delete.module.css";

const Delete = ({ datas, setDatas }) => {
  const [delId, setDelId] = useState(0);
  const handleDel = (e) => {
    setDelId(parseInt(e.target.value));
  };

  const handleDelBtn = () => {
    const result = datas.filter((data) => data.id != delId);
    setDatas(result);
    setDelId(0);
  };

  return (
    <div className={styles.container}>
      <div>
        <input
          type="text"
          value={delId || ""}
          onChange={handleDel}
          placeholder="삭제할 id 입력"
        />
        <button onClick={handleDelBtn}>삭제</button>
      </div>
    </div>
  );
};

export default Delete;
