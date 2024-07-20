import { useState } from "react";
import styles from "./Delete.module.css";
import { useCafeStore, useMemberStore } from "../../../../store/store";
import { useNavigate } from "react-router-dom";

export const Delete = ({ type }) => {
  const [delId, setDelId] = useState(0);

  const { delMembers } = useMemberStore();
  const { delCafe } = useCafeStore();
  const navi = useNavigate();

  let datas = {};

  if (type == "member") {
    datas = { del: delMembers }
  } else if (type == "cafe") {
    datas = { del: delCafe }
  }

  const { del } = datas;

  const handleDelId = (e) => {
    setDelId(parseInt(e.target.value));
  };

  const handleDelIdBtn = () => {
    del(delId);
    setDelId(0);
    navi("/" + type + "/list");

  };

  return (
    <div className={styles.container}>
      <input
        type="text"
        value={delId || ""}
        onChange={handleDelId}
        placeholder="삭제할 id"
      ></input>
      <button onClick={handleDelIdBtn}>삭제</button>
    </div>
  );
};
