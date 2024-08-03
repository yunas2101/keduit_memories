import './App.css';
import Header from './components/Header/Header';
import Navi from './components/Navi/Navi';
import Body from './components/Body/Body';
import Footer from './components/Footer/Footer';

function App() {
  return (
    <div className="container">
      <Header />
      <Navi />
      <Body />
      <Footer />
    </div>
  );
};

export default App;
