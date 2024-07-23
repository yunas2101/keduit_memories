import { useState } from "react";
import styles from "./Index.module.css";
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import { useAuthStore } from "../../store/store";

export const Index = () => {

    const { loginID, setLoginID } = useAuthStore();
    const navi = useNavigate();

    const [auth, setAuth] = useState({ id: '', pw: '' });

    // 입력 (로그인)
    const handleChange = (e) => {
        const { name, value } = e.target;
        setAuth(prev => ({ ...prev, [name]: value }));
    }
    const handleLogin = () => {
        axios.post(`http://192.168.1.15/auth`, auth).then((resp) => { // auth라는곳에 보낼 것임
            // 서버로부터 성공코드 반환 시 실행

            sessionStorage.setItem("loginID", resp.data);
            setLoginID(resp.data);  // resp로 돌아오는 data를 setLoginID로 loginID에 담겠다.
            // zustand 메모리 저장소에 보관 -> F5 한방에 초기화 된다.

            navi("/");

        }).catch(err => {  // 서버로부터 오류코드 반환 시 실행
            alert("ID 또는 PW를 다시 확인해주세요");
        })
    }

    return (

        <>
            {loginID ? <div><h1>{loginID}님 환영합니다.</h1></div> :
                <table>
                    <thead>
                        <tr>
                            <th>로그인</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" name="id" value={auth.id} onChange={handleChange} placeholder="input id"></input></td>
                        </tr>
                        <tr>
                            <td><input type="password" name="pw" value={auth.pw} onChange={handleChange} placeholder="input pw"></input></td>
                        </tr>
                        <tr>
                            <td align="center">
                                <button onClick={handleLogin}>로그인</button>
                                <button onClick={() => { navi("/member/signup") }}>회원가입</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            }

        </>
    )
}