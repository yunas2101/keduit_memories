import { useNavigate } from "react-router-dom";
import styles from "./SideMenu.module.css";

export const SideMenu = () => {
    const navi = useNavigate();
    return (
        <div className={styles.container}>
            <div className={styles.menu} onClick={() => { navi("/") }}>
                메인
            </div>
            <div className={styles.menu} onClick={() => { navi("/member") }}>
                회원관리
            </div>
            <div className={styles.menu} onClick={() => { navi("/cafe") }}>카페 메뉴 관리</div>
            <div className={styles.menu} onClick={() => { navi("/board") }}>게시글 관리</div>
        </div>
    );
};
