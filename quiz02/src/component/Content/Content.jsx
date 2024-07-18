import { useNavigate } from "react-router-dom";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import styles from "./Content.module.css";
import SubContent from "../SubContent/SubContent";

const Content = () => {
  const navi = useNavigate();

  return <div className={styles.container}>

    <div className={styles.tabs}>
      <div className={styles.tab} onClick={() => { navi("tab1") }}>
        Tab1
      </div>
      <div className={styles.tab} onClick={() => { navi("tab2") }}>
        Tab2
      </div>
      <div className={styles.tab} onClick={() => { navi("tab3") }}>
        Tab3
      </div>
    </div>
    <div className={styles.content}>
      <Routes>
        <Route path="/" element={<SubContent sub={"Hello"} />} />
        <Route path="tab1" element={<SubContent sub={"Hello"} />} />
        <Route path="tab2" element={<SubContent sub={"Hi"} />} />
        <Route path="tab3" element={<SubContent sub={"Bye"} />} />
      </Routes>
    </div>

  </div>;
};

export default Content;
