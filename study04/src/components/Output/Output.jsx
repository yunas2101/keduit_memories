import styles from "./Output.module.css";

const Output = ({ setPage, menus }) => {
  return (
    <div className={styles.output}>
      <div className={styles.header}>
        <span>Output</span>
      </div>
      <div className={styles.body}>
        <table border={1}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            {
              menus.map((menu, i) => {
                return (
                  <tr key={i}>
                    <td>{menu.id}</td>
                    <td>{menu.name}</td>
                    <td>{menu.price}</td>
                  </tr>
                )
              })
            }
          </tbody>
        </table>
      </div>
      <div className={styles.footer}>
        <button onClick={() => { setPage("/"); }}>
          첫페이지로
        </button>
      </div>
    </div>
  );
};

export default Output;
