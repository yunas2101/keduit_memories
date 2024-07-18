import { useNavigate } from "react-router-dom";
import styles from "./Index.module.css";

const Index = () => {

  const navi = useNavigate();

  return (
    <div className={styles.index}>
      <div className={styles.header}>
        <span>Index</span>
      </div>
      <div className={styles.body}>
        <div className={styles.left}>
          <button onClick={() => { navi('input') }}>toInput</button>
        </div>
        <div className={styles.right}>
          <button onClick={() => { navi('output') }}>toOutput</button>
        </div>
      </div>
    </div>
  );
};

export default Index;
