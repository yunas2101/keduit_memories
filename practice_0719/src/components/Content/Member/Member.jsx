import { List } from "./List/List";
import styles from "./Member.module.css";
import { Routes, Route, useNavigate } from "react-router-dom";

export const Member = ({ datas }) => {

    const navi = useNavigate();

    return (
        <div className={styles.container}>
            <div className={styles.tabs}>
                <div className={styles.tab} onClick={() => { navi("list") }}>목록</div>
                <div className={styles.tab} onClick={() => { navi("add") }}>신규 등록</div>
                <div className={styles.tab} onClick={() => { navi("modify") }}>정보 수정</div>
                <div className={styles.tab} onClick={() => { navi("delete") }}>삭제</div>
            </div>
            <div className={styles.content}>
                <Routes>
                    <Route path="" element={<List datas={datas} />} />
                    <Route path="list" element={<List datas={datas} />} />
                    <Route path="add" />
                    <Route path="modify" />
                    <Route path="delete" />
                </Routes>



            </div>
        </div>
    )
};
