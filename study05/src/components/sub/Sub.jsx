import styles from "./Sub.module.css"

const Sub = ({ content }) => {
    return (
        <div className={styles.container}>
            {content}
        </div>
    )
};

export default Sub;