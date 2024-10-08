import { useState } from "react";
import styles from "./Modify.module.css";
import { useMemberStore } from "../../../../store/store";
import { useNavigate } from "react-router-dom";

export const Modify = ({ datas, setDatas }) => {
    const navi = useNavigate();

    const [mod, setMod] = useState({ id: 0, name: '', email: '', role: '' });
    const { modify } = useMemberStore();

    const handleMod = (e) => {
        let { name, value } = e.target;
        if (name == "id") value = parseInt(value);

        setMod((prev) => {
            return (
                { ...prev, [name]: value }
            )
        })
    }
    const handleModBtn = () => {
        // const updated = datas.map((data) => {
        //     if (data.id == mod.id) return mod
        //     else return data
        // })
        // setDatas(updated);

        modify(mod);
        setMod({ id: 0, name: '', email: '', role: '' })
        navi("/member/list");
    }

    return (
        <div className={styles.container}>
            <input type="text" name="id" value={mod.id || ''} onChange={handleMod} placeholder="수정할 대상의 id"></input>
            <input type="text" name="name" value={mod.name} onChange={handleMod} placeholder="name 수정"></input>
            <input type="text" name="email" value={mod.email} onChange={handleMod} placeholder="email 수정"></input>
            <input type="text" name="role" value={mod.role} onChange={handleMod} placeholder="role 수정"></input>
            <button onClick={handleModBtn}>수정</button>
        </div>
    )
};
