
import { create } from 'zustand';


const useAuthStore = create((set) => ({
    token: null,
    isAuth: false,

    login: (token) => set({ token, isAuth: true }),
    logout: () => set({ token: null, isAuth: false })

}));


export default useAuthStore;


