import { create } from "zustand";

export const useMemberStore = create((set) => ({
    membersKey: ["id", "name", "email", "role"],
    membersKeySet: { id: 0, name: '', email: '', role: '' },
    members: [
        { id: 1, name: "홍길동", email: "hong@naver.com", role: "관리자" },
        { id: 2, name: "김철수", email: "kim@naver.com", role: "사용자" },
        { id: 3, name: "이영희", email: "lee@naver.com", role: "사용자" },
        { id: 4, name: "박영수", email: "park@naver.com", role: "사용자" },
        { id: 5, name: "최인수", email: "choi@naver.com", role: "사용자" },
    ],
    addMembers: (param) => set(prev => ({ members: [...prev.members, param] })),
    delMembers: (param) => set((prev) => ({ members: prev.members.filter((data) => data.id !== param) })),
    modify: (param) => set((prev) => ({
        members: prev.members.map((data) => {
            if (data.id == param.id) return param
            else return data
        })
    }))

}));

export const useCafeStore = create((set) => ({
    cafeKey: ["id", "name", "price", "type"],
    cafeKeySet: { id: 0, name: '', price: '', type: '' },
    cafe: [
        { id: 1, name: "아메리카노", price: 1500, type: "커피" },
        { id: 2, name: "카페라떼", price: 2000, type: "커피" },
        { id: 3, name: "오렌지 주스", price: 3500, type: "주스" },
        { id: 4, name: "포도 주스", price: 3500, type: "주스" },
        { id: 5, name: "망고 주스", price: 4000, type: "주스" },
    ],
    addCafe: (param) => set((prev) => ({ cafe: [...prev.cafe, param] })),
    delCafe: (param) => set((prev) => ({ cafe: prev.cafe.filter((data) => data.id !== param) }))

}))