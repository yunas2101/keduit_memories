import styles from "./SubContent.module.css"

const SubContent = ({ sub }) => {
    return (
        <div className={styles.container}>
            {sub}
        </div>
    )
}

export default SubContent;