
import { useEffect, useState } from 'react';
import './App.css';


const CompB = () => {
  useEffect(() => {
    console.log("컴포턴트B가 마운트 될 때 및 의존성 배열이 변경될 때 실행됨.");

    return () => {
      console.log("컴포넌트B가 언마운트 될 때 및 의존성 배열이 변경될 때 실행됨.");
    }
  });

  return (<div>CompB</div>)

}

const CompA = () => {
  useEffect(() => {
    console.log("컴포턴트A가 마운트 될 때 및 의존성 배열이 변경될 때 실행됨.");

    return () => { // clean up 함수
      console.log("컴포넌트A가 언마운트 될 때 및 의존성 배열이 변경될 때 실행됨.");
    }
  });

  return (<div>CompA</div>)

}



// function App() {

//   // useEffect : 코드 실행 타이밍을 지정하는 함수
//   // 컴포넌트가 마운트 될 때 실행 할 함수 지정
//   // 컴포넌트가 언마운트 될 때 실행 할 함수 지정
//   // 의존성 배열 내의 값이 변경될 때 실행 

//   const [comp, setComp] = useState("A");

//   const handleChange = () => {
//     if (comp == "A") setComp("B");
//     else setComp("A");
//   }

//   return (
//     <div className="App">
//       {comp == "A" && <CompA />}
//       {comp == "B" && <CompB />}
//       <button onClick={handleChange}>Comp 변경</button>
//     </div>
//   );
// }


function App() {
  console.log("useEffect 외부 코드");

  useEffect(() => {
    console.log("useEffect 내부 코드");
  }, [count]);
  // useEffect 의 2번 파라미터
  // 비워둘 시 : 일반 코드와 같이 useEffect 콜백을 rerendering 시점에도 실행됨.
  // [] (의존성 배열. 빈 배열) 넣을 시 : useEffect 콜백을 마운트 될 때만 실행됨

  const [count, setCount] = useState(10);


  return (
    <div>
      <button onClick={() => setCount(count + 1)}>카운트 변경</button>

    </div>
  )
}


export default App;
