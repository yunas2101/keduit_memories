import logo from './logo.svg';
import './App.css';
import { Header } from './components/Header/Header';
import { Content } from './components/Content/Content';
import { BrowserRouter as Router } from 'react-router-dom';


function App() {
  return (
    <Router>
      <div className="container">
        <Header />
        <Content />
      </div>
    </Router>
  );
}

export default App;
