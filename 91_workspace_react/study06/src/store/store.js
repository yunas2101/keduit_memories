import { create } from 'zustand';

export const useStore = create((set) => ({
    // set : useStore 수정하기 위한 함수
    str: 'Hello',
    setStr: (param) => set({ str: param })
}));
// export default useStore; 해도됨. 근데 얘만 내보낼때만 가능 


export const useArrayStore = create((set) => ({
    arr: ["Apple", "Orange", "Mango"],
    add: (param) => set(prev => ({ arr: [...prev.arr, param] })) // arr에 배열 넣어야 함
}))


export const useContactStore = create((set) => ({
    contact: [
        { id: 1001, name: "Tom", contact: "01011112222" },
        { id: 1002, name: "Bob", contact: "01033335555" }
    ],
    addContact: (param) => set((prev) => ({ contact: { ...prev.contact, param } })),
    delContact: (param) => set((prev) => ({ contact: prev.contact.filter((contact) => contact.id != "1001") }))
}))
// prev."contact".filter <= .contact  신경써서 적어주기 