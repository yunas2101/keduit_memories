import './App.css';
import styles from './App.module.css';
import Input from './components/Input/Input.jsx';
import Index from './components/Index/Index.jsx';
import Output from './components/Output/Output.jsx';
import { useState } from 'react';

function App() {

  const [page, setPage] = useState("/");
  const [menus, setMenus] = useState([
    { id: 1001, name: "Americano", price: 2000 },
    { id: 1002, name: "Cafe Latte", price: 3500 },
    { id: 1003, name: "Cafe Mocha", price: 4000 },
    { id: 1004, name: "Orange Juice", price: 5000 },
    { id: 1005, name: "Mango Juice", price: 6000 },
  ])

  return (
    <div className={styles.container}>
      <div className={styles.box}>
        {page == "/" && <Index setPage={setPage} />}
        {page == "input" && <Input setPage={setPage} setMenus={setMenus} />}
        {page == "output" && <Output setPage={setPage} menus={menus} />}
      </div>
    </div>
  );
}

export default App;
