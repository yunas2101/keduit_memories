import { useLocation, useNavigate } from "react-router-dom";
import styles from "./Mypage.module.css";
import { useEffect } from "react";
import axios from 'axios';
import { useState } from "react";

export const Mypage = () => {
    const navi = useNavigate();

    const loc = useLocation();
    const myInfo = loc.state; // Index에서 navi로 보내줌 



    return (
        <div className={styles.container}>
            <table>
                <thead>
                    <tr>
                        <th colSpan={2}>마이페이지</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>ID</td>
                        <td>{myInfo.id}</td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td>{myInfo.name}</td>
                    </tr>
                    <tr>
                        <td colSpan={2} align="center">
                            <button
                                onClick={() => {
                                    navi("/");
                                }}
                            >
                                홈으로
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
};
