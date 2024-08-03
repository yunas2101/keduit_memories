import './App.css';
import TableBox from './components/TableBox/TableBox';
import UlBox from './components/UlBox/UlBox';
import ImageBox from './components/ImageBox/ImageBox';

// let 은 값이 변할 수 있는 변수, const는 한번 값을 대입하면 변경이 불가한 함수
// const TableBox = function() {} // <-- ECMA6 이전 Style
// 아래와 같이 UI를 만들어 반환하는 함수를 Functional Component 라고 한다
// UI를 Functional Component로 구성할 경우, 함수처럼 재사용성을 가질 수 있음. (유지보수 비용 절감)


function App() { // Main - react app 의 시작점
  return (
    <div className="container">
      {/* {TableBox()} */}
      <TableBox />
      <UlBox />
      <ImageBox />
    </div>
  );
}

export default App;
