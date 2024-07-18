import styles from "./Content.module.css";
import { useNavigate } from 'react-router-dom';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Sub from './../sub/Sub';


const Content = ({ content }) => {

    const navi = useNavigate();

    return (
        <div className={styles.container}>
            <div className={styles.tabs}>
                <div className={styles.tab} onClick={() => { navi("list") }}>
                    List
                </div>
                <div className={styles.tab} onClick={() => { navi("add") }}>
                    Add
                </div>
                <div className={styles.tab} onClick={() => { navi("delete") }}>
                    Delete
                </div>
                <div className={styles.tab} onClick={() => { navi("modify") }}>
                    Modify
                </div>
            </div>
            <div className={styles.content}>
                <Routes>
                    <Route path="/" element={<Sub content={"Listing"} />} />
                    <Route path="list" element={<Sub content={"Listing"} />} />
                    <Route path="add" element={<Sub content={"Add"} />} />
                    <Route path="delete" element={<Sub content={"Delete"} />} />
                    <Route path="modify" element={<Sub content={"Modify"} />} />
                </Routes>
            </div>
        </div>
    );
};

export default Content;
