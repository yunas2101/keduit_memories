import { Add } from "./Add/Add";
import { Delete } from "./Delete/Delete";
import { List } from "./List/List";
import styles from "./Member.module.css";
import { Routes, Route, useNavigate } from "react-router-dom";
import { Modify } from './Modify/Modify';

export const Member = ({ datas, setDatas }) => {

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
                    <Route path="" element={<List type="member" />} />
                    <Route path="list" element={<List type="member" />} />
                    <Route path="add" element={<Add type="member" />} />
                    <Route path="modify" element={<Modify type="member" />} />
                    <Route path="delete" element={<Delete type="member" />} />
                </Routes>
            </div>
        </div>
    )
};
