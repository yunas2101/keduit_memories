import { Add } from "../Member/Add/Add";
import { Delete } from "../Member/Delete/Delete";
import { List } from "../Member/List/List";
import { Modify } from "../Member/Modify/Modify";
import styles from "./Cafe.module.css";
import { Routes, Route, useNavigate } from "react-router-dom";


export const Cafe = () => {


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
                    <Route path="" element={<List type="cafe" />} />
                    <Route path="list" element={<List type="cafe" />} />
                    <Route path="add" element={<Add type="cafe" />} />
                    <Route path="modify" element={<Modify type="cafe" />} />
                    <Route path="delete" element={<Delete type="cafe" />} />
                </Routes>
            </div>
        </div>
    )
}