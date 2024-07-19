import styles from "./List.module.css";

export const List = ({ datas }) => {
    return (
        <div className={styles.container}>
            <table border={1}>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>name</th>
                        <th>email</th>
                        <th>role</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        datas.map((item, i) => {
                            return (
                                <tr key={i}>
                                    <td>{item.id}</td>
                                    <td>{item.name}</td>
                                    <td>{item.email}</td>
                                    <td>{item.role}</td>
                                </tr>
                            )
                        })
                    }
                </tbody>
            </table>
        </div>
    )
};
