import { useState } from "react";
import styles from "./Signup.module.css";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export const Signup = () => {

    const navi = useNavigate();
    const [member, setMember] = useState({ id: '', pw: '', name: '' });

    // 추가 (회원가입)
    const handleAdd = (e) => {
        const { name, value } = e.target;
        setMember((prev) => ({ ...prev, [name]: value }))
    }
    const handleSignupBtn = () => {
        axios.post('http://192.168.1.15/members', member).then((resp) => {
            navi("/");
            setMember({ id: '', pw: '', name: '' });
        })
    }


    return (
        <table>
            <thead>
                <tr>
                    <th>회원가입</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <input type="text" name="id" value={member.id} onChange={handleAdd} placeholder="input id"></input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="pw" value={member.pw} onChange={handleAdd} placeholder="input pw"></input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="name" value={member.name} onChange={handleAdd} placeholder="input name"></input>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <button onClick={handleSignupBtn}>회원가입</button>
                        <button onClick={() => { navi("/") }}>홈으로</button>
                    </td>
                </tr>
            </tbody>

        </table>
    )

};
