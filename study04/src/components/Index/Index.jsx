import styles from "./Index.module.css";

const Index = ({ setPage }) => {
  return (
    <div className={styles.index}>
      <div className={styles.header}>
        <span>Index</span>
      </div>
      <div className={styles.body}>
        <div className={styles.left}>
          <button onClick={() => { setPage("input"); }}>
            toInput
          </button>
        </div>
        <div className={styles.right}>
          <button onClick={() => { setPage("output"); }}>
            toOutput
          </button>
        </div>
      </div>
    </div>
  );
};

export default Index;
