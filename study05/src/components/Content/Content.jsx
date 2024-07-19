import styles from "./Content.module.css";
import { useNavigate } from 'react-router-dom';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Sub from './../sub/Sub';
import { useState } from 'react';
import List from './../List/List';
import Add from './../Add/Add';
import Delete from './../Delete/Delete';


const Content = () => {

    const [datas, setDatas] = useState([
        { id: 1, name: "홍길동", email: "hong@naver.com", role: "관리자" },
        { id: 2, name: "김철수", email: "kim@naver.com", role: "사용자" },
        { id: 3, name: "이영희", email: "lee@naver.com", role: "사용자" },
        { id: 4, name: "박영수", email: "park@naver.com", role: "사용자" },
        { id: 5, name: "최인수", email: "choi@naver.com", role: "사용자" },

    ]);

    const navi = useNavigate();

    return (
        <div className={styles.container}>
            <div className={styles.tabs}>
                <div className={styles.tab} onClick={() => { navi("list") }}>
                    목록
                </div>
                <div className={styles.tab} onClick={() => { navi("add") }}>
                    신규등록
                </div>
                <div className={styles.tab} onClick={() => { navi("modify") }}>
                    정보수정
                </div>
                <div className={styles.tab} onClick={() => { navi("delete") }}>
                    삭제
                </div>
            </div>
            <div className={styles.content}>
                <Routes>
                    <Route path="/" element={<Sub content={"회원관리"} datas={datas} setDatas={setDatas} />} />
                    <Route path="list" element={<List content={"목록"} datas={datas} setDatas={setDatas} />} />
                    <Route path="add" element={<Add content={"신규등록"} datas={datas} setDatas={setDatas} />} />
                    <Route path="modify" element={<Sub content={"정보수정"} datas={datas} setDatas={setDatas} />} />
                    <Route path="delete" element={<Delete content={"삭제"} datas={datas} setDatas={setDatas} />} />
                </Routes>
            </div>
        </div>
    );
};

export default Content;
