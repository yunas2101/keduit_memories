import { useNavigate } from "react-router-dom";
import { useCafeStore, useMemberStore } from "../../../../store/store";
import styles from "./Add.module.css";
import { useState } from 'react';

export const Add = ({ type }) => {



    const { addMembers, membersKey, membersKeySet } = useMemberStore();
    const { addCafe, cafeKey, cafeKeySet } = useCafeStore();
    const navi = useNavigate();

    let datas = {};

    if (type == "member") {
        datas = { add: addMembers, key: membersKey, set: membersKeySet }
    } else if (type == "cafe") {
        datas = { add: addCafe, key: cafeKey, set: cafeKeySet }
    }

    const { add, key, set } = datas;

    const [data, setData] = useState(set);

    const handleAdd = (e) => {
        let { name, value } = e.target;
        if (name == "id") value = parseInt(value);
        setData((prev) => {
            return (
                { ...prev, [name]: value }
            )
        })
    }

    const handleAddBtn = () => {
        add(data); // zustand
        setData(set);

        // navi("/" + type + "/list")
        navi(`/${type}/list`);
    }

    return (
        <div className={styles.container}>

            {
                key.map((item, i) => {
                    return (
                        <input type="text" name={item} value={data[item] || ''} onChange={handleAdd} placeholder={"추가할 " + item} />
                    )
                })
            }
            {/* 
            <input type="text" name="id" value={data.id || ''} onChange={handleAdd} placeholder="추가할 id"></input>
            <input type="text" name="name" value={data.name} onChange={handleAdd} placeholder="추가할 name"></input>
            <input type="text" name="email" value={data.email} onChange={handleAdd} placeholder="추가할 email"></input>
            <input type="text" name="role" value={data.role} onChange={handleAdd} placeholder="추가할 role"></input> */}

            <button onClick={handleAddBtn}>추가</button>
        </div>
    )
}