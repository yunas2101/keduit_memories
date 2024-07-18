import './App.css';
import styles from './App.module.css';
import Input from './components/Input/Input.jsx';
import Index from './components/Index/Index.jsx';
import Output from './components/Output/Output.jsx';
import { useState } from 'react';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';



function App() {

  const [menus, setMenus] = useState([
    { id: 1001, name: "Americano", price: 2000 },
    { id: 1002, name: "Cafe Latte", price: 3500 },
    { id: 1003, name: "Cafe Mocha", price: 4000 },
    { id: 1004, name: "Orange Juice", price: 5000 },
    { id: 1005, name: "Mango Juice", price: 6000 },
  ])

  const [search, setSearch] = useState(menus);

  return (
    <div className={styles.container}>
      <Router>
        <div className={styles.box}>
          <Routes>
            <Route path="/" element={<Index />} />
            <Route path='input' element={<Input setMenus={setMenus} setSearch={setSearch} />} />
            <Route path='output' element={<Output setMenus={setMenus} menus={menus} search={search} setSearch={setSearch} />} />
          </Routes>
        </div>
      </Router>
    </div>
  );
}

export default App;
