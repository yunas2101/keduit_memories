import { useNavigate } from "react-router-dom";
import { useCafeStore, useMemberStore } from "../../../../store/store";
import styles from "./List.module.css";

export const List = ({ type }) => {

    const navi = useNavigate();

    const { membersKey, members } = useMemberStore();
    const { cafeKey, cafe } = useCafeStore();

    let datas = {};

    if (type == "member") {
        datas = { data: members, key: membersKey };

    } else if (type == "cafe") {
        datas = { data: cafe, key: cafeKey };
    }
    const { data, key } = datas;

    return (
        <div className={styles.container}>
            <table border={1}>
                <thead>
                    <tr>
                        {
                            key.map((item, i) => {
                                return (
                                    <td key={i}>{item}</td>
                                )
                            })
                        }
                    </tr>
                </thead>
                <tbody>
                    {
                        data.map((item, i) => {
                            return (
                                <tr key={i}>
                                    {
                                        key.map((index, i) => {
                                            return (
                                                <td key={i}>{item[index]}</td>
                                            )
                                        })
                                    }
                                </tr>
                            )
                        })
                    }
                </tbody>
            </table>
        </div>
    )
};
