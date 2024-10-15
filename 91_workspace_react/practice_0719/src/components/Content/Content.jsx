import styles from "./Content.module.css";
import { SideMenu } from "./SideMenu/SideMenu";
import { Routes, Route } from "react-router-dom";
import { Member } from "./Member/Member";
import { Main } from "./Main/Main";
import { useState } from "react";
import { Cafe } from "./Cafe/Cafe";

export const Content = () => {
  // const [datas, setDatas] = useState([
  //   { id: 1, name: "홍길동", email: "hong@naver.com", role: "관리자" },
  //   { id: 2, name: "김철수", email: "kim@naver.com", role: "사용자" },
  //   { id: 3, name: "이영희", email: "lee@naver.com", role: "사용자" },
  //   { id: 4, name: "박영수", email: "park@naver.com", role: "사용자" },
  //   { id: 5, name: "최인수", email: "choi@naver.com", role: "사용자" },

  // ]);

  return (
    <div className={styles.container}>
      <SideMenu />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/member/*" element={<Member />} />
        <Route path="/cafe/*" element={<Cafe />} />
        {/* <Route path="board" element={<Board />} /> */}
      </Routes>
    </div>
  );
};
