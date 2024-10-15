import { create } from 'zustand';


export const useMemberStore = create((set) => ({
    member: []
}))